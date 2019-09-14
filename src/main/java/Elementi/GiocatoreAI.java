package Elementi;

import java.util.ArrayList;

import AI.AIClass;
import AI.Writer;
import GUI.TavolaDaGioco;
import javafx.util.Pair;

public class GiocatoreAI extends Giocatore  {

	private Board board;
	Writer writer = new Writer();
	public GiocatoreAI(String nome) {
		super(nome);
	}
	
	/*
	 * Inizio turno giocatore bot
	 * può decidere se:
	 * - tirare dadi (return 0)
	 * - ipotecare qualcosa (TODO)
	 * - pagare/usare token se è in prigione (TODO)
	*/
	
	@Override
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
		String modoUscita = null;
		if(this.inPrigione) {
			writer.writeUscitaPrigione(this);
			AIClass newAI = new AIClass();
			modoUscita = newAI.uscitaPrigione();
			System.out.println("Scelta Fatta: " + modoUscita);
		}
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
	
	public void proposteDaFare(Giocatore avversario) {
		// TODO Auto-generated method stub
		
		if(this.getNome().equals("BOT1") && !this.getCaselleResidenziali().isEmpty() && 
				!avversario.getCaselleResidenziali().isEmpty()) {
			
			try {
				
				writer.writeGestioneProposte(this, avversario,
						this.getCaselleResidenzialiOggetto(), 
						avversario.getCaselleResidenzialiOggetto());
				AIClass newAI = new AIClass();
				ArrayList<String> esito = newAI.gestioneProposte();
				System.out.println("Proposte su caselle avversarie: " + esito.get(0));
				if(esito.get(0).equals("Scambio")){
					boolean scambioAccettato = false;
					scambioAccettato = TavolaDaGioco.chiediSeVuoleScambiare(esito.get(1), esito.get(2), 
							avversario.getNome());
					if (scambioAccettato) {
						board.scambia(board.getCasellaDaNome(esito.get(1)), this, avversario,
								board.getCasellaDaNome(esito.get(2)));
					}
				}
				else if (esito.get(0).equals("Acquisto")){
					boolean acquistoAccettato = false;
					acquistoAccettato = TavolaDaGioco.chiediSeVuoleVendere(esito.get(1), board.getCasellaDaNome(esito.get(1)).getPrezzoVendita(), 
							avversario.getNome());
					if (acquistoAccettato) {
						board.compraCasellaAvversaria(board.getCasellaDaNome(esito.get(1)), this, avversario, 
								board.getCasellaDaNome(esito.get(1)).getPrezzoVendita());
					}
				}
				
			} catch (Exception e) {
				System.err.println("VALUTAZIONE GESTIONE SCAMBIO FALLITA");
			}
			
			
		}

	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	
	
	
	public boolean chiediSeAccettaScambio2Caselle(String casellaDaCedere, String casellaDaPrendere, Giocatore giocatore, Giocatore avversario){
		writer.writeDecisioneScambio2Caselle(casellaDaCedere, casellaDaPrendere, giocatore, avversario);
		AIClass newAI = new AIClass();
		boolean avversarioAccettaScambio = newAI.decisioneScambioAcquisto28();
		
		System.out.println("Ho deciso se scambiare: " + avversarioAccettaScambio);
		return avversarioAccettaScambio;
	}
	
	public boolean chiediSeAccettaVendita(String casellaDaCedere, String prezzo, Giocatore giocatore, Giocatore avversario){
		writer.writeDecisioneAcquisto(casellaDaCedere, prezzo, giocatore, avversario);
		AIClass newAI = new AIClass();
		boolean avversarioAccetta = newAI.decisioneScambioAcquisto28();
		
		System.out.println("Ho deciso se scambiare: " + avversarioAccetta);
		return avversarioAccetta;
	}
	
	
	

}