package Elementi;

public class Casella {

	int prezzoVendita;
	int prezzoTransito;
	int prezzoIpoteca;
	int prezzoRiscattoDaIpoteca;
	private String nome;
	private String colore;
	

	
	public Casella(int prezzoVendita, int prezzoTransito, int prezzoIpoteca, int prezzoRiscattoDaIpoteca, String nome,
			String colore) {
		super();
		this.prezzoVendita = prezzoVendita;
		this.prezzoTransito = prezzoTransito;
		this.prezzoIpoteca = prezzoIpoteca;
		this.prezzoRiscattoDaIpoteca = prezzoRiscattoDaIpoteca;
		this.setNome(nome);
		this.colore = colore;
	}
	
	
	public int getPrezzoVendita() {
		return prezzoVendita;
	}
	public void setPrezzoVendita(int prezzoVendita) {
		this.prezzoVendita = prezzoVendita;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
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
	public int getPrezzoRiscattoDaIpoteca() {
		return prezzoRiscattoDaIpoteca;
	}
	public void setPrezzoRiscattoDaIpoteca(int prezzoRiscattoDaIpoteca) {
		this.prezzoRiscattoDaIpoteca = prezzoRiscattoDaIpoteca;
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
	
	
}
