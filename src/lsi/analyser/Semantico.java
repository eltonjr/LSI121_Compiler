package lsi.analyser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import lsi.TabelaDeSimbolos;
import lsi.TabelaDeSimbolosMetodo;
import lsi.constants.Constants;
import lsi.constants.NGC;
import lsi.constants.Regras;
import lsi.errors.SemanticError;
import lsi.i18n.I18nConstants;
import lsi.identificadores.Constante;
import lsi.identificadores.Funcao;
import lsi.identificadores.Identificador;
import lsi.identificadores.Metodo;
import lsi.identificadores.Parametro;
import lsi.identificadores.Procedimento;
import lsi.identificadores.Subcategoria;
import lsi.identificadores.SubcategoriaCadeia;
import lsi.identificadores.SubcategoriaIntervalo;
import lsi.identificadores.SubcategoriaPredefinido;
import lsi.identificadores.SubcategoriaVetor;
import lsi.identificadores.Variavel;

public class Semantico implements Constants, NGC {

	private TabelaDeSimbolos TDS = new TabelaDeSimbolos();
	private long tipoAtual;
	private Stack<Metodo> pilhaNivel = new Stack<Metodo>();
	private Stack<Identificador> pilhaIdentificadoresDeclarados = new Stack<Identificador>();
	private Stack<Metodo> pilhaProcedimentosDeclarados = new Stack<Metodo>();
	private Stack<Identificador> pilhaVariaveisDeclaradas = new Stack<Identificador>();
	private Stack<Identificador> pilhaLeituraDeclarados = new Stack<Identificador>();
	private Stack<Boolean> pilhaArmazenaExpressao = new Stack<Boolean>();
	private ArrayList<Parametro> listaParametrosFormais = new ArrayList<Parametro>();
	private ArrayList<Parametro> listaParametrosAux = new ArrayList<Parametro>();
	private Subcategoria subcategoriaAtual;
	private long contexto;
	private long mpp; // modo de passagem de parametro : referencia ou valor
	private long tipoConst;
	private String valConst;
	private long tipoConstLimInf;
	private String valConstLimInf;
	private int tamanhoVetor;
	private long tipoElementosVetor;
	private long tipoExpressao;
	private long tipoExpressaoSimples;
	private long contextoExpressao;
	private long tipoLadoEsquerdo;
	private long tipoVarIndexada;
	private long tipoTermo;
	private long tipoFator;
	private long tipoVar;
	private Stack<Integer> numParamAtuais = new Stack<Integer>();
	private long operadorAdd;
	private long operadorMult;
	private boolean operadorNegacao;
	private boolean operadorUnario;
	private boolean declarouRetorno;

	private String ultimoId;

	public void init() {
		TDS = new TabelaDeSimbolos();
		tipoAtual = -1;
		pilhaArmazenaExpressao.push(new Boolean(false));
	}

	public void executeAction(int action, Token token) throws SemanticError {
		System.out.println(I18nConstants.get("actionAction") + action + I18nConstants.get("actionToken") + token);  

		switch (action) {
		case 100:
			inserePrograma(token);
			break;
		case 101:
			verificaExistenciaDeIDSenaoCria(token, ID_CONST);
			break;
		case 102:
			verificaCompatibilidadeEntreConstanteEDeclaracao(token);
			break;
		case 103:
			contexto = LID_DECL;
			break;
		case 104:
			break;
		case 105:
			criaVariaveisDeclaradas();
			break;
		case 106:
			declaraMetodo(token, ID_PROC);
			break;
		case 107:
			criaParametrosDeclarados();
			break;
		case 108:
			saiDoProcedimento(token);
			break;
		case 109:
			declaraMetodo(token, ID_FUNC);
			break;
		case 110:
			atualizaRetornoDeFuncao(token);
			break;
		case 111:
			iniciaDeclaracaoParametrosRef(token);
			break;
		case 112:
			break;
		case 113:
			finalizaCriacaoDeParametros(token);
			break;
		case 114:
			iniciaDeclaracaoParametrosVal(token);
			break;
		case 115:
			declaraID(token);
			break;
		case 116:
			atribuiIdAConst(token);
			break;
		case 117:
			defineLimiteInferior(token);
			break;
		case 118:
			defineLimiteSuperior(token);
			break;
		case 119:
			defineTamanhoCadeia(token);
			break;
		case 120:
			defineTamanhoVetor(token);
			break;
		case 121:
			defineTipoVetor();
			break;
		case 122:
			tipoAtual = TIPO_INT;
			break;
		case 123:
			tipoAtual = TIPO_REAL;
			break;
		case 124:
			tipoAtual = TIPO_BOOL;
			break;
		case 125:
			tipoAtual = TIPO_CARACTERE;
			break;
		case 126:
			guardaPosicaoIdParaComando(token);
			break;
		case 127:
			verificaTipoExpressao(token);
			break;
		case 128:
			contexto = LID_LEITURA;
			break;
		case 129:
			verificaExpressaoParaEscrita(token);
			break;
		case 130:
			verificaSeIdPrecisaSerIndexado(token);
			break;
		case 131:
			verificaTiposCompativeis(token);
			break;
		case 132:
			verificaIdIndexado(token);
			break;
		case 133:
			verificaIndice(token);
			break;
		case 134:
			verificaSeEhProcedimento(token);
			break;
		case 135:
			inicializaPassagemParametros(token);
			break;
		case 136:
			verificaNumeroParametros(token);
			break;
		case 137:
			verificaFimDeChamadaDeMetodo(token);
			break;
		case 138:
			continuaPassandoParametros(token);
			break;
		case 139:
			tipoExpressao = tipoExpressaoSimples;
			break;
		case 140:
			verificaExpressaoRelacional(token);
			break;
		case 141:
			/* opRelacional = OP_IGUAL; */
			break;
		case 142:
			/* opRelacional = OP_MENOR; */
			break;
		case 143:
			/* opRelacional = OP_MAIOR; */
			break;
		case 144:
			/* opRelacional = OP_MA_IG; */
			break;
		case 145:
			/* opRelacional = OP_ME_IG; */
			break;
		case 146:
			/* opRelacional = OP_DIF; */
			break;
		case 147:
			tipoExpressaoSimples = tipoTermo;
			break;
		case 148:
			verificaOperadorEOperandoAdd(token);
			break;
		case 149:
			verificaTermoETipoExpSimples(token);
			break;
		case 150:
			operadorAdd = OP_SOMA;
			break;
		case 151:
			operadorAdd = OP_SUB;
			break;
		case 152:
			operadorAdd = OP_OU;
			break;
		case 153:
			tipoTermo = tipoFator;
			break;
		case 154:
			verificaOperadorEOperandoMult(token);
			break;
		case 155:
			verificaTipoFatorETipoTermo(token);
			break;
		case 156:
			operadorMult = OP_MULT;
			break;
		case 157:
			operadorMult = OP_DIV;
			break;
		case 158:
			operadorMult = OP_E;
			break;
		case 159:
			negaExpressao(token);
			break;
		case 160:
			verificaFatorComNegacao(token);
			break;
		case 161:
			negativaExpressao(token);
			break;
		case 162:
			verificaFatorComNegativo(token);
			break;
		case 163:
			limpaOperadoresUnarioENegacao(token);
			break;
		case 164:
			tipoFator = tipoExpressao;
			break;
		case 165:
			tipoFator = tipoVar;
			break;
		case 166:
			tipoFator = tipoConst;
			break;
		case 167:
			verificaSeIdEhFuncao(token);
			break;
		case 168:
			comparaNumParams(token);
			break;
		case 169:
			verificaVarIndexada(token);
			break;
		case 170:
			verificaSemanticaDeId(token);
			break;
		case 171:
			setConstantePraInt(token);
			break;
		case 172:
			setConstantePraReal(token);
			break;
		case 173:
			setConstantePraFalso();
			break;
		case 174:
			setConstantePraTrue();
			break;
		case 175:
			setConstantePraLiteral(token);
			break;
		}
	}

	// 100
	private void inserePrograma(Token token) {
		if (token != null) {
			TDS.adicionarPrograma(token.getLexeme());
		} else {
			TDS.adicionarPrograma(I18nConstants.get("begin")); 
		}
	}

	// 101
	private void verificaExistenciaDeIDSenaoCria(Token token, long tipoId) throws SemanticError {
		if (iDJaDeclaradoNoMesmoNivel(token)) {
			throw new SemanticError(I18nConstants.get("id") + token.getLexeme() + I18nConstants.get("alreadyDeclared"),  
					token.getPosition());
		} else {
			pilhaIdentificadoresDeclarados.push(new Identificador(token.getLexeme(), tipoId,
					pilhaNivel.size()));
		}
	}

	// 102
	private void verificaCompatibilidadeEntreConstanteEDeclaracao(Token token) throws SemanticError {
		Identificador ultimoDeclarado = pilhaIdentificadoresDeclarados.pop();
		if (tipoConst == tipoAtual && ultimoDeclarado.getCategoria() == ID_CONST) {
			if (tipoAtual == TIPO_CARACTERE && token.getLexeme().length() > 3) {
				throw new SemanticError(I18nConstants.get("charLongerThanAllowed"), token.getPosition()); 
			}
			getTSAtual().adicionarConstante(ultimoDeclarado.getNome(), pilhaNivel.size(),
					tipoAtual, token.getLexeme());
		} else {
			throw new SemanticError(I18nConstants.get("invalidConstType"), token.getPosition()); 
		}
	}

	// 105
	private void criaVariaveisDeclaradas() {
		int deslocamento = pilhaVariaveisDeclaradas.size();
		while (!pilhaVariaveisDeclaradas.isEmpty()) {
			Identificador ultimaDeclarada = pilhaVariaveisDeclaradas.pop();
			if (tipoAtual == TIPO_BOOL || tipoAtual == TIPO_CARACTERE || tipoAtual == TIPO_INT
					|| tipoAtual == TIPO_REAL) {
				subcategoriaAtual = new SubcategoriaPredefinido(tipoAtual);
			}
			getTSAtual().adicionarVariavel(ultimaDeclarada.getNome(), ultimaDeclarada.getNivel(),
					deslocamento--, subcategoriaAtual);
		}
	}

	// 106 e 109
	private void declaraMetodo(Token token, long tipo) throws SemanticError {
		if (iDJaDeclarado(token)) {
			throw new SemanticError(I18nConstants.get("id") + token.getLexeme() + I18nConstants.get("alreadyDeclared"),  
					token.getPosition());
		} else {
			declarouRetorno = false;
			listaParametrosFormais = new ArrayList<Parametro>();
			listaParametrosAux = new ArrayList<Parametro>();
			if (tipo == ID_FUNC) {
				Funcao func = new Funcao(token.getLexeme(), pilhaNivel.size(), 0, 0,
						new ArrayList<Parametro>(), -1, new TabelaDeSimbolosMetodo(getTSAtual()));
				getTSAtual().adicionarFuncao(func);
				pilhaNivel.push(func);
			} else if (tipo == ID_PROC) {
				Procedimento proc = new Procedimento(token.getLexeme(), pilhaNivel.size(), 0, 0,
						new ArrayList<Parametro>(), new TabelaDeSimbolosMetodo(getTSAtual()));
				getTSAtual().adicionarProcedimento(proc);
				pilhaNivel.push(proc);
			}
		}
	}

	// 107
	private void criaParametrosDeclarados() {
		pilhaNivel.peek().setNumParam(listaParametrosAux.size());
		pilhaNivel.peek().setParametros(listaParametrosAux);

		HashMap<String, Parametro> parametros = new HashMap<String, Parametro>();
		for (Parametro p : listaParametrosAux) {
			parametros.put(p.getNome(), p);
		}
		getTSAtual().adicionarParametros(parametros);
	}

	// 108
	private void saiDoProcedimento(Token token) throws SemanticError {
		if (pilhaNivel.peek().getCategoria() == ID_PROC
				|| (pilhaNivel.peek().getCategoria() == ID_FUNC && declarouRetorno)) {
			pilhaNivel.pop();
		} else {
			throw new SemanticError(I18nConstants.get("funcDoesntHasReturn"), token.getPosition()); 
		}
	}

	// 110
	private void atualizaRetornoDeFuncao(Token token) {
		Funcao func = (Funcao) pilhaNivel.peek();
		func.setTipoResultado(tipoAtual);
	}

	// 111
	private void iniciaDeclaracaoParametrosRef(Token token) {
		contexto = LID_PARFORMAL;
		mpp = TIPO_PARAM_REF;
		listaParametrosFormais = new ArrayList<Parametro>();
	}

	// 113
	private void finalizaCriacaoDeParametros(Token token) {
		for (Parametro p : listaParametrosFormais) {
			p.setTipo(tipoAtual);
		}
		listaParametrosAux.addAll(listaParametrosFormais);
	}

	// 114
	private void iniciaDeclaracaoParametrosVal(Token token) {
		contexto = LID_PARFORMAL;
		mpp = TIPO_PARAM_VALOR;
		listaParametrosFormais = new ArrayList<Parametro>();
	}

	// 115
	private void declaraID(Token token) throws SemanticError {
		if (contexto == LID_DECL) {
			if (iDJaDeclaradoNoMesmoNivel(token)) {
				throw new SemanticError(I18nConstants.get("idAlreadyDeclared"), token.getPosition()); 
			} else {
				pilhaVariaveisDeclaradas.push(new Identificador(token.getLexeme(), ID_VAR,
						pilhaNivel.size()));
			}
		} else if (contexto == LID_PARFORMAL) {
			for (Parametro current : listaParametrosFormais) {
				if (current.getNome().equals(token.getLexeme())) {
					throw new SemanticError(I18nConstants.get("idAlreadyDeclared"), token.getPosition()); 
				}
			}
			for (Parametro current : listaParametrosAux) {
				if (current.getNome().equals(token.getLexeme())) {
					throw new SemanticError(I18nConstants.get("idAlreadyDeclared"), token.getPosition()); 
				}
			}
			Parametro p = new Parametro(token.getLexeme(), pilhaNivel.size(), 0, mpp, -1);
			listaParametrosFormais.add(p);
		} else if (contexto == LID_LEITURA) {
			if (iDJaDeclarado(token)) {
				pilhaLeituraDeclarados.push(new Identificador(token.getLexeme(), ID_CONST,
						pilhaNivel.size()));
			} else {
				throw new SemanticError(I18nConstants.get("idNotDeclared"), token.getPosition()); 
			}
		}
	}

	// 116
	private void atribuiIdAConst(Token token) throws SemanticError {
		if (iDJaDeclarado(token)) {
			Identificador id = getId(token);
			if (id.getCategoria() != ID_CONST) {
				throw new SemanticError(I18nConstants.get("constIdExpected"), token.getPosition()); 
			} else {
				Constante c = (Constante) id;
				tipoConst = c.getTipo();
				valConst = c.getValor();
			}
		} else {
			throw new SemanticError(I18nConstants.get("idNotDeclared"), token.getPosition()); 
		}
	}

	// 117
	private void defineLimiteInferior(Token token) throws SemanticError {
		if (tipoConst == TIPO_CARACTERE || tipoConst == TIPO_INT) {
			tipoConstLimInf = tipoConst;
			valConstLimInf = valConst;
		} else {
			throw new SemanticError(I18nConstants.get("constShouldBeIntOrChar"), token.getPosition()); 
		}
	}

	// 118
	private void defineLimiteSuperior(Token token) throws SemanticError {
		if ((tipoConst == TIPO_CARACTERE && tipoConstLimInf == TIPO_CARACTERE && valConst.charAt(1) > valConstLimInf
				.charAt(1))
				|| (tipoConst == TIPO_INT && tipoConstLimInf == TIPO_INT && Integer
						.parseInt(valConst) > Integer.parseInt(valConstLimInf))) {
			tipoAtual = TIPO_INTERVALO;
			subcategoriaAtual = new SubcategoriaIntervalo(valConstLimInf, valConst, tipoAtual);
		} else {
			throw new SemanticError(I18nConstants.get("invalidInt"), token.getPosition()); 
		}
	}

	// 119
	private void defineTamanhoCadeia(Token token) throws SemanticError {
		if (tipoConst != TIPO_INT) {
			throw new SemanticError(I18nConstants.get("constIntExpected"), token.getPosition()); 
		} else if (Integer.parseInt(valConst) > 255) {
			throw new SemanticError(I18nConstants.get("stringLongerThanExpected"), token.getPosition()); 
		} else {
			tipoAtual = TIPO_CADEIA;
			subcategoriaAtual = new SubcategoriaCadeia(Integer.parseInt(valConst));
		}
	}

	// 120
	private void defineTamanhoVetor(Token token) throws SemanticError {
		if (tipoConst != TIPO_INT) {
			throw new SemanticError(I18nConstants.get("vectorSizeMustBeInt"), 
					token.getPosition());
		} else {
			tamanhoVetor = Integer.parseInt(valConst);
		}
	}

	// 121
	private void defineTipoVetor() {
		tipoElementosVetor = tipoAtual;
		tipoAtual = TIPO_VETOR;
		subcategoriaAtual = new SubcategoriaVetor(tamanhoVetor, tipoElementosVetor);
	}

	// 126
	private void guardaPosicaoIdParaComando(Token token) throws SemanticError {
		if (iDJaDeclarado(token)) {
			ultimoId = token.getLexeme();
		} else {
			throw new SemanticError(I18nConstants.get("idNotDeclared"), token.getPosition()); 
		}
	}

	// 127
	private void verificaTipoExpressao(Token token) throws SemanticError {
		if (tipoExpressao != TIPO_BOOL && tipoExpressao != TIPO_INT) {
			throw new SemanticError(I18nConstants.get("invalidExpressionType"), token.getPosition()); 
		}/* else { G. Codigo. } */
	}

	// 129
	private void verificaExpressaoParaEscrita(Token token) throws SemanticError {
		contextoExpressao = EXP_IMPRESSAO;
		if (tipoExpressao != TIPO_INT && tipoExpressao != TIPO_REAL
				&& tipoExpressao != TIPO_CARACTERE && tipoExpressao != TIPO_CADEIA) {
			throw new SemanticError(I18nConstants.get("invalidPrintingType"), token.getPosition()); 
		}/* else { G. Codigo. } */
	}

	// 130
	private void verificaSeIdPrecisaSerIndexado(Token token) throws SemanticError {
		Identificador id = getId(ultimoId);
		if (id.getCategoria() == ID_VAR) {
			Variavel v = (Variavel) id;
			if (v.getSubcategoria().getTipoSubCategoria() == TIPO_VETOR) {
				throw new SemanticError(I18nConstants.get("vectorMustBeIndexed"), token.getPosition()); 
			} else {
				tipoLadoEsquerdo = v.getSubcategoria().getTipoVar();
			}
		} else if (id.getCategoria() == ID_PARAM) {
			Parametro p = (Parametro) id;
			if (p.getTipo() == TIPO_VETOR) {
				throw new SemanticError(I18nConstants.get("vectorMustBeIndexed"), token.getPosition()); 
			} else {
				tipoLadoEsquerdo = p.getTipo();
			}
		} else if (id.getCategoria() == ID_FUNC) {
			Funcao f = (Funcao) id;
			if (dentroDoEscopoDaFuncao(f)) {
				tipoLadoEsquerdo = f.getTipoResultado();
				declarouRetorno = true;
			} else {
				throw new SemanticError(I18nConstants.get("outsideFunction"), token.getPosition()); 
			}
		} else {
			throw new SemanticError(I18nConstants.get("idShouldBeVarParamOrFunc"), 
					token.getPosition());
		}
	}

	// 131
	private void verificaTiposCompativeis(Token token) throws SemanticError {
		if (!Regras.podeArmazenar(tipoLadoEsquerdo, tipoExpressao)) {
			throw new SemanticError(I18nConstants.get("typeMismatch"), token.getPosition()); 
		}/* else { G. Codigo. } */
	}

	// 132
	private void verificaIdIndexado(Token token) throws SemanticError {
		if (getId(ultimoId).getCategoria() != ID_VAR) {
			throw new SemanticError(I18nConstants.get("varExpected"), token.getPosition()); 
		} else {
			Variavel v = (Variavel) getId(ultimoId);
			if (v.getSubcategoria().getTipoSubCategoria() != TIPO_VETOR
					&& v.getSubcategoria().getTipoSubCategoria() != TIPO_CADEIA) {
				throw new SemanticError(I18nConstants.get("onlyVectorsAndStringsCanBeIndexed"), 
						token.getPosition());
			} else {
				tipoVarIndexada = v.getSubcategoria().getTipoSubCategoria();
				tipoElementosVetor = v.getSubcategoria().getTipoVar();
			}
		}
	}

	// 133
	private void verificaIndice(Token token) throws SemanticError {
		if (tipoExpressao != TIPO_INT) {
			throw new SemanticError(I18nConstants.get("invalidIndexType"), token.getPosition()); 
		} else if (tipoVarIndexada == TIPO_VETOR) {
			tipoLadoEsquerdo = tipoElementosVetor;
		} else {
			tipoLadoEsquerdo = TIPO_CARACTERE;
		}
	}

	// 134
	private void verificaSeEhProcedimento(Token token) throws SemanticError {
		if (getId(ultimoId).getCategoria() != ID_PROC) {
			throw new SemanticError(I18nConstants.get("idShouldBeProc"), token.getPosition()); 
		} else {
			pilhaProcedimentosDeclarados.push((Procedimento) getId(ultimoId));
		}
	}

	// 135
	private void inicializaPassagemParametros(Token token) throws SemanticError {
		if (numParamAtuais.isEmpty()) {
			numParamAtuais.push(0);
		}
		contextoExpressao = EXP_PAR_ATUAL;
		Metodo m = pilhaProcedimentosDeclarados.peek();
		if (m.getNumParam() <= numParamAtuais.peek()) {
			throw new SemanticError(I18nConstants.get("nonExistantParam"), token.getPosition()); 
		}
		Parametro param = m.getParametros().get(numParamAtuais.peek());
		if (!Regras.podeArmazenar(param.getTipo(), tipoExpressao)) {
			throw new SemanticError(I18nConstants.get("nonExistantParam"), token.getPosition()); 
		}
	}

	// 136
	private void verificaNumeroParametros(Token token) throws SemanticError {
		Metodo m = pilhaProcedimentosDeclarados.pop();
		assert (m.getNumParam() == numParamAtuais.peek() + 1) : m.getNome() + I18nConstants.get("needs") 
				+ m.getNumParam() + I18nConstants.get("andHas") + (numParamAtuais.peek() + 1); 
		if (numParamAtuais.pop() + 1 != m.getNumParam()) {
			throw new SemanticError(I18nConstants.get("paramSizeError"), token.getPosition()); 
		} /* else { G. Codigo. } */
	}

	// 137
	private void verificaFimDeChamadaDeMetodo(Token token) throws SemanticError {
		if (getId(ultimoId).getCategoria() != ID_PROC) {
			throw new SemanticError(I18nConstants.get("idShouldBeProc"), token.getPosition()); 
		} else {
			Metodo m = (Metodo) getId(ultimoId);
			if (m.getNumParam() != 0 && numParamAtuais.pop() != m.getNumParam()) {
				throw new SemanticError(I18nConstants.get("paramSizeError"), token.getPosition()); 
			} /* else { G. Codigo. } */
		}
	}

	// 138
	private void continuaPassandoParametros(Token token) throws SemanticError {
		if (contextoExpressao == EXP_PAR_ATUAL) {
			Integer i = numParamAtuais.pop();
			numParamAtuais.push(i + 1);
			inicializaPassagemParametros(token);
		} else if (contextoExpressao == EXP_IMPRESSAO) {
			if (!iDJaDeclarado(token)) {
				throw new SemanticError(I18nConstants.get("idNotDeclared"), token.getPosition()); 
			}
		}
	}

	// 140
	private void verificaExpressaoRelacional(Token token) throws SemanticError {
		if (!Regras.podeCompararRelacional(tipoExpressao, tipoExpressaoSimples)) {
			throw new SemanticError(I18nConstants.get("operandMismatch"), token.getPosition()); 
		} else {
			tipoExpressao = TIPO_BOOL;
		}
	}

	// 148
	private void verificaOperadorEOperandoAdd(Token token) throws SemanticError {
		if (!Regras.isCompativelComOperador(tipoExpressaoSimples, operadorAdd)) {
			throw new SemanticError(I18nConstants.get("operator-operandMismatch"), token.getPosition()); 
		}
	}

	// 149
	private void verificaTermoETipoExpSimples(Token token) throws SemanticError {
		if (Regras.podeArmazenar(tipoTermo, tipoExpressaoSimples)) {
			tipoExpressaoSimples = Regras.tipoResultanteDeOperacao(tipoExpressaoSimples, tipoTermo,
					operadorAdd);
		} else {
			throw new SemanticError(I18nConstants.get("operatorMismatch"), token.getPosition()); 
		}
	}

	// 154
	private void verificaOperadorEOperandoMult(Token token) throws SemanticError {
		if (!Regras.isCompativelComOperador(tipoTermo, operadorMult)) {
			throw new SemanticError(I18nConstants.get("operator-operandMismatch"), token.getPosition()); 
		}
	}

	// 155
	private void verificaTipoFatorETipoTermo(Token token) throws SemanticError {
		if (Regras.podeArmazenar(tipoFator, tipoTermo)) {
			tipoTermo = Regras.tipoResultanteDeOperacao(tipoFator, tipoTermo, operadorMult);
		} else {
			throw new SemanticError(I18nConstants.get("operatorMismatch"), token.getPosition()); 
		}
	}

	// 159
	private void negaExpressao(Token token) throws SemanticError {
		if (operadorNegacao) {
			throw new SemanticError(I18nConstants.get("notOperatorError"), token.getPosition()); 
		} else {
			operadorNegacao = true;
		}
	}

	// 160
	private void verificaFatorComNegacao(Token token) throws SemanticError {
		if (tipoFator != TIPO_BOOL) {
			throw new SemanticError(I18nConstants.get("notOperatorRequiresBoolean"), token.getPosition()); 
		}
	}

	// 161
	private void negativaExpressao(Token token) throws SemanticError {
		if (operadorUnario) {
			throw new SemanticError(I18nConstants.get("unaryOperatorError"), token.getPosition()); 
		} else {
			operadorUnario = true;
		}
	}

	// 162
	private void verificaFatorComNegativo(Token token) throws SemanticError {
		if (tipoFator != TIPO_INT && tipoFator != TIPO_REAL) {
			throw new SemanticError(I18nConstants.get("mathSignRequiresNum"), token.getPosition()); 
		}
	}

	// 163
	private void limpaOperadoresUnarioENegacao(Token token) {
		operadorNegacao = false;
		operadorUnario = false;
	}

	// 167
	private void verificaSeIdEhFuncao(Token token) throws SemanticError {
		if (getId(ultimoId).getCategoria() != ID_FUNC) {
			throw new SemanticError(I18nConstants.get("idShouldBeFunc"), token.getPosition()); 
		} else {
			pilhaProcedimentosDeclarados.push((Funcao) getId(ultimoId));
		}
	}

	// 168
	private void comparaNumParams(Token token) throws SemanticError {
		Funcao f = (Funcao) pilhaProcedimentosDeclarados.pop();
		assert (f.getNumParam() == numParamAtuais.peek() + 1) : f.getNome() + I18nConstants.get("needs") 
				+ f.getNumParam() + I18nConstants.get("andHas") + (numParamAtuais.peek() + 1); 
		if (f.getNumParam() == numParamAtuais.pop() + 1) {
			tipoVar = f.getTipoResultado();
			/* Codigo para chamada de funcao */
		} else {
			throw new SemanticError(I18nConstants.get("paramSizeError"), token.getPosition()); 
		}
	}

	// 169
	private void verificaVarIndexada(Token token) throws SemanticError {
		if (tipoExpressao == TIPO_INT) {
			if (tipoVarIndexada == TIPO_VETOR) {
				tipoVar = tipoElementosVetor;
			} else {
				tipoVar = TIPO_CARACTERE;
			}
		} else {
			throw new SemanticError(I18nConstants.get("invalidIndexType"), token.getPosition()); 
		}
	}

	// 170
	private void verificaSemanticaDeId(Token token) throws SemanticError {
		Identificador id = getId(ultimoId);
		if (id.getCategoria() == ID_VAR) {
			Variavel v = (Variavel) id;
			if (v.getSubcategoria().getTipoSubCategoria() == TIPO_VETOR) {
				throw new SemanticError(I18nConstants.get("vectorMustBeIndexed"), token.getPosition()); 
			} else {
				tipoVar = v.getSubcategoria().getTipoVar();
			}
		} else if (id.getCategoria() == ID_PARAM) {
			Parametro p = (Parametro) id;
			if (p.getTipo() == TIPO_VETOR) {
				throw new SemanticError(I18nConstants.get("vectorMustBeIndexed"), token.getPosition()); 
			} else {
				tipoVar = p.getTipo();
			}
		} else if (id.getCategoria() == ID_FUNC) {
			Funcao f = (Funcao) id;
			if (f.getNumParam() != 0) {
				throw new SemanticError(I18nConstants.get("paramSizeError"), token.getPosition()); 
			} else {
				tipoVar = f.getTipoResultado();
				/* Código para chamada de função */
			}
		} else if (id.getCategoria() == ID_CONST) {
			Constante c = (Constante) id;
			tipoVar = c.getTipo();
		} else {
			throw new SemanticError(I18nConstants.get("varFuncOrConstExpected"), 
					token.getPosition());
		}
	}

	// 171
	private void setConstantePraInt(Token token) {
		tipoConst = TIPO_INT;
		valConst = token.getLexeme();
	}

	// 173
	private void setConstantePraFalso() {
		tipoConst = TIPO_BOOL;
		valConst = "0"; 
	}

	// 172
	private void setConstantePraReal(Token token) {
		tipoConst = TIPO_REAL;
		valConst = token.getLexeme();
	}

	// 174
	private void setConstantePraTrue() {
		tipoConst = TIPO_BOOL;
		valConst = "1"; 
	}

	// 175
	private void setConstantePraLiteral(Token token) {
		tipoConst = TIPO_CARACTERE;
		valConst = token.getLexeme();
	}

	// /////////////////////////////////////////////////////////////////////////

	private boolean dentroDoEscopoDaFuncao(Funcao f) {
		Stack<Metodo> pilhaTemp = (Stack<Metodo>) pilhaNivel.clone();
		while (!pilhaTemp.isEmpty()) {
			if (f.getNome().equals(pilhaTemp.pop().getNome())) {
				return true;
			}
		}
		return false;
	}

	private boolean iDJaDeclaradoNoMesmoNivel(Token token) {
		if (getTSAtual().verificarExistenciaIdentificador(token.getLexeme())) {
			return true;
		}

		Stack<Identificador> pilhaVarDeclaradasTemp = (Stack<Identificador>) pilhaVariaveisDeclaradas
				.clone();
		while (!pilhaVarDeclaradasTemp.isEmpty()) {
			Identificador jaDeclarado = pilhaVarDeclaradasTemp.pop();
			if (jaDeclarado.getNome().equals(token.getLexeme())) {
				return true;
			}
		}

		if (!pilhaNivel.isEmpty() && pilhaNivel.peek().getNome().equals(token.getLexeme())) {
			return true;
		}

		return false;
	}

	private boolean iDJaDeclarado(Token token) {
		Stack<Metodo> pilhaNiveisTemp = (Stack<Metodo>) pilhaNivel.clone();
		while (!pilhaNiveisTemp.isEmpty()) {
			Metodo escopo = pilhaNiveisTemp.pop();
			TabelaDeSimbolos tsAtual = escopo.getTabelaSimbolosLocal();
			if (tsAtual.verificarExistenciaIdentificador(token.getLexeme())) {
				return true;
			}
		}

		Stack<Identificador> pilhaVarDeclaradasTemp = (Stack<Identificador>) pilhaVariaveisDeclaradas
				.clone();
		while (!pilhaVarDeclaradasTemp.isEmpty()) {
			Identificador jaDeclarado = pilhaVarDeclaradasTemp.pop();
			if (jaDeclarado.getNome().equals(token.getLexeme())) {
				return true;
			}
		}

		if (TDS.verificarExistenciaIdentificador(token.getLexeme())) {
			return true;
		}
		return false;
	}

	private Identificador getId(Token token) {
		Stack<Metodo> pilhaNiveisTemp = (Stack<Metodo>) pilhaNivel.clone();
		while (!pilhaNiveisTemp.isEmpty()) {
			Metodo escopo = pilhaNiveisTemp.pop();
			TabelaDeSimbolos tsAtual = escopo.getTabelaSimbolosLocal();
			if (tsAtual.verificarExistenciaIdentificador(token.getLexeme())) {
				return tsAtual.getIdentificadorLocal(token.getLexeme());
			}
		}
		if (TDS.verificarExistenciaIdentificador(token.getLexeme())) {
			return TDS.getIdentificadorLocal(token.getLexeme());
		}
		return null;
	}

	private Identificador getId(String tokenName) {
		Stack<Metodo> pilhaNiveisTemp = (Stack<Metodo>) pilhaNivel.clone();
		while (!pilhaNiveisTemp.isEmpty()) {
			Metodo escopo = pilhaNiveisTemp.pop();
			TabelaDeSimbolos tsAtual = escopo.getTabelaSimbolosLocal();
			if (tsAtual.verificarExistenciaIdentificador(tokenName)) {
				return tsAtual.getIdentificadorLocal(tokenName);
			}
		}
		if (TDS.verificarExistenciaIdentificador(tokenName)) {
			return TDS.getIdentificadorLocal(tokenName);
		}
		return null;
	}

	public TabelaDeSimbolos getTSAtual() {
		if (pilhaNivel.size() == 0) {
			return TDS;
		} else {
			return pilhaNivel.peek().getTabelaSimbolosLocal();
		}
	}

}
