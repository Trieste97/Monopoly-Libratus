package Gioco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import Elementi.Casella;
import Elementi.CasellaResidenziale;


public class CreatoreCaselle {
	
	
	File file;
	
	//private ArrayList<Casella> caselle;
	
	HashMap<String, Casella> caselle;
	
	public CreatoreCaselle() {

		//this.setCaselle(new ArrayList<Casella>());
		this.caselle = new HashMap<String, Casella>();
	}
	
	
	
	public HashMap<Integer,String> caricaMappa() {

		HashMap<Integer,String> mappa = new HashMap<Integer,String>();
		
		try {
			Scanner scanner = new Scanner(new File("src/main/resources/board.txt"));
			int pos = -1;
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				if (line.isEmpty() || line.startsWith("//")) {
					continue;
				}
				pos++;
				String[] informazioniCasella = line.split(",");
				String tipo = informazioniCasella[0];
				
				if(tipo.equals("Special"))  {
					
					String nome = informazioniCasella[1];
					mappa.put(pos, nome);
					continue;
				} 
				
				Casella casella;
						
				String nome = informazioniCasella[1];
				String prezzoVenditas = informazioniCasella[2];
				String prezzoTransitos = informazioniCasella[3];
				
				int prezzoVendita = Integer.parseInt(prezzoVenditas);
				int prezzoTransito = Integer.parseInt(prezzoTransitos);
				
				if(tipo.equals("ColoredLand")) {
					
					String colore = informazioniCasella[4];
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
					
					casella = new CasellaResidenziale(prezzoVendita, prezzoTransito, prezzoVendita/2, nome, colore,
							prezzoTransitoNumeroCase1, prezzoTransitoNumeroCase2, prezzoTransitoNumeroCase3,
							prezzoTransitoNumeroCase4, prezzoTransitoHotel, prezzoCostruzioneCasa);
					
					
					mappa.put(pos, casella.getNome());
					caselle.put(casella.getNome(), casella);
				}
				else if(tipo.equals("Transportation"))  {
					casella = new Casella(prezzoVendita, prezzoTransito, prezzoVendita/2, nome, "station");
					
					mappa.put(pos, nome);
					caselle.put(nome, casella);
				}
				else if(tipo.equals("Infrastructure"))  {
					casella = new Casella(prezzoVendita, prezzoTransito, prezzoVendita/2, nome, "society");
					
					mappa.put(pos, nome);
					caselle.put(nome, casella);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return mappa;
	}



	public void stampa() {
		for (Casella casella : caselle.values()) {
			System.out.println(casella);
		}
	}



	public HashMap<String, Casella> getCaselle() {
		return caselle;
	}

	public void setCaselle(HashMap<String, Casella> caselle) {
		this.caselle = caselle;
	}

	public static void caricaNomiCaselle(HashSet<String> set)  {
		try {
			Scanner scanner = new Scanner(new File("src/main/resources/board.txt"));
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				if (line.isEmpty() || line.startsWith("//")) {
					continue;
				}
				String[] informazioniCasella = line.split(",");
				String tipo = informazioniCasella[0];
				
				if(!tipo.equals("Special"))  {
					
					String nome = informazioniCasella[1];
					set.add(nome);
					continue;
				} 
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
