package muestra;

import java.time.LocalDate;

import muestra.enums.TipoOpinion;
import usuario.Usuario;

public class Opinion {
    private Usuario autor;
    private TipoOpinion tipo;
    private boolean esDeExperto;
    private final Muestra muestra;
    private LocalDate fecha;

    public Opinion(Usuario autor, TipoOpinion tipo2, boolean esDeExperto, Muestra muestra, LocalDate fecha) {
        this.autor = autor;
        this.tipo = tipo2;
        this.esDeExperto = esDeExperto;
        this.muestra = muestra;
        this.fecha = fecha;
        
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
    public LocalDate getFecha() { return fecha; }
}
