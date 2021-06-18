/*
 * CLASS: Player
 * 
 * Author:Pnegyu Wang, 7863401
 * 
 * Remarks: An abstract class of Players that it can implements IPlayer this interface
 * 
 */
import java.util.*;

public abstract class Player implements IPlayer {

	private int index;
	private ArrayList<Card> cards;
	private Guess guess;

	/*
	 * Player
	 * 
	 * PURPOSE: Constructor of Player
	 * 
	 * PARAMETERS: 
	 * 			int i: index of this player
	 * 			ArrayList<Card>cards: the cards list in this player's hand
	 * 			Guess: The guess of this player
	 */
	public Player(int i, ArrayList<Card> cards, Guess g) {
		index = i;
		this.cards = cards;
		guess = g;

	}//end constructor

	/*
	 * canAnswer
	 * 
	 * PURPOSE: This method represents that player i (which is different from the current player) has made 
	 *			guess g. 
	 * 
	 * PARAMETERS: 
	 * 			Guess g: The guess should response
	 * 			IPlayer ip: The player's index
	 * 
	 * Returns: Return a card (which the current player must have in their hand) or null,
	 * 			 to represent that the current player cannot answer that guess.
	 * 
	 */
	public abstract Card canAnswer(Guess g, IPlayer ip);
	
	/*
	 * setGuess
	 * 
	 * PURPOSE: Set the guess of this player has made
	 * 
	 * PARAMETERS: 
	 * 			ArrayList<Card>ppl: the cards of people
	 * 			ArrayList<Card>places: the cards of places
	 * 			ArrayList<Card>weapons: the cards of weapons
	 * 
	 * Returns: The guess of this player
	 * 
	 */
	public abstract Guess setGuess(ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons);

	/*
	 * setIndex
	 * 
	 * PURPOSE: Set the index of this player after shuffle, the player's list
	 * 
	 * PARAMETERS: 
	 * 			int index: the value of index that you want to set		
	 * 
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/*
	 * receiveInfo
	 * 
	 * PURPOSE: This represents that the current player has made a guess (previously) and 
	 * 			the player at index i has one of the cards of the guess, c. 
	 * 
	 * PARAMETERS: 
	 * 			IPlayer ip: the current player ask previous player who has made guess
	 * 			Card c: one of the cards of the guess 
	 * 
	 */
	public void receiveInfo(IPlayer ip, Card c) {
		if(this instanceof AI) {
			System.out.println("Asking player " + ip.getIndex());

			if (c != null) {
				System.out.println(
						"Player <" + ip.getIndex() + "> refuted your suggestion by showing you <" + c.getValue() + ">.");
			} else {
				System.out.println("No one could refute your suggestion.");
			}
		}	
	}
	
	/*
	 * setup
	 * 
	 * PURPOSE: Print the whole list of cards
	 * 
	 * PARAMETERS: 
	 * 			int numPlayers: number of players
	 * 			int index: index of this player
	 * 			ArrayList<Card>ppl: cards list of people
	 * 			ArrayList<Card>places: cards list of places
	 * 			ArrayList<Card>weapons: cards list of weapons
	 * 
	 */
	public void setUp(int numPlayers, int index, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
		this.index = index;
	}

	/*
	 * setCard
	 * 
	 * PURPOSE: add one card to this player's hands
	 * 
	 * PARAMETERS:
	 * 			Card c: the card to be added
	 * 
	 */
	public void setCard(Card c) {
		cards.add(c);
	}

	/*
	 * buildCard
	 * 
	 * PURPOSE: add a list to this player's cards in hand
	 * 
	 * PARAMETERS:
	 * 			ArrayList<Card> c: the list will be added to player's hand
	 * 
	 */
	public void buildCard(ArrayList<Card> c) {
		cards = c;
	}
	
	/*
	 * getGuess
	 * 
	 * PURPOSE: Get the guess of this player, should be call after setGuess()
	 * 
	 */
	public Guess getGuess() {
		return guess;
	}

	/*
	 * getIndex
	 * 
	 * PURPOSE:Get the index of this player
	 * 
	 * Return: index
	 */
	public int getIndex() {
		return index;
	}
	
	/*
	 * getCard
	 * 
	 * PURPOSE:Get the cards list in player's hand
	 * 
	 * Return: cards, the cards list
	 * 
	 */
	public ArrayList<Card> getCard() {
		return cards;
	}

}
