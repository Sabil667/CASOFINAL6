package AnalisisyOrganizacióndeInformación;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GeneradorDeVentas {
    private static String[] nombres = {"Carlos", "Ana", "Pedro", "María", "Juan", "Carmen", "Luis", "Isabel", "Javier", "Teresa"};
    private static String[] apellidos = {"García", "Rodríguez", "Pérez", "Sánchez", "López", "Martínez", "González", "Hernández", "Fernández", "Torres"};

    public static List<Venta> generarVentas(int cantidad) {
        List<Venta> ventas = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < cantidad; i++) {
            String fecha = dateFormat.format(new Date());
            double monto = 100.0 + (500.0 - 100.0) * Math.random(); // Generar un monto aleatorio entre 100.0 y 500.0
            String nombreAleatorio = nombres[i % nombres.length] + " " + apellidos[i % apellidos.length]; // Generar un nombre aleatorio
            ventas.add(new Venta(nombreAleatorio, monto, fecha));
        }
        return ventas;
    }
}