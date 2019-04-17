package GUI;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Elementi.Casella;
import Elementi.Giocatore;
import Gioco.Board;


public class TavolaDaGioco extends JFrame{
	
	private static final long serialVersionUID = 1L;
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
}
