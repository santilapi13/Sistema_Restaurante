package modelo;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.ArrayList;

/**
 * Clase que representa una promoción.<br>
 * <b>Invariante:</b><br>
 * - El dia debe ser distinto de null.<br>
 */
public class Promocion implements Serializable {
    protected ArrayList<DayOfWeek> diasDePromo = new ArrayList<DayOfWeek>();
    private boolean activa = false;

    public Promocion(ArrayList<DayOfWeek> diasDePromo) {
        this.diasDePromo = diasDePromo;
    }

    public ArrayList<DayOfWeek> getDiasDePromo() {
        return diasDePromo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public void setDiasDePromo(ArrayList<DayOfWeek> diasDePromo) {
        this.diasDePromo = diasDePromo;
    }
}
