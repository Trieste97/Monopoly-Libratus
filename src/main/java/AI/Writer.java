package AI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Elementi.Casella;
import Elementi.CasellaResidenziale;
import Elementi.Giocatore;

public class Writer {

	public Writer() {
		// TODO Auto-generated constructor stub
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
	

}
