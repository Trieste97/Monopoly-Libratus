package AI;

import java.io.InputStream;
import java.util.ArrayList;

import Gioco.Main;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;
import javafx.util.Pair;

public class AIClass {

	String encodingResource = "";
	String instanceResource = "";
	Handler handler;
	InputProgram  program;
	Parser parser;
	
	public AIClass() {
		// TODO Auto-generated constructor stub
		String path = "";
		if (System.getProperty("os.name").startsWith("Windows"))  {
			path = "lib/dlv.mingw.exe";
		} else  {
			path = "lib/dlv";
		}
		handler = new DesktopHandler(new DLVDesktopService(path));
		program = new ASPInputProgram(); //forse può generare problemi: creare in ogni funzione uno nuovo
		parser = new Parser();
	}
	
	public int decisioneIniziale()  {
		encodingResource="encodings/decisioneInizialeNUOVO";
		instanceResource="encodings/decisioneInizialeInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		int esito = parser.parseDecisioneIniziale(answers);
		return esito;
	}
	
	public boolean propostaAcquisto() {
		encodingResource="encodings/propostaAcquisto";
		instanceResource="encodings/propostaAcquistoInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		boolean esito = parser.parsePropostaAcquisto(answers);
		return esito;
		
	}
	
	public String uscitaPrigione() {
		encodingResource="encodings/uscitaPrigione";
		instanceResource="encodings/uscitaPrigioneInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		String esito = parser.parseUscitaPrigione(answers);
		return esito;
		
	}
		
	public ArrayList<String> gestioneProposte() {
		encodingResource="encodings/gestioneProposte";
		instanceResource="encodings/gestioneProposteInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		ArrayList<String> esito = parser.parseProposte(answers);
		return esito;
		
	}
	
	/*public boolean decisioneScambioAcquisto() {
		encodingResource="encodings/decisioneScambioAcquisto";
		instanceResource="encodings/decisioneScambioAcquistoInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		boolean esito = parser.parseDecisioneScambioAcquisto(answers);
		return esito;
		
	}*/
	public String faiPropostaScambio() {
		encodingResource="encodings/decisioneIniziale";
		instanceResource="encodings/decisioneInizialeInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		String esito = parser.parseFaiPropostaScambio(answers);
		return esito;
	}

	public ArrayList<String> decidiCosaCostruire() {
		encodingResource="encodings/decisioneCostruzione";
		instanceResource="encodings/decisioneCostruzioneInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		ArrayList<String> esito = parser.parseDecidiCosaCostruire(answers);
		return esito;
	}
	
	public Pair<Boolean,String> decidiCosaIpotecare()  {
		encodingResource="encodings/decisioneIpoteca";
		instanceResource="encodings/decisioneIpotecaInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		return parser.parseDecidiCosaIpotecare(answers);
	}
	
	public String decidiCosaDisipotecare()  {
		encodingResource="encodings/decisioneIpotecaNegativa";
		instanceResource="encodings/decisioneIpotecaNegativaInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		return parser.parseDecidiCosaDisipotecare(answers);
	}
	
	public boolean decisioneScambioAcquisto28() {
		encodingResource="encodings/decisioneScambioAcquisto28";
		instanceResource="encodings/decisioneScambioAcquistoInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		boolean esito = parser.parseDecisioneScambioAcquisto(answers);
		return esito;
		
	}
	
	public boolean decisionePuntata() {
		encodingResource="encodings/decisioneAsta";
		instanceResource="encodings/decisioneAstaInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		boolean esito = parser.parseDecisionePuntata(answers);
		return esito;
		
	}
}
