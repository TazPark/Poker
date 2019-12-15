import java.util.*;

public class Player {
	
	private Hand playerHand;
	private double balance;
	private String name;
	
	public Player(String inputName, double balance) {
		this.playerHand = new Hand();
		this.name = inputName;
		this.balance = balance;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void deal(Card card){
		playerHand.addCard(card);
	}
	
	public void discard() {
		
		Scanner kb = new Scanner(System.in);
		int numCardsDiscard = 0;
		System.out.println("How many cards would you like to discard?");
		Card tempCard = new Card();
		String suit = "";
		int suitNum = 0;
		
		if(!this.getName().equalsIgnoreCase("Bot Negreanu")) {
			numCardsDiscard = kb.nextInt();
			if(numCardsDiscard == 0) {
				System.out.println("You have chose to not discard any cards.");
			} else if(numCardsDiscard <= 5 && numCardsDiscard > 0) {
				do {
					System.out.println("\n" + "Your hand:");
					System.out.println(this.showHand().printHand());
					System.out.println("What card would you like to discard?");
					System.out.println("Type the suit and then the value.");
					System.out.println("Type the number for the suit: ");
					System.out.println("0 for clubs");
					System.out.println("1 for diamonds");
					System.out.println("2 for hearts");
					System.out.println("3 for spades");
					suitNum = kb.nextInt();
					System.out.println("\n");
					
					if(!(suitNum >= 0 || suitNum <= 3)) {
						do {
							System.out.println("Invalid suit, please enter a valid suit: ");
							suitNum = kb.nextInt();
						}while(!(suitNum >= 0 || suitNum <= 3));
					}
					System.out.println("What is the value of the card?");
					System.out.println("Type the number for the value: ");
					System.out.println("1 for Ace");
					System.out.println("11 for Jack");
					System.out.println("12 for Queen");
					System.out.println("13 for King");
					tempCard = new Card(suitNum,kb.nextInt());
					System.out.println("\n");
					
					this.playerHand.removeCard(tempCard);
					numCardsDiscard--;
				}while(numCardsDiscard != 0);
			}
			return;
		}
		
		
		
		//For Computer
		if(playerHand.hasStraight() == true && playerHand.hasFlush() == true) {
			// numCardsDiscard = 0;
		}
		else if(playerHand.hasFourOfAKind() == true) {
			// numCardsDiscard = 1;
			this.playerHand.sortByValue();
			if(this.playerHand.getCardValueAt(0) == this.playerHand.getCardValueAt(2)) {
				tempCard = new Card(this.playerHand.getCardValueAt(4), this.playerHand.getCardValueAt(4));
				this.playerHand.removeCard(tempCard);
			} else {
				tempCard = new Card(this.playerHand.getCardValueAt(0), this.playerHand.getCardValueAt(0));
				this.playerHand.removeCard(tempCard);
			}
		}
		else if(playerHand.hasFullHouse() == true) {
			// numCardsDiscard = 2;
			tempCard = new Card(this.playerHand.getCardValueAt(0), this.playerHand.getCardValueAt(0));
			this.playerHand.removeCard(tempCard);
			tempCard = new Card(this.playerHand.getCardValueAt(4), this.playerHand.getCardValueAt(4));
			this.playerHand.removeCard(tempCard);
		}
		else if(playerHand.hasFlush() == true) {
			//numCardsDiscard = 5;
			this.playerHand.clear();
		}
		else if(playerHand.hasStraight() == true) {
			//numCardsDiscard = 5;
			this.playerHand.clear();
		}
		else if(playerHand.hasTriplet() == true) {
			//numCardsDiscard = 2;
			tempCard = new Card(this.playerHand.getCardValueAt(0), this.playerHand.getCardValueAt(0));
			this.playerHand.removeCard(tempCard);
			tempCard = new Card(this.playerHand.getCardValueAt(1), this.playerHand.getCardValueAt(1));
			this.playerHand.removeCard(tempCard);
		}
		else if(playerHand.numPairs() == 2) {
			//numCardsDiscard = 3;
			tempCard = new Card(this.playerHand.getCardValueAt(0), this.playerHand.getCardValueAt(0));
			this.playerHand.removeCard(tempCard);
			tempCard = new Card(this.playerHand.getCardValueAt(2), this.playerHand.getCardValueAt(2));
			this.playerHand.removeCard(tempCard);
			tempCard = new Card(this.playerHand.getCardValueAt(4), this.playerHand.getCardValueAt(4));
			this.playerHand.removeCard(tempCard);
		}
		else if(playerHand.numPairs() == 1) {
			//numCardsDiscard = 3;
			tempCard = new Card(this.playerHand.getCardValueAt(0), this.playerHand.getCardValueAt(0));
			this.playerHand.removeCard(tempCard);
			tempCard = new Card(this.playerHand.getCardValueAt(2), this.playerHand.getCardValueAt(2));
			this.playerHand.removeCard(tempCard);
			tempCard = new Card(this.playerHand.getCardValueAt(4), this.playerHand.getCardValueAt(4));
			this.playerHand.removeCard(tempCard);
		}
		else{
			//numCardsDiscard = 5;
			this.playerHand.clear();
		}
		return;
	}
	
	public double wager(double min) {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Minimum wage: " + min);
		System.out.println("What is your wager? ");
		
		double wage = min;
		
		if(!this.name.equalsIgnoreCase("Bot Negreanu")) {
			wage = kb.nextDouble();
			if(wage >= min) {
				balance -= wage;
				return wage;
			} else if (wage < min && this.balance >= min){
				do {
					System.out.println("Wage cannot be lower than the min.");
					System.out.println("Please enter a valid wage: ");
					wage = kb.nextDouble();
				}while(wage < min);
				balance -= wage;
				return wage;
			} else if (wage < min && this.balance < min) {
				System.out.println("Since you cannot wage the minimum, you will have to go all in");
				System.out.println("To go all in, type your full balance (Your balance: " + this.getBalance() +")");
				wage = kb.nextDouble();
				if(wage < min) {
					do{
						System.out.println("Invalid wage.");
						System.out.println("Input the correct wage: " + this.getBalance());
						wage = kb.nextDouble();
					}while(wage != balance);
					return wage;
				}
			}
		}
		
		
		//for computer
		if(playerHand.hasStraight() == true && playerHand.hasFlush() == true) {
			wage = this.balance;
			this.balance = this.balance - this.balance;
			System.out.println("Negreanu has chose to wage: " + wage);
			return wage;
		}
		if(playerHand.hasFourOfAKind() == true) {
			if((this.balance*.7) >= min) {
				wage = this.balance * .7f;
				this.balance -= wage;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return wage;
			}
			else {
				this.balance -= min;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return min;
			}
		}
		if(playerHand.hasFullHouse() == true) {
			if((this.balance*.6) >= min) {
				this.balance = this.balance * .6f;
				this.balance -= wage;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return wage;
			}
			else {
				this.balance -= min;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return min;
			}
		}
		if(playerHand.hasFlush() == true) {
			if((this.balance*.5) >= min) {
				wage = this.balance * .5f;
				this.balance -= wage;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return wage;
			}
			else {
				this.balance -= min;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return min;
			}
		}
		if(playerHand.hasStraight() == true) {
			if((this.balance) >= min) {
				wage = this.balance * .4f;
				this.balance -= wage;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return wage;
			}
			else {
				this.balance -= min;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return min;
			}
		}
		if(playerHand.hasTriplet() == true) {
			if((this.balance*.3) >= min) {
				wage = this.balance * .3f;
				this.balance -= wage;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return wage;
			}
			else {
				this.balance -= min;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return min;
			}
		}
		if(playerHand.numPairs() == 2) {
			if((this.balance*.2) >= min) {
				wage = this.balance * .2f;
				this.balance -= wage;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return wage;
			}
			else {
				this.balance -= min;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return min;
			}
		}
		if(playerHand.numPairs() == 1) {
			if((this.balance*.1) >= min) {
				wage = this.balance * .1f;
				this.balance -= wage;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return wage;
			}
			else {
				this.balance -= min;
				System.out.println("Negreanu has chose to wage: " + String.format("%.2f", wage));
				return min;
			}
		}
		kb.close();
		return -1;
	}
	
	public Hand showHand() {
		return playerHand;
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void winnings(double amount){
		this.balance += amount;
	}
}
