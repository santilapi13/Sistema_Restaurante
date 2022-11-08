package prueba;

import modelo.*;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBin;
import persistencia.UtilCerveceria;

import java.io.IOException;
import java.io.Serializable;
import java.time.DayOfWeek;

public class PruebaEscritura {

    public static void main(String[] args) {
        try {
            Admin admin = Cerveceria.getInstance().getAdmin();
            admin.registrarOperario("Wenceslao Avalos", "wencho8", "12345");
            Operario op1 = Cerveceria.getInstance().getOperarios().get(0);

            for (int i = 0; i < 6; i++) {
                admin.agregarMozo("Mozo " + i, 0, "01/01/1990");
            }
            op1.setEstado("Mozo 1", EstadoMozo.FRANCO);
            op1.setEstado("Mozo 2", EstadoMozo.FRANCO);
            op1.setEstado("Mozo 3", EstadoMozo.ACTIVO);
            op1.setEstado("Mozo 4", EstadoMozo.ACTIVO);
            op1.setEstado("Mozo 5", EstadoMozo.ACTIVO);

            admin.agregarProducto("Cerveza", 100, 125, 300);
            admin.agregarProducto("Gaseosa", 80, 100, 300);


            admin.agregarMesa(1);
            admin.agregarMesa(6);
            admin.agregarMesa(4);

            op1.asignarMesa("Mozo 3", 1);
            op1.asignarMesa("Mozo 3", 2);
            op1.asignarMesa("Mozo 4", 0);

            op1.tomarComanda(1, "Cerveza", 2);
            op1.tomarComanda(1, "Gaseosa", 4);

            op1.cerrarMesa(1, FormaPago.EFECTIVO);

            System.out.println(Cerveceria.getInstance().getVentas().get(0).getTotal());

            IPersistencia<Serializable> persistencia = new PersistenciaBin();
            persistencia.abrirOutput("cerveceria.bin");
            System.out.println("Crea archivo escritura");
            CerveceriaDTO cerveceriaDTO = UtilCerveceria.cerveceriaToCerveceriaDTO(Cerveceria.getInstance());
            persistencia.escribir(cerveceriaDTO);
            System.out.println("Escribe archivo");
            persistencia.cerrarOutput();
            System.out.println("Cierra archivo escritura");

        } catch (IOException e) {
            System.out.println("Error al abrir el archivo.");
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}

