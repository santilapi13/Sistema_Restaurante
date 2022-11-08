package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import excepciones.ContrasenaIncorrectaException;
import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.MozoInexistenteException;
import excepciones.MozoNoDisponibleException;
import modelo.*;
import vista.*;

public class ControladorOperario implements ActionListener {

	private IVistaLogin vista = null;                    //si hacemos interfaz de user cambiar
	private static ControladorOperario instance = null;
	
	private ControladorOperario() {
	}

	public static ControladorOperario getInstance() {
		if (instance == null)
			instance = new ControladorOperario();
		return instance;
	}

	public void setVista(IVistaLogin vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
		
		this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
		this.vista.ActualizarMozos(Cerveceria.getInstance().getMozos());
		this.vista.ActualizarComandas(Cerveceria.getInstance().getComandasAbiertas());
		this.vista.ActualizarVentas(Cerveceria.getInstance().getVentas());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		try {
		if (comando.equalsIgnoreCase("CAMBIAR CONTRASENA")) {	// Abre ventana de cambiar contrasena
			this.vista.cerrarse();
			ControladorOperario.getInstance().setVista(new VContrasena());
		  }
		
		else if (comando.equalsIgnoreCase("APLICAR CAMBIOS")) {	// Cambia la contrasena
			String passActual = this.vista.getPasswordActual();
			String pass = this.vista.getPassword();
			
			Cerveceria.getInstance().getOperarioLogueado().cambiarContrasena(pass, passActual);
		
			this.vista.cerrarse();
			ControladorLogin.getInstance().setVista(new VLogin());
			}
		
		else if (comando.equalsIgnoreCase("APLICAR") && !this.vista.getIsMozoEmpty()) {
			EstadoMozo estado = this.vista.getEstadoMozo();
			String nya = this.vista.getMozoSeleccionado().getNya();
			
			Cerveceria.getInstance().getOperarioLogueado().setEstado(nya, estado);
			this.vista.ActualizarMozos(Cerveceria.getInstance().getMozos());
		}
		
		else if (comando.equalsIgnoreCase("ASIGNAR") && !this.vista.getIsMozoEmpty() && !this.vista.getIsMesaEmpty()) {
			int nromesa = this.vista.getMesaSeleccionada().getNroMesa();
			String nya = this.vista.getMozoSeleccionado().getNya();
			
			Cerveceria.getInstance().getOperarioLogueado().asignarMesa(nya, nromesa);
			this.vista.ActualizarMozos(Cerveceria.getInstance().getMozos());
			this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
		}
		
		else if ((comando.equalsIgnoreCase("TOMAR COMANDA")  && !this.vista.getIsMesaEmpty()))
		{
			int nromesa = this.vista.getMesaSeleccionada().getNroMesa();
			
			
			this.vista.cerrarse();
			ControladorComanda.getInstance().setVista(new VComanda(), nromesa);
		}
		
		else if (comando.equalsIgnoreCase("SALIR")) {
			this.vista.cerrarse();
			ControladorLogin.getInstance().setVista(new VLogin());
		}
		
		}catch (ContrasenaIncorrectaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MozoInexistenteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MozoNoDisponibleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MesaInexistenteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MesaNoDisponibleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

}
