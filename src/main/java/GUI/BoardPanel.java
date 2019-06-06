package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel  {

	Image tavola;
	ArrayList<Pedina> pedine;
	ArrayList<Integer> posizioniX;
	ArrayList<Integer> posizioniY;
	
	public BoardPanel(int numPlayers)  {
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
	}
	
	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		g.drawImage(tavola, 0, 0, this);
		for(Pedina ped : pedine)  {
			g.drawImage(ped.getImg(), ped.getPosizioneX(), ped.getPosizioneY(), this);
		}
	}
	
	public void repaint(int idx, int places)  {
		pedine.get(idx).avanza(places);
		int newPos = pedine.get(idx).getCasella();
		pedine.get(idx).setPosizioneX(posizioniX.get(newPos));
		pedine.get(idx).setPosizioneY(posizioniY.get(newPos));
		
		this.repaint();
	}
}
