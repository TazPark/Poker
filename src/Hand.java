import java.util.ArrayList;

public class Hand {
	
	private Card[] Hand;
	private int size;
	
	Hand(){
		Hand = new Card[5];
		size = 0;
	}
	
	//clears the hand of all cards
	public void clear() {
		for(int j = 0 ; j< Hand.length; j++){
			Hand[j] = null; 
		}
		  size = 0;
	}
	
	//adds a new card to hand
	public void addCard(Card c) {
		for(int j=0 ; j< this.Hand.length; j++){ 
			if (Hand[j] == null){
				this.Hand[j] = c;
				size = size + 1;
				break;
			}
		 }
	}
	
	//removes card from hand
	public void removeCard(Card c) {
		for(int j=0 ; j<this.Hand.length; j++){ 
			if (this.Hand[j].equals(c)){
				this.Hand[j] = null;
				size = size-1;
			}
		}
	}
	
	//sorts hand by value (Smallest to largest)
	public void sortByValue() {
		
		int handSize = size;
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
		int handSize = size;
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
		
		for(int i = 0; i<Hand.length; i++){
			   if (Hand[i] != null){
				   System.out.println(Hand[i]);
			   }
		   }
		   System.out.println();
	   }
	
	
	//gets the number of cards
	public int cardCount() {
		return size;
	}
	
	/**
	 * comparisons
	 * @return
	 */
	
	//checks to see the number of pairs (# of cards where they are the same)
	public int numPairs() {
		int num = 0;
		this.sortByValue();

		for(int j=0;j<Hand.length-1;j++) {
			if(Hand[j].getValue()==Hand[j+1].getValue()) {
				j++;
				num++;
			}
		}
		return num;
	}
	
	//Returns the highest pair number (whether its 1 or 2 pairs)
	public int onePairNum() {
		int onePairnum = -99;
		int twoPairnum = -98;
		Card tempCard = new Card();
		this.sortByValue();
		
		if(this.numPairs() == 1) {
			for(int i = 0; i < size; i++) {
				tempCard = this.Hand[i];
				if(tempCard == null) {
					continue;
				}
				else if(tempCard.getValue() == Hand[i+1].getValue()){
					onePairnum = Hand[i+1].getValue();
					return onePairnum;
				}
			}
		}
		else if (this.numPairs() == 2) {
			for(int i = 0; i < size; i++) {
				tempCard = this.Hand[i];
				if(tempCard == null) {
					continue;
				}
				else if(tempCard.getValue() == Hand[i+1].getValue()){
					if(onePairnum == -99) {
						onePairnum = Hand[i+1].getValue();
						i++;
					}
					else {
						twoPairnum = Hand[i+1].getValue();
						return twoPairnum;
					}
				}
			}
		}
		return onePairnum;
	}
	
	//True if there is a pair (two cards have the same value)
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
		int size = this.size;
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
		int size = this.size;
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
		
		while(i != size) {
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
		int player = 0;
		int opponent = 0;
		
		boolean draw = true;
		for(int i = 0; i < size; i++) {
			if(this.Hand[i] != oppHand.Hand[i]) {
				draw = false;
			}
		}
		if(draw == true) {
			return 66;
		}
		
		//If Player has either a Royal && Straight Flush
		if(this.hasFlush() == true && this.hasStraight() == true) {
			if(this.Hand[2].getValue() == 12) {
				player = 10;
			}else {
				player = 9;	
			}
		}
		//If player has FourOfAKind
		else if(this.hasFourOfAKind() == true) {
			player = 8;
		}
		//If player has Full House
		else if(this.hasFullHouse() == true) {
			player = 7;
		}
		//If player has Flush
		else if(this.hasFlush() == true) {
			player = 6;
		}
		//If Player has Straight
		else if(this.hasStraight() == true) {
			player = 5;
		}
		//If player has ThreeOfAKind
		else if(this.hasTriplet() == true) {
			player = 4;
		}
		//If player has 2 pair
		else if(this.numPairs() == 2) {
			player = 3;
		}
		//If player has 1 pair
		else if(this.numPairs() == 1) {
			player = 2;
		}
		//if Player has nothing
		else {
			player = 0;
		}
		
		//If opponent has either a Royal && Straight Flush
		if(oppHand.hasFlush() == true && oppHand.hasStraight() == true) {
			if(oppHand.Hand[2].getValue() == 12) {
				opponent = 10;
			}else {
				opponent = 9;	
			}
		}
		//If opponent has FourOfAKind
		else if(oppHand.hasFourOfAKind() == true) {
			opponent = 8;
		}
		//If opponent has Full House
		else if(oppHand.hasFullHouse() == true) {
			opponent = 7;
		}
		//If opponent has Flush
		else if(oppHand.hasFlush() == true) {
			opponent = 6;
		}
		//If opponent has Straight
		else if(oppHand.hasStraight() == true) {
			opponent = 5;
		}
		//If opponent has ThreeOfAKind
		else if(oppHand.hasTriplet() == true) {
			opponent = 4;
		}
		//If opponent has 2 pair
		else if(oppHand.numPairs() == 2) {
			opponent = 3;
		}
		//If opponent has 1 pair
		else if(oppHand.numPairs() == 1) {
			opponent = 2;
		}
		//if opponent has nothing
		else {
			opponent = 0;
		}
		
		switch (player){
			
			//When Player has Royal Flush versus opponents hand
			case 10:
				switch(opponent) {
					case 10:
						if(this.Hand[0].getSuit() > oppHand.Hand[0].getSuit()) {
							return 99;
						} else {
							return -99;
						}
					case 9:	
						return 99;
					case 8: 
						return 99;
					case 7: 
						return 99;
					case 6: 
						return 99;
					case 5: 
						return 99;
					case 4:
						return 99;
					case 3:
						return 99;
					case 2:
						return 99;
					case 0:
						return 99;
				}
			//When Player has a Straight Flush
			case 9:
				switch(opponent) {
				case 10:
					return -99;
				case 9:
				if(this.Hand[0].getSuit() > oppHand.Hand[0].getSuit()) {
					return 99;
				} else {
					return -99;
				}
				case 8: 
					return 99;
				case 7: 
					return 99;
				case 6: 
					return 99;
				case 5: 
					return 99;
				case 4:
					return 99;
				case 3:
					return 99;
				case 2:
					return 99;
				case 0:
					return 99;
			}
			//When Player has a Four of a Kind
			case 8:
				switch(opponent) {
				case 10:
					return -99;
				case 9:	
					return -99;
				case 8:
					this.sortByValue();
					oppHand.sortByValue();
					if(this.Hand[2].getValue() > oppHand.Hand[2].getValue()) {
						return 99;
					}
					else {
						return -99;
					}
				case 7: 
					return 99;
				case 6: 
					return 99;
				case 5: 
					return 99;
				case 4:
					return 99;
				case 3:
					return 99;
				case 2:
					return 99;
				case 0:
					return 99;
			}
			//When Player has a Full House
			case 7:
				switch(opponent) {
				case 10:
					return -99;
				case 9:	
					return -99;
				case 8:
					return -99;
				case 7: 
					this.sortByValue();
					oppHand.sortByValue();
					if(this.Hand[2].getValue() > oppHand.Hand[2].getValue()) {
						return 99;
					}
					else {
						return -99;
					}
				case 6: 
					return 99;
				case 5: 
					return 99;
				case 4:
					return 99;
				case 3:
					return 99;
				case 2:
					return 99;
				case 0:
					return 99;
			}
			//When Player has a Flush
			case 6:
				switch(opponent) {
				case 10:
					return -99;
				case 9:	
					return -99;
				case 8:
					return -99;
				case 7: 
					return -99;
				case 6: 
					this.sortByValue();
					oppHand.sortByValue();
					if(this.Hand[2].getSuit() > oppHand.Hand[2].getSuit()) {
						return 99;
					} else if (this.Hand[2].getSuit() < oppHand.Hand[2].getSuit()) {
						return -99;
					}else if(this.Hand[2].getSuit() == oppHand.Hand[2].getSuit()) {
						if(this.Hand[4].getValue() > oppHand.Hand[4].getValue()) {
							return 99;
						}
						else {
							return -99;
						}
					}
				case 5: 
					return 99;
				case 4:
					return 99;
				case 3:
					return 99;
				case 2:
					return 99;
				case 0:
					return 99;
			}
			//When Player has a Straight
			case 5:
				switch(opponent) {
				case 10:
					return -99;
				case 9:	
					return -99;
				case 8:
					return -99;
				case 7: 
					return -99;
				case 6: 
					return -99;
				case 5: 
					this.sortByValue();
					oppHand.sortByValue();
					if(this.Hand[4].getValue() > oppHand.Hand[4].getValue()) {
						return 99;
					} else if(this.Hand[4].getValue() < oppHand.Hand[4].getValue()) {
						return -99;
					} else if(this.Hand[4].getValue() == oppHand.Hand[4].getValue()) {
						if(this.Hand[4].getSuit() > oppHand.Hand[4].getSuit()) {
							return 99;
						}else {
							return -99;
						}
					}
				case 4:
					return 99;
				case 3:
					return 99;
				case 2:
					return 99;
				case 0:
					return 99;
			}
			//When Player has a Three of a Kind
			case 4:
				switch(opponent) {
				case 10:
					return -99;
				case 9:	
					return -99;
				case 8:
					return -99;
				case 7: 
					return -99;
				case 6: 
					return -99;
				case 5: 
					return -99;
				case 4:
					this.sortByValue();
					oppHand.sortByValue();
					if(this.Hand[2].getValue() > oppHand.Hand[2].getValue()) {
						return 99;
					}
					else {
						return -99;
					}
				case 3:
					return 99;
				case 2:
					return 99;
				case 0:
					return 99;
			}
			//When Player has 2 Pairs
			case 3:
				switch(opponent) {
				case 10:
					return -99;
				case 9:	
					return -99;
				case 8:
					return -99;
				case 7: 
					return -99;
				case 6: 
					return -99;
				case 5: 
					return -99;
				case 4:
					return -99;
				case 3:
					if(this.onePairNum() > oppHand.onePairNum()) {
						return 99;
					}
					else if(this.onePairNum() < oppHand.onePairNum()){
						return -99;
					}
					else {
						if(this.Hand[4].getValue() > oppHand.Hand[4].getValue()) {
							return 99;
						}
						else if(this.Hand[4].getValue() < oppHand.Hand[4].getValue()) {
							return -99;
						}
						else {
							return 66;
						}
					}
				case 2:
					return 99;
				case 0:
					return 99;
			}
			//When Player has 1 Pair
			case 2:
				switch(opponent) {
				case 10:
					return -99;
				case 9:	
					return -99;
				case 8:
					return -99;
				case 7: 
					return -99;
				case 6: 
					return -99;
				case 5: 
					return -99;
				case 4:
					return -99;
				case 3:
					return -99;
				case 2:
					if(this.onePairNum() > oppHand.onePairNum()) {
						return 99;
					}
					else if(this.onePairNum() < oppHand.onePairNum()){
						return -99;
					}
					else {
						if(this.Hand[4].getValue() > oppHand.Hand[4].getValue()) {
							return 99;
						}
						else if(this.Hand[4].getValue() < oppHand.Hand[4].getValue()) {
							return -99;
						}
						else {
							return 66;
						}
					}
				case 0:
					return 99;
			}
			//When Player has nothing
			case 0:
				switch(opponent) {
				case 10:
					return -99;
				case 9:	
					return -99;
				case 8:
					return -99;
				case 7: 
					return -99;
				case 6: 
					return -99;
				case 5: 
					return -99;
				case 4:
					return -99;
				case 3:
					return -99;
				case 2:
					return -99;
				case 0:
					this.sortByValue();
					oppHand.sortByValue();
					if(this.highestValue().getValue() > oppHand.highestValue().getValue()) {
						return 99;
					} else if(this.highestValue().getValue() < oppHand.highestValue().getValue()) {
						return -99;
					} else {
						if(this.highestValue().getSuit() > oppHand.highestValue().getSuit()) {
							return 99;
						} else if(this.highestValue().getSuit() < oppHand.highestValue().getSuit()) {
							return -99;
						}
					}
					
			}
		}
		
		return 66;
		
		
		
		
		
		
	}
	
}
