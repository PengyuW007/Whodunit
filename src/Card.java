/*
 * CLASS: Card
 * 
 * Author: Pnegyu Wang, 7863401
 * 
 * REMARKS: Card class
 * 
 */
public class Card {
	private String type;
	private String value;

	/*
	 * Card
	 * 
	 * PURPOSE: Constructor of Card
	 * 
	 * PARAMETERS:
	 * 			String t: type of this card
	 * 			String v: value of this card
	 * 
	 */
	public Card(String t, String v) {
		type = t;
		value = v;
	}
	
	/*
	 * getType
	 * 
	 * PURPOSE: Get the type of this card
	 * 
	 * Return: The type of this card
	 * 
	 */
	public String getType() {
		return type;
	}

	/*
	 * getValue
	 * 
	 * PURPOSE: Get the value of this card
	 * 
	 * Return: The value of this card
	 * 
	 */
	public String getValue() {
		return value;
	}

}
