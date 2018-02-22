package br.ce.wcaquino.servicos;

public class NaoPodeDividirPorZero extends Exception {

	private static final long serialVersionUID = 1L;

	public NaoPodeDividirPorZero() {
		super();
	}

	public NaoPodeDividirPorZero(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NaoPodeDividirPorZero(String message, Throwable cause) {
		super(message, cause);
	}

	public NaoPodeDividirPorZero(String message) {
		super(message);
	}

	public NaoPodeDividirPorZero(Throwable cause) {
		super(cause);
	}

}
