package Gioco;

import java.awt.EventQueue;
import java.util.ArrayList;

import Elementi.Board;
import Elementi.Giocatore;
import Elementi.GiocatoreAI;
import GUI.TavolaDaGioco;


public class Main {	
	
	public static void main(String[] args) {
		final int numPlayers = 2;
		
		ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
		final Giocatore player = new Giocatore("YOU");
		giocatori.add(player);
		GiocatoreAI bot = new GiocatoreAI("BOT" + String.valueOf(1));
		giocatori.add(bot);
		
		final Board board = new Board(giocatori);
		bot.setBoard(board);
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TavolaDaGioco("Monopoly game", board, numPlayers, player);
            }
        });
	}
	

}
