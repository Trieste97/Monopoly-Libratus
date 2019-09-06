package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Elementi.Board;
import Elementi.Casella;
import Elementi.CasellaResidenziale;
import Elementi.Giocatore;
import Gioco.CreatoreCaselle;

public class AskBox extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	Board board;
	TextField spesa;
	
	JPanel panel = new JPanel();
	
	String territorioString;
	String nome;
	String metodo;
	Giocatore gioc;
	Casella casellaDaPrendere;
	
	HashSet<String> nomiCaselleEsistenti;
	
	public AskBox(final Board board)  {
		super("Monopoly");
		this.setResizable(false);
		this.setPreferredSize(new Dimension(400, 400));
		this.setLayout(new BorderLayout());
		
		nomiCaselleEsistenti = new HashSet<String>();
		CreatoreCaselle.caricaNomiCaselle(nomiCaselleEsistenti);
		
		panel.setBackground(new Color(100,100,100));
		spesa = new TextField();
		
		this.setContentPane(panel);
	    this.pack();
		this.board = board;
		this.setVisible(false);
	}	
	public void chiediInfoCostruzione()  {
		if(board.getGiocatoreVero().getNumSetsPosseduti() > 0)  {
			for(String coloreEPrezzo : board.getGiocatoreVero().getSetsEPrezzi())  {
				String colore = coloreEPrezzo.split(",")[0];
				String prezzo = coloreEPrezzo.split(",")[1];
				final ArrayList<String> temp = new ArrayList<String>();
				temp.add(colore);
				
				Button btn = new Button("Compra case su set " + traduciColore(colore) + " al prezzo di " + prezzo + "€");
				btn.addActionListener(new ActionListener()  {
					public void actionPerformed(ActionEvent e)  {
						board.costruisci(temp);
					}
				});
				this.add(btn);
			}
			this.setVisible(true);
		}
		else  {
			JLabel label = new JLabel("Non hai set di colori completi per costruire");
			this.add(label);
			this.setVisible(true);
		}
	}
	public String traduciColore(String col)  {
		if (col.equals("brown")) return "A";
		else if (col.equals("lightblue")) return "B";
		else if (col.equals("pink")) return "C";
		else if (col.equals("orange")) return "D";
		else if (col.equals("red")) return "E";
		else if (col.equals("yellow")) return "F";
		else if (col.equals("green")) return "G";
		else return "H";
	}
	public int calcolaPrezzoVenditaColore(String col)  {
		if (col.equals("brown")) return 500;
		else if (col.equals("lightblue")) return 750;
		else if (col.equals("pink")) return 1500;
		else if (col.equals("orange")) return 1500;
		else if (col.equals("red")) return 2250;
		else if (col.equals("yellow")) return 2250;
		else if (col.equals("green")) return 3000;
		else return 2000;
	}
	public void chiediInfoIpoteca()  {
		panel.removeAll();
		
		final JComboBox<String> box = new JComboBox<String>();
		
		Button confermButtonIpoteca;
		confermButtonIpoteca = new Button("Conferma");
		this.add(box);
		this.add(confermButtonIpoteca);
		
		ArrayList<Casella> caselle = board.getGiocatoreCorrente().getCaselleNonIpotecate();
		Set<String> sets = new HashSet<String>();
		for(Casella c : caselle)  {
			if (c instanceof CasellaResidenziale)  {
				CasellaResidenziale cas = (CasellaResidenziale) c;
				if (cas.getNumeroCaseCostruite() > 0)  {
					sets.add(cas.getColore());
					continue;
				}
			}
			box.addItem(c.getNome());
		}
		
		for(final String colore : sets)  {
			Button btn = new Button("Vendi 1 casa (per casella) del set " + 
				traduciColore(colore) + " al prezzo di " + calcolaPrezzoVenditaColore(colore) + "€");
			
			btn.addActionListener(new ActionListener()  {
				public void actionPerformed(ActionEvent e)  {
					board.vendiCase(colore);
					TavolaDaGioco.update(board.getGiocatoreVero());
				}
			});
			this.add(btn);
		}
		
		confermButtonIpoteca.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				board.ipoteca((String) box.getSelectedItem());
				TavolaDaGioco.update(board.getGiocatoreVero());
			}
		});
		
		this.setVisible(true);
	}
	public void chiediComeUscirePrigione()  {
		panel.removeAll();
		
		Button token = new Button("Utilizza token");
		Button paga = new Button("Paga la multa (500€)");
		
		token.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				board.esciDiPrigione("token");
			}
		});
		
		paga.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				board.esciDiPrigione("paga");
			}
		});
		
		this.add(token);
		this.add(paga);
		this.setVisible(true);
	}	
	public void chiediScambio()  {
		panel.removeAll();
		
		Button confermButtonProponiScambio;
		confermButtonProponiScambio = new Button("Conferma");
		String text = "<html><pre>Caselle tue:<br/>";
		
		int count = 0;
		for(Casella c : board.getGiocatoreVero().getCasellePossedute())  {
			if (++count > 4)  {
				text += "<br/>";
				count = 0;
			}
			text += c.getNome() + ",";
		}
		text = text.substring(0, text.length()-1);
		text += "<br/>";
		
		text += "Soldi avversario: </pre><b>" + board.getGiocatoreBot().getSoldi() + "</b><pre><br/>";
		text += "Caselle avversario: <br/>";
		count = 0;
		for(Casella c : board.getGiocatoreBot().getCasellePossedute())  {
			if (++count > 4)  {
				text += "<br/>";
				count = 0;
			}
			text += c.getNome() + ",";
		}
		text = text.substring(0, text.length()-1);
		text += "<br/>";
		
		JLabel label = new JLabel(text + "</pre></html>");
		
		Box box = Box.createVerticalBox();
		JLabel soldiDare = new JLabel("Soldi da dare:");
		final JTextField soldiDaDare = new JTextField("0");
		JLabel caselleDare = new JLabel("Caselle da dare: (separare con virgola)");
		final JTextField caselleDaDare = new JTextField(20);
		
		JLabel soldiRicevere = new JLabel("Soldi da ricevere");
		final JTextField soldiDaRicevere = new JTextField("0");
		JLabel caselleRicevere = new JLabel("Caselle da ricevere: (separare con virgola)");
		final JTextField caselleDaRicevere = new JTextField(20);
		
		box.add(soldiDare);
		box.add(soldiDaDare);
		box.add(caselleDare);
		box.add(caselleDaDare);
		box.add(soldiRicevere);
		box.add(soldiDaRicevere);
		box.add(caselleRicevere);
		box.add(caselleDaRicevere);
		this.add(box);
		this.add(label);
		this.add(confermButtonProponiScambio);
		this.setVisible(true);
		confermButtonProponiScambio.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				
				try  { 
					//controllo validità scambio
					int soldiToBot = Integer.parseInt(soldiDaDare.getText());
					int soldiToYou = Integer.parseInt(soldiDaRicevere.getText());
					
					if(soldiToBot < 0 || soldiToYou < 0)  {
						throw new Exception("Scambio non valido");
					}
					String caselleToBot_ = caselleDaDare.getText();
					String caselleToYou_ = caselleDaRicevere.getText();
					
					String[] caselleToBot = caselleToBot_.split(",");
					String[] caselleToYou = caselleToYou_.split(",");
					
					for (String c : caselleToBot)  {
						if (!nomiCaselleEsistenti.contains(c))  {
							throw new Exception("Scambio non valido");
						}
					}
					
					for (String c : caselleToYou)  {
						if (!nomiCaselleEsistenti.contains(c))  {
							throw new Exception("Scambio non valido");
						}
					}
					
					boolean accettato = board.getGiocatoreBot().chiediSeAccettaScambio(board, soldiToBot, soldiToYou, caselleToBot, caselleToYou);
					if(accettato)  {
						board.scambia(soldiToBot, soldiToYou, caselleToBot, caselleToYou);
					}
				} catch(Exception ex) {
					System.err.println("Scambio non valido");
				}
			}
		});
	}	
	public static boolean chiediSeAccettaScambio(String casellaDaPrendere, String casellaDaScambiare, String proprietarioCasellaDaPrendere)  {
		int answer = JOptionPane.showConfirmDialog(new JFrame(), proprietarioCasellaDaPrendere+
				", vuoi scambiare " + casellaDaPrendere + " con " + casellaDaScambiare + " ?");
		if (answer == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}	
	public static boolean chiediSeAccettaOfferta(String casellaDaPrendere, int prezzo, String proprietarioCasellaDaPrendere)  {
		int answer = JOptionPane.showConfirmDialog(new JFrame(), proprietarioCasellaDaPrendere+
				", vuoi vendere " + casellaDaPrendere + " per " + prezzo + " ?");
		if (answer == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
	public static void avversarioAccetta()  {
//		JOptionPane.showConfirmDialog(new JFrame(), "L'avversario accetta!");
		JOptionPane.showMessageDialog(new JFrame(), "L'avversario accetta!");
	}
	public static void avversarioRifiuta()  {
//		JOptionPane.showConfirmDialog(new JFrame(), "L'avversario rifiuta!");
		JOptionPane.showMessageDialog(new JFrame(), "L'avversario rifiuta!");
	}	
}
