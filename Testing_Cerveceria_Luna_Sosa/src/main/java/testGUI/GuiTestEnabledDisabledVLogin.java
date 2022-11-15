package testGUI;


import controladores.ControladorLogin;
import presentacion.IVistaLogin;
import presentacion.VLogin;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTestEnabledDisabledVLogin
{
    Robot robot;
    VLogin ventana;
    ControladorLogin controlador;

    public GuiTestEnabledDisabledVLogin()
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
    	ventana =  new VLogin();
        controlador = new ControladorLogin(ventana);
       
    }

    @After
    public void tearDown() throws Exception
    {
       this.ventana.cerrarse();
        
    }



    @Test
    public void testVacios()
    {
        //obtengo las referencias a los componentes necesarios
        JButton aceptarLog = (JButton) TestUtils.getComponentForName(ventana, "botonIngresar");
        
        //verifico los resultados
        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
    }


    @Test
    public void testLogSoloNombre()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, "textUser");
        JButton aceptarLog = (JButton) TestUtils.getComponentForName(ventana, "botonIngresar");
        //lleno los JTextField
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("holaaaaaaaaaaaaaaaaaaaaaaaaaaaa", robot);
        //verifico los resultados
        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
    }
  
    @Test
    public void testLogSoloContra()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField contrasena = (JTextField) TestUtils.getComponentForName(ventana, "textoContra");
        JButton aceptarLog = (JButton) TestUtils.getComponentForName(ventana, "botonIngresar");
        //lleno los JTextField
        TestUtils.clickComponent(contrasena, robot);
        TestUtils.tipeaTexto("hola", robot);
        //verifico los resultados
        Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
    }

    @Test
    public void testLogLleno()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField contrasena = (JTextField) TestUtils.getComponentForName(ventana, "textoContra");
        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana, "textUser");
        JButton aceptarLog = (JButton) TestUtils.getComponentForName(ventana, "botonIngresar");
        //lleno los JTextField
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("holaaaaaaaaaaaaaaaaaa", robot);
        TestUtils.clickComponent(contrasena, robot);
        TestUtils.tipeaTexto("holaaaaaaaaaaaaaaaaaa", robot);
        //verifico los resultados
      
        Assert.assertTrue("El boton de login deberia estar hablitado", aceptarLog.isEnabled());
    }
    
}

