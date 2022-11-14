package modelo;

import java.io.Serializable;

/**
 * * Clase Producto del modelo <br>
 * <b>Invariante: </b><br>
 * -id_producto, pCosto y pVenta deben ser mayor a 0. PCosto < PVenta
 * -nombre debe ser distinto de null y vacio <br>
 * -stock debe ser mayor o igual a 0
 */
public class Producto implements Serializable {
	private static int incremental = 0;
	public int id_producto;
	public String nombre;
	public double pCosto;
	public double pVenta;
	public int stock;
	public boolean enComanda; //cuando se agrega una vez a una comanda pasa a True y permanece en este estado.
							  //cuando se instancia un producto, inicia en false.
	/**
	 * Crea una nueva instancia de un Producto. <br>
	 * <b>Pre:</b> nombre debe ser distinto de null y vacio.<br>
	 * pCosto debe ser menor a pVenta, y ambos mayor a 0.<br>
	 * stock debe ser mayor o igual a 0<br>
	 * <b>Post:</b> El nuevo producto esta instanciado.<br>
	 * 
	 * @param nombre : Es la descripcion del producto instanciado
	 * @param pCosto : Es el valor que debe pagar la cerveceria para tener el
	 *               producto
	 * @param pVenta : Es el valor que debe pagar los clientes para tener el
	 *               producto
	 * @param stock: Es la cantidad de producto con la que cuenta la cerveceria
	 */
	public Producto(String nombre, double pCosto, double pVenta, int stock) {

		this.nombre = nombre;
		this.pCosto = pCosto;
		this.pVenta = pVenta;
		this.stock = stock;
		this.enComanda=false;
		this.invariante(); // verifica las condiciones establecidas en la precondicion del metodo
		this.id_producto = incremental++;

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		this.invariante();
	}

	public void setpCosto(double pCosto) {
		this.pCosto = pCosto;
		this.invariante();
	}

	public void setpVenta(double pVenta) {
		this.pVenta = pVenta;
		this.invariante();
	}

	/**
	 * Actualiza el stock disponible del producto. <br>
	 * <b>Pre:</b> si cant es menor a 0, no puede ser mayor (en valor absoluto) a la
	 * cantidad de stock actual.<br>
	 * <b>Post:</b> stock actualizado.<br>
	 * 
	 * @param cant : Es la cantidad de stock a agregar o quitar
	 */
	public void actualizaStock(double cant) {
		assert cant > 0 || (cant < 0 && cant <= this.stock) : "no hay stock suficiente del producto solicitado";
		// igualmente, no se llama nunca al metodo en esta situacion
		this.stock += cant;
		this.invariante();
	}

	public int getId_producto() {
		return id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public double getpCosto() {
		return pCosto;
	}

	public double getpVenta() {
		return pVenta;
	}

	public double getStock() {
		return stock;
	}
	public boolean EnComanda() {
		return enComanda;
	}

	public void invariante() {
		assert this.nombre != null && this.nombre != ""
				: "El nombre del producto debe ser diferente de null y de un string vacio.";
		assert this.pCosto < this.pVenta && this.pCosto > 0
				: "El precio de costo debe ser menor al precio de venta y mayor a 0";
		assert this.pVenta > 0 : "El precio de venta debe ser mayor a 0";
		assert this.stock >= 0 : "El stock debe ser mayor o igual a 0";
	}

	@Override
	public String toString() {
		return "#" + id_producto + " " + nombre + ", Precio de costo=" + pCosto + ", Precio de venta=" + pVenta
				+ ", Stock=" + stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
