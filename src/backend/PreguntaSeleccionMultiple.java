package backend;

import java.util.List;

public class PreguntaSeleccionMultiple extends Pregunta {
    private final List<String> opciones;
    private final String respuestaCorrecta;

    public PreguntaSeleccionMultiple(String enunciado, NivelBloom nivel, int tiempo,
                                     List<String> opciones, String respuestaCorrecta) {
        super(enunciado, nivel, TipoItem.MULTIPLE, tiempo);
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public List<String> getOpciones() { return opciones; }

    @Override
    public boolean esCorrecta(String respuestaUsuario) {
        return respuestaCorrecta.equalsIgnoreCase(respuestaUsuario);
    }
}
