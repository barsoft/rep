using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace RoyalCotillion
{
    [Serializable]
    public enum CardColor
    {
        Red,
        Black,
        Null
    }
    [Serializable]
    public enum CardSign
    {
        Ace,
        King,
        Queen,
        Jack,
        Ten,
        Nine,
        Eight,
        Seven,
        Six,
        Five,
        Four,
        Three,
        Two,
        Null
    }

    [Serializable]
    public enum CardSuit
    {
        Clubs,
        Diamonds,
        Hearts,
        Spades,
        Null
    }

    [Serializable]
    public class CardType
    {
        /// <summary>
        /// Static members
        /// </summary>
        private static String cardsTextres = @"textures\cards\";

        #region Clubs

        /// <summary>
        /// Clubs
        /// </summary>
        private static CardType aceOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\A.png", sign = CardSign.Ace, suit = CardSuit.Clubs };

        public static CardType AceOfClubs
        {
            get { return aceOfClubs; }
            set { aceOfClubs = value; }
        }
        private static CardType kingOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\K.png", sign = CardSign.King, suit = CardSuit.Clubs };

        public static CardType KingOfClubs
        {
            get { return CardType.kingOfClubs; }
            set { CardType.kingOfClubs = value; }
        }
        private static CardType queenOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\Q.png", sign = CardSign.Queen, suit = CardSuit.Clubs };

        public static CardType QueenOfClubs
        {
            get { return CardType.queenOfClubs; }
            set { CardType.queenOfClubs = value; }
        }
        private static CardType jackOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\J.png", sign = CardSign.Jack, suit = CardSuit.Clubs };

        public static CardType JackOfClubs
        {
            get { return CardType.jackOfClubs; }
            set { CardType.jackOfClubs = value; }
        }
        private static CardType tenOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\10.png", sign = CardSign.Ten, suit = CardSuit.Clubs };

        public static CardType TenOfClubs
        {
            get { return CardType.tenOfClubs; }
            set { CardType.tenOfClubs = value; }
        }
        private static CardType nineOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\9.png", sign = CardSign.Nine, suit = CardSuit.Clubs };

        public static CardType NineOfClubs
        {
            get { return CardType.nineOfClubs; }
            set { CardType.nineOfClubs = value; }
        }
        private static CardType eightOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\8.png", sign = CardSign.Eight, suit = CardSuit.Clubs };

        public static CardType EightOfClubs
        {
            get { return CardType.eightOfClubs; }
            set { CardType.eightOfClubs = value; }
        }
        private static CardType sevenOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\7.png", sign = CardSign.Seven, suit = CardSuit.Clubs };

        public static CardType SevenOfClubs
        {
            get { return CardType.sevenOfClubs; }
            set { CardType.sevenOfClubs = value; }
        }
        private static CardType sixOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\6.png", sign = CardSign.Six, suit = CardSuit.Clubs };

        public static CardType SixOfClubs
        {
            get { return CardType.sixOfClubs; }
            set { CardType.sixOfClubs = value; }
        }
        private static CardType fiveOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\5.png", sign = CardSign.Five, suit = CardSuit.Clubs };

        public static CardType FiveOfClubs
        {
            get { return CardType.fiveOfClubs; }
            set { CardType.fiveOfClubs = value; }
        }
        private static CardType fourOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\4.png", sign = CardSign.Four, suit = CardSuit.Clubs };

        public static CardType FourOfClubs
        {
            get { return CardType.fourOfClubs; }
            set { CardType.fourOfClubs = value; }
        }
        private static CardType threeOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\3.png", sign = CardSign.Three, suit = CardSuit.Clubs };

        public static CardType ThreeOfClubs
        {
            get { return CardType.threeOfClubs; }
            set { CardType.threeOfClubs = value; }
        }
        private static CardType twoOfClubs = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Clubs\2.png", sign = CardSign.Two, suit = CardSuit.Clubs };

        public static CardType TwoOfClubs
        {
            get { return CardType.twoOfClubs; }
            set { CardType.twoOfClubs = value; }
        }
        #endregion

        #region Diamonds
        /// <summary>
        /// Diamonds
        /// </summary>
        private static CardType aceOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\A.png", sign = CardSign.Ace, suit = CardSuit.Diamonds };

        public static CardType AceOfDiamonds
        {
            get { return CardType.aceOfDiamonds; }
            set { CardType.aceOfDiamonds = value; }
        }
        private static CardType kingOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\K.png", sign = CardSign.King, suit = CardSuit.Diamonds };

        public static CardType KingOfDiamonds
        {
            get { return CardType.kingOfDiamonds; }
            set { CardType.kingOfDiamonds = value; }
        }
        private static CardType queenOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\Q.png", sign = CardSign.Queen, suit = CardSuit.Diamonds };

        public static CardType QueenOfDiamonds
        {
            get { return CardType.queenOfDiamonds; }
            set { CardType.queenOfDiamonds = value; }
        }
        private static CardType jackOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\J.png", sign = CardSign.Jack, suit = CardSuit.Diamonds };

        public static CardType JackOfDiamonds
        {
            get { return CardType.jackOfDiamonds; }
            set { CardType.jackOfDiamonds = value; }
        }
        private static CardType tenOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\10.png", sign = CardSign.Ten, suit = CardSuit.Diamonds };

        public static CardType TenOfDiamonds
        {
            get { return CardType.tenOfDiamonds; }
            set { CardType.tenOfDiamonds = value; }
        }
        private static CardType nineOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\9.png", sign = CardSign.Nine, suit = CardSuit.Diamonds };

        public static CardType NineOfDiamonds
        {
            get { return CardType.nineOfDiamonds; }
            set { CardType.nineOfDiamonds = value; }
        }
        private static CardType eightOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\8.png", sign = CardSign.Eight, suit = CardSuit.Diamonds };

        public static CardType EightOfDiamonds
        {
            get { return CardType.eightOfDiamonds; }
            set { CardType.eightOfDiamonds = value; }
        }
        private static CardType sevenOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\7.png", sign = CardSign.Seven, suit = CardSuit.Diamonds };

        public static CardType SevenOfDiamonds
        {
            get { return CardType.sevenOfDiamonds; }
            set { CardType.sevenOfDiamonds = value; }
        }
        private static CardType sixOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\6.png", sign = CardSign.Six, suit = CardSuit.Diamonds };

        public static CardType SixOfDiamonds
        {
            get { return CardType.sixOfDiamonds; }
            set { CardType.sixOfDiamonds = value; }
        }
        private static CardType fiveOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\5.png", sign = CardSign.Five, suit = CardSuit.Diamonds };

        public static CardType FiveOfDiamonds
        {
            get { return CardType.fiveOfDiamonds; }
            set { CardType.fiveOfDiamonds = value; }
        }
        private static CardType fourOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\4.png", sign = CardSign.Four, suit = CardSuit.Diamonds };

        public static CardType FourOfDiamonds
        {
            get { return CardType.fourOfDiamonds; }
            set { CardType.fourOfDiamonds = value; }
        }
        private static CardType threeOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\3.png", sign = CardSign.Three, suit = CardSuit.Diamonds };

        public static CardType ThreeOfDiamonds
        {
            get { return CardType.threeOfDiamonds; }
            set { CardType.threeOfDiamonds = value; }
        }
        private static CardType twoOfDiamonds = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Diamonds\2.png", sign = CardSign.Two, suit = CardSuit.Diamonds };

        public static CardType TwoOfDiamonds
        {
            get { return CardType.twoOfDiamonds; }
            set { CardType.twoOfDiamonds = value; }
        }
        #endregion

        #region Hearts
        /// <summary>
        /// Hearts
        /// </summary>
        private static CardType aceOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\A.png", sign = CardSign.Ace, suit = CardSuit.Hearts };

        public static CardType AceOfHearts
        {
            get { return CardType.aceOfHearts; }
            set { CardType.aceOfHearts = value; }
        }
        private static CardType kingOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\K.png", sign = CardSign.King, suit = CardSuit.Hearts };

        public static CardType KingOfHearts
        {
            get { return CardType.kingOfHearts; }
            set { CardType.kingOfHearts = value; }
        }
        private static CardType queenOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\Q.png", sign = CardSign.Queen, suit = CardSuit.Hearts };

        public static CardType QueenOfHearts
        {
            get { return CardType.queenOfHearts; }
            set { CardType.queenOfHearts = value; }
        }
        private static CardType jackOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\J.png", sign = CardSign.Jack, suit = CardSuit.Hearts };

        public static CardType JackOfHearts
        {
            get { return CardType.jackOfHearts; }
            set { CardType.jackOfHearts = value; }
        }
        private static CardType tenOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\10.png", sign = CardSign.Ten, suit = CardSuit.Hearts };

        public static CardType TenOfHearts
        {
            get { return CardType.tenOfHearts; }
            set { CardType.tenOfHearts = value; }
        }
        private static CardType nineOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\9.png", sign = CardSign.Nine, suit = CardSuit.Hearts };

        public static CardType NineOfHearts
        {
            get { return CardType.nineOfHearts; }
            set { CardType.nineOfHearts = value; }
        }
        private static CardType eightOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\8.png", sign = CardSign.Eight, suit = CardSuit.Hearts };

        public static CardType EightOfHearts
        {
            get { return CardType.eightOfHearts; }
            set { CardType.eightOfHearts = value; }
        }
        private static CardType sevenOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\7.png", sign = CardSign.Seven, suit = CardSuit.Hearts };

        public static CardType SevenOfHearts
        {
            get { return CardType.sevenOfHearts; }
            set { CardType.sevenOfHearts = value; }
        }
        private static CardType sixOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\6.png", sign = CardSign.Six, suit = CardSuit.Hearts };

        public static CardType SixOfHearts
        {
            get { return CardType.sixOfHearts; }
            set { CardType.sixOfHearts = value; }
        }
        private static CardType fiveOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\5.png", sign = CardSign.Five, suit = CardSuit.Hearts };

        public static CardType FiveOfHearts
        {
            get { return CardType.fiveOfHearts; }
            set { CardType.fiveOfHearts = value; }
        }
        private static CardType fourOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\4.png", sign = CardSign.Four, suit = CardSuit.Hearts };

        public static CardType FourOfHearts
        {
            get { return CardType.fourOfHearts; }
            set { CardType.fourOfHearts = value; }
        }
        private static CardType threeOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\3.png", sign = CardSign.Three, suit = CardSuit.Hearts };

        public static CardType ThreeOfHearts
        {
            get { return CardType.threeOfHearts; }
            set { CardType.threeOfHearts = value; }
        }
        private static CardType twoOfHearts = new CardType() { color = CardColor.Red, imageURL = cardsTextres + @"Hearts\2.png", sign = CardSign.Two, suit = CardSuit.Hearts };

        public static CardType TwoOfHearts
        {
            get { return CardType.twoOfHearts; }
            set { CardType.twoOfHearts = value; }
        }
        #endregion

        #region Spades
        /// <summary>
        /// Spades
        /// </summary>
        private static CardType aceOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\A.png", sign = CardSign.Ace, suit = CardSuit.Spades };

        public static CardType AceOfSpades
        {
            get { return CardType.aceOfSpades; }
            set { CardType.aceOfSpades = value; }
        }
        private static CardType kingOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\K.png", sign = CardSign.King, suit = CardSuit.Spades };

        public static CardType KingOfSpades
        {
            get { return CardType.kingOfSpades; }
            set { CardType.kingOfSpades = value; }
        }
        private static CardType queenOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\Q.png", sign = CardSign.Queen, suit = CardSuit.Spades };

        public static CardType QueenOfSpades
        {
            get { return CardType.queenOfSpades; }
            set { CardType.queenOfSpades = value; }
        }
        private static CardType jackOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\J.png", sign = CardSign.Jack, suit = CardSuit.Spades };

        public static CardType JackOfSpades
        {
            get { return CardType.jackOfSpades; }
            set { CardType.jackOfSpades = value; }
        }
        private static CardType tenOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\10.png", sign = CardSign.Ten, suit = CardSuit.Spades };

        public static CardType TenOfSpades
        {
            get { return CardType.tenOfSpades; }
            set { CardType.tenOfSpades = value; }
        }
        private static CardType nineOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\9.png", sign = CardSign.Nine, suit = CardSuit.Spades };

        public static CardType NineOfSpades
        {
            get { return CardType.nineOfSpades; }
            set { CardType.nineOfSpades = value; }
        }
        private static CardType eightOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\8.png", sign = CardSign.Eight, suit = CardSuit.Spades };

        public static CardType EightOfSpades
        {
            get { return CardType.eightOfSpades; }
            set { CardType.eightOfSpades = value; }
        }
        private static CardType sevenOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\7.png", sign = CardSign.Seven, suit = CardSuit.Spades };

        public static CardType SevenOfSpades
        {
            get { return CardType.sevenOfSpades; }
            set { CardType.sevenOfSpades = value; }
        }
        private static CardType sixOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\6.png", sign = CardSign.Six, suit = CardSuit.Spades };

        public static CardType SixOfSpades
        {
            get { return CardType.sixOfSpades; }
            set { CardType.sixOfSpades = value; }
        }
        private static CardType fiveOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\5.png", sign = CardSign.Five, suit = CardSuit.Spades };

        public static CardType FiveOfSpades
        {
            get { return CardType.fiveOfSpades; }
            set { CardType.fiveOfSpades = value; }
        }
        private static CardType fourOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\4.png", sign = CardSign.Four, suit = CardSuit.Spades };

        public static CardType FourOfSpades
        {
            get { return CardType.fourOfSpades; }
            set { CardType.fourOfSpades = value; }
        }
        private static CardType threeOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\3.png", sign = CardSign.Three, suit = CardSuit.Spades };

        public static CardType ThreeOfSpades
        {
            get { return CardType.threeOfSpades; }
            set { CardType.threeOfSpades = value; }
        }
        private static CardType twoOfSpades = new CardType() { color = CardColor.Black, imageURL = cardsTextres + @"Spades\2.png", sign = CardSign.Two, suit = CardSuit.Spades };

        public static CardType TwoOfSpades
        {
            get { return CardType.twoOfSpades; }
            set { CardType.twoOfSpades = value; }
        }
        #endregion

        private static CardType foundationA = new CardType() { color = CardColor.Null, imageURL = cardsTextres + @"foundationA.png", sign = CardSign.Ace, suit = CardSuit.Null };

        public static CardType FoundationA
        {
            get { return CardType.foundationA; }
            set { CardType.foundationA = value; }
        }

        private static CardType foundationTwo = new CardType() { color = CardColor.Null, imageURL = cardsTextres + @"foundationTwo.png", sign = CardSign.Two, suit = CardSuit.Null };

        public static CardType FoundationTwo
        {
            get { return CardType.foundationTwo; }
            set { CardType.foundationTwo = value; }
        }

        private static CardType empty = new CardType() { color = CardColor.Null, imageURL = cardsTextres + @"empty.png", sign = CardSign.Null, suit = CardSuit.Null };

        public static CardType Empty
        {
            get { return CardType.empty; }
            set { CardType.empty = value; }
        }

        public static List<CardType> GetTypes()
        {
            List<CardType> cardTypes = new List<CardType>();
            foreach (var prop in typeof(CardType).GetProperties())
            {
                if (prop.GetMethod.IsStatic)
                    cardTypes.Add((CardType)prop.GetValue(null, null));
            }

            return cardTypes;
        }

        /// <summary>
        /// Dynamic members
        /// </summary>
        private String imageURL;

        public String ImageURL
        {
            get { return imageURL; }
            set { imageURL = value; }
        }

        private CardSign sign;

        public CardSign Sign
        {
            get { return sign; }
            set { sign = value; }
        }

        private CardColor color;

        public CardColor Color
        {
            get { return color; }
            set { color = value; }
        }
        private CardSuit suit;

        public CardSuit Suit
        {
            get { return suit; }
            set { suit = value; }
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
    }
}
