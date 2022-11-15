package testGUI;

import controladores.ControladorLogin;
import modelo.Cerveceria;
import modelo.Operario;
import negocio.Sistema;
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

import controladores.ControladorLogin;

public class GuiTestConjuntoConDatosVLogin
{
	Robot robot;
    VLogin ventana;
    ControladorLogin controlador;
    FalsoOptionPane pane = new FalsoOptionPane();

    public GuiTestConjuntoConDatosVLogin()
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
       
       ventana =  new VLogin();
       controlador = new ControladorLogin(ventana);
       Sistema.getInstance().setOptionpane(pane);
       Operario op = new Operario("Juan Perez", "Juanpe", "Juanpe123");
       Cerveceria.getInstance().addOperario(op);
    }

    @After
    public void tearDown() throws Exception
    {
       this.controlador.getVista().cerrarse();
       Cerveceria.setInstance();
        
    }

    @Test
    public void testLogOk()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField contrasena = (JTextField) TestUtils.getComponentForName(ventana, "textoContra");
        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana , "textUser");
        JButton aceptarLog = (JButton) TestUtils.getComponentForName(ventana , "botonIngresar");

        //lleno los JTextField
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("Juanpe", robot);
        TestUtils.clickComponent(contrasena, robot);
        TestUtils.tipeaTexto("Juanpe123", robot);	
        TestUtils.clickComponent(aceptarLog, robot);
        //verifico los resultados
        Assert.assertEquals("Deberia coincidir el nombre de usuario con el nombre ingresado", "Juanpe",
                            controlador.getVista().getUsername());
    }
    
    @Test
    public void testLogUsuarioInexistente()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField contrasena = (JTextField) TestUtils.getComponentForName(ventana, "textoContra");
        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana , "textUser");
        JButton aceptarLog = (JButton) TestUtils.getComponentForName(ventana , "botonIngresar");
        
        //lleno los JTextField
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("Juanse", robot);
        TestUtils.clickComponent(contrasena, robot);
        TestUtils.tipeaTexto("Juanse1234", robot);
        TestUtils.clickComponent(aceptarLog, robot);
        
        
        //verifico los resultados
        Assert.assertEquals("Mensaje incorrecto, deberia decir"+ "Usuario o contrasena incorrecta", "Usuario o contrasena incorrecta"  ,pane.getMensaje());
    }

    
    @Test
    public void testLogContrasenaMal()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField contrasena = (JTextField) TestUtils.getComponentForName(ventana, "textoContra");
        JTextField nombre = (JTextField) TestUtils.getComponentForName(ventana , "textUser");
        JButton aceptarLog = (JButton) TestUtils.getComponentForName(ventana , "botonIngresar");
        
        //lleno los JTextField
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("Juanpe", robot);
        TestUtils.clickComponent(contrasena, robot);
        TestUtils.tipeaTexto("Juanpe123425", robot);	 
        TestUtils.clickComponent(aceptarLog, robot);
        
        //verifico los resultados
        Assert.assertEquals("Mensaje incorrecto, deberia decir"+ "Usuario o contrasena incorrecta", "Usuario o contrasena incorrecta"  ,pane.getMensaje());
    }
    

    
}


