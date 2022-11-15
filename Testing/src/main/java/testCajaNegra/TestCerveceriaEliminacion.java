package testCajaNegra;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import escenarios.CerveceriaConDatos;
import excepciones.ComandaAbiertaException;
import excepciones.MesaInexistenteException;
import excepciones.MesaRepetidaException;
import excepciones.MozoInexistenteException;
import excepciones.OperarioInexistenteException;
import excepciones.ProductoEnComandaException;
import excepciones.ProductoInexistenteException;
import excepciones.PromoInexistenteException;
import excepciones.PromoRepetidaException;
import excepciones.UsuarioRepetidoException;
import modelo.Cerveceria;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;


public class TestCerveceriaEliminacion {

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
	public void EliminarOperarioExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Operario op = cerveceria.getOperario("SANTISOSA");
		try {
			cerveceria.deleteOperario(op);
			if (cerveceria.getOperarios().contains(op))
				Assert.fail("No se elimino el operario");
		} catch (OperarioInexistenteException e) {
			Assert.fail("No deberia largar excepcion de inexistente.");
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void EliminarOperarioInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Operario op = cerveceriaConDatos.getOp();
		try {
			cerveceria.deleteOperario(op);
			Assert.fail("Debe largar excepcion OperarioInexistenteException");
		} catch (OperarioInexistenteException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void EliminarMesaExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Mesa mesa1 = cerveceria.getInstance().getMesas().get(0);
		
			try {
				cerveceria.deleteMesa(mesa1);
				if (cerveceria.getMesas().contains(mesa1))
					Assert.fail("No se elimino correctamente la mesa del sistema");
			} catch (ComandaAbiertaException e) {
				Assert.fail("No deberia largar esta excepcion");
				e.printStackTrace();
			} catch (MesaInexistenteException e) {
				Assert.fail("No deberia largar esta excepcion");
				e.printStackTrace();
			}
				
	}
	
	@Test
	public void EliminarMesaInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		
		
			try {
				cerveceria.deleteMesa(cerveceriaConDatos.getMesa());
			
			} catch (ComandaAbiertaException e) {
				Assert.fail("No deberia largar esta excepcion");
				e.printStackTrace();
			} catch (MesaInexistenteException e) {
				//ok
				e.printStackTrace();
			}
				
	}
	
	
	@Test
	public void EliminarMozoExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Mozo mozo1 = cerveceria.getInstance().getMozos().get(0);
		
			try {
				cerveceria.deleteMozo(mozo1);
				if (cerveceria.getMozos().contains(mozo1))
					Assert.fail("No se elimino correctamente el mozo del sistema");
				
			} catch (MozoInexistenteException e) {
				Assert.fail("No deberia largar esta excepecion, el mozo existe");
				e.printStackTrace();
			}
				
	}
	
	@Test
	public void EliminarMozoInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Date fecha1 = new Date(2000, 8, 17);
		Mozo mozo = new Mozo("Juan pedro", fecha1, 1);
		
		try {
			cerveceria.deleteMozo(mozo);
			Assert.fail("Debe largar excepcion, mozo no existe");
		} catch (MozoInexistenteException e) {
			
			e.printStackTrace();
		}
				
	}
	
	@Test
	public void EliminarProductoExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Producto p1 = cerveceria.getProductos().get(0);
		
			
				try {
					cerveceria.deleteProducto(p1);
					if (cerveceria.getProductos().contains(p1))
						Assert.fail("No se elimino correctamente el producto del sistema");
				} catch (ProductoEnComandaException e) {
					Assert.fail("No deberia largar esta excepecion");
					e.printStackTrace();
				} catch (ProductoInexistenteException e) {
					Assert.fail("No deberia largar esta excepecion, el producto existe");
					e.printStackTrace();
				}
				
				
			
				
	}
	
	@Test
	public void EliminarProductoInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		Producto p1 = new Producto("xxxx", 100,200,10);
		
		
			try {
				//cerveceria.deleteProducto(cerveceriaConDatos.getPancho());
				cerveceria.deleteProducto(p1);
				Assert.fail("Debe largar ProductoInexistenteException");
				
			} catch (ProductoEnComandaException e) {
				Assert.fail("No deberia largar esta excepecion");
				e.printStackTrace();
			} catch (ProductoInexistenteException e) {
				
				e.printStackTrace();
			}
			
		
				
	}
	
	@Test
	public void EliminarPromoProdExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		
		PromoProducto p1 = cerveceria.getPromosProducto().get(0);
		try {
			cerveceria.deletePromoProducto(p1);
			if (cerveceria.getPromosProducto().contains(p1))
				Assert.fail("No se elimino promocion correctamente");
		} catch (PromoInexistenteException e) {
			Assert.fail("No deberia largar excepcion, promo existe.");
			e.printStackTrace();
		}
		
		}
	
	@Test
	public void EliminarPromoProdInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		
		ArrayList<String> diasPromo = new ArrayList<String>();
		diasPromo.add("LUNES");
		PromoProducto promo = new PromoProducto(diasPromo,cerveceriaConDatos.getPancho(), true, false, 5, 200);
		try {
			cerveceria.deletePromoProducto(promo);
			Assert.fail("Debe largar la excepcion");
		} catch (PromoInexistenteException e) {
			
			e.printStackTrace();
		}
		
		}
	
	@Test
	public void EliminarPromoTempExistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		
		PromoTemporal p1 = cerveceria.getPromoTemporales().get(0);
		try {
			cerveceria.deletePromoTemporal(p1);
			if (cerveceria.getPromoTemporales().contains(p1))
				Assert.fail("No se elimino promocion correctamente");
		} catch (PromoInexistenteException e) {
			Assert.fail("No deberia largar excepcion, promo existe.");
			e.printStackTrace();
		}
		
		}
	
	@Test
	public void EliminarPromoTempInexistente() {
		Cerveceria cerveceria = cerveceriaConDatos.getCerveceria();
		
		ArrayList<String> diasPromo = new ArrayList<String>();
		diasPromo.add("LUNES");
		PromoTemporal promoLunes = new PromoTemporal(diasPromo, "descuento", "TARJETA", 30, false);
		try {
			cerveceria.deletePromoTemporal(promoLunes);
			Assert.fail("Debe largar la excepcion");
		} catch (PromoInexistenteException e) {
			
			e.printStackTrace();
		}
		
		}
	
	
	
	
	
	
	
	

}
