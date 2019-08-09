package AI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;

public class Parser {

	public Parser() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean parsePropostaAcquisto(AnswerSets answers) {
		// TODO Auto-generated method stub
		boolean esito = false;
		int n = 0;
		for(AnswerSet a: answers.getAnswersets()){
			 System.out.println("AS n.: " + ++n + ": " + a);
			 String accetto = "accetto";
			 String as = a.toString();
			 if(as.contains(accetto)) {
				 esito = true;
				 System.out.println("Accetta Scambio");
			 }
			 else {
				 System.out.println("Rifiuta Scambio");
			 }
		}
		return esito;

	}
	
	
	public String parseUscitaPrigione(AnswerSets answers) {
		// TODO Auto-generated method stub
		String esito = "";
		int n = 0;
		for(AnswerSet a: answers.getAnswersets()){
			 System.out.println("AS n.: " + ++n + ": " + a);
			 String as = a.toString();
			 if(as.contains("token")) {
				 esito = "token";
				 System.out.println("TOKEN");
			 }
			 else if(as.contains("dadi")) {
				 esito = "dadi";
				 System.out.println("DADI");
			 }
			 else if (as.contains("paga")) {
				 esito = "paga";
				 System.out.println("PAGA");
			}
		}
		return esito;

	}
	
	public ArrayList<String> parseProposte(AnswerSets answers) {
		// TODO Auto-generated method stub
		//Ritorno un array list: A[0] indica il modo (Scambio o Acquisto o Niente), A[1] indica la casella da prendere,
		//A[2] indica la casella eventuale da scambiare
		ArrayList<String> esito = new ArrayList<String>();
//		esito.add("Niente");
		int n = 0;
		for(AnswerSet a: answers.getAnswersets()){ //il problema dei null pointer è che non ci sono as
			 System.out.println("AS n.: " + ++n + ": " + a);
			 String as = a.toString();
			 if(as.contains("chiediAcquisto")) {
				 System.out.println("AGGIUNGO ACQUISTO");
//				 esito.clear();
				 esito.add("Acquisto");
				 ArrayList<Atomo> atomi = parseAtomi(as, "chiediAcquisto");
				 esito.add(atomi.get(0).get(0));
			 }
			 else if(as.contains("propostaScambio")) {
				 System.out.println("AGGIUNGO SCAMBIO");
//				 esito.clear();
				 esito.add("Scambio");
				 ArrayList<Atomo> atomi = parseAtomi(as, "propostaScambio");
				 esito.add(atomi.get(0).get(0));
				 esito.add(atomi.get(0).get(1));
			 }
			 else{
				 System.out.println("AGGIUNGO NIENTE");
//				 esito.clear();
				 esito.add("Niente");
			 }
			 System.out.println("CI SONO UN SACCO DI ASSSSSSSSSSS");
		}
		System.out.println("MINNI VAJUUUUUUUUUUU");
		return esito;

	}
	
	private ArrayList<Atomo> parseAtomi(String as, String atomoDaIsolare) {
		// TODO Auto-generated method stub
		
		ArrayList<Atomo> atomiDaIsolare = new ArrayList<Atomo>();
		
		String[] atomi = as.split("\\)");
		for (String atomo : atomi) {
//			System.out.println(atomo);
			atomo = atomo.replaceAll("\\s", "");
			if(atomo.contains(atomoDaIsolare)) {
				System.out.println(atomo);
				atomo = atomo.replace(atomoDaIsolare, "");
				atomo = atomo.replace("(", "");
				atomo = atomo.replace(")", "");
				atomo = atomo.replace("[", "");
				atomo = atomo.replace("]", "");
				String[] valori = atomo.split("\\,");
				atomiDaIsolare.add(new Atomo(atomoDaIsolare, valori));
				for (String string : valori) {
					System.out.println("valore");
					System.out.println(string);
					System.out.println();
				}
			}
		}
		return atomiDaIsolare;

	}

}
