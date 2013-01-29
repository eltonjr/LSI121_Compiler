package lsi.identificadores;

import lsi.constants.NGC;

public class Variavel extends Identificador {

	private int deslocamento;
	private Subcategoria subcategoria;
	
	public Variavel(String nome, int nivel, int deslocamento, Subcategoria subcategoria) {
		super(nome, NGC.ID_VAR, nivel);
		setDeslocamento(deslocamento);
		setSubcategoria(subcategoria);
	}

	public int getDeslocamento() {
		return deslocamento;
	}

	public void setDeslocamento(int deslocamento) {
		this.deslocamento = deslocamento;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

}
