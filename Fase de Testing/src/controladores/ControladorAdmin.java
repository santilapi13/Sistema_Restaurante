package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.Cerveceria;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import negocio.Sistema;
import presentacion.IVistaAdmin;
import presentacion.VAdmin;
import presentacion.VLogin;
import presentacion.VMesa;
import presentacion.VMozo;
import presentacion.VOperario;
import presentacion.VProducto;
import presentacion.VPromocion;
import presentacion.VRegistroOp;

public class ControladorAdmin implements ActionListener {

	private IVistaAdmin vista = null;
	private static ControladorAdmin instance = null;

	private ControladorAdmin() {
		if (Cerveceria.getInstance().getAdmin().editado == false) {
			setNuevaContrasena();
			Sistema.getInstance().persistir();
		}
	}
	
	private void setNuevaContrasena() {
		String pass = JOptionPane.showInputDialog(null, "Ingrese nueva contraseña para ADMIN");
		while (pass == null || pass.isEmpty()|| pass.length()<6|| pass.length()>12|| !verifica(pass))
			pass = JOptionPane.showInputDialog(null, "Contraseña invalida.Ingrese nueva contraseña para ADMIN");
		Cerveceria.getInstance().getAdmin().setPassword(pass);
		Cerveceria.getInstance().getAdmin().editado = true;
	}
	
	public boolean verifica(String password) {
		
		int i=0;
		boolean mayus=false,num=false;
		
		while(i<password.length() && (!mayus || !num)){
			
	    	if(Character.isUpperCase(password.charAt(i)))
	    		mayus=true;
	    	
	    	else if (Character.isDigit(password.charAt(i)))
	        	num =true;
		
			i++;
	    }
		return (mayus&&num);
	}

	public static ControladorAdmin getInstance() {
		if (instance == null)
			instance = new ControladorAdmin();
		return instance;
	}

	public IVistaAdmin getVista() {
		return vista;
	}

	public void setVista(IVistaAdmin vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equalsIgnoreCase("REGISTRAR_MOZO")) {
			String nya = this.vista.getNombre();
			int cantHijos = this.vista.getCant();
			Date fechaNac = this.vista.getFecha();
			Mozo mozo = this.vista.getSelectedMozo();
			if (mozo != null) { // si existe es porque se quiere modificar
				mozo.setCantHijos(cantHijos);
				mozo.setNya(nya);
				this.vista.cerrarse();
				this.setVista(new VAdmin());
				Sistema.getInstance().actualizarListaAdmin();
				this.vista.notificar("Mozo modificado");
				Sistema.getInstance().persistir();
			} else
				Sistema.getInstance().addMozo(nya, fechaNac, cantHijos);
		} else if (comando.equalsIgnoreCase("REGISTRAR_OPERARIO")) {
			String nya = this.vista.getNombre();
			String user = this.vista.getUsername();
			String pass = this.vista.getPassword();
			boolean act = this.vista.getActivo();
			Operario op = this.vista.getSelectedOperario();
			if (op != null) {
				op.setActivo(act);
				op.setNya(nya);
				op.setUsername(user);
				op.setPassword(pass);
				this.vista.cerrarse();
				this.setVista(new VAdmin());
				Sistema.getInstance().actualizarListaAdmin();
				Sistema.getInstance().persistir();
				this.vista.notificar("Operario modificado");
			} else
				Sistema.getInstance().addOperario(nya, user, pass);
		} else if (comando.equalsIgnoreCase("REGISTRAR_PRODUCTO")) {
			String nombre = this.vista.getNombre();
			double pCosto = this.vista.getPCosto();
			double pVenta = this.vista.getPVenta();
			int stock = this.vista.getCant();
			Producto producto = this.vista.getSelectedProducto();
			if (producto != null) {
				producto.setNombre(nombre);
				producto.setpCosto(pCosto);
				producto.setpVenta(pVenta);
				producto.setStock(stock);
				this.vista.cerrarse();
				this.setVista(new VAdmin());
				Sistema.getInstance().actualizarListaAdmin();
				Sistema.getInstance().persistir();
				this.vista.notificar("Producto modificado");
			} else
				Sistema.getInstance().addProducto(nombre, pCosto, pVenta, stock);
		} else if (comando.equalsIgnoreCase("REGISTRAR_MESA")) {
			try {
				int cantComensales = this.vista.getCant();
				int nroMesa = this.vista.getNroComensales();
				Mesa mesa = this.vista.getSelectedMesa();
				if (mesa != null) {
					mesa.setCantComensales(cantComensales);
					this.vista.cerrarse();
					this.setVista(new VAdmin());
					Sistema.getInstance().actualizarListaAdmin();
					Sistema.getInstance().persistir();
					this.vista.notificar("Mesa modificada");
				} else
					Sistema.getInstance().addMesa(cantComensales, nroMesa);
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(null, "Se debe ingresar un caracter numercio");
			}
		} else if (comando.equalsIgnoreCase("SALIR_ADMIN")) {
			this.vista.cerrarse();
			ControladorLogin.getInstance().setVista(new VLogin());
			Sistema.getInstance().persistir();
		} else if (comando.equalsIgnoreCase("OPERARIO")) {
			this.vista.cerrarse();
			this.setVista(new VRegistroOp());
		} else if (comando.equalsIgnoreCase("PRODUCTO")) {
			this.vista.cerrarse();
			this.setVista(new VProducto());
		} else if (comando.equalsIgnoreCase("MESA")) {
			this.vista.cerrarse();
			this.setVista(new VMesa());
		} else if (comando.equalsIgnoreCase("PROMOCION")) {
			this.vista.cerrarse();
			ControladorPromo.getInstance().setVista(new VPromocion(Cerveceria.getInstance().getProductos()));
		} else if (comando.equalsIgnoreCase("MOZO")) {
			this.vista.cerrarse();
			this.setVista(new VMozo());
		} else if (comando.equalsIgnoreCase("ELIMINAR_MOZO")) {
			Mozo mozo = this.vista.getSelectedMozo();
			Sistema.getInstance().deleteMozo(mozo);
		} else if (comando.equalsIgnoreCase("ELIMINAR_OP")) {
			Operario op = this.vista.getSelectedOperario();
			Sistema.getInstance().deleteOperario(op);
		} else if (comando.equalsIgnoreCase("ELIMINAR_MESA")) {
			Mesa mesa = this.vista.getSelectedMesa();
			Sistema.getInstance().deleteMesa(mesa);
		} else if (comando.equalsIgnoreCase("ELIMINAR_PRODUCTO")) {
			Producto producto = this.vista.getSelectedProducto();
			Sistema.getInstance().deleteProducto(producto);
		} else if (comando.equalsIgnoreCase("ELIMINAR_PROMOT")) {
			PromoTemporal promo = this.vista.getSelectedPromoTemp();
			Sistema.getInstance().deletePromoTemp(promo);
		} else if (comando.equalsIgnoreCase("ELIMINAR_PROMOP")) {
			PromoProducto promo = this.vista.getSelectedPromoProd();
			Sistema.getInstance().deletePromoProducto(promo);
		} else if (comando.equalsIgnoreCase("MODIFICAR_MOZO")) {
			Mozo mozo = this.vista.getSelectedMozo();
			this.vista.cerrarse();
			this.setVista(new VMozo());
			this.vista.setMozo(mozo);
			Sistema.getInstance().actualizarListaAdmin();
		} else if (comando.equalsIgnoreCase("MODIFICAR_MESA")) {
			Mesa mesa = this.vista.getSelectedMesa();
			this.vista.cerrarse();
			this.setVista(new VMesa());
			this.vista.setMesa(mesa);
		} else if (comando.equalsIgnoreCase("MODIFICAR_OP")) {
			Operario op = this.vista.getSelectedOperario();
			this.vista.cerrarse();
			this.setVista(new VRegistroOp());
			this.vista.setOperario(op);
		} else if (comando.equalsIgnoreCase("MODIFICAR_PRODUCTO")) {
			Producto producto = this.vista.getSelectedProducto();
			this.vista.cerrarse();
			this.setVista(new VProducto());
			this.vista.setProducto(producto);
		} else if (comando.equalsIgnoreCase("MODIFICAR_PROMOT")) {
			PromoTemporal promo = this.vista.getSelectedPromoTemp();
			this.vista.cerrarse();
			ControladorPromo.getInstance().setVista(new VPromocion(Cerveceria.getInstance().getProductos()));
			ControladorPromo.getInstance().getVista().setPromoTemp(promo);
		} else if (comando.equalsIgnoreCase("MODIFICAR_PROMOP")) {
			PromoProducto promo = this.vista.getSelectedPromoProd();
			this.vista.cerrarse();
			ControladorPromo.getInstance().setVista(new VPromocion(Cerveceria.getInstance().getProductos()));
			ControladorPromo.getInstance().getVista().setPromoProd(promo);
		} else if (comando.equalsIgnoreCase("SALIR")) {
			ControladorAdmin.getInstance().getVista().cerrarse();
			ControladorAdmin.getInstance().setVista(new VAdmin());
			Sistema.getInstance().actualizarListaAdmin();
		} else if (comando.equalsIgnoreCase("INFORME MOZOS"))
			Sistema.getInstance().informeMozos();
	}

}
