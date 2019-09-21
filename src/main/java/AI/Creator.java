package AI;

import java.util.ArrayList;
import java.util.HashMap;

import Elementi.Casella;
import Elementi.CasellaResidenziale;
import Elementi.Giocatore;
import Elementi.GiocatoreAI;

public class Creator {

	public Creator() {
		// TODO Auto-generated constructor stub
	}
	
	public static String creaDatiGiocatori(ArrayList<Giocatore> giocatori)  {
		String data = "";
		for (Giocatore g : giocatori)  {
			data += "giocatore(" + g.getNome().toLowerCase() + ",";
			data += g.getSoldi() + ",";
			data += g.isInPrigione() + ",";
			data += g.getNumTokensPrigione() + ",";
			data += g.getTurniPrigione() + ",";
			data += g.getPosizioneInTabella() + ",";
			data += g.getNumCasellePossedute() + ",";
			data += g.getNumSetsPosseduti() + ").\n";
			
			/*String temp = "casellaAvv";
			if (g instanceof GiocatoreAI) temp = "casellaMia";
			for (Casella c : g.getCasellePossedute())  {
				data += temp + "(" + c.getNome().toLowerCase() + "," + c.getPrezzoVendita() + "," + c.getTipo() + ").\n";
			}*/
		}
		
		return data;
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
	
	public static String creaAvversario(Giocatore g) {
		String giocatore = "avversario(";
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
	
	public static String creaCasellaResidenziale(CasellaResidenziale c) {
		String casella = "casella(";
		casella = casella + c.getNome().toLowerCase();
		casella = casella + ",";
		casella = casella + determinaProprietarioCasella(c);
		casella = casella + ",";
		casella = casella + c.getColore().toLowerCase();
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
	
	public static String creaCasellaNonResidenziale(Casella c) {
		String casella = "casellaNonResidenziale(";
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
	
	public static String creaScambio(int soldiToBot, int soldiToYou, String[] caselleToBot, String[] caselleToYou) {
		String scambio = "";
		scambio += "soldiToBot(" + soldiToBot + ").\n";
		scambio += "soldiToYou(" + soldiToYou + ").\n";
		for(String c : caselleToBot)  {
			scambio += "casellaToBot(" + c.toLowerCase() + ").\n";
		}
		for(String c : caselleToYou)  {
			scambio += "casellaToYou(" + c.toLowerCase() + ").\n";
		}
		return scambio ;
	}
	
	public static String creaAsta(String casella, String puntata, String giocatore) {
		String asta = "proposta(";
		asta = asta + "idProposta";
		asta = asta + ",";
		asta = asta + casella.toLowerCase();
		asta = asta + ",";
		asta = asta + giocatore.toLowerCase();
		asta = asta + ",";
		asta = asta + puntata.toLowerCase();
		asta  = asta + ").";
		return asta;
	}
	
	public static String creaAcquisto(String nomeCasellaDaCedere, String prezzo) {
		String scambio = "acquisto(";
		scambio = scambio + "idProposta";
		scambio = scambio + ",";
		scambio = scambio + nomeCasellaDaCedere.toLowerCase();
		scambio = scambio + ",";
		scambio = scambio + prezzo;
		scambio  = scambio + ").";
		return scambio ;
	}
	
	public static String creaDatiSet(ArrayList<Casella> caselle)  {
		
		int[] coloriPosseduti = new int[8];
		boolean[] caseMaxate = new boolean[8];
		for(Casella c : caselle)  {
			if (c instanceof CasellaResidenziale)  {
				CasellaResidenziale cas = (CasellaResidenziale) c;
				String col = cas.getColore();
				
				if(cas.isIpotecata())  {
					continue;
				}
				if (col.equals("brown")) { coloriPosseduti[0] += 1; if(cas.caseMax()) caseMaxate[0] = true; }
				else if (col.equals("lightblue")) { coloriPosseduti[1] += 1; if(cas.caseMax()) caseMaxate[1] = true; }
				else if (col.equals("pink")) { coloriPosseduti[2] += 1; if(cas.caseMax()) caseMaxate[2] = true; }
				else if (col.equals("orange")) { coloriPosseduti[3] += 1; if(cas.caseMax()) caseMaxate[3] = true; }
				else if (col.equals("red")) { coloriPosseduti[4] += 1; if(cas.caseMax()) caseMaxate[4] = true; }
				else if (col.equals("yellow")) { coloriPosseduti[5] += 1; if(cas.caseMax()) caseMaxate[5] = true; }
				else if (col.equals("green")) { coloriPosseduti[6] += 1; if(cas.caseMax()) caseMaxate[6] = true; }
				else if (col.equals("blue")) { coloriPosseduti[7] += 1; if(cas.caseMax()) caseMaxate[7] = true; }
					
			}
		}
		String data = "";
		if (coloriPosseduti[0] == 2) data += "set(brown,1000," + caseMaxate[0] + ").\n";;
		if (coloriPosseduti[1] == 3) data += "set(lightblue,1500," + caseMaxate[1] + ").\n";
		else if (coloriPosseduti[2] == 3) data += "set(pink,3000," + caseMaxate[2] + ").\n";
		else if (coloriPosseduti[3] == 3) data += "set(orange,3000," + caseMaxate[3] + ").\n";
		else if (coloriPosseduti[4] == 3) data += "set(red,4500," + caseMaxate[4] + ").\n";
		else if (coloriPosseduti[5] == 3) data += "set(yellow,4500," + caseMaxate[5] + ").\n";
		else if (coloriPosseduti[6] == 3) data += "set(green,6000," + caseMaxate[6] + ").\n";
		else if (coloriPosseduti[7] == 2) data += "set(blue,4000," + caseMaxate[7] + ").\n";
		return data;
	}
	
	public static String creaDatiPerIpoteca(ArrayList<Casella> caselle)  {
		String data = "";
		HashMap<String, Integer> setsToAdd = new HashMap<String,Integer>();
		
		for(Casella c : caselle)  {
			if(c instanceof CasellaResidenziale)  {
				CasellaResidenziale cas  = (CasellaResidenziale) c;
				if (cas.getNumeroCaseCostruite() > 0)  {
					String col = cas.getColore();
					if (setsToAdd.containsKey(col))  {
						setsToAdd.replace(col, setsToAdd.get(col) + cas.getPrezzoCostruzioneCasa()/2);
					} else  {
						setsToAdd.put(col, cas.getPrezzoCostruzioneCasa()/2);
					}
				} else  {
					data += "casellaRes(" + c.getNome().toLowerCase() + "," + c.getPrezzoVendita() + ").\n";
				}
			} else  {
				data += "casella(" + c.getNome().toLowerCase() + "," + c.getPrezzoVendita() + ").\n";
			}
		}
		
		for(String col : setsToAdd.keySet())  {
			data += "set(" + col + "," + setsToAdd.get(col) + ").\n";
		}
		return data;
	}
	
	public static String creaScambio(String nomeCasellaDaCedere, String nomeCasellaDaPrendere) {
		String scambio = "scambio(";
		scambio = scambio + "idProposta";
		scambio = scambio + ",";
		scambio = scambio + nomeCasellaDaCedere.toLowerCase();
		scambio = scambio + ",";
		scambio = scambio + nomeCasellaDaPrendere.toLowerCase();
		scambio  = scambio + ").";
		return scambio ;
	}
}
