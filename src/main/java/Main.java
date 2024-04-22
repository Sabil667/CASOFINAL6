import GestionDeDatosDinamicos.AddData;
import GestionDeDatosDinamicos.DeleteData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> data = new ArrayList<>();
    private static AddData addData = new AddData(data);
    private static DeleteData deleteData = new DeleteData(data);

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

        String[] opciones = {"Añadir dato", "Eliminar dato", "Listar datos"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        panel.add(comboBox, gbc);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) comboBox.getSelectedItem();
                switch (opcionSeleccionada) {
                    case "Añadir dato":
                        String newData = JOptionPane.showInputDialog("Introduce el dato a añadir:");
                        addData.execute(newData);
                        break;
                    case "Eliminar dato":
                        String dataToRemove = JOptionPane.showInputDialog("Introduce el dato a eliminar:");
                        deleteData.execute(dataToRemove);
                        break;
                    case "Listar datos":
                        JOptionPane.showMessageDialog(null, "Datos actuales: " + data);
                        break;
                }
            }
        });
    }
}