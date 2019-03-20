package Elementi;

import java.util.ArrayList;
import java.util.HashMap;

import Gioco.CreatoreCaselle;

//singleton board
public class Board {

	ArrayList<Giocatore> giocatori;
	HashMap<Integer, String> mappa;
	HashMap<String, Casella> caselle;
	
	MazzoCarte mazzo;
	
	Banca banca;
	Dadi dadi;
	
	int giocatoreCorrenteIndex;
	Giocatore giocatoreCorrente;
	
	//simple constructor
	public Board(ArrayList<Giocatore> giocatori)  {
		this.giocatori = giocatori;
		this.dadi = new Dadi();
		this.mazzo = new MazzoCarte();
		
		this.giocatoreCorrenteIndex = 0;
		this.giocatoreCorrente = giocatori.get(0);
		this.creaMappa();
	}
	
	private void creaMappa()  {
		CreatoreCaselle creatore = new CreatoreCaselle();
		
		mappa = creatore.caricaMappa();
		caselle = creatore.getCaselle();
		banca = new Banca();
	}
	
	public void iniziaTurno()  {
		
	}
	
	public void finisciTurno()  {
		giocatoreCorrenteIndex++;
		
		if(giocatoreCorrenteIndex > giocatori.size())  {
			giocatoreCorrenteIndex = 0;
		}
		
		giocatoreCorrente = giocatori.get( giocatoreCorrenteIndex );
	}
	
	public void tiraDadi()  {
		int numPlaces = dadi.tiraDadi();
		
		int position = giocatoreCorrente.getPosizioneInTabella();
		
		//gestione "passa dal via"
		giocatoreCorrente.setPosizioneInTabella(position + numPlaces);
		if(giocatoreCorrente.getPosizioneInTabella() < position)  {
			banca.pagaPassaggioStart(giocatoreCorrente);
		}
		
		gestisciPosizione(mappa.get(giocatoreCorrente.getPosizioneInTabella()));
	}
	
	public void gestisciPosizione(String position)  {
		if(position.equals("Start") || position.equals("FreeParking") || position.equals("Jail"))  {
			return;
		}
		
		if(position.equals("GoJail"))  {
			giocatoreCorrente.setPosizioneInTabella(10);
			giocatoreCorrente.setInPrigione(true);
		} else if(position.equals("IncomeTax"))  {
			giocatoreCorrente.diminuisciSoldi(CostantiGioco.INCOME_TAX);
		} else if(position.equals("SuperTax"))  {
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
				
				boolean vuoleComprare = giocatoreCorrente.getSoldi() >= casella.getPrezzoVendita(); //&& "chiede se la vuole comprare"
				
				if(vuoleComprare)  {
					banca.vendiCasella(giocatoreCorrente, casella);
				} else  {
					banca.iniziaAsta(casella, giocatori);
				}
				
			} else  {
				//casella occupata
				
				int importo = casella.getPrezzoTransito();
				giocatoreCorrente.diminuisciSoldi(importo);
				casella.getProprietario().aumentaSoldi(importo);
			}
		}
	}
	
	public void gestisciCartaPescata(String[] carta)  {
		
		//TODO (appare schermata con il nome della carta per informare il giocatore di che carta si tratta) (carta[0])
		
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
		}
	}
}
