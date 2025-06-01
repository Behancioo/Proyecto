package frontend;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelPrueba extends JPanel {
    private int indice = 0;
    private final List<Pregunta> preguntas;
    private final List<String> respuestas;
    private final JPanel panelContenido;
    private ButtonGroup grupoOpciones;

    public PanelPrueba(VentanaPrincipal ventana, GestorPreguntas gestor) {
        this.preguntas = gestor.getPreguntas();
        this.respuestas = new ArrayList<>();

        for (int i = 0; i < preguntas.size(); i++) respuestas.add("");

        setLayout(new BorderLayout());
        panelContenido = new JPanel();
        add(panelContenido, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnAnterior = new JButton("Anterior");
        JButton btnSiguiente = new JButton("Siguiente");

        btnAnterior.addActionListener(_ -> {
            guardarRespuestaActual();
            if (indice > 0) {
                indice--;
                mostrarPregunta();
            }
        });

        btnSiguiente.addActionListener(_ -> {
            guardarRespuestaActual();
            if (indice < preguntas.size() - 1) {
                indice++;
                mostrarPregunta();
            } else {
                ventana.cambiarPanel("RESUMEN", new PanelResumen(ventana, preguntas, respuestas));
            }
        });

        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);
        add(panelBotones, BorderLayout.SOUTH);

        mostrarPregunta();
    }

    private void mostrarPregunta() {
        panelContenido.removeAll();
        Pregunta pregunta = preguntas.get(indice);
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.add(new JLabel("(" + (indice + 1) + "/" + preguntas.size() + ") " + pregunta.getEnunciado()));

        grupoOpciones = new ButtonGroup();
        if (pregunta instanceof PreguntaSeleccionMultiple psm) {
            for (String opcion : psm.getOpciones()) {
                JRadioButton radio = new JRadioButton(opcion);
                grupoOpciones.add(radio);
                panelContenido.add(radio);
                if (opcion.equals(respuestas.get(indice))) radio.setSelected(true);
            }
        } else if (pregunta instanceof PreguntaVerdaderoFalso) {
            JRadioButton verdadero = new JRadioButton("VERDADERO");
            JRadioButton falso = new JRadioButton("FALSO");
            grupoOpciones.add(verdadero);
            grupoOpciones.add(falso);
            panelContenido.add(verdadero);
            panelContenido.add(falso);
            if (respuestas.get(indice).equalsIgnoreCase("VERDADERO")) verdadero.setSelected(true);
            else if (respuestas.get(indice).equalsIgnoreCase("FALSO")) falso.setSelected(true);
        }

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void guardarRespuestaActual() {
        for (AbstractButton button : java.util.Collections.list(grupoOpciones.getElements())) {
            if (button.isSelected()) {
                respuestas.set(indice, button.getText());
                break;
            }
        }
    }
}