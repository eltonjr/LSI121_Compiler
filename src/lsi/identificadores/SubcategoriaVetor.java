package lsi.identificadores;

import lsi.constants.NGC;

public class SubcategoriaVetor implements Subcategoria {

	private int tamanho;
	private long tipo;
	
	public SubcategoriaVetor(int tamanho, long tipo){
		setTamanho(tamanho);
		setTipo(tipo);
	}
	
	public long getTipoSubCategoria() {
		return NGC.TIPO_VETOR;
	}

	public long getTipoVar() {
		return tipo;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public void setTipo(long tipo){
		this.tipo = tipo;
	}

}
