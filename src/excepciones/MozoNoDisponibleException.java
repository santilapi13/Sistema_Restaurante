package excepciones;

public class MozoNoDisponibleException extends Throwable {
    public MozoNoDisponibleException(String nya) {
        super("El mozo " + nya + " no se encuentra disponible.");
    }
}
