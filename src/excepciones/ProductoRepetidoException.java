package excepciones;

public class ProductoRepetidoException extends Throwable {
    public ProductoRepetidoException(String nombre) {
        super("El producto " + nombre + " ya existe y no puede ser agregado a la carta");
    }
}
