package backend;

public class PreguntaVerdaderoFalso extends Pregunta {

    public PreguntaVerdaderoFalso(String enunciado, NivelBloom nivel, int tiempo, boolean respuestaVF) {
        super(enunciado, nivel, TipoItem.VERDADERO_FALSO, tiempo);
    }

    @Override
    public boolean esCorrecta(String respuestaUsuario) {
        return Boolean.parseBoolean(respuestaUsuario.toLowerCase());
    }

}
