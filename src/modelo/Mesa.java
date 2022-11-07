package modelo;

import java.io.Serializable;

/**
 * Clase Mesa del modelo <br>
 * <b>Invariante: </b><br>
 * - nroMesa debe ser mayor o igual a 0.
 * - los nroMesa no pueden repetirse.
 * - la cantComensales debe ser mayor o igual a 2 cuando nroMesa es mayor a 1.
 */
public class Mesa implements Serializable {

    private static int nroUltima = -1;
    private int nroMesa;
    private int cantComensales;
    private boolean asignada;
    private boolean libre;

    /**
     * Constructor de la clase Mesa. El nroMesa 0 corresponde a la barra.<br>
     * <b>Pre:</b> El parametro cantComensales debe ser mayor o igual a 2 si nroMesa es mayor a 1.<br>
     * cantComensales debe ser siempre mayor o igual a 0.<br>
     * <b>Post:</b> Se creara una mesa con un nroMesa mayor al de la ultima creada, y la cantidad de comensales pasada por parametro.<br>
     * @param cantComensales : Cantidad de sillas (cantidad maxima de comensales) de la mesa.
     */
    public Mesa(int cantComensales) {
        nroUltima++;
        this.nroMesa = nroUltima;
        if (nroMesa > 1)
            assert cantComensales >= 2 : "La mesa no puede tener menos de 2 comensales si no se trata de la barra.";
        else
            assert cantComensales >= 0 : "La mesa no puede tener menos de 0 comensales.";
        this.cantComensales = cantComensales;
        this.asignada = false;
        this.libre = true;

    }

    public int getNroMesa() {
        return nroMesa;
    }
    public int getCantComensales() {
        return cantComensales;
    }
    public void setCantComensales(int cantComensales) {
        this.cantComensales = cantComensales;
    }
    public boolean isAsignada() {
        return asignada;
    }
    public void setAsignada(boolean asignada) {
        this.asignada = asignada;
    }
    public boolean isLibre() {
        return libre;
    }
    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public static int getNroUltima() {
        return nroUltima;
    }

    public static void setNroUltima(int nroUltima) {
        Mesa.nroUltima = nroUltima;
    }

    public void setNroMesa(int nroMesa) {
        this.nroMesa = nroMesa;
    }

    @Override
    public String toString() {
        return "Mesa N° " + nroMesa +
                "\t Capacidad: " + cantComensales +
                "\t Asignada: " + asignada +
                "\t Libre: " + libre;
    }
}
