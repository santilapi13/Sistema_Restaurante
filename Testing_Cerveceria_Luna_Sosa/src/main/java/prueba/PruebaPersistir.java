package prueba;

import modelo.Cerveceria;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

import java.io.IOException;
import java.io.Serializable;

public class PruebaPersistir {

    public static void main(String[] args) {
        Cerveceria cerveceria = Cerveceria.getInstance();
        IPersistencia<Serializable> persistencia = new PersistenciaBIN();
        try {
            persistencia.abrirOutput("Cerveceria.bin");
            System.out.println("Crea archivo escritura");
            CerveceriaDTO cDTO = UtilPersistencia.CerveceriaToCerveceriaDTO(cerveceria);
            persistencia.escribir(cDTO);
            System.out.println("Cerveceria grabada exitosamente");
            persistencia.cerrarOutput();
            System.out.println("Archivo cerrado");
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
