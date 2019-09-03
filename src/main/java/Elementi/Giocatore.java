package Elementi;

import java.util.ArrayList;

//@Id("Giocatore")
public class Giocatore {

//	@Param(0)
	String nome;
//	@Param(2)
	boolean inPrigione;
	ArrayList<Casella> casellePossedute;
	int[] coloriPosseduti;
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
		this.coloriPosseduti = new int[8];
		for(int i = 0; i < 8; i++)
			this.coloriPosseduti[i] = 0;
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
		if (casella instanceof CasellaResidenziale)  {
			aggiornaColoriPosseduti();
		}
	}
	public void rimuovi(Casella casella)  {
		casellePossedute.remove(casella);
		if (casella instanceof CasellaResidenziale)  {
			aggiornaColoriPosseduti();
		}
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
	
	public int getNumCasellePossedute()  {
		return casellePossedute.size();
	}
	
	public void aggiornaColoriPosseduti()  {
		for(Casella c : casellePossedute)  {
			if (c instanceof CasellaResidenziale)  {
				CasellaResidenziale cas = (CasellaResidenziale) c;
				String col = cas.getColore();
				if (col.equals("brown")) this.coloriPosseduti[0] += 1;
				else if (col.equals("lightblue")) this.coloriPosseduti[1] += 1;
				else if (col.equals("pink")) this.coloriPosseduti[2] += 1;
				else if (col.equals("orange")) this.coloriPosseduti[3] += 1;
				else if (col.equals("red")) this.coloriPosseduti[4] += 1;
				else if (col.equals("yellow")) this.coloriPosseduti[5] += 1;
				else if (col.equals("green")) this.coloriPosseduti[6] += 1;
				else if (col.equals("blue")) this.coloriPosseduti[7] += 1;
					
			}
		}
	}
	
	public int getNumSetsPosseduti()  {
		int num = 0;
		if (this.coloriPosseduti[0] == 2) num += 1;
		if (this.coloriPosseduti[7] == 2) num += 1;
		for(int i = 1; i <= 6; i++)
			if (this.coloriPosseduti[i] == 3) num += 1;
		
		return num;
	}
	
	//serve per ereditarietà
	public int decidiCosaFare(ArrayList<Giocatore> giocatori)  {
		return -1;
	}
}
