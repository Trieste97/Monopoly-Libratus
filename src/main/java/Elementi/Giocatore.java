package Elementi;

import java.util.ArrayList;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

//@Id("Giocatore")
public class Giocatore {

//	@Param(0)
	String nome;
//	@Param(2)
	boolean inPrigione;
	ArrayList<Casella> casellePossedute;
	int posizioneInTabella;
//	@Param(1)
	int soldi;
//	@Param(3)
	private int numTokensPrigione;
//	@Param(4)
	int numTurniPrigioneConsecutivi;
	
	public Giocatore(String nome) {
		this.nome = nome;
		this.inPrigione = false;
		this.casellePossedute = new ArrayList<Casella>();
		this.posizioneInTabella = 0;
		this.soldi = CostantiGioco.SOLDI_INIZIALI;
		this.setNumTokensPrigione(0);
		this.numTurniPrigioneConsecutivi = 0;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isInPrigione() {
		return inPrigione;
	}
	public void setInPrigione(boolean inPrigione) {
		this.inPrigione = inPrigione;
	}
	public ArrayList<Casella> getCasellePossedute() {
		return casellePossedute;
	}
	public void setCasellePossedute(ArrayList<Casella> casellePossedute) {
		this.casellePossedute = casellePossedute;
	}
	public int getPosizioneInTabella() {
		return posizioneInTabella;
	}
	public void setPosizioneInTabella(int posizioneInTabella) {
		this.posizioneInTabella = (posizioneInTabella % 40);
	}
	public int getSoldi() {
		return soldi;
	}
	public void aumentaSoldi(int soldi) {
		this.soldi += soldi;
	}
	public void diminuisciSoldi(int soldi)  {
		this.soldi -= soldi;
	}
	public void aggiungiCasella(Casella casella)  {
		casellePossedute.add(casella);
	}
	public void rimuovi(Casella casella)  {
		casellePossedute.remove(casella);
	}
	public void avanza(int numeroPassi)  {
		if (this.inPrigione)  {
			return;
		}
		posizioneInTabella = (posizioneInTabella + numeroPassi) % 40;
	}
	public void stampaCasellePossedute() {
		for (Casella casella : casellePossedute) {
			System.out.println(casella);
		}
	}
	public void addTokenPrigione()  {
		setNumTokensPrigione(getNumTokensPrigione() + 1);
	}
	public boolean hasTokenPrigione()  {
		return getNumTokensPrigione() > 0;
	}
	public void usaTokenPrigione()  {
		if(getNumTokensPrigione() > 0 && inPrigione)  {
			setNumTokensPrigione(getNumTokensPrigione() - 1);
			inPrigione = false;
		}
	}
	public ArrayList<String> getCaselleResidenziali()  {
		ArrayList<String> listaCasRes = new ArrayList<String>();
		
		for(Casella c : casellePossedute)  {
			if(c instanceof CasellaResidenziale)  {
				listaCasRes.add(c.getNome());
			}
		}
		
		return listaCasRes;
	}
	public ArrayList<CasellaResidenziale> getCaselleResidenzialiOggetto()  {
		ArrayList<CasellaResidenziale> listaCasRes = new ArrayList<CasellaResidenziale>();
		
		for(Casella c : casellePossedute)  {
			if(c instanceof CasellaResidenziale)  {
				listaCasRes.add((CasellaResidenziale) c);
			}
		}
		
		return listaCasRes;
	}
	public ArrayList<String> getCaselleNonIpotecate()  {
		ArrayList<String> listaCas = new ArrayList<String>();
		
		for(Casella c : casellePossedute)  {
			if(!c.isIpotecata())  {
				listaCas.add(c.getNome());
			}
		}
		
		return listaCas;
	}
	
	public void resetTurniPrigione()  {
		this.numTurniPrigioneConsecutivi = 0;
	}
	
	public void incrTurniPrigione()  {
		this.numTurniPrigioneConsecutivi += 1;
	}
	
	public int getTurniPrigione()  {
		return this.numTurniPrigioneConsecutivi;
	}


	public int getNumTokensPrigione() {
		return numTokensPrigione;
	}


	public void setNumTokensPrigione(int numTokensPrigione) {
		this.numTokensPrigione = numTokensPrigione;
	}
	
	//serve per ereditarietà
	public int decidiCosaFare()  {
		return -1;
	}
}
