
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
	}
	
	public static void gamePoker (Player player1, Player player2) {
		
	}
	
	// Displays the players balances
	public static void displayBalance (Player a1, Player a2) {
		System.out.println("****************************************" + "\n");
		System.out.println(a1.getName() + "'s balance: " + a1.getBalance());
		System.out.println(a2.getName() + "'s balance: " + a2.getBalance() + "\n");
		System.out.println("****************************************");
	}
	
	 
	
	
	
	
	
	
	
}
