package excepciones;

public class UsuarioRepetidoException extends Exception {
	
	public UsuarioRepetidoException(String mensaje) {
		super(mensaje);
	}
}
