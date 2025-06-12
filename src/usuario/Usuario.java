package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public boolean cumpleRequisitosDeExperto() {
        return estado.cumpleRequisitosDeExperto(this);
    }

    public void evaluarPromocion() {
        estado.evaluarPromocion(this);
    }

    public void enviarMuestra(Muestra muestra) {
        if (envios.contains(muestra)) {
            throw new IllegalStateException("Ya enviaste esta muestra");
        }
        envios.add(muestra);
    }

    public void opinar(Muestra muestra, TipoOpinion tipo) {
        for (Opinion op : revisiones) {
            if (op.getMuestra().equals(muestra)) {
                throw new IllegalStateException("Ya opinaste en esta muestra");
            }
        }
        if (muestra.getAutor().equals(this)) {
            throw new IllegalStateException("No podes opinar en tu muestra");
        }
        Opinion opinion = new Opinion(this, tipo, esExperto(), muestra, LocalDate.now());
        muestra.agregarOpinion(opinion);
        revisiones.add(opinion);
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public EstadoUsuario getEstado() { return estado; }
    public void setEstado(EstadoUsuario estado) { this.estado = estado; }
    public List<Muestra> getEnvios() { return envios; }
    public List<Opinion> getRevisiones() { return revisiones; }
}
