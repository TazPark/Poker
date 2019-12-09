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
		
	}
	
	public String toString() {
		
	}
	
	
}
