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

public class AskBox extends JFrame {

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
	AskBox temp;

	public AskBox(final Board board) {
		super("Monopoly");
		this.setResizable(false);
		this.setPreferredSize(new Dimension(400, 400));
		this.setLayout(new BorderLayout());

		nomiCaselleEsistenti = new HashSet<String>();
		CreatoreCaselle.caricaNomiCaselle(nomiCaselleEsistenti);

		panel.setBackground(new Color(100, 100, 100));
		spesa = new TextField();

		this.setContentPane(panel);
		this.pack();
		this.board = board;
		this.setVisible(false);
	}

	
	String coloreString;
	/*public void chiediInfoCostruzione() {
		panel.removeAll();
		if (board.getGiocatoreVero().getNumSetsPosseduti() > 0) {
			System.out.println("VOGLIO LA CASAAAAA");
			for (String coloreEPrezzo : board.getGiocatoreVero().getSetsEPrezzi()) {
				String colore = coloreEPrezzo.split(",")[0];
				String prezzo = coloreEPrezzo.split(",")[1];
				final ArrayList<String> tempString = new ArrayList<String>();
				tempString.add(colore);
				temp = this;
				Button btn = new Button(
						"Compra case su set " + traduciColore(colore) + " al prezzo di " + prezzo + "€");
				coloreString = traduciColore(colore);
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						board.costruisci(tempString);
						temp.setVisible(false);
						messaggio("Hai costruito su " + coloreString);
						TavolaDaGioco.update(board.getGiocatoreCorrente());
					}
				});
				this.add(btn);
			}
			this.setVisible(true);
		} else {
			JLabel label = new JLabel("Non hai set di colori completi per costruire");
			label.setForeground(Color.WHITE);
			this.add(label);
			this.setVisible(true);
		}
	}*/
	
	
	public void chiediInfoCostruzione() {
		panel.removeAll();
		JLabel label = new JLabel("Puoi costruire sui seguenti set");
		label.setForeground(Color.WHITE);
		this.add(label);

		for (String coloreEPrezzo : board.getGiocatoreVero().getSetsEPrezzi()) {
			String colore = coloreEPrezzo.split(",")[0];
			String prezzo = coloreEPrezzo.split(",")[1];
			final ArrayList<String> tempString = new ArrayList<String>();
			tempString.add(colore);
			temp = this;
			Button btn = new Button(
					"Compra case su set " + traduciColore(colore) + " al prezzo di " + prezzo + "€");
			coloreString = traduciColore(colore);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean costruito = board.costruisci(tempString);
					temp.setVisible(false);
					if (costruito) {
						messaggio("Hai costruito su " + coloreString);
					}
					TavolaDaGioco.update(board.getGiocatoreCorrente());
				}
			});
			this.add(btn);
		}
			this.setVisible(true);
	}

	public String traduciColore(String col) {
		if (col.equals("brown"))
			return "A";
		else if (col.equals("lightblue"))
			return "B";
		else if (col.equals("pink"))
			return "C";
		else if (col.equals("orange"))
			return "D";
		else if (col.equals("red"))
			return "E";
		else if (col.equals("yellow"))
			return "F";
		else if (col.equals("green"))
			return "G";
		else
			return "H";
	}

	public int calcolaPrezzoVenditaColore(String col) {
		if (col.equals("brown"))
			return 500;
		else if (col.equals("lightblue"))
			return 750;
		else if (col.equals("pink"))
			return 1500;
		else if (col.equals("orange"))
			return 1500;
		else if (col.equals("red"))
			return 2250;
		else if (col.equals("yellow"))
			return 2250;
		else if (col.equals("green"))
			return 3000;
		else
			return 2000;
	}

	public void chiediInfoIpoteca() {
		panel.removeAll();

		final JComboBox<String> box = new JComboBox<String>();
		final JComboBox<String> boxIpotecate = new JComboBox<String>();

		Button confermButtonIpoteca;
		confermButtonIpoteca = new Button("Ipoteca");
		Button confermButtonDisipoteca;
		confermButtonDisipoteca = new Button("Togli ipoteca");
		this.add(box);
		this.add(confermButtonIpoteca);
		this.add(boxIpotecate);
		this.add(confermButtonDisipoteca);

		ArrayList<Casella> caselle = board.getGiocatoreCorrente().getCaselleNonIpotecate();
		ArrayList<Casella> caselleIpotecate = board.getGiocatoreCorrente().getCaselleIpotecate();
		
//		Set<String> sets = new HashSet<String>();
		for (Casella c : caselle) {
			/*if (c instanceof CasellaResidenziale) {
				CasellaResidenziale cas = (CasellaResidenziale) c;
				if (cas.getNumeroCaseCostruite() > 0) {
					sets.add(cas.getColore());
					continue;
				}
			}*/
			box.addItem(c.getNome());
		}
		 
		for (Casella casella : caselleIpotecate) {
			boxIpotecate.addItem(casella.getNome());
		}

		/*for (final String colore : sets) {
			Button btn = new Button("Vendi 1 casa (per casella) del set " + traduciColore(colore) + " al prezzo di "
					+ calcolaPrezzoVenditaColore(colore) + "€");
			temp = this;
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					board.vendiCase(colore);
					TavolaDaGioco.update(board.getGiocatoreVero());
				}
			});
			this.add(btn);
		}*/
		temp = this;
		confermButtonDisipoteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elementoSelezionato = (String) boxIpotecate.getSelectedItem();
				if (elementoSelezionato == null) {
					elementoSelezionato = "";
				}
				board.disipoteca(elementoSelezionato);
				temp.setVisible(false);
				if (!elementoSelezionato.equals("")) {
					messaggio("Hai tolto l'ipoteca alla casella selezionata");
				}
				else {
					messaggio("Non hai caselle a cui togliere l'ipoteca");
				}
				TavolaDaGioco.update(board.getGiocatoreVero());
			}
		});
		
		
		confermButtonIpoteca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elementoSelezionato = (String) box.getSelectedItem();
				if (elementoSelezionato == null) {
					elementoSelezionato = "";
				}
				board.ipoteca(elementoSelezionato);
				temp.setVisible(false);
				if (!elementoSelezionato.equals("")) {
					messaggio("Hai ipotecato la casella selezionata");
				}
				else {
					messaggio("Non hai caselle da ipotecare");
				}
				TavolaDaGioco.update(board.getGiocatoreVero());
			}
		});
		

		this.setVisible(true);
	}

	public void chiediComeUscirePrigione() {
		panel.removeAll();
		
		temp = this;
		if (board.getGiocatoreCorrente().isInPrigione()) {
			
			Button paga = new Button("Paga la multa (500€)");
			
			if (board.getGiocatoreCorrente().hasTokenPrigione()) {
				Button token = new Button("Utilizza token");
				token.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						board.esciDiPrigione("token");
						temp.setVisible(false);
						messaggio("Sei uscito di prigione");
						TavolaDaGioco.update(board.getGiocatoreCorrente());
					}
				});
				this.add(token);
				
			}
			
			paga.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					board.esciDiPrigione("paga");
					temp.setVisible(false);
					messaggio("Sei uscito di prigione");
					TavolaDaGioco.update(board.getGiocatoreCorrente());
				}
			});
			
			this.add(paga);
		}

		this.setVisible(true);
	}


	public static boolean chiediSeAccettaScambio(String casellaDaPrendere, String casellaDaScambiare,
			String proprietarioCasellaDaPrendere) {
		
		int answer = JOptionPane.showConfirmDialog(new JFrame(), proprietarioCasellaDaPrendere + ", vuoi scambiare "
				+ casellaDaPrendere + " con " + casellaDaScambiare + " ?");
		if (answer == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}

	public static boolean chiediSeAccettaOfferta(String casellaDaPrendere, int prezzo,
			String proprietarioCasellaDaPrendere) {
		int answer = JOptionPane.showConfirmDialog(new JFrame(),
				proprietarioCasellaDaPrendere + ", vuoi vendere " + casellaDaPrendere + " per " + prezzo + " ?");
		if (answer == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}

	public static void avversarioAccetta() {
		JOptionPane.showMessageDialog(new JFrame(), "L'avversario accetta!");
	}

	public static void avversarioRifiuta() {
		JOptionPane.showMessageDialog(new JFrame(), "L'avversario rifiuta!");
	}

	public static void offertaNonValida() {
		JOptionPane.showMessageDialog(new JFrame(), "Offerta Non Valida!");
	}
	
	public static void soldiInsufficienti() {
		JOptionPane.showMessageDialog(new JFrame(), "Soldi insufficienti!");
	}
	
	public static void numeroMassimoCostruzioniSuperato() {
		JOptionPane.showMessageDialog(new JFrame(), "Non è possibile costruire altro su questo set!");
	}
	
	public static void messaggio(String messaggio) {
		JOptionPane.showMessageDialog(new JFrame(), messaggio);
	}

	public void giocatorePerdente(String giocatorePerdente) {
		TavolaDaGioco.update(board.getGiocatoreCorrente());
		JOptionPane.showMessageDialog(new JFrame(), "Il giocatore " + giocatorePerdente + " ha perso!");
	}
	
	
	
	ArrayList<String> casellePresentiGiocatore = new ArrayList<String>();
	ArrayList<String> casellePresentiBot = new ArrayList<String>();

	
	public void chiediScambio() {
		panel.removeAll();

		String text = "<html><pre>Caselle tue:<br/>";
		Button confermButtonProponiScambio;
		confermButtonProponiScambio = new Button("Conferma");

		casellePresentiGiocatore.clear();
		casellePresentiBot.clear();

		int count = 0;
		for (String c : board.getGiocatoreVero().getCaselleResidenziali()) {
			if (++count > 4) {
				text += "<br/>";
				count = 0;
			}
			text += c + ",";
			casellePresentiGiocatore.add(c);
		}
		text = text.substring(0, text.length() - 1);
		text += "<br/>";

		text += "Caselle avversario: <br/>";
		count = 0;
		for (String c : board.getGiocatoreBot().getCaselleResidenziali()) {
			if (++count > 4) {
				text += "<br/>";
				count = 0;
			}
			text += c + ",";
			casellePresentiBot.add(c);
		}
		text = text.substring(0, text.length() - 1);
		text += "<br/>";

		JLabel label = new JLabel(text + "</pre></html>");
		label.setForeground(Color.WHITE);
		
		Box box = Box.createVerticalBox();
		JLabel caselleDare = new JLabel("Casella da dare: ");
		caselleDare.setForeground(Color.WHITE);
		final JTextField caselleDaDare = new JTextField(20);

		JLabel caselleRicevere = new JLabel("Casella da ricevere: ");
		caselleRicevere.setForeground(Color.WHITE);
		final JTextField caselleDaRicevere = new JTextField(20);

		box.add(caselleDare);
		box.add(caselleDaDare);
		box.add(caselleRicevere);
		box.add(caselleDaRicevere);
		this.add(box);
		this.add(label);
		this.add(confermButtonProponiScambio);
		this.setVisible(true);
		temp = this;
		
		confermButtonProponiScambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String caselleToBot = caselleDaDare.getText();
				String caselleToYou = caselleDaRicevere.getText();
				if(caselleToYou.equals("")) {
					caselleToYou = "Casella non selezionata";
				}
				if(caselleToBot.equals("")) {
					caselleToBot = "Casella non selezionata";
				}

				if (casellePresentiGiocatore.contains(caselleToBot) && casellePresentiBot.contains(caselleToYou)) {
					Casella casellaDaScambiare = board.getCasellaDaNome(caselleToBot);
					Casella casellaDaPrendere = board.getCasellaDaNome(caselleToYou);

					boolean accettato = board.getGiocatoreBot().chiediSeAccettaScambio2Caselle(caselleToYou,
							caselleToBot, board.getGiocatori().get(board.getGiocatoreAvversarioIndex()),
							board.getGiocatoreCorrente());
					if (accettato) {
						board.scambia(casellaDaPrendere, board.getGiocatoreCorrente(),
								board.getGiocatori().get(board.getGiocatoreAvversarioIndex()), casellaDaScambiare);
						temp.setVisible(false);
						avversarioAccetta();
						TavolaDaGioco.update(board.getGiocatoreCorrente());
					}
					else {
						temp.setVisible(false);
						avversarioRifiuta();
					}
				} else {
					offertaNonValida();
				}
			}
		});
	}
	

	
	
	public void chiediVendita() {
		panel.removeAll();

		String text = "<html><pre>Soldi a disposizione:<br/>";
		Button confermButtonProponiScambio;
		confermButtonProponiScambio = new Button("Conferma");

		casellePresentiBot.clear();

		text += board.getGiocatoreCorrente().getSoldi();
		text += "<br/>";

		text += "Caselle avversario: <br/>";
		int count = 0;
		for (String c : board.getGiocatoreBot().getCaselleResidenziali()) {
			if (++count > 4) {
				text += "<br/>";
				count = 0;
			}
			text += c + ",";
			casellePresentiBot.add(c);
		}
		text = text.substring(0, text.length() - 1);
		text += "<br/>";

		JLabel label = new JLabel(text + "</pre></html>");
		label.setForeground(Color.WHITE);

		Box box = Box.createVerticalBox();
		JLabel caselleDare = new JLabel("Soldi da dare: ");
		caselleDare.setForeground(Color.WHITE);
		final JTextField caselleDaDare = new JTextField(20);

		JLabel caselleRicevere = new JLabel("Casella da ricevere: ");
		caselleRicevere.setForeground(Color.WHITE);
		final JTextField caselleDaRicevere = new JTextField(20);

		box.add(caselleDare);
		box.add(caselleDaDare);
		box.add(caselleRicevere);
		box.add(caselleDaRicevere);
		this.add(box);
		this.add(label);
		this.add(confermButtonProponiScambio);
		this.setVisible(true);
		temp = this;
		
		confermButtonProponiScambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String soldiDaDare = caselleDaDare.getText();
				String caselleToYou = caselleDaRicevere.getText();

				if(caselleToYou.equals("")) {
					caselleToYou = "Casella non selezionata";
				}
				if(soldiDaDare.equals("")) {
					soldiDaDare = "0";
				}
				int soldi = Integer.parseInt(soldiDaDare);

				if (soldi > board.getGiocatoreCorrente().getSoldi()) {
					soldi = board.getGiocatoreCorrente().getSoldi();
				}
				if (casellePresentiBot.contains(caselleToYou) && soldi>=0) {
					Casella casellaDaPrendere = board.getCasellaDaNome(caselleToYou);

					boolean accettato = board.getGiocatoreBot().chiediSeAccettaVendita(caselleToYou, soldiDaDare, 
							board.getGiocatori().get(board.getGiocatoreAvversarioIndex()),
							board.getGiocatoreCorrente());
					if (accettato) {
						board.compraCasellaAvversaria(casellaDaPrendere, board.getGiocatoreCorrente(),
								board.getGiocatori().get(board.getGiocatoreAvversarioIndex()), soldi);
						
						temp.setVisible(false);
						avversarioAccetta();
						TavolaDaGioco.update(board.getGiocatoreCorrente());
					}
					else {
						temp.setVisible(false);
						avversarioRifiuta();
					}
				} else {
					offertaNonValida();
				}
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
