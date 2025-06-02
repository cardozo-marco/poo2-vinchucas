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
        muestras.add(muestra);
    }

    public void opinar(Muestra muestra, TipoOpinion tipo) {
        Opinion opinion = new Opinion(this, tipo, esExperto());
        muestra.agregarOpinion(opinion);
        opiniones.add(opinion);
    }
}
