package excepciones;

public class MesaInexistenteException extends Throwable {
    public MesaInexistenteException(int nroMesa) {
        super("La mesa " + nroMesa + " no existe");
    }
}
