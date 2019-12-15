
import java.util.Random;
import java.util.*;

public class Poker {
	
	private static Random raNum = new Random();
	
	public static void main (String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Hello, Welcome to Poker!");
		System.out.println("What is your name?");
		Player player1 =  new Player(keyboard.nextLine(), 150);
		Player computer = new Player("Bot Negreanu", 150);
		
		gamePoker(player1, computer);
		keyboard.close();
	}
	
	public static void gamePoker (Player player1, Player player2) {
		Scanner keyboard = new Scanner(System.in);
		int winner = 0;
		float playerWager = 0;
		float compWager = 0;
		
		do {
			displayBalance(player1);
			displayBalance(player2);
			
			
			
			winner = player1.showHand().compareTo(player2.showHand());
			if(winner == 99) {
				System.out.println(player1.getName() + " has won!");
				player1.winnings(playerWager + compWager);	
			} 
			else if(winner == -99) {
				System.out.println(player2.getName() + " has won!");
				player2.winnings(playerWager + compWager);	
			} 
			else if(winner == 66) {
				player1.winnings(playerWager);
				player2.winnings(compWager);	
			}
			
			System.out.println("\n" + "Would you like to keep playing? Type: 'y' or 'n'");
		}while(keyboard.next().equalsIgnoreCase("y"));
		keyboard.close();
	}
	
	// Displays the players balances
	public static void displayBalance (Player a1) {
		System.out.println("****************************************" + "\n");
		
		System.out.println(a1.getName() + "'s balance: " + a1.getBalance() + "\n");
		
		System.out.println("****************************************");
	}
	
	//Creates a Deck
	public static Card[] getDeck(){
		Card[] cards = new Card[52];
		int cardIndex = 0;
		int ranDeck = raNum.nextInt(2);
		switch(ranDeck){
			case 0:
				for(int j = 1; j <= 13; j++){
					for(int i = 0; i <= 3; i++){
						Card tempCard = new Card(i, j);
						cards[cardIndex++] = tempCard;
					}
				}
				return cards;
			case 1:
				for(int j = 13; j > 0; j--){
					for(int i = 0; i <= 3; i++){
						Card tempCard = new Card(i, j);
						cards[cardIndex++] = tempCard;
					}
				}
				return cards;
			case 2:
				for(int j = 13; j > 0; j--){
					for(int i = 3; i >= 0; i--){
						Card tempCard = new Card(i, j);
						cards[cardIndex++] = tempCard;
					}
				}
				return cards;
		}
		return cards;
	}
	
	
	
	
	
	
	
}
