package Gioco;


import Elementi.*;
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
	
	//info relative allo stato del giocatore (turno corrente)
	int numDoppi;
	int giocatoreCorrenteIndex;
	Giocatore giocatoreCorrente;
	
	//simple constructor
	public Board(ArrayList<Giocatore> giocatori)  {
		this.giocatori = giocatori;
		this.dadi = new Dadi();
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
	
	//AZIONI GIOCATORE CORRENTE	
	
	public void tiraDadi()  {
		int numPlaces = dadi.tiraDadi();
		System.out.println("Giocatore " + giocatoreCorrente.getNome() + " ha rollato " + dadi.toString());
		
		if(dadi.isDoppioNumero() && numDoppi == 2)  {
			System.out.println("Terzo numero doppio consecutivo, Giocatore " + giocatoreCorrente.getNome() + " finisce in prigione");
			giocatoreCorrente.setInPrigione(true);
			giocatoreCorrente.setPosizioneInTabella(10);
			return;
		} else if(dadi.isDoppioNumero() && giocatoreCorrente.isInPrigione())  {
			System.out.println("Numero doppio, Giocatore " + giocatoreCorrente.getNome() + " esce dalla prigione");
			giocatoreCorrente.setInPrigione(false);
		} else if(giocatoreCorrente.isInPrigione())  {
			System.out.println("Giocatore " + giocatoreCorrente.getNome() + " non è uscito di prigione");
			return;
		}
		
		int position = giocatoreCorrente.getPosizioneInTabella();
		
		//gestione "passa dal via"
		giocatoreCorrente.setPosizioneInTabella(position + numPlaces);
		if(giocatoreCorrente.getPosizioneInTabella() < position)  {
			System.out.println("Giocatore " + giocatoreCorrente.getNome() + " passa dal via");
			banca.pagaPassaggioStart(giocatoreCorrente);
		}
		
		System.out.println("Giocatore " + giocatoreCorrente.getNome() + " arriva su: " + mappa.get(giocatoreCorrente.getPosizioneInTabella())); 
		gestisciPosizione(mappa.get(giocatoreCorrente.getPosizioneInTabella()));
		
		//controlla se fine turno
		if(dadi.isDoppioNumero())  {
			numDoppi++;
		} else  {
			finisciTurno();
		}
	}
	
	public void proponiScambio()  {
		
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
	
	public void usaTokenPrigione()  {
		if(giocatoreCorrente.hasTokenPrigione())  {
			giocatoreCorrente.usaTokenPrigione();
		}
		System.out.println("Giocatore " + giocatoreCorrente.getNome() + " ha usato token prigione ed è ora libero");
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
			System.out.println("Giocatore " + giocatoreCorrente.getNome() + " ha ipotecato " + cas.getNome() + " per " + cas.getPrezzoIpoteca());
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
			System.out.println("Giocatore " + giocatoreCorrente.getNome() + " finisce in prigione");
			giocatoreCorrente.setPosizioneInTabella(10);
			giocatoreCorrente.setInPrigione(true);
		} else if(position.equals("IncomeTax"))  {
			System.out.println("Giocatore " + giocatoreCorrente.getNome() + " paga " + CostantiGioco.INCOME_TAX + " di tassa");
			giocatoreCorrente.diminuisciSoldi(CostantiGioco.INCOME_TAX);
		} else if(position.equals("SuperTax"))  {
			System.out.println("Giocatore " + giocatoreCorrente.getNome() + " paga " + CostantiGioco.SUPER_TAX + " di super tassa");
			giocatoreCorrente.diminuisciSoldi(CostantiGioco.SUPER_TAX);
		} else if(position.equals("CommunityChest"))  {
			System.out.println("Giocatore " + giocatoreCorrente.getNome() + " pesca una carta");
			gestisciCartaPescata(mazzo.pescaChest());
		} else if(position.equals("Chance"))  {
			System.out.println("Giocatore " + giocatoreCorrente.getNome() + " pesca una carta");
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
		
		System.out.println(carta[0]);
		
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
}