package vinchucas.tp;

public interface Organizacion {
    void notificarNuevaMuestra(ZonaDeCobertura zona, Muestra muestra);
    void notificarValidacion(ZonaDeCobertura zona, Muestra muestra);
}