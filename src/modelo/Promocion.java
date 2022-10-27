package modelo;

import java.util.ArrayList;

/**
 * Clase que representa una promoción.<br>
 * <b>Invariante:</b><br>
 * - El dia debe ser distinto de null.<br>
 */
public class Promocion {
    private ArrayList<DiaSemana> diasDePromo = new ArrayList<DiaSemana>();
    private boolean activa = false;

    /**
     * Agrega un dia de la semana en el que corre la promocion.<br>
     * <b>Pre:</b> El parametro dia debe ser distinto de null.<br>
     * <b>Post:</b> Se agregara el dia de la semana en el que corre la promocion.<br>
     * @param dia
     */
    protected void agregarDia(DiaSemana dia) {
        assert dia != null : "El dia no puede ser null";
        diasDePromo.add(dia);
    }

    /**
     * Elimina el dia de la semana especificado de la lista de dias en los que corre la promocion.<br>
     * <b>Pre:</b> El parametro dia debe ser distinto de null y debe existir en la lista de dias.<br>
     * <b>Post:</b> Se eliminara el dia de la semana especificado de la lista de dias en los que corre la promocion.<br>
     * @param dia
     */
    protected void eliminarDia(DiaSemana dia) {
        assert dia != null : "El dia no puede ser null";
        assert diasDePromo.contains(dia) : "El dia no existe en la lista de dias";
        diasDePromo.remove(dia);
    }

    public ArrayList<DiaSemana> getDiasDePromo() {
        return diasDePromo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
