package lsi.analyser;

import lsi.errors.LexicalError;
import lsi.i18n.I18nConstants;

public class AnalisadorLexico {

	private static AnalisadorLexico instance;
	private Lexico lexico;

	public static AnalisadorLexico getInstance() {
		if (instance == null)
			instance = new AnalisadorLexico();
		return instance;
	}

	private AnalisadorLexico() {
		lexico = new Lexico();
	}

	public String analyse(String input) {
		lexico.setInput(input);
		try {
			Token t = null;
			while ((t = lexico.nextToken()) != null) {
				// System.out.println(t.getLexeme());
			}
		} catch (LexicalError e) {
			return e.getMessage() + I18nConstants.get("at") + e.getPosition(); 
		}
		return I18nConstants.get("noErrors"); 
	}
}
