package Gioco;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Elementi.Banca;
import Elementi.Casella;
import Elementi.CasellaResidenziale;
import Elementi.CostantiGioco;
import Elementi.Dadi;
import Elementi.Giocatore;
import Elementi.MazzoCarte;
import GUI.TavolaDaGioco;

//singleton board
public class Board {

	ArrayList<Giocatore> giocatori;
	HashMap<Integer, String> mappa;
	HashMap<String, Casella> caselle;
	
	MazzoCarte mazzo;
	
	Banca banca;
	private Dadi dadi;
	
	//info relative allo stato del giocatore (turno corrente)
	int numDoppi;
	int giocatoreCorrenteIndex;
	Giocatore giocatoreCorrente;
	
	
	//simple constructor
	public Board(ArrayList<Giocatore> giocatori)  {
		this.giocatori = giocatori;
		this.setDadi(new Dadi());
		this.mazzo = new MazzoCarte();
		
		//status
		this.giocatoreCorrenteIndex = 0;
		this.giocatoreCorrente = giocatori.get(0);
		this.numDoppi = 0;
		
		this.creaMappa();
	}
	
	private void creaMappa()  {
		CreatoreCaselle creatore = new CreatoreCaselle();
		
		mappa = creatore.caricaMappa();
		caselle = creatore.getCaselle();
		banca = new Banca();
	}
	
	public Giocatore getGiocatoreCorrente()  {
		return giocatoreCorrente;
	}
	
	public ArrayList<Giocatore> getGiocatori()  {
		return giocatori;
	}
	
	//AZIONI GIOCATORE CORRENTE
	
	private int numPlaces;
	public void rollaDadi() {
		numPlaces = getDadi().tiraDadi();
		TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " ha rollato " + getDadi().toString());
	}
	
	public void tiraDadi()  {
		if(getDadi().isDoppioNumero() && numDoppi == 2)  {
			TavolaDaGioco.aggiungiACronologia("Terzo numero doppio consecutivo, Giocatore " + giocatoreCorrente.getNome() + " finisce in prigione");
			
			giocatoreCorrente.setInPrigione(true);
			giocatoreCorrente.setPosizioneInTabella(10);
			finisciTurno();
			return;
		} else if(getDadi().isDoppioNumero() && giocatoreCorrente.isInPrigione())  {
			TavolaDaGioco.aggiungiACronologia("Numero doppio, Giocatore " + giocatoreCorrente.getNome() + " esce dalla prigione");
			
			giocatoreCorrente.setInPrigione(false);
		} else if(giocatoreCorrente.isInPrigione())  {
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " non è uscito di prigione");
			
			finisciTurno();
			return;
		}
		
		int position = giocatoreCorrente.getPosizioneInTabella();
		
		//gestione "passa dal via"
		giocatoreCorrente.setPosizioneInTabella(position + numPlaces);
		if(giocatoreCorrente.getPosizioneInTabella() < position)  {
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " passa dal via");
			
			banca.pagaPassaggioStart(giocatoreCorrente);
		}
		
		TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " arriva su: " + mappa.get(giocatoreCorrente.getPosizioneInTabella()));
		
		gestisciPosizione(mappa.get(giocatoreCorrente.getPosizioneInTabella()));
		
		//controlla se fine turno
		if(getDadi().isDoppioNumero())  {
			numDoppi++;
		} else  {
			finisciTurno();
		}
	}
	
	public void proponiScambio(Giocatore g)  {
		
	}
	
	public void costruisci(String nome)  {
		Casella cas = caselle.get(nome);
		
		if(cas == null)  {
			System.out.println("Non è stato selezionato niente");
			return;
		}
		
		boolean permesso = banca.checkPossedimentoColore(cas, giocatoreCorrente);

		if(permesso)  {
			
			CasellaResidenziale casella = (CasellaResidenziale) cas;					
			
			if(casella.getNumeroCaseCostruite() + 1 > 5)  {
				System.out.println("C'è già un albergo, impossibile costruire ancora qui");
			} else if(giocatoreCorrente.getSoldi() < casella.getPrezzoCostruzioneCasa())  {
				System.out.println("Soldi non sufficienti");
			} else  if(!banca.checkDifferenzaCaseColore(cas, giocatoreCorrente))  {
				System.out.println("Devi costruire prima sulle altre caselle");
			} else  {
				System.out.println("Casa costruita con successo");
				
				casella.aggiungiCasa();
				giocatoreCorrente.diminuisciSoldi(casella.getPrezzoCostruzioneCasa());
			}
			
		} else  {
			System.out.println("Non hai tutto il set del colore della casella scelta");
		}
	}
	
	public void esciDiPrigione(String modo)  {
		if(modo.equals("Utilizza token"))  {
			if(giocatoreCorrente.hasTokenPrigione())  {
				giocatoreCorrente.usaTokenPrigione();
				TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " ha usato token prigione ed è ora libero");
				
			} else  {
				System.out.println("Giocatore " + giocatoreCorrente.getNome() + " non ha token prigione");
			}
			
		}  else  {
			//vedere quanto si paga per uscire di prigione
		}
	}
	
	public void ipoteca(String nomeCasella)  {
		Casella cas = caselle.get(nomeCasella);
		
		if(cas == null)  {
			System.out.println("Non è stato selezionato niente");
		} else if(cas.isIpotecata())  {
			System.out.println("La casella è già ipotecata");
		} else  {
			giocatoreCorrente.aumentaSoldi(cas.getPrezzoIpoteca());
			cas.setIpotecata(true);
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " ha ipotecato " + cas.getNome() + " per " + cas.getPrezzoIpoteca());
		}
	}
	
	public void finisciTurno()  {
		giocatoreCorrenteIndex++;
		
		if(giocatoreCorrenteIndex >= giocatori.size())  {
			giocatoreCorrenteIndex = 0;
		}
		
		numDoppi = 0;
		giocatoreCorrente = giocatori.get( giocatoreCorrenteIndex );
	}
	
	public void gestisciPosizione(String position)  {
		if(position.equals("Start") || position.equals("FreeParking") || position.equals("Jail"))  {
			return;
		}
		
		if(position.equals("GoJail"))  {
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " finisce in prigione");
			giocatoreCorrente.setPosizioneInTabella(10);
			giocatoreCorrente.setInPrigione(true);
			
		} else if(position.equals("IncomeTax"))  {
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " paga " + CostantiGioco.INCOME_TAX + " di tassa");
			giocatoreCorrente.diminuisciSoldi(CostantiGioco.INCOME_TAX);
			
		} else if(position.equals("SuperTax"))  {
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " paga " + CostantiGioco.SUPER_TAX + " di super tassa");
			giocatoreCorrente.diminuisciSoldi(CostantiGioco.SUPER_TAX);
			
		} else if(position.equals("CommunityChest"))  {
			gestisciCartaPescata(mazzo.pescaChest());
			
		} else if(position.equals("Chance"))  {
			gestisciCartaPescata(mazzo.pescaChest());
			
		} else  {
			//capitato su una casella, se libera può acquistarla, altrimenti deve pagare la rendita
			Casella casella = caselle.get(position);
			
			if(!casella.haProprietario())  {
				//casella libera
				
				boolean vuoleComprare = giocatoreCorrente.getSoldi() >= casella.getPrezzoVendita() && TavolaDaGioco.chiediSeVuoleComprare(casella);
				
				if(vuoleComprare)  {
					banca.vendiCasella(giocatoreCorrente, casella);
					TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " ha acquistato " + casella.getNome());
				} else  {
					banca.iniziaAsta(casella, giocatori);
				}
				
			} else if(giocatoreCorrente != casella.getProprietario()) {
				//casella occupata
				
				int importo = casella.getPrezzoTransito();
				giocatoreCorrente.diminuisciSoldi(importo);
				casella.getProprietario().aumentaSoldi(importo);
				
				TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " paga a " + casella.getProprietario().getNome() + " " + importo + "€");
			}
		}
	}
	
	public void gestisciCartaPescata(String[] carta)  {
		
		TavolaDaGioco.aggiungiACronologia(carta[0]);
		
		String effetto = carta[1];
		String valore  = carta[2];
		int val = Integer.parseInt(valore);
		
		if(effetto.equals("RiceviSoldi"))  {
			giocatoreCorrente.aumentaSoldi(val);
		
		} else if(effetto.equals("PagaSoldi"))  {
			giocatoreCorrente.diminuisciSoldi(val);
			
		} else if(effetto.equals("MuoviSuCasella"))  {
			int prevPos = giocatoreCorrente.getPosizioneInTabella();
			giocatoreCorrente.setPosizioneInTabella(val);
			if(val == 10)  {
				giocatoreCorrente.setInPrigione(true);
				return;
			}
			
			//gestione passa dal via
			if(prevPos > val)  {
				banca.pagaPassaggioStart(giocatoreCorrente);
			}
			gestisciPosizione(mappa.get(val));
			
		} else if(effetto.equals("MuoviCaselleIndietro"))  {
			giocatoreCorrente.avanza(-val);
			gestisciPosizione(mappa.get(giocatoreCorrente.getPosizioneInTabella()));
		} else if(effetto.equals("TokenPrigione"))  {
			giocatoreCorrente.addTokenPrigione();
		}
	}

	public Dadi getDadi() {
		return dadi;
	}

	public void setDadi(Dadi dadi) {
		this.dadi = dadi;
	}
	
	
}