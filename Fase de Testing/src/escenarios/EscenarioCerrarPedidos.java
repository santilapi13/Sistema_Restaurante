package escenarios;

import java.util.ArrayList;
import java.util.Date;

import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.MesaRepetidaException;
import excepciones.MozoInexistenteException;
import excepciones.MozoNoDisponibleException;
import excepciones.MozoRepetidoException;
import excepciones.ProductoInexistenteException;
import excepciones.ProductoRepetidoException;
import excepciones.ProductosInvalidosException;
import excepciones.PromoRepetidaException;
import excepciones.UsuarioRepetidoException;
import modelo.Cerveceria;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;

public class EscenarioCerrarPedidos {

	Cerveceria cerveceria = Cerveceria.getInstance();
	Producto pancho =  new Producto("Pancho", 300, 2000, 40);
	Mesa mesa = new Mesa(9,3);

		
	public EscenarioCerrarPedidos() {
		 //add operaro, add mesa.
		try {
			
			//operario
			Operario op1 = new Operario("Santi Sosa", "SANTISOSA", "Santi123");
			
			cerveceria.addOperario(op1);
			//mesas
			Mesa mesa2 = new Mesa(6, 1);
			Mesa mesa1 = new Mesa(4, 0);
			
			cerveceria.addMesa(mesa1);
			cerveceria.addMesa(mesa2);
			
			//mozos
			Date fecha1 = new Date(2000, 8, 17);
			Mozo mozo1 = new Mozo("Santi Lapi", fecha1, 2);
			Mozo mozo2 = new Mozo("Wencho Avalos", fecha1, 1);
			cerveceria.addMozo(mozo1);
			cerveceria.addMozo(mozo2);
			
			//productos
			Producto hamburguesa = new Producto("Hamburguesa", 400, 1000, 10);
			cerveceria.addProducto(hamburguesa);
			Producto cerveza = new Producto("cerveza", 200, 400, 50);
			cerveceria.addProducto(cerveza);
			
			//Promociones
			ArrayList<String> diasPromo = new ArrayList<String>();
			diasPromo.add("LUNES");
			diasPromo.add("VIERNES");	
			
			//PROMO Producto
			PromoProducto promoHamburguesa = new PromoProducto(diasPromo, hamburguesa, false, true, 3, 180);
			cerveceria.addPromoProd(promoHamburguesa);

			// PROMO TEMPORAL
			PromoTemporal promoViernes = new PromoTemporal(diasPromo, "Lunes y Viernes descuento", "TARJETA", 50, true);
			cerveceria.addPromoTemp(promoViernes);
			
			//asignacion mesa, lista para pedir
			cerveceria.asignarMesa(mozo1, mesa1);  //mesa 1 asignada
			
			Producto prod1 = cerveceria.getProductos().get(0);
			Producto prod2 = cerveceria.getProductos().get(1);
			Pedido pedido1 = new Pedido(prod1, 1); 
			Pedido pedido2 = new Pedido(prod2, 2);
			
			
			ArrayList<Pedido> pedidoInicialmesa = new ArrayList<Pedido>();
			pedidoInicialmesa.add(pedido1);
			pedidoInicialmesa.add(pedido2);
			cerveceria.tomarComanda(mesa1, pedidoInicialmesa);
			
		} catch (UsuarioRepetidoException | MesaRepetidaException | MozoRepetidoException | ProductoRepetidoException | PromoRepetidaException | ProductoInexistenteException | MozoNoDisponibleException | MozoInexistenteException | MesaNoDisponibleException | MesaInexistenteException | ProductosInvalidosException e) {
			e.printStackTrace();
		}
		
		
	}

	public Cerveceria getCerveceria() {
		return cerveceria;
	}

	public Producto getPancho() {
		return pancho;
	}

	public Mesa getMesa() {
		return mesa;
	}	
	
	
	
	
	
}
