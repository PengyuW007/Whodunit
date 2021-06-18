/*
 * CLASS: Model
 * 
 * Author: Pnegyu Wang, 7863401
 * 
 * REMARKS: To run the model, deal cards, add players
 * 
 */
import java.util.*;

public class Model {
	private ArrayList<Player> player;
	private ArrayList<Player> deadP;
	private ArrayList<Card> ppl;
	private ArrayList<Card> places;
	private ArrayList<Card> weapons;

	/*
	 * Model
	 * 
	 * PURPOSE: Constructor of model
	 * 
	 * PARAMETERS: 
	 * 			ArrayList<Player> players: the player lists
	 * 			ArrayList<Card> ppl: cards of suspects
	 * 			ArrayList<Card> places: cards of places
	 * 			ArrayList<Card> weapons: cards of weapons
	 * 
	 */
	public Model(ArrayList<Player> players, ArrayList<Card> ppl, ArrayList<Card> places, ArrayList<Card> weapons) {
		this.player = players;
		this.ppl = ppl;
		this.places = places;
		this.weapons = weapons;
		deadP = new ArrayList<Player>();
	}

	/*
	 * runModel
	 * 
	 * PURPOSE: Run the model
	 * 
	 */
	public void runModel() {
		ArrayList<Card> answer = getAnswer(ppl, places, weapons);

		ArrayList<Card> cards = cardsList();

		Collections.shuffle(player);// shuffle the player list

		Collections.shuffle(cards);// shuffle the cards List

		System.out.println("Here are " + cards.size() + " cards should be dealt");

		for (int i = 0; i < player.size(); i++) {
			player.get(i).setIndex(i);
		}

		deal(cards);

		System.out.println("Playing...");
		int s = player.size();
		int pCounter = 0;// i
		int k;// turn of player

		while (deadP.size() != s - 1) {

			k = (pCounter + 1) % player.size();// turn of the player
			// ask active player for their guess
			Player p = player.get(k);
			playerGuess(p);//setup which can be printed
			Guess pGuess = p.setGuess(ppl, places, weapons);

			if (pGuess.getAS() == true) {
				// if guess is an accusation
				if (pGuess.getPersonGuess().getValue().equals(answer.get(0).getValue())
						&& pGuess.getPlaceGuess().getValue().equals(answer.get(1).getValue())
						&& pGuess.getWeaponGuess().getValue().equals(answer.get(2).getValue())) {
					// if correct, game is over, active player is the winner.
					System.out.println("Accusation: <" + pGuess.getPersonGuess().getValue() + "> in <"
							+ pGuess.getPlaceGuess().getValue() + "> with the <" + pGuess.getWeaponGuess().getValue()
							+ ">.");
					System.out.println("Game is won: Player <" + k + "> won the game.");
					System.exit(0);
				} else {
					// if not correct, active player is removed from the game.
					System.out.println("Bad accusation: \"Player <" + k
							+ "> made a bad accusation and was removed from the game.\"");
					deadP.add(player.get(k));// add this incorrect person into dead List

				} // end test accusation is correct

			} else {
				Card c = p.canAnswer(pGuess, p);
				p.receiveInfo(p, c);

			} // end it is an suggestion/accusation

			pCounter++;

		} // end while game is not over loop

	}

	/*
	 * playerGuess
	 * 
	 * PURPOSE: To set the cards they can guess
	 * 
	 * PARAMETERS: 
	 * 			Player p
	 * 
	 */
	public void playerGuess(Player p) {
		p.setUp(player.size(), p.getIndex(), ppl, places, weapons);
	}

	/*
	 * split
	 * 
	 * PURPOSE: Split the cards into several sublists
	 * 
	 * PARAMETERS: 
	 * 			ArrayList<Card> ori: original list
	 * 			int from: from this index
	 * 			int to: to this index
	 * 			
	 * Returns: ArrayList<Card>, the sublist has been splited
	 * 
	 */
	public ArrayList<Card> split(ArrayList<Card> ori, int from, int to) {
		ArrayList<Card> list = new ArrayList<Card>();
		int size = to - from + 1;
		for (int i = 0; i < size; i++) {
			list.add(ori.get(from));
			from++;
		}

		return list;
	}

	/*
	 * getAnswer
	 * 
	 * PURPOSE: Get the answer and store them into a list
	 * 
	 * PARAMETERS: 
	 * 			ArrayList<Card> l1:suspects list
	 * 			ArrayList<Card> l2: places list
	 * 			ArrayList<Card> l3: weapons list
	 * 
	 * Return: The list of the answer
	 * 
	 */
	public ArrayList<Card> getAnswer(ArrayList<Card> l1, ArrayList<Card> l2, ArrayList<Card> l3) {
		ArrayList<Card> answer = new ArrayList<Card>();

		Collections.shuffle(l1);
		Collections.shuffle(l2);
		Collections.shuffle(l3);

		Random r = new Random();

		int r1 = r.nextInt(l1.size());
		Card c1 = l1.get(r1);
		answer.add(l1.get(r1));
		l1.remove(r1);
		l1.add(c1);

		int r2 = r.nextInt(l2.size());
		Card c2 = l2.get(r2);
		answer.add(l2.get(r2));
		l2.remove(r2);
		l2.add(c2);

		int r3 = r.nextInt(l3.size());
		Card c3 = l3.get(r3);
		answer.add(l3.get(r3));
		l3.remove(r3);
		l3.add(c3);

		System.out.print("Answer is: ");
		for (int i = 0; i < answer.size(); i++) {
			System.out.print(answer.get(i).getValue() + " ");
		}
		System.out.println();

		return answer;
	}

	/*
	 * cardsList
	 * 
	 * PURPOSE: Add all the types of cards in one list
	 * 
	 * Return: ArrayList<Card> the list of all the cards
	 * 
	 */
	public ArrayList<Card> cardsList() {
		ArrayList<Card> cards = new ArrayList<Card>();

		for (int i = 0; i < ppl.size(); i++) {
			cards.add(ppl.get(i));
		}

		for (int i = 0; i < places.size(); i++) {
			cards.add(places.get(i));
		}

		for (int i = 0; i < weapons.size(); i++) {
			cards.add(weapons.get(i));
		}

		return cards;
	}
	
	/*
	 * deal
	 * 
	 * PURPOSE: Deal the cards to players
	 * 
	 * PARAMETERS:
	 * 			ArrayList<Card> cards: cards should be dealt
	 * 
	 */
	public void deal(ArrayList<Card> cards) {
		System.out.println("Dealing cards...");
		int numP = player.size();// 3

		int numC = cards.size();// 17,number of cards

		int numPerp = numC / numP;// 5

		for (int i = 0; i < numP; i++) {
			player.get(i).buildCard(split(cards, i * numPerp, (i + 1) * numPerp - 1));
		}

		int reminder = numC % numP;

		int former = numP * numPerp;// 15
		if (reminder != 0) {

			for (int j = 1; j < 8; j++) {
				if (reminder == j) {
					for (int i = 0; i < j; i++) {
						player.get(i).setCard(cards.get(former + i));
					}
				}
			}
		}

		// print how many cards human/me received
		for (int i = 0; i < numP; i++) {
			int j = 0;
			if (player.get(i) instanceof Human) {
				System.out.println("Cards you received: ");
				while (j < player.get(i).getCard().size()) {
					System.out.println("You received the card " + player.get(i).getCard().get(j).getValue() + " ");
					j++;
				}
			}
		}
	}
}
