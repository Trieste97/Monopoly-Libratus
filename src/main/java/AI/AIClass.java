package AI;

import java.util.ArrayList;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import Elementi.Casella;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv.desktop.DLVDesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class AIClass {

	String encodingResource = "";
	String instanceResource = "";
	Handler handler;
	InputProgram  program;
	Parser parser;
	
	public AIClass() {
		// TODO Auto-generated constructor stub
		handler = new DesktopHandler(new DLVDesktopService("lib/dlv.mingw.exe"));
		program = new ASPInputProgram(); //forse può generare problemi: creare in ogni funzione uno nuovo
		parser = new Parser();
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
	
	public boolean decisioneScambioAcquisto() {
		encodingResource="encodings/decisioneScambioAcquisto";
		instanceResource="encodings/decisioneScambioAcquistoInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		boolean esito = parser.parseDecisioneScambioAcquisto(answers);
		return esito;
		
	}
	
	public boolean costruzioneCasa() {
		encodingResource="encodings/costruzioneCasa";
		instanceResource="encodings/costruzioneCasaInstance";
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		boolean esito = parser.parseCostruzioneCasa(answers);
		return esito;
		
	}
	
	
	
}
