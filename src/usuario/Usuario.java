package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import muestra.Muestra;
import muestra.enums.TipoOpinion;
import usuario.estado.EstadoBasico;
import usuario.estado.EstadoUsuario;
import muestra.Opinion;

public class Usuario {
    private String nombre;
    private String email;
    private EstadoUsuario estado;
    private List<Muestra> envios;
    private List<Opinion> revisiones;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.envios = new ArrayList<>();
        this.revisiones = new ArrayList<>();
        this.estado = new EstadoBasico();
    }

    public boolean esExperto() {
        return estado.esExperto();
    }

    public boolean esEspecialista() {
        return estado.esEspecialista();
    }

    /*
     * Verifica si el usuario cumple los requisitos para ser promovido a Experto:
     * - Más de 10 envios de muestras en los últimos 30 días.
     * - Más de 20 revisiones en los ultimos 30 días. 
     * 
     * Cada opinion emitida por el usuario cuenta como revisión, independientemente de si
     * coincide con el resultado final de la muestra.
     */
    public boolean cumpleRequisitosDeExperto() {
    	long cantidadMuestras = this.cantidadMuestrasRecientes(LocalDate.now().minusDays(30));
    	long cantidadRevisiones = this.cantidadRevisionesRecientes(LocalDate.now().minusDays(30));
    	
    	return cantidadMuestras > 10 && cantidadRevisiones > 20; 
    }

    public void evaluarPromocion() {
        estado.evaluarPromocion(this);
    }

    public void enviarMuestra(Muestra muestra) {
        if (envios.contains(muestra)) {
            throw new IllegalStateException("Ya enviaste esta muestra");
        }
        envios.add(muestra);
        this.evaluarPromocion();
    }

    public void opinar(Muestra muestra, TipoOpinion tipo) {
    	// No permitir que un usuario opine más de una vez
        for (Opinion op : revisiones) {
            if (op.getMuestra().equals(muestra)) {
                throw new IllegalStateException("Ya opinaste en esta muestra");
            }
        }
        // No permitir que el usuario creador opine sobre su propia muestra
        if (muestra.getAutor().equals(this)) {
            throw new IllegalStateException("No podes opinar en tu muestra");
        }
        Opinion opinion = new Opinion(this, tipo, esExperto(), muestra, LocalDate.now());
        muestra.agregarOpinion(opinion);
        revisiones.add(opinion);
        this.evaluarPromocion();
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public EstadoUsuario getEstado() { return estado; }
    public void setEstado(EstadoUsuario estado) { this.estado = estado; }
    public List<Muestra> getEnvios() { return envios; }
    public List<Opinion> getRevisiones() { return revisiones; }
    
    
    // Retorna las muestras posteriores a la fecha limite
    public long cantidadMuestrasRecientes(LocalDate fechaLimite) {
    	return getEnvios().stream()
    	        .filter(m -> m.getFechaCreacion().isAfter(fechaLimite))
    	        .count();
    }
    
    // Retorna las opiniones posteriores a la fecha limite
    public long cantidadRevisionesRecientes(LocalDate fechaLimite) {
    	return getRevisiones().stream()
    	        .filter(m -> m.getFecha().isAfter(fechaLimite))
    	        .count();
    }
}
