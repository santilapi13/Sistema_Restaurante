package excepciones;

public class MesaInexistenteException extends Exception {
    public MesaInexistenteException(int nroMesa) {
        super("La mesa " + nroMesa + " no existe");
    }
}
