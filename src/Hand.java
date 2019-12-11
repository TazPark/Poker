import java.util.ArrayList;

public class Hand {
	
	private Card[] Hand;
	private int count;
	
	Hand(){
		Hand = new Card[5];
	}
	
	//clears the hand of all cards
	public void clear() {
		
	}
	
	//adds a new card to hand
	public void addCard(Card c) {
		for(int i=0 ; i< this.Hand.length; i++){ 
			if (Hand[i] == null){
				this.Hand[i] = c;
				count = count + 1;
				break;
			}
		 }
	}
	
	//removes card from hand
	public void removeCard(Card c) {
		for(int i=0 ; i<this.Hand.length; i++){ 
			if (this.Hand[i].equals(c)){
				this.Hand[i] = null;
				count = count-1;
			}
		}
	}
	
	//sorts hand by value (Smallest to largest)
	public void sortByValue() {
		
		int handSize = count;
		int i = 0;
		int smallNumIndex = 0;
		Card tempCard = new Card();
		Card[] tempHand = new Card[5];
		
		while(handSize > 0) {
			while(Hand[i] != null) {
				i++;
				continue;
			}
			tempCard = Hand[i];
			for(int j = 0; j < handSize; j++) {
				if(this.Hand[j] == null) {
					continue;
				}
				if(tempCard.getValue() > Hand[j].getValue()) {
					smallNumIndex = j;
					tempCard = Hand[j];
				}else if(tempCard.getValue() == Hand[j].getValue()){
					if(tempCard.getSuit() > Hand[j].getSuit()) {
						smallNumIndex = j;
					}
					continue;
				}else {
					continue;
				}
			} 
			tempCard = Hand[smallNumIndex];
			tempHand[i] = tempCard;
			this.removeCard(tempCard);
			i = 0;
			handSize--;
		}
	}
	
	//sorts hand by suit
	public void sortBySuit() {
		
	}
	
	//prints the cards in hand
	public void printHand() {
		
	}
	
	//gets the number of cards
	public int cardCount() {
		return count;
	}
	
	/**
	 * comparisons
	 * @return
	 */
	
	//checks to see the number of pairs (# of cards where they are the same)
	public int numPairs() {
		
	}
	
	//True if there is a pair (two cards have the same value)
	public boolean hasPairs() {
		
	}
	
	//True if there is a triplet (three of the same valued cards)
	public boolean hasTriplet() {
		
	}
	
	//True if there is a Flush
	public boolean hasFlush() {
		
	}
	
	//True if there is a straight (5 value cards in a row. e.g. Ace,2,3,4,5)
	public boolean hasStraight() {
		
	}
	
	//True if there is a full house (1 triplet and 1 pair)
	public boolean hasFullHouse() {
		
	}
	
	//True if there is a four of a kind (4 of the same value cards)
	public boolean hasFourOfAKind() {
		
	}
	
	//Value of the highest valued card
	public int highestValue() {
		
	}
	
	//Suit of the highest valued card
	public int highestDuplicate() {
		
	}
	
	//Comparing one hand to another
	public int compareTo(Hand opHand) {
		
	}
	
}
