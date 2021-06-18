/*
 * CLASS: Guess
 * 
 * Author: Pnegyu Wang, 7863401
 * 
 * REMARKS: Guess class
 * 
 */

public class Guess {
	private Card person;
	private Card place;
	private Card weapon;
	private Boolean as;
	
	/*
	 * Guess
	 * 
	 * PURPOSE: Constructor of guess
	 * 
	 * PARAMETERS: 
	 * 			Card person: guess the person card
	 * 			Card place:guess the place card
	 * 			Card weapon: guess the weapon card
	 * 			Boolean as: this is an accusation/ suggestion  
	 * 
	 */
	public Guess(Card person,Card place,Card weapon,Boolean as) {
		this.person = person;
		this.place = place;
		this.weapon = weapon;
		this.as = as;
	}
	
	/*
	 * Getters of Guess
	 */
	public Card getPersonGuess() {
		return person;
	}
	
	public Card getPlaceGuess() {
		return place;
	}
	
	public Card getWeaponGuess() {
		return weapon;
	}
	
	public Boolean getAS() {
		return as;
	}
	
}
