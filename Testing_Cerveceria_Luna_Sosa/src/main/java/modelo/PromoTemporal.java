package modelo;

import java.util.ArrayList;

/**
 * * Clase PromocionTemporal del modelo, se extiende de Promocion y representa a una promocion de determinado dia <br>
 * <b>Invariante: </b>nombre y forma de pago no deben ser null ni vacio<br>
 * -formaPagodebe ser: efectivo, tarjeta, mercPago o ctaDNI
 * -porcentajeDesc debe ser mayor que 0<br>
 */
public class PromoTemporal extends Promocion {
    public String nombre;
    public String formaPago; //efectivo-tarjeta-mp-ctaDNI
    public int porcentajeDesc;
    public boolean activa;
    public boolean esAcumulable;

    /**
     * Crea una nueva instancia de una PromoTemporal. <br>
     * <b>Pre:</b> diasDePromo debe ser distinto de null y no debe estar vacio.<br>
     * nombre y formaPago no deben ser null ni vacio <br>
     * porcentajeDesc ser mayor a 0<br>
     * <b>Post:</b> Nueva PromoTemporal instanciada.<br>
     *
     * @param diasDePromo     : Es una coleccion con los dias en los que la promocion esta vigente
     * @param nombre:         Establece el nombre de la promocion
     * @param formaPago:      Determina bajo que medio de pago se aplicara la promocion
     * @param porcentajeDesc: Establece el descuento que se le hace al total de la compra
     * @param esAcumulable:   Determina si la promocion puede aplicarse si hay otras promociones aplicadas
     * @param activa:         Determina si la promo esta activa o no
     */
    public PromoTemporal(ArrayList<String> diasDePromo, String nombre, String formaPago, int porcentajeDesc,boolean esAcumulable) {
        super(diasDePromo);
        this.nombre = nombre;
        this.formaPago = formaPago;
        this.porcentajeDesc = porcentajeDesc;
        this.activa = true;
        this.esAcumulable = esAcumulable;
        this.invariante();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.invariante();
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
        this.invariante();
    }

    public int getPorcentajeDesc() {
        return porcentajeDesc;
    }

    public void setPorcentajeDesc(int porcentajeDesc) {
        this.porcentajeDesc = porcentajeDesc;
        this.invariante();
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
        this.invariante();
    }

    public boolean isEsAcumulable() {
        return esAcumulable;
    }

    public void setEsAcumulable(boolean esAcumulable) {
        this.esAcumulable = esAcumulable;
        this.invariante();
    }

    public void invariante() {
    	
		super.invariante();
		assert this.nombre!=null && !this.nombre.equals(""):"el nombre debe ser distinto de null y string vacio";
		assert this.formaPago!=null && !this.formaPago.equals(""):"el nombre debe ser distinto de null y string vacio";
		assert this.formaPago.equalsIgnoreCase("efectivo") || this.formaPago.equalsIgnoreCase("tarjeta") ||
		this.formaPago.equalsIgnoreCase("mercPago") || this.formaPago.equalsIgnoreCase("ctaDNI"):"El medio de pago debe ser: efectivo, tarjeta, mercPago o ctaDNI";
		assert this.porcentajeDesc > 0: "procentajeDesc debe ser mayor que 0";
    }
    
    
    
	@Override
	public String toString() {
		return "nombre=" + nombre + ", formaPago=" + formaPago + ", porcentajeDesc=" + porcentajeDesc + ", activa="
				+ activa + ", esAcumulable=" + esAcumulable;
	}
    
    
}
