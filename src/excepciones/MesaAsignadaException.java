package excepciones;

public class MesaAsignadaException extends Exception {
    public MesaAsignadaException(int nroMesa) {
        super("La mesa " + nroMesa + " esta asignada por lo que no puede eliminarse.");
    }
}
