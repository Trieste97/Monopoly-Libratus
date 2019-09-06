package Elementi;


import java.util.ArrayList;
import java.util.HashMap;

import AI.Writer;
import GUI.TavolaDaGioco;
import Gioco.CreatoreCaselle;

//singleton board
public class Board {

	Writer writer = new Writer();
	//AIClass ai = new AIClass();
	
	
	ArrayList<Giocatore> giocatori;
	HashMap<Integer, String> mappa;
	private HashMap<String, Casella> caselle;
	
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
		setCaselle(creatore.getCaselle());
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
		boolean again = gestisciNumeroDadi();
		TavolaDaGioco.update(giocatoreCorrente);
		if (!again)  {
			finisciTurno();
			//giocatoreCorrente.setInPrigione(true);
			//gestione AI
			do  {
				int decisione = giocatoreCorrente.decidiCosaFare(this.giocatori);
				while (decisione > 0)  {
					GiocatoreAI player = (GiocatoreAI) giocatoreCorrente;
					
					//SCAMBIO
					if (decisione == 1)  {
						//non implementata ancora
						break;
						//String tmp = player.decidiCosaScambiare(this.giocatori);
					}
					
					//ESCI PRIGIONE
					else if (decisione == 2)  {
						String modo = player.voglioUscireDiPrigione();
						if (modo.equals("dadi"))
							break;
						else
							esciDiPrigione(modo);
						
						break;
					}
					
					//IPOTECA
					else if (decisione == 3)  {
						//può ritornare il colore (=> vendere una casa per casella del set
						//o il nome di una casella
						String daIpotecare = player.decidiCosaIpotecare(getGiocatoreBot());
						
						if (isColore(daIpotecare))  {
							this.vendiCase(daIpotecare);
						} else  {
							this.ipoteca(daIpotecare);
						}
					}
					
					//COSTRUISCO
					else if (decisione == 4)  {
						this.costruisci(player.decidiCosaCostruire(this));
						break;
					}
					decisione = giocatoreCorrente.decidiCosaFare(this.giocatori);
				}
				numPlaces = getDadi().tiraDadi();
				TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " ha rollato " + getDadi().toString());
				again = gestisciNumeroDadi();
				TavolaDaGioco.update(giocatoreCorrente);
			} while (again);
			finisciTurno();
		}
		getGiocatoreVero().setInPrigione(true);
	}
	public boolean gestisciNumeroDadi()  {		
		if(getDadi().isDoppioNumero() && numDoppi == 2)  {
			TavolaDaGioco.aggiungiACronologia("Terzo numero doppio consecutivo,\nGiocatore " + giocatoreCorrente.getNome() + " finisce in prigione");
			giocatoreCorrente.setInPrigione(true);
			giocatoreCorrente.setPosizioneInTabella(10);
			
			return false;
		} else if(getDadi().isDoppioNumero() && giocatoreCorrente.isInPrigione())  {
			TavolaDaGioco.aggiungiACronologia("Numero doppio, Giocatore " + giocatoreCorrente.getNome() + " esce dalla prigione");
			giocatoreCorrente.resetTurniPrigione();
			giocatoreCorrente.setInPrigione(false);
		} else if(giocatoreCorrente.isInPrigione() && giocatoreCorrente.getTurniPrigione() > 2)  {
			giocatoreCorrente.resetTurniPrigione();
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " esce di prigione dopo 3 turni");
		} else if(giocatoreCorrente.isInPrigione())  {
			giocatoreCorrente.incrTurniPrigione();
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " non è uscito di prigione");
			
			return false;
		}
		
		int position = giocatoreCorrente.getPosizioneInTabella();
		
		//gestione "passa dal via"
		giocatoreCorrente.setPosizioneInTabella(position + numPlaces);
		if(giocatoreCorrente.getPosizioneInTabella() < position)  {
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " passa dal via");
			TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " riceve 2000 euro");
			
			banca.pagaPassaggioStart(giocatoreCorrente);
		}
		
		TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " arriva su: " + mappa.get(giocatoreCorrente.getPosizioneInTabella()));
		
		gestisciPosizione(mappa.get(giocatoreCorrente.getPosizioneInTabella()));
		
		//controlla se fine turno
		if(getDadi().isDoppioNumero())  {
			numDoppi++;
			return true;
		}
		return false;
	}
	public void scambia(int soldiToBot, int soldiToYou, String[] caselleToBot, String[] caselleToYou)  {
		getGiocatoreBot().aumentaSoldi(soldiToBot);
		getGiocatoreBot().diminuisciSoldi(soldiToYou);
		getGiocatoreVero().aumentaSoldi(soldiToYou);
		getGiocatoreVero().diminuisciSoldi(soldiToBot);
		
		for (String s : caselleToBot)  {
			caselle.get(s).setProprietario(getGiocatoreBot());
		}
		for (String s : caselleToBot)  {
			caselle.get(s).setProprietario(getGiocatoreVero());
		}
	}
	public void compraCasellaAvversaria(Casella casella, Giocatore giocatoreCheFaPorposta, Giocatore giocatoreCheAccetta, int prezzo)  {
		giocatoreCheAccetta.getCasellePossedute().remove(casella);
		giocatoreCheFaPorposta.getCasellePossedute().add(casella);
		casella.setProprietario(giocatoreCheFaPorposta);
		giocatoreCheFaPorposta.diminuisciSoldi(prezzo);
		TavolaDaGioco.aggiungiACronologia(giocatoreCheFaPorposta.getNome() + " ha acquistato " + casella.getNome());
	}
	public void costruisci(ArrayList<String> sets)  {
		for(String colore : sets)  {
			ArrayList<CasellaResidenziale> caselle = this.checkPossedimentoColore(colore, giocatoreCorrente);
			
			if(caselle != null)  {
				
				if(caselle.get(0).getNumeroCaseCostruite() + 1 > 5)  {
					TavolaDaGioco.aggiungiACronologia("C'è già un albergo, impossibile costruire ancora qui".toUpperCase());
				} else if(giocatoreCorrente.getSoldi() < caselle.get(0).getPrezzoCostruzioneCasa() * caselle.size())  {
					TavolaDaGioco.aggiungiACronologia("Soldi non sufficienti".toUpperCase());
				} else  {
					//TavolaDaGioco.aggiungiACronologia("Casa costruita con successo".toUpperCase());
					
					for (CasellaResidenziale c : caselle)  {
						c.aggiungiCasa();
						giocatoreCorrente.diminuisciSoldi(c.getPrezzoCostruzioneCasa());
					}
					
				}
				
			}
		}		
	}
	public void esciDiPrigione(String modo)  {
		if(modo.equals("token"))  {
			if(giocatoreCorrente.hasTokenPrigione())  {
				giocatoreCorrente.usaTokenPrigione();
				TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " ha usato token prigione ed è ora libero");
				
			} else  {
				System.out.println("Giocatore " + giocatoreCorrente.getNome() + " non ha token prigione");
			}
			
		}
		else if (modo.equals("paga")) {
			if(giocatoreCorrente.isInPrigione())  {
				giocatoreCorrente.diminuisciSoldi(500);
				giocatoreCorrente.setInPrigione(false);
				TavolaDaGioco.aggiungiACronologia("Giocatore " + giocatoreCorrente.getNome() + " ha pagato per uscire di prigione");
			} else  {
				System.out.println("Non sei in prigione");
			}
		}
	}
	public void ipoteca(String nomeCasella)  {
		Casella cas = getCaselle().get(nomeCasella);
		
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
	public void vendiCase(String colore)  {
		for(CasellaResidenziale c : getGiocatoreCorrente().getCaselleResidenzialiOggetto())  {
			if (c.getColore().equals(colore))  {
				c.rimuoviCasa();
				getGiocatoreCorrente().aumentaSoldi(c.getPrezzoCostruzioneCasa()/2);
			}
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
			Casella casella = getCaselle().get(position);
			
			if(!casella.haProprietario())  {
				//casella libera

				boolean vuoleComprare = false;
				
				if (giocatoreCorrente.getNome().equals("BOT1")) {
					vuoleComprare = getGiocatoreBot().decidiSeComprareCasella(casella, getGiocatoreBot());
				}
				else {
				//Se sei il giocatore normale gestisci la proposta
					vuoleComprare = giocatoreCorrente.getSoldi() >= casella.getPrezzoVendita() && TavolaDaGioco.chiediSeVuoleComprare(casella);
				}
	
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
				giocatoreCorrente.setPosizioneInTabella(10);
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
	public int getGiocatoreCorrenteIndex()  {
		return giocatoreCorrenteIndex;
	}
	public int getGiocatoreAvversarioIndex()  {
		if(giocatoreCorrenteIndex == 0) {
			return 1;
		}
		return 0;
	}

	public boolean isAITurn()  {
		return giocatoreCorrente instanceof GiocatoreAI;
	}
	public Giocatore getGiocatoreVero()  {
		return this.giocatori.get(0);
	}
	public GiocatoreAI getGiocatoreBot()  {
		return (GiocatoreAI) this.giocatori.get(1);
	}
	public ArrayList<CasellaResidenziale> checkPossedimentoColore(String colore, Giocatore giocatore)  {
		ArrayList<CasellaResidenziale> caselle = new ArrayList<CasellaResidenziale>();
		for(Casella c : giocatore.getCasellePossedute())  {
			if(!(c instanceof CasellaResidenziale))  {
				continue;
			}
			
			CasellaResidenziale cr = (CasellaResidenziale) c;
			String col2 = cr.getColore();
			if(colore.equals(col2))  {
				caselle.add(cr);
			}
		}
		if(caselle.size() == 3)  {
			return caselle;
		} else if(caselle.size() == 2 && (colore.equals("brown") || colore.equals("blue")))  {
			return caselle;
		}
		
		return null;
	}
	
	
	public HashMap<String, Casella> getCaselle() {
		return caselle;
	}
	public void setCaselle(HashMap<String, Casella> caselle) {
		this.caselle = caselle;
	}
	public boolean isColore(String col)  {
		return col.equals("brown") || col.equals("lightblue") || col.equals("pink") || 
				col.equals("orange") || col.equals("red") || col.equals("yellow") || 
				col.equals("green") || col.equals("blue");
	}
}