package Gioco;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Board {

	ArrayList<Player> players;
	Bank bank;
	HashMap<Integer, String> map;
	HashMap<String, Card> cards;
	
	//simple constructor
	public Board(ArrayList<Player> players)  {
		this.players = players;
		
		this.createMap();
	}
	
	//parserizza "board.txt" e aggiunge i territori della mappa e le carte definite nel file (le carte vengono aggiunte anche in banca)
	private void createMap()  {
		map = new HashMap<Integer, String>();
		cards = new HashMap<String, Card>();
		
		try {
			Scanner scanner = new Scanner(new File("resources/board.txt"));
			int pos = 0;
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (!line.isEmpty() || !line.startsWith("//")) {
					
					String type = line.substring(0, ',');
					String info = line.substring(type.length()+1);					
					
					if(type.equals("Special"))  {
						map.put(pos, info);
					} else  {
						
						Card card; 
						
						if(type.equals("Buildable")) {
							card = new BuildableCard(info);
							
							map.put(pos, card.getName());
							cards.put(card.getName(), card);
							bank.addCard(card);
						}
						else if(type.equals("NotBuildable"))  {
							card = new NotBuildableCard(info);
							
							map.put(pos, card.getName());
							cards.put(card.getName(), card);
							bank.addCard(card);
						}
					}
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}
	}
}
