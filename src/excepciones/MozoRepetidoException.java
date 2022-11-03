package excepciones;

public class MozoRepetidoException extends Exception {
    public MozoRepetidoException(String nya) {
        super("El mozo de nombre y apellido " + nya + " no puede registrarse porque ya existe.");
    }
}
