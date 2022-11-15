package presentacion;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Comanda;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Pedido;
import modelo.Producto;
import modelo.Venta;

public interface IVistaOperario {
	void cerrarse();

	void setActionListener(ActionListener actionListener);

	Mozo getSelectedMozo();

	Mesa getSelectedMesa();
	
	String getSelectedPago();

	void actualizaListaVenta(ArrayList<Venta> ventas);

	void actualizaListaComanda(ArrayList<Comanda> comandas);

	void actualizaListaMesas(ArrayList<Mesa> mesas);

	void actualizaListaMozos(ArrayList<Mozo> mozos);
	
	void actualizaListaProducto(ArrayList<Producto> productos);
	
	Producto getProducto();
	
	double getCant();
	
	void agregaPedido(Pedido pedido);
	
	void borraPedido(Pedido pedido);
	
	Pedido getPedido();
	
	ArrayList<Pedido> getListaPedidos();

	void notificar(String noti);
}
