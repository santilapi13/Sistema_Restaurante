package persistencia;

import modelo.*;

import java.io.Serializable;
import java.util.ArrayList;

public class CerveceriaDTO implements Serializable {

    private String nombreLocal;
    private ArrayList<Mozo> mozos = new ArrayList<Mozo>();
    private ArrayList<Mesa> mesas = new ArrayList<Mesa>();
    private ArrayList<Producto> carta = new ArrayList<Producto>();
    private ArrayList<Operario> operarios = new ArrayList<Operario>();
    private ArrayList<Comanda> comandasAbiertas = new ArrayList<Comanda>();
    private ArrayList<Venta> ventas = new ArrayList<Venta>();
    private Admin administrador = new Admin();
    private ArrayList<PromoProducto> promosProductos = new ArrayList<PromoProducto>();
    private ArrayList<PromoTemporal> promosTemporales = new ArrayList<PromoTemporal>();
    Operario operarioLogueado = null;

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
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

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }

    public ArrayList<Producto> getCarta() {
        return carta;
    }

    public void setCarta(ArrayList<Producto> carta) {
        this.carta = carta;
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

    public Admin getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Admin administrador) {
        this.administrador = administrador;
    }

    public ArrayList<PromoProducto> getPromosProductos() {
        return promosProductos;
    }

    public void setPromosProductos(ArrayList<PromoProducto> promosProductos) {
        this.promosProductos = promosProductos;
    }

    public ArrayList<PromoTemporal> getPromosTemporales() {
        return promosTemporales;
    }

    public void setPromosTemporales(ArrayList<PromoTemporal> promosTemporales) {
        this.promosTemporales = promosTemporales;
    }

    public Operario getOperarioLogueado() {
        return operarioLogueado;
    }

    public void setOperarioLogueado(Operario operarioLogueado) {
        this.operarioLogueado = operarioLogueado;
    }
}
