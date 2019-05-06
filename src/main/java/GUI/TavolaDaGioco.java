package GUI;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Elementi.Casella;
import Elementi.Giocatore;
import Gioco.Board;


public class TavolaDaGioco extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JPanel panel;
	private ImageIcon immagineTavola;
	JPanel pannelloComandi;
	JPanel pannelloDadi;
	JPanel pannelloCronologia;
	JPanel pannelloPedine;
	AskBox askBox;
	private Board board;
	
	Pedina pedina = new Pedina();

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
		creaPannelloPedine();
//		panel.add(new JLabel(pedina.getImmagine()), BorderLayout.CENTER);
//		panel.add(new JLabel(immagineTavola), BorderLayout.CENTER);
		panel.add(pannelloPedine, BorderLayout.CENTER);
		creaPannelloComandi();
		panel.add(pannelloComandi, BorderLayout.EAST);
//		creaPannelloDadi();
		//panel.add(pannelloDadi, BorderLayout.SOUTH);
		creaPannelloCronologia();
		//panel.add(pannelloCronologia, BorderLayout.WEST);
		panel.add(scrollbar, BorderLayout.WEST);
		
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
		proponiScambio.setPreferredSize(new Dimension(100, 40));
		proponiScambio.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				askBox.chiediConChiScambiare();
			}
		});
		
		Button costruisci = new Button("Costruisci");
		costruisci.setPreferredSize(new Dimension(100, 40));
		costruisci.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				askBox.chiediInfoCostruzione();
			}
		});
		
		Button ipoteca = new Button("Ipoteca");
		ipoteca.setPreferredSize(new Dimension(100, 40));
		ipoteca.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				askBox.chiediInfoIpoteca();
			}
		});
		
		Button esciPrigione = new Button("Esci di prigione");
		esciPrigione.setPreferredSize(new Dimension(100, 40));
		esciPrigione.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				askBox.chiediComeUscirePrigione();
			}
		});
		
		
		
		box.add(Box.createVerticalStrut(50));
		box.add(azioni);
		box.add(Box.createVerticalStrut(10));
		box.add(proponiScambio);
		box.add(Box.createVerticalStrut(10));
		box.add(costruisci);
		box.add(Box.createVerticalStrut(10));
		box.add(ipoteca);
		box.add(Box.createVerticalStrut(10));
		box.add(esciPrigione);
		box.add(Box.createVerticalStrut(10));
		box.add(creaPannelloDadi());
		
		pannelloComandi.add(box);
		pannelloComandi.setBackground(new Color(30, 30, 30));
	}
	
	JLabel risultatoDadi = new JLabel("");
	public JPanel creaPannelloDadi() {
		pannelloDadi = new JPanel();
		
		Button tiraDadi = new Button("Tira i dadi");
		tiraDadi.setPreferredSize(new Dimension(100,  40));
		tiraDadi.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				board.rollaDadi();
				updateTabellone();
				settaNumeroDadiGrafico();
				board.tiraDadi();
				aggiornaCronologia();
			}
		});
		
		pannelloDadi.add(tiraDadi);
		
		risultatoDadi.setForeground(Color.WHITE);
		pannelloDadi.add(risultatoDadi);
		pannelloDadi.setBackground(new Color(30, 30, 30));
		return pannelloDadi;
	}
	
	public void settaNumeroDadiGrafico() {
		int dado1 = board.getDadi().getDado1();
		int dado2 = board.getDadi().getDado2();
		this.risultatoDadi.setText(dado1 + " - " + dado2);
	}
	
	
	public static boolean chiediSeVuoleComprare(Casella casella)  {
		int answer = JOptionPane.showConfirmDialog(new JFrame(), "Vuoi comprare? Prezzo: " + casella.getPrezzoVendita());
		
		if (answer == JOptionPane.YES_OPTION) {
		      return true;
		}
		
		return false;
	}
	
	public static boolean chiediSeVuolePuntare(Giocatore g, int posta)  {
		int answer = JOptionPane.showConfirmDialog(new JFrame(), "Turno di " + g.getNome() + "\nVuoi puntare 10? Posta attuale: " + posta);
		
		if (answer == JOptionPane.YES_OPTION) {
		      return true;
		}
		
		return false;
	}
	
	JScrollPane scrollbar;
	public void creaPannelloCronologia() {
		this.pannelloCronologia = new JPanel();
		this.pannelloCronologia.setLayout(new BoxLayout(pannelloCronologia, BoxLayout.PAGE_AXIS));
		scrollbar = new JScrollPane(pannelloCronologia);
		
		JLabel titolo = new JLabel("Cronologia");
		titolo.setForeground(Color.WHITE);
		pannelloCronologia.add(titolo);
		pannelloCronologia.setBackground(new Color(30, 30, 30));
	}
	
	public void aggiornaCronologia() {
		File file = new File("./cronologia.txt"); 
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String st = "Monopoly";
		String ultimaRiga = "";
		while (st != null) {
			try {
				st = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(st != null)
				ultimaRiga = st;
		}
		JLabel stringaDaAggiungere = new JLabel(ultimaRiga);
		stringaDaAggiungere.setForeground(Color.WHITE);
		pannelloCronologia.add(stringaDaAggiungere);
	} 
	
	
	
	
	public void creaPannelloPedine() {
		pannelloPedine = new JPanel();
		pannelloPedine.setBackground(new Color(30, 30, 30));
		JLabel immagineTavolaLabel = new JLabel(immagineTavola);
		JLabel pedinaLabel = new JLabel(pedina.getImmagine());
		pedinaLabel.setLocation(20, 20);
		//immagineTavolaLabel.setLayout(new BorderLayout());
		immagineTavolaLabel.setLayout(null);
		pedinaLabel.setBounds(pedina.getPosizioneX(), pedina.getPosizioneY(), pedina.getHeight(), pedina.getWidth());
		immagineTavolaLabel.add(pedinaLabel);
		pannelloPedine.add(immagineTavolaLabel);
	}
	
	public void updateTabellone() {
		pannelloPedine.removeAll();
		pannelloPedine.setBackground(new Color(30, 30, 30));
		JLabel immagineTavolaLabel = new JLabel(immagineTavola);
		JLabel pedinaLabel = new JLabel(pedina.getImmagine());
		pedinaLabel.setLocation(20, 20);
		immagineTavolaLabel.setLayout(null);
		
		pedina.setPosizioneX(pedina.getPosizioneX() + 10);
		pedina.setPosizioneY(pedina.getPosizioneY() + 10);
		
		pedinaLabel.setBounds(pedina.getPosizioneX(), pedina.getPosizioneY(), pedina.getHeight(), pedina.getWidth());
		immagineTavolaLabel.add(pedinaLabel);
		pannelloPedine.add(immagineTavolaLabel);
		
	}
}
