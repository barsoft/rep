using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace RoyalCotillion
{
    public class Card : UserControl
    {
        protected Game game;

        private CardType cardType;

        public CardType CardType
        {
            get { return cardType; }
            set
            {
                cardType = value;
                SetImageSourceFace();
            }
        }

        private Image image;

        public Image Image
        {
            get { return image; }
            set { image = value; }
        }

        private bool canMove;

        public bool CanMove
        {
            get { return canMove; }
            set { canMove = value; }
        }

        private bool canClick;

        public bool CanClick
        {
            get { return canClick; }
            set { canClick = value; }
        }

        private double captureX;

        public double CaptureX
        {
            get { return captureX; }
            set { captureX = value; }
        }
        private double captureY;

        public double CaptureY
        {
            get { return captureY; }
            set { captureY = value; }
        }

        private bool isInverted;

        public bool IsInverted
        {
            get { return isInverted; }
            set
            {
                isInverted = value;
                if (value)
                {
                    SetImageSourceCover();
                }
                else 
                {
                    SetImageSourceFace();
                }
            }
        }

        private void SetImageSourceFace()
        {
            Uri baseUri = new Uri(System.Reflection.Assembly.GetEntryAssembly().Location);
            Uri uri = new Uri(baseUri, cardType.ImageURL);

            this.Image.Source = new BitmapImage(uri);
            this.Width = CardType.Width;
            this.Height = CardType.Height;
        }

        private void SetImageSourceCover()
        {
            Uri baseUri = new Uri(System.Reflection.Assembly.GetEntryAssembly().Location);
            Uri uri = new Uri(baseUri, "textures//cards//cover.png");

            this.Image.Source = new BitmapImage(uri);
            this.Width = CardType.Width;
            this.Height = CardType.Height;
        }

        private double captureMouseX;

        private double captureMouseY;

        private bool dragProcess;

        private Storyboard storyboard;

        public Card(Game game)
        {
            this.game = game;

            this.Image = new Image();

            this.Content = Image;

            this.CardType = CardType.AceOfClubs;

            this.canMove = false;
            this.canClick = false;
            this.isInverted = false;

            this.MouseDown += Card_MouseDown;
            this.MouseUp += Card_MouseUp;
            this.game.MouseLeave += game_MouseLeave;
            Application.Current.MainWindow.MouseMove += Card_MouseMove;
        }



        void Card_MouseDown(object sender, MouseButtonEventArgs e)
        {
            if (canMove)
            {

                BringToTheFront();
                if (storyboard != null)
                {
                    if (captureX != Margin.Left || captureY != Margin.Top)
                        return;
                    storyboard.Stop();
                }

                Rect rect = new Rect(Margin.Left, Margin.Top, Width, Height);
                Point mouse = new Point(e.GetPosition(Application.Current.MainWindow).X, e.GetPosition(Application.Current.MainWindow).Y);
                if (rect.Contains(mouse))
                {
                    dragProcess = true;
                }
                captureMouseX = e.GetPosition(this).X;
                captureMouseY = e.GetPosition(this).Y;

                captureX = Margin.Left;
                captureY = Margin.Top;

                game.mainWindow.status.Content = "Card has been picked";
            }

            if (canClick)
            {
                BringToTheFront();
                game.stockPile.Remove(this);
                game.wastePile.Add(this);

                game.Serialize();
                game.Update();

                game.mainWindow.status.Content = "Card has left stock pile";
            }
        }

        void Card_MouseMove(object sender, MouseEventArgs e)
        {
            if (canMove)
            {
                Point mouse = new Point(e.GetPosition(Application.Current.MainWindow).X, e.GetPosition(Application.Current.MainWindow).Y);
                if (e.LeftButton == MouseButtonState.Pressed && dragProcess)
                {
                    this.Margin = new Thickness(e.GetPosition(Application.Current.MainWindow).X - captureMouseX, e.GetPosition(Application.Current.MainWindow).Y - captureMouseY, this.Margin.Right, this.Margin.Bottom);
                    game.mainWindow.status.Content = "Card is beeing moved";
                }
            }
        }

        void Card_MouseUp(object sender, MouseButtonEventArgs e)
        {
            if (canMove && dragProcess)
            {
                dragProcess = false;

                PlayReturnAnimation();

                TryAttach();

            }
        }


        void game_MouseLeave(object sender, MouseEventArgs e)
        {
            if (canMove && dragProcess)
            {
                dragProcess = false;

                PlayReturnAnimation();
            }
        }

        void TryAttach()
        {
            Point mouse = new Point(Mouse.GetPosition(Application.Current.MainWindow).X, Mouse.GetPosition(Application.Current.MainWindow).Y);

            game.mainWindow.status.Content = "Card has been released";

            foreach (FoundationAPile pile in game.foundationAPileList)
            {
                Rect pileRect = new Rect(pile.Left, pile.Top, pile.Width, pile.Height);

                if (pileRect.Contains(mouse))
                {
                    if (!pile.Add(this))
                        return;
                    RemoveFromList();
                    game.Serialize();
                    pile.Update();
                    this.PlayReturnAnimation();

                    game.mainWindow.status.Content = "Card has been attached to foundation pile A";
                }
            }

            foreach (FoundationTwoPile pile in game.foundationTwoPileList)
            {
                Rect pileRect = new Rect(pile.Left, pile.Top, pile.Width, pile.Height);

                if (pileRect.Contains(mouse))
                {
                    if (!pile.Add(this))
                        return;
                    RemoveFromList();
                    game.Serialize();
                    pile.Update();
                    this.PlayReturnAnimation();

                    game.mainWindow.status.Content = "Card has been attached to foundation pile 2";
                }
            }
        }

        void RemoveFromList()
        {

            if (game.stockPile.Remove(this))
            {
                game.stockPile.Update();
                return;
            }

            foreach (Pile pile in game.reservePileList)
            {
                if (pile.Remove(this))
                {
                    pile.Update();
                    return;
                }
            }

            foreach (Pile pile in game.tableauPileList)
            {
                if (pile.Remove(this))
                {
                    pile.Update();
                    return;
                }
            }

            foreach (Pile pile in game.foundationAPileList)
            {
                if (pile.Remove(this))
                {
                    pile.Update();
                    return;
                }
            }

            foreach (Pile pile in game.foundationTwoPileList)
            {
                if (pile.Remove(this))
                {
                    pile.Update();
                    return;
                }
            }
        }



        public void PlayReturnAnimation()
        {
            var animation2 = new ThicknessAnimation();
            animation2.From = Margin;
            animation2.To = new Thickness(captureX, captureY, Margin.Right, Margin.Top);
            animation2.Duration = TimeSpan.FromSeconds(0.2);
            Storyboard.SetTarget(animation2, this);
            Storyboard.SetTargetProperty(animation2, new PropertyPath(MarginProperty));

            storyboard = new Storyboard();
            storyboard.Children = new TimelineCollection { animation2 };

            storyboard.FillBehavior = FillBehavior.HoldEnd;

            storyboard.Begin();
        }


        public void BringToTheFront()
        {
            game.stockPile.BringToTheBottom();

            foreach (Pile pile in game.reservePileList)
            {
                pile.BringToTheBottom();
            }

            foreach (Pile pile in game.tableauPileList)
            {
                pile.BringToTheBottom();
            }

            foreach (Pile pile in game.foundationAPileList)
            {
                pile.BringToTheBottom();
            }

            foreach (Pile pile in game.foundationTwoPileList)
            {
                pile.BringToTheBottom();
            }

            Canvas.SetZIndex(this, 1);
        }

        public void BringToTheBottom()
        {
            Canvas.SetZIndex(this, 0);
        }


        public SerializedCard ToSerializedCard()
        {
            SerializedCard sc = new SerializedCard();
            sc.cardType = cardType;
            return sc;
        }
    }
}
