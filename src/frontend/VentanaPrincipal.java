package frontend;

import backend.GestorPreguntas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private final CardLayout cardLayout;
    private final JPanel contenedor;

    public VentanaPrincipal() {
        super("Sistema de Pruebas - Taxonomía de Bloom");
        GestorPreguntas gestor = new GestorPreguntas();
        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        PanelCargaArchivo panelCarga = new PanelCargaArchivo(this, gestor);
        contenedor.add(panelCarga, "CARGA");

        setContentPane(contenedor);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cambiarPanel(String nombre, JPanel panel) {
        contenedor.add(panel, nombre);
        cardLayout.show(contenedor, nombre);
    }
}
