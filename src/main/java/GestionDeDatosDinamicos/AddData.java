package GestionDeDatosDinamicos;

import java.util.List;

public class AddData {
    private List<String> data;

    public AddData(List<String> data) {
        this.data = data;
    }

    public void execute(String newData) {
        data.add(newData);
    }
}