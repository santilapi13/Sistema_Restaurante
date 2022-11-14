package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import excepciones.ProductoInexistenteException;
import modelo.*;
import vista.IVistaLogin;
import vista.VAdmin;

import javax.swing.*;

public class ControladorModificaciones implements ActionListener {

    private IVistaLogin vista = null;
    private static ControladorModificaciones instance = null;
    Operario op = null;
    Producto prod = null;
    Mozo mozo = null;
    PromoTemporal promTemp = null;
    PromoProducto promProd = null;
    Mesa mesa = null;

    public static ControladorModificaciones getInstance() {
        if (instance == null) {
            instance = new ControladorModificaciones();
        }
        return instance;
    }

    //DEPENDIENDO LO QUE SE MODIFIQUE 5 METODOS DE SET VISTA C DISTINTOS PARAMETROS.
    public void setVista(IVistaLogin vista, Producto prod) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.prod = prod;
    }

    public void setVista(IVistaLogin vista, Operario op) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.op = op;
    }

    public void setVista(IVistaLogin vista, Mozo mozo) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.mozo = mozo;
    }

    public void setVista(IVistaLogin vista, PromoProducto prom) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.promProd = prom;
    }

    public void setVista(IVistaLogin vista, PromoTemporal prom) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.promTemp = prom;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if (comando.equalsIgnoreCase("Aceptar")) {

                if (this.prod != null) {        // MODIFICACION DE PRODUCTO
                    double pVenta = this.vista.pVenta();
                    double pCosto = this.vista.pCosto();
                    int stock = this.vista.stock();

                    if (pVenta < 0 && pVenta != -1 || pCosto < 0 && pVenta != -1 || stock < 0 && stock != -1)
                        JOptionPane.showMessageDialog(null, "No se puede ingresar valores negativos. En caso de no modificar, dejar vacio.");
                    else {
                        Cerveceria.getInstance().getAdmin().modificarProducto(prod, pCosto, pVenta, stock);
                        this.vista.cerrarse();
                        ControladorAdmin.getInstance().setVista(new VAdmin());
                    }
                    this.prod = null;
                }
                else if (this.op != null) {    // MODIFICACION DE OPERARIO
                    Cerveceria.getInstance().getAdmin().setEstadoOperario(op, this.vista.getEstadoOperario());
                    this.op = null;
                    this.vista.cerrarse();
                    ControladorAdmin.getInstance().setVista(new VAdmin());
                }
                else if (this.mozo != null) {    // MODIFICACION DE MOZO
                    int cantHijos = this.vista.getHijos();
                    if (cantHijos < 0)
                        JOptionPane.showMessageDialog(null, "La cantidad de hijos no puede ser negativa.");
                    else {
                        Cerveceria.getInstance().getAdmin().modificarMozo(mozo, cantHijos);
                        this.vista.cerrarse();
                        ControladorAdmin.getInstance().setVista(new VAdmin());
                        this.mozo = null;
                    }
                }

            }

            Cerveceria.getInstance().persistirCerveceria();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
