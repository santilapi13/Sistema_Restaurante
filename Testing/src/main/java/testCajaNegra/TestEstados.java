package testCajaNegra;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import escenarios.EscenarioEstados;
import excepciones.MozoInexistenteException;
import modelo.Cerveceria;
import modelo.Estado;
import modelo.Mozo;
import modelo.Operario;

public class TestEstados {

	EscenarioEstados escenario;
	
	@Before
	public void setUp() throws Exception {
		escenario = new EscenarioEstados();
	}

	@After
	public void tearDown() throws Exception {
		Cerveceria.setInstance();
	}

	@Test
	public void SetearEstadoMozoExistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		Mozo mozo = cerveceria.getMozos().get(0);
		try {
			cerveceria.setEstado(mozo, Estado.FRANCO);
			if (mozo.getEstado() != Estado.FRANCO)
				Assert.fail("El estado no se cambio correctamente.");
		} catch (MozoInexistenteException e1) {
			Assert.fail("No deberia largar esta excepcion, mozo existe en sistema.");
			e1.printStackTrace();
		}
	
		
	}
	
	@Test
	public void SetearEstadoMozoInexistente() {
		Cerveceria cerveceria = escenario.getCerveceria();
		Mozo mozo = escenario.getMozo();
		try {
			cerveceria.setEstado(mozo, Estado.FRANCO);
            Assert.fail("Debe largar excepcion.");
            
		} catch (MozoInexistenteException e1) {
			
			e1.printStackTrace();
		}
	
		
	}
	
	

	


}
