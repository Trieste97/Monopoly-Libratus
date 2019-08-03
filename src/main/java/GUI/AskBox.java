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
				
				board.scambia(casellaDaPrendere, board.getGiocatoreCorrente(), gioc, casellaDaScambiare);
			}
			//aggiungere i tasti per accettare o rifiutare
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
				System.out.println(nome);
				for(Giocatore g : board.getGiocatori())  {
					if(nome.equals(g.getNome()))  {
						gioc = g;
						break;
					}
				}
				
				
				String territorioString = (String) box2.getSelectedItem();
				System.out.println(territorioString );
				
				for (Casella casella : gioc.getCasellePossedute()) {
					if(casella.getNome().equals(territorioString)) {
						casellaDaPrendere = casella;
						break;
					}
				}
				
				
				int spesaInt = Integer.parseInt(spesa.getText());
				System.out.println(spesaInt); 
				board.compraCasellaAvversaria(casellaDaPrendere, board.getGiocatoreCorrente(), gioc);
			}
			//aggiungere i tasti per accettare o rifiutare
		});
		
		
		this.setVisible(true);
		box.setVisible(true);
		box2.setVisible(true);
		spesa.setVisible(true);
		confermButton.setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
	public void chiediConChiScambiare()  {
		box2.setVisible(false);
		box3.setVisible(false);
		box4.setVisible(false);

		box.removeAllItems();
		
		for(Giocatore g : board.getGiocatori())  {
			if(g == board.getGiocatoreCorrente())  {
				continue;
			}
			
			box.addItem(g.getNome());
		}

		
		nome = (String) box.getSelectedItem();
		//Giocatore gioc = null;
		
		for(Giocatore g : board.getGiocatori())  {
			if(nome.equals(g.getNome()))  {
				gioc = g;
				break;
			}
		}
		
		
		box2.removeAllItems();

		for (Casella nomeCasella : gioc.getCasellePossedute()) {
			box2.addItem(nomeCasella.getNome());
		}
		
		box2.setVisible(true);
		//Casella casellaDaPrendere;
		territorioString = (String) box2.getSelectedItem();
		for (Casella casella : gioc.getCasellePossedute()) {
			if(casella.getNome().equals(territorioString)) {
				casellaDaPrendere = casella;
			}
		}
		
		box3.removeAllItems();

		box3.addItem("Scambia con denaro");
		box3.addItem("Scambia con terrritorio");
		box3.setVisible(true);
		
				
		confermButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				
				metodo = (String) box3.getSelectedItem();
				if(metodo.equals("Scambia con denaro")) {
					board.compraCasellaAvversaria(casellaDaPrendere, board.getGiocatoreCorrente(), gioc);
				}
				else {
					//segli la tua casella da scambiare e scambia
					
					box4.removeAllItems();
					for (Casella casella : board.getGiocatoreCorrente().getCasellePossedute()) {
						box4.addItem(casella.getNome());
					}
					box4.setVisible(true);
					String nomeCasellaDaScambiare = (String) box3.getSelectedItem();
					Casella casellaDaScambiare;
					for (Casella casella : board.getGiocatoreCorrente().getCasellePossedute()) {
						if(casella.getNome().equals(nomeCasellaDaScambiare)) {
							casellaDaScambiare = casella;
						}
					}
					//board.scambia();
				}
			}
			//aggiungere i tasti per accettare o rifiutare
		});
		
		this.setVisible(true);
		box.setVisible(true);
		confermButton.setVisible(true);
	}*/
}
