package vinchucas.tp;

import java.util.ArrayList;
import java.util.List;

public class Muestra {
	private Ubicacion ubicacion;
	private List<MuestraObserver> observadores = new ArrayList<>();
    private List<Opinion> opiniones;
    private boolean verificada = false;
    
    public void agregarObservador(MuestraObserver observador) {
        observadores.add(observador);
    }

    public Muestra(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
        this.opiniones = new ArrayList<>();
    }

    public void agregarOpinion(Opinion opinion) {
        this.opiniones.add(opinion);
    }

    public List<Opinion> getOpiniones() {
        return new ArrayList<>(opiniones); // Para que no me toquen las opiniones
    }
    
    private void notificarVerificacion() {
        if (verificada) {
            observadores.forEach(o -> o.muestraVerificada(this));
        }
    }
    
    public void verificar() {
        this.verificada = true;
        notificarVerificacion();
    }
    
    public Ubicacion getUbicacion() { return ubicacion; }
    
    public boolean estaVerificada() { return verificada; }
    
}