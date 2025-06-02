package vinchucas.tp;

import java.util.ArrayList;
import java.util.List;

abstract class Usuario {
    protected String usuario;
    protected String foto;
    protected String ubicacion;
    protected List<Muestra> muestras = new ArrayList<>();
    protected List<Opinion> opiniones = new ArrayList<>();

    public abstract boolean esExperto();

    public void enviarMuestra(Muestra muestra) {
        // Check si ya lo envie
        for (Muestra m : muestras) {
            if (m.equals(muestra)) {
                throw new IllegalStateException("Ya enviaste esta muestra");
            }
        }
        muestras.add(muestra);
    }

    public void opinar(Muestra muestra, TipoOpinion tipo) {
        // Check si ya vote
        for (Opinion op : opiniones) {
            if (op.getMuestra().equals(muestra)) {
                throw new IllegalStateException("Ya opinaste en esta muestra");
            }
        }
        
        // Check si es mia
        if (muestra.getAutor().equals(this)) {
            throw new IllegalStateException("No podes opinar en tu muestra");
        }
        
        Opinion opinion = new Opinion(this, tipo, esExperto(), muestra);
        muestra.agregarOpinion(opinion);
        opiniones.add(opinion);
    }
}
