package muestra.estados;

import muestra.Muestra;
import muestra.Opinion;
import muestra.enums.TipoOpinion;


public interface EstadoMuestra {
  
    default boolean esVerificada() { return false; }

    default boolean esAbierta() { return false; }

    default boolean esEnVerificacion() { return false; }

    default void notificarSiVerificada(Muestra muestra) {}

    void agregarOpinion(Muestra muestra, Opinion opinion);
    
    TipoOpinion getResultadoActual(Muestra muestra);
    
    String getNombreEstado();
}
