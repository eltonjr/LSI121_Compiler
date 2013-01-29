package lsi.errors;

public class SyntaticError extends AnalysisError {
	private static final long serialVersionUID = -4234688871250711678L;

	public SyntaticError(String msg, int position) {
		super(msg, position);
	}

	public SyntaticError(String msg) {
		super(msg);
	}
}
