package backend;

public abstract class Pregunta {
    protected String enunciado;
    protected NivelBloom nivel;
    protected TipoItem tipo;
    protected int tiempo;

    public Pregunta(String enunciado, NivelBloom nivel, TipoItem tipo, int tiempo) {
        this.enunciado = enunciado;
        this.nivel = nivel;
        this.tipo = tipo;
        this.tiempo = tiempo;
    }

    public abstract boolean esCorrecta(String respuestaUsuario);

    public String getEnunciado() { return enunciado; }
    public NivelBloom getNivel() { return nivel; }
    public TipoItem getTipo() { return tipo; }
    public int getTiempo() { return tiempo; }
}
