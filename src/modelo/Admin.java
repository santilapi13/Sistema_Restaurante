package modelo;

import excepciones.*;

import java.io.Serializable;
import java.time.DayOfWeek;

/**
 * Clase que representa un operario administrador.<br>
 */
public class Admin extends Operario implements Serializable {

	private boolean isPrimera = true;       //tiene q ser true, para pruebas lo deje en false
	 
    public Admin() {
        super("ADMIN", "ADMIN", "ADMIN1234");
    }

    /**
     * Instacia y agrega un operario a la lista de operarios de la cerveceria.<br>
     * <b>Pre:</b> Los parametros nya, username y password deben ser distinto de null.<br>
     * <b>Post:</b> El operario debe ser instanciado y agregado a la lista de operarios de la cerveceria.<br>
     *
     * @param nya : Es el nombre y apellido reales del operario.
     * @param username : Es el nombre de usuario con el cual el operario sera identificado en el sistema.
     * @param password : Es la constrasena con la cual el operario podra iniciar sesion en el sistema y acceder a sus funciones.
     * @throws OperarioRepetidoException : Se lanza si el operario ya existe en el sistema.
     */
    public void registrarOperario(String nya, String username, String password) throws OperarioRepetidoException {
        assert nya != null : "El nombre y apellido del operario no puede ser nulo.";
        assert username != null : "El nombre de usuario del operario no puede ser nulo.";
        assert password != null : "La contrasena del operario no puede ser nula.";
        Operario operario = new Operario(nya, username, password);
        Cerveceria.getInstance().agregarOperario(operario);
    }
    
    

    /**
     * Elimina un operario de la lista de operarios de la cerveceria.<br>
     * <b>Pre:</b> El parametro username debe ser distinto de null.<br>
     * <b>Post:</b> El operario debe ser eliminado de la lista de operarios de la cerveceria.<br>
     *
     * @param username : Es el nombre de usuario con el cual el operario sera identificado en el sistema.
     * @throws OperarioInexistenteException : Se lanza si el operario no existe en la lista de operarios de la cerveceria.
     */
    public void eliminarOperario(String username) throws OperarioInexistenteException {
        assert username != null : "El nombre de usuario del operario a eliminar no puede ser nulo.";
        Cerveceria.getInstance().eliminarOperario(username);
    }

    /**
     * Agrega un nuevo producto a la carta de la cerveceria.<br>
     * <b>Pre:</b> El parametro nombre debe ser distinto de null. Los parametros pCosto, pVenta y stockInicial deben ser positivos<br>
     * @param nombre : Es el nombre del producto a agregar a la carta de la cerveceria.
     * @param pCosto : Es el precio de costo del producto.
     * @param pVenta : Es el precio de venta del producto.
     * @param stockInicial : Es la cantidad de unidades del producto que se agregaran al stock inicial.
     * @throws ProductoRepetidoException : Se lanza si el producto ya existe en la carta de la cerveceria.
     */
    public void agregarProducto(String nombre, double pCosto, double pVenta, int stockInicial) throws ProductoRepetidoException {
        assert nombre != null : "El nombre del producto no puede ser nulo.";
        Producto producto = new Producto(nombre, pCosto, pVenta, stockInicial);
        Cerveceria.getInstance().agregarProducto(producto);
    }

    /**
     * Elimina un producto de la carta de la cerveceria.<br>
     * <b>Pre:</b> El parametro nombre debe ser distinto de null.<br>
     * <b>Post:</b> El producto debe ser eliminado de la carta de la cerveceria.<br>
     *
     * @param nombre : Es el nombre del producto a eliminar de la carta de la cerveceria.
     * @throws ProductoInexistenteException : Se lanza si el producto no existe en la carta de la cerveceria.
     */
    public void eliminarProducto(String nombre) throws ProductoInexistenteException {
        assert nombre != null : "El nombre del producto a eliminar no puede ser nulo.";
        Cerveceria.getInstance().eliminarProducto(nombre);
    }

    /**
     * Actualiza un producto de la carta de la cerveceria. En caso de que un parametro sea null o -1, no lo modifica.<br>
     * <b>Pre:</b> El parametro nombre debe ser distinto de null. Los parametros pCosto, pVenta y stock deben ser positivos o -1.<br>
     * <b>Post:</b> Los atributos de producto deben ser actualizados con los proporcionados en los parametros.<br>
     *
     * @param nombre : Es el nombre del producto a modificar.
     * @param pCosto : Es el nuevo precio de costo del producto.
     * @param pVenta : Es el nuevo precio de venta del producto.
     * @param stock : Es la nueva cantidad de unidades en stock del producto.
     * @throws ProductoInexistenteException : Se lanza si el producto no existe en la carta de la cerveceria.
     */
    public void modificarProducto(String nombre, double pCosto, double pVenta, int stock) throws ProductoInexistenteException {
        assert nombre != null : "El nombre del producto a modificar no puede ser nulo.";
        assert pCosto > 0 || pCosto == -1 : "El precio de costo del producto debe ser -1 (no se actualiza) o positivo.";
        assert pVenta > 0 || pVenta == -1 : "El precio de venta del producto debe ser -1 (no se actualiza) o positivo.";
        assert stock > 0 || stock == -1 : "El stock del producto debe ser -1 (no se actualiza) o positivo.";
        Cerveceria.getInstance().modificarProducto(nombre, pCosto, pVenta, stock);
    }

    /**
     * Agrega una promocion a la lista de promociones de producto.<br>
     * <b>Pre:</b> El parametro promocion debe ser distinto de null. Los parametros aplicaDosPorUno y aplicaDtoPorCantidad no pueden ser simultáneamente false.
     * dtoPorCantidad_CantMinima y dtoPorCantidad_Precio_Unit deben ser mayor o igual a 0 (si son 0 es porque no aplicaDtoPorCantidad).<br>
     * <b>Post:</b> Se agregara una promocion a la lista de promociones de producto.<br>
     * @param nombreProducto: El nombre del producto al que se le aplica la promocion.
     * @param aplicaDosPorUno: Indica si la promocion aplica dos por uno.
     * @param aplicaDtoPorCantidad: Indica si la promocion aplica descuento por cantidad.
     * @param dtoPorCantidad_CantMinima: La cantidad minima para aplicar el descuento por cantidad.
     * @param dtoPorCantidad_PrecioUnit: El precio unitario para aplicar el descuento por cantidad.
     * @throws ProductoInexistenteException: Se lanza si el producto no existe.
     * @throws PromocionRepetidaException : Se lanza si la promocion ya existe.
     */
    public void agregarPromocion(String nombreProducto, boolean aplicaDosPorUno, boolean aplicaDtoPorCantidad, int dtoPorCantidad_CantMinima, double dtoPorCantidad_PrecioUnit) throws ProductoInexistenteException, PromocionRepetidaException {
        assert nombreProducto != null : "El producto no puede ser null";
        assert aplicaDosPorUno || aplicaDtoPorCantidad : "Debe aplicar al menos un tipo de promoción";
        assert dtoPorCantidad_CantMinima >= 0 : "La cantidad mínima debe ser mayor o igual a 0";
        assert dtoPorCantidad_PrecioUnit >= 0 : "El precio unitario debe ser mayor o igual a 0";
        if (aplicaDtoPorCantidad) {
            assert dtoPorCantidad_CantMinima > 0 : "La cantidad mínima debe ser mayor a 0";
            assert dtoPorCantidad_PrecioUnit > 0 : "El precio unitario debe ser mayor a 0";
        }
        Cerveceria.getInstance().agregarPromocion(nombreProducto, aplicaDosPorUno, aplicaDtoPorCantidad, dtoPorCantidad_CantMinima, dtoPorCantidad_PrecioUnit);
    }

    /**
     * Agrega una promocion a la lista de promociones temporales.<br>
     * <b>Pre:</b> El parametro nombre debe ser distinto de null. El parametro formaPago debe ser distinto de null.
     * El porcentaje de descuento debe ser un numero entre 0 y 1. Las horaInicio y horaFin deben ser distintas de null.<br>
     * <b>Post:</b> Se agregara una promocion a la lista de promociones temporales.<br>
     * @param nombre: El nombre de la promocion.
     * @param formaPago: La forma de pago para aplicar la promocion.
     * @param porcentajeDesc: El porcentaje de descuento.
     * @param esAcumulable: Indica si la promocion es acumulable.
     * @param horaInicio: La hora de inicio de la promocion.
     * @param horaFin: La hora de fin de la promocion.
     * @throws PromocionRepetidaException: Se lanza si la promocion ya existe.
     */
    public void agregarPromocion(String nombre, FormaPago formaPago, double porcentajeDesc, boolean esAcumulable, int horaInicio, int horaFin) throws PromocionRepetidaException {
        assert nombre != null : "El nombre no puede ser null";
        assert porcentajeDesc > 0 : "El porcentaje de descuento debe ser mayor a 0";
        assert horaInicio >= 0 && horaInicio <= 23 : "La hora de inicio debe ser un numero entre 0 y 23";
        assert horaFin >= 0 && horaFin <= 23 : "La hora de fin debe ser un numero entre 0 y 23";

        Cerveceria.getInstance().agregarPromocion(nombre, formaPago, porcentajeDesc, esAcumulable, horaInicio, horaFin);
    }

    /**
     * Elimina una promocion de la lista de promociones de producto.<br>
     * <b>Pre:</b> El parametro id_promo debe ser mayor o igual a 0.<br>
     * <b>Post:</b> Se eliminara una promocion de la lista de promociones de producto.<br>
     *
     * @param id_promo: El id de la promocion.
     * @throws PromocionInexistenteException: Se lanza si la promocion no existe.
     */
    public void eliminarPromocion(int id_promo) throws PromocionInexistenteException {
        assert id_promo >= 0 : "El id de la promocion debe ser mayor o igual a 0";
        Cerveceria.getInstance().eliminarPromocion(id_promo);
    }

    /**
     * Elimina una promocion de la lista de promociones temporales.<br>
     * <b>Pre:</b> El parametro nombre debe ser distinto de null.<br>
     * <b>Post:</b> Se eliminara una promocion de la lista de promociones temporales.<br>
     *
     * @param nombre: El nombre de la promocion.
     * @throws PromocionInexistenteException: Se lanza si la promocion no existe.
     */
    public void eliminarPromocion(String nombre) throws PromocionInexistenteException {
        assert nombre != null : "El nombre no puede ser null";

        Cerveceria.getInstance().eliminarPromocion(nombre);
    }

    public void agregarDiaPromocion(DayOfWeek dia, String nombrePromocion) throws PromocionInexistenteException {
        assert nombrePromocion != null : "El nombre de la promocion no puede ser null";
        assert dia != null : "El dia no puede ser null";
        Cerveceria.getInstance().agregarDiaPromocion(nombrePromocion, dia);
    }

    public void agregarDiaPromocion(DayOfWeek dia, int idPromocion) throws PromocionInexistenteException {
        assert idPromocion >= 0 : "El id de la promocion debe ser mayor o igual a 0";
        assert dia != null : "El dia no puede ser null";
        Cerveceria.getInstance().agregarDiaPromocion(idPromocion, dia);
    }

    public void agregarMozo(String nya, int cantHijos, String fechaNacimiento) throws MozoRepetidoException {
        assert nya != null : "El nombre y apellido no puede ser null";
        assert cantHijos >= 0 : "La cantidad de hijos debe ser mayor o igual a 0";
        assert fechaNacimiento != null : "La fecha de nacimiento no puede ser null";
        Cerveceria.getInstance().agregarMozo(nya, cantHijos, fechaNacimiento);
    }

    public void eliminarMozo(String nya) throws MozoInexistenteException {
        assert nya != null : "El nombre y apellido no puede ser null";
        Cerveceria.getInstance().eliminarMozo(nya);
    }

    public void modificarMozo(String nya, int cantHijos) throws MozoInexistenteException {
        assert nya != null : "El nombre y apellido no puede ser null";
        assert cantHijos >= 0 : "La cantidad de hijos debe ser mayor o igual a 0";
        Cerveceria.getInstance().modificarMozo(nya, cantHijos);
    }

    public void agregarMesa(int cantComensales) {
        assert cantComensales >= 0 : "La cantidad de comensales debe ser mayor o igual a 0";
        Cerveceria.getInstance().agregarMesa(cantComensales);
    }

    public void eliminarMesa(int nroMesa) throws MesaInexistenteException {
        assert nroMesa >= 0 : "El numero de mesa debe ser mayor o igual a 0";
        Cerveceria.getInstance().eliminarMesa(nroMesa);
    }

    public void modificarMesa(int nroMesa, int cantComensales) throws MesaInexistenteException {
        assert nroMesa >= 0 : "El numero de mesa debe ser mayor o igual a 0";
        assert cantComensales >= 0 : "La cantidad de comensales debe ser mayor o igual a 0";
        Cerveceria.getInstance().modificarMesa(nroMesa, cantComensales);
    }

	public boolean isPrimera() {
		return isPrimera;
	}

	public void setPrimera(boolean isPrimera) {
		this.isPrimera = isPrimera;
	}
    
    

    
    
}
