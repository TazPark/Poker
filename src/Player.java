import java.util.*;

public class Player {
	
	private Hand playerHand;
	private float balance;
	private String name;
	
	public Player(String inputName, float balance) {
		this.playerHand = new Hand();
		this.name = inputName;
		this.balance = balance;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void discard() {
		
		Scanner keyboard = new Scanner(System.in);
		int numCardsDiscard = 0;
		System.out.println("How many cards would you like to discard?");
		numCardsDiscard = keyboard.nextInt();
		Card tempCard = new Card();
		String suit = "";
		int suitNum = 0;
		
		if(!this.getName().equalsIgnoreCase("Bot Negreanu")) {
			if(numCardsDiscard == 0) {
				System.out.println("You have chose to not discard any cards.");
			} else if(numCardsDiscard <= 5 && numCardsDiscard > 0) {
				do {
					this.showHand();
					System.out.println("What card would you like to discard?");
					System.out.println("Type the suit and then the value.");
					System.out.println("(e.g. for 2 of Clubs. Type Clubs, then 2.");
					suit = keyboard.next();
					
					if(!suit.equalsIgnoreCase("clubs") || !suit.equalsIgnoreCase("diamonds") || !suit.equalsIgnoreCase("hearts") || !suit.equalsIgnoreCase("spades")) {
						do {
							System.out.println("Invalid suit, please enter a valid suit: ");
							suit = keyboard.next();
						}while(!suit.equalsIgnoreCase("clubs") || !suit.equalsIgnoreCase("diamonds") || !suit.equalsIgnoreCase("hearts") || !suit.equalsIgnoreCase("spades"));
					}
					
					if(suit.equalsIgnoreCase("clubs")) {
						suitNum = 0;
					} else if(suit.equalsIgnoreCase("diamonds")) {
						suitNum = 1;
					} else if(suit.equalsIgnoreCase("hearts")) {
						suitNum = 2;
					} else if(suit.equalsIgnoreCase("spades")) {
						suitNum = 3;
					}
					
					tempCard = new Card(keyboard.nextInt(), suitNum);
					this.playerHand.removeCard(tempCard);
					numCardsDiscard--;
				}while(numCardsDiscard != 0);
			}
			return;
		}
		
		
		
		//For Computer
		if(playerHand.hasStraight() == true && playerHand.hasFlush() == true) {
			numCardsDiscard = 0;
		}
		else if(playerHand.hasFourOfAKind() == true) {
			numCardsDiscard = 1;
			this.playerHand.sortByValue();
			if(this.playerHand) {
				
			}
		}
		else if(playerHand.hasFullHouse() == true) {
			numCardsDiscard = 2;
		}
		else if(playerHand.hasFlush() == true) {
			numCardsDiscard = 5;
		}
		else if(playerHand.hasStraight() == true) {
			numCardsDiscard = 5;
		}
		else if(playerHand.hasTriplet() == true) {
			numCardsDiscard = 2;
		}
		else if(playerHand.numPairs() == 2) {
			numCardsDiscard = 3;
		}
		else if(playerHand.numPairs() == 1) {
			numCardsDiscard = 3;
		}
		else{
			numCardsDiscard = 5;
		}
	}
	
	public float wager(float min) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Minimum wage: " + min);
		System.out.println("What is your wager? ");
		
		float wage = min;
		
		if(!this.name.equalsIgnoreCase("Bot Negreanu")) {
			wage = keyboard.nextFloat();
			if(wage >= min) {
				balance -= wage;
				keyboard.close();
				return wage;
			} else if (wage < min && this.balance >= min){
				do {
					System.out.println("Wage cannot be lower than the min.");
					System.out.println("Please enter a valid wage: ");
					wage = keyboard.nextFloat();
				}while(wage < min);
				balance -= wage;
				keyboard.close();
				return wage;
			} else if (wage < min && this.balance < min) {
				System.out.println("Since you cannot wage the minimum, you will have to go all in");
				System.out.println("To go all in, type your full balance (Your balance: " + this.getBalance() +")");
				wage = keyboard.nextFloat();
				if(wage < min) {
					do{
						System.out.println("Invalid wage.");
						System.out.println("Input the correct wage: " + this.getBalance());
						wage = keyboard.nextFloat();
					}while(wage != balance);
					keyboard.close();
					return wage;
				}
			}
		}
		
		
		//for computer
		if(playerHand.hasStraight() == true && playerHand.hasFlush() == true) {
			wage = this.balance;
			this.balance = this.balance - this.balance;
			keyboard.close();
			return wage;
		}
		if(playerHand.hasFourOfAKind() == true) {
			if((this.balance*.7) >= min) {
				wage = this.balance * .7f;
				this.balance -= wage;
				keyboard.close();
				return wage;
			}
			else {
				this.balance -= min;
				keyboard.close();
				return min;
			}
		}
		if(playerHand.hasFullHouse() == true) {
			if((this.balance*.6) >= min) {
				this.balance = this.balance * .6f;
				this.balance -= wage;
				keyboard.close();
				return wage;
			}
			else {
				this.balance -= min;
				keyboard.close();
				return min;
			}
		}
		if(playerHand.hasFlush() == true) {
			if((this.balance*.5) >= min) {
				wage = this.balance * .5f;
				this.balance -= wage;
				keyboard.close();
				return wage;
			}
			else {
				this.balance -= min;
				keyboard.close();
				return min;
			}
		}
		if(playerHand.hasStraight() == true) {
			if((this.balance) >= min) {
				wage = this.balance * .4f;
				this.balance -= wage;
				keyboard.close();
				return wage;
			}
			else {
				this.balance -= min;
				keyboard.close();
				return min;
			}
		}
		if(playerHand.hasTriplet() == true) {
			if((this.balance*.3) >= min) {
				wage = this.balance * .3f;
				this.balance -= wage;
				keyboard.close();
				return wage;
			}
			else {
				this.balance -= min;
				keyboard.close();
				return min;
			}
		}
		if(playerHand.numPairs() == 2) {
			if((this.balance*.2) >= min) {
				wage = this.balance * .2f;
				this.balance -= wage;
				keyboard.close();
				return wage;
			}
			else {
				this.balance -= min;
				keyboard.close();
				return min;
			}
		}
		if(playerHand.numPairs() == 1) {
			if((this.balance*.1) >= min) {
				wage = this.balance * .1f;
				this.balance -= wage;
				keyboard.close();
				return wage;
			}
			else {
				this.balance -= min;
				keyboard.close();
				return min;
			}
		}
		keyboard.close();
		return -1;
	}
	
	public void showHand() {
		playerHand.printHand();
	}
	
	public float getBalance() {
		return this.balance;
	}
	
	public void winnings(float amount){
		this.balance += amount;
	}
}
