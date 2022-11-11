package modelo;

import excepciones.*;
import persistencia.CerveceriaDTO;
import persistencia.IPersistencia;
import persistencia.PersistenciaBin;
import persistencia.UtilCerveceria;

import java.io.IOException;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Observable;

public class Cerveceria extends Observable {

    private String nombreLocal;
    private ArrayList<Mozo> mozos = new ArrayList<Mozo>();
    private ArrayList<Mesa> mesas;
    private ArrayList<Producto> carta = new ArrayList<Producto>();
    private ArrayList<Operario> operarios = new ArrayList<Operario>();
    private ArrayList<Comanda> comandasAbiertas = new ArrayList<Comanda>();
    private ArrayList<Venta> ventas = new ArrayList<Venta>();
    private Admin administrador = new Admin();
    private ArrayList<PromoProducto> promosProductos = new ArrayList<PromoProducto>();
    private ArrayList<PromoTemporal> promosTemporales = new ArrayList<PromoTemporal>();
    Operario operarioLogueado = null;

    private static Cerveceria instance = null;
    private Cerveceria() {
        mesas = new ArrayList<Mesa>();
        this.mesas.add(new Mesa(1));    // Por defecto, la barra empieza con 1 comensal
    }
    public static Cerveceria getInstance(){
        if(instance == null){
            instance = new Cerveceria();
        }
        return instance;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public ArrayList<Mozo> getMozos() {
        return mozos;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public ArrayList<Operario> getOperarios() {
        return operarios;
    }

    /**
     * Asigna como estado actual del mozo pasado por parámetro al estado pasado por parámetro.<br>
     * <b>Pre:</b> El parametro mozo debe ser distinto de null, y el parametro estado debe ser ACTIVO, FRANCO o AUSENTE.<br>
     * <b>Post:</b> El mozo pasado por parametro debera tener el estado pasado por parametro.<br>
     *
     * @param mozo : El mozo al cual se quiere cambiar su estado.
     * @param estado : El estado que se quiere asignar al mozo.
     */
    public void setEstado(Mozo mozo, EstadoMozo estado) throws MozoInexistenteException, EstadoInvalidoException {
       if (mozos.contains(mozo)) {
           if (estado != EstadoMozo.ACTIVO && !mozo.getMesas().isEmpty())
               throw new EstadoInvalidoException(mozo.getNya(), estado);
           else
               mozos.get(mozos.indexOf(mozo)).setEstado(estado);
       } else
           throw new MozoInexistenteException(mozo.getNya());

        this.invariante();
    }

    /**
     * Asigna una mesa pasada por parametro al mozo pasado por parámetro .<br>
     * <b>Pre:</b> Los parametros nya y mesa deben ser distintos de null.<br>
     * <b>Post:</b> Se agregara una mesa a la lista de mesas del mozo y se marcara la mesa como asignada.<br>
     *
     * @param mozo : El mozo al cual se quiere agregar una mesa.
     * @param mesa : Mesa a asignarle.
     */
    public void asignarMesa(Mozo mozo, Mesa mesa) throws MozoNoDisponibleException, MozoInexistenteException, MesaInexistenteException, MesaNoDisponibleException {

        if (!mozos.contains(mozo))
            throw new MozoInexistenteException(mozo.getNya());

        if (!mesas.contains(mesa))
            throw new MesaInexistenteException(mesa.getNroMesa());

        if (mozo.getEstado() != EstadoMozo.ACTIVO)
            throw new MozoNoDisponibleException(mozo.getNya());

        if (mesa.isAsignada())
            throw new MesaNoDisponibleException("La mesa " + mesa.getNroMesa() + " ya fue asignada a otro mozo.");

        mozos.get(mozos.indexOf(mozo)).getMesas().add(mesa);
        mesa.setAsignada(true);

        this.invariante();
    }

    /**
     * Cierra la mesa porque los comensales pidieron la cuenta, dejando la mesa libre.<br>
     * <b>Pre: </b> El numero de mesa debe ser mayor o igual que 0.<br>
     * <b>Post: </b> Se cierra la mesa y se deja libre.<br>
     *
     * @param nroMesa : Numero de mesa a cerrar.
     * @param formaPago : Forma de pago de la cuenta.
     * @throws MesaInexistenteException : Se lanza si la mesa no existe.
     * @throws MesaNoDisponibleException : Se lanza si la mesa no esta disponible.
     */
    public void cerrarMesa(int nroMesa, FormaPago formaPago) throws MesaInexistenteException, MesaNoDisponibleException {

        int i = 0;
        while (i < mesas.size() && mesas.get(i).getNroMesa() != nroMesa)
            i++;

        if (i >= mesas.size() || mesas.get(i).getNroMesa() != nroMesa)
            throw new MesaInexistenteException(nroMesa);
        Mesa mesa = mesas.get(i);

        if (mesa.isLibre())
            throw new MesaNoDisponibleException("La mesa " + nroMesa + " no puede cerrarse porque ya esta libre.");

        i = 0;
        while (i < comandasAbiertas.size() && comandasAbiertas.get(i).getMesa() != mesa)
            i++;
        Comanda comanda = comandasAbiertas.get(i);
        comandasAbiertas.remove(i);

        double total = 0;
        ArrayList <Promocion> listaPromos = new ArrayList<Promocion>();
        ArrayList<PromoTemporal> listaPromosTemporales = new ArrayList<PromoTemporal>();
        ArrayList<PromoProducto> listaPromosProductos = new ArrayList<PromoProducto>();

        for (Pedido pedido : comanda.getPedidos()) {
            total += pedido.getProducto().getpVenta() * pedido.getCantidad();
            i = 0;
            while (i < promosProductos.size() && !promosProductos.get(i).getProducto().equals(pedido.getProducto()))
                i++;
            if (i < promosProductos.size() && promosProductos.get(i).getProducto().equals(pedido.getProducto())) {
                PromoProducto promo = promosProductos.get(i);
                if (!listaPromos.contains(promo) && promo.getDiasDePromo().contains(comanda.getFecha().getDayOfWeek())) {
                    listaPromosProductos.add(promo);
                    listaPromos.add(promo);
                }
            }
        }

        for (PromoTemporal promocion : promosTemporales) {
            if ((promocion.isEsAcumulable() || !promocion.isEsAcumulable() && listaPromos.isEmpty()) && promocion.getDiasDePromo().contains(comanda.getFecha().getDayOfWeek()) && promocion.getHoraInicio() <= comanda.getFecha().getHour() && promocion.getHoraFin() > comanda.getFecha().getHour() && promocion.getFormaPago() == formaPago) {
                listaPromosTemporales.add(promocion);
                listaPromos.add(promocion);
            }
        }

        int cantProducto;
        for (PromoProducto promo : listaPromosProductos) {
            cantProducto = 0;
            for (Pedido pedido : comanda.getPedidos()) {
                if (pedido.getProducto().equals(promo.getProducto()))
                    cantProducto += pedido.getCantidad();
            }
            if (promo.isAplicaDosPorUno()) {
                if (cantProducto % 2 == 0)
                    total -= promo.getProducto().getpVenta() * (cantProducto / 2);
                else
                    total -= promo.getProducto().getpVenta() * ((cantProducto - 1) / 2);
            } else if (cantProducto >= promo.getDtoPorCantidad_CantMinima())
                total -= cantProducto * (promo.getProducto().getpVenta() - promo.getDtoPorCantidad_PrecioUnit());
        }

        for (PromoTemporal promo : listaPromosTemporales) {
            total *= 1 - promo.getPorcentajeDesc();
        }

        Venta venta = new Venta(comanda, formaPago, total, listaPromos);
        ventas.add(venta);
        i = 0;
        while (i < mozos.size() && !mozos.get(i).getMesas().contains(mesa))
            i++;
        mozos.get(i).getVentas().add(venta);
        mesa.setLibre(true);

        this.invariante();
    }

    /**
     * Agrega un operario a la lista de operarios.<br>
     * <b>Pre:</b> El parametro operario debe ser distinto de null.<br>
     * <b>Post:</b> Se agregara un operario a la lista de operarios.<br>
     *
     * @param operario : El operario a agregar.
     * @throws OperarioRepetidoException : Se lanza si el operario ya existe.
     */
    public void agregarOperario(Operario operario) throws OperarioRepetidoException {
        assert operario != null : "El operario no puede ser null";
        int i = 0;
        while (i < operarios.size() && !operarios.get(i).getUsername().equalsIgnoreCase(operario.getUsername()))
            i++;
        if (i < operarios.size() && operarios.get(i).getUsername().equalsIgnoreCase(operario.getUsername()))
            throw new OperarioRepetidoException(operario.getUsername());
        else
            operarios.add(operario);
        this.invariante();
    }

    /**
     * Elimina un operario de la lista de operarios de la cerveceria.<br>
     * <b>Pre:</b> El parametro operario debe ser distinto de null.<br>
     * <b>Post:</b> El operario debe ser eliminado de la lista de operarios de la cerveceria.<br>
     *
     * @param operario : Es el operario a eliminar del sistema.
     * @throws OperarioInexistenteException : Se lanza si el operario no existe en la lista de operarios de la cerveceria.
     */
    public void eliminarOperario(Operario operario) throws OperarioInexistenteException{

        assert operario != null : "El operario no puede ser null";

        if (operarios.contains(operario))
            operarios.remove(operario);
        else
            throw new OperarioInexistenteException(operario.getUsername());

        this.invariante();
    }

    /**
     * Agrega un producto a la lista de productos.<br>
     * <b>Pre:</b> El parametro producto debe ser distinto de null.<br>
     * <b>Post:</b> Se agregara un producto a la lista de productos.<br>
     * @param producto : El producto a agregar.
     * @throws ProductoRepetidoException : Se lanza si el producto ya existe.
     */
    public void agregarProducto(Producto producto) throws ProductoRepetidoException {
        assert producto != null : "El producto no puede ser null";
        int i = 0;
        while (i < carta.size() && !carta.get(i).getNombre().equalsIgnoreCase(producto.getNombre()))
            i++;
        if (i < carta.size() && carta.get(i).getNombre().equalsIgnoreCase(producto.getNombre()))
            throw new ProductoRepetidoException(producto.getNombre());
        else
            carta.add(producto);
        this.invariante();
    }

    /**
     * Eliminar un producto de la lista de productos.<br>
     * <b>Pre:</b> El parametro producto debe ser distinto de null.<br>
     * <b>Post:</b> Se eliminara un producto de la lista de productos.<br>
     * @param producto : El producto a eliminar.
     * @throws ProductoInexistenteException : Se lanza si el producto no existe.
     */
    public void eliminarProducto(Producto producto) throws ProductoInexistenteException{
        assert producto != null : "El producto no puede ser null";

        if (carta.contains(producto))
            carta.remove(producto);
        else
            throw new ProductoInexistenteException(producto.getNombre());

        this.invariante();
    }

    /**
     * Actualiza los atributos de un producto de la lista de productos. En caso de que un parametro sea null o -1, no lo modifica.<br>
     * <b>Pre:</b> El parametro producto debe ser distinto de null. Los parametros pCosto, pVenta y stock deben ser positivos o -1.<br>
     * <b>Post:</b> Se actualizara un producto de la lista de productos.<br>
     *
     * @param producto : El producto a modificar.
     * @param pCosto : El nuevo precio de costo.
     * @param pVenta : El nuevo precio de venta.
     * @param stock : El nuevo stock.
     */
    public void modificarProducto(Producto producto, double pCosto, double pVenta, int stock) throws ProductoInexistenteException {
        assert producto != null : "El producto no puede ser null";
        assert pCosto > 0 || pCosto == -1 : "El precio de costo debe ser positivo o -1";
        assert pVenta > 0 || pVenta == -1 : "El precio de venta debe ser positivo o -1";
        assert stock > 0 || stock == -1 : "El stock debe ser positivo o -1";
        if (carta.contains(producto)) {
            int i = carta.indexOf(producto);
            if (pCosto > 0)
                carta.get(i).setpCosto(pCosto);
            if (pVenta > 0)
                carta.get(i).setpVenta(pVenta);
            if (stock > 0)
                carta.get(i).setStock(stock);
        } else
            throw new ProductoInexistenteException(producto.getNombre());
        this.invariante();
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
     * @param diasDePromo: Los dias de la semana en los que aplica la promocion.
     * @throws ProductoInexistenteException: Se lanza si el producto no existe.
     * @throws PromocionRepetidaException: Se lanza si la promocion ya existe.
     */
    public void agregarPromocion(String nombreProducto, boolean aplicaDosPorUno, boolean aplicaDtoPorCantidad, int dtoPorCantidad_CantMinima, double dtoPorCantidad_PrecioUnit, ArrayList<DayOfWeek> diasDePromo) throws ProductoInexistenteException, PromocionRepetidaException {
        assert nombreProducto != null : "El producto no puede ser null";
        assert aplicaDosPorUno || aplicaDtoPorCantidad : "Debe aplicar al menos un tipo de promoción";
        assert dtoPorCantidad_CantMinima >= 0 : "La cantidad mínima debe ser mayor o igual a 0";
        assert dtoPorCantidad_PrecioUnit >= 0 : "El precio unitario debe ser mayor o igual a 0";
        if (aplicaDtoPorCantidad) {
            assert dtoPorCantidad_CantMinima > 0 : "La cantidad mínima debe ser mayor a 0";
            assert dtoPorCantidad_PrecioUnit > 0 : "El precio unitario debe ser mayor a 0";
        }

        int i = 0;
        while (i < carta.size() && !carta.get(i).getNombre().equalsIgnoreCase(nombreProducto))
            i++;

        if (i < carta.size() && carta.get(i).getNombre().equalsIgnoreCase(nombreProducto)) {
            PromoProducto promocion = new PromoProducto(carta.get(i), aplicaDosPorUno, aplicaDtoPorCantidad, dtoPorCantidad_CantMinima, dtoPorCantidad_PrecioUnit, diasDePromo);
            if (promosProductos.contains(promocion))
                throw new PromocionRepetidaException("del producto " + nombreProducto);
            else
                promosProductos.add(promocion);
        } else
            throw new ProductoInexistenteException(nombreProducto);

        this.invariante();
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
     * @param diasDePromo : Los dias de la semana en los que se aplica la promocion.
     * @throws PromocionRepetidaException: Se lanza si la promocion ya existe.
     */
    public void agregarPromocion(String nombre, FormaPago formaPago, double porcentajeDesc, boolean esAcumulable, int horaInicio, int horaFin, ArrayList<DayOfWeek> diasDePromo) throws PromocionRepetidaException {
        assert nombre != null : "El nombre no puede ser null";
        assert porcentajeDesc > 0 : "El porcentaje de descuento debe ser mayor a 0";
        assert horaInicio >= 0 && horaInicio <= 23 : "La hora de inicio debe ser un numero entre 0 y 23";
        assert horaFin >= 0 && horaFin <= 23 : "La hora de fin debe ser un numero entre 0 y 23";

        int i = 0;
        while (i < promosTemporales.size() && !promosTemporales.get(i).getNombre().equalsIgnoreCase(nombre))
            i++;
        if (i < promosTemporales.size() && promosTemporales.get(i).getNombre().equalsIgnoreCase(nombre))
            throw new PromocionRepetidaException("temporal " + nombre);
        else
            promosTemporales.add(new PromoTemporal(nombre, formaPago, porcentajeDesc, esAcumulable, horaInicio, horaFin, diasDePromo));

        this.invariante();
    }

    /**
     * Elimina una promocion de la lista de promociones de producto.<br>
     * <b>Pre:</b> El parametro promo debe ser distinto de null.<br>
     * <b>Post:</b> Se eliminara una promocion de la lista de promociones de producto.<br>
     *
     * @param promo: La promocion a eliminar.
     * @throws PromocionInexistenteException: Se lanza si la promocion no existe.
     */
    public void eliminarPromocion(PromoProducto promo) throws PromocionInexistenteException {
        assert promo != null : "promo debe ser distinto de null";

        if (promosProductos.contains(promo))
            promosProductos.remove(promo);
        else
            throw new PromocionInexistenteException(String.valueOf(promo.getId_promo()));

        this.invariante();
    }

    /**
     * Elimina una promocion de la lista de promociones temporales.<br>
     * <b>Pre:</b> El parametro promo debe ser distinto de null.<br>
     * <b>Post:</b> Se eliminara una promocion de la lista de promociones temporales.<br>
     *
     * @param promo: La promocion a eliminar.
     * @throws PromocionInexistenteException: Se lanza si la promocion no existe.
     */
    public void eliminarPromocion(PromoTemporal promo) throws PromocionInexistenteException {
        assert promo != null : "La promocion no puede ser null";

        if (promosTemporales.contains(promo))
            promosTemporales.remove(promo);
        else
            throw new PromocionInexistenteException(promo.getNombre());

        this.invariante();
    }

    private void invariante() {
        assert this.promosProductos != null : "La lista de promociones de producto no puede ser null";
        assert this.promosTemporales != null : "La lista de promociones temporales no puede ser null";
        assert this.carta != null : "La lista de productos no puede ser null";
        assert this.comandasAbiertas != null : "La lista de comandas abiertas no puede ser null";
        assert this.mesas != null : "La lista de mesas no puede ser null";
    }

    /**
     * Crea un pedido con nombreProducto y cantidad y lo agrega a la comanda de la mesa indicada si ya está abierta.
     * Sino, abre la mesa, crea una nueva comanda y agrega este pedido.<br>
     * <b>Pre:</b> El parametro nombreProducto debe ser distinto de null. La cantidad debe ser positiva.<br>
     * <b>Post:</b> Se agregara un pedido a la comanda de la mesa.<br>
     * @param nroMesa: El numero de la mesa a la que se le agregara el pedido.
     * @param nombreProducto: El nombre del producto a agregar.
     * @param cantidad: La cantidad del producto a agregar.
     * @throws MesaInexistenteException : Se lanza si la mesa no existe.
     * @throws ProductoInexistenteException : Se lanza si el producto no existe.
     */
    public void tomarComanda(int nroMesa, String nombreProducto, int cantidad) throws MesaInexistenteException, ProductoInexistenteException {
        assert nroMesa >= 0 : "El numero de mesa debe ser positivo";
        assert nombreProducto != null : "El nombre del producto no puede ser null";
        assert cantidad > 0 : "La cantidad debe ser positiva";

        int i = 0;
        while (i < mesas.size() && mesas.get(i).getNroMesa() != nroMesa)
            i++;
        if (i >= mesas.size() || mesas.get(i).getNroMesa() != nroMesa)     // Valida existencia de mesa
            throw new MesaInexistenteException(nroMesa);

        int j = 0;
        while (j < carta.size() && !carta.get(j).getNombre().equalsIgnoreCase(nombreProducto))
            j++;
        if (j >= carta.size() || !carta.get(j).getNombre().equalsIgnoreCase(nombreProducto))    // Valida existencia de producto
            throw new ProductoInexistenteException(nombreProducto);
        Pedido pedido = new Pedido(carta.get(j), cantidad);

        if (!mesas.get(i).isLibre()) {  //Si la comanda ya está abierta (mesa ocupada), agrega el pedido a la comanda
            int k = 0;
            while (k < comandasAbiertas.size() && comandasAbiertas.get(k).getMesa().getNroMesa() != nroMesa)
                k++;
            if (k < comandasAbiertas.size() && comandasAbiertas.get(k).getMesa().getNroMesa() == nroMesa)
                comandasAbiertas.get(k).agregarPedido(pedido);
        } else {    //Si la comanda no está abierta (mesa libre), crea una nueva comanda y agrega el pedido
            mesas.get(i).setLibre(false);
            Comanda comanda = new Comanda(mesas.get(i));
            comanda.agregarPedido(pedido);
            this.comandasAbiertas.add(comanda);
        }

        this.invariante();
    }

    public Admin getAdmin() {
        return administrador;
    }

    public void eliminarMozo(Mozo mozo) throws MozoInexistenteException {
        assert mozo != null : "El mozo no puede ser null";

        if (mozos.contains(mozo))
            mozos.remove(mozo);
        else
            throw new MozoInexistenteException(mozo.getNya());
    }

    public void agregarMozo(String nya, int cantHijos, String fechaNacimiento) throws MozoRepetidoException {
        int i = 0;
        while (i < mozos.size() && !mozos.get(i).getNya().equalsIgnoreCase(nya))
            i++;
        if (i < mozos.size() && mozos.get(i).getNya().equalsIgnoreCase(nya))
            throw new MozoRepetidoException(nya);
        else
            mozos.add(new Mozo(nya, cantHijos, fechaNacimiento));
    }

    public void modificarMozo(Mozo mozo, int cantHijos) throws MozoInexistenteException {
        if (mozos.contains(mozo))
            mozos.get(mozos.indexOf(mozo)).setCantHijos(cantHijos);
        else
            throw new MozoInexistenteException(mozo.getNya());
    }

    public void agregarMesa(int cantComensales) {
        mesas.add(new Mesa(cantComensales));
    }

    public void eliminarMesa(Mesa mesa) throws MesaInexistenteException, MesaAsignadaException {
        if (mesas.contains(mesa))
            if (mesa.isAsignada())
                throw new MesaAsignadaException(mesa.getNroMesa());
            else
                mesas.remove(mesa);
        else
            throw new MesaInexistenteException(mesa.getNroMesa());
    }

    public void modificarMesa(int nroMesa, int cantComensales) throws MesaInexistenteException {
        int i = 0;
        while (i < mesas.size() && mesas.get(i).getNroMesa() != nroMesa)
            i++;
        if (i < mesas.size() && mesas.get(i).getNroMesa() == nroMesa)
            mesas.get(i).setCantComensales(cantComensales);
        else
            throw new MesaInexistenteException(nroMesa);
    }

    public ArrayList<Comanda> getComandasAbiertas() {
        return comandasAbiertas;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }
    
    
    public void loguear(String username, String password, String tipo) {   //----
		int i = 0;
		String mensaje = "INCORRECTO";

		if (tipo.equalsIgnoreCase("ADMIN")) {
			if (this.getAdmin().getUsername().equals(username) && this.getAdmin().getPassword().equals(password))
                mensaje = "ADMIN";

		} else if (tipo.equalsIgnoreCase("OPERARIO")) {

			while (i < this.operarios.size() && !this.operarios.get(i).getUsername().equals(username))
				i++;
			if (i < this.operarios.size() && this.operarios.get(i).getPassword().equals(password) && this.operarios.get(i).isActivo())
				mensaje = "OPERARIO";
            else if (i < this.operarios.size() && this.operarios.get(i).getPassword().equals(password) && !this.operarios.get(i).isActivo())
                mensaje = "INACTIVO";

		} 
		
		if (mensaje != "INCORRECTO" || mensaje != "INACTIVO") {
            if (mensaje == "OPERARIO")
			    setOperarioLogueado(this.operarios.get(i));
            else
                setOperarioLogueado(this.getAdmin());
		}
		
		this.setChanged();
		this.notifyObservers(mensaje);
	}
    
    

    private Operario getOperario(String username) {  //----
		int i = 0;
		Operario operario = null;
		while (i < this.operarios.size() && !this.operarios.get(i).getUsername().equalsIgnoreCase(username))
			i++;
		if (i < this.operarios.size())
			operario = this.operarios.get(i);
		return operario;
	}

    public String mozoMayorVentas() {
        double maxVenta = -1;
        String mozoMaxVenta = null;
        for (Mozo mozo : mozos) {
            if (mozo.totalVentas() > maxVenta) {
                maxVenta = mozo.totalVentas();
                mozoMaxVenta = mozo.getNya();
            }
        }
        return mozoMaxVenta;
    }

    public String mozoMenorVentas() {
        double minVenta = 999999999;
        String mozoMinVenta = null;
        for (Mozo mozo : mozos) {
            if (mozo.totalVentas() < minVenta) {
                minVenta = mozo.totalVentas();
                mozoMinVenta = mozo.getNya();
            }
        }
        return mozoMinVenta;
    }

    public ArrayList<Producto> getCarta() {
        return carta;
    }

    public Admin getAdministrador() {
        return administrador;
    }

    public ArrayList<PromoProducto> getPromosProductos() {
        return promosProductos;
    }

    public ArrayList<PromoTemporal> getPromosTemporales() {
        return promosTemporales;
    }

    public Operario getOperarioLogueado() {
        return operarioLogueado;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public void setMozos(ArrayList<Mozo> mozos) {
        this.mozos = mozos;
    }

    public void setMesas(ArrayList<Mesa> mesas) {
        this.mesas = mesas;
    }

    public void setCarta(ArrayList<Producto> carta) {
        this.carta = carta;
    }

    public void setOperarios(ArrayList<Operario> operarios) {
        this.operarios = operarios;
    }

    public void setComandasAbiertas(ArrayList<Comanda> comandasAbiertas) {
        this.comandasAbiertas = comandasAbiertas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public void setAdministrador(Admin administrador) {
        this.administrador = administrador;
    }

    public void setPromosProductos(ArrayList<PromoProducto> promosProductos) {
        this.promosProductos = promosProductos;
    }

    public void setPromosTemporales(ArrayList<PromoTemporal> promosTemporales) {
        this.promosTemporales = promosTemporales;
    }

    public void setOperarioLogueado(Operario operarioLogueado) {
        this.operarioLogueado = operarioLogueado;
    }


    public void setEstadoOperario(Operario operario, boolean estado) throws OperarioInexistenteException {
        if (operarios.contains(operario)) {
            operarios.get(operarios.indexOf(operario)).setActivo(estado);
        } else
            throw new OperarioInexistenteException(operario.getUsername());
    }
    
    public ArrayList<Pedido> getComandaDeMesa(int nromesa)
    {
    	ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    	int i=0;
    	
    	while (i < this.comandasAbiertas.size() && this.comandasAbiertas.get(i).getMesa().getNroMesa() != nromesa)
			i++;
		if (i < this.comandasAbiertas.size() &&  this.comandasAbiertas.get(i).getMesa().getNroMesa() == nromesa)
			pedidos = this.comandasAbiertas.get(i).getPedidos();
    	
    	return pedidos;
    }

    public void persistirCerveceria() throws IOException {
        IPersistencia<Serializable> persistencia = new PersistenciaBin();
        persistencia.abrirOutput("Cerveceria.bin");
        CerveceriaDTO cerveceriaDTO = UtilCerveceria.cerveceriaToCerveceriaDTO(Cerveceria.getInstance());
        persistencia.escribir(cerveceriaDTO);
        persistencia.cerrarOutput();
    }

    public void cargarCerveceria() throws IOException, ClassNotFoundException {
        IPersistencia<Serializable> persistencia = new PersistenciaBin();
        Cerveceria cerveceria = Cerveceria.getInstance();
        persistencia.abrirInput("cerveceria.bin");
        CerveceriaDTO cerveceriaDTO = (CerveceriaDTO) persistencia.leer();
        UtilCerveceria.cerveceriaDTOToCerveceria(cerveceriaDTO, cerveceria);
        persistencia.cerrarInput();
    }
    
}
