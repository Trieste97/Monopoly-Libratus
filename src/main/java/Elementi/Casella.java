package Elementi;

public class Casella {

	int prezzoVendita;
	int prezzoTransito;
	int prezzoIpoteca;
	private String nome;
	boolean ipotecata;
	Giocatore proprietario;
	
	public Casella(int prezzoVendita, int prezzoTransito, int prezzoIpoteca, String nome) {
		super();
		this.prezzoVendita = prezzoVendita;
		this.prezzoTransito = prezzoTransito;
		this.prezzoIpoteca = prezzoIpoteca;
		this.ipotecata = false;
		this.setNome(nome);
	}
	
	
	public int getPrezzoVendita() {
		return prezzoVendita;
	}
	public void setPrezzoVendita(int prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	public int getPrezzoTransito() {
		return prezzoTransito;
	}
	public void setPrezzoTransito(int prezzoTransito) {
		this.prezzoTransito = prezzoTransito;
	}
	
	public int getPrezzoIpoteca() {
		return prezzoIpoteca;
	}
	public void setPrezzoIpoteca(int prezzoIpoteca) {
		this.prezzoIpoteca = prezzoIpoteca;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public String toString() {
		return this.nome;
	}
	
	public boolean haProprietario()  {
		if(proprietario != null)  {
			return true;
		}
		return false;
	}
	
	public Giocatore getProprietario()  {
		return proprietario;
	}
	public void setProprietario(Giocatore proprietario)  {
		this.proprietario = proprietario;
	}
	public boolean isIpotecata() {
		return ipotecata;
	}
	public void setIpotecata(boolean ipotecata) {
		this.ipotecata = ipotecata;
	}
	
}
