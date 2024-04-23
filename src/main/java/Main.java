import GestionDeDatosDinamicos.AddData;
import GestionDeDatosDinamicos.DeleteData;
import AnalisisyOrganizacióndeInformación.Venta;
import AnalisisyOrganizacióndeInformación.Ordenamiento;
import AnalisisyOrganizacióndeInformación.GeneradorDeVentas;
import AnalisisyOrganizacióndeInformación.AnalisisDeRegistros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    private static List<Venta> ventas = GeneradorDeVentas.generarVentas(15);
    private static AddData addData = new AddData(ventas);
    private static DeleteData deleteData = new DeleteData(ventas);
    private static AnalisisDeRegistros analisisDeRegistros = new AnalisisDeRegistros();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Menú de Gestión de Datos");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titulo, gbc);

        String[] opciones = {"Añadir dato", "Eliminar dato", "Listar datos", "Ordenar nombres", "Ordenar ventas por monto ascendente", "Ordenar ventas por monto descendente", "Filtrar ventas por nombre", "Filtrar ventas por monto mínimo", "Filtrar ventas por monto máximo"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        panel.add(comboBox, gbc);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboBox.getSelectedItem();
                List<Venta> resultado;
                switch (opcionSeleccionada) {
                    case "Añadir dato":
                        String newData = JOptionPane.showInputDialog("Introduce el nombre de la venta a añadir:");
                        double newMonto = Double.parseDouble(JOptionPane.showInputDialog("Introduce el monto de la venta a añadir:"));
                        String newFecha = JOptionPane.showInputDialog("Introduce la fecha de la venta a añadir:");
                        Venta ventaNueva = new Venta(newData, newMonto, newFecha);
                        addData.execute(ventaNueva);
                        break;
                    case "Eliminar dato":
                        String dataToRemove = JOptionPane.showInputDialog("Introduce el nombre de la venta a eliminar:");
                        Venta ventaEliminar = ventas.stream().filter(venta -> venta.getNombre().equals(dataToRemove)).findFirst().orElse(null);
                        if (ventaEliminar != null) {
                            deleteData.execute(ventaEliminar);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró la venta con el nombre especificado.");
                        }
                        break;
                    case "Listar datos":
                        JOptionPane.showMessageDialog(null, "Datos actuales: " + ventas);
                        break;
                    case "Ordenar nombres":
                        resultado = analisisDeRegistros.ordenarVentasPorNombre(ventas);
                        StringBuilder ventasOrdenadasPorNombre = new StringBuilder();
                        for (Venta venta : resultado) {
                            ventasOrdenadasPorNombre.append(venta).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Ventas ordenadas por nombre: \n" + ventasOrdenadasPorNombre);
                        break;
                    case "Ordenar ventas por monto ascendente":
                        resultado = analisisDeRegistros.ordenarVentasPorMonto(ventas, true);
                        StringBuilder ventasOrdenadasAsc = new StringBuilder();
                        for (Venta venta : resultado) {
                            ventasOrdenadasAsc.append(venta).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Ventas ordenadas por monto (ascendente): \n" + ventasOrdenadasAsc);
                        break;
                    case "Ordenar ventas por monto descendente":
                        resultado = analisisDeRegistros.ordenarVentasPorMonto(ventas, false);
                        StringBuilder ventasOrdenadasDesc = new StringBuilder();
                        for (Venta venta : resultado) {
                            ventasOrdenadasDesc.append(venta).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Ventas ordenadas por monto (descendente): \n" + ventasOrdenadasDesc);
                        break;
                    case "Filtrar ventas por nombre":
                        String nombre = JOptionPane.showInputDialog("Introduce el nombre para filtrar las ventas:");
                        resultado = analisisDeRegistros.filtrarVentasPorNombre(ventas, nombre);
                        StringBuilder ventasFiltradasPorNombre = new StringBuilder();
                        for (Venta venta : resultado) {
                            ventasFiltradasPorNombre.append(venta).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Ventas filtradas por nombre: \n" + ventasFiltradasPorNombre);
                        break;
                    case "Filtrar ventas por monto mínimo":
                        resultado = analisisDeRegistros.filtrarVentasPorMontoMinimo(ventas, 230.92274973325436);
                        StringBuilder ventasFiltradasPorMontoMinimo = new StringBuilder();
                        for (Venta venta : resultado) {
                            ventasFiltradasPorMontoMinimo.append(venta).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Ventas filtradas por monto mínimo: \n" + ventasFiltradasPorMontoMinimo);
                        break;
                    case "Filtrar ventas por monto máximo":
                        resultado = analisisDeRegistros.filtrarVentasPorMontoMaximo(ventas, 230.92274973325436);
                        StringBuilder ventasFiltradasPorMontoMaximo = new StringBuilder();
                        for (Venta venta : resultado) {
                            ventasFiltradasPorMontoMaximo.append(venta).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Ventas filtradas por monto máximo: \n" + ventasFiltradasPorMontoMaximo);
                        break;
                }
            }
        });
    }
}