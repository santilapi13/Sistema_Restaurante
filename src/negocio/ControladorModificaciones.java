package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.*;
import vista.IVistaLogin;

public class ControladorModificaciones implements ActionListener {

	private IVistaLogin vista = null;                    
	private static ControladorModificaciones instance = null;
	Operario op = null;
    Producto prod = null;
    Mozo mozo = null;
    Promocion prom = null;
    Mesa mesa = null;

	public static ControladorModificaciones getInstance() {
		if (instance == null) {
			instance = new ControladorModificaciones();
		}
		return instance;
	}
               //DEPENDIENDO LO QUE SE MODIFIQUE 5 METODOS DE SET VISTA C DISTINTOS PARAMETROS.
	public void setVista(IVistaLogin vista, Producto prod) {
		this.vista = vista;
		this.vista.setActionListener(this);
		this.prod = prod;
	}
	
	public void setVista(IVistaLogin vista, Operario op) {
		this.vista = vista;
		this.vista.setActionListener(this);
		this.op = op;
	}
	
	public void setVista(IVistaLogin vista, Mozo mozo) {
		this.vista = vista;
		this.vista.setActionListener(this);
		this.mozo = mozo;
	}
	
	public void setVista(IVistaLogin vista, Mesa mesa) {
		this.vista = vista;
		this.vista.setActionListener(this);
		this.mesa = mesa;
	}
	
	public void setVista(IVistaLogin vista, Promocion prom) {
		this.vista = vista;
		this.vista.setActionListener(this);
		this.prom = prom;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if ((comando.equalsIgnoreCase("Aceptar") &&  prod != null))
		{
			   //llamar metodo modificar producto
		}
		 else if ((comando.equalsIgnoreCase("Aceptar") &&  op != null))
		 {
			//Llamar metodo modificar operario 
		 }
		 else if ((comando.equalsIgnoreCase("Aceptar") &&  mozo != null)) {
			//llamar metodo modificar mozo
		 }
		 else if ((comando.equalsIgnoreCase("Aceptar") &&  mesa != null)) {
			//Llamar metodo modificar mesa
		 }
		 else if ((comando.equalsIgnoreCase("Aceptar") &&  prom != null)) {
			//llamar metodo modificar prom
		 }
		
		
	}
	
}
