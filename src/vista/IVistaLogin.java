package vista;

import java.awt.event.ActionListener;
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
	
	public void ActualizarPromociones(ArrayList<Promocion> promociones);
	
	public void ActualizarProductos(ArrayList<Producto> productos);
	
	public boolean getIsProductoEmpty();
	
	public Producto getProdSeleccionado();
	

	
}