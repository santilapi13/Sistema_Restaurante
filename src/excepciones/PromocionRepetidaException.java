package excepciones;

public class PromocionRepetidaException extends Exception {
    public PromocionRepetidaException(String nombre) {
        super("La promoción " + nombre + " ya existe.");
    }
}
