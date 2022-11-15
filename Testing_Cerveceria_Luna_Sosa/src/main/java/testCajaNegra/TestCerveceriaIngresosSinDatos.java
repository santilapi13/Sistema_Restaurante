package testCajaNegra;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

public class TestCerveceriaIngresosSinDatos {

	private Cerveceria cerveceria;
	
	
	@Before
	public void setUp() throws Exception {
		this.cerveceria = Cerveceria.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		Cerveceria.setInstance();
	}

	@Test
	public void AgregarOperario() {
		Operario op1 = new Operario("operario", "operario", "123456");
		
		try {
			cerveceria.addOperario(op1);
			
			if (cerveceria.getOperario("operario") == null)
				Assert.fail("No se ingreso correctamente el operario al sistema");
			
		} catch (UsuarioRepetidoException e) {
			Assert.fail("No deberia lanzarse una excepcion de usuario repetido.");
		}
		
	}
	
	@Test
	public void AgregarMesa() {

		Mesa mesa1 = new Mesa(4, 3);
		try {
			cerveceria.addMesa(mesa1);
			if (!cerveceria.getMesas().contains(mesa1))
				Assert.fail("No se ingreso correctamente la mesa al sistema");
			
		} catch (MesaRepetidaException e) {
			Assert.fail("No deberia lanzarse una excepcion de mesa repetida.");
			
		}
		
	}
	
	
	@Test
	public void AgregarMozo() {
	
		Date fecha1 = new Date(2000, 8, 17);
		Mozo mozo1 = new Mozo("Peter Jackson", fecha1, 1);
		
		try {
			cerveceria.addMozo(mozo1);
			
			if (!cerveceria.getMozos().contains(mozo1))
				Assert.fail("No se ingreso correctamente el mozo al sistema");
			
		} catch (MozoRepetidoException e) {
	      Assert.fail("No deberia lanzarse una excepcion de mozo repetida.");
		}
		
	}
	
	@Test
	public void AgregarProducto() {
		
		Producto p1 = new Producto("Cheesecake", 400, 410, 10);
		
		try {
			cerveceria.addProducto(p1);
			if (!cerveceria.getProductos().contains(p1))
				Assert.fail("No se ingreso correctamente el mozo al sistema");
		} catch (ProductoRepetidoException e) {
		
		}
		
		
	}
	
	@Test
	public void AgregarPromoTempinexistente() {
			
	        ArrayList<String> diasPromo = new ArrayList<String>();
			diasPromo.add("LUNES");
			diasPromo.add("VIERNES");
			PromoTemporal promoLunes = new PromoTemporal(diasPromo, "Descuento", "TARJETA", 30, false);
			try {
				cerveceria.addPromoTemp(promoLunes);
				if (!cerveceria.getPromoTemporales().contains(promoLunes))
					Assert.fail("No se agrego correctamente");
			} catch (PromoRepetidaException e) {
				
				e.printStackTrace();
			}
				
			
		}
	
	
	
	@Test
	public void AgregarPromoProdConProductoListado() {
		
		
		ArrayList<String> diasPromo = new ArrayList<String>();
		diasPromo.add("LUNES");
		
		try {
			Producto hamburguesa = new Producto("Hamburguesa", 400, 1000, 10);
			cerveceria.addProducto(hamburguesa);
			PromoProducto promo = new PromoProducto(diasPromo, Cerveceria.getInstance().getProductos().get(0), true, false, 4, 190);
			cerveceria.addPromoProd(promo);
			if (!cerveceria.getPromosProducto().contains(promo))
				Assert.fail("No se ingreso correctamente el mozo al sistema");
			
		} catch (PromoRepetidaException e) {
			Assert.fail("No deberia lanzar excepcion de tipo PromoRepetidaExceptio ");
			
		} catch (ProductoInexistenteException e) {
			
			Assert.fail("No deberia lanzar excepcion de tipo ProductoInexistenteException");
		} catch (ProductoRepetidoException e) {
			
			Assert.fail("No deberia lanzar excepcion de tipo ProductoInexistenteException");
			e.printStackTrace();
		}
		
		
		}

}
