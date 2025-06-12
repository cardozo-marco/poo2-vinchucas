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
        // No permitir que un usuario opine más de una vez
        boolean yaOpino = muestra.getHistorialOpiniones().getOpiniones().stream()
            .anyMatch(o -> o.getAutor().equals(opinion.getAutor()));
        if (yaOpino) {
            throw new IllegalArgumentException("El usuario ya opinó sobre esta muestra.");
        }
        // Agregar la opinión al historial
        muestra.agregarOpinionAlHistorial(opinion);
        // Verificar si hay coincidencia de dos expertos
        long coincidencias = muestra.getHistorialOpiniones().getOpiniones().stream()
            .filter(o -> o.getAutor().esExperto())
            .collect(java.util.stream.Collectors.groupingBy(
                o -> o.getTipo(),
                java.util.stream.Collectors.counting()
            ))
            .values().stream().filter(c -> c >= 2).count();
        if (coincidencias > 0) {
            muestra.setEstado(new EstadoVerificada());
        }
    }

    @Override
    public String getNombreEstado() {
        return "En Verificación";
    }
}