package testIntegracion;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.MozoInexistenteException;
import excepciones.MozoRepetidoException;
import modelo.Cerveceria;
import modelo.Comanda;
import modelo.Estado;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import modelo.PromoTemporal;

public class TestOperario {
	Cerveceria cerveceria;
	Mozo mozo = new Mozo("Santiago Lapiana", new Date(26, 4, 2002), 0);
	Operario op = new Operario("Wenceslao Avalos", "wencho8", "12345");
	Mesa mesa = new Mesa(5, 0);
	
	@Before
	public void setUp() throws Exception {
		Cerveceria.setInstance();
		cerveceria = Cerveceria.getInstance();
	}
	
	@Test
	public void testSetEstado() {
		try {
			cerveceria.addMozo(mozo);	// Se asegura de que el mozo esta para solo testear setEstado()
			op.setEstado(mozo, Estado.ACTIVO);
			Assert.assertEquals("El estado deberia cambiar a activo.", cerveceria.getMozos().get(0).getEstado(), Estado.ACTIVO);
		} catch (MozoRepetidoException e) {
			Assert.fail("No deberia haber una excepcion.");
		} catch (MozoInexistenteException e) {
			Assert.fail("No deberia haber una excepcion.");
		}
	}
	
	@Test
	public void testAsignarMesa() {
		try {
			mozo.setEstado(Estado.ACTIVO);	// Da las condiciones para que efectivamente se pueda
			mesa.setAsignado(false);
			mesa.setEstado("LIBRE");
			cerveceria.addMozo(mozo);
			cerveceria.addMesa(mesa);
			
			op.asignarMesa(mozo,  mesa);
			
			Assert.assertTrue("La mesa deberia tener su atributo asignado en true", cerveceria.getMesas().get(0).isAsignado());
			Assert.assertEquals("La mesa deberia seguir estando libre", cerveceria.getMesas().get(0).getEstado(), "LIBRE");
			Assert.assertEquals("El mozo deberia tener solo una mesa asignada.", cerveceria.getMozos().get(0).getMesas().size(), 1);
			Assert.assertEquals("El numero de mesa asignada al mozo debe coincidir con el que se le asigno", mesa.getNroMesa(), cerveceria.getMozos().get(0).getMesas().get(0).getNroMesa());
		} catch (Exception e1) {
			Assert.fail("No deberia haber una excepcion.");
		}
	}
	
	@Test
	public void testTomarComanda() {
		try {
			mesa.setAsignado(true);
			mesa.setEstado("LIBRE");
			cerveceria.addMesa(mesa);
			Producto producto = new Producto("Cerveza", 100, 150, 10);
			cerveceria.addProducto(producto);
			ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
			Pedido pedido = new Pedido(producto, 3);
			pedidos.add(pedido);
			
			op.tomarPedido(mesa,  pedidos);
			
			Assert.assertEquals("El estado de la mesa deberia ser OCUPADA", cerveceria.getMesas().get(0).getEstado(), "OCUPADA");
			Assert.assertEquals("Deberia haber una unica comanda abierta.", cerveceria.getComandasAbiertas().size(), 1);
			Assert.assertEquals("Deberia haber un unico pedido en la comanda abierta", cerveceria.getComandasAbiertas().get(0).getListaProductos().size(), 1);
			pedido = cerveceria.getComandasAbiertas().get(0).getListaProductos().get(0);
			Assert.assertTrue("El pedido deberia componerse de 3 cervezas.", pedido.getProducto().getNombre().equalsIgnoreCase("Cerveza") && pedido.getCantidad() == 3);
			
		} catch (Exception e) {
			Assert.fail("No deberia haber una excepcion.");
		}
	}
	
	@Test
	public void testCerrarMesa() {
		try {
			mesa.setAsignado(true);
			mesa.setEstado("OCUPADA");
			cerveceria.addMesa(mesa);
			mozo.setEstado(Estado.ACTIVO);
			mozo.getMesas().add(mesa);
			cerveceria.addMozo(mozo);
			ArrayList<String> dias = new ArrayList<String>();
			dias.add("LUNES");
			dias.add("MARTES");
			dias.add("MIERCOLES");
			dias.add("JUEVES");
			dias.add("VIERNES");
			dias.add("SABADO");
			dias.add("DOMINGO");
			PromoTemporal promoT = new PromoTemporal(dias, "Happy Hour", "EFECTIVO", 30, false);	// Para que realice el 30% de descuento hay que poner que el porcentaje de descuento es 70%.
			cerveceria.addPromoTemp(promoT);
			Producto producto = new Producto("Cerveza", 100, 150, 10);
			cerveceria.addProducto(producto);
			ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
			Pedido pedido = new Pedido(producto, 3);
			pedidos.add(pedido);
			Comanda comanda = new Comanda(mesa, pedidos);
			cerveceria.getComandasAbiertas().add(comanda);
			
			op.cerrarMesa(mesa,  "EFECTIVO");
			System.out.println(cerveceria.getMozos().get(0).getTotalVentas());
			Assert.assertEquals("El mozo deberia tener una sola venta.", cerveceria.getMozos().get(0).getCantVentas(), 1);
			Assert.assertEquals("Deberia haber una unica venta en la cerveceria.", cerveceria.getVentas().size(), 1);
			Assert.assertTrue("El total de la venta deberia ser 315.", cerveceria.getVentas().get(0).getTotal() == 315);
			Assert.assertTrue("El mozo deberia tener $315 en su total.", cerveceria.getMozos().get(0).getTotalVentas() == 315); // 315 es el resultadod e restar el 30% a las 3 cervezas
			
		} catch (Exception e) {
			Assert.fail("No deberia haber una excepcion.");
		}
	}
	

}
