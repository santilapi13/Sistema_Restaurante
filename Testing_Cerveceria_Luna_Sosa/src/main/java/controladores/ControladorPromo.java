package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import negocio.Sistema;
import presentacion.IVistaPromo;
import presentacion.VAdmin;

public class ControladorPromo implements ActionListener {

	private IVistaPromo vista = null;
	private static ControladorPromo instance = null;

	private ControladorPromo() {
	}

	public static ControladorPromo getInstance() {
		if (instance == null)
			instance = new ControladorPromo();
		return instance;
	}

	public IVistaPromo getVista() {
		return vista;
	}

	public void setVista(IVistaPromo vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equalsIgnoreCase("ENVIAR")) {
			ArrayList<String> dias = this.vista.getDias();
			boolean act = this.vista.getActivo();
			if (vista.getTipoPromo().equalsIgnoreCase("TEMPORAL")) {
				PromoTemporal promo = (PromoTemporal) this.vista.getPromocion();
				String nombre = this.vista.getNombre();
				String formaPago = this.vista.getFormaPago();
				int porcentaje = this.vista.getPorcentaje();
				boolean acumulable = this.vista.getAcumulable();
				if (promo != null) {
					promo.setActiva(act);
					promo.setDiasDePromo(dias);
					promo.setEsAcumulable(acumulable);
					promo.setFormaPago(formaPago);
					promo.setNombre(nombre);
					promo.setPorcentajeDesc(porcentaje);
					this.vista.cerrarse();
					ControladorAdmin.getInstance().setVista(new VAdmin());
					Sistema.getInstance().actualizarListaAdmin();
					Sistema.getInstance().persistir();
					ControladorAdmin.getInstance().getVista().notificar("Promocion modificada");
				} else
					Sistema.getInstance().addPromoTemporal(dias, nombre, formaPago, porcentaje, acumulable);
			} else {
				PromoProducto promo = (PromoProducto) this.vista.getPromocion();
				Producto producto = this.vista.getProducto();
				int cant = this.vista.getCant();
				double precio = this.vista.getPrecio();
				boolean dosPorUno = this.vista.getDosPorUno();
				if (promo != null) {
					promo.setActiva(act);
					promo.setAplicaDosPorUno(dosPorUno);
					promo.setAplicaDtoPorCantidad(!dosPorUno);
					promo.setDiasDePromo(dias);
					promo.setDtoPorCantidad_CantMinima(cant);
					promo.setDtoPorCantidad_PrecioUnit(precio);
					this.vista.cerrarse();
					ControladorAdmin.getInstance().setVista(new VAdmin());
					Sistema.getInstance().actualizarListaAdmin();
					Sistema.getInstance().persistir();
					ControladorAdmin.getInstance().getVista().notificar("Promocion modificada");
				} else
					Sistema.getInstance().addPromoProducto(dias, producto, dosPorUno, !dosPorUno, cant, precio);
			}
		} else if (comando.equalsIgnoreCase("SALIR")) {
			this.vista.cerrarse();
			ControladorAdmin.getInstance().setVista(new VAdmin());
			Sistema.getInstance().actualizarListaAdmin();
		}
	}

}
