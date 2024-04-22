import GestionDeDatosDinamicos.AddData;
import GestionDeDatosDinamicos.DeleteData;
import AnalisisyOrganizacióndeInformación.Venta;
import AnalisisyOrganizacióndeInformación.Ordenamiento;
import AnalisisyOrganizacióndeInformación.OrdenamientoNombres;
import AnalisisyOrganizacióndeInformación.GeneradorDeVentas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    private static List<Venta> ventas = GeneradorDeVentas.generarVentas(15);
    private static AddData addData = new AddData(ventas);
    private static DeleteData deleteData = new DeleteData(ventas);

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

        String[] opciones = {"Añadir dato", "Eliminar dato", "Listar datos", "Ordenar nombres", "Ordenar ventas por monto ascendente", "Ordenar ventas por monto descendente"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        panel.add(comboBox, gbc);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboBox.getSelectedItem();
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
                        OrdenamientoNombres.main(new String[]{});
                        break;
                    case "Ordenar ventas por monto ascendente":
                        Ordenamiento.ordenarVentasPorMonto(ventas, true);
                        StringBuilder ventasOrdenadasAsc = new StringBuilder();
                        for (Venta venta : ventas) {
                            ventasOrdenadasAsc.append("Nombre: ").append(venta.getNombre()).append(", Monto: ").append(venta.getMonto()).append(", Fecha: ").append(venta.getFecha()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Ventas ordenadas por monto (ascendente): \n" + ventasOrdenadasAsc);
                        break;
                    case "Ordenar ventas por monto descendente":
                        Ordenamiento.ordenarVentasPorMonto(ventas, false);
                        StringBuilder ventasOrdenadasDesc = new StringBuilder();
                        for (Venta venta : ventas) {
                            ventasOrdenadasDesc.append("Nombre: ").append(venta.getNombre()).append(", Monto: ").append(venta.getMonto()).append(", Fecha: ").append(venta.getFecha()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, "Ventas ordenadas por monto (descendente): \n" + ventasOrdenadasDesc);
                        break;
                }
            }
        });
    }
}