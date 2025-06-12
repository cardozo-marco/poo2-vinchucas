package muestra.estados;

import muestra.*;

/** Abierta a opiniones **/
public class EstadoAbierta implements EstadoMuestra {
    @Override
    public boolean esAbierta() { return true; }
    @Override
    public void agregarOpinion(Muestra muestra, Opinion opinion) {
        // No permitir que el usuario creador opine sobre su propia muestra
        if (opinion.getAutor().equals(muestra.getAutor())) {
            throw new IllegalArgumentException("El usuario creador no puede opinar sobre su propia muestra.");
        }
        // No permitir que un usuario opine más de una vez
        boolean yaOpino = muestra.getOpiniones().stream()
            .anyMatch(o -> o.getAutor().equals(opinion.getAutor()));
        if (yaOpino) {
            throw new IllegalArgumentException("El usuario ya opinó sobre esta muestra.");
        }
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