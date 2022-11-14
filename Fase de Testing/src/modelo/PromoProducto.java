package modelo;

import java.util.ArrayList;

/**
 * * Clase PromocionProducto del modelo, se extiende de Promocion y representa a
 * una promocion de determinado producto <br>
 * <b>Invariante: </b><br>
 * -diasDePromo !=null. producto!=null y no debe estar vacio.
 * -id_promo,dtoPorCantidad_CantMinima y dtoPorCantidad_PrecioUnit deben ser
 * mayor a 0
 * -si aplicaDosPorUno es true, aplicaDtoPorCantidad debe ser false y viceversa.
 */
public class PromoProducto extends Promocion {
	public Producto producto;
	public boolean aplicaDosPorUno;
	public boolean aplicaDtoPorCantidad;
	public int dtoPorCantidad_CantMinima;
	public double dtoPorCantidad_PrecioUnit;
	public boolean activa;

	/**
	 * Crea una nueva instancia de una PromoProducto. <br>
	 * <b>Pre:</b> diasDePromo debe ser distinto de null y no debe estar vacio.<br>
	 * id_promo,dtoPorCantidad_CantMinima y dtoPorCantidad_PrecioUnit deben ser
	 * mayor a 0<br>
	 * aplicaDosPorUno debe ser true si aplicaDtoPorCantidad es false y
	 * viceversa.<br>
	 * producto no debe ser null<br>
	 * <b>Post:</b> Nueva PromoProducto instanciada.<br>
	 *
	 * @param diasDePromo                : Es una coleccion con los dias en los que
	 *                                   la promocion esta vigente
	 * @param producto:                  Es una instancia de la clase Producto.
	 * @param aplicaDosPorUno:           Determina si la promocion es 2x1 o no
	 * @param aplicaDtoPorCantidad:      Determina si la promocion aplica comprando
	 *                                   cierta cantidad del producto
	 * @param dtoPorCantidad_CantMinima: Establece que cantidad hay que comprar para
	 *                                   que aplique la promocion por cantidad
	 * @param dtoPorCantidad_PrecioUnit: Establece el nuevo precio unitario al
	 *                                   aplicar la promocion por cantidad
	 * @param activa:                    Determina si la promo esta activa o no
	 */
	public PromoProducto(ArrayList<String> diasDePromo, Producto producto, boolean aplicaDosPorUno,
			boolean aplicaDtoPorCantidad, int dtoPorCantidad_CantMinima, double dtoPorCantidad_PrecioUnit) {
		super(diasDePromo);
		this.aplicaDosPorUno = aplicaDosPorUno;
		this.aplicaDtoPorCantidad = aplicaDtoPorCantidad;
		this.dtoPorCantidad_CantMinima = dtoPorCantidad_CantMinima;
		this.dtoPorCantidad_PrecioUnit = dtoPorCantidad_PrecioUnit;
		this.producto = producto;
		this.invariante();
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
		this.invariante();
	}

	public boolean isAplicaDosPorUno() {
		return aplicaDosPorUno;
	}

	public void setAplicaDosPorUno(boolean aplicaDosPorUno) {
		this.aplicaDosPorUno = aplicaDosPorUno;
		this.invariante();
	}

	public boolean isAplicaDtoPorCantidad() {
		return aplicaDtoPorCantidad;
	}

	public void setAplicaDtoPorCantidad(boolean aplicaDtoPorCantidad) {
		this.aplicaDtoPorCantidad = aplicaDtoPorCantidad;
		this.invariante();
	}

	public int getDtoPorCantidad_CantMinima() {
		return dtoPorCantidad_CantMinima;
	}

	public void setDtoPorCantidad_CantMinima(int dtoPorCantidad_CantMinima) {
		this.dtoPorCantidad_CantMinima = dtoPorCantidad_CantMinima;
		this.invariante();
	}

	public double getDtoPorCantidad_PrecioUnit() {
		return dtoPorCantidad_PrecioUnit;
	}

	public void setDtoPorCantidad_PrecioUnit(double dtoPorCantidad_PrecioUnit) {
		this.dtoPorCantidad_PrecioUnit = dtoPorCantidad_PrecioUnit;
		this.invariante();
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
		this.invariante();
	}

	public void invariante() {
		
		super.invariante();
		assert this.dtoPorCantidad_CantMinima>0:"La cantidad minima para aplicar el desc por cant minima debe ser > 0";
		assert this.dtoPorCantidad_PrecioUnit>0:"El precio unitario para aplicar dto por cantidad debe ser > 0";	
		assert (this.aplicaDosPorUno==true && this.aplicaDtoPorCantidad==false) 
				||(this.aplicaDosPorUno==false && this.aplicaDtoPorCantidad==true):
				"si aplicaDosPorUno es true, aplicaDtoPorCantidad debe ser false y viceversa.";
		assert this.producto!=null:"el producto debe ser distinto de null";
	}
	
	//Equals redefinido
	//Se toma que 2 promos Producto son iguales: si se aplican los mismos descuentos y los mismos valores para estos (independientemente de los dias que se apliquen y si estan activas o no)
	public boolean equals(PromoProducto pp) {
		
		if (this.aplicaDosPorUno==pp.aplicaDosPorUno && this.aplicaDtoPorCantidad==pp.aplicaDtoPorCantidad && this.dtoPorCantidad_CantMinima==pp.dtoPorCantidad_CantMinima && this.dtoPorCantidad_PrecioUnit==pp.dtoPorCantidad_CantMinima)
			return true;
		else
			return false;
	}
	@Override
	public String toString() {
		return "producto=" + producto.getNombre() + ", aplicaDosPorUno=" + aplicaDosPorUno + ", aplicaDtoPorCantidad="
				+ aplicaDtoPorCantidad + ", dtoPorCantidad_CantMinima=" + dtoPorCantidad_CantMinima
				+ ", dtoPorCantidad_PrecioUnit=" + dtoPorCantidad_PrecioUnit + ", activa=" + this.isActiva();
	}

}
