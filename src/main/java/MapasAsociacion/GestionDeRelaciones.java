package MapasAsociacion;

import java.util.HashMap;
import java.util.Map;

public class GestionDeRelaciones {
    private Map<Integer, String> mapaNumerosALetras;
    private Map<Integer, String> mapaNumerosATexto;

    public GestionDeRelaciones() {
        mapaNumerosALetras = new HashMap<>();
        mapaNumerosATexto = new HashMap<>();
    }

    public void asociarNumeroALetra(int numero, String letra) {
        mapaNumerosALetras.put(numero, letra);
    }

    public void asociarNumeroATexto(int numero, String texto) {
        mapaNumerosATexto.put(numero, texto);
    }

    public String obtenerLetraAsociada(int numero) {
        return mapaNumerosALetras.get(numero);
    }

    public String obtenerTextoAsociado(int numero) {
        return mapaNumerosATexto.get(numero);
    }
}