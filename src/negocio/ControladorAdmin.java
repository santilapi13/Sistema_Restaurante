package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import excepciones.ContrasenaIncorrectaException;
import excepciones.MozoRepetidoException;
import excepciones.OperarioRepetidoException;
import excepciones.ProductoRepetidoException;
import modelo.Cerveceria;
import vista.*;

public class ControladorAdmin  implements ActionListener {

	private IVistaLogin vista = null;                    //si hacemos interfaz de user cambiar
	private static ControladorAdmin instance = null;
    String tipo = null;


	public static ControladorAdmin getInstance() {
		if (instance == null) {
			instance = new ControladorAdmin();
		}
		return instance;
	}

	public void setVista(IVistaLogin vista, String tipo) {
		this.vista = vista;
		this.vista.setActionListener(this);
		this.tipo = tipo;
		this.vista.ActualizarListaOperarios(Cerveceria.getInstance().getOperarios());
		this.vista.ActualizarMozos(Cerveceria.getInstance().getMozos());
		this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
		this.vista.ActualizarProductos(Cerveceria.getInstance().getCarta());
	}
	
	public void setVista(IVistaLogin vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
		this.vista.ActualizarListaOperarios(Cerveceria.getInstance().getOperarios());
		this.vista.ActualizarMozos(Cerveceria.getInstance().getMozos());
		this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
		this.vista.ActualizarProductos(Cerveceria.getInstance().getCarta());
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = e.getActionCommand();
		
		try {
		  if (comando.equalsIgnoreCase("Aplicar Cambios")) {  //aca para diferenciar si es admin o no.
			
			String passActual = this.vista.getPasswordActual();
			String pass = this.vista.getPassword();
	        
			Cerveceria.getInstance().getAdmin().cambiarContrasena(pass, passActual);
			this.vista.cerrarse();
		    ControladorLogin.getInstance().setVista(new VLogin());
		   }
		  else if (comando.equalsIgnoreCase("contrasena")) {
			  this.vista.cerrarse();
			  ControladorAdmin.getInstance().setVista(new VContraseña());
		  }
		  else if (comando.equalsIgnoreCase("Agregar Operario"))  {           //REGISTROS
			  this.vista.cerrarse();
			  ControladorAdmin.getInstance().setVista(new VRegOp(), "registroOP");
		  }
		  else if ((comando.equalsIgnoreCase("ACEPTAR") && this.tipo.equalsIgnoreCase("registroOP"))) {
			  String nya = this.vista.getNya();
			  String pass = this.vista.getPassword();
			  String username = this.vista.getUsername();
			  
			  Cerveceria.getInstance().getAdmin().registrarOperario(nya, username, pass);
			  this.vista.cerrarse();
			  ControladorAdmin.getInstance().setVista(new VAdmin());
		  }
		  else if (comando.equalsIgnoreCase("Agregar Mozo")) {
			  this.vista.cerrarse();
			  ControladorAdmin.getInstance().setVista(new VRegMozo(), "RegistroMo");
		  }
		  else if ((comando.equalsIgnoreCase("ACEPTAR") && this.tipo.equalsIgnoreCase("RegistroMo"))) {
			  String nya = this.vista.getNya();
			  int hijos = this.vista.getHijos();
			  String fecha = this.vista.fecha(); 
			  
			  Cerveceria.getInstance().getAdmin().agregarMozo(nya, hijos, fecha);
			  this.vista.cerrarse();
			  ControladorAdmin.getInstance().setVista(new VAdmin());
		  }
		  else if (comando.equalsIgnoreCase("Agregar Mesa")) {
			  Cerveceria.getInstance().getAdmin().agregarMesa(this.vista.getComensales());
			  this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());  
		  }
		  else if (comando.equalsIgnoreCase("Agregar Producto")) {
			  this.vista.cerrarse();
			  ControladorAdmin.getInstance().setVista(new VProducto(), "RegistroProd");
		  }
		  else if ((comando.equalsIgnoreCase("ACEPTAR") && this.tipo.equalsIgnoreCase("RegistroProd"))) {    //FIN REGISTROS
			  String nombre = this.vista.getNya();
			  double pVenta = this.vista.pVenta();
			  double pCosto = this.vista.pCosto();
			  int Stock =  this.vista.stock();
			  
			  Cerveceria.getInstance().getAdmin().agregarProducto(nombre, pCosto, pVenta, Stock);
			  this.vista.cerrarse();
			  ControladorAdmin.getInstance().setVista(new VAdmin());
		  }
		   /////////////////////////MODIFICACIONES, LLAMA A OTRO CONTROLADOR, PORQUE HAY Q PASAR UN OBJETO.
		  else if  (comando.equalsIgnoreCase("Modificar") && !this.vista.getIsProductoEmpty()) {    //SOLO PUSE EJEMPLO DE LLAMADA DE PRODUCTO
			  this.vista.cerrarse();            
			  ControladorModificaciones.getInstance().setVista(new VProducto(), this.vista.getProdSeleccionado());
		  }

		 
		} catch (ContrasenaIncorrectaException | OperarioRepetidoException | MozoRepetidoException | ProductoRepetidoException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}	
		
	}
	
}
