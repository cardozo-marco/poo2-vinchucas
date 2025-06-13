package muestra.estados;

import muestra.*;
import muestra.enums.TipoOpinion;

public class EstadoVerificada implements EstadoMuestra {
    @Override
    public boolean esVerificada() { return true; }
    @Override
    public void agregarOpinion(Muestra muestra, Opinion opinion) {
        throw new IllegalStateException("No se pueden agregar opiniones a una muestra verificada.");
    }

    @Override
    public void notificarSiVerificada(Muestra muestra) {
        // La notificación debe ser gestionada por la ZonaDeCobertura, no por la muestra.
    }

    @Override
    public String getNombreEstado() {
        return "Verificada";
    }
    
    @Override
    public TipoOpinion getResultadoActual(Muestra muestra) {
    	return muestra.getResultadoVerificado();
    }
}
