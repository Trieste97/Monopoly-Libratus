package Elementi;

import java.util.ArrayList;

public class Giocatore {

	String nome;
	boolean inPrigione;
	ArrayList<Casella> casellePossedute;
	int posizioneInTabella;
	private int soldi;
	
	
	public Giocatore(String nome) {
		this.nome = nome;
		this.inPrigione = false;
		this.casellePossedute = new ArrayList<Casella>();
		this.posizioneInTabella = 0;
		this.soldi = 1000;
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


	public void setSoldi(int soldi) {
		this.soldi = soldi;
	}
	
	public void passaDaStart() {
		this.soldi = this.soldi + 200;
	}
	public void avanza(int numeroCaselle) {
		this.posizioneInTabella = this.posizioneInTabella + numeroCaselle;
	}
	
	public void addCard(Casella casella) {
		this.casellePossedute.add(casella);
	}
	public void removeCard(Casella casella) {
		this.casellePossedute.remove(casella);
	}
	public void stampaCasellePossedute() {
		for (Casella casella : casellePossedute) {
			System.out.println(casella);
		}
	}
}
