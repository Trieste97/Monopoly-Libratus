package GUI;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;


public class TavolaDaGioco extends JFrame{
	
	JPanel panel;
	private ImageIcon immagineTavola;
	JPanel pannelloComandi;
	JPanel pannelloDadi;

	public TavolaDaGioco() {
		caricaImmagine();
		init();
	}
	
	public void init() {
		panel = new JPanel();
		panel.setBackground(new Color(30, 30, 30));
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel(immagineTavola), BorderLayout.CENTER);
		creaPannelloComandi();
		panel.add(pannelloComandi, BorderLayout.EAST);
		creaPannelloDadi();
		panel.add(pannelloDadi, BorderLayout.SOUTH);
		this.setContentPane(panel);
	}
	
	public void caricaImmagine() {
		immagineTavola = new ImageIcon("src\\main\\java\\GUI\\immagineTavola.jpg");
	}
	
	public void creaPannelloComandi() {
		pannelloComandi = new JPanel();
		Box box = Box.createVerticalBox();
		JLabel azioni = new JLabel("Fai il tuo gioco");
		azioni.setForeground(Color.WHITE);;
		Button compra = new Button("Compra");
		Button costruisci = new Button("Costruisci");
		Button ipoteca = new Button("Ipoteca");
		compra.setPreferredSize(new Dimension(100, 40));
		costruisci.setPreferredSize(new Dimension(100, 40));
		ipoteca.setPreferredSize(new Dimension(100, 40));
		box.add(Box.createVerticalStrut(50));
		box.add(azioni);
		box.add(Box.createVerticalStrut(10));
		box.add(compra);
		box.add(Box.createVerticalStrut(10));
		box.add(costruisci);
		box.add(Box.createVerticalStrut(10));
		box.add(ipoteca);
		pannelloComandi.add(box);
		/*pannelloComandi.add(azioni);
		pannelloComandi.add(compra);
		pannelloComandi.add(costruisci);
		pannelloComandi.add(ipoteca); */
		pannelloComandi.setBackground(new Color(30, 30, 30));
	}
	
	public void creaPannelloDadi() {
		pannelloDadi = new JPanel();
		Button tiraDadi = new Button("Tira i dadi");
		tiraDadi.setPreferredSize(new Dimension(100,  40));
		pannelloDadi.add(tiraDadi);
		JLabel risultatoDadi = new JLabel("1-1");
		risultatoDadi.setForeground(Color.WHITE);
		pannelloDadi.add(risultatoDadi);
		pannelloDadi.setBackground(new Color(30, 30, 30));
		
	}
	
	
	public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TavolaDaGioco().setVisible(true);
            }
        });
    }
}
