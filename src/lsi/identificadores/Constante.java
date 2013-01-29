package lsi.identificadores;

import lsi.constants.NGC;

public class Constante extends Identificador {

	private long tipo;
	private String valor;

	public Constante(String nome, int nivel, long tipo, String valor) {
		super(nome, NGC.ID_CONST, nivel);
		setNivel(nivel);
		setTipo(tipo);
		setValor(valor);
	}

	public long getTipo() {
		return tipo;
	}

	public void setTipo(long tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
