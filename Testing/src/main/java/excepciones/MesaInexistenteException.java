package excepciones;
import modelo.Mesa;

public class MesaInexistenteException extends Exception {

	public MesaInexistenteException(String mensaje) {
		super(mensaje);
	}
}

