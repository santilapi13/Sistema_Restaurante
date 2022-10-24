package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase que representa una comanda.
 * <b>Invariante</b><br>
 * - La mesa debe estar ocupada mientras la comanda siga abierta.<br>
 */
public class Comanda {
    private LocalDate fecha;
    private Mesa mesa;
    private boolean abierta;
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    /**
     * Constructor de la clase Comanda.<br>
     * <b>Pre:</b> El parametro mesa debe ser distinto de null.<br>
     * El parametro mesa debe existir en la lista de mesas de la cerveceria.<br>
     * <b>Post:</b> Se creara una comanda abierta con la fecha actual, la mesa pasada por parametro.<br>
     * @param mesa : Mesa a la cual se le asignara la comanda.
     */
    public Comanda(Mesa mesa) {
        assert mesa != null : "La mesa no puede ser nula";
        this.mesa = mesa;
        this.abierta = true;
        this.fecha = LocalDate.now();
        this.invariante();
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public Mesa getMesa() {
        return mesa;
    }
    public boolean isAbierta() {
        return abierta;
    }
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    public void agregarPedido(Pedido pedido){
        this.pedidos.add(pedido);
        this.invariante();
    }
    public void eliminarPedido(Pedido pedido){
        this.pedidos.remove(pedido);
        this.invariante();
    }
    public void cerrarComanda(){
        this.abierta = false;
    }

    private void invariante() {
        assert !mesa.isLibre() : "La mesa debe estar ocupada mientras la comanda siga abierta.";
    }
}
