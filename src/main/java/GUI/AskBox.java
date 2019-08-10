package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Elementi.Board;
import Elementi.Casella;
import Elementi.Giocatore;

public class AskBox extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	Board board;
	JComboBox<String> box;
	JComboBox<String> box2;
	JComboBox<String> box3;
	JComboBox<String> box4;
	TextField spesa;
	
	Button confermButton;
	
	public AskBox(final Board board)  {
		super("AAA");
		this.setResizable(false);
		this.setPreferredSize(new Dimension(400, 400));
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100,100,100));
		
		box = new JComboBox<String>();
		box2 = new JComboBox<String>();
		box3 = new JComboBox<String>();
		box4 = new JComboBox<String>();
		confermButton = new Button("Conferma");
		spesa = new TextField();
		
		this.setContentPane(panel);
		this.add(box);
		this.add(box2);
		this.add(box3);
		this.add(box4);
		this.add(confermButton);
		this.add(spesa);
	    this.pack();
		this.board = board;
		this.setVisible(false);
	}
	
	public void chiediInfoCostruzione()  {
		box2.setVisible(false);
		box3.setVisible(false);
		box4.setVisible(false);
		spesa.setVisible(false);
		
		box.removeAllItems();
		ArrayList<String> caselle = board.getGiocatoreCorrente().getCaselleResidenziali();
		
		for(int i = 0; i < caselle.size(); i++)  {
			box.addItem(caselle.get(i));
		}
		
		confermButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				board.costruisci((String) box.getSelectedItem());
			}
		});
		
		this.setVisible(true);
		box.setVisible(true);
		confermButton.setVisible(true);
	}
	
	public void chiediInfoIpoteca()  {
		box2.setVisible(false);
		box3.setVisible(false);
		box4.setVisible(false);
		spesa.setVisible(false);

		
		box.removeAllItems();
		ArrayList<String> caselle = board.getGiocatoreCorrente().getCaselleNonIpotecate();
		
		for(int i = 0; i < caselle.size(); i++)  {
			box.addItem(caselle.get(i));
		}
		
		confermButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				board.ipoteca((String) box.getSelectedItem());
			}
		});
		
		this.setVisible(true);
		box.setVisible(true);
		confermButton.setVisible(true);
	}
	
	public void chiediComeUscirePrigione()  {
		box2.setVisible(false);
		box3.setVisible(false);
		box4.setVisible(false);
		spesa.setVisible(false);
	
		box.removeAllItems();
		box.addItem("Utilizza token");
		box.addItem("Paga multa");
		
		confermButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				board.esciDiPrigione((String) box.getSelectedItem());
			}
		});
		
		this.setVisible(true);
		box.setVisible(true);
		confermButton.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	String territorioString;
	String nome;
	String metodo;
	Giocatore gioc;
	Casella casellaDaPrendere;
	public void chiediScambio()  {
		
		box3.setVisible(false);
		box.removeAllItems();
		box2.removeAllItems();
		box4.removeAllItems();
		spesa.setVisible(false);
		
		
		int indiceGiocatoreAvversario = 0;
		boolean primaVolta = true;
		for(Giocatore g : board.getGiocatori())  {
			if(g == board.getGiocatoreCorrente())  {
				if(primaVolta) {
					indiceGiocatoreAvversario = 1;
				}
				primaVolta = false;
				continue;
			}
			primaVolta = false;
			box.addItem(g.getNome());
		}

		// NON SI POPOLA DINAMICAMENTE CAMBIANDO IL GIOCATORE
		/*for (Casella nomeCasella : gioc.getCasellePossedute()) {
			box2.addItem(nomeCasella.getNome());
		}*/
		
		for (Casella nomeCasella : board.getGiocatori().get(indiceGiocatoreAvversario).getCasellePossedute()) {
			box2.addItem(nomeCasella.getNome());
		}
	
		
		for (Casella casella : board.getGiocatoreCorrente().getCasellePossedute()) {
			box4.addItem(casella.getNome());
		}

		
		confermButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				
				String nome = (String) box.getSelectedItem();
				for(Giocatore g : board.getGiocatori())  {
					if(nome.equals(g.getNome()))  {
						gioc = g;
						break;
					}
				}
				
				
				String territorioString = (String) box2.getSelectedItem();
				for (Casella casella : gioc.getCasellePossedute()) {
					if(casella.getNome().equals(territorioString)) {
						casellaDaPrendere = casella;
						break;
					}
				}
				
				
				String nomeCasellaDaScambiare = (String) box4.getSelectedItem();
				Casella casellaDaScambiare = null;
				for (Casella casella : board.getGiocatoreCorrente().getCasellePossedute()) {
					if(casella.getNome().equals(nomeCasellaDaScambiare)) {
						casellaDaScambiare = casella;
						break;
					}
				}
				
					
				boolean primoclick = true;
				if (primoclick) {
					boolean avversarioAccettaScambio = chiediSeAccettaScambio(casellaDaPrendere.getNome(), casellaDaScambiare.getNome(), 
							casellaDaPrendere.getProprietario().getNome());
					if (avversarioAccettaScambio) {
						board.scambia(casellaDaPrendere, board.getGiocatoreCorrente(), gioc, casellaDaScambiare);
						aggiornaDropDownScambio();
					}
					primoclick = false;
				}
			}
		});
		
		
		this.setVisible(true);
		box.setVisible(true);
		box2.setVisible(true);
		box4.setVisible(true);
		confermButton.setVisible(true);
	}

	
	public void chiediVendita()  {
			
			box4.setVisible(false);
			box3.setVisible(false);
			box.removeAllItems();
			box2.removeAllItems();
			spesa.setText("Prezzo");
			
			int indiceGiocatoreAvversario = 0;
			boolean primaVolta = true;
			for(Giocatore g : board.getGiocatori())  {
				if(g == board.getGiocatoreCorrente())  {
					if(primaVolta) {
						indiceGiocatoreAvversario = 1;
					}
					primaVolta = false;
					continue;
				}
				primaVolta = false;
				box.addItem(g.getNome());
			}
	
			// NON SI POPOLA DINAMICAMENTE CAMBIANDO IL GIOCATORE
			/*for (Casella nomeCasella : gioc.getCasellePossedute()) {
				box2.addItem(nomeCasella.getNome());
			}*/
			
			for (Casella nomeCasella : board.getGiocatori().get(indiceGiocatoreAvversario).getCasellePossedute()) {
				box2.addItem(nomeCasella.getNome());
			}
		
			
					
			confermButton.addActionListener(new ActionListener()  {
				public void actionPerformed(ActionEvent e)  {
					
					String nome = (String) box.getSelectedItem();
					for(Giocatore g : board.getGiocatori())  {
						if(nome.equals(g.getNome()))  {
							gioc = g;
							break;
						}
					}
					
					
					String territorioString = (String) box2.getSelectedItem();
					
					for (Casella casella : gioc.getCasellePossedute()) {
						if(casella.getNome().equals(territorioString)) {
							casellaDaPrendere = casella;
							break;
						}
					}
					
					
					int spesaInt = Integer.parseInt(spesa.getText());
					boolean primoclick = true;
					if (primoclick) {
						boolean avversarioAccettaOfferta = chiediSeAccettaOfferta(casellaDaPrendere.getNome(), spesaInt, 
								casellaDaPrendere.getProprietario().getNome());
						if (avversarioAccettaOfferta) {
							board.compraCasellaAvversaria(casellaDaPrendere, board.getGiocatoreCorrente(), gioc, spesaInt);
						}
						primoclick = false;
					}
				}
			});
			
			
			this.setVisible(true);
			box.setVisible(true);
			box2.setVisible(true);
			spesa.setVisible(true);
			confermButton.setVisible(true);
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
	
	private void aggiornaDropDownScambio() {
		// TODO Auto-generated method stub
		box3.setVisible(false);
		box.removeAllItems();
		box2.removeAllItems();
		box4.removeAllItems();
		spesa.setVisible(false);
		
		
		int indiceGiocatoreAvversario = 0;
		boolean primaVolta = true;
		for(Giocatore g : board.getGiocatori())  {
			if(g == board.getGiocatoreCorrente())  {
				if(primaVolta) {
					indiceGiocatoreAvversario = 1;
				}
				primaVolta = false;
				continue;
			}
			primaVolta = false;
			box.addItem(g.getNome());
		}
		
		for (Casella nomeCasella : board.getGiocatori().get(indiceGiocatoreAvversario).getCasellePossedute()) {
			box2.addItem(nomeCasella.getNome());
		}
	
		
		for (Casella casella : board.getGiocatoreCorrente().getCasellePossedute()) {
			box4.addItem(casella.getNome());
		}

		

	}
	

}
