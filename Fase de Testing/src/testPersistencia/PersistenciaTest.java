package testPersistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.Cerveceria;
import modelo.Comanda;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import modelo.Promocion;
import modelo.Venta;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

public class PersistenciaTest {
	
	Cerveceria cerveceria;
	
	/**
	 * Eliminar cualquier archivo persistido para poder comenzar el testing.
	 */
    @Before
    public void setUp() {
        cerveceria = Cerveceria.getInstance();
        File archivo = new File("Cerveceria.bin");
        if (archivo.exists())
        	archivo.delete();
    }
    
    @After
    public void TearDown() {
    }

    /**
     * Comprueba creacion efectiva del archivo de persistencia.
     */
	@Test
	public void test_CrearArchivo() {
		cerveceria.persistir();
		File archivo = new File("Cerveceria.bin");
		Assert.assertTrue("Debería existir el archivo Cerveceria.bin", archivo.exists());
	}
	
	/**
	 * Se testea la despersistencia de la cerveceria a partir de un archivo inexistente.
	 */
	@Test
    public void testDespersitirSinArchivo() {
        try {
            cerveceria.despersistir();
            Assert.fail("debería haber lanzado una excepcion de tipo FileNotFound");
        }
        catch (FileNotFoundException e) {

        } catch (ClassNotFoundException e) {
			Assert.fail("La clase deberia existir (no debería arrojar ClassNotFoundException).");
		} catch (IOException e) {
			Assert.fail("La IOException deberia ser porque el archivo no se encontro, no otra.");
		}
    }
	
	/**
	 * Se testea la persistencia de la cerveceria cuando las colecciones estan vacias.
	 */
	@Test
	public void testCerveceriaVacia() {
		cerveceria.persistir();
		Cerveceria.setInstance();
		cerveceria = Cerveceria.getInstance();
		try {
			cerveceria.despersistir();
			Assert.assertTrue("El administrador nunca puede ser nulo.", cerveceria.getAdmin() != null);
			Assert.assertTrue("No deberia existir ningun mozo.", cerveceria.getMozos().isEmpty());
			Assert.assertTrue("No deberia existir ninguna mesa.", cerveceria.getMesas().isEmpty());
			Assert.assertTrue("No deberia existir ningun producto.", cerveceria.getProductos().isEmpty());
			Assert.assertTrue("No deberia existir ningun operario.", cerveceria.getOperarios().isEmpty());
			Assert.assertTrue("No deberia existir ninguna comanda abierta.", cerveceria.getComandasAbiertas().isEmpty());
			Assert.assertTrue("No deberia existir ninguna venta.", cerveceria.getVentas().isEmpty());
			Assert.assertTrue("No deberia existir ninguna promo temporal.", cerveceria.getPromoTemporales().isEmpty());
			Assert.assertTrue("No deberia existir ninguna promo de producto.", cerveceria.getPromosProducto().isEmpty());
		} catch (ClassNotFoundException e) {
			Assert.fail("La clase deberia existir (no debería arrojar ClassNotFoundException).");
		} catch (IOException e) {
			Assert.fail("No deberia arrojar IOException.");
		}
		
	}
	
	/**
	 * Se testea la correcta persistencia de la cerveceria cuyas colecciones contengan al menos un elemento.
	 */
	@Test
	public void testCerveceriaNoVacia() {
		Mozo mo = new Mozo("Santiago Lapiana", new Date(26, 4, 2002), 0);
		Mesa me1 = new Mesa(5, 0);
		Mesa me2 = new Mesa(3, 1);
		Producto prod = new Producto("Cerveza", 100, 150, 5); 
		Operario op = new Operario("Wenceslao Avalos", "wencho8", "12345");
		
		cerveceria.getMozos().add(mo);
		cerveceria.getMesas().add(me1);
		cerveceria.getMesas().add(me2);
		cerveceria.getProductos().add(prod);
		cerveceria.getOperarios().add(op);
		
		CerveceriaDTO anterior = UtilPersistencia.CerveceriaToCerveceriaDTO(cerveceria);
		IPersistencia<Serializable> persistencia = new PersistenciaBIN();
		
		try {
			persistencia.abrirOutput("Cerveceria.bin");
			persistencia.escribir(anterior);
			persistencia.cerrarOutput();
			persistencia.abrirInput("Cerveceria.bin");
			CerveceriaDTO nueva = (CerveceriaDTO) persistencia.leer();
			persistencia.cerrarInput();
			
			Mozo mo_nuevo = nueva.getMozos().get(0);
			Assert.assertTrue("Los mozos deberian coincidir al despersistir.", mo.getNya().equals(mo_nuevo.getNya()) && mo.getCantHijos() == mo_nuevo.getCantHijos() && mo.getEdad() == mo_nuevo.getEdad());
			Mesa m1_nueva = nueva.getMesas().get(0);
			Assert.assertTrue("Las barras deberian coincidir al despersistir.", me1.getNroMesa() == m1_nueva.getNroMesa() && me1.getCantComensales() == m1_nueva.getCantComensales() && me1.getEstado().equals(m1_nueva.getEstado()));
			Mesa m2_nueva = nueva.getMesas().get(1);
			Assert.assertTrue("Las mesas deberian coincidir al despersistir.", me2.getNroMesa() == m2_nueva.getNroMesa() && me2.getCantComensales() == m2_nueva.getCantComensales() && me2.getEstado().equals(m2_nueva.getEstado()));
			Producto p_nuevo = nueva.getProductos().get(0);
			Assert.assertTrue("Los productos deberian coincidir al despersistir.", prod.getId_producto() == p_nuevo.getId_producto() && prod.getNombre().equals(p_nuevo.getNombre()) && prod.getpCosto() == p_nuevo.getpCosto() && prod.getpVenta() == p_nuevo.getpVenta() && prod.getStock() == p_nuevo.getStock());
			Operario op_nuevo = nueva.getOperarios().get(0);
			Assert.assertTrue("Los operarios deberian coincidir al despersistir.", op.getNya().equals(op_nuevo.getNya()) && op.getUsername().equals(op_nuevo.getUsername()));
		} catch (ClassNotFoundException e) {
			Assert.fail("La clase deberia existir (no debería arrojar ClassNotFoundException).");
		} catch (IOException e) {
			Assert.fail("No deberia arrojar IOException.");
		}
	}

}
