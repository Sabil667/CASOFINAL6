package AnalisisyOrganizacióndeInformación;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GeneradorDeVentas {
    public static List<Venta> generarVentas(int cantidad) {
        List<Venta> ventas = new ArrayList<>();
        Random random = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 1; i <= cantidad; i++) {
            String fecha = dateFormat.format(new Date());
            double monto = 100.0 + (500.0 - 100.0) * random.nextDouble(); // Generar un monto aleatorio entre 100.0 y 500.0
            String nombreAleatorio = UUID.randomUUID().toString(); // Generar un nombre aleatorio
            ventas.add(new Venta(nombreAleatorio, monto, fecha));
        }
        return ventas;
    }
}