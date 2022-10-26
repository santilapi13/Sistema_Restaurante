package modelo;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Clase que representa a un mozo de la cerveceria.<br>
 * <b>Invariante: </b><br>
 * - el mozo debe ser mayor de 18 anos.
 * - la cantidad de hijos debe ser mayor o igual a 0.
 */
public class Mozo {

    private String nya;
    private LocalDate fechaNacimiento;
    private int cantHijos;
    private int cantVentas;
    private double totalVentas;
    private ArrayList<Mesa> mesas = new ArrayList<Mesa>();

    private EstadoMozo estado = null;

    /**
     * Constructor de la clase Mozo.<br>
     * <b>Pre:</b> Los parametros nya y fechaNacimiento debe, ser distinto de null.<br>
     * El parametro cantHijos debe ser mayor o igual que 0.<br>
     * La edad debe ser mayor o igual que 18.<br>
     * @param nya : Nombre y apellido del mozo.
     * @param cantHijos : Cantidad de hijos del mozo.
     * @param fechaNacimiento : Fecha de nacimiento del mozo en formato dd/MM/yyyy.
     */
    public Mozo(String nya, int cantHijos, String fechaNacimiento) {
        this.nya = nya;
        this.cantHijos = cantHijos;
        this.fechaNacimiento = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.invariante();
    }

    public int getEdad() {
        Period periodo = Period.between(fechaNacimiento, LocalDate.now());
        return periodo.getYears();
    }

    public String getNya() {
        return nya;
    }

    public int getCantHijos() {
        return cantHijos;
    }

    public void setCantHijos(int cantHijos) {
        this.cantHijos = cantHijos;
        this.invariante();
    }

    public int getCantVentas() {
        return cantVentas;
    }

    public void setCantVentas(int cantVentas) {
        this.cantVentas = cantVentas;
    }

    public double getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(double totalVentas) {
        this.totalVentas = totalVentas;
    }

    public EstadoMozo getEstado() {
        return estado;
    }

    public void setEstado(EstadoMozo estado) {
        this.estado = estado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    private void invariante() {
        assert nya != null : "El nombre y apellido del mozo no puede ser nulo";
        assert fechaNacimiento != null : "La fecha de nacimiento del mozo no puede ser nula";
        assert cantHijos >= 0 : "La cantidad de hijos del mozo no puede ser negativa";
        assert getEdad() >= 18 : "La edad debe ser mayor o igual que 18";
    }

}
