package lsi.errors;

public class LexicalError extends AnalysisError {
	private static final long serialVersionUID = 5589369905883039040L;

	public LexicalError(String msg, int position) {
		super(msg, position);
	}

	public LexicalError(String msg) {
		super(msg);
	}
}
