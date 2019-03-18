package Elementi;

import java.util.ArrayList;

public class Giocatore {

	String nome;
	boolean inPrigione;
	ArrayList<Casella> casellePossedute;
	int posizioneInTabella;
	
	
	public Giocatore(String nome) {
		this.nome = nome;
		this.inPrigione = false;
		this.casellePossedute = new ArrayList<Casella>();
		this.posizioneInTabella = 0;
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
	
	
}
