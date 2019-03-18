package Gioco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Elementi.Casella;
import Elementi.CasellaResidenziale;


public class CreatoreCaselle {
	
	
	File file;
	
	//private ArrayList<Casella> caselle;
	
	HashMap<Integer, Casella> caselle;
	
	public CreatoreCaselle() {

		//this.setCaselle(new ArrayList<Casella>());
		this.caselle = new HashMap<Integer, Casella>();
		this.file = new File("src/main/resources/gameBoard.txt");
	}
	
	
	
	public void caricaCaselle() {

		try {
			Scanner scanner = new Scanner(file);
			int posizione = -1;
			while (scanner.hasNextLine()) {
				
				posizione++;
				
				
				String casella = scanner.nextLine();
				String[] informazioniCasella = casella.split(",");
				String tipo = informazioniCasella[0];
				String nome = informazioniCasella[1];
				String colore = informazioniCasella[4];
				String prezzoVanditas = informazioniCasella[2];
				String prezzoTransitos = informazioniCasella[3];
				String prezzoIpotecas = informazioniCasella[2];
				String prezzoRiscattoDaIpotecas = informazioniCasella[3];
				
				int prezzoVandita = Integer.parseInt(prezzoVanditas);
				int prezzoTransito = Integer.parseInt(prezzoTransitos);
				int prezzoIpoteca = Integer.parseInt(prezzoIpotecas);
				int prezzoRiscattoDaIpoteca = Integer.parseInt(prezzoRiscattoDaIpotecas);
				
				if (tipo.equals("ColoredLand")) {
					String prezzoTransitoNumeroCase1s = informazioniCasella[5];
					String prezzoTransitoNumeroCase2s = informazioniCasella[6];
					String prezzoTransitoNumeroCase3s = informazioniCasella[7];
					String prezzoTransitoNumeroCase4s = informazioniCasella[8];
					String prezzoTransitoHotels = informazioniCasella[9];
					String prezzoCostruzioneCasas = informazioniCasella[11];
					
					int prezzoTransitoNumeroCase1 = Integer.parseInt(prezzoTransitoNumeroCase1s);
					int prezzoTransitoNumeroCase2 = Integer.parseInt(prezzoTransitoNumeroCase2s);
					int prezzoTransitoNumeroCase3 = Integer.parseInt(prezzoTransitoNumeroCase3s);
					int prezzoTransitoNumeroCase4 = Integer.parseInt(prezzoTransitoNumeroCase4s);
					int prezzoTransitoHotel = Integer.parseInt(prezzoTransitoHotels);
					int prezzoCostruzioneCasa = Integer.parseInt(prezzoCostruzioneCasas);
					
					//this.getCaselle().add(new CasellaResidenziale(prezzoVandita, prezzoTransito, prezzoIpoteca, prezzoRiscattoDaIpoteca, nome, colore, prezzoTransitoNumeroCase1, prezzoTransitoNumeroCase2, prezzoTransitoNumeroCase3, prezzoTransitoNumeroCase4, prezzoTransitoHotel, prezzoCostruzioneCasa));
					Casella casellaDaAggiungere = new CasellaResidenziale(prezzoVandita, prezzoTransito, prezzoIpoteca, prezzoRiscattoDaIpoteca, nome, colore, prezzoTransitoNumeroCase1, prezzoTransitoNumeroCase2, prezzoTransitoNumeroCase3, prezzoTransitoNumeroCase4, prezzoTransitoHotel, prezzoCostruzioneCasa);
					this.caselle.put(posizione, casellaDaAggiungere);
					
				}
				else {
					
					//this.getCaselle().add(new Casella(prezzoVandita, prezzoTransito, prezzoIpoteca, prezzoRiscattoDaIpoteca, nome, colore));
					Casella casellaDaAggiungere = new Casella(prezzoVandita, prezzoTransito, prezzoIpoteca, prezzoRiscattoDaIpoteca, nome, colore);
					this.caselle.put(posizione, casellaDaAggiungere);
				}
				
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File : " + file.getAbsolutePath() + " NOT FOUND!");
		}

	}



	public void stampa() {
		/*for (Casella casella : getCaselle()) {
			System.out.println(casella);
		}*/
		for (Casella casella : caselle.values()) {
			System.out.println(casella);
		}
	}



	public HashMap<Integer, Casella> getCaselle() {
		return caselle;
	}



	public void setCaselle(HashMap<Integer, Casella> caselle) {
		this.caselle = caselle;
	}



	/*public ArrayList<Casella> getCaselle() {
		return caselle;
	}



	public void setCaselle(ArrayList<Casella> caselle) {
		this.caselle = caselle;
	}*/



}
