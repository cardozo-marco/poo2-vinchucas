package muestra.estados;

import muestra.*;

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
}