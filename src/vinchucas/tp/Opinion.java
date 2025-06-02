package vinchucas.tp;

class Opinion {
    private Usuario autor;
    private TipoOpinion tipo;
    private boolean esDeExperto;
    private final Muestra muestra;

    public Opinion(Usuario autor, TipoOpinion tipo, boolean esDeExperto, Muestra muestra) {
        this.autor = autor;
        this.tipo = tipo;
        this.esDeExperto = esDeExperto;
        this.muestra = muestra;
        
        validarAutoVoto();
    }
    
    private void validarAutoVoto() {
        if (autor.equals(muestra.getAutor())) {
            throw new IllegalStateException("No podes opinar en tu muestra");
        }
    }
    
    public Muestra getMuestra() {
        return muestra;
    }

    public TipoOpinion getTipo() { return tipo; }
    public boolean esDeExperto() { return esDeExperto; }
    public Usuario getAutor() { return autor; }
}
