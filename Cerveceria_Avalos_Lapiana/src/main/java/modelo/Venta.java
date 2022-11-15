package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa una venta de una comanda.
 *
 */
public class Venta implements Serializable {
    private Comanda comanda;
    private double total;
    private FormaPago formaPago;
    private ArrayList<Promocion> promosAplicadas = new ArrayList<Promocion>();

    /**
     * Constructor de la clase Venta.<br>
     * <b>Pre:</b> El parametro comanda debe ser distinto de null.<br>
     * <b>Post:</b> Se creara una venta.<br>
     * @param comanda : La comanda de la venta.
     * @param total : El total de la venta.
     * @param formaPago : La forma de pago de la venta.
     * @param promosAplicadas : Las promociones aplicadas a la venta.
     */
    public Venta(Comanda comanda, FormaPago formaPago, double total, ArrayList<Promocion> promosAplicadas) {
        assert comanda != null : "La comanda no puede ser null";
        this.comanda = comanda;
        this.total = total;
        this.formaPago = formaPago;
        this.promosAplicadas = promosAplicadas;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public double getTotal() {
        return total;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public ArrayList<Promocion> getPromosAplicadas() {
        return promosAplicadas;
    }

    public void setPromosAplicadas(ArrayList<Promocion> promosAplicadas) {
        this.promosAplicadas = promosAplicadas;
    }

    @Override
    public String toString() {
        return "Total: $" + total +
                "\t Forma de pago: " + formaPago +
                "\t Comanda: \n" + comanda +
                "\t\n PromosAplicadas: \n" + promosAplicadas;
    }
}

