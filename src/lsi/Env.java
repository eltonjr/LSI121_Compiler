package lsi;

import lsi.analyser.AnalisadorLexico;
import lsi.analyser.AnalisadorSintatico;
import lsi.screen.ParentScreenController;

public class Env {

	private static Env instance;
	
	private Env(){}
	
	public static Env getInstance(){
		if(instance == null){
			instance = new Env();
		}
		return instance;
	}
	
	private static ParentScreenController parentScreenController;
	private static AnalisadorLexico lexicalAnalyser;
	private static AnalisadorSintatico syntaticAnalyser;
	
	public void init(){
		setParentScreenController(ParentScreenController.getInstance());
		setLexicalAnalyser(AnalisadorLexico.getInstance());
		setSyntaticAnalyser(AnalisadorSintatico.getInstance());
		
		parentScreenController.init();
	}

	public static ParentScreenController getParentScreenController() {
		return parentScreenController;
	}

	public static void setParentScreenController(ParentScreenController parentScreenController) {
		Env.parentScreenController = parentScreenController;
	}

	public static AnalisadorLexico getLexicalAnalyser() {
		return lexicalAnalyser;
	}

	public static void setLexicalAnalyser(AnalisadorLexico lexicalAnalyser) {
		Env.lexicalAnalyser = lexicalAnalyser;
	}

	public static AnalisadorSintatico getSyntaticAnalyser() {
		return syntaticAnalyser;
	}

	public static void setSyntaticAnalyser(AnalisadorSintatico syntaticAnalyser) {
		Env.syntaticAnalyser = syntaticAnalyser;
	}

}
