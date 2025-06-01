package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelRevision extends JPanel {
    private int indice = 0;
    private final List<Pregunta> preguntas;
    private final List<String> respuestas;
    private final List<Boolean> resultados;
    private final JPanel panelContenido;

    public PanelRevision(VentanaPrincipal ventana, List<Pregunta> preguntas, List<String> respuestas, List<Boolean> resultados) {
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        this.resultados = resultados;
        setLayout(new BorderLayout());

        panelContenido = new JPanel();
        add(panelContenido, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAnterior = new JButton("Anterior");
        JButton btnSiguiente = new JButton("Siguiente");
        JButton btnResumen = new JButton("Volver al resumen");

        btnAnterior.addActionListener(_ -> {
            if (indice > 0) {
                indice--;
                mostrarPregunta();
            }
        });

        btnSiguiente.addActionListener(_ -> {
            if (indice < preguntas.size() - 1) {
                indice++;
                mostrarPregunta();
            }
        });

        btnResumen.addActionListener(_ -> ventana.cambiarPanel("RESUMEN", this));

        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);
        panelBotones.add(btnResumen);
        add(panelBotones, BorderLayout.SOUTH);

        mostrarPregunta();
    }

    private void mostrarPregunta() {
        panelContenido.removeAll();
        Pregunta pregunta = preguntas.get(indice);
        String respuesta = respuestas.get(indice);
        boolean correcta = resultados.get(indice);

        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(new JLabel("(" + (indice + 1) + "/" + preguntas.size() + ") " + pregunta.getEnunciado()));
        panelContenido.add(new JLabel("Tu respuesta: " + respuesta));
        panelContenido.add(new JLabel("Resultado: " + (correcta ? "Correcta" : "Incorrecta")));

        panelContenido.revalidate();
        panelContenido.repaint();
    }
}
