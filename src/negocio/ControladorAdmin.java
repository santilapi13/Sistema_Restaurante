package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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

			if (comando.equalsIgnoreCase("MODIFICAR") || comando.equalsIgnoreCase("ELIMINAR")) {
				int opcionesSeleccionadas = 0;
				if (!this.vista.getIsProductoEmpty())
					opcionesSeleccionadas++;
				if (!this.vista.getIsMozoEmpty())
					opcionesSeleccionadas++;
				if (!this.vista.getIsOperarioEmpty())
					opcionesSeleccionadas++;
				if (!this.vista.getIsMesaEmpty())
					opcionesSeleccionadas++;
				if (!this.vista.getIsPromocionEmpty())
					opcionesSeleccionadas++;

				if (opcionesSeleccionadas != 1)
					JOptionPane.showMessageDialog(null, "Debe seleccionar un solo elemento de una unica lista.");
			}

		  	else if (comando.equalsIgnoreCase("CONTRASENA")) {	// Abre ventana de cambiar contrasena
				this.vista.cerrarse();
				ControladorAdmin.getInstance().setVista(new VContrasena());
			  }

			else if (comando.equalsIgnoreCase("APLICAR CAMBIOS")) {	// Cambia la contrasena
				String passActual = this.vista.getPasswordActual();
				String pass = this.vista.getPassword();

				Cerveceria.getInstance().getAdmin().cambiarContrasena(pass, passActual);
				this.vista.cerrarse();
				ControladorLogin.getInstance().setVista(new VLogin());
			}

			// VENTANAS DE AGREGAR COSAS
		  	else if (comando.equalsIgnoreCase("AGREGAR OPERARIO"))  {	// Abre ventana de registrar operario
			  	this.vista.cerrarse();
			  	ControladorAdmin.getInstance().setVista(new VRegOp(), "registroOP");
		  	}
			else if (comando.equalsIgnoreCase("AGREGAR MOZO")) {		// Abre ventana de registrar mozo
				this.vista.cerrarse();
				ControladorAdmin.getInstance().setVista(new VMozo(), "RegistroMo");
			}
			else if (comando.equalsIgnoreCase("AGREGAR MESA")) {		// Agrega una nueva mesa
				Cerveceria.getInstance().getAdmin().agregarMesa(this.vista.getComensales());
				this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
			}
			else if (comando.equalsIgnoreCase("AGREGAR PRODUCTO")) {		// Abre la ventana de agregar producto
				this.vista.cerrarse();
				ControladorAdmin.getInstance().setVista(new VProducto(), "RegistroProd");
			}
			else if (comando.equalsIgnoreCase("AGREGAR PROMOCION")) {		// Abre la ventana de agregar promocion
				this.vista.cerrarse();
				ControladorAdmin.getInstance().setVista(new VPromo(), "RegistroProm");
			}

		  	else if (comando.equalsIgnoreCase("ACEPTAR")) {		// Agregar un nuevo operario, mozo, producto o promocion
				if (this.tipo.equalsIgnoreCase("registroOP")) {
					String nya = this.vista.getNya();
					String pass = this.vista.getPassword();
					String username = this.vista.getUsername();

					Cerveceria.getInstance().getAdmin().registrarOperario(nya, username, pass);
					this.vista.cerrarse();
					ControladorAdmin.getInstance().setVista(new VAdmin());
				}
				else if (this.tipo.equalsIgnoreCase("RegistroMo")) {
					String nya = this.vista.getNya();
					int hijos = this.vista.getHijos();
					String fecha = this.vista.fecha();

					Cerveceria.getInstance().getAdmin().agregarMozo(nya, hijos, fecha);
					this.vista.cerrarse();
					ControladorAdmin.getInstance().setVista(new VAdmin());
				}
				else if (this.tipo.equalsIgnoreCase("RegistroProd")) {
					String nombre = this.vista.getNya();
					double pVenta = this.vista.pVenta();
					double pCosto = this.vista.pCosto();
					int stock =  this.vista.stock();
					if (pVenta < 0 || pCosto < 0 || stock < 0)
						JOptionPane.showMessageDialog(null, "No se puede ingresar valores negativos.");
					else {
						Cerveceria.getInstance().getAdmin().agregarProducto(nombre, pCosto, pVenta, stock);
						this.vista.cerrarse();
						ControladorAdmin.getInstance().setVista(new VAdmin());
					}
				}
				else if (this.tipo.equalsIgnoreCase("RegistroProm")) {
					//TODO
				}
		  	}

			else if  (comando.equalsIgnoreCase("MODIFICAR")) {	// Abre la ventana de modificacion correspondiente
				if (!this.vista.getIsProductoEmpty()) {    // Modificar producto
					this.vista.cerrarse();
					ControladorModificaciones.getInstance().setVista(new VProducto(), this.vista.getProdSeleccionado());
				} else if (!this.vista.getIsMesaEmpty()) {    // Modificar mesa
					Cerveceria.getInstance().modificarMesa(this.vista.getMesaSeleccionada().getNroMesa(), this.vista.getComensales());
					this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
				} else if (!this.vista.getIsMozoEmpty()) {    // Modificar mozo
					this.vista.cerrarse();
					ControladorModificaciones.getInstance().setVista(new VMozo(), this.vista.getMozoSeleccionado());
				} else if (!this.vista.getIsOperarioEmpty()) {    // Modificar operario
					this.vista.cerrarse();
					ControladorModificaciones.getInstance().setVista(new VRegOp(), this.vista.getOperarioSeleccionado());
				} else if (!this.vista.getIsPromocionEmpty()) {    // Modificar promocion
					this.vista.cerrarse();
					ControladorModificaciones.getInstance().setVista(new VPromo(), this.vista.getPromocionSeleccionada());
				}
			}

			else if (comando.equalsIgnoreCase("ELIMINAR")) {
				if (!this.vista.getIsProductoEmpty()) {
					Cerveceria.getInstance().getAdmin().eliminarProducto(this.vista.getProdSeleccionado());
					this.vista.ActualizarProductos(Cerveceria.getInstance().getCarta());
				}
				else if (!this.vista.getIsMesaEmpty()) {
					Cerveceria.getInstance().getAdmin().eliminarMesa(this.vista.getMesaSeleccionada().getNroMesa());
					this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
				}
				else if (!this.vista.getIsMozoEmpty()) {
					Cerveceria.getInstance().getAdmin().eliminarMozo(this.vista.getMozoSeleccionado());
					this.vista.ActualizarMozos(Cerveceria.getInstance().getMozos());
				}
				else if (!this.vista.getIsOperarioEmpty()) {
					Cerveceria.getInstance().getAdmin().eliminarOperario(this.vista.getOperarioSeleccionado());
					this.vista.ActualizarListaOperarios(Cerveceria.getInstance().getOperarios());
				}
				else if (!this.vista.getIsPromocionEmpty()) {
					// TODO : Diferenciar promociones de productos y temporales
					Cerveceria.getInstance().getAdmin().eliminarPromocion(this.vista.getPromocionSeleccionada());
					this.vista.ActualizarPromociones(Cerveceria.getInstance());
				}
			}

			else if (comando.equalsIgnoreCase("SALIR")) {
				this.vista.cerrarse();
				ControladorLogin.getInstance().setVista(new VLogin());
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}	
		
	}
	
}
