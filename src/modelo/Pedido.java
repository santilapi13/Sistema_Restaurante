package modelo;

import java.time.LocalDate;

/**
 * Clase que representa un pedido que forma parte de una comanda.<br>
 */
public class Pedido {
    private Producto producto;
    private int cantidad;
    private LocalDate fecha;

    /**
     * Constructor de la clase Pedido.<br>
     * <b>Pre:</b> El parametro producto debe ser distinto de null y la cantidad debe ser mayor a 0.<br>
     * El stock del producto debe ser mayor o igual a la cantidad.<br>
     * <b>Post:</b> Se creara un pedido con los datos pasados por parametro.<br>
     * @param producto : Producto que se quiere pedir.
     * @param cantidad : Cantidad de producto que se quiere pedir.
     */
    public Pedido(Producto producto, int cantidad) {
        assert producto != null : "El producto no puede ser nulo.";
        assert cantidad > 0 : "La cantidad debe ser mayor a 0.";
        assert producto.getStock() >= cantidad : "El stock del producto debe ser mayor o igual a la cantidad.";
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = LocalDate.now();
    }

    public Producto getProducto() {
        return producto;
    }
    public int getCantidad() {
        return cantidad;
    }
    public LocalDate getFecha() {
        return fecha;
    }

}
