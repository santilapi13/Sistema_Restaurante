package prueba;

import modelo.Cerveceria;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBin;
import persistencia.UtilCerveceria;

import java.io.IOException;
import java.io.Serializable;

public class PruebaLectura {

    public static void main(String[] args) {
        IPersistencia<Serializable> persistencia = new PersistenciaBin();
        Cerveceria cerveceria = Cerveceria.getInstance();
        try {
            persistencia.abrirInput("cerveceria.bin");
            System.out.println("Archivo abierto.");
            CerveceriaDTO cerveceriaDTO = (CerveceriaDTO) persistencia.leer();
            UtilCerveceria.cerveceriaDTOToCerveceria(cerveceriaDTO, cerveceria);
            System.out.println("Cerveceria recuperada.");
            persistencia.cerrarInput();
            System.out.println("Archivo cerrado");

            System.out.println("Venta: " + cerveceria.getVentas().get(0).getTotal());
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer el archivo.");
        }
    }

}

