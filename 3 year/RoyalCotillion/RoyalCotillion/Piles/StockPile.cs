using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RoyalCotillion
{
    [Serializable]
    public class StockPile : Pile
    {
        public StockPile(Game game)
            : base(game)
        {

        }

        public override void Update()
        {
            base.Update();

            foreach (Card card in cards)
            {
                card.CanMove = false;
                card.IsInverted = true;
            }

            if (cards.Count > 0)
                this.cards[cards.Count - 1].CanClick = true;
        }

       
    }
}
