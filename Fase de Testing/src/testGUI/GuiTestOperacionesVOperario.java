package testGUI;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controladores.ControladorOperario;
import modelo.Cerveceria;
import modelo.Estado;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import negocio.Sistema;
import presentacion.VOperario;

public class GuiTestOperacionesVOperario {

	
	 Robot robot;
	 VOperario ventana;
	 ControladorOperario controlador;

	 public GuiTestOperacionesVOperario()
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
	     ventana =  new VOperario();  
	     
	     
	    
	     
	     controlador = new ControladorOperario(ventana);
	     Operario op = new Operario("Santi Sosa", "SANTISOSA", "Santi123");
	     Sistema.getInstance().setOpLogueado(op);
	     
	     Date fecha1 = new Date(2000, 8, 17);
	     Mozo mozo = new Mozo("Julian Alvarez", fecha1, 3);
	     Mesa mesa1 = new Mesa(4, 0);
	     Cerveceria.getInstance().addMesa(mesa1);
	     Cerveceria.getInstance().addMozo(mozo);
	     this.ventana.actualizaListaMozos(Cerveceria.getInstance().getMozos());
	     this.ventana.actualizaListaMesas(Cerveceria.getInstance().getMesas());
	       
	    }	
	    
	    @After
	 public void tearDown() throws Exception
	    {
	       this.controlador.getVista().cerrarse();
	       Cerveceria.setInstance();
	       Sistema.getInstance().setOpLogueado(null);
	        
	    }    
	    
	    
	   @Test
	 public void testEstadoFrancoMozo()
	    {
	    	   
	        // Hay una JList dentro de un JScrollPane
	        // Obtengo la referencia a la JList que contiene los mozos     
	        JList mozos = (JList) TestUtils.getComponentForName(ventana, "ListaMozos");
	        
	        JButton botonActivo = (JButton) TestUtils.getComponentForName( ventana, "Activo");
	        JButton botonEstadistica = (JButton) TestUtils.getComponentForName( ventana, "Estadisticas");
	        JButton botonAusente = (JButton) TestUtils.getComponentForName( ventana, "Ausente");
	        JButton botonFranco = (JButton) TestUtils.getComponentForName( ventana, "Franco");
	        
	        // Obtengo la referencia al JScrollPane
	        JScrollPane contenedor = (JScrollPane) TestUtils.getComponentForName(ventana, "ScrollMozo");
	        
	        // Obtengo la posicion del contenedor (JScrollPane)
	        Point posicion = contenedor.getLocationOnScreen();        
	    
	       
	        //  Voy a hacer click cerca del inicio del contenido --> 20% del ancho
	        int anchoContenedor = (int) (contenedor.getWidth()*0.2);
	       
	        // Obtengo la cantidad de elementos que tiene actualmente la lista
	        int numeroDeElementos = mozos.getModel().getSize();
	        
	       
	 
	        int myPos=0;
	        Mozo mozo = (Mozo) mozos.getModel().getElementAt(myPos);
	            
	        // Obtengo la posicion del elemento en la lista, referenciado a su contenedor
	        Point indexToLocation = mozos.indexToLocation(myPos);
	          
	            
	        robot.delay(2000);          
	            
	            
	            // Nos posicionamos sobre el elemento 
	        int x = (int) posicion.getX() + (int) indexToLocation.getX() + anchoContenedor;
	        int y = (int) posicion.getY() + (int) indexToLocation.getY() + 15;
	            
	        // movemos el mouse
	        robot.mouseMove(x, y);
	        robot.delay(2000);
	        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	        robot.delay(2000);
	        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	        robot.delay(2000);
	            
	              
	        TestUtils.clickComponent(botonFranco, robot);
	        
	       
	        if (Cerveceria.getInstance().getMozos().get(0).getEstado() != Estado.FRANCO)
	        	Assert.fail("El estado debe ser franco");
	        
	            
	        }
	    
	     @Test
		 public void testEstadoAusenteMozo()
		    {
		    	   
		        // Hay una JList dentro de un JScrollPane
		        // Obtengo la referencia a la JList que contiene los mozos     
		        JList mozos = (JList) TestUtils.getComponentForName(ventana, "ListaMozos");
		        
		        JButton botonActivo = (JButton) TestUtils.getComponentForName( ventana, "Activo");
		        JButton botonEstadistica = (JButton) TestUtils.getComponentForName( ventana, "Estadisticas");
		        JButton botonAusente = (JButton) TestUtils.getComponentForName( ventana, "Ausente");
		        JButton botonFranco = (JButton) TestUtils.getComponentForName( ventana, "Franco");
		        
		        // Obtengo la referencia al JScrollPane
		        JScrollPane contenedor = (JScrollPane) TestUtils.getComponentForName(ventana, "ScrollMozo");
		        
		        // Obtengo la posicion del contenedor (JScrollPane)
		        Point posicion = contenedor.getLocationOnScreen();        
		    
		       
		        //  Voy a hacer click cerca del inicio del contenido --> 20% del ancho
		        int anchoContenedor = (int) (contenedor.getWidth()*0.2);
		       
		        // Obtengo la cantidad de elementos que tiene actualmente la lista
		        int numeroDeElementos = mozos.getModel().getSize();
		        
		       
		 
		        int myPos=0;
		        Mozo mozo = (Mozo) mozos.getModel().getElementAt(myPos);
		            
		        // Obtengo la posicion del elemento en la lista, referenciado a su contenedor
		        Point indexToLocation = mozos.indexToLocation(myPos);
		          
		            
		        robot.delay(2000);          
		            
		            
		            // Nos posicionamos sobre el elemento 
		        int x = (int) posicion.getX() + (int) indexToLocation.getX() + anchoContenedor;
		        int y = (int) posicion.getY() + (int) indexToLocation.getY() + 15;
		            
		        // movemos el mouse
		        robot.mouseMove(x, y);
		        robot.delay(2000);
		        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		        robot.delay(2000);
		        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		        robot.delay(2000);
		            
		              
		        TestUtils.clickComponent(botonAusente, robot);
		        
		       
		        if (Cerveceria.getInstance().getMozos().get(0).getEstado() != Estado.AUSENTE)
		        	Assert.fail("El estado debe ser Ausente");
		        
		            
		        }
	    
	    
	    @Test
		 public void testEstadoActivoMozo()
		    {
		    	   
		        // Hay una JList dentro de un JScrollPane
		        // Obtengo la referencia a la JList que contiene los mozos     
		        JList mozos = (JList) TestUtils.getComponentForName(ventana, "ListaMozos");
		        
		        JButton botonActivo = (JButton) TestUtils.getComponentForName( ventana, "Activo");
		        JButton botonEstadistica = (JButton) TestUtils.getComponentForName( ventana, "Estadisticas");
		        JButton botonAusente = (JButton) TestUtils.getComponentForName( ventana, "Ausente");
		        JButton botonFranco = (JButton) TestUtils.getComponentForName( ventana, "Franco");
		        
		        // Obtengo la referencia al JScrollPane
		        JScrollPane contenedor = (JScrollPane) TestUtils.getComponentForName(ventana, "ScrollMozo");
		        
		        // Obtengo la posicion del contenedor (JScrollPane)
		        Point posicion = contenedor.getLocationOnScreen();        
		    
		       
		        //  Voy a hacer click cerca del inicio del contenido --> 20% del ancho
		        int anchoContenedor = (int) (contenedor.getWidth()*0.2);
		       
		        // Obtengo la cantidad de elementos que tiene actualmente la lista
		        int numeroDeElementos = mozos.getModel().getSize();
		        
		       
		 
		        int myPos=0;
		        Mozo mozo = (Mozo) mozos.getModel().getElementAt(myPos);
		            
		        // Obtengo la posicion del elemento en la lista, referenciado a su contenedor
		        Point indexToLocation = mozos.indexToLocation(myPos);
		          
		            
		        robot.delay(2000);          
		            
		            
		            // Nos posicionamos sobre el elemento 
		        int x = (int) posicion.getX() + (int) indexToLocation.getX() + anchoContenedor;
		        int y = (int) posicion.getY() + (int) indexToLocation.getY() + 15;
		            
		        // movemos el mouse
		        robot.mouseMove(x, y);
		        robot.delay(2000);
		        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		        robot.delay(2000);
		        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		        robot.delay(2000);
		            
		              
		        TestUtils.clickComponent(botonActivo, robot);
		        
		       
		        if (Cerveceria.getInstance().getMozos().get(0).getEstado() != Estado.ACTIVO)
		        	Assert.fail("El estado debe ser Activo");
		        
		            
		        }
		    
	    
	  
	    
	    @Test
	    public void testAsignarMesaAMozo()
	    {
	    	   
	        // Hay una JList dentro de un JScrollPane
	        // Obtengo la referencia a la JList que contiene los mozos     
	        JList mozos = (JList) TestUtils.getComponentForName(ventana, "ListaMozos");
	        
	        JButton botonActivo = (JButton) TestUtils.getComponentForName( ventana, "Activo");
	        JButton botonEstadistica = (JButton) TestUtils.getComponentForName( ventana, "Estadisticas");
	        JButton botonAusente = (JButton) TestUtils.getComponentForName( ventana, "Ausente");
	        JButton botonFranco = (JButton) TestUtils.getComponentForName( ventana, "Franco");
	        
	        // Obtengo la referencia al JScrollPane
	        JScrollPane contenedor = (JScrollPane) TestUtils.getComponentForName(ventana, "ScrollMozo");
	        
	        // Obtengo la posicion del contenedor (JScrollPane)
	        Point posicion = contenedor.getLocationOnScreen();        
	    
	       
	        //  Voy a hacer click cerca del inicio del contenido --> 20% del ancho
	        int anchoContenedor = (int) (contenedor.getWidth()*0.2);
	       
	        // Obtengo la cantidad de elementos que tiene actualmente la lista
	        int numeroDeElementos = mozos.getModel().getSize();
	        
	       
	 
	        int myPos=0;
	        Mozo mozo = (Mozo) mozos.getModel().getElementAt(myPos);
	            
	            // Obtengo la posicion del elemento en la lista, referenciado a su contenedor
	        Point indexToLocation = mozos.indexToLocation(myPos);
	          
	            
	        robot.delay(2000);          
	            
	            
	            // Nos posicionamos sobre el elemento 
	        int x = (int) posicion.getX() + (int) indexToLocation.getX() + anchoContenedor;
	        int y = (int) posicion.getY() + (int) indexToLocation.getY() + 15;
	            
	        // movemos el mouse
	        robot.mouseMove(x, y);
	        robot.delay(2000);
	        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	        robot.delay(2000);
	        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	        robot.delay(500);
	            
	     // Hay una JList dentro de un JScrollPane
	        // Obtengo la referencia a la JList que contiene los mozos     
	        JList mesas = (JList) TestUtils.getComponentForName(ventana, "ListaMesas");
	        
	        JButton botonAsignar = (JButton) TestUtils.getComponentForName( ventana, "Asignar");
	        JButton botonCerrar = (JButton) TestUtils.getComponentForName( ventana, "Cerrar");
	        JButton botonPedido = (JButton) TestUtils.getComponentForName( ventana, "Pedido");
	        JButton botonDesasignar = (JButton) TestUtils.getComponentForName( ventana, "Desasignar");
	      
	        
	        // Obtengo la referencia al JScrollPane
	        JScrollPane contenedor1 = (JScrollPane) TestUtils.getComponentForName(ventana, "ScrollMesa");
	        
	        // Obtengo la posicion del contenedor (JScrollPane)
	        Point posicion1 = contenedor1.getLocationOnScreen();        
	    
	       
	        //  Voy a hacer click cerca del inicio del contenido --> 20% del ancho
	        int anchoContenedor1 = (int) (contenedor1.getWidth()*0.2);
	       
	        // Obtengo la cantidad de elementos que tiene actualmente la lista
	        int numeroDeElementos1 = mesas.getModel().getSize();
	        
	       
	 
	        int myPos1=0;
	        Mesa mesa = (Mesa) mesas.getModel().getElementAt(myPos1);
	            
	            // Obtengo la posicion del elemento en la lista, referenciado a su contenedor
	        Point indexToLocation1 = mesas.indexToLocation(myPos1);
	          
	            
	        robot.delay(2000);          
	            
	            
	            // Nos posicionamos sobre el elemento
	        int x1 = (int) posicion1.getX() + (int) indexToLocation1.getX() + anchoContenedor1;
	        int y1 = (int) posicion1.getY() + (int) indexToLocation1.getY() + 15;
	            
	        // movemos el mouse
	        robot.mouseMove(x1, y1);
	        robot.delay(2000);
	        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	        robot.delay(2000);
	        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	        robot.delay(2000);
	              
	        TestUtils.clickComponent(botonAsignar, robot);
	        robot.delay(1000);
	        
	        if (!Cerveceria.getInstance().getMozos().get(0).getMesas().contains(mesa))
	        	Assert.fail("La mesa no fue asignada correctamente");
	        
	        robot.delay(1000);
	       
	        
	            
	        }
	    @Test 
	   public void TestDesasignarMesas() {
	    	JButton botonDesasignar = (JButton) TestUtils.getComponentForName( ventana, "Desasignar");
	    	TestUtils.clickComponent(botonDesasignar, robot);
	    	robot.delay(1000);
	    	if (Cerveceria.getInstance().getMesas().get(0).isAsignado())
	        	Assert.fail("La mesa no fue desasignada correctamente");
	    	
	   }
	    
	
	
}
