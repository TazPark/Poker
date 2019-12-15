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
		int numCardsDiscard = 0;
		
		//For Computer
		if(playerHand.hasStraight() == true && playerHand.hasFlush() == true) {
			numCardsDiscard = 0;
		}
		else if(playerHand.hasFourOfAKind() == true) {
			numCardsDiscard = 1;
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
