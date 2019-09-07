package Elementi;

import java.util.ArrayList;

import AI.AIClass;
import AI.Writer;
import javafx.util.Pair;

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
	
	public String decidiCosaScambiare(ArrayList<Giocatore> giocatori)  {
		writer.writeFaiPropostaScambio(giocatori);
		AIClass newAI = new AIClass();
		String esito = newAI.faiPropostaScambio();
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
	
	public boolean chiediSeAccettaScambio(Board board, int soldiToBot, int soldiToYou, String[] caselleToBot, String[] caselleToYou)  {
		writer.writeDecisioneScambio(board, soldiToBot, soldiToYou, caselleToBot, caselleToYou);
		AIClass newAI = new AIClass();
		boolean avversarioAccettaScambio = newAI.decisioneScambioAcquisto();
		
		//temp
		avversarioAccettaScambio = true;
		System.out.println("Ho deciso se scambiare: " + avversarioAccettaScambio);
		return avversarioAccettaScambio;
	}
	
	public ArrayList<String> decidiCosaCostruire(Board board)  {
		writer.writeDecidiCosaCostruire(this);
		AIClass newAI = new AIClass();
		ArrayList<String> esito = newAI.decidiCosaCostruire();
		return esito;
	}
	
	public boolean decidiSeComprareCasella(Casella casella, GiocatoreAI bot)  {
		writer.writePropostaAcquisto(casella, bot);
		AIClass newAI = new AIClass();
		boolean vuoleComprare = newAI.propostaAcquisto();
		System.out.println("Scelta Fatta: " + vuoleComprare);
		return vuoleComprare;
	}
	
	public Pair<Boolean,String> decidiCosaIpotecare(GiocatoreAI bot)  {
		writer.writeDecidiCosaIpotecare(bot);
		AIClass newAI = new AIClass();
		Pair<Boolean,String> daIpotecare = newAI.decidiCosaIpotecare();
		System.out.println("Scelta Fatta: " + daIpotecare.getValue());
		return daIpotecare;
	}

}