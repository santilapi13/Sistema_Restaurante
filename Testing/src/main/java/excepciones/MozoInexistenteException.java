package excepciones;

import modelo.Mozo;

public class MozoInexistenteException extends Exception {
	
	public MozoInexistenteException(String mensaje) {
		super(mensaje);
	}
}
