package organizacion;

import muestra.Muestra;
import ubicacion.ZonaDeCobertura;

public interface ZonaObserver {
    void notificarNuevaMuestra(ZonaDeCobertura zona, Muestra muestra);
    void notificarValidacion(ZonaDeCobertura zona, Muestra muestra);
}
