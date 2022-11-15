package modelo;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * Clase Pedido del modelo <br>
 * <b>Invariante: </b><br>
 * - producto !=null. fecha != null. cantidad > 0
 */
public class Pedido implements Serializable{
	public Producto producto;
	public double cantidad;
	public Date fecha; // no se si va esta

	/**
	 * Crea una nueva instancia de la clase Pedido. <br>
	 * <b>Pre:</b> Producto debe ser distinto de null y encontrarse en la lista de
	 * productos.<br>
	 * cantidad debe ser mayor a 0 y menor o igual al stock disponible del
	 * producto<.<br>
	 * <b>Post:</b> El pedido nuevo esta instanciado con su producto, cantidad y
	 * fecha de creacion. Al producto se le decremento su stock.<br>
	 * 
	 * @param producto  : Es una instancia de la clase Producto.
	 * @param cantidad: Es el numero de elementos que descontaremos del stock del
	 *                  producto.
	 */
	public Pedido(Producto producto, double cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.fecha = Date.from(Instant.now());
		this.invariante();
		producto.actualizaStock(-cantidad);
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
		this.invariante();
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
		this.invariante();
	}

	public Date getFecha() {
		return fecha;
	}
	
	@Override
	public String toString() {
		return "" +cantidad+ " "+ producto.getNombre();
	}

	public void invariante() {
		assert producto != null : "producto debe ser diferente de null";
		assert fecha != null : "fecha debe ser diferente de null";
		assert cantidad > 0 : "La cantidad a pedir debe ser mayor a 0";

	}

}
