package AnalisisyOrganizacióndeInformación;

import AnalisisyOrganizacióndeInformación.Venta;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AnalisisDeRegistros {

    // Ordenar las ventas por monto
    public List<Venta> ordenarVentasPorMonto(List<Venta> ventas, boolean ascendente) {
        if (ascendente) {
            return ventas.stream()
                    .sorted(Comparator.comparingDouble(Venta::getMonto))
                    .collect(Collectors.toList());
        } else {
            return ventas.stream()
                    .sorted(Comparator.comparingDouble(Venta::getMonto).reversed())
                    .collect(Collectors.toList());
        }
    }

    // Filtrar las ventas por un monto mínimo
    public List<Venta> filtrarVentasPorMontoMinimo(List<Venta> ventas, double montoMinimo) {
        return ventas.stream()
                .filter(venta -> venta.getMonto() >= montoMinimo)
                .collect(Collectors.toList());
    }

    // Filtrar las ventas por un monto máximo
    public List<Venta> filtrarVentasPorMontoMaximo(List<Venta> ventas, double montoMaximo) {
        return ventas.stream()
                .filter(venta -> venta.getMonto() <= montoMaximo)
                .collect(Collectors.toList());
    }

    // Filtrar las ventas por nombre
    public List<Venta> filtrarVentasPorNombre(List<Venta> ventas, String nombre) {
        return ventas.stream()
                .filter(venta -> venta.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }
}