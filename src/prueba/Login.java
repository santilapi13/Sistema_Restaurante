package prueba;

import negocio.ControladorLogin;
import excepciones.OperarioRepetidoException;
import modelo.*;

public class Login {

	public static void main(String[] args) {

		Admin admin = Cerveceria.getInstance().getAdmin();

        try {
			//admin.registrarOperario("Wenceslao Avalos", "wencho8", "12345");
			ControladorLogin login = ControladorLogin.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
