package testGUI;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controladores.ControladorAdmin;
import modelo.Cerveceria;
import modelo.Operario;
import modelo.Producto;
import negocio.Sistema;
import presentacion.VLogin;
import presentacion.VProducto;

public class GuiTestConjuntoConDatosVProducto {

	Robot robot;
    VProducto ventana;
    ControladorAdmin controlador;
    FalsoOptionPane pane = new FalsoOptionPane();

    public GuiTestConjuntoConDatosVProducto()
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
       controlador = new ControladorAdmin(ventana);
       Sistema.getInstance().setOptionpane(pane);
       
       Producto hamburguesa = new Producto("Hamburguesa", 400, 1000, 11);
       Cerveceria.getInstance().addProducto(hamburguesa);
    }

    @After
    public void tearDown() throws Exception
    {
       this.controlador.getVista().cerrarse();
       Cerveceria.setInstance();
        
    }	
	
    
    @Test
    public void testCantidad()
    {
    	if (Cerveceria.getInstance().getProductos().size() != 1)
            Assert.fail("Debe haber un producto registrado");
    }
    
    @Test
    public void testAgregarOk()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, "textNombre");
        JTextField venta = (JTextField) TestUtils.getComponentForName(ventana, "textVenta");
        JTextField costo = (JTextField) TestUtils.getComponentForName(ventana, "textCosto");
        JSpinner Stock = (JSpinner) TestUtils.getComponentForName(ventana, "Stock");
        		
        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");

        //lleno los JTextField
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("CocaCola", robot);
        TestUtils.clickComponent(venta, robot);
        TestUtils.tipeaTexto("100", robot);
        TestUtils.clickComponent(costo, robot);
        TestUtils.tipeaTexto("50", robot);
        TestUtils.clickComponent(Stock, robot);
        TestUtils.tipeaTexto("2", robot);
        
        TestUtils.clickComponent(enviar, robot);
        
        if (!Cerveceria.getInstance().getProductos().get(1).getNombre().equalsIgnoreCase("CocaCola"))
              Assert.fail("Nombre mal ingresado");
        if (Cerveceria.getInstance().getProductos().get(1).getpCosto() != 50)
            Assert.fail("Mal el pcosto");
        if (Cerveceria.getInstance().getProductos().get(1).getpVenta() != 100)
            Assert.fail("Mal el pventa");
        if (Cerveceria.getInstance().getProductos().get(1).getStock() != 21)
            Assert.fail("Mal el stock");
        
    }
    
    @Test
    public void testAgregarRepetido()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, "textNombre");
        JTextField venta = (JTextField) TestUtils.getComponentForName(ventana, "textVenta");
        JTextField costo = (JTextField) TestUtils.getComponentForName(ventana, "textCosto");
        JSpinner Stock = (JSpinner) TestUtils.getComponentForName(ventana, "Stock");
        		
        JButton enviar = (JButton) TestUtils.getComponentForName(ventana, "Enviar");
        JButton salir = (JButton) TestUtils.getComponentForName(ventana, "Salir");

        //lleno los JTextField
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("Hamburguesa", robot);
        TestUtils.clickComponent(venta, robot);
        TestUtils.tipeaTexto("1000", robot);
        TestUtils.clickComponent(costo, robot);
        TestUtils.tipeaTexto("400", robot);
        TestUtils.clickComponent(Stock, robot);
        TestUtils.tipeaTexto("11", robot);
        
        TestUtils.clickComponent(enviar, robot);
        Assert.assertEquals("Mensaje incorrecto, deberia decir"+ "Usuario o contrasena incorrecta", "Producto ya existente"  ,pane.getMensaje());
        
    }
	
	
}
