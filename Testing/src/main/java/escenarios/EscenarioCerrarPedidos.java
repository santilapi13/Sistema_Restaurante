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
	double totalMesa1;
	double totalMesa2;

		
	public double getTotalMesa1() {
		return totalMesa1;
	}

	public double getTotalMesa2() {
		return totalMesa2;
	}

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
			PromoProducto promoHamburguesa = new PromoProducto(diasPromo, hamburguesa, true, false, 3, 180);
			cerveceria.addPromoProd(promoHamburguesa);

			
			
			//asignacion mesa, lista para pedir
			cerveceria.asignarMesa(mozo1, mesa1);  //mesa 1 asignada
			cerveceria.asignarMesa(mozo2, mesa2);
			
			
			Pedido pedido1 = new Pedido(hamburguesa, 1); 
			Pedido pedido2 = new Pedido(cerveza, 2);
			Pedido pedido3 = new Pedido(hamburguesa, 5);
			
			totalMesa1 = hamburguesa.getpVenta() + cerveza.getpVenta()*2; 
			totalMesa2 = hamburguesa.getpVenta() *5; 
			
			ArrayList<Pedido> pedidoInicialmesa = new ArrayList<Pedido>();  //pedido no apto promo
			pedidoInicialmesa.add(pedido1);
			pedidoInicialmesa.add(pedido2);
			cerveceria.tomarComanda(mesa1, pedidoInicialmesa);
			
			pedidoInicialmesa.clear();
			pedidoInicialmesa.add(pedido3);  //pedido apto promo
			cerveceria.tomarComanda(mesa2, pedidoInicialmesa);
			
			
		} catch (UsuarioRepetidoException | MesaRepetidaException | MozoRepetidoException | ProductoRepetidoException | PromoRepetidaException | ProductoInexistenteException | MozoNoDisponibleException | MozoInexistenteException | MesaNoDisponibleException | MesaInexistenteException | ProductosInvalidosException e) {
			e.printStackTrace();
		}
		
		
	}

	public Cerveceria getCerveceria() {
		return cerveceria;
	}

	
	
	
	
	
}
