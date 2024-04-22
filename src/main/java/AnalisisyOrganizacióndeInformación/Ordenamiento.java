package AnalisisyOrganizacióndeInformación;

import java.util.List;

public class Ordenamiento {
    public static void ordenarVentasPorMonto(List<Venta> ventas, boolean ascendente) {
        int n = ventas.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (ascendente ? ventas.get(j).getMonto() > ventas.get(j+1).getMonto() : ventas.get(j).getMonto() < ventas.get(j+1).getMonto()) {
                    // Intercambiar ventas.get(j+1) y ventas.get(i)
                    Venta temp = ventas.get(j);
                    ventas.set(j, ventas.get(j+1));
                    ventas.set(j+1, temp);
                }
    }
}