package excepciones;

public class PromocionInexistenteException extends Exception {
    public PromocionInexistenteException(String nombre) {
        super("La promoción " + nombre + " no existe.");
    }
}
