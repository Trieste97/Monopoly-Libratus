package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Elementi.Casella;
import Elementi.Giocatore;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel  {

	Giocatore player;
	Image tavola;
	ArrayList<Pedina> pedine;
	HashMap<String,Contratto> contratti;
	ArrayList<Integer> posizioniX;
	ArrayList<Integer> posizioniY;
	
	public BoardPanel(int numPlayers, Giocatore player)  {
		this.player = player;
		try {
			tavola = ImageIO.read(new File("src/main/resources/immagineTavola.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inizializzaPosizioni();
		
		pedine = new ArrayList<Pedina>();
		for(int i = 0; i < numPlayers; i++)  {
			pedine.add(new Pedina(i));
		}
		
		for(Pedina ped : pedine)  {
			ped.setPosizioneX(posizioniX.get(0));
			ped.setPosizioneY(posizioniY.get(0));
		}
		
		String contr = "A1,A2,B1,B2,B3,C1,C2,C3,D1,D2,D3,E1,E2,E3,"
				+ "F1,F2,F3,G1,G2,G3,H1,H2,S1,S2,S3,S4,T1,T2";
		contratti = new HashMap<String,Contratto>();
		for(String nome : contr.split(","))  {
			Contratto c = new Contratto(nome);
			contratti.put(nome, c);
		}
		
		inizializzaPosizioniContratti();
	}
	
	public void inizializzaPosizioni()  {
		posizioniX = new ArrayList<Integer>(40);
		posizioniY = new ArrayList<Integer>(40);
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/main/resources/positions.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.isEmpty() || line.startsWith("//")) {
				continue;
			}
			
			String[] position = line.split(",");
			posizioniX.add(Integer.parseInt(position[0]));
			posizioniY.add(Integer.parseInt(position[1]));
		}
		
		scanner.close();
	}
	
	public void inizializzaPosizioniContratti()  {
		contratti.get("A1").setPosizioneXY(0,440);
		contratti.get("A2").setPosizioneXY(90,440);
		contratti.get("B1").setPosizioneXY(180,440);
		contratti.get("B2").setPosizioneXY(270,440);
		contratti.get("B3").setPosizioneXY(360,440);
		contratti.get("C1").setPosizioneXY(0,490);
		contratti.get("C2").setPosizioneXY(90,490);
		contratti.get("C3").setPosizioneXY(180,490);
		contratti.get("D1").setPosizioneXY(270,490);
		contratti.get("D2").setPosizioneXY(360,490);
		contratti.get("D3").setPosizioneXY(450,490);
		contratti.get("E1").setPosizioneXY(0,540);
		contratti.get("E2").setPosizioneXY(90,540);
		contratti.get("E3").setPosizioneXY(180,540);
		contratti.get("F1").setPosizioneXY(270,540);
		contratti.get("F2").setPosizioneXY(360,540);
		contratti.get("F3").setPosizioneXY(450,540);
		contratti.get("G1").setPosizioneXY(0,590);
		contratti.get("G2").setPosizioneXY(90,590);
		contratti.get("G3").setPosizioneXY(180,590);
		contratti.get("H1").setPosizioneXY(270,590);
		contratti.get("H2").setPosizioneXY(360,590);
		contratti.get("S1").setPosizioneXY(0,640);
		contratti.get("S2").setPosizioneXY(90,640);
		contratti.get("S3").setPosizioneXY(180,640);
		contratti.get("S4").setPosizioneXY(270,640);
		contratti.get("T1").setPosizioneXY(360,640);
		contratti.get("T2").setPosizioneXY(450,640);
	}
	
	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		g.drawImage(tavola, 0, 0, this);
		for(Pedina ped : pedine)  {
			g.drawImage(ped.getImg(), ped.getPosizioneX(), ped.getPosizioneY(), this);
		}
		for(Casella c : player.getCasellePossedute())  {
			Contratto ct = contratti.get(c.getNome());
			g.drawImage(ct.getImg(), ct.getPosizioneX(), ct.getPosizioneY(), this);
		}
	}
	
	public void repaint(int idx, Giocatore player2)  {
		int newPos = player2.getPosizioneInTabella();
		if (newPos == 30)
			newPos = 10;
		pedine.get(idx).setPosizioneX(posizioniX.get(newPos));
		pedine.get(idx).setPosizioneY(posizioniY.get(newPos));
		
		this.repaint();
	}
}
