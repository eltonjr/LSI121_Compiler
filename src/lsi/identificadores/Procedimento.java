package lsi.identificadores;

import java.util.List;

import lsi.TabelaDeSimbolosMetodo;
import lsi.constants.NGC;

public class Procedimento extends Metodo {

	public Procedimento(String nome, int nivel, int endereco, int numParam, List<Parametro> params, TabelaDeSimbolosMetodo tabelaLocal) {
		super(nome, NGC.ID_PROC, nivel, endereco, numParam, params, tabelaLocal);
	}

}
