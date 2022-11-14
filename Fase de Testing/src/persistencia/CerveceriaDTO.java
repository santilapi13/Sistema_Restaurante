package persistencia;

import modelo.*;

import java.io.Serializable;
import java.util.ArrayList;

public class CerveceriaDTO implements Serializable {

    private String nombreLocal;
    private double sueldo;
    private Admin admin;
    private ArrayList<Mozo> mozos = new ArrayList<Mozo>();
    private ArrayList<Mesa> mesas = new ArrayList<Mesa>();
    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private ArrayList<Operario> operarios = new ArrayList<Operario>();
    private ArrayList<Comanda> comandasAbiertas = new ArrayList<Comanda>();
    private ArrayList<Venta> ventas = new ArrayList<Venta>();
    private ArrayList<PromoTemporal> promoTemporales = new ArrayList<PromoTemporal>();
    private ArrayList<PromoProducto> promosProducto = new ArrayList<PromoProducto>();

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public ArrayList<Mozo> getMozos() {
        return mozos;
    }

    public void setMozos(ArrayList<Mozo> mozos) {
        this.mozos = mozos;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Operario> getOperarios() {
        return operarios;
    }

    public void setOperarios(ArrayList<Operario> operarios) {
        this.operarios = operarios;
    }

    public ArrayList<Comanda> getComandasAbiertas() {
        return comandasAbiertas;
    }

    public void setComandasAbiertas(ArrayList<Comanda> comandasAbiertas) {
        this.comandasAbiertas = comandasAbiertas;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public ArrayList<PromoTemporal> getPromoTemporales() {
        return promoTemporales;
    }

    public void setPromoTemporales(ArrayList<PromoTemporal> promoTemporales) {
        this.promoTemporales = promoTemporales;
    }

    public ArrayList<PromoProducto> getPromosProducto() {
        return promosProducto;
    }

    public void setPromosProducto(ArrayList<PromoProducto> promosProducto) {
        this.promosProducto = promosProducto;
    }
}
