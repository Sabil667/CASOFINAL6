package AnalisisyOrganizacióndeInformación;

public class Venta {
    private String nombre;
    private double monto;
    private String fecha;

    public Venta(String nombre, double monto, String fecha) {
        this.nombre = nombre;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public double getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }
}