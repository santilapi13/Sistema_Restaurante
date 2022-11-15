package modelo;

import java.util.ArrayList;
import java.util.Date;

import excepciones.ComandaAbiertaException;
import excepciones.MesaInexistenteException;
import excepciones.MesaRepetidaException;
import excepciones.MozoInexistenteException;
import excepciones.MozoRepetidoException;
import excepciones.OperarioInexistenteException;
import excepciones.ProductoEnComandaException;
import excepciones.ProductoInexistenteException;
import excepciones.ProductoRepetidoException;
import excepciones.PromoInexistenteException;
import excepciones.PromoRepetidaException;
import excepciones.UsuarioRepetidoException;

/**
 * Clase Admin <br>
 * <b>mismo invariante que su clase padre Operario: </b><br>
 * - nya != null y distinto de vacio.
 * username != null y distinto de vacio. -
 * password !=null y distinto de vacio.
 */
public class Admin extends Operario {

	public boolean editado = false;

	/**
	 * Crea una nueva instancia de la clase Admin. <br>
	 * <b>Post:</b> El Admin se creara con un nombre de usuario (ADMIN) y una
	 * contrasena (ADMIN1234) por defecto.<br>
	 */
	public Admin() {
		super("ADMINISTRADOR", "ADMIN", "ADMIN1234");
		super.invariante();
	}


	/**
	 * Agrega un operario a la lista de operarios de la cerveceria con los datos
	 * pasados por parametro<br>
	 * <b>Pre:</b> nya !=null. username!=null. password!=null.<br>
	 * <b>Post:</b> El operario se instancia y se agrega a la lista de operarios de
	 * la cerveceria.<br>
	 *
	 * @param nya      : Nombre y apellido del operario.
	 * @param username : Nombre de usuario que utilizara el operario para acceder al
	 *                 sistema luego de ser registrado.
	 * @param password : Contrasena que utilizara el operario para acceder al
	 *                 sistema luego de ser registrado.
	 * @throws OperarioRepetidoException : Se lanza si el operario ya existe en el
	 *                                   ArrayList de operarios.
	 */
	public void addOperario(String nya, String username, String password) throws UsuarioRepetidoException {

		assert nya != null : "el nombre y apellido debe ser distinto de null";
		assert username != null : "el username debe ser distinto de null";
		assert password != null : "el password debe ser distinto de null";
		assert nya != null : "el nombre y apellido debe ser distinto de null";

		Operario op = new Operario(nya, username, password);
		Cerveceria.getInstance().addOperario(op); 

	}

	/**
	 * Agrega un mozo a la lista de mozos de la cerveceria con los datos pasados por
	 * parametro<br>
	 * <b>Pre:</b> nya !=null. cantHijos>=0. fechaNac!=null.<br>
	 * <b>Post:</b> El mozo se instancia y se agrega a la lista de mozos de la
	 * cerveceria.<br>
	 *
	 * @param nya       : Nombre y apellido del mozo.
	 * @param cantHijos : cantidad de hijos del mozo a agregar a la cerveceria.
	 * @param fechaNac  : fecha de nacimiento del mozo a agregar a la cerveceria.
	 * @throws MozoRepetidoException : Se lanza si el mozo ya existe en el ArrayList
	 *                               de mozos.
	 */
	public void addMozo(String nya, Date fechaNac, int cantHijos) throws MozoRepetidoException {

		assert nya != null && nya != "" : "el nombre y apellido debe ser distinto de null y de vacio";
		assert cantHijos >= 0 : "la cantidad de hijos debe ser >= 0";
		assert fechaNac != null : "la fecha de nacimiento debe ser distinto de null";

		Date date = new Date();
		Mozo mozo = new Mozo(nya, date, cantHijos);
		Cerveceria.getInstance().addMozo(mozo);
	}

	/**
	 * Agrega un producto a la lista de productos de la cerveceria con los datos
	 * pasados por parametro<br>
	 * <b>Pre:</b> nombre !=null. pCosto>0. pVenta>0. stock >= 0<br>
	 * <b>Post:</b> El producto se instancia y se agrega a la lista de mozos de la
	 * cerveceria.<br>
	 *
	 * @param nombre: Nombre del producto.
	 * @param pCosto  : precio de costo de cada unidad del producto.
	 * @param pVenta  : precio de venta de cada unidad del producto.
	 * @param stock   : stock inicial del producto que se va a instanciar.
	 * @throws ProductoRepetidoException : Se lanza si el producto ya existe en el
	 *                                   ArrayList de productos.
	 */

	public void addProducto(String nombre, double pCosto, double pVenta, int stock) throws ProductoRepetidoException {

		assert nombre != null : "el nombre del producto debe ser distinto de null";
		assert pCosto > 0 && pVenta > 0 : "el precio de costo y de venta deben ser ambos > 0";
		assert stock >= 0 : "el stock debe ser >= 0";

		Producto prod = new Producto(nombre, pCosto, pVenta, stock);
		Cerveceria.getInstance().addProducto(prod);
	}

	/**
	 * Agrega una mesa a la lista de mesas de la cerveceria con los datos pasados
	 * por parametro<br>
	 * <b>Pre:</b> cantComensales > 0. nroMesa>=0.<br>
	 * <b>Post:</b> La mesa se instancia y se agrega a la lista de mesas de la
	 * cerveceria.<br>
	 *
	 * @param cantComensales: Cantidad de sillas que posee la mesa.
	 * @param nroMesa         : numero de mesa de la mesa a instanciar.
	 * @throws MesaRepetidaException : Se lanza si la mesa ya existe en el ArrayList
	 *                               de mesas.
	 */
	public void addMesa(int cantComensales, int nroMesa) throws MesaRepetidaException {

		assert cantComensales > 0 : "la cantidad de comensales debe ser > 0";
		assert nroMesa >= 0 : "el nroMesa debe ser >= 0";

		Mesa mesa = new Mesa(cantComensales, nroMesa);
		Cerveceria.getInstance().addMesa(mesa);
	}

	/**
	 * Instancia y agrega una promo producto a la lista de promociones de producto de la
	 * cerveceria con los datos pasados por parametro<br>
	 * <b>Pre:</b> diasDePromo debe ser distinto de null y no debe estar vacio.<br>
	 * dtoPorCantidad_CantMinima y dtoPorCantidad_PrecioUnit deben ser mayor a 0<br>
	 * aplicaDosPorUno debe ser true si aplicaDtoPorCantidad es false y
	 * viceversa.<br>
	 * producto no debe ser null<br>
	 * <br>
	 * 
	 * <b>Post:</b> La promo Producto se instancia y se agrega al ArrayList de
	 * promociones de PromosProducto de la cerveceria.<br>
	 *
	 * @param diasDePromo: ArrayList con los dias que se aplicara la promo producto.
	 * @param producto     : producto que tendra la promocion.
	 * @throws PromoRepetidaException : Se lanza si la mesa ya existe en el ArrayList de
	 *                        mesas.
	 */

	public void addPromoProducto(ArrayList<String> diasDePromo, Producto producto, boolean aplicaDosPorUno,
			boolean aplicaDtoPorCantidad, int dtoPorCantidad_CantMinima, double dtoPorCantidad_PrecioUnit)
			throws PromoRepetidaException, ProductoInexistenteException {

		assert diasDePromo != null && diasDePromo.size() > 0
				: "diasDePromo debe ser distinto de null y contener al menos 1 elemento";
		assert dtoPorCantidad_CantMinima > 0 : "La cantidad minima para aplicar el desc por cant minima debe ser > 0";
		assert dtoPorCantidad_PrecioUnit > 0 : "El precio unitario para aplicar dto por cantidad debe ser > 0";
		assert (aplicaDosPorUno == true && aplicaDtoPorCantidad == false)
				|| (aplicaDosPorUno == false && aplicaDtoPorCantidad == true)
				: "si aplicaDosPorUno es true, aplicaDtoPorCantidad debe ser false y viceversa.";
		assert producto != null : "el producto debe ser distinto de null";

		PromoProducto promoProd = new PromoProducto(diasDePromo, producto, aplicaDosPorUno, aplicaDtoPorCantidad,
				dtoPorCantidad_CantMinima, dtoPorCantidad_PrecioUnit);
		Cerveceria.getInstance().addPromoProd(promoProd);
	}

	
	/**
	 * Instancia y agrega una promo temporal a la lista de promociones temporales de la
	 * cerveceria con los datos pasados por parametro.<br>
	 * <b>Pre:</b> dias debe ser distinto de null y no debe estar vacio.<br>
	 * nombre !=null y distinto de vacio.<br>
	 * formaPagodebe ser: efectivo, tarjeta, mercPago o ctaDNI.<br>
	 * porcentajeDesc debe ser mayor que <br>
	 * <b>Post:</b> La promo Temporal se instancia y se agrega al ArrayList de
	 * promociones de PromosTemporales de la cerveceria.<br>
	 *
	 * @param dias: ArrayList con los dias que se aplicara la promo producto.
	 * @param nombre     : nombre de la promocion
	 * @param formaPago: Determina bajo que medio de pago se aplicara la promocion
	 * @param porcentaje: Establece el descuento que se le hace al total de la compra
	 * @param acumulable: Determina si la promocion puede aplicarse si hay otras promociones aplicadas
	 * @throws PromoException : Se lanza si la mesa ya existe en el ArrayList de
	 *                        mesas.
	 */
	public void addPromoTemporal(ArrayList<String> dias, String nombre, String formaPago, int porcentaje, boolean acumulable) throws PromoRepetidaException{
		
		assert dias != null && dias.size() > 0
				: "diasDePromo debe ser distinto de null y contener al menos 1 elemento";
		assert nombre!=null && !nombre.equals(""):"el nombre debe ser distinto de null y string vacio";
		assert formaPago!=null && !formaPago.equals(""):"el nombre debe ser distinto de null y string vacio";
		assert formaPago.equalsIgnoreCase("efectivo") || formaPago.equalsIgnoreCase("tarjeta") ||
		formaPago.equalsIgnoreCase("mercPago") || formaPago.equalsIgnoreCase("ctaDNI"):"El medio de pago debe ser: efectivo, tarjeta, mercPago o ctaDNI";
		assert porcentaje > 0: "procentajeDesc debe ser mayor que 0";
		
		PromoTemporal promoTemp = new PromoTemporal(dias,nombre,formaPago,porcentaje,acumulable);
		Cerveceria.getInstance().addPromoTemp(promoTemp);
	}
	

	public void deleteMozo(Mozo mozo) throws MozoInexistenteException {
		assert mozo!=null:"el mozo debe ser != null";
		Cerveceria.getInstance().deleteMozo(mozo);
	}

	//no se puede eliminar una mesa que tenga una comanda abierta asociada.
	
	public void deleteMesa(Mesa mesa) throws ComandaAbiertaException,MesaInexistenteException {
		assert mesa!=null:"la mesa debe ser !=null";
		Cerveceria.getInstance().deleteMesa(mesa);
	}

	//no se puede eliminar un producto si es/fue parte de una comanda.
	public void deleteProducto(Producto producto) throws ProductoEnComandaException,ProductoInexistenteException {
		assert producto!=null: "el producto debe ser !=null";
		Cerveceria.getInstance().deleteProducto(producto);
	}

	public void deleteOperario(Operario operario) throws OperarioInexistenteException {
		assert operario!=null: "el operario debe ser !=null";
		Cerveceria.getInstance().deleteOperario(operario);
	}

	public void deletePromoTemporal(PromoTemporal promo) throws PromoInexistenteException{
		assert promo!=null: "la promo debe ser !=null";
		Cerveceria.getInstance().deletePromoTemporal(promo);
	}

	public void deletePromoProducto(PromoProducto promo) throws PromoInexistenteException{
		assert promo!=null: "la promo debe ser !=null";
		Cerveceria.getInstance().deletePromoProducto(promo);
	}
	
}
