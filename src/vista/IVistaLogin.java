package vista;

import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.ArrayList;

import modelo.*;

public interface IVistaLogin {

	void setActionListener(ActionListener actionListener);
	
	void cerrarse();
	
	String getUsername();
	
	String getPassword();
	
	String getTipo();
	
	String getPasswordActual();
	
	String getNya();
	
	int getHijos();
	
	String fecha();
	
	int getComensales();
	
	double pCosto();
	
	double pVenta();
	
	int stock();
	
	public void ActualizarListaOperarios(ArrayList<Operario> operarios);
	
	public void ActualizarMozos(ArrayList<Mozo> mozos);
	
	public void ActualizarMesas(ArrayList<Mesa> mesas);
	
	public void ActualizarPromociones(ArrayList<PromoProducto> promocionesProd, ArrayList<PromoTemporal> promocionesTem);
	
	public void ActualizarProductos(ArrayList<Producto> productos);
	
	public void ActualizarVentas(ArrayList<Venta> ventas);
	
	public void ActualizarComandas(ArrayList<Comanda> comandas);
	
	public void ActualizarPedidos(ArrayList<Pedido> pedidos);
	
	public boolean getIsProductoEmpty();
	
	public Producto getProdSeleccionado();

	public boolean getIsOperarioEmpty();

	public boolean getIsMesaEmpty();

	public boolean getIsMozoEmpty();

	public boolean getIsPromocionProdEmpty();
	
	public boolean getIsPromocionTempEmpty();

	public Operario getOperarioSeleccionado();

	public Mesa getMesaSeleccionada();

	public Mozo getMozoSeleccionado();

	public PromoProducto getPromocionProdSeleccionada();
	
	public PromoTemporal getPromocionTempSeleccionada();

	public boolean getEstadoOperario();
	
	public boolean is2x1();
	
	public boolean isCantidad();
	
	public int getCantMinima();
	
	public double getpUnitario();
	
	public ArrayList<DayOfWeek> getDias();
	
	public FormaPago getFormaPago();
	
	public EstadoMozo getEstadoMozo();
	
	public int getHoraInicio();
	
	public int getHoraFin();
	
	public double getPorcentaje();
	
	public boolean isAcumulable();

	public void deseleccionarTodo();
	
	public void estadisticas(String s);
	
}