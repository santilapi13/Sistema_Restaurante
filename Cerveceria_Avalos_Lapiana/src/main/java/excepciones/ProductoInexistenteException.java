package excepciones;

public class ProductoInexistenteException extends Exception {

    public ProductoInexistenteException(String nombre) {
        super("El producto " + nombre + " no existe.");
    }

}
