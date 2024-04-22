package AnalisisyOrganizacióndeInformación;

import java.util.TreeSet;

public class OrdenamientoNombres {
    public static void main(String[] args) {
        TreeSet<String> nombres = new TreeSet<>();
        nombres.add("Carlos");
        nombres.add("Ana");
        nombres.add("Luis");
        nombres.add("Maria");
        nombres.add("Pedro");
        nombres.add("Juan");


        System.out.println("Nombres ordenados: " + nombres);
    }
}