package modelo;

public class PromoProducto extends Promocion {
    private int id_promo;
    private Producto producto;
    private boolean aplicadosPorUno;
    private boolean aplicadosDtoPorCantidad;
    private int dtoPorCantidad;
    private double dtoPorCantidad_PrecioUnit;
    private boolean aciva;
}
