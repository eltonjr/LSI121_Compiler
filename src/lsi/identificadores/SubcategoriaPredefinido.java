package lsi.identificadores;

public class SubcategoriaPredefinido implements Subcategoria {

	private long tipo;
	
	public SubcategoriaPredefinido(long tipo){
		setTipo(tipo);
	}
	
	public long getTipoSubCategoria() {
		return tipo;
	}

	public long getTipoVar() {
		return tipo;
	}

	public void setTipo(long tipo) {
		this.tipo = tipo;
	}

}
