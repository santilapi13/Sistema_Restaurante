package prueba;

import java.io.IOException;
import java.io.Serializable;

import controladores.ControladorLogin;
import modelo.Cerveceria;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

public class pruebaVentanas {
	public static void main(String[] args) {
		IPersistencia<Serializable> persistencia = new PersistenciaBIN();
		Cerveceria cerveceria = Cerveceria.getInstance();
		try {
			persistencia.abrirInput("Cerveceria.bin");
			// System.out.println("Archivo abierto");
			CerveceriaDTO cdto = (CerveceriaDTO) persistencia.leer();
			UtilPersistencia.CerveceriaDTOToCerveceria(cdto, cerveceria);
			// System.out.println("Cerveceria recuperada");
			persistencia.cerrarInput();
			// System.out.println("Archivo cerrado");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			ControladorLogin.getInstance();
		}
	}

}
