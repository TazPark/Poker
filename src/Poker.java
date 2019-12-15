
import java.util.Random;
import java.util.*;

public class Poker {
	
	private static Random raNum = new Random();
	private static int pos = 0;
	
	public static void main (String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Hello, Welcome to Poker!");
		System.out.println("What is your name?");
		Player player1 =  new Player(keyboard.next(), 150);
		keyboard.nextLine();
		Player computer = new Player("Bot Negreanu", 150);
		
		gamePoker(player1, computer);
		
	}
	
	public static void gamePoker (Player player1, Player player2) {
		Scanner keyboard = new Scanner(System.in);
		Card[] deck = newDeck();
		String keepPlaying = "y";
		int winner = 0;
		double playerWager = 0;
		double compWager = 0;
		
		do {
			displayBalance(player1);
			displayBalance(player2);
			
			shuffDeck(deck);
			dealHand(deck, player1);
			dealHand(deck, player2);
			
			System.out.println("****************************************" + "");
			System.out.println(player1.getName() + "'s turn");
			System.out.println("****************************************" + "");
			playerWager = wager(player1, 0);
			System.out.println("\n");
			System.out.println("****************************************" + "");
			System.out.println(player2.getName() + "'s turn");
			System.out.println("****************************************" + "");
			compWager = wager(player2, playerWager);
			System.out.println("\n");
			
			discardAndDeal(deck, player1);
			System.out.println("\n");
			discardAndDeal(deck, player2);
			
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
			
			displayBalance(player1);
			displayBalance(player2);
			
			player1.showHand().clear();
			player2.showHand().clear();
			
			System.out.println("\n" + "Would you like to keep playing? Type: 'y' or 'n'" + "\n");
			keepPlaying = keyboard.next();
		}while(keepPlaying.equalsIgnoreCase("y"));
	}
	
	// Displays the players balances
	public static void displayBalance (Player a1) {
		System.out.println("****************************************" + "\n");
		
		System.out.println(a1.getName() + "'s balance: " + String.format("%.2f", a1.getBalance()) + "\n");
		
		System.out.println("****************************************");
	}
	
	//Creates a Deck
	public static Card[] newDeck(){
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
	
	//ShufflesDeck
	public static void shuffDeck(Card[] cards){
		pos = 0;
		for(int j = 0; j < 52; j++){
			int randIndex = raNum.nextInt(52);
			Card tempCard = cards[j];
			cards[j] = cards[randIndex];
			cards[randIndex] = tempCard;
		}
	}
	
	//Puts cards into hands of players
	public static void dealHand(Card[] deck, Player p1){
		int numCards = 5 - p1.showHand().handSize();
		for(int j = 0; j < numCards; j ++){
			p1.deal(nextCard(deck));
		}
	}
	
	//Discards card and then deals new cards
	public static void discardAndDeal(Card[] deck, Player p1){
		p1.discard();
		for(int i = 0; i < p1.showHand().handSize(); i++){
			Card tempCard = new Card(-1,-1);
			p1.showHand().removeCard(tempCard, nextCard(deck));
			
			//p1.deal(nextCard(deck));	
		}
		if(!p1.getName().equalsIgnoreCase("Bot Negreanu")) {
			System.out.println("****************************************" + "");
			System.out.println(p1.getName() + "'s New Cards: ");
			System.out.println(p1.showHand().printHand()+ "");
			System.out.println("****************************************" + "");
		}
	}
	
	// Returns the next card to deal to the player.
	public static Card nextCard(Card[] deck){
		return deck[pos++];
	}
	
	//Shows hand and asks how much to wage
	public static double wager(Player player, double minimum){
		if(!player.getName().equalsIgnoreCase("Bot Negreanu")) {
		System.out.println(player.getName() +"'s Cards: ");
		player.showHand().sortByValue();
			System.out.println(player.showHand().printHand());
		}
		return player.wager(minimum);
	}
}
