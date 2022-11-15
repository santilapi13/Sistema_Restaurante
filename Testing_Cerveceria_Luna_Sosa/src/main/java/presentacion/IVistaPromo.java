package presentacion;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Producto;
import modelo.PromoProducto;
import modelo.PromoTemporal;
import modelo.Promocion;

public interface IVistaPromo {
	void cerrarse();

	void setActionListener(ActionListener actionListener);

	String getTipoPromo();

	boolean getDosPorUno();

	String getNombre();

	String getFormaPago();

	void setPromoTemp(PromoTemporal promo);

	void setPromoProd(PromoProducto promo);

	int getPorcentaje();

	boolean getAcumulable();

	Producto getProducto();

	int getCant();

	double getPrecio();

	ArrayList<String> getDias();

	void cargaProductos(ArrayList<Producto> productos);

	Promocion getPromocion();

	boolean getActivo();

}
