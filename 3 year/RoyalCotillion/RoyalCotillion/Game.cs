using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;
using System.Timers;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Threading;


namespace RoyalCotillion
{
    public class Game : UserControl
    {
        public MainWindow mainWindow;

        public StockPile stockPile;

        public List<TableauPile> tableauPileList;

        public List<ReservePile> reservePileList;

        public Pile wastePile;

        public List<FoundationAPile> foundationAPileList;

        public List<FoundationTwoPile> foundationTwoPileList;

        private Canvas canvas;

        public Canvas Canvas
        {
            get { return canvas; }
            set { canvas = value; }
        }

        public Game(MainWindow mainWindow)
        {
            this.mainWindow = mainWindow;
            this.Loaded += Game_Loaded;

            this.mainWindow.edit.SubmenuOpened+=edit_SubmenuOpened;

            canvas = new Canvas();

            Uri baseUri = new Uri(System.Reflection.Assembly.GetEntryAssembly().Location);

            Uri uri = new Uri(baseUri, "textures//back.jpg");

            canvas.Background = new ImageBrush(new BitmapImage(uri));
            Content = canvas;


        }

        void edit_SubmenuOpened(object sender, RoutedEventArgs e)
        {
            if (cur > 1)
                mainWindow.undo.IsEnabled = true;
            else
                mainWindow.undo.IsEnabled = false;

            if (cur < num)
                mainWindow.redo.IsEnabled = true;
            else
                mainWindow.redo.IsEnabled = false;
        }



        void Game_Loaded(object sender, RoutedEventArgs e)
        {
           

            Reset();
        }

        public void Update()
        {
            if (tableauPileList != null)
            {
                int cardWidth = 100;
                int cardHeight = 150;

                float coef = 1.2f;

                int center = (int)(Application.Current.MainWindow.Height / 2 - (4 * cardHeight * coef) / 2);

                stockPile.Left = (int)(Application.Current.MainWindow.Width - 50 - 10 * cardWidth * coef);
                stockPile.Top = (int)(center + 0 * cardHeight * coef); ;
                stockPile.Update();

                wastePile.Left = (int)(Application.Current.MainWindow.Width - 50 - 9 * cardWidth * coef);
                wastePile.Top = stockPile.Top;
                wastePile.Update();

                for (int i = 0; i < 4; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        tableauPileList[i * 4 + j].Left = (int)(Application.Current.MainWindow.Width - 50 - (i + 1) * cardWidth * coef);
                        tableauPileList[i * 4 + j].Top = (int)(center + (j) * cardHeight * coef);
                        tableauPileList[i * 4 + j].Update();
                    }
                }

                for (int i = 0; i < 4; i++)
                {

                    reservePileList[i].Left = (int)(Application.Current.MainWindow.Width - 50 - (i + 7) * cardWidth * coef);
                    reservePileList[i].Top = (int)(center + cardHeight * coef);
                    reservePileList[i].Update();

                }

                for (int i = 0; i < 4; i++)
                {
                    foundationAPileList[i].Left = (int)(Application.Current.MainWindow.Width - 50 - 6 * cardWidth * coef);
                    foundationAPileList[i].Top = (int)(center + (i) * cardHeight * coef);
                    foundationAPileList[i].Update();
                }
                for (int i = 0; i < 4; i++)
                {
                    foundationTwoPileList[i].Left = (int)(Application.Current.MainWindow.Width - 50 - 5 * cardWidth * coef);
                    foundationTwoPileList[i].Top = (int)(center + (i) * cardHeight * coef);
                    foundationTwoPileList[i].Update();
                }

            }
        }

        DispatcherTimer timer;

        public void Reset()
        {
            canvas.Children.Clear();

            stockPile = new StockPile(this);

            for (int j = 1; j < CardType.GetTypes().Count - 3; j++)
            {
                Card card = new Card(this);
                card.CardType = CardType.GetTypes()[j];
                stockPile.Add(card);
            }

            wastePile = new Pile(this);

            tableauPileList = new List<TableauPile>();

            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    TableauPile tableauPile = new TableauPile(this);

                    Card card = stockPile.GetRandomCard();

                    tableauPile.Add(card);
                    stockPile.Remove(card);

                    tableauPile.Update();
                    tableauPileList.Add(tableauPile);
                }
            }


            reservePileList = new List<ReservePile>();

            for (int i = 0; i < 4; i++)
            {
                ReservePile reservePile = new ReservePile(this);
                for (int j = 0; j < 3; j++)
                {
                    Card card = stockPile.GetRandomCard();

                    reservePile.Add(card);
                    stockPile.Remove(card);

                    reservePile.Update();
                }
                reservePileList.Add(reservePile);
            }

            foundationAPileList = new List<FoundationAPile>();


            for (int j = 0; j < 4; j++)
            {
                FoundationAPile tableauPile = new FoundationAPile(this);

                tableauPile.Update();
                foundationAPileList.Add(tableauPile);
            }

            foundationTwoPileList = new List<FoundationTwoPile>();

            for (int j = 0; j < 4; j++)
            {
                FoundationTwoPile foundationPile = new FoundationTwoPile(this);

                foundationPile.Update();
                foundationTwoPileList.Add(foundationPile);
            }

            Update();

            DateTime startTime = DateTime.Now;

            if (timer != null)
            {
                timer.Stop();
                timer = null;
            }

            timer = new DispatcherTimer(new TimeSpan(0, 0, 1), DispatcherPriority.Normal, delegate
           {
               TimeSpan ts = DateTime.Now.Subtract(startTime);
               mainWindow.time.Content = ts.ToString("hh") + ":" + ts.ToString("mm") + ":" + ts.ToString("ss");
           }, this.Dispatcher);


            Serialize();
        }

        int num;
        int cur;

        public void Serialize()
        {
            cur++;
            num = cur;

            string[] files = Directory.GetFiles("steps//", "*.ser*");

            for (int i = 0; i < files.Length; i++)
            {
                if (Int32.Parse(files[i].Replace("steps//step", "").Replace(".ser", "")) > num)
                {
                    File.Delete(files[i]);
                }
            }


            FileStream fs = new FileStream("steps//step" + num + ".ser", FileMode.Create, FileAccess.Write, FileShare.ReadWrite);
            BinaryFormatter bf = new BinaryFormatter();

            SerializedData sd = new SerializedData();
            sd.stockPile = stockPile.ToSerializedPile();
            sd.tableauPileList = tableauPileList.Select(i => i.ToSerializedPile()).ToList();
            sd.foundationAPileList = foundationAPileList.Select(i => i.ToSerializedPile()).ToList();
            sd.foundationTwoPileList = foundationTwoPileList.Select(i => i.ToSerializedPile()).ToList();
            sd.reservePileList = reservePileList.Select(i => i.ToSerializedPile()).ToList();
            sd.wastePile = wastePile.ToSerializedPile();
            bf.Serialize(fs, sd);
            fs.Close();
        }

        public void Deserialize(int j)
        {
            FileStream fs = new FileStream("steps//step" + j + ".ser", FileMode.Open, FileAccess.Read, FileShare.ReadWrite);
            BinaryFormatter bf = new BinaryFormatter();

            SerializedData sd = (bf.Deserialize(fs) as SerializedData);

            stockPile.FromSerializedPile(sd.stockPile);
            wastePile.FromSerializedPile(sd.wastePile);


            for (int i = 0; i < tableauPileList.Count; i++)
            {
                tableauPileList[i].FromSerializedPile(sd.tableauPileList[i]);
            }

            for (int i = 0; i < foundationAPileList.Count; i++)
            {
                foundationAPileList[i].FromSerializedPile(sd.foundationAPileList[i]);
            }
            for (int i = 0; i < foundationTwoPileList.Count; i++)
            {
                foundationTwoPileList[i].FromSerializedPile(sd.foundationTwoPileList[i]);

            }
            for (int i = 0; i < reservePileList.Count; i++)
            {
                reservePileList[i].FromSerializedPile(sd.reservePileList[i]);
            }

            Update();

            fs.Close();
        }

        public void Undo()
        {
            if (cur > 1)
            {
                cur--;
                Deserialize(cur);
                mainWindow.status.Content = "Undo operation succeed";
            }
        }

        public void Redo()
        {
            if (cur < num)
            {
                cur++;
                Deserialize(cur);
                mainWindow.status.Content = "Redo operation succeed";
            }
        }
    }
}
