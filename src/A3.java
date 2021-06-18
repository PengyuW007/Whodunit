/*-----------------------------------------
      NAME		: Pengyu Wang
      STUDENT NUMBER	: 7863401
      COURSE		: COMP 2150
      INSTRUCTOR	: Ali Neshati
      ASSIGNMENT	: assignment 3
      QUESTION	: question 1      
      
      REMARKS: A game that involves deduction to 
	 			determine the "who, where and how" of a murder.
     
     
//-----------------------------------------*/
import java.util.*;

public class A3 {

	public static void main(String[] args) {
		System.out.println("Welcome to \"whodunnit?\"");

		Scanner scan = new Scanner(System.in);
		System.out.println("How many computer opponents would you like?");

		int numAI = scan.nextInt();

		System.out.println("Setting up players...");

		ArrayList<Card> suspects = new ArrayList<Card>();
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

		System.out.println("Here are the names of all the suspects:");
		for (int i = 0; i < suspects.size() - 1; i++) {
			System.out.print(suspects.get(i).getValue() + ", ");
		}
		System.out.println(suspects.get(suspects.size() - 1).getValue());

		ArrayList<Card> locations = new ArrayList<Card>();
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

		System.out.println("Here are all the locations:");
		for (int i = 0; i < locations.size() - 1; i++) {
			System.out.print(locations.get(i).getValue() + ", ");
		}
		System.out.println(locations.get(locations.size() - 1).getValue());

		// adding weapons list
		ArrayList<Card> weapons = new ArrayList<Card>();
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

		System.out.println("Here are all the weapons:");
		for (int i = 0; i < weapons.size() - 1; i++) {
			System.out.print(weapons.get(i).getValue() + ", ");
		}
		System.out.println(weapons.get(weapons.size() - 1).getValue());

		// adding players
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Human(0, null, null));
		for (int i = 1; i < numAI + 1; i++) {

			players.add(new AI(i, null, null));
		}

		Model m = new Model(players, suspects, locations, weapons);

		m.runModel();

		scan.close();
	}

}
