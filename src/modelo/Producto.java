package modelo;

import java.io.Serializable;

/**
 * Clase Producto del modelo <br>
 * <b>Invariante: </b><br>
 * - El pVenta no puede ser menor al pCosto. <br>
 * - El pVenta y pCosto deben ser mayores a 0. <br>
 * - El stock debe ser mayor o igual a 0. <br>
 */
public class Producto implements Serializable {
    private static int id_ultimo = 0;
    private int id_producto;
    private String nombre;
    private double pCosto;
    private double pVenta;
    private int stock;

    /**
     * Crea un nuevo producto para la carta de la cervecería. <br>
     * <b>Pre:</b> El nombre del producto no puede ser nulo. <br>
     * <b>Post:</b> El producto debe ser instanciado de forma que pueda ser agregado a la carta de la cervecería. <br>
     * @param nombre : Nombre del producto a vender.
     * @param pCosto : Costo de compra del producto.
     * @param pVenta : Precio de venta del producto.
     * @param stockInicial : Stock inicial del producto.
     */
    public Producto(String nombre, double pCosto, double pVenta, int stockInicial) {
        assert nombre != null : "El nombre del producto no puede ser nulo.";
        this.nombre = nombre;
        this.pCosto = pCosto;
        this.pVenta = pVenta;
        this.stock = stockInicial;
        this.invariante();
        id_ultimo++;
        this.id_producto = id_ultimo;
    }

    public int getId_producto() {
        return id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getpCosto() {
        return pCosto;
    }

    public void setpCosto(double pCosto) {
        this.pCosto = pCosto;
        this.invariante();
    }

    public double getpVenta() {
        return pVenta;
    }

    public void setpVenta(double pVenta) {
        this.pVenta = pVenta;
        this.invariante();
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        this.invariante();
    }

    private void invariante() {
        assert this.pVenta >= this.pCosto : "El precio de venta no puede ser menor al precio de costo.";
        assert this.pVenta > 0 : "El precio de venta debe ser mayor a 0.";
        assert this.pCosto > 0 : "El precio de costo debe ser mayor a 0.";
        assert this.stock >= 0 : "El stock debe ser mayor o igual a 0.";
    }

    public static int getId_ultimo() {
        return id_ultimo;
    }

    public static void setId_ultimo(int id_ultimo) {
        Producto.id_ultimo = id_ultimo;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre +
                ", pCosto: $" + pCosto +
                ", pVenta: $" + pVenta +
                ", " + stock + " unidades";
    }
}
