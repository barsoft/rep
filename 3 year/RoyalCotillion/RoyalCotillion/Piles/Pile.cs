using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace RoyalCotillion
{
    public class Pile
    {
        protected List<Card> cards;

        private Game game;

        public Game Game
        {
            get { return game; }
            set { game = value; }
        }

        private int left;

        public int Left
        {
            get { return left; }
            set { left = value; }
        }

        private int top;

        public int Top
        {
            get { return top; }
            set { top = value; }
        }


        public int Right
        {
            get { return left + width; }
        }


        public int Bottom
        {
            get { return top + height; }
        }

        private int width = 100;

        public int Width
        {
            get { return width; }
            set { width = value; }
        }
        private int height = 150;

        public int Height
        {
            get { return height; }
            set { height = value; }
        }

        private double intervalX;

        public double IntervalX
        {
            get { return intervalX; }
            set { intervalX = value; }
        }

        private double intervalY;

        public double IntervalY
        {
            get { return intervalY; }
            set { intervalY = value; }
        }

        private Card back;

        public Card Back
        {
            get { return back; }
            set { back = value; }
        }

        public Pile(Game game)
        {
            this.game = game;
            this.left = 0;
            this.top = 0;
            this.IntervalX = 0.5;
            this.IntervalY = 0;
            this.cards = new List<Card>();

            this.back = new Card(game);
            this.back.CardType = CardType.Empty;
            this.game.Canvas.Children.Add(back);

            this.Update();
        }

        public virtual bool Add(Card card)
        {
            this.cards.Add(card);
            Update();
            return true;
        }

        public virtual bool Remove(Card card)
        {
            var res = cards.Remove(card);
            Update();
            return res;
        }

        public virtual void Update()
        {
            int i = 0;
            for (i = 0; i < this.game.Canvas.Children.Count; i++)
            {
                if (this.game.Canvas.Children[i] is Card)
                {
                    if (cards.Contains(this.game.Canvas.Children[i]))
                    {
                        this.game.Canvas.Children.RemoveAt(i);
                        i = -1;
                    }
                }
            }

            foreach (Card card in cards)
            {
                card.CanMove = false;
                card.CanClick = false;
                card.IsInverted = false;
                this.game.Canvas.Children.Add(card);
            }

            if (cards.Count > 0)
                this.cards[cards.Count - 1].CanMove = true;

            double marginLeft = left;
            double marginTop = top;

            back.Margin = new Thickness(marginLeft, marginTop, back.Margin.Right, back.Margin.Bottom);

            foreach (Card card in cards)
            {
                card.Margin = new Thickness(marginLeft, marginTop, card.Margin.Right, card.Margin.Bottom);

                card.CaptureX = marginLeft;
                card.CaptureY = marginTop;

                marginLeft += intervalX;
                marginTop += intervalY;
            }


        }

        public Card GetRandomCard()
        {
            Random r = new Random();
            Card card = cards[r.Next(cards.Count - 1)];
            return card;
        }

        public void BringToTheBottom()
        {
            foreach (Card card in cards)
            {
                card.BringToTheBottom();
            }
        }

        public SerializedPile ToSerializedPile()
        {
            SerializedPile sp = new SerializedPile();
            sp.cards = new List<SerializedCard>();
            foreach (Card c in cards)
            {
                sp.cards.Add(c.ToSerializedCard());
            }
            return sp;
        }

        public void FromSerializedPile(SerializedPile serializedPile)
        {
            int i = 0;

            for (i = 0; i < this.game.Canvas.Children.Count; i++)
            {
                if (this.game.Canvas.Children[i] is Card)
                {
                    if (cards.Contains(this.game.Canvas.Children[i]))
                    {
                        this.game.Canvas.Children.RemoveAt(i);
                        i = -1;
                    }
                }
            }

            cards.Clear();


            foreach (SerializedCard sc in serializedPile.cards)
            {
                Card c = new Card(game);
                c.CardType = sc.cardType;
                Add(c);
            }
        }
    }
}
