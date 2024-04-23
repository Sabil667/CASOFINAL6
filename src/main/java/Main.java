import java.util.stream.Collectors;
import GestionDeDatosDinamicos.AddData;
import GestionDeDatosDinamicos.DeleteData;
import AnalisisyOrganizacióndeInformación.Venta;
import AnalisisyOrganizacióndeInformación.Ordenamiento;
import AnalisisyOrganizacióndeInformación.GeneradorDeVentas;
import AnalisisyOrganizacióndeInformación.AnalisisDeRegistros;
import MapasAsociacion.GestionDeRelaciones;
import MapasAsociacion.RecuperacionEficiente;

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
    private static GestionDeRelaciones gestionDeRelaciones = new GestionDeRelaciones();
    private static RecuperacionEficiente recuperacionEficiente = new RecuperacionEficiente(); // Añadido

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

        String[] opciones = {"Gestionar datos", "Análisis/Organización de Datos", "Mapa y Asociación de Datos"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);

        panel.add(comboBox, gbc);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboBox.getSelectedItem();
                List<Venta> resultado;
                switch (opcionSeleccionada) {
                    case "Gestionar datos":
                        String[] opcionesGestion = {"Añadir dato", "Eliminar dato", "Listar datos"};
                        String opcionGestion = (String) JOptionPane.showInputDialog(null, "Elige una opción", "Gestión de datos", JOptionPane.QUESTION_MESSAGE, null, opcionesGestion, opcionesGestion[0]);
                        switch (opcionGestion) {
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
                        }
                        break;
                    case "Análisis/Organización de Datos":
                        String[] opcionesAnalisis = {"Ordenar", "Filtrar"};
                        String opcionAnalisis = (String) JOptionPane.showInputDialog(null, "Elige una opción", "Análisis/Organización de Datos", JOptionPane.QUESTION_MESSAGE, null, opcionesAnalisis, opcionesAnalisis[0]);
                        switch (opcionAnalisis) {
                            case "Ordenar":
                                String[] opcionesOrdenar = {"Ordenar nombres", "Ordenar ventas por monto ascendente", "Ordenar ventas por monto descendente"};
                                String opcionOrdenar = (String) JOptionPane.showInputDialog(null, "Elige una opción", "Ordenar", JOptionPane.QUESTION_MESSAGE, null, opcionesOrdenar, opcionesOrdenar[0]);
                                switch (opcionOrdenar) {
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
                                }
                                break;
                            case "Filtrar":
                                String[] opcionesFiltrar = {"Filtrar ventas por nombre", "Filtrar ventas por monto mínimo", "Filtrar ventas por monto máximo"};
                                String opcionFiltrar = (String) JOptionPane.showInputDialog(null, "Elige una opción", "Filtrar", JOptionPane.QUESTION_MESSAGE, null, opcionesFiltrar, opcionesFiltrar[0]);
                                switch (opcionFiltrar) {
                                    case "Filtrar ventas por nombre":
                                        String letraInicial = JOptionPane.showInputDialog("Introduce la primera letra para filtrar las ventas:");
                                        String letraFinal = JOptionPane.showInputDialog("Introduce la última letra para filtrar las ventas:");
                                        resultado = ventas.stream()
                                                .filter(venta -> venta.getNombre().substring(0, 1).compareToIgnoreCase(letraInicial) >= 0 && venta.getNombre().substring(0, 1).compareToIgnoreCase(letraFinal) <= 0)
                                                .collect(Collectors.toList());
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
                                break;
                        }
                        break;
                    case "Mapa y Asociación de Datos":
                        String[] opcionesMapa = {"Asociar número a letra", "Asociar número a texto", "Obtener letra asociada", "Obtener texto asociado", "Almacenar dato", "Recuperar dato"}; // Añadido
                        String opcionMapa = (String) JOptionPane.showInputDialog(null, "Elige una opción", "Mapa y Asociación de Datos", JOptionPane.QUESTION_MESSAGE, null, opcionesMapa, opcionesMapa[0]);
                        switch (opcionMapa) {
                            case "Asociar número a letra":
                                int numeroParaLetra = Integer.parseInt(JOptionPane.showInputDialog("Introduce el número a asociar:"));
                                String letraParaNumero = JOptionPane.showInputDialog("Introduce la letra a asociar:");
                                gestionDeRelaciones.asociarNumeroALetra(numeroParaLetra, letraParaNumero);
                                break;
                            case "Asociar número a texto":
                                int numeroParaTexto = Integer.parseInt(JOptionPane.showInputDialog("Introduce el número a asociar:"));
                                String textoParaNumero = JOptionPane.showInputDialog("Introduce el texto a asociar:");
                                gestionDeRelaciones.asociarNumeroATexto(numeroParaTexto, textoParaNumero);
                                break;
                            case "Obtener letra asociada":
                                int numeroParaObtenerLetra = Integer.parseInt(JOptionPane.showInputDialog("Introduce el número para obtener la letra asociada:"));
                                String letraAsociada = gestionDeRelaciones.obtenerLetraAsociada(numeroParaObtenerLetra);
                                JOptionPane.showMessageDialog(null, "La letra asociada es: " + letraAsociada);
                                break;
                            case "Obtener texto asociado":
                                int numeroParaObtenerTexto = Integer.parseInt(JOptionPane.showInputDialog("Introduce el número para obtener el texto asociado:"));
                                String textoAsociado = gestionDeRelaciones.obtenerTextoAsociado(numeroParaObtenerTexto);
                                JOptionPane.showMessageDialog(null, "El texto asociado es: " + textoAsociado);
                                break;
                            case "Almacenar dato": // Añadido
                                String clave = JOptionPane.showInputDialog("Introduce la clave:");
                                String valor = JOptionPane.showInputDialog("Introduce el valor:");
                                recuperacionEficiente.almacenarDato(clave, valor);
                                break;
                            case "Recuperar dato": // Añadido
                                String claveParaRecuperar = JOptionPane.showInputDialog("Introduce la clave para recuperar el valor:");
                                String valorRecuperado = recuperacionEficiente.recuperarDato(claveParaRecuperar);
                                JOptionPane.showMessageDialog(null, "El valor recuperado es: " + valorRecuperado);
                                break;
                        }
                        break;

                }

            }
        });
    }
}