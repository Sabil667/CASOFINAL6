package AnalisisyOrganizacióndeInformación;

import java.text.NumberFormat;
import java.util.Locale;

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

    @Override
    public String toString() {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        return "Nombre: " + nombre + ", Monto: " + format.format(monto) + ", Fecha: " + fecha;
    }
}