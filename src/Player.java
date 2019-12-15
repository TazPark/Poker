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
	
	public void deal() {
		
	}
	
	public void discard() {
		
	}
	
	public int wager() {
		
	}
	
	public String showHand() {
		return playerHand.printHand;
	}
	
	public int getBalance() {
		return balance;
	}

}
