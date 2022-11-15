package escenarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import excepciones.MesaRepetidaException;
import excepciones.MozoRepetidoException;
import excepciones.ProductoInexistenteException;
import excepciones.ProductoRepetidoException;
import excepciones.PromoRepetidaException;
import excepciones.UsuarioRepetidoException;
import modelo.Cerveceria;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;

public class CerveceriaConDatos {
	
Cerveceria cerveceria = Cerveceria.getInstance();
Producto pancho =  new Producto("Pancho", 300, 2000, 40);
Operario op = new Operario("Pedro salazar", "Peter", "peter123");
Mesa mesa = new Mesa(9,3);

	
public CerveceriaConDatos() {
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
		
		//promociones
		ArrayList<String> diasPromo = new ArrayList<String>();
		diasPromo.add("LUNES");
		diasPromo.add("VIERNES");
		//promo prod
		PromoProducto promoHamburguesa = new PromoProducto(diasPromo, hamburguesa, false, true, 3, 180);
		cerveceria.addPromoProd(promoHamburguesa);
		//prmo temp
		PromoTemporal promoViernes = new PromoTemporal(diasPromo, "Lunes y Viernes descuento", "TARJETA", 50, true);
		cerveceria.addPromoTemp(promoViernes);
		
	} catch (UsuarioRepetidoException | MesaRepetidaException | MozoRepetidoException | ProductoRepetidoException | PromoRepetidaException | ProductoInexistenteException e) {
		e.printStackTrace();
	}
	
	
}

public Cerveceria getCerveceria() {
	return cerveceria;
}

public Producto getPancho() {
	return pancho;
}

public Operario getOp() {
	return op;
}

public Mesa getMesa() {
	return mesa;
}





}

