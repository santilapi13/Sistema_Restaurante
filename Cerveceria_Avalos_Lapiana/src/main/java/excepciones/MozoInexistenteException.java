package excepciones;

public class MozoInexistenteException extends Exception {
    public MozoInexistenteException(String nya) {
        super("El mozo " + nya + " no existe.");
    }
}
