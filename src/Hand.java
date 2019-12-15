import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> playerHand;
	private int size;
	
	public Hand(){
		playerHand = new ArrayList<Card>();
		size = 0;
	}
	
	//clears the hand of all cards.
	public void clear() {
		playerHand.clear();
		size = 0;
	}
	
	public int getCardValueAt(int index) {
		return this.playerHand.get(index).getValue();
	}
	
	public int getCardSuitAt(int index) {
		return this.playerHand.get(index).getSuit();
	}
	
	//adds a new card to hand
	public void addCard(Card c) {
		playerHand.add(c);
		size ++;
	}
	
	//removes card from hand
	public void removeCard(Card c) {
		for(int i=0 ; i< playerHand.size(); i++){ 
			if (playerHand.get(i).equals(c)){
				playerHand.remove(i);
				Card tempCard = new Card(-1,-1);
				this.addCard(tempCard);
				size--;
			}
		}
	}
	
	public void removeCard(Card c, Card u) {
		for(int i=0 ; i< playerHand.size(); i++){ 
			if (playerHand.get(i).equals(c)){
				playerHand.remove(i);
				this.addCard(u);
				size--;
			}
		}
	}
	
	//sorts hand by value (Smallest to largest)
	public void sortByValue() {
		playerHand.sort(Card.CVC);
	}
	
	//sorts hand by suit (smallest to largest)
	public void sortBySuit() {
		playerHand.sort(Card.CSC);
	}
	
	//prints the cards in hand
	public String printHand() {
		String temp = "";
		for(Card card : playerHand) {
			temp += card.toString() + "\n";
		}
		return temp;
	}
	
	//gets the number of cards
	public int handSize() {
		return size;
	}
	
	/**
	 * comparisons
	 * @return
	 */
	
	//checks to see the number of pairs (# of cards where they are the same)
	public int numPairs() {
		int numpairs = 0;
		sortByValue();

		for(int i=0;i<playerHand.size()-2;i++)
			for(int j = 1; j < playerHand.size()-1; j++)
				if(playerHand.get(i).equals(playerHand.get(j)))
					numpairs++;
		
		return numpairs;
	}
	
	//Returns the highest pair number (whether its 1 or 2 pairs)
	public int onePairNum() {
		int onePairnum = -99;
		int twoPairnum = -98;
		Card tempCard = new Card();
		sortByValue();
		
		if(numPairs() == 1) {
			for(int i = 0; i < size; i++) {
				tempCard = playerHand.get(i);
				if(tempCard == null) {
					continue;
				}
				else if(tempCard.getValue() == playerHand.get(i+1).getValue()){
					onePairnum = playerHand.get(i+1).getValue();
					return onePairnum;
				}
			}
		}
		else if (numPairs() == 2) {
			for(int i = 0; i < size; i++) {
				tempCard = playerHand.get(i+1);
				if(tempCard == null) {
					continue;
				}
				else if(tempCard.getValue() == playerHand.get(i+1).getValue()){
					if(onePairnum == -99) {
						onePairnum = playerHand.get(i+1).getValue();
						i++;
					}
					else {
						twoPairnum = playerHand.get(i+1).getValue();
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
		sortByValue();

		for(int i=0;i<playerHand.size()-2;i++)
			for(int j = 1; j < playerHand.size()-1; j++)
				if(playerHand.get(i).equals(playerHand.get(j)))
					numpairs++;
		
		if(numpairs > 0) {
			return true;
		}
		return false;
	}
	
	//True if there is a triplet (three of the same valued cards)
	public boolean hasTriplet() {
		sortByValue();
		int i = 0;
		int handSize = size;
		int tripCount = 0;
		
		while(i != 3) {
			
			for (int j = 0; j < handSize; j++) {
				if(playerHand.get(i).getValue() == playerHand.get(j).getValue() && playerHand.get(i).getSuit() == playerHand.get(j).getSuit()) {
					continue;
				}else if(playerHand.get(i).getValue() == playerHand.get(j).getValue() && playerHand.get(i).getSuit() != playerHand.get(j).getSuit()) {
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
		sortBySuit();
		int handSize = size;
		int i = 0;
		
		while(i != handSize) {
			if(playerHand.get(handSize-1).getSuit() != playerHand.get(i).getSuit()) {
				return false;
			}
			i++;
		}
		
		return true;
	}
	
	//True if there is a straight (5 value cards in a row. e.g. Ace,2,3,4,5)
	public boolean hasStraight() {
		sortByValue();
		int i = 1;
		int j = 0;
		
		while(i != size) {
			if((playerHand.get(i).getValue()-playerHand.get(j).getValue()) != 1) {
				return false;
			}
			i++;
			j++;
		}
		return true;
	}
	
	//True if there is a full house (1 triplet and 1 pair)
	public boolean hasFullHouse() {
		sortByValue();
		if(hasTriplet() == true && hasPairs() == true) {
			return true;
		}
		return false;
	}
	
	//True if there is a four of a kind (4 of the same value cards)
	public boolean hasFourOfAKind() {
		sortByValue();
		
		if(playerHand.get(0).getValue() == playerHand.get(1).getValue() && 
				playerHand.get(0).getValue() == playerHand.get(2).getValue() && playerHand.get(0).getValue() == playerHand.get(3).getValue()) {
			return true;
		} else if(playerHand.get(4).getValue() == playerHand.get(1).getValue() && 
				playerHand.get(4).getValue() == playerHand.get(2).getValue() && playerHand.get(4).getValue() == playerHand.get(3).getValue()) {
			return true;
		}
		return false;
		
	}
	
	//Value of the highest valued card
	public Card highestValue() {
		sortByValue();
		return playerHand.get(4);
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
	/* typically returns 1 if the first value (player) is higher, -1 if second value (opponent) is higher, or 0 if equal
	 * not a huge deal but my dad was very adamant about you knowing this common practice
	 */
	public int compareTo(Hand oppHand) {
		sortByValue();
		oppHand.sortByValue();
		int player = 0;
		int opponent = 0;
		
		boolean draw = true;
		for(int i = 0; i < size; i++) {
			if(playerHand.get(i) != oppHand.playerHand.get(i)) {
				draw = false;
			}
		}
		if(draw == true) {
			return 66;
		}
		
		//If Player has either a Royal && Straight Flush
		if(hasFlush() == true && hasStraight() == true) {
			if(playerHand.get(2).getValue() == 12) {
				player = 10;
			}else {
				player = 9;	
			}
		}
		//If player has FourOfAKind
		else if(hasFourOfAKind() == true) {
			player = 8;
		}
		//If player has Full House
		else if(hasFullHouse() == true) {
			player = 7;
		}
		//If player has Flush
		else if(hasFlush() == true) {
			player = 6;
		}
		//If Player has Straight
		else if(hasStraight() == true) {
			player = 5;
		}
		//If player has ThreeOfAKind
		else if(hasTriplet() == true) {
			player = 4;
		}
		//If player has 2 pair
		else if(numPairs() == 2) {
			player = 3;
		}
		//If player has 1 pair
		else if(numPairs() == 1) {
			player = 2;
		}
		//if Player has nothing
		else {
			player = 0;
		}
		
		//If opponent has either a Royal && Straight Flush
		if(oppHand.hasFlush() == true && oppHand.hasStraight() == true) {
			if(oppHand.playerHand.get(2).getValue() == 12) {
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
						if(playerHand.get(0).getSuit() > oppHand.playerHand.get(0).getSuit()) {
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
				if(playerHand.get(0).getSuit() > oppHand.playerHand.get(0).getSuit()) {
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
					sortByValue();
					oppHand.sortByValue();
					if(playerHand.get(2).getValue() > oppHand.playerHand.get(2).getValue()) {
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
					sortByValue();
					oppHand.sortByValue();
					if(playerHand.get(2).getValue() > oppHand.playerHand.get(2).getValue()) {
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
					sortByValue();
					oppHand.sortByValue();
					if(playerHand.get(2).getSuit() > oppHand.playerHand.get(2).getSuit()) {
						return 99;
					} else if (playerHand.get(2).getSuit() < oppHand.playerHand.get(2).getSuit()) {
						return -99;
					}else if(playerHand.get(2).getSuit() == oppHand.playerHand.get(2).getSuit()) {
						if(playerHand.get(4).getValue() > oppHand.playerHand.get(4).getValue()) {
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
					sortByValue();
					oppHand.sortByValue();
					if(playerHand.get(4).getValue() > oppHand.playerHand.get(4).getValue()) {
						return 99;
					} else if(playerHand.get(4).getValue() < oppHand.playerHand.get(4).getValue()) {
						return -99;
					} else if(playerHand.get(4).getValue() == oppHand.playerHand.get(4).getValue()) {
						if(playerHand.get(4).getSuit() > oppHand.playerHand.get(4).getSuit()) {
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
					sortByValue();
					oppHand.sortByValue();
					if(playerHand.get(2).getValue() > oppHand.playerHand.get(2).getValue()) {
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
					if(onePairNum() > oppHand.onePairNum()) {
						return 99;
					}
					else if(onePairNum() < oppHand.onePairNum()){
						return -99;
					}
					else {
						if(playerHand.get(4).getValue() > oppHand.playerHand.get(4).getValue()) {
							return 99;
						}
						else if(playerHand.get(4).getValue() < oppHand.playerHand.get(4).getValue()) {
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
					if(onePairNum() > oppHand.onePairNum()) {
						return 99;
					}
					else if(onePairNum() < oppHand.onePairNum()){
						return -99;
					}
					else {
						if(playerHand.get(4).getValue() > oppHand.playerHand.get(4).getValue()) {
							return 99;
						}
						else if(playerHand.get(4).getValue() < oppHand.playerHand.get(4).getValue()) {
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
					sortByValue();
					oppHand.sortByValue();
					if(highestValue().getValue() > oppHand.highestValue().getValue())
						return 99;
					else if(highestValue().getValue() < oppHand.highestValue().getValue())
						return -99;
					else {
						if(highestValue().getSuit() > oppHand.highestValue().getSuit())
							return 99;
						else if(highestValue().getSuit() < oppHand.highestValue().getSuit())
							return -99;
					}
				}
		}
		
		return 66;	
	}
	
}
