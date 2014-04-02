using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RoyalCotillion
{
    public class ReservePile : Pile
    {
        public ReservePile(Game game)
            : base(game)
        {
            this.IntervalX = 0;
            this.IntervalY = 30;
        }
    }
}
