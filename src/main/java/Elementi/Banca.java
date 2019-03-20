package Elementi;

import java.util.ArrayList;

public class Banca {

	final int numHotels = 12;
	final int numCase = 32;	
	
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
	public void iniziaAsta(Casella casella, ArrayList<Giocatore> giocatori)  {
		
		int posta = 10;
		int turno = 0;
		
		ArrayList<Integer> candidati = new ArrayList<Integer>();
		for(int i = 0; i < giocatori.size(); i++)  {
			candidati.add(i);
		}
		
		while(candidati.size() > 1)  {
			
			//chiede al giocatore se vuole puntare o ritirarsi
			boolean decidePuntare = true;
			
			if(decidePuntare)  {
				if(giocatori.get( candidati.get( turno ) ).getSoldi() < posta+10)  {
					//il giocatore non si pu� permettere di continuare
					candidati.remove(turno);
				}
				else  {
					//il giocatore punta aumentando la posta
					posta += 10;
				}
			} else  {
				//decide di ritirarsi
				candidati.remove(turno);
			}
			
			turno ++;
			if(turno >= candidati.size())  {
				turno = 0;
			}
		}
		
		//while finito, c'� solo un candidato rimanente
		
		Giocatore vincitoreAsta = giocatori.get( candidati.get(0) );
		vincitoreAsta.diminuisciSoldi(posta);
		vincitoreAsta.aggiungiCasella(casella);
		casella.setProprietario(vincitoreAsta);
	}
}