package IndexacionYVisualizacionDeArchivos;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrdenacionYListado {
    public Map<String, String> ordenarArchivosAlfabeticamente(Map<String, String> indiceArchivos) {
        return indiceArchivos.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public void listarArchivos(Map<String, String> indiceArchivos) {
        for (Map.Entry<String, String> entrada : indiceArchivos.entrySet()) {
            System.out.println("Nombre del archivo: " + entrada.getKey() + ", Ruta completa: " + entrada.getValue());
        }
    }
}