import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestWhodunit {

	private ArrayList<Card> suspects;
	private ArrayList<Card> locations;
	private ArrayList<Card> weapons;

	@BeforeEach
	void setUp() throws Exception {
		suspects = new ArrayList<Card>();
		Card s1 = new Card("Suspects", "Miller");
		Card s2 = new Card("Suspects", "Domaratzki");
		Card s3 = new Card("Suspects", "Cameron");
		Card s4 = new Card("Suspects", "Guderian");
		Card s5 = new Card("Suspects", "Durocher");
		Card s6 = new Card("Suspects", "Bristow");
		Card s7 = new Card("Suspects", "Matheson");

		suspects.add(s1);
		suspects.add(s2);
		suspects.add(s3);
		suspects.add(s4);
		suspects.add(s5);
		suspects.add(s6);
		suspects.add(s7);

		locations = new ArrayList<Card>();
		Card l1 = new Card("Locations", "COMP2080");
		Card l2 = new Card("Locations", "COMP2140");
		Card l3 = new Card("Locations", "COMP2150");
		Card l4 = new Card("Locations", "COMP2160");
		Card l5 = new Card("Locations", "COMP2280");
		Card l6 = new Card("Locations", "COMP3380");

		locations.add(l1);
		locations.add(l2);
		locations.add(l3);
		locations.add(l4);
		locations.add(l5);
		locations.add(l6);

		// adding weapons list
		weapons = new ArrayList<Card>();
		Card w1 = new Card("Weapons", "Assignments");
		Card w2 = new Card("Weapons", "Labs");
		Card w3 = new Card("Weapons", "Midterm");
		Card w4 = new Card("Weapons", "Final");
		Card w5 = new Card("Weapons", "Quizzes");

		weapons.add(w1);
		weapons.add(w2);
		weapons.add(w3);
		weapons.add(w4);
		weapons.add(w5);
	}

	@Test
	public void test1() {

		Guess g = new Guess(suspects.get(0), locations.get(0), weapons.get(0), false);
		ArrayList<Card> c = new ArrayList<Card>();
		AI ai = new AI(0, c, g);// ai player has no cards

		// canAnswer return null
		assertNull(ai.canAnswer(g, ai));
	}

	@Test
	public void test2() {
		Guess g = new Guess(suspects.get(0), locations.get(0), weapons.get(0), false);
		ArrayList<Card> c = new ArrayList<Card>();
		AI ai = new AI(0, c, g);// ai player has no cards

		ai.setCard(suspects.get(0));

		assertNotNull(ai.canAnswer(g, ai));
	}

	@Test
	public void test3() {
		Guess g = new Guess(suspects.get(0), locations.get(0), weapons.get(0), false);
		ArrayList<Card> c = new ArrayList<Card>();
		AI ai = new AI(0, c, g);

		ai.buildCard(locations);

		assertEquals(ai.canAnswer(g, ai).getValue(), "COMP2080");

	}

	@Test
	public void test4() {
		Guess g = new Guess(suspects.get(0), locations.get(0), weapons.get(0), false);
		ArrayList<Card> c = new ArrayList<Card>();
		AI ai = new AI(0, c, g);

		ai.buildCard(locations);

		assertNotEquals(ai.getGuess().getPersonGuess().getValue(), locations.get(0).getValue());
	}

	@Test
	public void test5() {
		Guess g = new Guess(suspects.get(0), locations.get(0), weapons.get(0), true);
		ArrayList<Card> c = new ArrayList<Card>();
		AI ai = new AI(0, c, g);

		ai.setCard(suspects.get(0));
		ai.setCard(locations.get(0));
		ai.setCard(weapons.get(0));

		assertTrue(ai.getGuess().getAS());// it is an accusation

		assertEquals(ai.getGuess().getPersonGuess().getValue(), suspects.get(0).getValue());
		assertEquals(ai.getGuess().getPlaceGuess().getValue(), locations.get(0).getValue());
		assertEquals(ai.getGuess().getWeaponGuess().getValue(), weapons.get(0).getValue());
	}

	@Test
	public void test6() {
		Guess g = new Guess(suspects.get(0), locations.get(0), weapons.get(0), false);
		ArrayList<Card> c = new ArrayList<Card>();
		AI ai = new AI(0, c, g);

		c.add(suspects.get(0));
		c.add(locations.get(0));
		c.add(weapons.get(0));
		c.add(suspects.get(1));

		ai.buildCard(c);

		assertFalse(ai.getGuess().getAS());

		ai.receiveInfo(ai, c.get(0));

		assertTrue(!ai.getGuess().getAS());
	}

	@Test
	public void test7() {
		Guess g = new Guess(suspects.get(0), locations.get(0), weapons.get(0), false);
		ArrayList<Card> c = new ArrayList<Card>();
		Human h = new Human(0, c, g);
		
		h.buildCard(locations);
		
		assertEquals(h.canAnswer(g, h).getValue(),locations.get(0).getValue());
	}
	
	/*
	 * test8:TestGetIndex
	 * 
	 * PURPOSE: This is to test wether getIndex return the correct index
	 * 
	 */
	@Test
	public void test8() {
		Guess g = new Guess(suspects.get(0), locations.get(0), weapons.get(0), false);
		ArrayList<Card> c = new ArrayList<Card>();
		
		
		ArrayList<Player>players = new ArrayList<Player>();
		
		//add one player
		Player human1 = new Human(0,c,g);
		players.add(human1);
		assertEquals(String.valueOf(players.get(0).getIndex()),"0");
		
		//add to two
		Player human2 = new Human(1,c,g);
		players.add(human2);
		assertEquals(String.valueOf(players.get(1).getIndex()),"1");
		
		//add a different type of player, ai
		Player ai = new AI(2,c,g);
		players.add(ai);
		assertEquals(String.valueOf(players.get(2).getIndex()),"2");
		
	}
	
	/*
	 * test9
	 * 
	 * PURPOSE: Test different types of players were added into the list, 
	 * 			the cards is set as we wish
	 * 
	 */
	@Test
	public void test9() {
		Guess g = new Guess(suspects.get(0), locations.get(0), weapons.get(0), false);
		ArrayList<Card> c = new ArrayList<Card>();
		Player human = new Human(0,c,g);
		Player ai = new AI(1,c,g);
		c.add(suspects.get(0));
		ArrayList<Player>players = new ArrayList<Player>();
		players.add(human);
		players.add(ai);
		
		assertEquals(players.get(0).getCard().get(0).getValue(),"Miller");
		assertEquals(players.get(1).getCard().get(0).getValue(),"Miller");
	}
}
