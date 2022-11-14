package testCajaNegra;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import escenarios.EscenarioCerrarPedidos;
import escenarios.EscenarioPedidos;
import excepciones.MesaSinComandaException;
import excepciones.ProductoEnComandaException;
import excepciones.ProductoInexistenteException;
import excepciones.PromoRepetidaException;
import modelo.Cerveceria;
import modelo.Comanda;
import modelo.Mesa;
import modelo.Producto;
import modelo.PromoTemporal;

public class TestCerrarComanda {

	EscenarioCerrarPedidos escenario;
	
	@Before
	public void setUp() throws Exception {
		escenario = new EscenarioCerrarPedidos();
	}

	@After
	public void tearDown() throws Exception {
		Cerveceria.setInstance();
	}

	@Test      
	public void CerrarMesaValidaSinPromocion() {
		Cerveceria cerveceria = escenario.getCerveceria();
		
		Mesa mesa = cerveceria.getMesas().get(0); //mesa con comanda valida, sin promocion.
		try {
			
			cerveceria.cerrarMesa(mesa, "TARJETA");
			String formaPago = cerveceria.getVentas().get(0).getFormaPago();
			boolean hayPromo = cerveceria.getVentas().get(0).getListaPromosAplicadas().size() != 0;
			double total = cerveceria.getVentas().get(0).getTotal();
			
			
			if (!(formaPago.equalsIgnoreCase("TARJETA") && (hayPromo == false) && (total == escenario.getTotalMesa1())))
				Assert.fail("No se cerro bien la comanda, la venta esta mal creada.");
			
			
		} catch (MesaSinComandaException e) {
			Assert.fail("No debe largar excepcion");
		}
		
	}
	
	@Test      
	public void CerrarMesaValidaConPromocionProducto() {
		Cerveceria cerveceria = escenario.getCerveceria();
		
		Mesa mesa = cerveceria.getMesas().get(1); //mesa con comanda valida promocion producto y temporal.
		try {
			
			cerveceria.cerrarMesa(mesa, "TARJETA");
			String formaPago = cerveceria.getVentas().get(0).getFormaPago();
			boolean hayPromo = cerveceria.getVentas().get(0).getListaPromosAplicadas().size() != 0;
			double total = cerveceria.getVentas().get(0).getTotal();
			
			if (!(formaPago.equalsIgnoreCase("TARJETA") && (hayPromo == true ) && (total == (escenario.getTotalMesa2()/2))))
				Assert.fail("No se cerro bien la comanda, la venta esta mal creada.");
			
			
			
			
		} catch (MesaSinComandaException e) {
			Assert.fail("No debe largar excepcion");
		}
		
	}
	
	@Test     
	public void CerrarMesaValidaConPromocionTemporal() {
        Cerveceria cerveceria = escenario.getCerveceria();
		
		Mesa mesa = cerveceria.getMesas().get(0);
		try {
			
			// PROMO TEMPORAL
			ArrayList<String> diasPromo = new ArrayList<String>();
			diasPromo.add("LUNES");
			diasPromo.add("VIERNES");
		    PromoTemporal promoViernes = new PromoTemporal(diasPromo, "Lunes y Viernes descuento", "TARJETA", 50, true);
			cerveceria.addPromoTemp(promoViernes);
			
			cerveceria.cerrarMesa(mesa, "TARJETA");
			String formaPago = cerveceria.getVentas().get(0).getFormaPago();
			boolean hayPromo = cerveceria.getVentas().get(0).getListaPromosAplicadas().size() != 0;
			double total = cerveceria.getVentas().get(0).getTotal();
			
	
			if (!(formaPago.equalsIgnoreCase("TARJETA") && (hayPromo == true) && (total == (escenario.getTotalMesa1()/2))))
				Assert.fail("No se cerro bien la comanda, la venta esta mal creada.");
			
			
		} catch (MesaSinComandaException   e) {
			Assert.fail("No debe largar excepcion");
		} catch (PromoRepetidaException e) {
			Assert.fail("No debe largar excepcion");
			e.printStackTrace();
		}
		
	}
	
	
	@Test      
	public void CerrarMesaInvalida() {
		Cerveceria cerveceria = escenario.getCerveceria();
		
		Mesa mesa = cerveceria.getMesas().get(0); //mesa con comanda valida, sin promocion.
		try {
			
			cerveceria.cerrarMesa(mesa, "TARJETA");
			
			//se vuelve a cerrar
			cerveceria.cerrarMesa(mesa, "TARJETA");
			Assert.fail("Debe largar excepcion");
			
		} catch (MesaSinComandaException e) {
			//Ok
		}
		
	}
	
	
	@Test      
	public void EliminarProductoEnComandaAbierta() {
		Cerveceria cerveceria = escenario.getCerveceria();
		Producto prod = cerveceria.getProductos().get(0);
		
		
			try {
				cerveceria.deleteProducto(prod);
				Assert.fail("No puede eliminarse, esta en una comanda aun");
				
			} catch (ProductoEnComandaException e) {
				
				e.printStackTrace();
			} catch (ProductoInexistenteException e) {
				Assert.fail("No debe largar esta excepcion, ya testeada.");
				e.printStackTrace();
			}

			
		
		
	}
	

}
