using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RoyalCotillion
{
    [Serializable]
    public class SerializedData
    {
        public SerializedPile stockPile;
        public SerializedPile wastePile;
        public List<SerializedPile> tableauPileList;
        public List<SerializedPile> reservePileList;
        public List<SerializedPile> foundationAPileList;
        public List<SerializedPile> foundationTwoPileList;
    }

    [Serializable]
    public class SerializedCard
    {
        public CardType cardType;
    }

    [Serializable]
    public class SerializedPile
    {
        public List<SerializedCard> cards;
    }
}
