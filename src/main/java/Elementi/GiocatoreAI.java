package Elementi;


public class GiocatoreAI extends Giocatore  {

	public GiocatoreAI(String nome) {
		super(nome);
	}
	
	/*
	 * Inizio turno giocatore bot
	 * pu� decidere se:
	 * - tirare dadi (return 0)
	 * - proporre scambio (TODO)
	 * - ipotecare qualcosa (TODO)
	 * - pagare/usare token se � in prigione (TODO)
	*/
	
	public int decidiCosaFare()  {
		return 0;
	}

}