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
		giocatore = giocatore + ",";
		giocatore = giocatore + g.isInPrigione();
		giocatore = giocatore + ",";
		giocatore = giocatore + g.getNumTokensPrigione();
		giocatore = giocatore + ",";
		giocatore = giocatore + g.getTurniPrigione();
		giocatore = giocatore + ",";
		giocatore = giocatore + g.getPosizioneInTabella();
		giocatore = giocatore + ").";
		return giocatore;
	}
	
	public static String creaCasella(Casella c) {
		String casella = "casella(";
		casella = casella + c.getNome().toLowerCase();
		casella = casella + ",";
		casella = casella + determinaProprietarioCasella(c);
		casella = casella + ",";
		casella = casella + c.getPrezzoVendita();
		casella = casella + ",";
		casella = casella + c.getPrezzoTransito();
		casella = casella + ",";
		casella = casella + c.getPrezzoIpoteca();
		casella = casella + ",";
		casella = casella + c.isIpotecata();
		casella = casella + ").";
		return casella;
	}
	
	public static String determinaProprietarioCasella(Casella c) {
		// TODO Auto-generated method stub
		String proprietario;
		if(!(c.getProprietario() instanceof Giocatore)) {
			proprietario = "banca";
		}
		else {
			proprietario = c.getProprietario().getNome().toLowerCase();
		}
		return proprietario;
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
	
	public static String creaInPrigione(String nomeGiocatore) {
		String inPrigione = "inPrigione(";
		inPrigione = inPrigione + nomeGiocatore.toLowerCase();
		inPrigione  = inPrigione + ").";
		return inPrigione;
	}
}
