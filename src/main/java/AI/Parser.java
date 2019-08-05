package AI;

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
}
