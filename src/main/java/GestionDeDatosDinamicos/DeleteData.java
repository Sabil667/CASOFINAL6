package GestionDeDatosDinamicos;

import java.util.List;

public class DeleteData {
    private List<String> data;

    public DeleteData(List<String> data) {
        this.data = data;
    }

    public void execute(String dataToRemove) {
        data.remove(dataToRemove);
    }
}