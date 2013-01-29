package lsi.identificadores;

import java.util.List;

import lsi.TabelaDeSimbolosMetodo;

public abstract class Metodo extends Identificador {
	
	private int endereco;
	private int numParam;
	private List<Parametro> parametros;
	private TabelaDeSimbolosMetodo tabelSimbolosLocal;

	public Metodo(String nome, long categoria, int nivel, int endereco, int numParam, List<Parametro> params, TabelaDeSimbolosMetodo tabelaLocal) {
		super(nome, categoria, nivel);
		setEndereco(endereco);
		setNumParam(numParam);
		setParametros(params);
		setTabelSimbolosLocal(tabelaLocal);
	}
	
	public int getEndereco() {
		return endereco;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}

	public int getNumParam() {
		return numParam;
	}

	public void setNumParam(int numParam) {
		this.numParam = numParam;
	}

	public TabelaDeSimbolosMetodo getTabelaSimbolosLocal() {
		return tabelSimbolosLocal;
	}

	public void setTabelSimbolosLocal(TabelaDeSimbolosMetodo tabelSimbolosLocal) {
		this.tabelSimbolosLocal = tabelSimbolosLocal;
	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}


}
