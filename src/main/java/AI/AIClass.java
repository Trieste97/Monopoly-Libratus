package AI;

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

/*	private static String encodingResource="encodings/propostaAcquisto";
	private static String instanceResource="encodings/propostaAcquistoInstance";
	private static Handler handler;
	
						
	public static void main(String[] args) {
		
		handler = new DesktopHandler(new DLVDesktopService("lib/dlv.mingw.exe"));
		
		InputProgram  program = new ASPInputProgram();
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		
/*		try {
			ASPMapper.getInstance().registerClass(Casella.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//fine commento		
		Output o =  handler.startSync();
		
		AnswerSets answers = (AnswerSets) o;
		Parser parser = new Parser();
		int n = 0;
		parser.parsePropostaAcquisto(answers); // passa tutti gli anwersets, se vuoi passarne solo 1, mettilo nel for
		/*for(AnswerSet a:answers.getAnswersets()){
			 System.out.println("AS n.: " + ++n + ": " + a);
			try {

				for(Object obj:a.getAtoms()){
//a					if(obj instanceof Casella)  {
//a						Casella cas = (Casella) obj;
//a						System.out.print(cas.getNome() + " ");
//a					}
					System.out.println(obj);
					System.out.println("ciao");
				}
				System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
			} 			
		}*/
//	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean propostaAcquisto() {
		String encodingResource="encodings/propostaAcquisto";
		String instanceResource="encodings/propostaAcquistoInstance";
		Handler handler = new DesktopHandler(new DLVDesktopService("lib/dlv.mingw.exe"));
		InputProgram  program = new ASPInputProgram();
		program.addFilesPath(encodingResource);
		program.addFilesPath(instanceResource);
		handler.addProgram(program);
		Output o =  handler.startSync();
		AnswerSets answers = (AnswerSets) o;
		Parser parser = new Parser();
		boolean esito = parser.parsePropostaAcquisto(answers);
		System.out.println("Esito :" + esito);
		return esito;
		
	}
		
	
}
