package presentacion;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;

public interface IVistaAdmin {
	void setActionListener(ActionListener actionListener);

	void cerrarse();

	String getUsername();

	String getPassword();

	String getNombre();

	Date getFecha();

	int getCant();

	int getNroComensales();

	double getPVenta();

	double getPCosto();

	Operario getSelectedOperario();

	Mozo getSelectedMozo();

	Mesa getSelectedMesa();

	PromoTemporal getSelectedPromoTemp();

	PromoProducto getSelectedPromoProd();

	Producto getSelectedProducto();

	void setMozo(Mozo mozo);

	void setMesa(Mesa mesa);

	void setOperario(Operario op);

	void setProducto(Producto producto);

	void actualizaListaProductos(ArrayList<Producto> productos);

	void actualizaListaOperarios(ArrayList<Operario> operarios);

	void actualizaListaMesas(ArrayList<Mesa> mesas);

	void actualizaListaPromoTemp(ArrayList<PromoTemporal> promos);

	void actualizaListaPromoProd(ArrayList<PromoProducto> promos);

	void actualizaListaMozos(ArrayList<Mozo> mozos);

	void notificar(String noti);

	boolean getActivo();

}
