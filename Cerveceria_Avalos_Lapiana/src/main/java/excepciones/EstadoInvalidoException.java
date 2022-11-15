package excepciones;

import modelo.EstadoMozo;

public class EstadoInvalidoException extends Exception {
    public EstadoInvalidoException(String nya, EstadoMozo estado) {
        super("El estado " + estado + " no es valido para el mozo " + nya + " porque tiene al menos una mesa asignada.");
    }
}
