package muestra.estados;

import java.util.stream.Stream;

import muestra.*;
import muestra.enums.TipoOpinion;

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
        
        // Verificar si pasa a ser verificada
        this.verificarResultado(muestra, opinion);
        
    }

    /*
     * Verifica si la opinion a agregar cambia el estado de la muestra a Verificada.
    */
    private void verificarResultado(Muestra muestra, Opinion opinion) {
    	if (this.hayCoincidenciaDeExpertos(muestra, opinion.getTipo())) {
    		
    		muestra.setResultadoVerificado(opinion.getTipo());
    		muestra.verificar();
    	}
    }
    
    /*
     * Indica si un tipoOpinion específica está respaldada por al menos un experto.
    */
    private boolean hayCoincidenciaDeExpertos(Muestra muestra, TipoOpinion tipo) {
    	Stream<TipoOpinion> clasifDeExpertos = muestra.getOpiniones().stream()
                .filter(o -> o.getAutor().esExperto())
                .map(o -> o.getTipo());
    	return clasifDeExpertos.anyMatch(t -> t.equals(tipo));
    }
    
    @Override
    public String getNombreEstado() {
        return "En Verificación";
    }
    
    @Override
    public TipoOpinion getResultadoActual(Muestra muestra) {
    	// Si hay mas de una opinion de experto se considera empate, y se retorna el tipoDeOpinion NINGUNA
    	if (muestra.getHistorialOpiniones().cantidadOpinionesExperto() > 1) {
    		return TipoOpinion.NINGUNA;
    	}
    	
    	// Retorna el resultado de la ultima opinion de la muestra, ya que el experto
    	// es el ultimo en opinar en una muestra en EstadoEnVerificacion
    	return muestra.getResultadoUltimaOpinion();
    	
    }
    
}