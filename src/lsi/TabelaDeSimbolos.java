package lsi;
import java.util.HashMap;

import lsi.identificadores.Constante;
import lsi.identificadores.Funcao;
import lsi.identificadores.Identificador;
import lsi.identificadores.Procedimento;
import lsi.identificadores.Programa;
import lsi.identificadores.Subcategoria;
import lsi.identificadores.Variavel;


public class TabelaDeSimbolos {
	protected HashMap<String, Identificador> tabelaDeSimbolos = new HashMap<String, Identificador>();
	protected Programa programa;
	
	public void adicionarPrograma(String nome){
		programa = new Programa(nome);
		tabelaDeSimbolos.put(nome, programa);
	}
	
	public void adicionarConstante(String nome, int nivel, long tipo, String valor){
		tabelaDeSimbolos.put(nome, new Constante(nome, nivel, tipo, valor));
	}

	public void adicionarVariavel(String nome, int nivel, int deslocamento, Subcategoria subCat){
		tabelaDeSimbolos.put(nome, new Variavel(nome, nivel, deslocamento, subCat));
	}

	public void adicionarProcedimento(Procedimento procedimento){
		tabelaDeSimbolos.put(procedimento.getNome(), procedimento);
	}
	
	public void adicionarFuncao(Funcao funcao){
		tabelaDeSimbolos.put(funcao.getNome(), funcao);
	}
	
	public void adicionarParametros(HashMap parametros){
		tabelaDeSimbolos.putAll(parametros);
	}

	public boolean verificarExistenciaIdentificador(String chave){
		return tabelaDeSimbolos.containsKey(chave);
	}
	
	public Identificador getIdentificadorLocal(String chave){
		return tabelaDeSimbolos.get(chave);
	}
	
	public Identificador getIdentificadorGlobal(String chave){
		if (tabelaDeSimbolos.containsKey(chave)){
			return tabelaDeSimbolos.get(chave);
		} else{
			return null;
		}
	}
	
}

