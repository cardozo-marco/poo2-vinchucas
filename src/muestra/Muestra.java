package muestra;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import muestra.enums.Especie;
import muestra.enums.TipoOpinion;
import muestra.estados.EstadoMuestra;
import muestra.estados.EstadoVerificada;
import ubicacion.Ubicacion;
import usuario.Usuario;

public class Muestra {
    private String foto;
    private Ubicacion ubicacion;
    private Usuario autor;
    private Especie especieInicial;
    private LocalDate fechaCreacion;
    private HistorialOpiniones historialOpiniones;
    private EstadoMuestra estado;
    private List<MuestraObserver> observadores = new ArrayList<>();
    private TipoOpinion resultadoVerificado;

    public Muestra(String foto, Ubicacion ubicacion, Usuario usuarioCreador, Especie especieInicial, LocalDate fechaCreacion, EstadoMuestra estadoInicial) {
        this.foto = foto;
        this.ubicacion = ubicacion;
        this.autor = usuarioCreador;
        this.especieInicial = especieInicial;
        this.fechaCreacion = fechaCreacion;
        this.historialOpiniones = new HistorialOpiniones();
        this.estado = estadoInicial;
        this.resultadoVerificado = TipoOpinion.NINGUNA;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void agregarOpinion(Opinion opinion) {
        estado.agregarOpinion(this, opinion);
    }

    public List<Opinion> getOpiniones() {
        return historialOpiniones.getOpiniones();
    }

    public void agregarOpinionAlHistorial(Opinion opinion) {
        historialOpiniones.agregarOpinion(opinion);
    }

    private void notificarVerificacion() {
    for (MuestraObserver observador : observadores) {
        observador.muestraVerificada(this);
    	}
    }

    public void agregarObservador(MuestraObserver observador) {
    observadores.add(observador);
    }

    public void quitarObservador(MuestraObserver observador) {
    observadores.remove(observador);
    }

    public void setEstado(EstadoMuestra nuevoEstado) {
        this.estado = nuevoEstado;
    }
    
    public void setResultadoVerificado(TipoOpinion tipo) {
    	this.resultadoVerificado = tipo;
    }

    public void verificar() {
        setEstado(new EstadoVerificada());
        notificarVerificacion();
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public String getFoto() {
        return foto;
    }

    public Especie getEspecieInicial() {
        return especieInicial;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public boolean estaVerificada() {
        return estado.esVerificada();
    }
    
    public HistorialOpiniones getHistorialOpiniones() {
    	return historialOpiniones;
    }
    
    public String getNombreEstado() {
        return estado.getNombreEstado();
    }
    
    public TipoOpinion getResultadoVerificado() {
    	return this.resultadoVerificado;
    }
    
    public LocalDate getFechaUltimaVotacion() {
        return this.getOpiniones().stream()
            .map(o -> o.getFecha())
            .max((fecha1, fecha2) -> fecha1.compareTo(fecha2))
            .orElse(this.getFechaCreacion()); //si no tiene opiniones
    }
    
    public TipoOpinion getResultadoUltimaOpinion() {
    	return (this.getHistorialOpiniones().getUltimaOpinion()).getTipo();
    }
    
    public int cantidadOpinionesExperto() {
    	return this.historialOpiniones.cantidadOpinionesExperto(); 
    }
}