
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
		
	}
	
	
	public static void displayBalance (Player a1, Player a2) {
		System.out.println("_______________________________________");
		System.out.println(a1.getName() + "'s balance: " + a1.getBalance());
		System.out.println(a2.getName() + "'s balance: " + a2.getBalance());
		System.out.println("-______________________________________");
	}
	

	
	
	
	
	
	
	
}
