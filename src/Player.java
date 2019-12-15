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
	
	public void discard() {
		
	}
	
	public float wager(float min) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Minimum wage: " + min);
		System.out.println("What is your wager? ");
		
		float wage = min;
		
		if(!this.name.equalsIgnoreCase("Bot Negreanu")) {
			wage = keyboard.nextFloat();
		}
		
	}
	
	public String showHand() {
		return playerHand.printHand;
	}
	
	public int getBalance() {
		return balance;
	}

}
