package lsi.identificadores;

import lsi.constants.NGC;

public class SubcategoriaCadeia implements Subcategoria {

	private int tamanho;
	
	public SubcategoriaCadeia(int tamanho){
		this.setTamanho(tamanho);
	}
	
	public long getTipoSubCategoria() {
		return NGC.TIPO_CADEIA;
	}

	public long getTipoVar() {
		return NGC.TIPO_CARACTERE;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

}
