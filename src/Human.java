/*
 * CLASS: Human
 * 
 * Author: Pnegyu Wang, 7863401
 * 
 * REMARKS: Human extends Player(Abstract class)
 * 
 */
import java.util.*;

public class Human extends Player {	
	
	/*
	 * Human 
	 * 
	 * PURPOSE:Constructor of human
	 * 
	 * PARAMETERS: 
	 * 			int i: index of this player
	 * 			ArrayList<Card> cards in this player's hand
	 * 			Guess g: Guess of this player
	 *
	 */
	public Human(int i, ArrayList<Card> cards, Guess g) {
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
		Scanner scan = new Scanner(System.in);
		Card card = null;
		String person = g.getPersonGuess().getValue();
		String place = g.getPlaceGuess().getValue();
		String weapon = g.getWeaponGuess().getValue();

		ArrayList<Card> match = new ArrayList<Card>();
		for (int i = 0; i < getCard().size(); i++) {
			Card c = getCard().get(i);
			String str = c.getValue();
			if (str.equals(person) || str.equals(place) || str.equals(weapon)) {
				match.add(c);
			}
		}

		if (match.size() == 0) {
			// - Human Player can't respond to a suggestion
			// "Player <idx> asked you about <guess>, but you couldn't answer."
			System.out.println("Player <" + ip.getIndex() + "> asked you about <" + person + " in the " + place
					+ " with the " + weapon + ">, but you couldn't answer.");
		} else if (match.size() == 1) {
			// - Human Player only has one card to respond to a suggestion
			// : "Player <idx> asked you about <guess>, you only have one card, <card>,
			// showed it to them."
			card = match.get(0);
			System.out.println(
					"Player <" + ip.getIndex() + "> asked you about <" + person + " in the " + place + " with the "
							+ weapon + ">, you only have one card, <" + card.getValue() + ">, showed it to them.");
		} else {
			// - Human Player has multiple cards to respond to a suggestion
			// : "Player <idx> asked you about <guess>. Which do you show?"
			System.out.println("Player <" + ip.getIndex() + "> asked you about <" + person + " in the " + place
					+ " with the " + weapon + ">. Which do you show?");
			for (int i = 0; i < match.size(); i++) {
				System.out.println(i + ": " + match.get(i).getValue());
			}

			Boolean valid = false;
			int index;
			do {
				index = scan.nextInt();
				if (index > match.size() || index < 0) {
					System.out.println("Not valid. Try again.");
				} else {
					card = match.get(index);
					valid = true;

				}
			} while (valid == false);
			System.out.println("Player <" + getIndex() + "> answered.");
		}

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
		System.out.println("It is your turn.");

		System.out.println("Which person do you want to suggest?");

		for (int i = 0; i < ppl.size(); i++) {
			System.out.println(i + ". " + ppl.get(i).getValue());
		}
		
		Scanner scan = new Scanner(System.in);
		int person;
		Boolean valid = false;

		do {
			person = scan.nextInt();

			if (person >= ppl.size() || person < 0)
				System.out.println("Index out of bound!  Try again.");
			else
				valid = true;
		} while (valid == false);

		Card personC = ppl.get(person);

		System.out.println("Which location do you want to suggest?");

		for (int i = 0; i < places.size(); i++) {
			System.out.println(i + ". " + places.get(i).getValue());
		}

		int place;
		valid = false;
		do {
			place = scan.nextInt();

			if (place >= places.size() || place < 0)
				System.out.println("Index out of bound! Try again.");
			else
				valid = true;
		} while (valid == false);
		
		Card placeC = places.get(place);

		System.out.println("Which weapon do you want to suggest?");

		for (int i = 0; i < weapons.size(); i++) {
			System.out.println(i + ". " + weapons.get(i).getValue());
		}

		int weapon;
		valid = false;
		
		do {
			weapon = scan.nextInt();

			if (weapon >= ppl.size() || weapon < 0)
				System.out.println("Index out of bound! Try again.");
			else
				valid = true;
		} while (valid == false);
		
		Card weaponC = weapons.get(weapon);

		System.out.println("Is this an accusation( Y/N)");

		String asString = "";
		Boolean as = null;
		valid = false;
		
		do {
			asString = scan.next();
			
			if (asString.equalsIgnoreCase("N")) {
				valid = true;
				as = false;
				
			} else if (asString.equalsIgnoreCase("Y")) {
				valid = true;
				as = true;
			}else {
				System.out.println("Input is invalid! Input should be Y/N/y/n. Try again.");
				valid = false;
			}
		}while(valid==false);
		
		Guess g = new Guess(personC, placeC, weaponC, as);

		return g;

	}

}
