import java.util.*;

public class Player {
	
	private Hand playerHand;
	private float blanace;
	private String name;
	
	public Player(String inputName, float balance) {
		this.playerHand = new Hand();
		this.name = inputName;
		this.blanace = balance;
	}
	
	public String getName() {
		return this.name;
	}
	
	/**public void discard() {
		
	}*/
	
	public float wager(float min) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Minimum wage: " + min);
		System.out.println("What is your wager? ");
		
		float wage = min;
		
		if(!this.name.equalsIgnoreCase("Bot Negreanu")) {
			wage = keyboard.nextFloat();
			if(wage >= min) {
				blanace -= wage;
				return wage;
			} else if (wage < min && this.blanace >= min){
				do {
					System.out.println("Wage cannot be lower than the min.");
					System.out.println("Please enter a valid wage: ");
					wage = keyboard.nextFloat();
				}while(wage < min);
				blanace -= wage;
				return wage;
			} else if (wage < min && this.blanace < min) {
				System.out.println("Since you cannot wage the minimum, you will have to go all in");
				System.out.println("To go all in, type your full balance (Your balance: " + this.getBalance() +")");
				wage = keyboard.nextFloat();
				if(wage < min) {
					do{
						System.out.println("Invalid wage.");
						System.out.println("Input the correct wage: " + this.getBalance());
						wage = keyboard.nextFloat();
					}while(wage != blanace);
					return wage;
				}
			}
		}
		
		//for computer
		if(playerHand.hasStraight() == true && playerHand.hasFlush() == true) {
			wage = this.blanace;
			this.blanace = this.blanace - this.blanace;
			return wage;
		}
		if(playerHand.hasFourOfAKind() == true) {
			if((this.blanace*.7) >= min) {
				wage = this.blanace * .7f;
				this.blanace -= wage;
				return wage;
			}
			else {
				this.blanace -= min;
				return min;
			}
		}
		if(playerHand.hasFullHouse() == true) {
			if((this.blanace*.6) >= min) {
				this.blanace = this.blanace * .6f;
				this.blanace -= wage;
				return wage;
			}
			else {
				this.blanace -= min;
				return min;
			}
		}
		if(playerHand.hasFlush() == true) {
			if((this.blanace*.5) >= min) {
				wage = this.blanace * .5f;
				this.blanace -= wage;
				return wage;
			}
			else {
				this.blanace -= min;
				return min;
			}
		}
		if(playerHand.hasStraight() == true) {
			if((this.blanace) >= min) {
				wage = this.blanace * .4f;
				this.blanace -= wage;
				return wage;
			}
			else {
				this.blanace -= min;
				return min;
			}
		}
		if(playerHand.hasTriplet() == true) {
			if((this.blanace*.3) >= min) {
				wage = this.blanace * .3f;
				this.blanace -= wage;
				return wage;
			}
			else {
				this.blanace -= min;
				return min;
			}
		}
		if(playerHand.numPairs() == 2) {
			if((this.blanace*.2) >= min) {
				wage = this.blanace * .2f;
				this.blanace -= wage;
				return wage;
			}
			else {
				this.blanace -= min;
				return min;
			}
		}
		if(playerHand.numPairs() == 1) {
			if((this.blanace*.1) >= min) {
				wage = this.blanace * .1f;
				this.blanace -= wage;
				return wage;
			}
			else {
				this.blanace -= min;
				return min;
			}
		}
		return -1;
	}
	
	public void showHand() {
		playerHand.printHand();
	}
	
	public float getBalance() {
		return this.blanace;
	}
	
	public void winnings(float amount){
		this.blanace += amount;
	}
}
