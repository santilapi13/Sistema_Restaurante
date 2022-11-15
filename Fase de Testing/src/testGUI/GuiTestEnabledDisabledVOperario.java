package testGUI;

import controladores.ControladorLogin;
import presentacion.IVistaLogin;
import presentacion.VLogin;
import presentacion.VRegistroOp;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuiTestEnabledDisabledVOperario
{
    Robot robot;
    VRegistroOp ventana;
    ControladorLogin controlador;

    public GuiTestEnabledDisabledVOperario()
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
      ventana =  new VRegistroOp();
       
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
        
    }
  
    @Test
    public void testLogSoloContra()
    {
       
    }

    @Test
    public void testLogLleno()
    {
       
    }
    
}


