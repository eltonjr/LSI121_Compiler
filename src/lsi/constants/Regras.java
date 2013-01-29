package lsi.constants;
import lsi.identificadores.Parametro;

public final class Regras implements NGC {
	
	public static final boolean podeArmazenar(long tipo1, long tipo2) {
		return ((tipo1 == tipo2) || (tipo1 == TIPO_REAL && tipo2 == TIPO_INT)
				|| (tipo1 == TIPO_CADEIA && tipo2 == TIPO_CARACTERE));
	}
	
	public static final boolean podeCompararLogico(long tipo1, long tipo2) {
		return (tipo1 == TIPO_BOOL && tipo2 == TIPO_BOOL);
	}
	
	public static final boolean podeCompararRelacional(long tipo1, long tipo2) {
		return ((tipo1 == tipo2) || (tipo1 == TIPO_INT && tipo2 == TIPO_REAL)
				|| (tipo1 == TIPO_REAL && tipo2 == TIPO_INT)
				|| (tipo1 == TIPO_CARACTERE && tipo2 == TIPO_CADEIA)
				|| (tipo1 == TIPO_CADEIA && tipo2 == TIPO_CARACTERE));
	}
	
	public static final boolean podeCompararAritimetico(long tipo1, long tipo2) {
		return (tipo1 == TIPO_INT && tipo2 == TIPO_REAL)
				|| (tipo1 == TIPO_REAL && tipo2 == TIPO_INT);
	}
	
	public static final long tipoResultanteDeOperacaoAritimetica(long tipo1, long tipo2, long op){
		if(tipo1 == TIPO_INT && tipo2 == TIPO_INT && op != OP_DIV){
			return TIPO_INT;
		} else {
			return TIPO_REAL;
		}
	}
	
	public static final long tipoResultanteDeOperacao(long tipo1, long tipo2, long op){
		if(op == OP_DIV || op == OP_SOMA || op == OP_SUB || op == OP_MULT){
			return Regras.tipoResultanteDeOperacaoAritimetica(tipo1, tipo2, op);
		} else {
			return TIPO_BOOL;
		}
	}


	public static final boolean isCompativelComOperador(long operando, long operador) {
		if (operador == OP_MA_IG || operador == OP_ME_IG
				|| operador == OP_MAIOR
				|| operador == OP_MENOR
				|| operador == OP_IGUAL
				|| operador == OP_DIF){
			return true;
		} else if (operador == OP_SOMA || operador == OP_SUB
					|| operador == OP_DIV
					|| operador == OP_MULT){
			return (operando == TIPO_REAL || operando == TIPO_INT);
		} else if(operador == OP_E || operador == OP_OU){
			return (operando == TIPO_BOOL);
		} else {
			System.err.println("[Regras] isCompativelComOperador : operador="+operador+" operando="+operando);
			return false;
		}
	}

	public static boolean comparaParametroDeclaradoComPassado(Parametro paramFormal, Parametro paramAtual) {
		return (Regras.podeArmazenar(paramFormal.getTipo(), paramAtual.getTipo())
				&& (paramAtual.getTipoPassagem() == -1 || paramFormal.getTipoPassagem() == paramAtual.getTipoPassagem()));
	}

}
