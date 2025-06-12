package muestra.estados;

import muestra.*;

/* Proceso de verif */
public class EstadoEnVerificacion implements EstadoMuestra {
    @Override
    public boolean esEnVerificacion() { return true; }
    @Override
    public void agregarOpinion(Muestra muestra, Opinion opinion) {
        // Solo expertos (o especialistas) pueden opinar en este estado
        if (!opinion.getAutor().esExperto()) {
            throw new IllegalArgumentException("Solo expertos pueden opinar en este estado.");
        }
      
        // Agregar la opinión al historial
        muestra.agregarOpinionAlHistorial(opinion);
        
        // Verificar si hay coincidencia de dos expertos
        long coincidencias = muestra.contarCoincidenciasDeExpertos();
        
        if (coincidencias > 0) {
        	// No se deberia llamar al metodo muestra.verificar() ?, que setea el estado en EstadoVerificada y notifica a las zonas.
            muestra.setEstado(new EstadoVerificada());
        }
    }

    @Override
    public String getNombreEstado() {
        return "En Verificación";
    }
}