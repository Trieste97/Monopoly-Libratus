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
		
		super(prezzoVendita, prezzoTransito, prezzoIpoteca, nome, colore);
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
	public int getPrezzoCostruzioneCasa() {
		return prezzoCostruzioneCasa;
	}
	public void setPrezzoCostruzioneCasa(int prezoCostruzioneCasa) {
		this.prezzoCostruzioneCasa = prezoCostruzioneCasa;
	}
	public String getColore()  {
		return this.colore;
	}
	
	public int getNumeroCaseCostruite()  {
		return this.numeroCaseCostruite;
	}
	public void setNumeroCaseCostruite(int numeroCaseCostruite)  {
		this.numeroCaseCostruite = numeroCaseCostruite;
	}
	
	public void aggiungiCasa()  {
		if(numeroCaseCostruite < 5)
			numeroCaseCostruite++;
	}
	public void rimuoviCasa()  {
		if(numeroCaseCostruite > 0)
			numeroCaseCostruite--;
	}
	
	public boolean caseMax()  {
		return numeroCaseCostruite == 5;
	}
	
	@Override
	public int getPrezzoPassaggio()  {
		if(this.tipo.equals("ColoredLand"))  {
			int prezzo = 0;
			switch(numeroCaseCostruite)  {
			case 0:
				prezzo = this.prezzoTransito;
				break;
			case 1:
				prezzo = this.prezzoTransitoNumeroCase1;
				break;
			case 2:
				prezzo = this.prezzoTransitoNumeroCase2;
				break;
			case 3:
				prezzo = this.prezzoTransitoNumeroCase3;
				break;
			case 4:
				prezzo = this.prezzoTransitoNumeroCase4;
				break;
			case 5:
				prezzo = this.prezzoTransitoHotel;
				break;
			}
			return prezzo;
		} else {
			return super.getPrezzoPassaggio();
		}
	}
}
