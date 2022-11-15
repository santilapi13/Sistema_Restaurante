package excepciones;

public class MozoNoDisponibleException extends Exception {
    public MozoNoDisponibleException(String nya) {
        super("El mozo " + nya + " no se encuentra disponible.");
    }
}
