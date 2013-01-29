package lsi.identificadores;

import lsi.constants.NGC;

public class Parametro extends Identificador {

	private int deslocamento;
	private long tipoPassagem;
	private long tipo;
	
	public Parametro(String nome, int nivel, int deslocamento, long tipoPassagem, long tipo) {
		super(nome, NGC.ID_PARAM, nivel);
		setDeslocamento(deslocamento);
		setTipoPassagem(tipoPassagem);
		setTipo(tipo);
	}

	public int getDeslocamento() {
		return deslocamento;
	}

	public void setDeslocamento(int deslocamento) {
		this.deslocamento = deslocamento;
	}

	public long getTipoPassagem() {
		return tipoPassagem;
	}

	public void setTipoPassagem(long tipoPassagem) {
		this.tipoPassagem = tipoPassagem;
	}

	public long getTipo() {
		return tipo;
	}

	public void setTipo(long tipo) {
		this.tipo = tipo;
	}

}
