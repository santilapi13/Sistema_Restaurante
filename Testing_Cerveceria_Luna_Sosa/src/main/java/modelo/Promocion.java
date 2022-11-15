package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * * Clase Promocion del modelo, es la clase padre de PromoProducto y
 * PromoTemporal <br>
 * <b>Invariante: </b><br>
 * -diasDePromo distintos de null y no puede estar vacio
 * -idPromo > 0.
 */
public class Promocion implements Serializable {

	public static int incremental = 0;
	public int idPromo;
	public ArrayList<String> diasDePromo = new ArrayList<String>(); // Lunes a Domingo
	private boolean activa;

	/**
	 * Crea una nueva instancia de una Promocion. <br>
	 * <b>Pre:</b> diasDePromo debe ser distinto de null y no debe estar vacio.<br>
	 * <b>Post:</b> Nueva promocion instanciada.<br>
	 * 
	 * @param diasDePromo : Es una coleccion con los dias en los que la promocion
	 *                    esta vigente
	 * @param activa:     Determina si la promo esta activa o no
	 */
	public Promocion(ArrayList<String> diasDePromo) {
		this.idPromo = ++incremental;
		this.diasDePromo = diasDePromo;
		this.activa = true;
	}

	public ArrayList<String> getDiasDePromo() {
		return diasDePromo;
	}

	public void setDiasDePromo(ArrayList<String> diasDePromo) {
		this.diasDePromo = diasDePromo;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public String getDias() {
		String dias = "";
		for (String dia : this.diasDePromo)
			dias += dia;
		return dias;
	}

	public void invariante() {
		
		assert diasDePromo!=null && diasDePromo.size()>0:"diasDePromo debe ser distinto de null y contener al menos 1 elemento";
		assert idPromo > 0: "El idPromo debe ser > 0";
		
	}
	
	
	@Override
	public String toString() {
		return "idPromo= #" + idPromo + ", diasDePromo=" + getDias();
	}

}
