package lsi.identificadores;

import java.util.List;

import lsi.TabelaDeSimbolosMetodo;
import lsi.constants.NGC;

public class Funcao extends Metodo {

	private long tipoResultado;
	
	public Funcao(String nome, int nivel, int endereco, int numParam, List<Parametro> params, long tipoResultado, TabelaDeSimbolosMetodo tabelaLocal) {
		super(nome, NGC.ID_FUNC, nivel, endereco, numParam, params, tabelaLocal);
		setTipoResultado(tipoResultado);
	}

	public long getTipoResultado() {
		return tipoResultado;
	}

	public void setTipoResultado(long tipoResultado) {
		this.tipoResultado = tipoResultado;
	}

}
