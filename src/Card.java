import java.util.Comparator;

public class Card {
	
	public final static int Clubs = 0;
	public final static int Diamonds = 1;
	public final static int Hearts = 2;
	public final static int Spades = 3;
	
	public final static int Ace = 1;
	public final static int Jack = 11;
	public final static int Queen = 12;
	public final static int King = 13;
	
private final int suit;
	
	private final int value;
	
	public Card() {
		suit = 0;
		value = 1;
	}
	
	public Card(int theSuit, int theValue) {
		this.value = theValue;
		this.suit = theSuit;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public String suitToString() {
		switch(suit) {
			case 0: return "Clubs";
			case 1: return "Diamonds";
			case 2: return "Hearts";
			case 3: return "Spades";
			default: return "tempSuit";
		}
	}
	
	public String valueToString() {
		switch(value) {
			case 1: return "Ace";
			case 2: return "2";
			case 3: return "3";
			case 4: return "4";
			case 5: return "5";
			case 6: return "6";
			case 7: return "7";
			case 8: return "8";
			case 9: return "9";
			case 10: return "10";
			case 11: return "Jack";
			case 12: return "Queen";
			case 13: return "King";
			default: return "Joker";
		}
	}
	
	public boolean equals(Card c) {
		if((this.suit == c.suit) && (this.value == c.value))
			return true;
		else
			return false;
	}
	
	public String toString() {
		return this.valueToString() + " of " + this.suitToString();
	}
	
	/**
	 * @param c - card to be compared
	 * @return -1 if first card (this) is bigger,
	 *          0 if cards are equal
	 *          1 if second card (c) is bigger.
	 */
	public int compareTo(Card c) {
		if(this.suit > c.suit)
			return -1;
		else if(this.suit < c.suit)
			return 1;
		else if(this.value > c.value)
			return -1;
		else if(this.value < c.value)
			return 1;
		else
			return 0;
	}
	
	public static Comparator<Card> CVC = new Comparator<Card>() {
		
		public int compare(Card c1, Card c2) {
			return (Integer.valueOf(c1.value).compareTo(Integer.valueOf(c2.value)));
		}
		
	};
	
	public static Comparator<Card> CSC = new Comparator<Card>() {
		
		public int compare(Card c1, Card c2) {
			return (Integer.valueOf(c1.suit).compareTo(Integer.valueOf(c2.suit)));
		}
		
	};
	
	
	
	
}
