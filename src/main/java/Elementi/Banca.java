package Elementi;

import java.util.ArrayList;

import GUI.AskBox;
import GUI.TavolaDaGioco;

public class Banca {
	
	public Banca()  {
		
	}
	
	public void pagaPassaggioStart(Giocatore giocatore)  {
		giocatore.aumentaSoldi(2000);
	}
	
	public void vendiCasella(Giocatore giocatore, Casella casella)  {
		giocatore.diminuisciSoldi(casella.getPrezzoVendita());
		giocatore.aggiungiCasella(casella);
		casella.setProprietario(giocatore);
	}
	
	//asta semplice, prezzo di partenza = 10, i giocatori possono o ritirarsi o aumentare la posta di 10
	public void iniziaAsta(Casella casella, ArrayList<Giocatore> giocatori, boolean turnoAI)  {
		
		int posta = 14950;
		int turno = 0;
		
		ArrayList<Integer> candidati = new ArrayList<Integer>();
		for(int i = 0; i < giocatori.size(); i++)  {
			candidati.add(i);
		}
		
		while(candidati.size() > 1)  {
			
			
			if(turnoAI) {
				
				GiocatoreAI bot = (GiocatoreAI) giocatori.get(candidati.get(turno));
				String postaString = Integer.toString(posta + 10);
				boolean decisionePuntata = bot.chiediSeVuolePuntare(casella.getNome(), postaString);
				if(decisionePuntata)  {
					AskBox.messaggio("Giocatore " + giocatori.get(candidati.get(turno)).getNome() + " decide di puntare");
					posta += 10;
				} 
				else  {
					//decide di ritirarsi
					AskBox.messaggio("Giocatore " + giocatori.get(candidati.get(turno)).getNome() + " si ritira dall'asta");
					candidati.remove(turno);
				}
				turno ++;
				if(turno >= candidati.size())  {
					turno = 0;
				}

				turnoAI = false;
			}
			else {
				//chiede al giocatore se vuole puntare o ritirarsi
				boolean decidePuntare = TavolaDaGioco.chiediSeVuolePuntare(giocatori.get(candidati.get(turno)), posta);
				
				if(decidePuntare)  {
					if(giocatori.get( candidati.get( turno ) ).getSoldi() < posta+10)  {
						//il giocatore non si può permettere di continuare
						AskBox.messaggio("Giocatore " + giocatori.get(candidati.get(turno)).getNome() + " non ha soldi a sufficienza");
						candidati.remove(turno);
					}
					else  {
						//il giocatore punta aumentando la posta
//						AskBox.messaggio("Giocatore " + giocatori.get(candidati.get(turno)).getNome() + " decide di puntare");
						posta += 10;
					}
				} else  {
					//decide di ritirarsi
//					AskBox.messaggio("Giocatore " + giocatori.get(candidati.get(turno)).getNome() + " si ritira dall'asta");
					candidati.remove(turno);
				}
				
				turno ++;
				if(turno >= candidati.size())  {
					turno = 0;
				}
				turnoAI = true;
			
			}
		}
		
		//while finito, c'è solo un candidato rimanente
		
		Giocatore vincitoreAsta = giocatori.get( candidati.get(0) );
		vincitoreAsta.diminuisciSoldi(posta);
		vincitoreAsta.aggiungiCasella(casella);
		casella.setProprietario(vincitoreAsta);
		
		TavolaDaGioco.aggiungiACronologia("Giocatore " + vincitoreAsta.getNome() + " si aggiudica " + casella.getNome() + " per " + posta);
	}	
	public boolean checkDifferenzaCaseColore(Casella casella, Giocatore giocatore)  {
		
		CasellaResidenziale casellaRes = (CasellaResidenziale) casella;
		for(Casella c : giocatore.getCasellePossedute())  {
			if(!(c instanceof CasellaResidenziale))  {
				continue;
			}
			
			CasellaResidenziale cas = (CasellaResidenziale) c;
			if(Math.abs(casellaRes.getNumeroCaseCostruite()-cas.getNumeroCaseCostruite()) > 1 && cas.getColore().equals(casellaRes.getColore()))  {
				return false;
			}
		}
		
		return true;
	}
}
