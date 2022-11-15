package testCajaBlanca;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.MesaRepetidaException;
import excepciones.MozoInexistenteException;
import excepciones.MozoNoDisponibleException;
import excepciones.MozoRepetidoException;
import modelo.Cerveceria;
import modelo.Estado;
import modelo.Mesa;
import modelo.Mozo;

public class testAsignarMesaCajaBlanca {

	Date fecha1 = new Date(2000, 8, 17);
	
	@Before
	public void setUp() throws Exception {
		Cerveceria.setInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void camino1() {  //mozo inexistente
		
		 
		 Cerveceria cerveceria = Cerveceria.getInstance();
		 Mozo mozo = new Mozo("Cristiano ronaldo", fecha1, 3);
		 Mesa mesa = new Mesa(6, 1);
		 
		 try {
			cerveceria.asignarMesa(mozo, mesa);
			Assert.fail("Debe largar excepcion");
		} catch (MozoNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MozoInexistenteException e) {
			//OK
			e.printStackTrace();
		} catch (MesaNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaInexistenteException e) {
			Assert.fail();
			e.printStackTrace();
		}
		 
		 
		
	}
	
	
	@Test
	public void camino2() {  //mozo no disponible
		
		 Cerveceria.setInstance();
		 Cerveceria cerveceria = Cerveceria.getInstance();
		 Mozo mozo = new Mozo("Cristiano ronaldo", fecha1, 3);
		 Mozo mozo1 = new Mozo("Gareth Bale", fecha1, 3);
		 Mesa mesa = new Mesa(6, 1);
		 
	
		 try {
			cerveceria.addMozo(mozo);
			cerveceria.setEstado(mozo, Estado.FRANCO);
			cerveceria.asignarMesa(mozo1, mesa);
			
			Assert.fail("Debe largar excepcion");
		} catch (MozoNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MozoInexistenteException e) {
			//OK
			e.printStackTrace();
		} catch (MesaNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaInexistenteException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MozoRepetidoException e) {
			Assert.fail();
			e.printStackTrace();
		}
		 
		 
		
	}
	
	
	@Test
	public void camino3() {  //mozo no disponible
		
		Cerveceria.setInstance();
		 Cerveceria cerveceria = Cerveceria.getInstance();
		 Mozo mozo = new Mozo("Cristiano ronaldo", fecha1, 3);
		 Mesa mesa = new Mesa(6, 1);
	
		 try {
			cerveceria.addMozo(mozo);
			cerveceria.setEstado(mozo, Estado.FRANCO);
			cerveceria.asignarMesa(mozo, mesa);
			Assert.fail("Debe largar excepcion");
		} catch (MozoNoDisponibleException e) {
			//OK
			e.printStackTrace();
		} catch (MozoInexistenteException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaInexistenteException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MozoRepetidoException e) {
			Assert.fail();
			e.printStackTrace();
		}
		 	
	}
	
	@Test
	public void camino4() {  //mozo no disponible
		
		 Cerveceria.setInstance();
		 Cerveceria cerveceria = Cerveceria.getInstance();
		 Mozo mozo = new Mozo("Cristiano ronaldo", fecha1, 3);
		 Mesa mesa = new Mesa(6, 1);
	
		 try {
			cerveceria.addMozo(mozo);
			cerveceria.asignarMesa(mozo, mesa);
			Assert.fail("Debe largar excepcion");
			
		} catch (MozoNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MozoInexistenteException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaInexistenteException e) {
			//OK
			e.printStackTrace();
		} catch (MozoRepetidoException e) {
			Assert.fail();
			e.printStackTrace();
		}
		 	
	}
	
	@Test
	public void camino5() {  //mozo no disponible
		
		 Cerveceria.setInstance();
		 Cerveceria cerveceria = Cerveceria.getInstance();
		 Mozo mozo = new Mozo("Cristiano ronaldo", fecha1, 3);
		 Mesa mesa = new Mesa(6, 1);
	
		 try {
			cerveceria.addMozo(mozo);
			cerveceria.addMesa(mesa);
			cerveceria.asignarMesa(mozo, mesa);
			//OK
			
		} catch (MozoNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MozoInexistenteException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaInexistenteException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MozoRepetidoException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaRepetidaException e) {
			Assert.fail();
			e.printStackTrace();
		}
		 	
	}
	
	@Test
	public void camino6() {  //mozo no disponible
		
		 Cerveceria.setInstance();
		 Cerveceria cerveceria = Cerveceria.getInstance();
		 Mozo mozo = new Mozo("Cristiano ronaldo", fecha1, 3);
		 Mesa mesa = new Mesa(6, 1);
		 Mesa mesa1 = new Mesa(9, 3);
	
		 try {
			cerveceria.addMozo(mozo);
			cerveceria.addMesa(mesa);
			cerveceria.addMesa(mesa1);
			cerveceria.asignarMesa(mozo, mesa1);
			cerveceria.asignarMesa(mozo, mesa1);
			Assert.fail();
			
		} catch (MozoNoDisponibleException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MozoInexistenteException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaNoDisponibleException e) {
		    //OK
			e.printStackTrace();
		} catch (MesaInexistenteException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MozoRepetidoException e) {
			Assert.fail();
			e.printStackTrace();
		} catch (MesaRepetidaException e) {
			Assert.fail();
			e.printStackTrace();
		}
		 
		 
		
	}

}
