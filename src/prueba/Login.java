package prueba;

import negocio.ControladorLogin;
import excepciones.OperarioRepetidoException;
import modelo.*;

public class Login {

	public static void main(String[] args) {

		Admin admin = Cerveceria.getInstance().getAdmin();

        try {
			admin.registrarOperario("Wenceslao Avalos", "wencho8", "12345");
			admin.agregarMozo("carlos " , 0, "01/01/1990");
			admin.agregarMesa(1);
        	admin.agregarProducto("cerveza", 12, 15, 10);
        	admin.agregarPromocion("Cerveza", true, false, 0, 0);
        	admin.agregarPromocion("Happy Hour", FormaPago.EFECTIVO, 0.2, true, 19, 21);
			ControladorLogin login = ControladorLogin.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
