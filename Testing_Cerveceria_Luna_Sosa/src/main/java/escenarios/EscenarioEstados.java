package escenarios;

import java.util.ArrayList;
import java.util.Date;

import excepciones.MesaRepetidaException;
import excepciones.MozoInexistenteException;
import excepciones.MozoRepetidoException;
import excepciones.ProductoInexistenteException;
import excepciones.ProductoRepetidoException;
import excepciones.PromoRepetidaException;
import excepciones.UsuarioRepetidoException;
import modelo.Cerveceria;
import modelo.Estado;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;

public class EscenarioEstados {

Cerveceria cerveceria = Cerveceria.getInstance();	

private Mozo mozo;


public EscenarioEstados() {
	 //add operaro, add mesa.
	try {
		
        Operario op1 = new Operario("Santi Sosa", "SANTISOSA", "Santi123");
		cerveceria.addOperario(op1);
		
		//mozos
		Date fecha1 = new Date(2000, 8, 17);
		Mozo mozo1 = new Mozo("Santi Lapi", fecha1, 2);
		Mozo mozo2 = new Mozo("Wencho Avalos", fecha1, 1);
		Mozo mozo3 = new Mozo("Guillermo", fecha1, 1);
		mozo = new Mozo("Cristiano ronaldo", fecha1, 3);
		cerveceria.addMozo(mozo1);
		cerveceria.addMozo(mozo2);
		cerveceria.addMozo(mozo3);
		cerveceria.setEstado(mozo2, Estado.FRANCO);
		
		
		Mesa mesa2 = new Mesa(6, 1);
		Mesa mesa1 = new Mesa(4, 0);
		
		cerveceria.addMesa(mesa1);
		cerveceria.addMesa(mesa2);
		
		
	} catch ( MozoRepetidoException | MozoInexistenteException | UsuarioRepetidoException | MesaRepetidaException e) {
		e.printStackTrace();
	}
	
	
}

public Cerveceria getCerveceria() {
	return cerveceria;
}

public Mozo getMozo() {
	return mozo;
}








}
