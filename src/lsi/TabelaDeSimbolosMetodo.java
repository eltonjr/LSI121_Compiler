package lsi;
import java.util.ArrayList;

import lsi.constants.Regras;
import lsi.identificadores.Identificador;
import lsi.identificadores.Parametro;


public class TabelaDeSimbolosMetodo extends TabelaDeSimbolos {
	private TabelaDeSimbolos nivelSuperior;
	private ArrayList<Parametro> parametros = new ArrayList<Parametro>();
	public int parametroIncompativel;
		
	public TabelaDeSimbolosMetodo(TabelaDeSimbolos nivelSuperior) {
		this.nivelSuperior = nivelSuperior;
		if(nivelSuperior!=null){
			this.programa = nivelSuperior.programa;
			tabelaDeSimbolos.put(nivelSuperior.programa.getNome(), nivelSuperior.programa);
		}
	}
	
	public Identificador getIdentificadorGlobal(String chave){
		if (tabelaDeSimbolos.containsKey(chave)){
			return (Identificador) tabelaDeSimbolos.get(chave);
		} else{
			if (nivelSuperior == null) {
				return null;
			} else {
				return nivelSuperior.getIdentificadorGlobal(chave);
			}
		}
	}
	
	public boolean verificarExistenciaIdentificadorGlobal(String chave){
		if (tabelaDeSimbolos.containsKey(chave)){
			return true;
		} else{
			if (nivelSuperior == null) {
				return false;
			} else {
				return nivelSuperior.verificarExistenciaIdentificador(chave);
			}
		}		
	}
	
	public void removerMetodo(String nome){
		parametros.remove(tabelaDeSimbolos.get(nome));
		tabelaDeSimbolos.remove(nome);
	}
	
	public int contaParametros(){
		return parametros.size();
	}
	
	public boolean verificaParametros(ArrayList<Parametro> parametrosAtuais){
		if (parametros.size() != parametrosAtuais.size()){
			return false;
		} else {
			for(int i = 0; i<parametros.size();i++){
				if(!(Regras.comparaParametroDeclaradoComPassado(parametros.get(i), parametrosAtuais.get(i)))){
					parametroIncompativel = i + 1;
					return false;
				}
			}
			return true;
		}
	}
	
	public boolean verificaParametro(Parametro param, int deslocamento){
		return (Regras.comparaParametroDeclaradoComPassado(parametros.get(deslocamento), param));
	}
	
}
