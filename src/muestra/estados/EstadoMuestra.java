package muestra.estados;

import muestra.Muestra;
import muestra.Opinion;


public interface EstadoMuestra {
  
    default boolean esVerificada() { return false; }

    default boolean esAbierta() { return false; }

    default boolean esEnVerificacion() { return false; }

    default void notificarSiVerificada(Muestra muestra) {}

    void agregarOpinion(Muestra muestra, Opinion opinion);
    String getNombreEstado();
}
