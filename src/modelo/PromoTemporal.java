package modelo;

import java.sql.Time;

/**
 * Clase que representa una promoción temporal.<br>
 */
public class PromoTemporal extends Promocion {
    private String nombre;
    private FormaPago formaPago;
    private int porcentajeDesc;
    private boolean esAcumulable;
    private Time horaInicio;
    private Time horaFin;

    /**
     * Constructor de la clase PromoTemporal.<br>
     * <b>Pre:</b> El parametro nombre debe ser distinto de null. El parametro formaPago debe ser distinto de null.
     * El porcentaje de descuento debe ser un numero entre 0 y 1. Las horaInicio y horaFin deben ser distintas de null.<br>
     * <b>Post:</b> Se creara una promoción temporal.<br>
     * @param nombre : El nombre de la promoción.
     * @param formaPago : La forma de pago de la promoción.
     * @param porcentajeDesc : El porcentaje de descuento de la promoción.
     * @param esAcumulable : Indica si la promoción es acumulable con otras.
     * @param horaInicio : La hora de inicio de la promoción.
     * @param horaFin : La hora de fin de la promoción.
     */
    public PromoTemporal(String nombre, FormaPago formaPago, int porcentajeDesc, boolean esAcumulable, String horaInicio, String horaFin) {
        assert nombre != null : "El nombre no puede ser null";
        assert formaPago != null : "La forma de pago no puede ser null";
        assert porcentajeDesc > 0 && porcentajeDesc < 1: "El porcentaje de descuento debe ser un valor valido";
        assert horaInicio != null : "La hora de inicio no puede ser null";
        assert horaFin != null : "La hora de fin no puede ser null";
        this.nombre = nombre;
        this.formaPago = formaPago;
        this.porcentajeDesc = porcentajeDesc;
        this.esAcumulable = esAcumulable;
        this.horaInicio = Time.valueOf(horaInicio); // Convierte el string en formato "hh-mm-ss" en un Time
        this.horaFin = Time.valueOf(horaFin);
    }

    public String getNombre() {
        return nombre;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public int getPorcentajeDesc() {
        return porcentajeDesc;
    }

    public boolean isEsAcumulable() {
        return esAcumulable;
    }
}
