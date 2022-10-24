package modelo;

import excepciones.MesaInexistenteException;
import excepciones.MesaNoDisponibleException;
import excepciones.MozoInexistenteException;
import excepciones.MozoNoDisponibleException;

import java.util.ArrayList;

public class Cerveceria {

    private String nombreLocal;
    private ArrayList<Mozo> mozos = new ArrayList<Mozo>();
    private ArrayList<Mesa> mesas = new ArrayList<Mesa>();
//lista de prodcutos
//lista de operarios
    private static Cerveceria instance = null;
    private Cerveceria() {
    }
    public static Cerveceria getInstance(){
        if(instance == null){
            instance = new Cerveceria();
        }
        return instance;
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

        mozos.get(i).agregarMesa(mesas.get(nroMesa));
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

        // TO DO : Hacer calculos de cuenta y cobrar.

        mesa.setLibre(true);
    }
}
