package GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Elementi.Giocatore;
import Gioco.Board;

public class AskBox extends JFrame  {
	
	private static final long serialVersionUID = 1L;
	Board board;
	JComboBox<String> box;
	Button confermButton;
	
	public AskBox(final Board board)  {
		super("AAA");
		this.setResizable(false);
		this.setPreferredSize(new Dimension(200, 100));
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100,100,100));
		
		box = new JComboBox<String>();
		confermButton = new Button("Conferma");
		
		this.setContentPane(panel);
		this.add(box);
		this.add(confermButton);
	    this.pack();
		this.board = board;
		this.setVisible(false);
	}
	
	public void chiediInfoCostruzione()  {
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
	
	public void chiediConChiScambiare()  {
		box.removeAllItems();
		
		for(Giocatore g : board.getGiocatori())  {
			if(g == board.getGiocatoreCorrente())  {
				continue;
			}
			
			box.addItem(g.getNome());
		}
		
		confermButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e)  {
				String nome = (String) box.getSelectedItem();
				Giocatore gioc = null;
				
				for(Giocatore g : board.getGiocatori())  {
					if(nome.equals(g.getNome()))  {
						gioc = g;
						break;
					}
				}
				
				board.proponiScambio(gioc);
			}
		});
		
		this.setVisible(true);
		box.setVisible(true);
		confermButton.setVisible(true);
	}
}
