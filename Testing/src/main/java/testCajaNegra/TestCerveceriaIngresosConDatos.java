package testCajaNegra;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import escenarios.CerveceriaConDatos;
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

public class TestCerveceriaIngresosConDatos {

	CerveceriaConDatos cerveceriaConDatos;
	
	@Before
	public void setUp() throws Exception {
		cerveceriaConDatos = new CerveceriaConDatos();
	}

	@After
	public void tearDown() throws Exception {
		Cerveceria.setInstance();
	}

	
	@Test
	public void AgregarOperarioInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
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
	public void AgregarOperarioExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Operario op1 = new Operario("Santi Sosa", "SANTISOSA", "Santi123");
		try {
			cerveceria.addOperario(op1);
			Assert.fail("Deberia lanzarse una excepcion de tipo UsuarioRepetidoException");
			
		} catch (UsuarioRepetidoException e) {
			//OK
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void AgregarMesaInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
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
	public void AgregarMesaExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Mesa mesa1 = new Mesa(4, 0);
		
		try {
			cerveceria.addMesa(mesa1);
			Assert.fail("Deberia lanzarse una excepcion de tipo MesaRepetidaException");
			
		} catch (MesaRepetidaException e) {
			
		}
		
	}
	
	@Test
	public void AgregarMozoInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
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
	public void AgregarMozoExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Date fecha1 = new Date(2000, 8, 17);
		Mozo mozo1 = new Mozo("Santi Lapi", fecha1, 2);
		try {
			cerveceria.addMozo(mozo1);
			Assert.fail("Deberia lanzarse excepcion de tipo mozoRepetidoException");
		} catch (MozoRepetidoException e) {
	
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void AgregarProductoInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Producto p1 = new Producto("Cheesecake", 400, 410, 10);
		
		try {
			cerveceria.addProducto(p1);
			if (!cerveceria.getProductos().contains(p1))
				Assert.fail("No se ingreso correctamente el producto al sistema");
		} catch (ProductoRepetidoException e) {
		
		}
		
		
	}
	
	@Test
	public void AgregarProductoExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Producto hamburguesa = new Producto("Hamburguesa", 400, 1000, 10);
		
		try {
			cerveceria.addProducto(hamburguesa);
			Assert.fail("Deberia lanzarse excepcion de producto repetido Exception");
		} catch (ProductoRepetidoException e) {
			
		}
	}
	
	@Test
	public void AgregarPromoProdInexistenteConProductoListado() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		
		ArrayList<String> diasPromo = new ArrayList<String>();
		diasPromo.add("LUNES");
		PromoProducto promo = new PromoProducto(diasPromo, cerveceria.getProductos().get(0), true, false, 4, 190);
		try {
			cerveceria.addPromoProd(promo);
			if (!cerveceria.getPromosProducto().contains(promo))
				Assert.fail("No se ingreso correctamente la promocion al sistema");
			
		} catch (PromoRepetidaException e) {
			Assert.fail("No deberia lanzar excepcion de tipo PromoRepetidaExceptio ");
			
		} catch (ProductoInexistenteException e) {
			
			Assert.fail("No deberia lanzar excepcion de tipo ProductoInexistenteException");
		}
		
		
		}
	
	@Test
	public void AgregarPromoProdInexistenteConProductoNoListado() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		
		ArrayList<String> diasPromo = new ArrayList<String>();
		diasPromo.add("LUNES");
		PromoProducto promo= new PromoProducto(diasPromo, cerveceriaConDatos.getPancho(), false, true, 3, 180);
		try {
			cerveceria.addPromoProd(promo);
			Assert.fail("Deberia lanzar excpecion de tipo ProductoInexistenteException");
		} catch (PromoRepetidaException e) {
			Assert.fail("No deberia lanzar excepcion de tipo PromoRepetidaExceptio ");
			
		} catch (ProductoInexistenteException e) {
			
			//Ok
		}
		
		
		}
	
	 
	@Test
	public void AgregarPromoProdExistenteConProductoListado() {
	
       Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		
        ArrayList<String> diasPromo = new ArrayList<String>();
		diasPromo.add("LUNES");
		diasPromo.add("VIERNES");
		PromoProducto promoHamburguesa = new PromoProducto(diasPromo, cerveceria.getProductos().get(0), false, true, 3, 180);
		try {
			cerveceria.addPromoProd(promoHamburguesa);
			Assert.fail("Deberia lanzar excepcion de tipo PromoRepetidaException ");

		} catch (PromoRepetidaException e) {
			//ok
			
		} catch (ProductoInexistenteException e) {
			
			Assert.fail("No deberia lanzar excepcion de tipo ProductoInexistenteException");
		}
		
	}
	
	@Test
	public void AgregarPromoTempinexistente() {
		
	        Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
			
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
	   public void AgregarPromoTempExistente() {
		
        Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		
        ArrayList<String> diasPromo = new ArrayList<String>();
		diasPromo.add("LUNES");
		diasPromo.add("VIERNES");
		PromoTemporal promoViernes = new PromoTemporal(diasPromo, "Lunes y Viernes descuento", "TARJETA", 50, true);
		try {
			cerveceria.addPromoTemp(promoViernes);
			Assert.fail("Deberia largar excepcion PromoRepetidaException");
            
		} catch (PromoRepetidaException e) {
		
			e.printStackTrace();
		}
			
		
	}
	
}
	
	


