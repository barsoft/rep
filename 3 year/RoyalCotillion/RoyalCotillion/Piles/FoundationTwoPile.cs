using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace RoyalCotillion
{
    public class FoundationTwoPile : Pile
    {
        public FoundationTwoPile(Game game)
            : base(game)
        {
            Back.CardType = CardType.FoundationTwo;
        }

        public override void Update()
        {
            base.Update();

            foreach (Card card in cards)
            {
                card.CanMove = false;
            }
        }

        public override bool Add(Card card)
        {

            switch (cards.Count)
            {
                case 0:
                    {
                        if (card.CardType.Sign == CardSign.Two)
                        {
                            break;
                        }
                        return false;
                    }
                case 1:
                    {
                        if (card.CardType.Sign == CardSign.Four)
                        {
                            break;
                        }
                        return false;
                    }
                case 2:
                    {
                        if (card.CardType.Sign == CardSign.Six)
                        {
                            break;
                        }
                        return false;
                    }
                case 3:
                    {
                        if (card.CardType.Sign == CardSign.Eight)
                        {
                            break;
                        }
                        return false;
                    }
                case 4:
                    {
                        if (card.CardType.Sign == CardSign.Ten)
                        {
                            break;
                        }
                        return false;
                    }
                case 5:
                    {
                        if (card.CardType.Sign == CardSign.Queen)
                        {
                            break;
                        }
                        return false;
                    }
                case 6:
                    {
                        if (card.CardType.Sign == CardSign.Ace)
                        {
                            break;
                        }
                        return false;
                    }
                case 7:
                    {
                        if (card.CardType.Sign == CardSign.Three)
                        {
                            break;
                        }
                        return false;
                    }
                case 8:
                    {
                        if (card.CardType.Sign == CardSign.Five)
                        {
                            break;
                        }
                        return false;
                    }
                case 9:
                    {
                        if (card.CardType.Sign == CardSign.Seven)
                        {
                            break;
                        }
                        return false;
                    }
                case 10:
                    {
                        if (card.CardType.Sign == CardSign.Nine)
                        {
                            break;
                        }
                        return false;
                    }
                case 11:
                    {
                        if (card.CardType.Sign == CardSign.Jack)
                        {
                            break;
                        }
                        return false;
                    }
                case 12:
                    {
                        if (card.CardType.Sign == CardSign.King)
                        {
                            break;
                        }
                        return false;
                    }
                default:
                    return false;
            }
            cards.Add(card);
            Update();
            return true;
        }
    }
}
