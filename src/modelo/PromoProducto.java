package modelo;

import java.time.DayOfWeek;
import java.util.ArrayList;

/**
 * Clase que representa una promoción sobre un producto.<br>
 */
public class PromoProducto extends Promocion {
    private static int id_ultimo = 0;
    private int id_promo;
    private Producto producto;
    private boolean aplicaDosPorUno;
    private boolean aplicaDtoPorCantidad;
    private int dtoPorCantidad_CantMinima;
    private double dtoPorCantidad_PrecioUnit;

    /**
     * Constructor de la clase PromoProducto.<br>
     * <b>Pre:</b> El parametro producto debe ser distinto de null. Los parametros aplicaDosPorUno y aplicaDtoPorCantidad no pueden ser simultáneamente false.
     * dtoPorCantidad_CantMinima y dtoPorCantidad_Precio_Unit deben ser mayor o igual a 0 (si son 0 es porque no aplicaDtoPorCantidad).
     * <b>Post:</b> Se creara una promoción sobre un producto.<br>
     *
     * @param producto : El producto sobre el que se aplica la promoción.
     * @param aplicaDosPorUno : Indica si la promoción es 2x1.
     * @param aplicaDtoPorCantidad : Indica si la promoción es por cantidad.
     * @param dtoPorCantidad_CantMinima : La cantidad mínima para aplicar el descuento por cantidad.
     * @param dtoPorCantidad_PrecioUnit : El nuevo precio unitario del producto al aplicar el descuento por cantidad.
     * @param diasPromo : Los dias de la semana en los que se aplica la promoción.
     */
    public PromoProducto(Producto producto, boolean aplicaDosPorUno, boolean aplicaDtoPorCantidad, int dtoPorCantidad_CantMinima, double dtoPorCantidad_PrecioUnit, ArrayList<DayOfWeek> diasPromo) {
        super(diasPromo);
        assert producto != null : "El producto no puede ser null";
        assert aplicaDosPorUno || aplicaDtoPorCantidad : "Debe aplicar al menos un tipo de promoción";
        assert dtoPorCantidad_CantMinima >= 0 : "La cantidad mínima debe ser mayor o igual a 0";
        assert dtoPorCantidad_PrecioUnit >= 0 : "El precio unitario debe ser mayor o igual a 0";
        if (aplicaDtoPorCantidad) {
            assert dtoPorCantidad_CantMinima > 0 : "La cantidad mínima debe ser mayor a 0";
            assert dtoPorCantidad_PrecioUnit > 0 : "El precio unitario debe ser mayor a 0";
            this.dtoPorCantidad_PrecioUnit = dtoPorCantidad_PrecioUnit;
        } else
            this.dtoPorCantidad_PrecioUnit = producto.getpVenta();
        this.producto = producto;
        this.aplicaDosPorUno = aplicaDosPorUno;
        this.aplicaDtoPorCantidad = aplicaDtoPorCantidad;
        this.dtoPorCantidad_CantMinima = dtoPorCantidad_CantMinima;
        this.id_ultimo++;
        this.id_promo = id_ultimo;
    }

    public int getId_promo() {
        return id_promo;
    }

    public Producto getProducto() {
        return producto;
    }

    public boolean isAplicaDosPorUno() {
        return aplicaDosPorUno;
    }

    public boolean isAplicaDtoPorCantidad() {
        return aplicaDtoPorCantidad;
    }

    public int getDtoPorCantidad_CantMinima() {
        return dtoPorCantidad_CantMinima;
    }

    public double getDtoPorCantidad_PrecioUnit() {
        return dtoPorCantidad_PrecioUnit;
    }

    public static int getId_ultimo() {
        return id_ultimo;
    }

    public static void setId_ultimo(int id_ultimo) {
        PromoProducto.id_ultimo = id_ultimo;
    }

    public void setId_promo(int id_promo) {
        this.id_promo = id_promo;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setAplicaDosPorUno(boolean aplicaDosPorUno) {
        this.aplicaDosPorUno = aplicaDosPorUno;
    }

    public void setAplicaDtoPorCantidad(boolean aplicaDtoPorCantidad) {
        this.aplicaDtoPorCantidad = aplicaDtoPorCantidad;
    }

    public void setDtoPorCantidad_CantMinima(int dtoPorCantidad_CantMinima) {
        this.dtoPorCantidad_CantMinima = dtoPorCantidad_CantMinima;
    }

    public void setDtoPorCantidad_PrecioUnit(double dtoPorCantidad_PrecioUnit) {
        this.dtoPorCantidad_PrecioUnit = dtoPorCantidad_PrecioUnit;
    }

    @Override
    public String toString() {
        return id_promo + ". " +
                producto.getNombre() + ": " +
                (aplicaDosPorUno ? "2x1" : "") +
                (aplicaDtoPorCantidad ? ("$" + dtoPorCantidad_PrecioUnit + " llevando " + dtoPorCantidad_CantMinima) : "") +
                ", dias: " + getDiasDePromo();
    }
}
