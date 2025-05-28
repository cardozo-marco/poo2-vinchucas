package vinchucas.tp;

class Opinion {
    private Usuario autor;
    private TipoOpinion tipo;
    private boolean esDeExperto;

    public Opinion(Usuario autor, TipoOpinion tipo, boolean esDeExperto) {
        this.autor = autor;
        this.tipo = tipo;
        this.esDeExperto = esDeExperto;
    }

    public TipoOpinion getTipo() { return tipo; }
    public boolean esDeExperto() { return esDeExperto; }
    public Usuario getAutor() { return autor; }
}
