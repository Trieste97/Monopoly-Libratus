package Gioco;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;

//import Gioco.Board;
import Elementi.Casella;
import Elementi.Dadi;
import Elementi.Giocatore;
import GUI.TavolaDaGioco;

public class Main {
	
	/*public static void main1(String[] args) {
		
		Giocatore uno = new Giocatore("uno");
		Giocatore due = new Giocatore("due");
		Giocatore tre = new Giocatore("tre");
		ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
		giocatori.add(uno);
		giocatori.add(due);
		giocatori.add(tre);
		Board board = new Board(giocatori);
		
		Dadi dadi = new Dadi();
		
		//prova
		board.finisciTurno();
		board.gestisciPosizione("Chance");
		board.gestisciPosizione("Jail");
		
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
			
			System.out.println("saldo attuale " + giocatori.get(giocatore).getSoldi());
			System.out.println("posizione attuale " + giocatori.get(giocatore).getPosizioneInTabella());
			
			int posizioneGiocatore = giocatori.get(giocatore).getPosizioneInTabella();
			int nuovaPosizione = posizioneGiocatore + numero;
			giocatori.get(giocatore).setPosizioneInTabella(nuovaPosizione);
			
			System.out.println("il giocatore si trova sulla casella " + nuovaPosizione%40 + " in ");
			//System.out.println(tavola.get((Integer) nuovaPosizione));
			
			
			
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
		
	}*/
	
	
	public static void main(String[] args) {
		int numPlayer = 2;
		
		try {
			do {
				numPlayer = Integer.parseInt(JOptionPane.showInputDialog("Enter Player Amount"));
			} while (2 > numPlayer || numPlayer > 4);
		} catch (Exception e) {
			System.exit(0);
		}
		
		final int numPlayers = numPlayer;
		
		ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
		Giocatore player = new Giocatore("YOU");
		giocatori.add(player);
		for(int i = 1; i < numPlayer; i++)  {
			Giocatore bot = new Giocatore("BOT" + String.valueOf(i));
			giocatori.add(bot);
		}
		
		final Board board = new Board(giocatori);
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TavolaDaGioco("Monopoly game", board, numPlayers);
            }
        });
	}
	

}
