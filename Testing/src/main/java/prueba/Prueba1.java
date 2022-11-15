package prueba;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import modelo.Cerveceria;
import modelo.Comanda;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

public class Prueba1 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		try {
			Cerveceria cerveceria = Cerveceria.getInstance();
			IPersistencia<Serializable> persistencia = new PersistenciaBIN();
			Operario op1 = new Operario("Santi Sosa", "SANTISOSA", "Santi123");
			cerveceria.addOperario(op1);
			Date fecha1 = new Date(2000, 8, 17);


			Mesa mesa1 = new Mesa(4, 0);
			cerveceria.addMesa(mesa1);
			Mesa mesa2 = new Mesa(6, 1);
			cerveceria.addMesa(mesa2);
			Mesa mesa3 = new Mesa(2, 3);
			cerveceria.addMesa(mesa3);

			Mozo mozo1 = new Mozo("Santi Lapi", fecha1, 2);
			cerveceria.addMozo(mozo1);
			Mozo mozo2 = new Mozo("Wencho Avalos", fecha1, 1);
			cerveceria.addMozo(mozo2);
			Mozo mozo3 = new Mozo("Jose Gonzalez", fecha1, 3);
			cerveceria.addMozo(mozo3);

			cerveceria.asignarMesa(mozo1, mesa1);
			cerveceria.asignarMesa(mozo2, mesa2);
			cerveceria.asignarMesa(mozo3, mesa3);

			cerveceria.addMesa(new Mesa(10, 5));
			cerveceria.addMesa(new Mesa(2, 8));
			
			cerveceria.addProducto(new Producto("Papas fritas", 150, 300, 20));
			
			cerveceria.addProducto(new Producto("Pizza", 200.50, 450, 15));
			
			Producto milanesa = new Producto("Milanesa", 500, 800, 10);
			cerveceria.addProducto(milanesa);
			
			Producto hamburguesa = new Producto("Hamburguesa", 400, 1000, 10);
			cerveceria.addProducto(hamburguesa);
			
			Producto cerveza = new Producto("cerveza", 200, 400, 50);
			cerveceria.addProducto(cerveza);

			Pedido pedido1 = new Pedido(hamburguesa, 2); // 2 hamburguesas
			Pedido pedido2 = new Pedido(cerveza, 2); // 2 cervezas
			Pedido pedido3 = new Pedido(milanesa, 5);

			// YA ESTAN ASIGNADOS LOS MOZOS A LAS MESAS Y EXISTEN LOS PRODUCTOS EN LA
			// CERVECERIA

			// CREO EL PEDIDO DE LA MESA1
			ArrayList<Pedido> pedidoInicialmesa1 = new ArrayList<Pedido>();
			pedidoInicialmesa1.add(pedido1);
			pedidoInicialmesa1.add(pedido2);

			// PEDIDO POSTERIOR MESA1
			ArrayList<Pedido> pedidoPosteriormesa1 = new ArrayList<Pedido>();
			pedidoPosteriormesa1.add(pedido3);
			
			// TOMO LAS COMANDAS
			cerveceria.tomarComanda(mesa1, pedidoInicialmesa1);

			// AGREGO UN PEDIDO NUEVO A LA COMANDA DE LA MESA1
			cerveceria.tomarComanda(mesa1, pedidoPosteriormesa1);

			ArrayList<Pedido> pedidoParcialMesa1 = new ArrayList<Pedido>();

			// VERIFICO QUE SE HAYA Agregado BIEN EL PEDIDO NUEVO DE MILANESA A LA COMANDA
			// EXISTENTE
			Comanda PruebaComanda = cerveceria.getComandasAbiertas().get(0);
			pedidoParcialMesa1 = PruebaComanda.getListaProductos();

			// PROMOCIONES
			ArrayList<String> diasPromo = new ArrayList<String>();
			diasPromo.add("LUNES");
			diasPromo.add("VIERNES");

			// HAMBURGUESA EN 2X1 LUNES Y VIERNES -> VER BIEN COMO PONER
			// DTO_PORCANT_PRECIOUNIT SI NO SE USA!
			PromoProducto promoHamburguesa = new PromoProducto(diasPromo, hamburguesa, false, true, 3, 180);
			cerveceria.addPromoProd(promoHamburguesa);

			// PROMO TEMPORAL

			PromoTemporal promoViernes = new PromoTemporal(diasPromo, "Lunes y Viernes descuento", "TARJETA", 50, true);
			cerveceria.addPromoTemp(promoViernes);

			// DTO X CANTIDAD MILANESA

			PromoProducto promoPorCantMilanesa = new PromoProducto(diasPromo, milanesa, false, true, 2, 700);
			cerveceria.addPromoProd(promoPorCantMilanesa);

			// PRUEBA DE LAS PROMOS

			cerveceria.cerrarMesa(mesa1, "TARJETA");
			System.out.println(cerveceria.getVentas().get(0));
			persistencia.abrirOutput("Cerveceria.bin");
			System.out.println("Crea archivo escritura");
			CerveceriaDTO cDTO = UtilPersistencia.CerveceriaToCerveceriaDTO(cerveceria);
			persistencia.escribir(cDTO);
			System.out.println("Cerveceria grabada exitosamente");
			persistencia.cerrarOutput();
			System.out.println("Archivo cerrado");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
