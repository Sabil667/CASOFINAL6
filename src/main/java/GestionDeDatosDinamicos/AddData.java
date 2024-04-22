package GestionDeDatosDinamicos;

import AnalisisyOrganizacióndeInformación.Venta;

import java.util.List;

public class AddData {
    private List<Venta> data;

    public AddData(List<Venta> data) {
        this.data = data;
    }

    public void execute(Venta newData) {
        data.add(newData);
    }
}