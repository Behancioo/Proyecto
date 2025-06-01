package frontend;

import backend.GestorPreguntas;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelCargaArchivo extends JPanel {
    public PanelCargaArchivo(VentanaPrincipal ventana, GestorPreguntas gestor) {
        setLayout(new BorderLayout());

        JButton botonCargar = new JButton("Cargar archivo de ítems");
        JLabel resultado = new JLabel("", SwingConstants.CENTER);
        JButton iniciar = new JButton("Iniciar Prueba");
        iniciar.setEnabled(false);

        botonCargar.addActionListener(_ -> {
            JFileChooser fileChooser = new JFileChooser();
            int opcion = fileChooser.showOpenDialog(this);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                try {
                    gestor.cargarDesdeArchivo(archivo.getAbsolutePath());
                    resultado.setText("Ítems cargados: " + gestor.obtenerCantidadPreguntas() + ", Tiempo total: " + gestor.obtenerTiempoTotal() + " seg.");
                    iniciar.setEnabled(true);
                } catch (Exception ex) {
                    ex.printStackTrace(); // <- Añade esto
                    JOptionPane.showMessageDialog(this, "Error al cargar el archivo:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        iniciar.addActionListener(_ -> {
            PanelPrueba panelPrueba = new PanelPrueba(ventana, gestor);
            ventana.cambiarPanel("PRUEBA", panelPrueba);
        });

        add(botonCargar, BorderLayout.NORTH);
        add(resultado, BorderLayout.CENTER);
        add(iniciar, BorderLayout.SOUTH);
    }
}
