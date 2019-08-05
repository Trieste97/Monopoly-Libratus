package AI;

import Elementi.Casella;
import Elementi.Giocatore;

public class Creator {

	public Creator() {
		// TODO Auto-generated constructor stub
	}
	
	public static String creaGiocatore(Giocatore g) {
		String giocatore = "giocatore(";
		giocatore = giocatore + g.getNome().toLowerCase();
		giocatore = giocatore + ",";
		giocatore = giocatore + g.getSoldi();
		giocatore = giocatore + ").";
		return giocatore;
	}
	
	public static String creaCasella(Casella c) {
		String casella = "casella(";
		casella = casella + c.getNome().toLowerCase();
		casella = casella + ",";
		casella = casella + c.getPrezzoVendita();
		casella = casella + ").";
		return casella;
	}
	
	public static String creaProposta(String nomeCasella, String nomeGiocatore) {
		String proposta = "proposta(";
		proposta = proposta + "idProposta";
		proposta = proposta + ",";
		proposta = proposta + nomeCasella.toLowerCase();
		proposta = proposta + ",";
		proposta = proposta + nomeGiocatore.toLowerCase();
		proposta  = proposta + ").";
		return proposta ;
	}
}
