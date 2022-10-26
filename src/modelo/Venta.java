package modelo;

/**
 * Clase que representa una venta de una comanda.
 *
 */
public class Venta {
    private Comanda comanda;
    private double total;
    private FormaPago formaPago;

    /**
     * Constructor de la clase Venta.<br>
     * <b>Pre:</b> El parametro comanda debe ser distinto de null.<br>
     * <b>Post:</b> Se creara una venta.<br>
     * @param comanda : La comanda de la venta.
     */
    public Venta(Comanda comanda, FormaPago formaPago, double total) {
        assert comanda != null : "La comanda no puede ser null";
        this.comanda = comanda;
        this.total = total;
        this.formaPago = formaPago;
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
}

