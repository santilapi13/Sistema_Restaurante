package modelo;

import excepciones.OperarioInexistenteException;
import excepciones.OperarioRepetidoException;
import excepciones.ProductoInexistenteException;
import excepciones.ProductoRepetidoException;

/**
 * Clase que representa un operario administrador.<br>
 */
public class Admin extends Operario {

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

}
