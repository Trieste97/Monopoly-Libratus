package Gioco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Elementi.Casella;
import Elementi.Dadi;
import Elementi.Giocatore;

public class Main {
	
	public static void main(String[] args) {
		
		CreatoreCaselle creatore = new CreatoreCaselle();
		creatore.caricaCaselle();
		creatore.stampa();
		
		HashMap<Integer, Casella> tavola = creatore.getCaselle();
		
		Giocatore uno = new Giocatore("uno");
		Giocatore due = new Giocatore("due");
		Giocatore tre = new Giocatore("tre");
		ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
		giocatori.add(uno);
		giocatori.add(due);
		giocatori.add(tre);
		
		Dadi dadi = new Dadi();
		
		
		Scanner s = new Scanner(System.in);
		String avanti = "a";
		
		int giocatore = 0;
		
		while(avanti != "q") {
			
			avanti = s.nextLine();
			System.out.println("turno del giocatore ");
			System.out.println(giocatori.get(giocatore).getNome());
			int numero = dadi.tiraDadi();
			System.out.println("è uscito il numero");
			System.out.println(numero);
			dadi.stampa();
			
			
			int posizioneGiocatore = giocatori.get(giocatore).getPosizioneInTabella();
			int nuovaPosizione = posizioneGiocatore + numero;
			giocatori.get(giocatore).setPosizioneInTabella(nuovaPosizione);
			
			System.out.println("il giocatore si trova sulla casella " + nuovaPosizione + " in ");
			System.out.println(tavola.get((Integer) nuovaPosizione));
			
			
			
			if (dadi.isDoppioNumero()) {
				System.out.println("DOPPIO NUMERO");
			}
			else {
				giocatore++;
				if(giocatore == 3) {
					giocatore = 0;
				}
			}
			
		}
		
	}

}
