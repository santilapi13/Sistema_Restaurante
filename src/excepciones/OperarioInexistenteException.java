package excepciones;

public class OperarioInexistenteException extends Exception {


    public OperarioInexistenteException(String username) {
        super("El operario con username " + username + " no existe.");
    }
}
