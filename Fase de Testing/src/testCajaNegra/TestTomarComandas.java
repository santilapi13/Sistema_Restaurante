package testCajaNegra;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import escenarios.EscenarioPedidos;
import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.ProductosInvalidosException;
import modelo.Cerveceria;
import modelo.Mesa;
import modelo.Pedido;
import modelo.Producto;

public class TestTomarComandas { //No se chequea q la cantidad del prod este ok, pq todo esta dentro del pedido.

	//falta: Test mesa inexistente, testMesa no disponible
	
	EscenarioPedidos escenario;
	
	@Before
	public void setUp() throws Exception {
		escenario = new EscenarioPedidos();
	}

	@After
	public void tearDown() throws Exception {
		Cerveceria.setInstance();
	}

	@Test
	public void TestTomarPedidoMesaDisponibleProductoValido() {
	
		Cerveceria cerveceria = escenario.getCerveceria();
		Mesa mesa = cerveceria.getMesas().get(0);
		Producto prod1 = cerveceria.getProductos().get(0);
		Producto prod2 = cerveceria.getProductos().get(1);
		Pedido pedido1 = new Pedido(prod1, 1); 
		
		
		ArrayList<Pedido> pedidoInicialmesa = new ArrayList<Pedido>();
		pedidoInicialmesa.add(pedido1);
		
		
		try {
			cerveceria.tomarComanda(mesa, pedidoInicialmesa); //pedido inicial
			pedidoInicialmesa.clear();            //asi seguimos con este arraylist.
			
			Pedido pedido2 = new Pedido(prod2, 2); 
			pedidoInicialmesa.add(pedido2);
		    cerveceria.tomarComanda(mesa, pedidoInicialmesa); //se agrega otro pedido
				
		    if ( !cerveceria.getComandasAbiertas().get(0).getListaProductos().contains(pedido1) || !cerveceria.getComandasAbiertas().get(0).getListaProductos().contains(pedido2))
		    	Assert.fail("Pedidos no se tomaron correctamente.");
		    	
		} catch (MesaInexistenteException e) {
			Assert.fail("No deberia largar excepcion");
		} catch (MesaNoDisponibleException e) {
			Assert.fail("No deberia largar excepcion");
			e.printStackTrace();
		} catch (ProductosInvalidosException e) {
			Assert.fail("No deberia largar excepcion");
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void TestPedidoMesaDisponibleUnProductoNoValido() {
	
		Cerveceria cerveceria = escenario.getCerveceria();
		Mesa mesa = cerveceria.getMesas().get(0);
		Producto prod1 = escenario.getPancho();  //producto no listado
		Pedido pedido1 = new Pedido(prod1, 2); 
	
		
		ArrayList<Pedido> pedidoInicialmesa = new ArrayList<Pedido>();
		pedidoInicialmesa.add(pedido1);
		
		
		try {
			cerveceria.tomarComanda(mesa, pedidoInicialmesa);
			System.out.println(cerveceria.getProductos());
			System.out.println(cerveceria.getComandasAbiertas().get(0).getListaProductos());
			Assert.fail("Deberia largar excepcion ProductosInvalidosException");
			
		} catch (MesaInexistenteException e) {
			
			Assert.fail("No deberia largar excepcion");
		} catch (MesaNoDisponibleException e) {
			Assert.fail("No deberia largar excepcion");
			e.printStackTrace();
		} catch (ProductosInvalidosException e) {
			  //Ok
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void TestPedidoMesaDisponibleUnProductoNoValidoConUnoValido() {
	
		Cerveceria cerveceria = escenario.getCerveceria();
		Mesa mesa = cerveceria.getMesas().get(0);
		Producto prod1 = escenario.getPancho();  //producto no listado
		Producto prod2 = cerveceria.getProductos().get(0);
		Pedido pedido1 = new Pedido(prod1, 2); 
		Pedido pedido2 = new Pedido(prod2, 1); 
		
		ArrayList<Pedido> pedidoInicialmesa = new ArrayList<Pedido>();
		pedidoInicialmesa.add(pedido1);
		pedidoInicialmesa.add(pedido2);
		
		
		try {
			cerveceria.tomarComanda(mesa, pedidoInicialmesa);
			//Debe tener solo el producto valido y no el invalido
			  if ( cerveceria.getComandasAbiertas().get(0).getListaProductos().contains(pedido1) || !cerveceria.getComandasAbiertas().get(0).getListaProductos().contains(pedido2))
			    	Assert.fail("Pedidos no se tomaron correctamente.");
			  
			
		} catch (MesaInexistenteException e) {
			
			Assert.fail("No deberia largar excepcion");
		} catch (MesaNoDisponibleException e) {
			Assert.fail("No deberia largar excepcion");
			e.printStackTrace();
		} catch (ProductosInvalidosException e) {
			Assert.fail("Hay al menos un producto valido, no debe lanzar la excepcion");
			e.printStackTrace();
		}
		
		
	}
	
	
	@Test
	public void TestPedidoMesaNoDisponible() {
	
		Cerveceria cerveceria = escenario.getCerveceria();
		Mesa mesa = cerveceria.getMesas().get(1);  //mesa no asignada
		Producto prod1 = cerveceria.getProductos().get(0);
		Pedido pedido1 = new Pedido(prod1, 2); 
		
		ArrayList<Pedido> pedidoInicialmesa = new ArrayList<Pedido>();
		pedidoInicialmesa.add(pedido1);
		
		try {
			cerveceria.tomarComanda(mesa, pedidoInicialmesa);
			//Debe tener solo el producto valido y no el invalido
			Assert.fail("Debe largar excepcion MesaNoDisponibleException");
			  
			
		} catch (MesaInexistenteException e) {
			
			Assert.fail("No deberia largar esta excepcion");
		} catch (MesaNoDisponibleException e) {
			//ok
			e.printStackTrace();
		} catch (ProductosInvalidosException e) {
			Assert.fail("Hay al menos un producto valido, no debe lanzar la excepcion");
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void TestPedidoMesaInexistente() {
	
		Cerveceria cerveceria = escenario.getCerveceria();
		Mesa mesa = escenario.getMesa();  //mesa no asignada
		Producto prod1 = cerveceria.getProductos().get(0);
		Pedido pedido1 = new Pedido(prod1, 2); 
		
		ArrayList<Pedido> pedidoInicialmesa = new ArrayList<Pedido>();
		pedidoInicialmesa.add(pedido1);
		
		try {
			cerveceria.tomarComanda(mesa, pedidoInicialmesa);
			Assert.fail("Debe largar excepcion MesaInexistenteException");
			  
			
		} catch (MesaInexistenteException e) {
			
		} catch (MesaNoDisponibleException e) {
			Assert.fail("No deberia largar esta excepcion");
			e.printStackTrace();
		} catch (ProductosInvalidosException e) {
			Assert.fail("Hay al menos un producto valido, no debe lanzar la excepcion");
			e.printStackTrace();
		}
		
		
	}

}
