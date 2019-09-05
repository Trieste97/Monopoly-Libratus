package Elementi;

//@Id("casella")
public class Casella {

//	@Param(2)
	int prezzoVendita;
//	@Param(3)
	int prezzoTransito;
//	@Param(4)
	int prezzoIpoteca;
//	@Param(0)
	private String nome;
//	@Param(5)
	boolean ipotecata;
//	@Param(1)
	Giocatore proprietario;
	String tipo;
	
	
	public Casella(int prezzoVendita, int prezzoTransito, int prezzoIpoteca, String nome, String tipo) {
		super();
		this.tipo = tipo;
		this.prezzoVendita = prezzoVendita;
		this.prezzoTransito = prezzoTransito;
		this.prezzoIpoteca = prezzoIpoteca;
		this.ipotecata = false;
		this.setNome(nome);
	}
	
	
	public String getTipo()  {
		return tipo;
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
