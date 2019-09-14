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
	static BoardPanel pannelloTavola;
	AskBox askBox;
	private static Board board;

	public TavolaDaGioco(String title, Board board, int numPlayers, Giocatore player) {
		super(title);
		
		this.setResizable(false);
		this.setPreferredSize(new Dimension(1000, 750));
	    this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.askBox = new AskBox(board);
		TavolaDaGioco.board = board;
		init(numPlayers, player, board);
	}
	
	public void init(int numPlayers, Giocatore player, Board board) {
		this.setPreferredSize(new Dimension(800,600));
		panel = new JPanel();
		panel.setBackground(new Color(30, 30, 30));
		panel.setLayout(new BorderLayout());
		creaPannelloPedine(numPlayers, board);
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
				if (!board.isAITurn())  {
					//askBox.chiediConChiScambiare();
					askBox.chiediScambio();
				}
			}
		});
		Button proponiVendita = new Button("Proponi vendita");
		proponiVendita.setPreferredSize(new Dimension(100, 40));
		proponiVendita.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				if (!board.isAITurn())  {
					//askBox.chiediConChiScambiare();
					askBox.chiediVendita();
				}
			}
		});
		
		Button costruisci = new Button("Costruisci");
		costruisci.setPreferredSize(new Dimension(100, 40));
		costruisci.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				if (!board.isAITurn())  {
					askBox.chiediInfoCostruzione();
				}
			}
		});
		
		Button ipoteca = new Button("Ipoteca");
		ipoteca.setPreferredSize(new Dimension(100, 40));
		ipoteca.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				if (!board.isAITurn())  {
					askBox.chiediInfoIpoteca();
				}
			}
		});
		
		Button esciPrigione = new Button("Esci di prigione");
		esciPrigione.setPreferredSize(new Dimension(100, 40));
		esciPrigione.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				if (!board.isAITurn())  {
					if(board.getGiocatoreCorrente().isInPrigione()) {
						askBox.chiediComeUscirePrigione();
					}
				}
			}
		});
		
				
		box.add(Box.createVerticalStrut(50));
		box.add(azioni);
		box.add(Box.createVerticalStrut(10));
		box.add(proponiScambio);
		box.add(Box.createVerticalStrut(10));
		box.add(proponiVendita);
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
	
	static JLabel risultatoDadi = new JLabel("");
	static JLabel soldiDisponibili = new JLabel("");
	
	boolean turnofinito = true;
	public JPanel creaPannelloDadi() {
		
		Box box = Box.createVerticalBox();
		pannelloDadi = new JPanel();
		
		Button tiraDadi = new Button("Tira i dadi");
		tiraDadi.setPreferredSize(new Dimension(100,  40));
		tiraDadi.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				if (!board.isAITurn())  {
					board.rollaDadi();
				}
			}
		});
		
		/*
		Button terminaTurno = new Button("Termina Turno");
		terminaTurno.setPreferredSize(new Dimension(100,  40));
		terminaTurno.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				if (!board.isAITurn())  {
					if (!turnofinito) {
						updateTabellone(0);
						board.finisciTurno();
						turnofinito = true;
						board.iniziaTurnoGiocatoreSuccessivo();
					}
				}
			}
		});
		*/
		
		risultatoDadi.setForeground(Color.WHITE);
		soldiDisponibili.setForeground(Color.WHITE);
		
		box.add(Box.createVerticalStrut(10));
		box.add(tiraDadi);
		//box.add(Box.createVerticalStrut(10));
		//box.add(terminaTurno);
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
	
	public static void settaNumeroDadiGrafico() {
		int dado1 = board.getDadi().getDado1();
		int dado2 = board.getDadi().getDado2();
		risultatoDadi.setText("Dadi: " + dado1 + " - " + dado2);
	}
	public static void settaSoldiDisponibili() {
		soldiDisponibili.setText("Soldi: " + Integer.toString(board.getGiocatoreVero().getSoldi()));
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
	
	public void creaPannelloPedine(int numplayers, Board board) {
		pannelloTavola = new BoardPanel(numplayers, board);
		pannelloTavola.setBackground(new Color(30, 30, 30));
	}
	
	public static void updateTabellone(Giocatore player) {
		pannelloTavola.repaint(board.getGiocatoreCorrenteIndex(), player);
	}

	
	public static boolean chiediSeVuoleScambiare(String casellaDaPrendere, String casellaDaScambiare, String proprietarioCasellaDaPrendere) {
		// TODO Auto-generated method stub
		return AskBox.chiediSeAccettaScambio(casellaDaPrendere, casellaDaScambiare, proprietarioCasellaDaPrendere);

	}
	
	public static boolean chiediSeVuoleVendere(String casellaDaPrendere, int prezzo, String proprietarioCasellaDaPrendere) {
		// TODO Auto-generated method stub
		return AskBox.chiediSeAccettaOfferta(casellaDaPrendere, prezzo, proprietarioCasellaDaPrendere);

	}
	
	public static void update(Giocatore player)  {
		updateTabellone(player);
		settaNumeroDadiGrafico();
		settaSoldiDisponibili();
	}
}
