package lsi;

/**
 * The <code>Main</code> class only initiates the <code>Environment</code>.
 * @author 	Elton Jr"
 * 			Mauricio Branco
 */
public class Main {

	public static void main(String[] args){
		Env env = Env.getInstance();
		env.init();
	}
}
