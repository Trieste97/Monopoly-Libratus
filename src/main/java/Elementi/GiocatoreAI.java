package Elementi;

import java.util.ArrayList;

import AI.AIClass;
import AI.Writer;

public class GiocatoreAI extends Giocatore  {

	Writer writer = new Writer();
	public GiocatoreAI(String nome) {
		super(nome);
	}
	
	/*
	 * Inizio turno giocatore bot
	 * può decidere se:
	 * - tirare dadi (return 0)
	 * - proporre scambio (TODO)
	 * - ipotecare qualcosa (TODO)
	 * - pagare/usare token se è in prigione (TODO)
	*/
	
	public int decidiCosaFare(ArrayList<Giocatore> giocatori)  {
		writer.writeDecisioneIniziale(giocatori);
		AIClass newAI = new AIClass();
		int esito = newAI.decisioneIniziale();
		System.out.println("Scelta Fatta: " + esito);
		return esito;
	}
	
	public String voglioUscireDiPrigione()  {
		writer.writeUscitaPrigione(this);
		AIClass newAI = new AIClass();
		String modoUscita = newAI.uscitaPrigione();
		System.out.println("Scelta Fatta: " + modoUscita);
		return modoUscita;
	}

}