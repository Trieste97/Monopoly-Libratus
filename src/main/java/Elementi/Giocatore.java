package Elementi;

import java.util.ArrayList;

public class Giocatore {

	String nome;
	boolean inPrigione;
	ArrayList<Casella> casellePossedute;
	int posizioneInTabella;
	int soldi;
	
	
	public Giocatore(String nome) {
		this.nome = nome;
		this.inPrigione = false;
		this.casellePossedute = new ArrayList<Casella>();
		this.posizioneInTabella = 0;
		this.soldi = CostantiGioco.SOLDI_INIZIALI;
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
		posizioneInTabella = (posizioneInTabella + numeroPassi) % 40;
	}
	public void stampaCasellePossedute() {
		for (Casella casella : casellePossedute) {
			System.out.println(casella);
		}
	}
}
