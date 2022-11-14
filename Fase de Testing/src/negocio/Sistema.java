package negocio;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import controladores.ControladorAdmin;
import controladores.ControladorLogin;
import controladores.ControladorOperario;
import controladores.ControladorPromo;
import excepciones.ComandaAbiertaException;
import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.MesaRepetidaException;
import excepciones.MozoInexistenteException;
import excepciones.MozoNoDisponibleException;
import excepciones.MozoRepetidoException;
import excepciones.ProductoEnComandaException;
import excepciones.ProductoInexistenteException;
import excepciones.ProductoRepetidoException;
import excepciones.PromoRepetidaException;
import excepciones.UsuarioRepetidoException;
import modelo.Cerveceria;
import modelo.Estado;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import presentacion.VAdmin;
import presentacion.VOperario;
import presentacion.VPedido;

public class Sistema {

	private static Sistema instance = null;
	private Cerveceria cerveceria = Cerveceria.getInstance();
	private Operario opLogueado;

	private Sistema() {

	}

	public static Sistema getInstance() {
		if (instance == null)
			instance = new Sistema();
		return instance;
	}

	public Operario getOpLogueado() {
		return opLogueado;
	}

	public void setOpLogueado(Operario opLogueado) {
		this.opLogueado = opLogueado;
	}

	public void loguear(String user, String pass) {
		if (cerveceria.getAdmin().getUsername().equals(user))
			if (cerveceria.getAdmin().getPassword().equals(pass)) {
				ControladorLogin.getInstance().getVista().cerrarse();
				ControladorAdmin.getInstance().setVista(new VAdmin());
				ControladorAdmin.getInstance().getVista().actualizaListaMesas(cerveceria.getMesas());
				ControladorAdmin.getInstance().getVista().actualizaListaMozos(cerveceria.getMozos());
				ControladorAdmin.getInstance().getVista().actualizaListaProductos(cerveceria.getProductos());
				ControladorAdmin.getInstance().getVista().actualizaListaOperarios(cerveceria.getOperarios());
				ControladorAdmin.getInstance().getVista().actualizaListaPromoTemp(cerveceria.getPromoTemporales());
				ControladorAdmin.getInstance().getVista().actualizaListaPromoProd(cerveceria.getPromosProducto());
			} else
				JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrecta");
		else {
			Operario auxOp = cerveceria.getOperario(user);
			if (auxOp != null && auxOp.getPassword().equals(pass))
				if (auxOp.isActivo()) {
					this.opLogueado = auxOp;
					ControladorLogin.getInstance().getVista().cerrarse();
					ControladorOperario.getInstance().setVista(new VOperario());
					ControladorOperario.getInstance().getVista()
							.actualizaListaComanda(cerveceria.getComandasAbiertas());
					ControladorOperario.getInstance().getVista().actualizaListaMesas(cerveceria.getMesas());
					ControladorOperario.getInstance().getVista().actualizaListaMozos(cerveceria.getMozos());
					ControladorOperario.getInstance().getVista().actualizaListaVenta(cerveceria.getVentas());
				} else
					JOptionPane.showMessageDialog(null, "Usuario inactivo");
			else
				JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrecta");
		}
	}

	@SuppressWarnings("deprecation")
	public void addMozo(String nya, Date fecha, int cantHijos) {

		try {
			int edad = Date.from(Instant.now()).getYear() - fecha.getYear();

			if (edad > 18 || (edad == 18 && fecha.getMonth() <= Date.from(Instant.now()).getMonth())) {
				cerveceria.getAdmin().addMozo(nya.toUpperCase(), fecha, cantHijos);
				ControladorAdmin.getInstance().getVista().cerrarse();
				ControladorAdmin.getInstance().setVista(new VAdmin());
				ControladorAdmin.getInstance().getVista().notificar("Mozo registrado correctamente");
				actualizarListaAdmin();
				persistir();

			} else
				ControladorAdmin.getInstance().getVista()
						.notificar("No se puede registrar mozo, debe ser mayor a 18 años");
		} catch (MozoRepetidoException e) {
			ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
		}

	}

	public void addOperario(String nya, String user, String pass) {

		if (verifica(pass)) {
			try {
				cerveceria.getAdmin().addOperario(nya, user, pass);
				ControladorAdmin.getInstance().getVista().cerrarse();
				ControladorAdmin.getInstance().setVista(new VAdmin());
				ControladorAdmin.getInstance().getVista().notificar("Operario registrado correctamente");
				actualizarListaAdmin();
				persistir();
			} catch (UsuarioRepetidoException e) {
				ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
			}

		} else
			JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 1 digito y 1 mayuscula");
	}

	public boolean verifica(String password) {

		int i = 0;
		boolean mayus = false, num = false;

		while (i < password.length() && (!mayus || !num)) {

			if (Character.isUpperCase(password.charAt(i)))
				mayus = true;

			else if (Character.isDigit(password.charAt(i)))
				num = true;

			i++;
		}
		return (mayus && num);
	}

	public void addProducto(String nombre, double pCosto, double pVenta, int stock) {
		try {
			cerveceria.getAdmin().addProducto(nombre, pCosto, pVenta, stock);

			ControladorAdmin.getInstance().getVista().cerrarse();
			ControladorAdmin.getInstance().setVista(new VAdmin());
			ControladorAdmin.getInstance().getVista().notificar("Producto registrado correctamente");
			actualizarListaAdmin();
			persistir();
		} catch (ProductoRepetidoException e) {
			JOptionPane.showMessageDialog(null, "Producto ya existente");
		}
	}

	public void addMesa(int cantComensales, int nroMesa) {
		try {
			cerveceria.getAdmin().addMesa(cantComensales, nroMesa);
			ControladorAdmin.getInstance().getVista().cerrarse();
			ControladorAdmin.getInstance().setVista(new VAdmin());
			ControladorAdmin.getInstance().getVista().notificar("Mesa registrada correctamente");
			actualizarListaAdmin();
			persistir();
		} catch (MesaRepetidaException e) {
			ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
		}
	}

	public void addPromoTemporal(ArrayList<String> dias, String nombre, String formaPago, int porcentaje,
			boolean acumulable) {
		try {
			cerveceria.getAdmin().addPromoTemporal(dias, nombre, formaPago, porcentaje, acumulable);
			ControladorPromo.getInstance().getVista().cerrarse();
			ControladorAdmin.getInstance().setVista(new VAdmin());
			ControladorAdmin.getInstance().getVista().notificar("Promocion registrada correctamente");
			actualizarListaAdmin();
			persistir();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public void addPromoProducto(ArrayList<String> dias, Producto producto, boolean dosPorUno, boolean dtoPorCantidad,
			int cantMinima, double precioUnit) {
		try {
			cerveceria.getAdmin().addPromoProducto(dias, producto, dosPorUno, dtoPorCantidad, cantMinima, precioUnit);
			ControladorPromo.getInstance().getVista().cerrarse();
			ControladorAdmin.getInstance().setVista(new VAdmin());
			ControladorAdmin.getInstance().getVista().notificar("Promocion registrada correctamente");
			actualizarListaAdmin();
			persistir();
		} catch (ProductoInexistenteException e) {
			ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
		} catch (PromoRepetidaException e) {
			ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
		}

	}

	public void deleteProducto(Producto producto) {
		try {
			cerveceria.getAdmin().deleteProducto(producto);
			ControladorAdmin.getInstance().getVista().actualizaListaProductos(cerveceria.getProductos());
			ControladorAdmin.getInstance().getVista().notificar("Producto eliminado correctamente");
			persistir();
		} catch (Exception e) {
			ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
		}
	}

	public void deleteMesa(Mesa mesa) {
		try {
			cerveceria.getAdmin().deleteMesa(mesa);
			ControladorAdmin.getInstance().getVista().actualizaListaMesas(cerveceria.getMesas());
			ControladorAdmin.getInstance().getVista().notificar("Mesa eliminada correctamente");
			persistir();

		} catch (Exception e) {
			ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
		}
	}

	public void deletePromoTemp(PromoTemporal promocion) {
		try {
			cerveceria.getAdmin().deletePromoTemporal(promocion);
			ControladorAdmin.getInstance().getVista().actualizaListaPromoTemp(cerveceria.getPromoTemporales());
			ControladorAdmin.getInstance().getVista().notificar("Promocion eliminada correctamente");
			persistir();
		} catch (Exception e) {
			ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
		}

	}

	public void deletePromoProducto(PromoProducto promocion) {
		try {
			cerveceria.getAdmin().deletePromoProducto(promocion);
			ControladorAdmin.getInstance().getVista().actualizaListaPromoProd(cerveceria.getPromosProducto());
			ControladorAdmin.getInstance().getVista().notificar("Promocion eliminada correctamente");
			persistir();
		}

		catch (Exception e) {
			ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
		}
	}

	public void deleteMozo(Mozo mozo) {
		try {
			cerveceria.getAdmin().deleteMozo(mozo);
			ControladorAdmin.getInstance().getVista().actualizaListaMozos(cerveceria.getMozos());
			ControladorAdmin.getInstance().getVista().notificar("Mozo eliminado correctamente");
			persistir();
		} catch (Exception e) {
			ControladorAdmin.getInstance().getVista().notificar(e.getMessage());
		}

	}

	public void deleteOperario(Operario op) {
		try {
			cerveceria.getAdmin().deleteOperario(op);
			ControladorAdmin.getInstance().getVista().actualizaListaOperarios(cerveceria.getOperarios());
			ControladorAdmin.getInstance().getVista().notificar("Operario eliminado correctamente");
			persistir();
		} catch (Exception e) {
			ControladorAdmin.getInstance().getVista().notificar("Operario eliminado correctamente");
		}

	}

	public void actualizarListaAdmin() {
		ControladorAdmin.getInstance().getVista().actualizaListaMesas(cerveceria.getMesas());
		ControladorAdmin.getInstance().getVista().actualizaListaMozos(cerveceria.getMozos());
		ControladorAdmin.getInstance().getVista().actualizaListaProductos(cerveceria.getProductos());
		ControladorAdmin.getInstance().getVista().actualizaListaOperarios(cerveceria.getOperarios());
		ControladorAdmin.getInstance().getVista().actualizaListaPromoTemp(cerveceria.getPromoTemporales());
		ControladorAdmin.getInstance().getVista().actualizaListaPromoProd(cerveceria.getPromosProducto());
	}

	public void setEstadoMozo(Mozo mozo, String estado) {
		try {
			if (estado.equalsIgnoreCase("ACTIVO"))
				opLogueado.setEstado(mozo, Estado.ACTIVO);
			else if (estado.equalsIgnoreCase("FRANCO"))
				opLogueado.setEstado(mozo, Estado.FRANCO);
			else if (estado.equalsIgnoreCase("AUSENTE"))
				opLogueado.setEstado(mozo, Estado.AUSENTE);
			ControladorOperario.getInstance().getVista().actualizaListaMozos(cerveceria.getMozos());
			ControladorOperario.getInstance().getVista().notificar("Mozo establecido como " + estado.toUpperCase());
			persistir();
		} catch (MozoInexistenteException e) {
			ControladorOperario.getInstance().getVista().notificar(e.getMessage());
		}

	}

	public void asignarMesa(Mozo mozo, Mesa mesa) {
		try {
			this.opLogueado.asignarMesa(mozo, mesa);
			ControladorOperario.getInstance().getVista().actualizaListaMesas(cerveceria.getMesas());
			ControladorOperario.getInstance().getVista().notificar("Mesa asignada correctamente");
			cerveceria.persistir();
		} 
		catch (Exception e) {
			ControladorOperario.getInstance().getVista().notificar(e.getMessage());
		}

	}

	public void cerrarMesa(Mesa mesa, String formaPago) {
		try {
			this.opLogueado.cerrarMesa(mesa, formaPago);
			ControladorOperario.getInstance().getVista().notificar("Mesa cerrada correctamente");
			ControladorOperario.getInstance().getVista().actualizaListaMesas(cerveceria.getMesas());
			ControladorOperario.getInstance().getVista().actualizaListaMozos(cerveceria.getMozos());
			ControladorOperario.getInstance().getVista().actualizaListaVenta(cerveceria.getVentas());
			ControladorOperario.getInstance().getVista().actualizaListaComanda(cerveceria.getComandasAbiertas());
			persistir();
		} catch (Exception e) {
			ControladorOperario.getInstance().getVista().notificar("La mesa no tiene una comanda abierta");
		}
	}

	public void getEstadisticas(Mozo mozo) {
		String informe = this.opLogueado.getEstadisticas(mozo);
		ControladorOperario.getInstance().getVista().notificar(informe);
	}

	public void agregaPedido(Producto producto, double cant) {
		if (producto.getStock() - cant < 0)
			ControladorOperario.getInstance().getVista().notificar("No se puede tomar el pedido, stock insuficiente");
		else {
			ControladorOperario.getInstance().getVista().agregaPedido(new Pedido(producto, cant));
		}
	}

	public void tomarPedido(Mesa mesa) {
		if (mesa.isAsignado()) {
			ControladorOperario.getInstance().getVista().cerrarse();
			ControladorOperario.getInstance().setVista(new VPedido(mesa));
			ControladorOperario.getInstance().getVista()
					.actualizaListaProducto(Cerveceria.getInstance().getProductos());
		} else
			ControladorOperario.getInstance().getVista().notificar("Mesa sin asignar");
	}

	public void enviarPedido(Mesa mesa, ArrayList<Pedido> pedidos) {
		try {
			this.opLogueado.tomarPedido(mesa, pedidos);
			ControladorOperario.getInstance().getVista().cerrarse();
			ControladorOperario.getInstance().setVista(new VOperario());
			ControladorOperario.getInstance().getVista().actualizaListaMesas(cerveceria.getMesas());
			ControladorOperario.getInstance().getVista().actualizaListaMozos(cerveceria.getMozos());
			ControladorOperario.getInstance().getVista().actualizaListaVenta(cerveceria.getVentas());
			ControladorOperario.getInstance().getVista().actualizaListaComanda(cerveceria.getComandasAbiertas());
			ControladorOperario.getInstance().getVista()
					.notificar("Pedido de la mesa #" + mesa.getNroMesa() + " tomado correctamente");
			persistir();
		} catch (Exception e) {
			ControladorOperario.getInstance().getVista().notificar(e.getMessage());
		}

	}

	public void persistir() {
		cerveceria.persistir();
	}

	public void informeMozos() {
		String informe = cerveceria.getInformeMozos();
		ControladorAdmin.getInstance().getVista().notificar(informe);

	}

	public void liberarMesasDesocupadas() {
			cerveceria.liberarMesasDesocupadas();
			ControladorOperario.getInstance().getVista().notificar("Se desasignaron los mozos de todas las mesas que estaban desocupadas");
			ControladorOperario.getInstance().getVista().actualizaListaMesas(cerveceria.getMesas());
	}
	
}
