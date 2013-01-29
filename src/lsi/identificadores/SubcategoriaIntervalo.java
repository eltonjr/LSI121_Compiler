package lsi.identificadores;

import lsi.constants.NGC;

public class SubcategoriaIntervalo implements Subcategoria {

	private String limiteInferior;
	private String limiteSuperior;
	private long tipo;
	
	public SubcategoriaIntervalo(String limiteInferior, String limiteSuperior, long tipo){
		setLimiteInferior(limiteInferior);
		setLimiteSuperior(limiteSuperior);
		setTipo(tipo);
	}
	
	public long getTipoSubCategoria() {
		return NGC.TIPO_INTERVALO;
	}

	public long getTipoVar() {
		return tipo;
	}

	public String getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(String limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public String getLimiteSuperior() {
		return limiteSuperior;
	}

	public void setLimiteSuperior(String limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}

	public void setTipo(long tipo) {
		this.tipo = tipo;
	}

}
