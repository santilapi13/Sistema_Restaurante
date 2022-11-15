package excepciones;

public class PromocionRepetidaException extends Exception {
    public PromocionRepetidaException(String nombre) {
        super("La promocion " + nombre + " ya existe.");
    }
}
