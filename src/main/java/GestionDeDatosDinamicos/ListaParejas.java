package GestionDeDatosDinamicos;

import java.util.ArrayList;

public class ListaParejas {
    private ArrayList<Pareja> lista;

    public ListaParejas() {
        lista = new ArrayList<>();
    }

    public void agregarPareja(Pareja pareja) {
        lista.add(pareja);
    }

    public void eliminarPareja(Pareja pareja) {
        lista.remove(pareja);
    }

    public ArrayList<Pareja> getLista() {
        return lista;
    }
}