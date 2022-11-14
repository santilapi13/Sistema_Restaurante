package testCajaNegra;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import escenarios.EscenarioEstados;
import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.MozoInexistenteException;
import excepciones.MozoNoDisponibleException;
import modelo.*;

public class TestAsignacionMesaMozo {

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
	public void AsignarMesaLibreMozoDisponible() {	
	 try {
	
		 Cerveceria cerveceria = escenario.getCerveceria();
		 Mozo mozo = cerveceria.getMozos().get(0);
		 Mesa mesa = cerveceria.getMesas().get(0);
		 
		 cerveceria.asignarMesa(mozo,mesa );
		  if (!mozo.mesas.contains(mesa))
			   Assert.fail("Error al asignar mesa");
		 
		} catch (MozoNoDisponibleException e) {
			Assert.fail("Mozo disponible");
		} catch (MozoInexistenteException e) {
			Assert.fail("Mozo existe");
		} catch (MesaNoDisponibleException e) {
			Assert.fail("Mesa disponible");
		} catch (MesaInexistenteException e) {
			Assert.fail("Mesa existe");
		}
	 
	 }
    
    @Test  
   	public void AsignarMesaNoLibreMozoDisponible() {	
   	 try {
   	
   		 Cerveceria cerveceria = escenario.getCerveceria();
   		 Mozo mozo = cerveceria.getMozos().get(0);
   		 Mesa mesa = cerveceria.getMesas().get(0);
   		 Mozo mozo1 = cerveceria.getMozos().get(2);
   		 
   		 cerveceria.asignarMesa(mozo,mesa );
   		 cerveceria.asignarMesa(mozo1, mesa);
   		 Assert.fail("Debe largar excepcion mesa no disponible");
   		
   		} catch (MozoNoDisponibleException e) {
   			Assert.fail("Mozo disponible");
   		} catch (MozoInexistenteException e) {
   			Assert.fail("Mozo existe");
   		} catch (MesaNoDisponibleException e) {
   			//ok
   		} catch (MesaInexistenteException e) {
   			Assert.fail("Mesa existe");
   		}
   	 
   	 }
    
    @Test  
   	public void AsignarMesaLibreMozoNoDisponible() {	
   	 try {
   	
   		 Cerveceria cerveceria = escenario.getCerveceria();
   		 Mozo mozo = cerveceria.getMozos().get(1);
   		 Mesa mesa = cerveceria.getMesas().get(0);
   		 
   		 
   		 cerveceria.asignarMesa(mozo,mesa );
   		 Assert.fail("Debe largar excepcion mozo no disponible");
   		
   		} catch (MozoNoDisponibleException e) {
   			
   		} catch (MozoInexistenteException e) {
   			Assert.fail("Mozo existe");
   		} catch (MesaNoDisponibleException e) {
   			Assert.fail("Mesa disponible");
   		} catch (MesaInexistenteException e) {
   			Assert.fail("Mesa existe");
   		}
   	 
   	 }
    
    


}

	
	


