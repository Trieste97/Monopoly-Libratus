package Gioco;

import java.awt.EventQueue;
import java.util.ArrayList;

import Elementi.Board;
import Elementi.Giocatore;
import GUI.TavolaDaGioco;


public class Main {	
	
	public static void main(String[] args) {
		final int numPlayers = 2;
		
		ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
		final Giocatore player = new Giocatore("YOU");
		giocatori.add(player);
		for(int i = 1; i < numPlayers; i++)  {
			Giocatore bot = new Giocatore("BOT" + String.valueOf(i));
			giocatori.add(bot);
		}
		
		final Board board = new Board(giocatori);
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TavolaDaGioco("Monopoly game", board, numPlayers, player);
            }
        });
	}
	

}
