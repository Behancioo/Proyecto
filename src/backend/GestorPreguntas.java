package backend;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GestorPreguntas {
    private final List<Pregunta> preguntas;

    public GestorPreguntas() {
        preguntas = new ArrayList<>();
    }

    public void cargarDesdeArchivo(String ruta) throws IOException {
        preguntas.clear();
        List<String> lineas = Files.readAllLines(Paths.get(ruta));
        int lineaActual = 0;

        for (String linea : lineas) {
            lineaActual++;
            if (linea.trim().isEmpty()) continue;
            String[] partes = linea.split(";");

            if (partes.length != 6) {
                throw new IllegalArgumentException("Línea " + lineaActual + " no tiene 6 campos: " + linea);
            }

            try {
                TipoItem tipo = TipoItem.valueOf(partes[0].trim().toUpperCase());
                NivelBloom nivel = NivelBloom.valueOf(partes[1].trim().toUpperCase());
                String enunciado = partes[2].trim();
                String opcionesTexto = partes[3].trim();
                String respuesta = partes[4].trim();
                int tiempo = Integer.parseInt(partes[5].trim());

                if (tipo == TipoItem.MULTIPLE) {
                    List<String> opciones = Arrays.asList(opcionesTexto.split("\\|"));
                    if (!opciones.contains(respuesta)) {
                        throw new IllegalArgumentException("La respuesta no está entre las opciones: " + respuesta);
                    }
                    preguntas.add(new PreguntaSeleccionMultiple(enunciado, nivel, tiempo, opciones, respuesta));
                } else if (tipo == TipoItem.VERDADERO_FALSO) {
                    boolean respuestaVF;
                    if (respuesta.equalsIgnoreCase("VERDADERO")) {
                        respuestaVF = true;
                    } else if (respuesta.equalsIgnoreCase("FALSO")) {
                        respuestaVF = false;
                    } else {
                        throw new IllegalArgumentException("Respuesta VF inválida: " + respuesta);
                    }
                    preguntas.add(new PreguntaVerdaderoFalso(enunciado, nivel, tiempo, respuestaVF));
                }
            } catch (Exception e) {
                throw new IOException("Error en línea " + lineaActual + ": " + e.getMessage());
            }
        }
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public int obtenerTiempoTotal() {
        return preguntas.stream().mapToInt(Pregunta::getTiempo).sum();
    }

    public int obtenerCantidadPreguntas() {
        return preguntas.size();
    }
}
