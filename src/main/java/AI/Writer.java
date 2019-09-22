package AI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import Elementi.Board;
import Elementi.Casella;
import Elementi.CasellaResidenziale;
import Elementi.Giocatore;
import Elementi.GiocatoreAI;

public class Writer {

	public Writer() {
		// TODO Auto-generated constructor stub
	}
	
	public void writeDecisioneIniziale(ArrayList<Giocatore> giocatori)  {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/decisioneInizialeInstance", "UTF-8");
			writer.println(Creator.creaDatiGiocatori(giocatori));
			for (Giocatore giocatore : giocatori) {
				if (giocatore instanceof GiocatoreAI) {
					ArrayList<Casella> tutteLeCaselle = giocatore.getCaselleIpotecate();
					for (Casella caselleTutte : tutteLeCaselle) {
						if(caselleTutte instanceof CasellaResidenziale) {
							writer.println(Creator.creaCasellaResidenziale((CasellaResidenziale)caselleTutte));
						}
						else if(caselleTutte.getTipo().equals("station") || caselleTutte.getTipo().equals("society")) {
							writer.println(Creator.creaCasellaNonResidenziale(caselleTutte));
							
						}
					}
				}
				
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void writePropostaAcquisto(Casella casella, Giocatore giocatore) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/propostaAcquistoInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(giocatore));
			writer.println(Creator.creaCasella(casella));
			writer.println(Creator.creaProposta(casella.getNome(), giocatore.getNome()));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void writeUscitaPrigione(Giocatore giocatore) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/uscitaPrigioneInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(giocatore));
			writer.println(Creator.creaInPrigione(giocatore.getNome()));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void writeGestioneProposte(Giocatore giocatore, Giocatore avversario, 
			ArrayList<CasellaResidenziale>caselleGiocatore, ArrayList<CasellaResidenziale>caselleAvversarie) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/gestioneProposteInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(giocatore));
			writer.println(Creator.creaAvversario(avversario));
			for (CasellaResidenziale casellaResidenziale : caselleGiocatore) {
				writer.println(Creator.creaCasellaResidenziale(casellaResidenziale));
			}
			for (CasellaResidenziale casellaResidenziale : caselleAvversarie) {
				writer.println(Creator.creaCasellaResidenziale(casellaResidenziale));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public void writeDecisioneScambio(Board board, int soldiToBot, int soldiToYou, String[] caselleToBot, String[] caselleToYou) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/decisioneScambioAcquistoInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(board.getGiocatoreBot()));
			writer.println(Creator.creaAvversario(board.getGiocatoreVero()));
			for (CasellaResidenziale casellaResidenziale : board.getGiocatoreBot().getCaselleResidenzialiOggetto()) {
				writer.println(Creator.creaCasellaResidenziale(casellaResidenziale));
			}
			for (CasellaResidenziale casellaResidenziale : board.getGiocatoreVero().getCaselleResidenzialiOggetto()) {
				writer.println(Creator.creaCasellaResidenziale(casellaResidenziale));
			}
			writer.println(Creator.creaScambio(soldiToBot, soldiToYou, caselleToBot, caselleToYou));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void writeDecisioneAcquisto(String casellaDaCedere, String prezzo, Giocatore giocatore, Giocatore avversario) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/decisioneScambioAcquistoInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(giocatore));
			writer.println(Creator.creaAvversario(avversario));
			for (CasellaResidenziale casellaResidenziale : giocatore.getCaselleResidenzialiOggetto()) {
				writer.println(Creator.creaCasellaResidenziale(casellaResidenziale));
			}
			for (CasellaResidenziale casellaResidenziale : avversario.getCaselleResidenzialiOggetto()) {
				writer.println(Creator.creaCasellaResidenziale(casellaResidenziale));
			}
			writer.println(Creator.creaAcquisto(casellaDaCedere, prezzo));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void writeDecisionePuntata(String casella, String puntata, Giocatore giocatore, Board board) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/decisioneAstaInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(giocatore));
			
			
			Collection<Casella> tutteLeCaselle = board.getCaselle().values();
			
			for (Casella caselleTutte : tutteLeCaselle) {
				if(caselleTutte instanceof CasellaResidenziale) {
					writer.println(Creator.creaCasellaResidenziale((CasellaResidenziale)caselleTutte));
				}
				else if(caselleTutte.getTipo().equals("station") || caselleTutte.getTipo().equals("society")) {
					writer.println(Creator.creaCasellaNonResidenziale(caselleTutte));
					
				}
			}
			writer.println(Creator.creaAsta(casella, puntata, giocatore.getNome()));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void writeDecidiCosaCostruire(GiocatoreAI giocatoreAI) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/decisioneCostruzioneInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(giocatoreAI));
			writer.println(Creator.creaDatiSet(giocatoreAI.getCasellePossedute()));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void writeDecidiCosaIpotecare(GiocatoreAI bot)  {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/decisioneIpotecaInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(bot));
			writer.println(Creator.creaDatiPerIpoteca(bot.getCaselleNonIpotecate()));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeDecidiCosaDisipotecare(GiocatoreAI bot)  {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/decisioneIpotecaNegativaInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(bot));
			ArrayList<Casella> tutteLeCaselle = bot.getCaselleIpotecate();
			for (Casella caselleTutte : tutteLeCaselle) {
				if(caselleTutte instanceof CasellaResidenziale) {
					writer.println(Creator.creaCasellaResidenziale((CasellaResidenziale)caselleTutte));
				}
				else if(caselleTutte.getTipo().equals("station") || caselleTutte.getTipo().equals("society")) {
					writer.println(Creator.creaCasellaNonResidenziale(caselleTutte));
					
				}
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeDecisioneScambio2Caselle(String casellaDaCedere, String casellaDaPrendere, Giocatore giocatore, Giocatore avversario) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("encodings/decisioneScambioAcquistoInstance", "UTF-8");
			writer.println(Creator.creaGiocatore(giocatore));
			writer.println(Creator.creaAvversario(avversario));
			for (CasellaResidenziale casellaResidenziale : giocatore.getCaselleResidenzialiOggetto()) {
				writer.println(Creator.creaCasellaResidenziale(casellaResidenziale));
			}
			for (CasellaResidenziale casellaResidenziale : avversario.getCaselleResidenzialiOggetto()) {
				writer.println(Creator.creaCasellaResidenziale(casellaResidenziale));
			}
			writer.println(Creator.creaScambio(casellaDaCedere, casellaDaPrendere));
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
