package Elementi;

public class CasellaResidenziale extends Casella {
	
	String colore;
	int prezzoTransitoNumeroCase1;
	int prezzoTransitoNumeroCase2;
	int prezzoTransitoNumeroCase3;
	int prezzoTransitoNumeroCase4;
	int prezzoTransitoHotel;
	int prezzoCostruzioneCasa;
	
	int numeroCaseCostruite;
	
	
	
	public CasellaResidenziale(int prezzoVendita, int prezzoTransito, int prezzoIpoteca, String nome, 
			String colore, int prezzoTransitoNumeroCase1, int prezzoTransitoNumeroCase2,
			int prezzoTransitoNumeroCase3, int prezzoTransitoNumeroCase4, int prezzoTransitoHotel,
			int prezzoCostruzioneCasa) {
		
		super(prezzoVendita, prezzoTransito, prezzoIpoteca, nome);
		this.colore = colore;
		this.prezzoTransitoNumeroCase1 = prezzoTransitoNumeroCase1;
		this.prezzoTransitoNumeroCase2 = prezzoTransitoNumeroCase2;
		this.prezzoTransitoNumeroCase3 = prezzoTransitoNumeroCase3;
		this.prezzoTransitoNumeroCase4 = prezzoTransitoNumeroCase4;
		this.prezzoTransitoHotel = prezzoTransitoHotel;
		this.prezzoCostruzioneCasa = prezzoCostruzioneCasa;
		this.numeroCaseCostruite = 0;
	}
	
	
	
	
	
	public int getPrezzoTransitoNumeroCase1() {
		return prezzoTransitoNumeroCase1;
	}
	public void setPrezzoTransitoNumeroCase1(int prezzoTransitoNumeroCase1) {
		this.prezzoTransitoNumeroCase1 = prezzoTransitoNumeroCase1;
	}
	public int getPrezzoTransitoNumeroCase2() {
		return prezzoTransitoNumeroCase2;
	}
	public void setPrezzoTransitoNumeroCase2(int prezzoTransitoNumeroCase2) {
		this.prezzoTransitoNumeroCase2 = prezzoTransitoNumeroCase2;
	}
	public int getPrezzoTransitoNumeroCase3() {
		return prezzoTransitoNumeroCase3;
	}
	public void setPrezzoTransitoNumeroCase3(int prezzoTransitoNumeroCase3) {
		this.prezzoTransitoNumeroCase3 = prezzoTransitoNumeroCase3;
	}
	public int getPrezzoTransitoNumeroCase4() {
		return prezzoTransitoNumeroCase4;
	}
	public void setPrezzoTransitoNumeroCase4(int prezzoTransitoNumeroCase4) {
		this.prezzoTransitoNumeroCase4 = prezzoTransitoNumeroCase4;
	}
	public int getPrezzoTransitoHotel() {
		return prezzoTransitoHotel;
	}
	public void setPrezzoTransitoHotel(int prezzoTransitoHotel) {
		this.prezzoTransitoHotel = prezzoTransitoHotel;
	}
	public int getPrezoCostruzioneCasa() {
		return prezzoCostruzioneCasa;
	}
	public void setPrezoCostruzioneCasa(int prezoCostruzioneCasa) {
		this.prezzoCostruzioneCasa = prezoCostruzioneCasa;
	}
	
	
}
