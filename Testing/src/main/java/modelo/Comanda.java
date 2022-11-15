package modelo;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
/**
 * Clase Comanda <br>
 * <b>Invariante: </b><br>
 * - fecha != null.
 * - mesa != null.
 * - listaProductos!=null.
 * - estado != null y vacio. estado="abierta" o estado="cerrada".
 */
public class Comanda implements Serializable{
	public Date fecha;
	public Mesa mesa;
	public ArrayList<Pedido> listaProductos = new ArrayList<Pedido>();
	public String estado; // abierta-cerrada

	/**
	 * Instancia una comanda. Se asocia a la mesa pasada por parametro.<br>
	 * <b>Pre:</b> mesa != null.<br>
	 * listaProductos inicializada.<br>
	 * <b>Post:</b> Se intancia una comanda con estado = ABIERTA y tendr� referencia
	 * a la mesa de donde se tomo esa comanda.<br>
	 * 
	 * @param mesa : Mesa a la cual se le asignara la comanda.
	 */

	public Comanda(Mesa mesa, ArrayList<Pedido> listaProductos) {
		this.mesa = mesa;
		this.listaProductos = listaProductos;
		this.estado = "ABIERTA";
		this.fecha = Date.from(Instant.now());// bien?
		this.invariante();
	}

	public void actualizaListaProductos(ArrayList<Pedido> listaProductos) {
		for (Pedido pedido : listaProductos)
			this.listaProductos.add(pedido);
		this.invariante();
	}

	public void removePedido(Pedido pedido) {
		this.listaProductos.remove(pedido);
		this.invariante();
	}

	public void setEstado(String estado) {
		this.estado = estado;
		this.invariante();
	}

	public void cerrarse() {
		this.setEstado("CERRADA");
		this.invariante();
	}

	public Date getFecha() {
		return fecha;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public ArrayList<Pedido> getListaProductos() {
		return listaProductos;
	}

	public String getEstado() {
		return estado;
	}
	
	@Override
	public String toString() {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaString = formatter.format(fecha);
		return "mesa N°" +mesa.getNroMesa()+ " fecha: "+ fechaString+" estado= "+estado;
	}
	public void invariante() {
		assert fecha!=null:"la fecha debe ser !=null";
		assert listaProductos!=null:"la fecha debe ser !=null";
		assert estado!=null:"El estado debe ser !=null";
		assert estado.equalsIgnoreCase("abierta") || estado.equalsIgnoreCase("cerrada"):"el estado de la comanda debe ser abierta o cerrada";
	}

}
