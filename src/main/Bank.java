package temp;

import java.util.ArrayList;

public class Bank {

	final int numHotels = 12;
	final int numHouses = 32;
	
	ArrayList<Card> cards;
	
	
	public Bank()  {
		
	}
	
	public void addCard(Card card)  {
		cards.add(card);
	}
}
