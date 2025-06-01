package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PanelResumen extends JPanel {
    public PanelResumen(VentanaPrincipal ventana, List<Pregunta> preguntas, List<String> respuestas) {
        setLayout(new BorderLayout());
        JTextArea areaResumen = new JTextArea();
        areaResumen.setEditable(false);

        Evaluador evaluador = new Evaluador(preguntas, respuestas);

        areaResumen.append("\nPorcentaje de aciertos por nivel Bloom:\n");
        for (Map.Entry<NivelBloom, Double> entrada : evaluador.obtenerPorcentajePorNivel().entrySet()) {
            areaResumen.append(entrada.getKey() + ": " + String.format("%.2f", entrada.getValue()) + "%\n");
        }

        areaResumen.append("\nPorcentaje de aciertos por tipo de ítem:\n");
        for (Map.Entry<TipoItem, Double> entrada : evaluador.obtenerPorcentajePorTipo().entrySet()) {
            areaResumen.append(entrada.getKey() + ": " + String.format("%.2f", entrada.getValue()) + "%\n");
        }

        JButton btnRevisar = new JButton("Revisar ítems uno a uno");
        btnRevisar.addActionListener(_ -> {
            PanelRevision panelRevision = new PanelRevision(ventana, preguntas, respuestas, evaluador.obtenerResultadosIndividuales());
            ventana.cambiarPanel("REVISION", panelRevision);
        });

        add(new JScrollPane(areaResumen), BorderLayout.CENTER);
        add(btnRevisar, BorderLayout.SOUTH);
    }
}