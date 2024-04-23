package MapasAsociacion;

import java.util.HashMap;
import java.util.Map;

public class RecuperacionEficiente {
    private Map<String, String> datos;

    public RecuperacionEficiente() {
        datos = new HashMap<>();
    }

    public void almacenarDato(String clave, String valor) {
        datos.put(clave, valor);
    }

    public String recuperarDato(String clave) {
        return datos.get(clave);
    }
}