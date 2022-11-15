package excepciones;
import modelo.Producto;

public class StockInsuficienteException extends Exception {
	
	public StockInsuficienteException(String mensaje) {
		super(mensaje);
	}
}
