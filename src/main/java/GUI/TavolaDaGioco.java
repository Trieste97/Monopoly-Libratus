package GUI;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import Gioco.Board;


public class TavolaDaGioco extends JFrame{
	
	JPanel panel;
	private ImageIcon immagineTavola;
	JPanel pannelloComandi;
	JPanel pannelloDadi;
	AskBox askBox;
	private Board board;

	public TavolaDaGioco(String title, Board board) {
		super(title);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1000, 750));
	    this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.askBox = new AskBox(board);
		this.board = board;
		caricaImmagine();
		init();
	}
	
	public void init() {
		this.setPreferredSize(new Dimension(800,600));
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
		azioni.setForeground(Color.WHITE);
		
		Button proponiScambio = new Button("Proponi scambio");
		proponiScambio.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				board.proponiScambio();
			}
		});
		
		Button costruisci = new Button("Costruisci");
		costruisci.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				askBox.chiediInfoCostruzione();
			}
		});
		
		Button ipoteca = new Button("Ipoteca");
		ipoteca.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				askBox.chiediInfoIpoteca();
			}
		});
		
		proponiScambio.setPreferredSize(new Dimension(100, 40));
		costruisci.setPreferredSize(new Dimension(100, 40));
		ipoteca.setPreferredSize(new Dimension(100, 40));
		box.add(Box.createVerticalStrut(50));
		box.add(azioni);
		box.add(Box.createVerticalStrut(10));
		box.add(proponiScambio);
		box.add(Box.createVerticalStrut(10));
		box.add(costruisci);
		box.add(Box.createVerticalStrut(10));
		box.add(ipoteca);
		pannelloComandi.add(box);
		pannelloComandi.setBackground(new Color(30, 30, 30));
	}
	
	public void creaPannelloDadi() {
		pannelloDadi = new JPanel();
		
		Button tiraDadi = new Button("Tira i dadi");
		tiraDadi.setPreferredSize(new Dimension(100,  40));
		tiraDadi.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				board.tiraDadi();
			}
		});
		pannelloDadi.add(tiraDadi);
		
		JLabel risultatoDadi = new JLabel("1-1");
		risultatoDadi.setForeground(Color.WHITE);
		pannelloDadi.add(risultatoDadi);
		pannelloDadi.setBackground(new Color(30, 30, 30));
		
	}
}
