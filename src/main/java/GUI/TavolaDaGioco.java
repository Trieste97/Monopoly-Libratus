package GUI;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Elementi.Board;
import Elementi.Casella;
import Elementi.Giocatore;


public class TavolaDaGioco extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JPanel pannelloComandi;
	JPanel pannelloDadi;
	static JPanel pannelloCronologia;
	BoardPanel pannelloTavola;
	AskBox askBox;
	private Board board;

	public TavolaDaGioco(String title, Board board, int numPlayers, Giocatore player) {
		super(title);
		
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1000, 750));
	    this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.askBox = new AskBox(board);
		this.board = board;
		init(numPlayers, player);
	}
	
	public void init(int numPlayers, Giocatore player) {
		this.setPreferredSize(new Dimension(800,600));
		panel = new JPanel();
		panel.setBackground(new Color(30, 30, 30));
		panel.setLayout(new BorderLayout());
		creaPannelloPedine(numPlayers, player);
//		panel.add(new JLabel(pedina.getImmagine()), BorderLayout.CENTER);
//		panel.add(new JLabel(immagineTavola), BorderLayout.CENTER);
		panel.add(pannelloTavola, BorderLayout.CENTER);
		creaPannelloComandi();
		panel.add(pannelloComandi, BorderLayout.EAST);
//		creaPannelloDadi();
		//panel.add(pannelloDadi, BorderLayout.SOUTH);
		creaPannelloCronologia();
		//panel.add(pannelloCronologia, BorderLayout.WEST);
		panel.add(scrollbar, BorderLayout.WEST);
		
		this.setContentPane(panel);
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
				//askBox.chiediConChiScambiare();
				askBox.chiediScambio();
			}
		});
		
		Button proponiVendita = new Button("Proponi vendita");
		proponiVendita.setPreferredSize(new Dimension(100, 40));
		proponiVendita.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				//askBox.chiediConChiScambiare();
				askBox.chiediVendita();
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
		box.add(proponiVendita);
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
	JLabel soldiDisponibili = new JLabel("");
	public JPanel creaPannelloDadi() {
		
		Box box = Box.createVerticalBox();
		pannelloDadi = new JPanel();
		
		Button tiraDadi = new Button("Tira i dadi");
		tiraDadi.setPreferredSize(new Dimension(100,  40));
		tiraDadi.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				int numPosizioni = board.rollaDadi();
				updateTabellone(numPosizioni);
				settaNumeroDadiGrafico();
				settaSoldiDisponibili();
				board.tiraDadi();
			}
		});
		
		risultatoDadi.setForeground(Color.WHITE);
		soldiDisponibili.setForeground(Color.WHITE);
		
		box.add(Box.createVerticalStrut(10));
		box.add(tiraDadi);
		box.add(Box.createVerticalStrut(10));
		box.add(risultatoDadi);
		box.add(Box.createVerticalStrut(10));
		box.add(soldiDisponibili);
		
		
		pannelloDadi.add(box);
		pannelloDadi.setBackground(new Color(30, 30, 30));
		/*
		pannelloDadi.add(tiraDadi);
		
		risultatoDadi.setForeground(Color.WHITE);
		pannelloDadi.add(risultatoDadi);
		soldiDisponibili.setForeground(Color.WHITE);
		pannelloDadi.add(soldiDisponibili);
		pannelloDadi.setBackground(new Color(30, 30, 30));*/
		return pannelloDadi;
	}
	
	public void settaNumeroDadiGrafico() {
		int dado1 = board.getDadi().getDado1();
		int dado2 = board.getDadi().getDado2();
		this.risultatoDadi.setText("Dadi: " + dado1 + " - " + dado2);
	}
	public void settaSoldiDisponibili() {
		this.soldiDisponibili.setText("Soldi: " + Integer.toString(board.getGiocatoreCorrente().getSoldi()));
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
		pannelloCronologia = new JPanel();
		pannelloCronologia.setLayout(new BoxLayout(pannelloCronologia, BoxLayout.PAGE_AXIS));
		scrollbar = new JScrollPane(pannelloCronologia);
		/*scrollbar.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	            e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
	        }
	    });*/
		
		JLabel titolo = new JLabel("Cronologia");
		titolo.setForeground(Color.WHITE);
		pannelloCronologia.add(titolo);
		pannelloCronologia.setBackground(new Color(30, 30, 30));
	}
	
	public static void aggiungiACronologia(String st) {
		JLabel stringaDaAggiungere = new JLabel(st);
		stringaDaAggiungere.setForeground(Color.WHITE);
		pannelloCronologia.add(stringaDaAggiungere);
		pannelloCronologia.revalidate();
		pannelloCronologia.repaint();
	} 
	
	public void creaPannelloPedine(int numplayers, Giocatore player) {
		pannelloTavola = new BoardPanel(numplayers, player);
		pannelloTavola.setBackground(new Color(30, 30, 30));
	}
	
	public void updateTabellone(int numPosizioni) {
		pannelloTavola.repaint(this.board.getGiocatoreCorrenteIndex(), numPosizioni);
		
	}
	
	
}
