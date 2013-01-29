package lsi.constants;

/**
 * Non-Gals Constants
 * @author elton
 */
public interface NGC {

	long ID_PROG = 0;
	long ID_CONST = 1;
	long ID_VAR = 2;
	long ID_PROC = 3;
	long ID_FUNC = 4;
	long ID_PARAM = 5;
	
	long TIPO_INT = 6;
	long TIPO_REAL = 7;
	long TIPO_CARACTERE = 8;
	long TIPO_BOOL = 9;
	
	long TIPO_CADEIA = 10;
	long TIPO_VETOR = 11;
	long TIPO_INTERVALO = 12;
	
	long SUBCAT_PREDEFINIDO = 13;
	long SUBCAT_CADEIA = 14;
	long SUBCAT_VETOR = 15;
	long SUBCAT_INTERVALO = 16;
	
	long TIPO_PARAM_VALOR = 17;
	long TIPO_PARAM_REF = 18;
	
	long LID_DECL = 19;
	long LID_PARFORMAL = 20;
	long LID_LEITURA = 21;
	
	long OP_SOMA = 22;
	long OP_SUB = 23;
	long OP_MULT = 24;
	long OP_DIV = 25;
	long OP_IGUAL = 26;
	long OP_DIF = 27;
	long OP_MAIOR = 28;
	long OP_MENOR = 29;
	long OP_MA_IG = 30;
	long OP_ME_IG = 31;
	long OP_E = 32;
	long OP_OU = 33;
	
	long EXP_IMPRESSAO = 34;
	long EXP_PAR_ATUAL = 35;
	
}
