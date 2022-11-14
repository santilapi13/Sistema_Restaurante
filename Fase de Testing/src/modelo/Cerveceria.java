package modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import excepciones.ComandaAbiertaException;
import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.MesaRepetidaException;
import excepciones.MesaSinComandaException;
import excepciones.MozoInexistenteException;
import excepciones.MozoNoDisponibleException;
import excepciones.MozoRepetidoException;
import excepciones.OperarioInexistenteException;
import excepciones.ProductoEnComandaException;
import excepciones.ProductoInexistenteException;
import excepciones.ProductoRepetidoException;
import excepciones.ProductosInvalidosException;
import excepciones.PromoInexistenteException;
import excepciones.PromoRepetidaException;
import excepciones.UsuarioRepetidoException;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import persistencia.UtilPersistencia;

/**
 * Clase Cerveceria <br>
 * <b>Invariante: </b><br>
 * - productos != null. mesas != null. comandasAbiertas!=null. 
 * - promosTemporales != null. promosProducto !=null.
 */

public class Cerveceria {
	private static Cerveceria instance = null;
	private String nombreLocal;
	private double sueldo;
	private Admin admin;
	private ArrayList<Mozo> mozos = new ArrayList<Mozo>();
	private ArrayList<Mesa> mesas = new ArrayList<Mesa>();
	private ArrayList<Producto> productos = new ArrayList<Producto>();
	private ArrayList<Operario> operarios = new ArrayList<Operario>();
	private ArrayList<Comanda> comandasAbiertas = new ArrayList<Comanda>();
	private ArrayList<Venta> ventas = new ArrayList<Venta>();
	private ArrayList<PromoTemporal> promosTemporales = new ArrayList<PromoTemporal>();
	private ArrayList<PromoProducto> promosProducto = new ArrayList<PromoProducto>();

	private Cerveceria() {
		this.admin = new Admin();
	}

	public static Cerveceria getInstance() {
		if (instance == null)
			instance = new Cerveceria();
		return instance;
	}
	
	public static void setInstance() {
		if (instance != null)
			instance = null;
	}

	/**
	 * Modifica el estado del mozo pasado por parametro (por el estado e).<br>
	 * <b>Pre:</b> mozo != null. e != null y debe ser ACTIVO, FRANCO o AUSENTE.<br>
	 * <b>Post:</b> El mozo pasado por parametro tendra como nuevo estado el pasado
	 * por parametro.<br>
	 *
	 * @param mozo : mozo al cual se quiere cambiar su estado.
	 * @param e    : nuevo estado que se le asignara al mozo.
	 * @throws MozoInexsitenteException : Se lanza si el mozo pasado por parametro
	 *                                  no existe en el ArrayList de mozos de la
	 *                                  cerveceria.
	 * 
	 */

	public void setEstado(Mozo mozo, Estado e) throws MozoInexistenteException {
		int i = 0;
		while (i < mozos.size() && !mozo.equals(mozos.get(i)))
			i++;
		if (i < mozos.size() && mozo.equals(mozos.get(i)))
			mozo.setEstado(e);
		else
			throw new MozoInexistenteException("El mozo seleccionado no existe");

		this.invariante();
	}

	/**
	 * Asigna al mozo pasado por par�metro la mesa pasada por par�metro<br>
	 * <b>Pre:</b> mozo != null. mesa != null<br>
	 * <b>Post:</b> La mesa se agrega al ArrayList de mesas del mozo. El estado de
	 * la mesa pasa a "OCUPADA"<br>
	 * 
	 * @param mozo : El mozo al cual se quiere agregar una mesa.
	 * @param mesa : Numero de mesa a asignarle.
	 * 
	 * @throws MozoNoDisponibleException : Se lanza si el mozo pasado por par�metro
	 *                                   no est� disponible.
	 * @throws MozoInexsitenteException  : Se lanza si el mozo pasado por par�metro
	 *                                   no existe en el ArrayList de mozos de la
	 *                                   cerveceria.
	 * @throws MesaNoDisponibleException : Se lanza si la mesa pasada por par�metro
	 *                                   no est� disponible.
	 * @throws MesaInexsitenteException  : Se lanza si la mesa pasada por par�metro
	 *                                   no existe en el ArrayList de mesas de la
	 *                                   cerveceria.
	 */
	public void asignarMesa(Mozo mozo, Mesa mesa) throws MozoNoDisponibleException, MozoInexistenteException,
			MesaNoDisponibleException, MesaInexistenteException {

		int i = 0;

		while (i < mozos.size() && !mozo.equals(mozos.get(i)))
			i++;

		if (i < mozos.size() && mozo.equals(mozos.get(i))) { // existe el mozo

			if (mozo.getEstado() == Estado.ACTIVO) { // el mozo esta disponible
				i = 0;

				while (i < mesas.size() && mesa.getNroMesa()!=mesas.get(i).getNroMesa())
					i++;

				if (i < mesas.size() && mesa.getNroMesa()==mesas.get(i).getNroMesa()) { // la mesa existe

					if (mesa.getEstado().equalsIgnoreCase("LIBRE") && !mesa.isAsignado()) { // el mozo y la mesa estan disponibles
						mozo.addMesa(mesa);
						mesa.setAsignado(true);

					} 
					else { //la mesa no esta disponible
						String msg;
						if (mesa.isAsignado())
							 msg = "ERROR: La mesa ya estaba asignada";
						else
							msg="La mesa " + mesa.getNroMesa() + " no esta disponible";
						throw new MesaNoDisponibleException(msg);
					}

				} else
					throw new MesaInexistenteException("La mesa seleccionada no existe");
			} else
				throw new MozoNoDisponibleException("El mozo " + mozo.getNya() + " no esta disponible");
		} else
			throw new MozoInexistenteException("El mozo seleccionado no existe");

		this.invariante();
	}

	/**
	 * Inicializa el total de ventas y la cantidad de ventas de cada mozo de la
	 * cervecer�a en 0<br>
	 * <b>Pre:La lista de mozos debe estar inicializada.</b> <br>
	 * <b>Post:</b> Cada mozo del ArrayList de mozos tiene ventas totales = 0 y cant
	 * ventas = 0<br>
	 */
	public void reiniciarMozos() {
		for (Mozo mozoAct : this.mozos)
			mozoAct.reinicio();
		
		this.invariante();
	}

	public void cerrarMesa(Mesa mesa, String formaPago) throws MesaSinComandaException {
		int pos;
		boolean temporal = false, enc;
		ArrayList<Promocion> listaPromos = new ArrayList<Promocion>();
		PromoTemporal promoTemp;
		PromoProducto promoProducto;
		double total = 0;

		pos = getComanda(mesa);
		if (pos != -1) {
			Comanda comandaAct = this.comandasAbiertas.get(pos);

			/*
			 * Busca si hay una promo temporal activa
			 */
			Iterator<PromoTemporal> iteratorPromoTemp = this.promosTemporales.iterator();
			while (iteratorPromoTemp.hasNext() && !temporal) {
				promoTemp = (PromoTemporal) iteratorPromoTemp.next();
				if (promoTemp.getDiasDePromo().contains(Util.intToDia(comandaAct.getFecha().getDay()))
						&& promoTemp.isActiva())
					if (promoTemp.getFormaPago().equals(formaPago)) {
						temporal = true;
						listaPromos.add(promoTemp);
					}
			}

			/*
			 * Busca por cada producto la promo que le corresponda
			 */
			for (Pedido pedido : comandaAct.listaProductos) {
				Iterator<PromoProducto> iteratorPromoProducto = this.promosProducto.iterator();
				enc = false;
				while (iteratorPromoProducto.hasNext() && !enc) {
					promoProducto = (PromoProducto) iteratorPromoProducto.next();
					if ((pedido.getProducto().equals(promoProducto.getProducto()))
							&& promoProducto.getDiasDePromo().contains(Util.intToDia(comandaAct.getFecha().getDay()))
							&& promoProducto.isActiva()) {
						enc = true;
						listaPromos.add(promoProducto);
					}
				}
				if (enc) {
					promoProducto = (PromoProducto) listaPromos.get(listaPromos.size() - 1);
					if (temporal) {
						promoTemp = (PromoTemporal) listaPromos.get(0);
						if (promoTemp.isEsAcumulable())
							total += getPrecioConDescuento(pedido, promoProducto) * promoTemp.getPorcentajeDesc() / 100;
						else
							total += getPrecioConDescuento(pedido, promoProducto);
					} else
						total += getPrecioConDescuento(pedido, promoProducto);
				} else if (temporal) {
					promoTemp = (PromoTemporal) listaPromos.get(0);
					total += (pedido.getProducto().getpVenta() * pedido.getCantidad()) * promoTemp.getPorcentajeDesc()
							/ 100;
				} else
					total += pedido.getProducto().getpVenta() * pedido.getCantidad();
			}

			// busca el mozo que tiene asignada la mesa
			Mozo mozo = BuscarMozo(mesa);
			mozo.addCantVentas();
			mozo.addTotalVentas(total);
			comandaAct.setEstado("CERRADA");
			mesa.setEstado("LIBRE");
			this.ventas.add(new Venta(comandaAct, total, formaPago, listaPromos));
			this.comandasAbiertas.remove(pos);

		} else
			throw new MesaSinComandaException("No se puede cerrar la mesa ya que no tiene una comanda abierta asociada");

		
		this.invariante();

	}

	private Mozo BuscarMozo(Mesa mesa) {
		boolean enc = false;
		Mozo mozo = null;
		Iterator<Mozo> mozoIt = this.getMozos().iterator();
		while (mozoIt.hasNext() && !enc) {
			mozo = mozoIt.next();
			if (mozo.getMesas().contains(mesa))
				enc = true;
		}
		return mozo;
	}

	/**
	 * Retorna el precio con descuento del pedido pasado por parametro considerando
	 * la promo del producto del producto de ese pedido (y la cantidad pedida)<br>
	 * <b>Pre:pedido != null. promoProducto !=null y promoProducto debe estar
	 * ACTIVA. Ademas debe coincidir el dia de promocion de promoProducto con el del
	 * producto del pedido.</b> <br>
	 * <b>Post:</b> Devolvera el precio con Descuento del pedido considerando el
	 * producto, la cantidad y el descuento que se le aplica.<br>
	 * 
	 * @param pedido        : pedido al cual se le aplicara la promocion.
	 * @param promoProducto : promocion del producto que se le aplicara al pedido.
	 * 
	 */

	private double getPrecioConDescuento(Pedido pedido, PromoProducto promoProducto) {
		double precio = pedido.getProducto().getpVenta() * pedido.getCantidad();
		if (promoProducto.isAplicaDosPorUno())
			precio -= pedido.getProducto().getpVenta() * Math.floorDiv((int) pedido.getCantidad(), 2);
		else if (pedido.getCantidad() >= promoProducto.getDtoPorCantidad_CantMinima()) // si no cumple con la cant min,
																						// no aplico desc
			precio = promoProducto.getDtoPorCantidad_PrecioUnit() * pedido.getCantidad();

		return precio;
	}

	/* Si la mesa tiene una comanda, devuelve el index de la comanda, sino -1 */

	public int getComanda(Mesa mesa) {
		int i = 0;
		boolean enc = false;
		while (i < this.comandasAbiertas.size() && enc == false) {
			if (mesa.equals(this.comandasAbiertas.get(i).mesa))
				enc = true;
			else
				i++;
		}
		return (enc == false) ? -1 : i;
	}

	/**
	 * Toma una nueva comanda en caso de que la mesa este libre. Si la mesa pasada
	 * por parametro esta ocupada, se agregan los pedidos al ArrayList de pedidos de
	 * la comanda abierta de esa mesa.<br>
	 * <b>Pre:</b> mozo != null. mesa != null<br>
	 * <b>Post:</b> La mesa se agrega al ArrayList de mesas del mozo. El estado de
	 * la mesa pasa a "OCUPADA"<br>
	 *
	 * @param mozo : El mozo al cual se quiere agregar una mesa.
	 * @param mesa : Numero de mesa a asignarle.
	 */

	public void tomarComanda(Mesa mesa, ArrayList<Pedido> pedidos)
			throws MesaInexistenteException, MesaNoDisponibleException,ProductosInvalidosException {

		int i = 0;
		ArrayList<Pedido> pedidosValidos = new ArrayList<Pedido>();

		while (i < mesas.size() && !mesa.equals(mesas.get(i)))
			i++;

		if (i < mesas.size() && mesa.equals(mesas.get(i))) { // la mesa existe
			
			if (!mesa.asignado)
				throw new MesaNoDisponibleException("No se puede tomar comanda: la mesa no tiene mozo asignado");
			// verifico que al menos 1 producto de los pedidos del ArrayList exista (en el
			// ArrayList de Productos)
			for (Pedido pedido : pedidos) {
				if (existeProducto(pedido.getProducto())) {
					pedidosValidos.add(pedido);
				}
			}
			if (pedidosValidos.size() <= 0) // No se pidio ningun producto valido
				throw new ProductosInvalidosException("No se pidio ningun producto valido");

			int pos = getComanda(mesa);

			if (pos != -1) // la mesa ya tiene una comanda abierta
				agregarPedido(this.comandasAbiertas.get(pos), pedidosValidos);
			else{ // la mesa esta libre

				addComanda(new Comanda(mesa, pedidosValidos));
				mesa.setEstado("OCUPADA");
			}
		} else
			throw new MesaInexistenteException("la mesa elegida no existe");
		
		this.invariante();

	}

	public boolean existeProducto(Producto producto) {

		for (Producto producto_i : productos) { // recorro el ArrayList de productos de la cervecer�a
			if (producto_i.equals(producto))
				return true; // el producto existe
		}
		return false;
	}

	private void agregarPedido(Comanda comanda, ArrayList<Pedido> pedidos) {
		comanda.actualizaListaProductos(pedidos);
	}

	/**
	 * Agrega el operario pasado por parametro al ArrayList de operarios.<br>
	 * <b>Pre:</b> op != null. ArrayList de operarios inicializado. <br>
	 * <b>Post:</b> El operario pasado por par�metro se agreg� al ArrayList de
	 * operarios.<br>
	 *
	 * @param operario : Operario que se busca agregar a la cervecer�a.
	 * @throws UsuarioRepetidoException : Se lanza si ya hay un operario registrado
	 *                                  con el mismo username.
	 */

	public void addOperario(Operario op) throws UsuarioRepetidoException {

		assert op != null : "El operario debe ser distinto de null";

		for (Operario opAct : this.operarios) {
			if (opAct.getUsername().equals(op.getUsername()))
				throw new UsuarioRepetidoException(
						"No se pudo registrar al operario " + op.getUsername() + ". Nombre de usuario repetido.");
		}
		this.operarios.add(op);
		this.invariante();
	}


	// Metodos necesarios para el LOGIN

	public Operario getOperario(String username) {
		int i = 0;
		Operario op = null;

		while (i < this.operarios.size() && !this.operarios.get(i).getUsername().equalsIgnoreCase(username))
			i++;

		if (i < this.operarios.size())
			op = this.operarios.get(i);

		return op;
	}

	/**
	 * Agrega el mozo pasado por parametro al ArrayList de mozos.<br>
	 * <b>Pre:</b> mozo != null. <br>
	 * <b>Post:</b> El mozo pasado por parametro se agrega al ArrayList de
	 * mozos.<br>
	 *
	 * @param mozo : mozo que se desea agregar a la cerveceria.
	 * @throws MozoRepetidoException : Se lanza si ya hay un mozo registrado con el
	 *                               mismo nombre.
	 */

	public void addMozo(Mozo mozo) throws MozoRepetidoException {

		assert mozo != null : "El mozo debe ser distinto de null";

		for (Mozo mozoAct : this.mozos) {
			if (mozoAct.getNya().equals(mozo.getNya()))
				throw new MozoRepetidoException(
						"No se pudo registrar al mozo " + mozo.getNya() + ". Nombre y apellido del mozo repetido.");
		}

		this.mozos.add(mozo);
		this.invariante();
	}

	/**
	 * Agrega el producto pasado por parametro al ArrayList de productos.<br>
	 * <b>Pre:</b> producto != null. <br>
	 * <b>Post:</b> El producto pasado por parametro se agrega al ArrayList de
	 * productos.<br>
	 *
	 * @param producto : producto que se desea agregar a la cerveceria.
	 * @throws ProductoRepetidoException : Se lanza si ya hay un producto registrado
	 *                                   con el mismo nombre.
	 */
	public void addProducto(Producto producto) throws ProductoRepetidoException {

		assert producto != null : "El producto debe ser distinto de null";

		for (Producto prodAct : this.productos) {
			if (prodAct.getNombre().equalsIgnoreCase(producto.getNombre()))
				throw new ProductoRepetidoException("No se pudo registrar el producto " + producto.getNombre()
						+ ". Nombre y apellido del mozo repetido.");
		}

		this.productos.add(producto);
		this.invariante();
	}

	/**
	 * Agrega la mesa pasada por parametro al ArrayList de mesas.<br>
	 * <b>Pre:</b> mesa != null. <br>
	 * <b>Post:</b> La mesa pasada por parametro se agrega al ArrayList de
	 * mesas.<br>
	 *
	 * @param mesa : mesa que se desea agregar a la cerveceria.
	 * @throws MesaRepetidaException : Se lanza si ya hay una mesa registrada con el
	 *                               mismo nroMesa.
	 */

	public void addMesa(Mesa mesa) throws MesaRepetidaException {

		assert mesa != null : "La mesa debe ser distinto de null";

		for (Mesa mesaAct : this.mesas) {
			if (mesaAct.getNroMesa() == mesa.getNroMesa())
				throw new MesaRepetidaException("No se pudo registrar la mesa " + mesa.getNroMesa()
						+ ". Ya existe una mesa con el mismo numero.");
		}

		this.mesas.add(mesa);
		this.invariante();
	}


	/**
	 * Agrega una promo producto a la lista de promociones de producto de la
	 * cerveceria <br>
	 * <b>Pre:</b> promo != null<br>
	 * <b>Post:</b> La promo pasada por parametro se agrega al ArrayList de
	 * promos de producto.<br>
	 * @param promo: promocion de Producto que se desea agregar al ArrayList de
	 *               promociones de producto de la cerveceria.
	 * @throws PromoRepetidaException : Se lanza si ya existe una promocion para ese producto con los mismos valores.
	 *@throws ProductoInexistenteException : Se lanza si la quiere agregar una promoProducto de un producto que no existe en la cerveceria.
	 */
	public void addPromoProd(PromoProducto promo) throws PromoRepetidaException, ProductoInexistenteException {

		assert promo != null : "la promo debe ser distinto de null";

		int j=0;
		
		 while (j < productos.size() && !productos.get(j).getNombre().equalsIgnoreCase(promo.getProducto().getNombre()))
	            j++;
		 
		 if (j >= productos.size())
			 throw new ProductoInexistenteException(
						"El producto " + promo.getProducto().getNombre() + " no existe en la cerveceria");	

		else { // verifico si ya existe la misma promo
			
			int i=0;
			 while (i < promosProducto.size() && !promosProducto.get(i).equals(promo))
		            i++;
			 
			 if (i < promosProducto.size() && promosProducto.get(i).equals(promo)) { //si ya existe la misma promo
					throw new PromoRepetidaException("La promo de " + promo.getProducto().getNombre()
							+ " ya existe en el sistema con los mismos valores");
			 }
		}

		this.promosProducto.add(promo);
		this.invariante();
	}
	
	/**
	 * Agrega una promo temporal a la lista de promociones temporales de la
	 * cerveceria <br>
	 * <b>Pre:</b> promo != null<br>
	 * <b>Post:</b> La promocion pasada por parametro se agrega al ArrayList de promos temporales
	 * .<br>
	 * @param promo: promocion temporal que se desea agregar al ArrayList de promociones temporales de la cerveceria.
	 * @throws PromoRepetidaException : Se lanza si ya existe una promoTemporal con el mismo nombre.

	 */
	
	public void addPromoTemp(PromoTemporal promo) throws PromoRepetidaException {
		
		PromoTemporal promoAct;
		boolean enc=false;
		Iterator<PromoTemporal> it= this.promosTemporales.iterator();
		
		while(it.hasNext() && !enc) {
			promoAct=it.next();
			if(promoAct.getNombre().equalsIgnoreCase(promo.getNombre()))
				enc=true;
		}
		
		
		if (enc) 
			throw new PromoRepetidaException("Ya existe una promo temporal con el mismo nombre ("+promo.getNombre()+")");
		else
			this.promosTemporales.add(promo);
		
		this.invariante();
	}
	
	public void addComanda(Comanda comanda) {
		this.comandasAbiertas.add(comanda);
		this.invariante();
	}

	public void addVenta(Venta venta) {
		this.ventas.add(venta);
		this.invariante();
	}
	
	
	public void deleteOperario(Operario op) throws OperarioInexistenteException {
		
        assert op != null : "El operario no puede ser null";

        if (this.operarios.contains(op))
            operarios.remove(op);
        else
            throw new OperarioInexistenteException("El operario que se quiere eliminar no existe");
   
		this.operarios.remove(op);
		this.invariante();
	}

	public void deleteMozo(Mozo mozo)throws MozoInexistenteException {
		
        assert mozo != null : "El mozo no puede ser null";

        if (mozos.contains(mozo))
            mozos.remove(mozo);
        else
            throw new MozoInexistenteException("El mozo que se quiere eliminar no existe");
        
        this.invariante();
    }
	

	public void deleteProducto(Producto prod) throws ProductoEnComandaException,ProductoInexistenteException {
		
		if (this.productos.contains(prod)) {
			if (prod.EnComanda())
				throw new ProductoEnComandaException("El producto no se puede eliminar ya que es parte de una comanda");
			else
				this.productos.remove(prod);
		}
		else
			throw new ProductoInexistenteException("El producto que se quiere eliminar no existe");
			
		this.invariante();
	}

	public void deleteMesa(Mesa mesa) throws ComandaAbiertaException, MesaInexistenteException {

		if (this.mesas.contains(mesa)) {
			if (mesa.estado.equalsIgnoreCase("OCUPADA"))
				throw new ComandaAbiertaException(
						"La mesa " + mesa.getNroMesa() + " no se puede eliminar ya que tiene una comanda abierta");
			else
				this.mesas.remove(mesa);
		}
		else
			throw new MesaInexistenteException("La mesa que se quiere eliminar no existe");
		
		this.invariante();
	}

	
	public void deleteComanda(Comanda comanda) {
		this.comandasAbiertas.remove(comanda);
		this.invariante();
	}

	public void deletePromoTemporal(PromoTemporal promo) throws PromoInexistenteException {
		
		assert promo != null : "La promo no puede ser null";
		
		if (promosTemporales.contains(promo))
			promosTemporales.remove(promo);
	    else
	    	throw new PromoInexistenteException("La promo que se quiere eliminar no existe");

	    this.invariante();
	}

	
	public void deletePromoProducto(PromoProducto promo) throws PromoInexistenteException {
		
		assert promo != null : "La promo no puede ser null";
		
		if (promosProducto.contains(promo))
			this.promosProducto.remove(promo);
	    else
	    	throw new PromoInexistenteException("La promo que se quiere eliminar no existe");

	    this.invariante();
	}

	// getters

	public Admin getAdmin() {
		return admin;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public double getSueldo() {
		return sueldo;
	}

	public ArrayList<Mozo> getMozos() {
		return mozos;
	}

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public ArrayList<Operario> getOperarios() {
		return operarios;
	}

	public ArrayList<Comanda> getComandasAbiertas() {
		return comandasAbiertas;
	}

	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	public ArrayList<PromoTemporal> getPromoTemporales() {
		return promosTemporales;
	}

	public ArrayList<PromoProducto> getPromosProducto() {
		return promosProducto;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
		this.invariante();
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
		this.invariante();
	}

	public void setMozos(ArrayList<Mozo> mozos) {
		this.mozos = mozos;
		this.invariante();
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
		this.invariante();
	}

	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
		this.invariante();
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
		this.invariante();
	}

	public void setOperarios(ArrayList<Operario> operarios) {
		this.operarios = operarios;
		this.invariante();
	}

	public void setComandasAbiertas(ArrayList<Comanda> comandasAbiertas) {
		this.comandasAbiertas = comandasAbiertas;
		this.invariante();
	}

	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
		this.invariante();
	}

	public void setPromoTemporales(ArrayList<PromoTemporal> promoTemporales) {
		this.promosTemporales = promoTemporales;
		this.invariante();
	}

	public void setPromosProducto(ArrayList<PromoProducto> promosProducto) {
		this.promosProducto = promosProducto;
		this.invariante();
	}

	 public String mozoMayorVentas() {
	        double maxVenta = -1;
	        String mozoMaxVenta = null;
	        for (Mozo mozo : mozos) {
	            if (mozo.getTotalVentas() > maxVenta) {
	                maxVenta = mozo.getTotalVentas();
	                mozoMaxVenta = mozo.getNya();
	            }
	        }
	        return mozoMaxVenta;
	    }

	    public String mozoMenorVentas() {
	        double minVenta = 999999999;
	        String mozoMinVenta = null;
	        for (Mozo mozo : mozos) {
	            if (mozo.getTotalVentas() < minVenta) {
	                minVenta = mozo.getTotalVentas();
	                mozoMinVenta = mozo.getNya();
	            }
	        }
	        return mozoMinVenta;
	    }
	    
	public String getInformeMozos() {
		return "Mozo con mayores ventas: " + mozoMayorVentas()+ ".  Mozo con menores ventas: "
				+ mozoMenorVentas();
	}
	    
	public void persistir() {
		try {
			IPersistencia<Serializable> persistencia = new PersistenciaBIN();
			persistencia.abrirOutput("Cerveceria.bin");
			// System.out.println("Crea archivo escritura");
			CerveceriaDTO cDTO = UtilPersistencia.CerveceriaToCerveceriaDTO(this);
			persistencia.escribir(cDTO);
			// System.out.println("Cerveceria grabada exitosamente");
			persistencia.cerrarOutput();
			// System.out.println("Archivo cerrado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void cerrarJornada() throws ComandaAbiertaException {
		if (!this.comandasAbiertas.isEmpty())
			throw new ComandaAbiertaException("No se puede cerrar la jornada si hay mesas sin cerrar");
		else {
			for (Mesa mesa : this.mesas)
				mesa.setAsignado(false);
			for (Mozo mozo : this.mozos)
				mozo.getMesas().clear();
			// cambia el estado?

		}
	}
	
	//libera (desasigna los mozos) de aquellas mesas que no estan ocupadas por clientes.
	public void liberarMesasDesocupadas() {
        for (Mesa mesaAct : mesas) {
            if (mesaAct.isAsignado() && mesaAct.getEstado().equals("LIBRE")) {
            	mesaAct.setAsignado(false);
            }
        }
	}

	private void invariante() {
		assert this.promosProducto != null : "La lista de promociones de producto no puede ser null";
		assert this.promosTemporales != null : "La lista de promociones temporales no puede ser null";
		assert this.productos != null : "La lista de productos no puede ser null";
		assert this.comandasAbiertas != null : "La lista de comandas abiertas no puede ser null";
		assert this.mesas != null : "La lista de mesas no puede ser null";
	}
	
	public String getEstadisticas(Mozo mozo) {
		
		double prom;
		if (mozo.getCantVentas()==0)
			prom=0;
		else
			prom=mozo.getTotalVentas() / mozo.getCantVentas();
		
		return "Promedio de  ventas: $" + prom + " ,  Total de ventas: $"
				+ mozo.getTotalVentas() + " , Cantidad de ventas: " + mozo.getCantVentas();

	}

	public double getSueldo(Mozo mozo) { // arreglar
		return sueldo + mozo.cantHijos;
	}
	
	public void despersistir() throws IOException, ClassNotFoundException {
        IPersistencia<Serializable> persistencia = new PersistenciaBIN();
        Cerveceria cerveceria = Cerveceria.getInstance();
            persistencia.abrirInput("Cerveceria.bin");
            CerveceriaDTO cdto = (CerveceriaDTO) persistencia.leer();
            UtilPersistencia.CerveceriaDTOToCerveceria(cdto, cerveceria);
            persistencia.cerrarInput();
    }	
}
