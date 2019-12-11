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
		int a = 0;
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
						tempCard = Hand[j];
					}
					continue;
				}else {
					continue;
				}
			} 
			tempCard = Hand[smallNumIndex];
			tempHand[a] = tempCard;
			a++;
			this.removeCard(tempCard);
			i = 0;
			handSize--;
		}
	}
	
	//sorts hand by suit (smallest to largest)
	public void sortBySuit() {
		int handSize = count;
		int i = 0;
		int a = 0;
		int smallSuitIndex = 0;
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
				if(tempCard.getSuit() > Hand[j].getSuit()) {
					smallSuitIndex = j;
					tempCard = Hand[j];
				}else if(tempCard.getSuit() == Hand[j].getSuit()){
					if(tempCard.getValue() > Hand[j].getValue()) {
						smallSuitIndex = j;
						tempCard = Hand[j];
					}
					continue;
				}else {
					continue;
				}
			} 
			tempCard = Hand[smallSuitIndex];
			tempHand[a] = tempCard;
			a++;
			this.removeCard(tempCard);
			i = 0;
			handSize--;
		}
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
	/** Fix This */
	public int numPairs() {
		int numpairs = 0;
		this.sortByValue();

		for(int i=0;i<Hand.length-1;i++) {
			if(Hand[i].getValue()==Hand[i+1].getValue()) {
				i++;
				numpairs++;
			}
		}
		return numpairs;
	}
	
	//True if there is a pair (two cards have the same value)
	/** Fix This */
	public boolean hasPairs() {
		int numpairs = 0;
		this.sortByValue();

		for(int i=0;i<Hand.length-1;i++) {
			if(Hand[i].getValue()==Hand[i+1].getValue()) {
				i++;
				numpairs++;
			}
		}
		if(numpairs > 0) {
			return true;
		}
		return false;
	}
	
	//True if there is a triplet (three of the same valued cards)
	public boolean hasTriplet() {
		this.sortByValue();
		int i = 0;
		int size = count;
		int tripCount = 0;
		
		while(i != 3) {
			
			for (int j = 0; j < size; j++) {
				if(Hand[i].getValue() == Hand[j].getValue() && Hand[i].getSuit() == Hand[j].getSuit()) {
					continue;
				}else if(Hand[i].getValue() == Hand[j].getValue() && Hand[i].getSuit() != Hand[j].getSuit()) {
					tripCount++;
				}else {
					continue;
				}
			}
			
			if(tripCount == 3) {
				return true;
			}
			tripCount = 0;
			i++;
		}
		return false;
	}
	
	//True if there is a Flush (all the same suit)
	public boolean hasFlush() {
		this.sortBySuit();
		int size = count;
		int i = 0;
		
		while(i != size) {
			if(this.Hand[size-1].getSuit() != this.Hand[i].getSuit()) {
				return false;
			}
			i++;
		}
		
		return true;
	}
	
	//True if there is a straight (5 value cards in a row. e.g. Ace,2,3,4,5)
	public boolean hasStraight() {
		this.sortByValue();
		int i = 1;
		int j = 0;
		
		while(i != count) {
			if((this.Hand[i].getValue()-this.Hand[j].getValue()) != 1) {
				return false;
			}
			i++;
			j++;
		}
		return true;
	}
	
	//True if there is a full house (1 triplet and 1 pair)
	public boolean hasFullHouse() {
		this.sortByValue();
		if(this.hasTriplet() == true && this.hasPairs() == true) {
			return true;
		}
		return false;
	}
	
	//True if there is a four of a kind (4 of the same value cards)
	public boolean hasFourOfAKind() {
		this.sortByValue();
		
		if(Hand[0].getValue() == Hand[1].getValue() && 
				Hand[0].getValue() == Hand[2].getValue() && Hand[0].getValue() == Hand[3].getValue()) {
			return true;
		} else if(Hand[4].getValue() == Hand[1].getValue() && 
				Hand[4].getValue() == Hand[2].getValue() && Hand[4].getValue() == Hand[3].getValue()) {
			return true;
		}
		return false;
		
	}
	
	//Value of the highest valued card
	public Card highestValue() {
		this.sortByValue();
		return Hand[4];
	}
	
	//Suit of the highest valued card
	/** Fix This */
	public Card highestDuplicate() {
		this.sortByValue();
		if(Hand[0].getValue() == 1) {
			return Hand[0];
		}
		return Hand[4];
	}
	
	/**Comparing one hand to another 
	 * 
	 * @param oppHand
	 * @return
	 * if 99 is returned == Player has won
	 * if -99 is returned == Opponent won
	 * if 66 is returned == draw
	 * 
	 */
	public int compareTo(Hand oppHand) {
		this.sortByValue();
		oppHand.sortByValue();
		
		boolean draw = true;
		for(int i = 0; i < count; i++) {
			if(this.Hand[i] != oppHand.Hand[i]) {
				draw = false;
			}
		}
		if(draw == true) {
			return 66;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
