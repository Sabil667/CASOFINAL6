package IndexacionYVisualizacionDeArchivos;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class IndexacionRecursiva {
    private Map<String, String> indiceArchivos;

    public IndexacionRecursiva() {
        indiceArchivos = new HashMap<>();
    }

    public void indexarDirectorio(String rutaDirectorio) {
        File directorio = new File(rutaDirectorio);
        File[] archivos = directorio.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    indexarDirectorio(archivo.getPath());
                } else {
                    indiceArchivos.put(archivo.getName(), archivo.getPath());
                }
            }
        }
    }

    public String buscarArchivo(String nombreArchivo) {
        return indiceArchivos.get(nombreArchivo);
    }
}