package modelo;

import excepciones.*;

import java.util.ArrayList;

public class Cerveceria {

    private String nombreLocal;
    private ArrayList<Mozo> mozos = new ArrayList<Mozo>();
    private ArrayList<Mesa> mesas = new ArrayList<Mesa>();
    private ArrayList<Producto> carta = new ArrayList<Producto>();
    private ArrayList<Operario> operarios = new ArrayList<Operario>();
    private ArrayList<Comanda> comandasAbiertas = new ArrayList<Comanda>();
    private ArrayList<Venta> ventas = new ArrayList<Venta>();
    private Admin administrador = new Admin();

    private static Cerveceria instance = null;
    private Cerveceria() {
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
     * @param nya : El nombre del mozo al cual se quiere cambiar su estado.
     * @param estado : El estado que se quiere asignar al mozo.
     */
    public void setEstado(String nya, EstadoMozo estado) throws MozoInexistenteException {
       int i = 0;
       while (i < mozos.size() && !mozos.get(i).getNya().equalsIgnoreCase(nya))
           i++;

       if (i < mozos.size() && mozos.get(i).getNya().equalsIgnoreCase(nya))
           mozos.get(i).setEstado(estado);
       else
           throw new MozoInexistenteException(nya);
        this.invariante();
    }

    /**
     * Asigna una mesa pasada por parametro al mozo pasado por parámetro .<br>
     * <b>Pre:</b> El parametro nya debe ser distinto de null y el nro de mesa debe ser mayor o igual que 0.<br>
     * <b>Post:</b> Se agregara una mesa a la lista de mesas del mozo y se marcara la mesa como asignada.<br>
     *
     * @param nya : El mozo al cual se quiere agregar una mesa.
     * @param nroMesa : Numero de mesa a asignarle.
     */
    public void asignarMesa(String nya, int nroMesa) throws MozoNoDisponibleException, MozoInexistenteException, MesaInexistenteException, MesaNoDisponibleException {
        int i = 0;
        while (i < mozos.size() && !mozos.get(i).getNya().equalsIgnoreCase(nya))
            i++;

        if (i >= mozos.size() && !mozos.get(i).getNya().equalsIgnoreCase(nya))
            throw new MozoInexistenteException(nya);
        Mozo mozo = mozos.get(i);

        if (nroMesa < mesas.size())
            throw new MesaInexistenteException(nroMesa);
        Mesa mesa = mesas.get(nroMesa);

        if (mozo.getEstado() != EstadoMozo.ACTIVO)
            throw new MozoNoDisponibleException(nya);
        if (mesa.isAsignada())
            throw new MesaNoDisponibleException("La mesa " + nroMesa + " ya fue asignada a otro mozo.");

        mozos.get(i).getMesas().add(mesas.get(nroMesa));
        mesa.setAsignada(true);

        this.invariante();
    }

    /**
     * Cierra la mesa porque los comensales pidieron la cuenta, dejando la mesa libre.<br>
     * <b>Pre: </b> El numero de mesa debe ser mayor o igual que 0.<br>
     * <b>Post: </b> Se cierra la mesa y se deja libre.<br>
     *
     * @param nroMesa : Numero de mesa a cerrar.
     */
    public void cerrarMesa(int nroMesa) throws MesaInexistenteException, MesaNoDisponibleException {
        if (nroMesa < mesas.size())
            throw new MesaInexistenteException(nroMesa);

        Mesa mesa = mesas.get(nroMesa);

        if (mesa.isLibre())
            throw new MesaNoDisponibleException("La mesa " + nroMesa + " no puede cerrarse porque ya esta libre.");

        // TODO : Hacer calculos de cuenta y cobrar.
        // TODO : Crear venta.

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
     * Eliminar un operario de la lista de operarios.<br>
     * <b>Pre:</b> El parametro username debe ser distinto de null.<br>
     * <b>Post:</b> Se eliminara un operario de la lista de operarios.<br>
     *
     * @param username : El username del operario a eliminar.
     * @throws OperarioInexistenteException : Se lanza si el operario no existe.
     */
    public void eliminarOperario(String username) throws OperarioInexistenteException{
        int i = 0;
        while (i < operarios.size() && !operarios.get(i).getUsername().equalsIgnoreCase(username))
            i++;

        if (i < operarios.size() && operarios.get(i).getUsername().equalsIgnoreCase(username))
            operarios.remove(i);
        else
            throw new OperarioInexistenteException(username);
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
     * <b>Pre:</b> El parametro nombre debe ser distinto de null.<br>
     * <b>Post:</b> Se eliminara un producto de la lista de productos.<br>
     * @param nombre : El nombre del producto a eliminar.
     * @throws ProductoInexistenteException : Se lanza si el producto no existe.
     */
    public void eliminarProducto(String nombre) throws ProductoInexistenteException{
        assert nombre != null : "El nombre no puede ser null";
        int i = 0;
        while (i < carta.size() && !carta.get(i).getNombre().equalsIgnoreCase(nombre))
            i++;

        if (i < carta.size() && carta.get(i).getNombre().equalsIgnoreCase(nombre))
            carta.remove(i);
        else
            throw new ProductoInexistenteException(nombre);
        this.invariante();
    }

    /**
     * Actualiza los atributos de un producto de la lista de productos. En caso de que un parametro sea null o -1, no lo modifica.<br>
     * <b>Pre:</b> El parametro nombre debe ser distinto de null. Los parametros pCosto, pVenta y stock deben ser positivos o -1.<br>
     * <b>Post:</b> Se actualizara un producto de la lista de productos.<br>
     *
     * @param nombre : El nombre del producto a modificar.
     * @param pCosto : El nuevo precio de costo.
     * @param pVenta : El nuevo precio de venta.
     * @param stock : El nuevo stock.
     */
    public void modificarProducto(String nombre, double pCosto, double pVenta, int stock) throws ProductoInexistenteException {
        assert nombre != null : "El nombre no puede ser null";
        assert pCosto > 0 || pCosto == -1 : "El precio de costo debe ser positivo o -1";
        assert pVenta > 0 || pVenta == -1 : "El precio de venta debe ser positivo o -1";
        assert stock > 0 || stock == -1 : "El stock debe ser positivo o -1";

        int i = 0;
        while (i < carta.size() && !carta.get(i).getNombre().equalsIgnoreCase(nombre))
            i++;

        if (i < carta.size() && carta.get(i).getNombre().equalsIgnoreCase(nombre)) {
            if (pCosto > 0)
                carta.get(i).setpCosto(pCosto);
            if (pVenta > 0)
                carta.get(i).setpVenta(pVenta);
            if (stock > 0)
                carta.get(i).setStock(stock);
        } else
            throw new ProductoInexistenteException(nombre);
        this.invariante();
    }

    private void invariante() {
        // TODO Auto-generated method stub
    }
}
