package modelo;

import java.io.Serializable;
import java.util.ArrayList;

import excepciones.ComandaAbiertaException;
import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.MesaSinComandaException;
import excepciones.MozoInexistenteException;
import excepciones.MozoNoDisponibleException;
import excepciones.ProductosInvalidosException;

/**
 * Clase Operario <br>
 * <b>Invariante: </b><br>
 * - nya != null y distinto de vacio. - username != null y distinto de vacio. -
 * password !=null y distinto de vacio.
 */
@SuppressWarnings("serial")
public class Operario implements Serializable {
	private String nya;
	private String username;
	private String password;
	boolean activo;

	/**
	 * Crea una nueva instancia de un Operario. <br>
	 * <b>Pre:</b> Todos los parametros deben ser distintos de null.<br>
	 * Todos los parametros debe ser distinto de vacio.<br>
	 * Ademas, password debe tener entre 6 y 12 caracteres con al menos 1 digito y 1
	 * mayuscula<br>
	 * <b>Post:</b> El nuevo operario esta instanciado y puede ingresar al sistema
	 * mediante su username y contrasena.<br>
	 * 
	 * @param nya      : nombre y apellido del operario.
	 * @param username : nombre de usuario con el cual el operario sera identificado
	 *                 en el sistema.
	 * @param password : Es la constrasena con la cual el operario podra iniciar
	 *                 sesion en el sistema.
	 */
	public Operario(String nya, String username, String password) {
		this.nya = nya;
		this.username = username;
		this.password = password;
		this.activo = true;
		this.invariante();
	}

	// setters
	public void setNya(String nya) {
		this.nya = nya;
		this.invariante();
	}

	public void setUsername(String username) {
		this.username = username;
		this.invariante();
	}

	public void setPassword(String password) {
		this.password = password;
		this.invariante();
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
		this.invariante();
	}

	/**
	 * Modifica el estado del mozo pasado por parametro. Lo hace llamando al metodo
	 * setEstado(mozo,estado) de la cerveceria. <br>
	 * <b>Pre:</b> Todos los parametros deben ser distintos de null.<br>
	 * El mozo debe existir en el ArrayList de mozos de la cerveceria.<br>
	 * El estado debe corresponder a un enumerado valido <br>
	 * <b>Post:</b> El mozo pasara a tener el estado pasado por parametro.<br>
	 * 
	 * @param mozo : Mozo pasado por parametro del cual se desea modificar su
	 *             estado.
	 * @param e:   Estado enumerado que se le asignara al mozo pasado por parametro.
	 * @throws MozoInexistenteException : Se lanza si el mozo pasado por parametro
	 *                                  no existe en el ArrayList de mozos de la
	 *                                  cerveceria.
	 */
	public void setEstado(Mozo mozo, Estado e) throws MozoInexistenteException {

		assert mozo != null : "El mozo debe ser distinto de null.";
		assert e != null : "El estado debe ser distinto de null";

		assert e == Estado.ACTIVO || e == Estado.FRANCO || e == Estado.AUSENTE
				: "El estado no puede ser diferente de ACTIVO, FRANCO o AUSENTE";

		Cerveceria.getInstance().setEstado(mozo, e);
		this.invariante();
	}

	/**
	 * Delega la creacion de la relacion entre un mozo y una mesa. <br>
	 * <b>Pre:</b> mozo != null. mesa !=null.<br>
	 * el mozo y la mesa deben existir en los respectivos ArrayLists de mozos y
	 * mesas de la cerveceria.<br>
	 * <b>Post:</b> El mozo contendra a la mesa asignada en su coleccion de
	 * mesas.<br>
	 * 
	 * @param mozo  : instancia de la clase Mozo que se le asignara la mesa pasada
	 *              por parametro.
	 * @param mesa: mesa que se le asignara al mozo pasado por parametro.
	 * @throws MozoNoDisponibleException : Se lanza si el mozo pasado por parametro
	 *                                   no esta disponible.
	 * @throws MozoInexsitenteException  : Se lanza si el mozo pasado por parametro
	 *                                   no existe en el ArrayList de mozos de la
	 *                                   cerveceria.
	 * @throws MesaNoDisponibleException : Se lanza si la mesa pasada por parametro
	 *                                   no esta disponible.
	 * @throws MesaInexsitenteException  : Se lanza si la mesa pasada por parametro
	 *                                   no existe en el ArrayList de mesas de la
	 *                                   cerveceria..
	 */
	public void asignarMesa(Mozo mozo, Mesa mesa) throws MozoNoDisponibleException, MozoInexistenteException,
			MesaNoDisponibleException, MesaInexistenteException {

		assert mozo != null : "El mozo debe ser distinto de null";
		assert mesa != null : "La mesa debe ser distinto de null";

		Cerveceria.getInstance().asignarMesa(mozo, mesa);
	}

	/**
	 * Delega el cierre de la mesa pasada por parametro a la cerveceria. <br>
	 * <b>Pre:</b> mesa debe ser distinto de null.<br>
	 * formaPago != null. formaPago debe ser "efectivo", "tarjeta", "mercPago" o
	 * "ctaDNI" <br>
	 * La mesa debe estar "OCUPADA" con una comanda abierta. <b>Post:</b> La mesa
	 * pasa a estar libre, la comanda correspondiente a la mesa deja de estar
	 * abierta y se genera la venta con el precio correspondiente <br>
	 * 
	 * @param mesa:      mesa que se desea cerrar.
	 * @param formaPago: cadena que indicara la forma de pago del cliente.
	 * @throws MesaSinComandaException: se lanza si en la mesa que se quiere cerrar no hay una comanda abierta.
	 */
	public void cerrarMesa(Mesa mesa, String formaPago) throws MesaSinComandaException {

		assert mesa != null : "la mesa debe ser distinto de null";
		assert formaPago.equalsIgnoreCase("efectivo") || formaPago.equalsIgnoreCase("tarjeta") || formaPago.equalsIgnoreCase("mercPago")
				|| formaPago.equalsIgnoreCase("ctaDNI") : "forma de pago incorrecta";

		Cerveceria.getInstance().cerrarMesa(mesa, formaPago);
	}

	/**
	 * Carga los pedidos formulados por la mesa a la comanda correspondiente. <br>
	 * <b>Pre:</b> Todos los parametros deben ser distintos de null.<br>
	 * pedidos != null y debe tener al menos 1 elemento.<br>
	 * La mesa debe estar habilitada y tener un mozo activo asociado<br>
	 * <b>Post:</b> Si la mesa estaba "OCUPADA" a su comanda se le agregan los
	 * pedidos, sino se crea la comanda y pasa a estar "LIBRE". Se descuenta el
	 * stock de los productos pedidos<br>
	 * 
	 * @param mesa:    mesa de la cual se toma el pedido pasado por parametro.
	 * @param pedidos: ArrayList de pedidos (cada uno con el producto, sus cantidad
	 *                 y fecha)
	 */
	public void tomarPedido(Mesa mesa, ArrayList<Pedido> pedidos)
			throws MesaInexistenteException, MesaNoDisponibleException,ProductosInvalidosException {

		assert pedidos != null && pedidos.size() > 0
				: "pedidos debe ser distinto de null y debe haberse realizado el pedido de al menos 1 producto";
		assert mesa != null : "la mesa debe ser distinto de null";

		Cerveceria.getInstance().tomarComanda(mesa, pedidos);
	}

	public String getNya() {
		return nya;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean isActivo() {
		return activo;
	}

	public void invariante() {
		assert this.nya != null && !this.nya.equals("")
				: "El nombre y apellido del usuario no puede ser null ni vacio.";
		assert this.username != null && !this.username.equals("") : "El username no puede ser null ni vacio.";
		assert this.password != null && !this.password.equals("") : "El password no puede ser null ni vacio.";

	}

	@Override
	public String toString() {
		return "nya=" + nya + ", username=" + username + ", password=" + password + ", activo=" + activo;
	}

	public String getEstadisticas(Mozo mozo) {
		return Cerveceria.getInstance().getEstadisticas(mozo);
	}

	public void cerrarJornada() throws ComandaAbiertaException {
		Cerveceria.getInstance().cerrarJornada();
	}

}
