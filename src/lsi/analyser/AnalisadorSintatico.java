package lsi.analyser;

import lsi.Env;
import lsi.errors.LexicalError;
import lsi.errors.SemanticError;
import lsi.errors.SyntaticError;
import lsi.i18n.I18nConstants;

public class AnalisadorSintatico {

	private static AnalisadorSintatico instance;
	private Lexico lexico;
	private Sintatico sintatico;
	private Semantico semantico;

	public static AnalisadorSintatico getInstance() {
		if (instance == null)
			instance = new AnalisadorSintatico();
		return instance;
	}

	private AnalisadorSintatico() {
		lexico = new Lexico();
		sintatico = new Sintatico();
		semantico = new Semantico();
	}
	
	private void limpaAnalisadores(){
		lexico = new Lexico();
		sintatico = new Sintatico();
		semantico = new Semantico();
	}
	
	public String analyse(String input){
		limpaAnalisadores();
		semantico.init();
		lexico.setInput(input);
		try {
			sintatico.parse(lexico, semantico);
		} catch (LexicalError e) {
			Env.getParentScreenController().setCaretPosition(e.getPosition());
			return e.getMessage() + I18nConstants.get("at") + e.getPosition(); 
		} catch (SyntaticError e) {
			Env.getParentScreenController().setCaretPosition(e.getPosition());
			return e.getMessage() + I18nConstants.get("at") + e.getPosition(); 
		} catch (SemanticError e) {
			Env.getParentScreenController().setCaretPosition(e.getPosition());
			return e.getMessage() + I18nConstants.get("at") + e.getPosition(); 
		}
		return I18nConstants.get("noErrors"); 
	}
	
}
