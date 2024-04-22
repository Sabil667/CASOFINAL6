package GestionDeDatosDinamicos;

import AnalisisyOrganizacióndeInformación.Venta;

import java.util.List;

public class DeleteData {
    private List<Venta> data;

    public DeleteData(List<Venta> data) {
        this.data = data;
    }

    public void execute(Venta dataToRemove) {
        data.remove(dataToRemove);
    }
}