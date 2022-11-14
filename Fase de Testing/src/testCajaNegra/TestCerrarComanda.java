package testCajaNegra;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import escenarios.EscenarioCerrarPedidos;
import escenarios.EscenarioPedidos;
import excepciones.MesaSinComandaException;
import modelo.Cerveceria;
import modelo.Comanda;
import modelo.Mesa;

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

	/*@Test      //No hay informacion sobre la validacion de la forma de pago en el contrato.
	public void CerrarMesaValidaSinPromocion() {
		Cerveceria cerveceria = escenario.getCerveceria();
		
		Mesa mesa = cerveceria.getMesas().get(0);
		try {
			
			cerveceria.cerrarMesa(mesa, "TARJETA");
			String formaPago = cerveceria.getVentas().get(0).getFormaPago();
			boolean hayPromo = cerveceria.getVentas().get(0).getListaPromosAplicadas() != null;
			double total = cerveceria.getVentas().get(0).getTotal();
			
			//if (formaPago.equalsIgnoreCase("TARJETA") && )
			
			
		} catch (MesaSinComandaException e) {
			e.printStackTrace();
		}
		
	}*/
	
	@Test      //No hay informacion sobre la validacion de la forma de pago en el contrato.
	public void CerrarMesaValidaConPromociones() {
		Cerveceria cerveceria = escenario.getCerveceria();
		
		Mesa mesa = cerveceria.getMesas().get(0);
		try {
			int pos = cerveceria.getComanda(mesa);
			Comanda comandaAct = cerveceria.getComandasAbiertas().get(pos);
			cerveceria.cerrarMesa(mesa, "TARJETA");
			
			System.out.println(cerveceria.getVentas());
			
			
		} catch (MesaSinComandaException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
