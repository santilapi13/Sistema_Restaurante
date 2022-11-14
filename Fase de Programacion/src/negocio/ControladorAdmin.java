package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.*;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBin;
import persistencia.UtilCerveceria;
import vista.*;

public class ControladorAdmin implements ActionListener {

    private IVistaLogin vista = null;                    //si hacemos interfaz de user cambiar
    private static ControladorAdmin instance = null;
    String tipo = null;


    public static ControladorAdmin getInstance() {
        if (instance == null) {
            instance = new ControladorAdmin();
        }
        return instance;
    }

    public void setVista(IVistaLogin vista, String tipo) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.tipo = tipo;
        this.actualizarTodo();
    }

    public void setVista(IVistaLogin vista) {
        this.vista = vista;
        this.vista.setActionListener(this);
        this.actualizarTodo();
    }

    private void actualizarTodo() {
        this.vista.ActualizarListaOperarios(Cerveceria.getInstance().getOperarios());
        this.vista.ActualizarMozos(Cerveceria.getInstance().getMozos());
        this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
        this.vista.ActualizarProductos(Cerveceria.getInstance().getCarta());
        this.vista.ActualizarPromociones(Cerveceria.getInstance().getPromosProductos(), Cerveceria.getInstance().getPromosTemporales());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {

            if (comando.equalsIgnoreCase("ELIMINAR") || comando.equalsIgnoreCase("MODIFICAR")) {
                int opcionesSeleccionadas = 0;
                if (!this.vista.getIsProductoEmpty())
                    opcionesSeleccionadas++;
                if (!this.vista.getIsMozoEmpty())
                    opcionesSeleccionadas++;
                if (!this.vista.getIsOperarioEmpty())
                    opcionesSeleccionadas++;
                if (!this.vista.getIsMesaEmpty())
                    opcionesSeleccionadas++;
                if (!this.vista.getIsPromocionTempEmpty())
                    opcionesSeleccionadas++;
                if (!this.vista.getIsPromocionProdEmpty())
                    opcionesSeleccionadas++;

                if (opcionesSeleccionadas != 1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un solo elemento de una unica lista.");
                    this.vista.deseleccionarTodo();
                }
            }

            // CAMBIO DE CONTRASENA
            if (comando.equalsIgnoreCase("CAMBIAR CONTRASENA")) {    // Abre ventana de cambiar contrasena
                this.vista.cerrarse();
                ControladorAdmin.getInstance().setVista(new VContrasena());
            }

            else if (comando.equalsIgnoreCase("APLICAR CAMBIOS")) {    // Cambia la contrasena
                String passActual = this.vista.getPasswordActual();
                String pass = this.vista.getPassword();

                if (!passActual.equals(Cerveceria.getInstance().getAdmin().getPassword()))
                    JOptionPane.showMessageDialog(null, "La contrasena actual no es correcta.");
                else if (passActual.equals(pass))
                    JOptionPane.showMessageDialog(null, "La nueva contrasena no puede ser igual a la anterior.");
                else if (pass.length() < 5)
                    JOptionPane.showMessageDialog(null, "La nueva contrasena debe tener al menos 5 caracteres.");
                else {
                    Cerveceria.getInstance().getAdmin().cambiarContrasena(pass, passActual);
                    this.vista.cerrarse();
                    ControladorLogin.getInstance().setVista(new VLogin());
                }
            }

            // VENTANAS DE AGREGAR COSAS
            else if (comando.equalsIgnoreCase("AGREGAR OPERARIO")) {    // Abre ventana de registrar operario
                this.vista.cerrarse();
                ControladorAdmin.getInstance().setVista(new VRegOp(), "registroOP");
            }

            else if (comando.equalsIgnoreCase("AGREGAR MOZO")) {        // Abre ventana de registrar mozo
                this.vista.cerrarse();
                ControladorAdmin.getInstance().setVista(new VMozo(), "RegistroMo");
            }

            else if (comando.equalsIgnoreCase("AGREGAR MESA")) {        // Agrega una nueva mesa
                int capacidad = this.vista.getComensales();

                if (capacidad < 2)
                    JOptionPane.showMessageDialog(null, "Las mesas deben admitir al menos 2 comensales");
                else {
                    Cerveceria.getInstance().getAdmin().agregarMesa(this.vista.getComensales());
                    this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
                }
            }

            else if (comando.equalsIgnoreCase("AGREGAR PRODUCTO")) {        // Abre la ventana de agregar producto
                this.vista.cerrarse();
                ControladorAdmin.getInstance().setVista(new VProducto(), "RegistroProd");
            }

            else if (comando.equalsIgnoreCase("AGREGAR PROMO PROD")) {        // Abre la ventana de agregar promocion
                this.vista.cerrarse();
                ControladorAdmin.getInstance().setVista(new VPromoProd(), "RegistroPromoProd");
            }

            else if (comando.equalsIgnoreCase("AGREGAR PROMO TEMP")) {        // Abre la ventana de agregar promocion
                this.vista.cerrarse();
                ControladorAdmin.getInstance().setVista(new VPromoTemp(), "RegistroPromoTemp");
            }

            // AGREGADO DE NUEVAS COSAS
            else if (comando.equalsIgnoreCase("ACEPTAR")) {        // Agregar un nuevo operario, mozo, producto o promocion

                if (this.tipo.equalsIgnoreCase("RegistroOp")) {
                    String nya = this.vista.getNya();
                    String pass = this.vista.getPassword();
                    String username = this.vista.getUsername();

                    if (nya.length() < 5)
                        JOptionPane.showMessageDialog(null, "El nombre y apellido debe tener al menos 5 caracteres.");
                    else if (pass.length() < 5)
                        JOptionPane.showMessageDialog(null, "La contrasena debe tener al menos 5 caracteres.");
                    else if (username.length() < 5)
                        JOptionPane.showMessageDialog(null, "El nombre de usuario debe tener al menos 5 caracteres.");
                    else {
                        Cerveceria.getInstance().getAdmin().registrarOperario(nya, username, pass);
                        this.vista.cerrarse();
                        ControladorAdmin.getInstance().setVista(new VAdmin());
                    }
                }
                else if (this.tipo.equalsIgnoreCase("RegistroMo")) {
                    String nya = this.vista.getNya();
                    int hijos = this.vista.getHijos();
                    String fecha = this.vista.fecha();

                    if (nya.length() < 5)
                        JOptionPane.showMessageDialog(null, "El nombre y apellido debe tener al menos 5 caracteres.");
                    else if (hijos < 0)
                        JOptionPane.showMessageDialog(null, "La cantidad de hijos no puede ser negativa.");
                    else if (fecha.length() != 10)
                        JOptionPane.showMessageDialog(null, "La fecha de nacimiento debe estar en formato dd/mm/aaaa.");
                    else {
                        Cerveceria.getInstance().getAdmin().agregarMozo(nya, hijos, fecha);
                        this.vista.cerrarse();
                        ControladorAdmin.getInstance().setVista(new VAdmin());
                    }
                }
                else if (this.tipo.equalsIgnoreCase("RegistroProd")) {
                    String nombre = this.vista.getNya();
                    double pVenta = this.vista.pVenta();
                    double pCosto = this.vista.pCosto();
                    int stock = this.vista.stock();

                    if (nombre.length() < 5)
                        JOptionPane.showMessageDialog(null, "El nombre del producto debe tener al menos 4 caracteres.");
                    else if (pVenta < 0 || pCosto < 0 || stock < 0)
                        JOptionPane.showMessageDialog(null, "No se pueden dejar campos vacios ni ingresar valores negativos.");
                    else if (pVenta < pCosto)
                        JOptionPane.showMessageDialog(null, "El precio de venta no puede ser menor al precio de costo.");
                    else {
                        Cerveceria.getInstance().getAdmin().agregarProducto(nombre, pCosto, pVenta, stock);
                        this.vista.cerrarse();
                        ControladorAdmin.getInstance().setVista(new VAdmin());
                    }
                }
                else if (this.tipo.equalsIgnoreCase("registroPromoProd")) {
                    Producto producto = this.vista.getProdSeleccionado();
                    boolean dosporuno = this.vista.is2x1();
                    boolean cant = this.vista.isCantidad();
                    int cantMin = this.vista.getCantMinima();
                    double pUnitario = this.vista.getpUnitario();
                    ArrayList<DayOfWeek> diasDePromo = this.vista.getDias();

                    if (!dosporuno && !cant)
                        JOptionPane.showMessageDialog(null, "Debe aplicar al menos un tipo de promocion.");
                    else if (cant && (pUnitario <= 0 || cantMin <= 0))
                        JOptionPane.showMessageDialog(null, "Si descuento por cantidad esta activo, no dejar vacios sus campos ni poner en ellos numeros no positivos.");
                    else if (pUnitario >= Cerveceria.getInstance().getCarta().get(Cerveceria.getInstance().getCarta().indexOf(producto)).getpVenta())
                        JOptionPane.showMessageDialog(null, "El precio de descuento debe ser menor al precio de venta del producto.");
                    else if (diasDePromo.isEmpty())
                        JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un dia de la semana.");
                    else {
                        Cerveceria.getInstance().getAdmin().agregarPromocion(producto.getNombre(), dosporuno, cant, cantMin, pUnitario, diasDePromo);
                        this.vista.cerrarse();
                        ControladorAdmin.getInstance().setVista(new VAdmin());
                    }

                }
                else if (this.tipo.equalsIgnoreCase("registroPromoTemp")) {
                    String nombre = this.vista.getNya();
                    FormaPago forma = this.vista.getFormaPago();
                    double porcentaje = this.vista.getPorcentaje();
                    boolean isAcum = this.vista.isAcumulable();
                    int horaIn = this.vista.getHoraInicio();
                    int horaFin = this.vista.getHoraFin();

                    if (nombre.length() < 5)
                        JOptionPane.showMessageDialog(null, "El nombre de la promocion debe tener al menos 5 caracteres.");
                    else if (!FormaPago.EFECTIVO.equals(forma) && !FormaPago.TARJETA.equals(forma) && !FormaPago.MP.equals(forma) && !FormaPago.CTADNI.equals(forma))
                        JOptionPane.showMessageDialog(null, "La forma de pago debe ser una de las opciones validas.");
                    else if (porcentaje < 0 || porcentaje > 100)
                        JOptionPane.showMessageDialog(null, "El porcentaje debe estar entre 0 y 100.");
                    else if (horaIn < 0 || horaIn > 23 || horaFin < 0 || horaFin > 23)
                        JOptionPane.showMessageDialog(null, "Las horas deben estar entre 0 y 23.");
                    else if (horaIn > horaFin)
                        JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser mayor a la hora de fin.");
                    else if (this.vista.getDias().isEmpty())
                        JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un dia de la semana.");
                    else {
                        Cerveceria.getInstance().getAdmin().agregarPromocion(nombre, forma, porcentaje/100, isAcum, horaIn, horaFin, this.vista.getDias());
                        this.vista.cerrarse();
                        ControladorAdmin.getInstance().setVista(new VAdmin());
                    }
                }

            }

            else if (comando.equalsIgnoreCase("MODIFICAR")) {    // Abre la ventana de modificacion correspondiente

                if (!this.vista.getIsProductoEmpty()) {    // Modificar producto
                    this.vista.cerrarse();
                    ControladorModificaciones.getInstance().setVista(new VProducto(), this.vista.getProdSeleccionado());
                }
                else if (!this.vista.getIsMesaEmpty()) {    // Modificar mesa
                    int capacidad = this.vista.getComensales();
                    int nroMesa = this.vista.getMesaSeleccionada().getNroMesa();

                    if (nroMesa > 0 && capacidad < 2)
                        JOptionPane.showMessageDialog(null, "Las mesas deben admitir al menos 2 comensales");
                    else if (nroMesa == 0 && capacidad <= 0)
                        JOptionPane.showMessageDialog(null, "La barra debe admitir al menos 1 comensal");
                    else {
                        Cerveceria.getInstance().modificarMesa(this.vista.getMesaSeleccionada().getNroMesa(), this.vista.getComensales());
                        this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
                    }
                }
                else if (!this.vista.getIsMozoEmpty()) {    // Modificar mozo
                    this.vista.cerrarse();
                    ControladorModificaciones.getInstance().setVista(new VMozo(), this.vista.getMozoSeleccionado());
                    JOptionPane.showMessageDialog(null, "Solo se modificara la cantidad de hijos del mozo.");
                }
                else if (!this.vista.getIsOperarioEmpty()) {    // Modificar operario
                    this.vista.cerrarse();
                    ControladorModificaciones.getInstance().setVista(new VRegOp(), this.vista.getOperarioSeleccionado());
                    JOptionPane.showMessageDialog(null, "Solo se modificara el estado del operario.");
                }

            } else if (comando.equalsIgnoreCase("ELIMINAR")) {

                if (!this.vista.getIsProductoEmpty()) {
                    Cerveceria.getInstance().getAdmin().eliminarProducto(this.vista.getProdSeleccionado());
                    this.vista.ActualizarProductos(Cerveceria.getInstance().getCarta());
                } else if (!this.vista.getIsMesaEmpty()) {
                    Mesa mesa = this.vista.getMesaSeleccionada();
                    if (mesa.getNroMesa() == 0)
                        JOptionPane.showMessageDialog(null, "No se puede eliminar la barra, solo modificar su capacidad.");
                    else {
                        Cerveceria.getInstance().eliminarMesa(mesa);
                        this.vista.ActualizarMesas(Cerveceria.getInstance().getMesas());
                    }
                } else if (!this.vista.getIsMozoEmpty()) {
                    Cerveceria.getInstance().getAdmin().eliminarMozo(this.vista.getMozoSeleccionado());
                    this.vista.ActualizarMozos(Cerveceria.getInstance().getMozos());
                } else if (!this.vista.getIsOperarioEmpty()) {
                    Cerveceria.getInstance().getAdmin().eliminarOperario(this.vista.getOperarioSeleccionado());
                    this.vista.ActualizarListaOperarios(Cerveceria.getInstance().getOperarios());
                } else if (!this.vista.getIsPromocionProdEmpty()) {
                    Cerveceria.getInstance().getAdmin().eliminarPromocion(this.vista.getPromocionProdSeleccionada());
                    this.vista.ActualizarPromociones(Cerveceria.getInstance().getPromosProductos(), Cerveceria.getInstance().getPromosTemporales());
                } else if (!this.vista.getIsPromocionTempEmpty()) {
                    Cerveceria.getInstance().getAdmin().eliminarPromocion(this.vista.getPromocionTempSeleccionada());
                    this.vista.ActualizarPromociones(Cerveceria.getInstance().getPromosProductos(), Cerveceria.getInstance().getPromosTemporales());
                }

            } else if (comando.equalsIgnoreCase("SALIR")) {
                this.vista.cerrarse();
                ControladorLogin.getInstance().setVista(new VLogin());
            }

            Cerveceria.getInstance().persistirCerveceria();

        } catch (Exception e2){
            JOptionPane.showMessageDialog(null, e2.getMessage());
        }

    }

}
