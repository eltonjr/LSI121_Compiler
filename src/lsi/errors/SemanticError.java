package lsi.errors;

public class SemanticError extends AnalysisError {
	private static final long serialVersionUID = -4226410595861238016L;

	public SemanticError(String msg, int position) {
		super(msg, position);
	}

	public SemanticError(String msg) {
		super(msg);
	}
}
