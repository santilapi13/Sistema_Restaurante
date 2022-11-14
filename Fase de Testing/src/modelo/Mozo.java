package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase Mozo <br>
 * <b>Invariante: </b><br>
 * - nya != null y distinto de vacio. - fecha_nac != null. - edad>18. -
 * cantHijos>=0. - estado !=null. - mesas !=null. - cantVentas >= 0. -
 * totalVentas >= 0.
 */

public class Mozo implements Serializable {
	public String nya;
	public Date fecha_nac;
	public int cantHijos;
	public Estado estado;
	public ArrayList<Mesa> mesas = new ArrayList<Mesa>();
	public int cantVentas;
	public double totalVentas;

	/**
	 * Constructor de la clase Mozo.<br>
	 * <b>Pre:</b> nya !=null y distinto de string vacio.y fechaNacimiento debe, ser
	 * distinto de null.<br>
	 * La edad debe ser mayor o igual que 18.<br>
	 * cantHijos>= 0<br>
	 * <b>Post:</b> Se instancia un mozo con los valores pasados por paramtro<br>
	 * 
	 * @param nya       : Nombre y apellido del mozo.
	 * @param fecha_nac : Fecha de nacimiento del mozo en formato Date.
	 * @param cantHijos : Cantidad de hijos del mozo.
	 * 
	 */
	public Mozo(String nya, Date fecha_nac, int cantHijos) {
		this.nya = nya;
		this.fecha_nac = fecha_nac;
		this.cantHijos = cantHijos;
		this.cantVentas = 0;
		this.totalVentas = 0;
		this.estado = Estado.ACTIVO;
		this.invariante();
	}

	public String getNya() {
		return nya;
	}

	public void setNya(String nya) {
		this.nya = nya;
		this.invariante();
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
		this.invariante();
	}

	public int getCantHijos() {
		return cantHijos;
	}

	public void setCantHijos(int cantHijos) {
		this.cantHijos = cantHijos;
		this.invariante();
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
		this.invariante();
	}

	public int getCantVentas() {
		return cantVentas;
	}

	/**
	 * Incrementa en una unidad la cantidad de ventas realizadas por el mozo.<br>
	 */
	public void addCantVentas() {
		this.cantVentas++;
		this.invariante();
	}

	public double getTotalVentas() {
		return totalVentas;
	}

	/**
	 * Incrementa el monto de ventas del mozo.<br>
	 * <b>Pre:</b> monto >=0.<br>
	 * <b>Post:</b> Se agrega al total de ventas del mozo el monto pasado por
	 * parametro<br>
	 * 
	 * @param monto : monto de la venta realizada por el mozo.
	 * 
	 */
	public void addTotalVentas(double monto) {
		assert monto >= 0 : "el monto de la venta debe ser >=0";
		this.totalVentas += monto;
		this.invariante();
	}

	/**
	 * Asigna una nueva mesa al mozo.<br>
	 * <b>Pre:</b> mesa != null. La mesa no debe estar asignada.<br>
	 * > <b>Post:</b> Se agrega la mesa pasada por parametro al ArrayList de mesas
	 * del mozo.<br>
	 * 
	 * @param mesa : mesa que le sera asignada al mozo.
	 * 
	 */
	public void addMesa(Mesa mesa) {
		assert mesa != null : "la mesa debe ser distinto de null";
		assert !mesa.isAsignado() : "la mesa no debe estar asignada para asignarsela a otro mozo";
		this.mesas.add(mesa);
		this.invariante();
	}

	/**
	 * Se desvincula la mesa con el mozo.<br>
	 * <b>Pre:</b> mesa != null. La mesa debe estar en el ArrayList de mesas del
	 * mozo.<br>
	 * > <b>Post:</b> Se elimina la mesa pasada por parametro del ArrayList de mesas
	 * del mozo.<br>
	 * 
	 * @param mesa : mesa que se le quitara al mozo.
	 * 
	 */
	public void removeMesa(Mesa mesa) {
		assert mesa != null : "la mesa debe ser distinto de null";
		this.mesas.remove(mesa);
		mesa.setAsignado(false);
		this.invariante();
	}

	/**
	 * Inicializa el total de ventas y la cantidad de ventas del mozo en 0.<br>
	 */
	public void reinicio() {
		this.totalVentas = 0;
		this.cantVentas = 0;
		this.invariante();
	}
	
	

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}

	@SuppressWarnings("deprecation")
	public int getEdad() {
		Date date = new Date();
		return date.getYear() + 1900 - this.fecha_nac.getYear();
	}

	public void invariante() {
		assert this.nya != null && this.nya != "" : "El nombre y apellido no puede ser null ni vacio.";
		assert this.fecha_nac != null : "La fecha de nacimiento no puede ser null";
		assert this.getEdad() > 18 : "El mozo debe tener mas de 18 anos";
		assert this.cantHijos >= 0 : "La cantidad de hijos del mozo debe ser >=0";
		assert this.estado != null : "El estado no puede ser null";
		assert this.mesas != null : "El ArrayList de mesas no puede ser null";
		assert this.cantVentas >= 0 : "La cantidad de ventas del mozo debe ser >=0";
		assert this.totalVentas >= 0 : "El total de ventas del mozo debe ser >=0";
	}

	@Override
	public String toString() {
		return "Nombre y Apellido= " + nya + ", edad "+this.getEdad()+" Cantidad de hijos= " + cantHijos
				+ ", estado=" + estado;
	}

}
