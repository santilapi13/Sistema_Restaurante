package modelo;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Clase que representa una promoción temporal.<br>
 */
public class PromoTemporal extends Promocion {
    private String nombre;
    private FormaPago formaPago;
    private double porcentajeDesc;
    private boolean esAcumulable;
    private int horaInicio;
    private int horaFin;

    /**
     * Constructor de la clase PromoTemporal.<br>
     * <b>Pre:</b> El parametro nombre debe ser distinto de null. El parametro formaPago debe ser distinto de null.
     * El porcentaje de descuento debe ser un numero entre 0 y 1. Las horaInicio y horaFin deben ser numeros entre 0 y 23.<br>
     * <b>Post:</b> Se creara una promoción temporal.<br>
     * @param nombre : El nombre de la promoción.
     * @param formaPago : La forma de pago de la promoción.
     * @param porcentajeDesc : El porcentaje de descuento de la promoción.
     * @param esAcumulable : Indica si la promoción es acumulable con otras.
     * @param horaInicio : La hora de inicio de la promoción.
     * @param horaFin : La hora de fin de la promoción.
     * @param diasPromo : Los dias de la semana en los que se aplica la promoción.
     */
    public PromoTemporal(String nombre, FormaPago formaPago, double porcentajeDesc, boolean esAcumulable, int horaInicio, int horaFin, ArrayList<DayOfWeek> diasPromo) {
        super(diasPromo);
        assert nombre != null : "El nombre no puede ser null";
        assert formaPago != null : "La forma de pago no puede ser null";
        assert porcentajeDesc > 0 && porcentajeDesc < 1: "El porcentaje de descuento debe ser un valor valido";
        assert horaInicio >= 0 && horaInicio <= 23 : "La hora de inicio debe ser un numero entre 0 y 23";
        assert horaFin >= 0 && horaFin <= 23 : "La hora de fin debe ser un numero entre 0 y 23";
        this.nombre = nombre;
        this.formaPago = formaPago;
        this.porcentajeDesc = porcentajeDesc;
        this.esAcumulable = esAcumulable;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public double getPorcentajeDesc() {
        return porcentajeDesc;
    }

    public boolean isEsAcumulable() {
        return esAcumulable;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public void setPorcentajeDesc(double porcentajeDesc) {
        this.porcentajeDesc = porcentajeDesc;
    }

    public void setEsAcumulable(boolean esAcumulable) {
        this.esAcumulable = esAcumulable;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        return nombre +
                ", " + formaPago +
                ", " + porcentajeDesc*100 + "%" +
                ", " + (esAcumulable ? "acumulable" : "no acumulable") +
                ", desde " + horaInicio + " hs hasta " + horaFin + " hs" +
                ", dias: " + getDiasDePromo();
    }
}
