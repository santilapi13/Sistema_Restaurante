package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Venta implements Serializable {
	public Comanda comanda;
	public double total;
	public String formaPago;
	public ArrayList<Promocion> listaPromosAplicadas = new ArrayList<Promocion>();

	/**
	 * Constructor de la clase venta.<br>
	 * <b>Pre:</b> comanda!= null. listaPromosAplicadas debe estar inicializada.<br>
	 * <b>Post:</b> Se instancia un objeto de la clase venta con las caracteristicas
	 * pasadas por parametro..<br>
	 * 
	 * @param comanda              : Comanda de la venta.
	 * @param total                : El total de la venta.
	 * @param formaPago            : La forma de pago de la venta.
	 * @param listaPromosAplicadas : ArrayList de promociones aplicadas a la venta.
	 */

	public Venta(Comanda comanda, double total, String formaPago, ArrayList<Promocion> listaPromosAplicadas) {
		this.comanda = comanda;
		this.total = total;
		this.formaPago = formaPago;
		this.listaPromosAplicadas = listaPromosAplicadas;
	}

	public Comanda getComanda() {
		return comanda;
	}

	public double getTotal() {
		return total;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public ArrayList<Promocion> getListaPromosAplicadas() {
		return listaPromosAplicadas;
	}

	private String getPromos() {
		String promosAplicadas="";
		for(Promocion promo: this.listaPromosAplicadas)
			promosAplicadas+=promo.toString();
		return promosAplicadas;
	}
	
	@Override
	public String toString() {
		return "fecha= "+comanda.getFecha().getDay()+"/"+comanda.getFecha().getMonth()+"/"+(comanda.getFecha().getYear()+1900)+", mesa=" + comanda.getMesa().getNroMesa() + ", total=" + total + ", formaPago=" + formaPago + ", listaPromosAplicadas="
				+ getPromos();
	}
	
	
}
