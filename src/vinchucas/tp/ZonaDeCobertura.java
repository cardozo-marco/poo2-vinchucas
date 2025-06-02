package vinchucas.tp;

import calculadoraDistancia.CalculadoraDistancia;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ZonaDeCobertura implements MuestraObserver {
    private String nombre;
    private Ubicacion epicentro;
    private double radio;
    private Set<Muestra> muestras;
    private Set<Organizacion> organizacionesRegistradas;

    public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radio) {
        this.nombre = nombre;
        this.epicentro = epicentro;
        this.radio = radio;
        this.muestras = new HashSet<>();
        this.organizacionesRegistradas = new HashSet<>();
    }

    // Getters
    public String getNombre() { return nombre; }
    public Ubicacion getEpicentro() { return epicentro; }
    public double getRadio() { return radio; }
    public Set<Muestra> getMuestras() { return new HashSet<>(muestras); } // Para que no me toquen el set

    // Principal
    public boolean contiene(Ubicacion ubicacion) {
        return this.epicentro.distanciaA(ubicacion) <= this.radio;
    }

    public boolean seSuperponeCon(ZonaDeCobertura otra) {
        return CalculadoraDistancia.seSuperponenLasZonas(this, otra);
    }

    // Muestras
    public void agregarMuestra(Muestra muestra) {
        if (this.contiene(muestra.getUbicacion())) {
            muestras.add(muestra);
            muestra.agregarObservador(this);  // Observador
            notificarNuevaMuestra(muestra);
        }
    }
    

    public void muestraVerificada(Muestra muestra) {
        if (this.contiene(muestra.getUbicacion())) {
            notificarValidacion(muestra);
        }
    }


    // Organizacion
    public void registrarOrganizacion(Organizacion org) {
        organizacionesRegistradas.add(org);
    }

    public void desregistrarOrganizacion(Organizacion org) {
        organizacionesRegistradas.remove(org);
    }

    // Notif
    private void notificarNuevaMuestra(Muestra muestra) {
        organizacionesRegistradas.forEach(org -> 
            org.notificarNuevaMuestra(this, muestra));
    }

    private void notificarValidacion(Muestra muestra) {
        if (muestra.estaVerificada()) {
            organizacionesRegistradas.forEach(org -> 
                org.notificarValidacion(this, muestra));
        }
    }
}
    
