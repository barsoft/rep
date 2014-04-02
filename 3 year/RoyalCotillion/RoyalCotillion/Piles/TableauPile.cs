using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RoyalCotillion
{
    public class TableauPile : Pile
    {
        public TableauPile(Game game)
            : base(game)
        {
        }

        public override bool Remove(Card card)
        {
            if (this.cards.Remove(card))
            {
                Card dstCard = Game.stockPile.GetRandomCard();
                Add(dstCard);
                Game.stockPile.Remove(dstCard);
                return true;
            }
            return false;
        }
    }
}
