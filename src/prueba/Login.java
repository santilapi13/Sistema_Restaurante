package prueba;

import negocio.ControladorLogin;
import excepciones.OperarioRepetidoException;
import modelo.*;

import java.io.IOException;

public class Login {

	public static void main(String[] args) {

		Admin admin = Cerveceria.getInstance().getAdmin();

        try {
			Cerveceria.getInstance().cargarCerveceria();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			ControladorLogin login = ControladorLogin.getInstance();
		}

	}

}
