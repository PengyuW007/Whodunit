/*
 * CLASS: AI
 * 
 * Author: Pnegyu Wang, 7863401
 * 
 * REMARKS: AI extends Player(Abstract class)
 * 
 */
import java.util.*;

public class AI extends Player {

	/*
	 * AI 
	 * 
	 * PURPOSE: constructor
	 * 
	 * PARAMETERS: 
	 * 			int i: index of AI player
	 * 			ArrayList<Card> cards: cards in this AI's hand
	 * 			Guess g: AI's guess
	 * 			
	 */
	public AI(int i, ArrayList<Card> cards, Guess g) {
		super(i, cards, g);

	}

	/*
	 * canAnswer
	 * 
	 * PURPOSE: This method represents that player i (which is different from the current player) has made 
	 * 			guess g. 
	 * 
	 * PARAMETERS: 
	 * 			Guess g: The guess has been made
	 * 			IPlayer: the player's index
	 * 
	 * Return: The card that he can or cannot answer
	 * 
	 */
	public Card canAnswer(Guess g, IPlayer ip) {

		Card card = null;
		String person = g.getPersonGuess().getValue();
		String place = g.getPlaceGuess().getValue();
		String weapon = g.getWeaponGuess().getValue();

		for (int i = 0; i < getCard().size(); i++) {
			Card c = getCard().get(i);
			String str = c.getValue();
			if (str.equals(person) || str.equals(place) || str.equals(weapon)) {
				card = c;
			}
		}

		if (card != null)
			System.out.println("Player <" + getIndex() + "> answered.");
		else
			System.out.println("Player <" + getIndex() + "> cannot answer this guess");

		return card;
	}

	/*
	 * setGuess
	 * 
	 * PURPOSE: Set the guess of human player has made
	 * 
	 * PARAMETERS:
	 * 			ArrayList<Card> ppl: cards of people you can guess
	 * 			ArrayList<Card>places: the cards of places
	 * 			ArrayList<Card>weapons: the cards of weapons
	 * 
	 * Return: Guess
	 * 
	 */
	public Guess setGuess(ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {

		Random r = new Random();

		int person = Math.abs(r.nextInt(ppl.size()));
		Card personC = ppl.get(person);

		int location = Math.abs(r.nextInt(places.size()));
		Card locationC = places.get(location);

		int weapon = Math.abs(r.nextInt(weapons.size()));
		Card weaponC = weapons.get(weapon);

		Boolean as;
		
		int acsu = r.nextInt(101);
		if (acsu >99) {
			as = true;
		} else {
			as = false;
		}
		
		Guess g = new Guess(personC, locationC, weaponC, as);

		return g;
	}

}
