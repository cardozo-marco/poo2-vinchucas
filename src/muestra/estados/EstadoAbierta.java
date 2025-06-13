package muestra.estados;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import muestra.*;
import muestra.enums.TipoOpinion;

/** Abierta a opiniones **/
public class EstadoAbierta implements EstadoMuestra {
    @Override
    public boolean esAbierta() { return true; }
    @Override
    public void agregarOpinion(Muestra muestra, Opinion opinion) {
        // Agregar la opinión al historial
        muestra.agregarOpinionAlHistorial(opinion);
        // Si opina un experto, pasar a EstadoEnVerificacion
        if (opinion.getAutor().esExperto()) {
            muestra.setEstado(new EstadoEnVerificacion());
        }
        // Si no, permanece en EstadoAbierta
    }

    @Override
    public String getNombreEstado() {
        return "Abierta";
    }
    
    /*
     * Indica el TipoOpinion con mas votos entre las opiniones de la muestra.
    */
    @Override
    public TipoOpinion getResultadoActual(Muestra muestra) {
    	
    	//Se verica si hay opiniones
    	if (muestra.getOpiniones().isEmpty()) {
            return TipoOpinion.NINGUNA;
        }

        //Se agrupan y cuentan los votos por cada tipo de opinion
        Map<TipoOpinion, Long> conteo = muestra.getOpiniones().stream()
            .collect(Collectors.groupingBy(
                Opinion::getTipo,
                Collectors.counting()
            ));

        //Encontrar la clasificación más votada
        long maxVotos = conteo.values().stream()
                		.max(Long::compare)
                		.orElseThrow();

        //Se verifica si hay empates
        List<TipoOpinion> masVotadas = conteo.entrySet().stream()
                .filter(e -> e.getValue() == maxVotos)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        //Retorna el tipo de opinion mas votada o NINGUNA si hay empate.
        return masVotadas.size() == 1 ? masVotadas.get(0) : TipoOpinion.NINGUNA;
    }
}