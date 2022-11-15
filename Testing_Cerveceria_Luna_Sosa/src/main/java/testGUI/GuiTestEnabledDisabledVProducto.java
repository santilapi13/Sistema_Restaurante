package testGUI;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controladores.ControladorAdmin;
import modelo.Cerveceria;
import modelo.Operario;
import negocio.Sistema;
import presentacion.VLogin;
import presentacion.VProducto;

public class GuiTestEnabledDisabledVProducto {

	
		Robot robot;
	    VProducto ventana;
	    ControladorAdmin controlador;
	    FalsoOptionPane pane = new FalsoOptionPane();

	    public GuiTestEnabledDisabledVProducto()
	    {
	        try
	        {
	            robot = new Robot();
	        } catch (AWTException e)
	        {
	        }
	    }

	    @Before
	    public void setUp() throws Exception
	    {
	    	robot.delay(2000);
	       
	       ventana =  new VProducto();
	       //controlador = new ControladorAdmin(ventana);
	       Sistema.getInstance().setOptionpane(pane);
	       
	       
	    }

	    @After
	    public void tearDown() throws Exception
	    {
	       this.ventana.cerrarse();
	       Cerveceria.setInstance();
	        
	    }	
	    
	    @Test
	    public void testVacios()
	    {
	        
	        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
	        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");
	        
	        
	        Assert.assertFalse("El boton de enviar deberia estar desdeshablitado", enviar.isEnabled());
	        Assert.assertFalse("El boton de salir deberia estar hablitado", !salir.isEnabled());
	    }
	    
	    @Test
	    public void testSoloNombre()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, "textNombre");
	        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
	        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");
	        //lleno los JTextField
	        TestUtils.clickComponent(nombre, robot);
	        TestUtils.tipeaTexto("Cerveza tirada rica", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de Enviar deberia estar deshablitado", enviar.isEnabled());
	        Assert.assertFalse("El boton de salir deberia estar hablitado", !salir.isEnabled());
	    }
	    
	    @Test
	    public void testSoloCosto()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	        JTextField costo = (JTextField) TestUtils.getComponentForName(ventana, "textCosto");
	        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
	        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");
	        //lleno los JTextField
	        TestUtils.clickComponent(costo, robot);
	        TestUtils.tipeaTexto("10000", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de Enviar deberia estar deshablitado", enviar.isEnabled());
	        Assert.assertFalse("El boton de salir deberia estar hablitado", !salir.isEnabled());
	    }
	    
	    @Test
	    public void testSoloVenta()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	        JTextField venta = (JTextField) TestUtils.getComponentForName(ventana, "textVenta");
	        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
	        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");
	        //lleno los JTextField
	        TestUtils.clickComponent(venta, robot);
	        TestUtils.tipeaTexto("10000", robot);
	        //verifico los resultados
	        Assert.assertFalse("El boton de Enviar deberia estar deshablitado", enviar.isEnabled());
	        Assert.assertFalse("El boton de salir deberia estar hablitado", !salir.isEnabled());
	    }

	    
	    
	    @Test
	    public void testSinNombre()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	  
	        JTextField venta = (JTextField) TestUtils.getComponentForName(ventana, "textVenta");
	        JTextField costo = (JTextField) TestUtils.getComponentForName(ventana, "textCosto");
	        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
	        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");
	        //lleno los JTextField
	  
	        TestUtils.clickComponent(venta, robot);
	        TestUtils.tipeaTexto("1000000", robot);
	        TestUtils.clickComponent(costo, robot);
	        TestUtils.tipeaTexto("10000", robot);
	        
	        
	        //verifico los resultados
	        Assert.assertFalse("El boton de Enviar deberia estar deshablitado", enviar.isEnabled());
	        Assert.assertFalse("El boton de salir deberia estar hablitado", !salir.isEnabled());
	    }
	   
	    
	    @Test
	    public void testSinCosto()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, "textNombre");
	        JTextField venta = (JTextField) TestUtils.getComponentForName(ventana, "textVenta");
	    
	        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
	        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");
	        //lleno los JTextField
	        
	        TestUtils.clickComponent(nombre, robot);
	        TestUtils.tipeaTexto("Cerveza tirada rica", robot);
	        TestUtils.clickComponent(venta, robot);
	        TestUtils.tipeaTexto("1000000", robot);
	     
	        
	        
	        //verifico los resultados
	        Assert.assertFalse("El boton de Enviar deberia estar deshablitado", enviar.isEnabled());
	        Assert.assertFalse("El boton de salir deberia estar hablitado", !salir.isEnabled());
	    }
	    
	    @Test
	    public void testSinVenta()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, "textNombre");
	        JTextField costo = (JTextField) TestUtils.getComponentForName(ventana, "textCosto");    
	    
	        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
	        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");
	        //lleno los JTextField
	        
	        TestUtils.clickComponent(nombre, robot);
	        TestUtils.tipeaTexto("Cerveza tirada rica", robot);
	        TestUtils.clickComponent(costo, robot);
	        TestUtils.tipeaTexto("1000000", robot);
	     
	        
	        
	        //verifico los resultados
	        Assert.assertFalse("El boton de Enviar deberia estar deshablitado", enviar.isEnabled());
	        Assert.assertFalse("El boton de salir deberia estar hablitado", !salir.isEnabled());
	    }
	    
	    @Test
	    public void testTodosLLenos()
	    {
	        robot.delay(TestUtils.getDelay());
	        //obtengo las referencias a los componentes necesarios
	        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, "textNombre");
	        JTextField venta = (JTextField) TestUtils.getComponentForName(ventana, "textVenta");
	        JTextField costo = (JTextField) TestUtils.getComponentForName(ventana, "textCosto");
	        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
	        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");
	        //lleno los JTextField
	        TestUtils.clickComponent(nombre, robot);
	        TestUtils.tipeaTexto("Cerveza tirada rica", robot);
	        TestUtils.clickComponent(venta, robot);
	        TestUtils.tipeaTexto("1000000", robot);
	        TestUtils.clickComponent(costo, robot);
	        TestUtils.tipeaTexto("10000", robot);
	        
	        
	        //verifico los resultados
	        Assert.assertFalse("El boton de Enviar deberia estar hablitado", !enviar.isEnabled());
	        Assert.assertFalse("El boton de salir deberia estar hablitado", !salir.isEnabled());
	    }
	
	
}
	
	
