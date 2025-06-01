package backend;

import java.util.*;

public class Evaluador {
    private final List<Pregunta> preguntas;
    private final List<String> respuestas;

    public Evaluador(List<Pregunta> preguntas, List<String> respuestas) {
        this.preguntas = preguntas;
        this.respuestas = respuestas;
    }

    public Map<NivelBloom, Double> obtenerPorcentajePorNivel() {
        Map<NivelBloom, int[]> conteo = new EnumMap<>(NivelBloom.class);
        for (int i = 0; i < preguntas.size(); i++) {
            NivelBloom nivel = preguntas.get(i).getNivel();
            boolean correcta = preguntas.get(i).esCorrecta(respuestas.get(i));
            conteo.putIfAbsent(nivel, new int[2]);
            conteo.get(nivel)[1]++;
            if (correcta) conteo.get(nivel)[0]++;
        }
        Map<NivelBloom, Double> resultado = new EnumMap<>(NivelBloom.class);
        for (NivelBloom nivel : conteo.keySet()) {
            int[] datos = conteo.get(nivel);
            resultado.put(nivel, 100.0 * datos[0] / datos[1]);
        }
        return resultado;
    }

    public Map<TipoItem, Double> obtenerPorcentajePorTipo() {
        Map<TipoItem, int[]> conteo = new EnumMap<>(TipoItem.class);
        for (int i = 0; i < preguntas.size(); i++) {
            TipoItem tipo = preguntas.get(i).getTipo();
            boolean correcta = preguntas.get(i).esCorrecta(respuestas.get(i));
            conteo.putIfAbsent(tipo, new int[2]);
            conteo.get(tipo)[1]++;
            if (correcta) conteo.get(tipo)[0]++;
        }
        Map<TipoItem, Double> resultado = new EnumMap<>(TipoItem.class);
        for (TipoItem tipo : conteo.keySet()) {
            int[] datos = conteo.get(tipo);
            resultado.put(tipo, 100.0 * datos[0] / datos[1]);
        }
        return resultado;
    }

    public List<Boolean> obtenerResultadosIndividuales() {
        List<Boolean> resultados = new ArrayList<>();
        for (int i = 0; i < preguntas.size(); i++) {
            resultados.add(preguntas.get(i).esCorrecta(respuestas.get(i)));
        }
        return resultados;
    }
}
