package Elementi;

import java.util.ArrayList;

import Elementi.Casella;

public class Bank {

	private final int numHotels = 12;
	private final int numHouses = 32;
	
	private ArrayList<Casella> cards;
	
	
	public Bank(){
		this.cards = new ArrayList<Casella>();
	}
	
	public void addCard(Casella card)  {
		getCards().add(card);
	}
	public void removeCard(Casella c) {
		this.cards.remove(c);
	}

	public int getNumHotels() {
		return numHotels;
	}

	public int getNumHouses() {
		return numHouses;
	}

	public ArrayList<Casella> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Casella> cards) {
		this.cards = cards;
	}
	public boolean haCarta(Casella casella) {
		return this.cards.contains(casella);
	}
}
