package excepciones;

public class OperarioRepetidoException extends Throwable {
    public OperarioRepetidoException(String username) {
        super("El operario de username " + username + " no puede registrarse porque ya existe.");
    }
}
