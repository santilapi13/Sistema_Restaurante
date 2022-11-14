package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Cerveceria;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Pedido;
import modelo.Producto;
import negocio.Sistema;
import presentacion.IVistaOperario;
import presentacion.VLogin;
import presentacion.VPedido;

public class ControladorOperario implements ActionListener {
	private IVistaOperario vista = null;
	private static ControladorOperario instance = null;

	private ControladorOperario() {
	}

	public static ControladorOperario getInstance() {
		if (instance == null)
			instance = new ControladorOperario();
		return instance;
	}

	public IVistaOperario getVista() {
		return vista;
	}

	public void setVista(IVistaOperario vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equalsIgnoreCase("SALIR")) {
			this.vista.cerrarse();
			ControladorLogin.getInstance().setVista(new VLogin());
		} else if (comando.equalsIgnoreCase("ACTIVO")) {
			Mozo mozo = this.vista.getSelectedMozo();
			Sistema.getInstance().setEstadoMozo(mozo, comando);
		} else if (comando.equalsIgnoreCase("FRANCO")) {
			Mozo mozo = this.vista.getSelectedMozo();
			Sistema.getInstance().setEstadoMozo(mozo, comando);
		} else if (comando.equalsIgnoreCase("AUSENTE")) {
			Mozo mozo = this.vista.getSelectedMozo();
			Sistema.getInstance().setEstadoMozo(mozo, comando);
		} else if (comando.equalsIgnoreCase("ASIGNAR")) {
			Mozo mozo = this.vista.getSelectedMozo();
			Mesa mesa = this.vista.getSelectedMesa();
			Sistema.getInstance().asignarMesa(mozo, mesa);
		} else if (comando.equalsIgnoreCase("CERRAR")) {
			Mesa mesa = this.vista.getSelectedMesa();
			String formaPago = this.vista.getSelectedPago();
			Sistema.getInstance().cerrarMesa(mesa, formaPago);
		} else if (comando.equalsIgnoreCase("PEDIDO")) {
			Mesa mesa = this.vista.getSelectedMesa();
			Sistema.getInstance().tomarPedido(mesa);
			

		} else if (comando.equalsIgnoreCase("ESTADISTICAS")) {
			Mozo mozo = this.vista.getSelectedMozo();
			Sistema.getInstance().getEstadisticas(mozo);
		}else if (comando.equalsIgnoreCase("ENVIAR_PEDIDO")) {
			ArrayList<Pedido> pedidos = this.vista.getListaPedidos();
			Mesa mesa = this.vista.getSelectedMesa();
			Sistema.getInstance().enviarPedido(mesa, pedidos);

		} else if (comando.equalsIgnoreCase("AGREGAR_PEDIDO")) {
			Producto producto = this.vista.getProducto();
			double cant = this.vista.getCant();
			Sistema.getInstance().agregaPedido(producto, cant);
		} else if (comando.equalsIgnoreCase("ELIMINAR_PEDIDO")) {
			Pedido pedido=this.vista.getPedido();
			this.vista.borraPedido(pedido);
		}else if(comando.equalsIgnoreCase("DESASIGNAR")) {
			Sistema.getInstance().liberarMesasDesocupadas();
		}
	}

}
