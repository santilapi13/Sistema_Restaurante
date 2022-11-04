package prueba;

import modelo.*;

import java.time.DayOfWeek;

public class main {

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


                admin.agregarPromocion("Gaseosa", false, true, 3, 90);
                admin.agregarPromocion("Cerveza", true, false, 0, 0);
                admin.agregarPromocion("Happy Hour", FormaPago.EFECTIVO, 0.2, true, 19, 21);

                admin.agregarDiaPromocion(DayOfWeek.FRIDAY, "Happy Hour");
                admin.agregarDiaPromocion(DayOfWeek.FRIDAY, 1);
                admin.agregarDiaPromocion(DayOfWeek.FRIDAY, 2);

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

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

}
