package lsi.analyser;

import lsi.errors.LexicalError;

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
			return e.getMessage() + " em " + e.getPosition();
		}
		return "Sem erros";
	}
}
