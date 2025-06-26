package ubicacion;

import java.util.ArrayList;
import java.util.List;

import muestra.Muestra;
import muestra.MuestraObserver;
import organizacion.Organizacion;

public class ZonaDeCobertura implements MuestraObserver {
    private String nombre;
    private Ubicacion epicentro;
    private double radio;
    private List<Muestra> muestras;
    private List<Organizacion> organizacionesRegistradas;
    private CalculadoraDistancias calculadora;


    public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radioKm, CalculadoraDistancias calculadora) {
        this.nombre = nombre;
        this.epicentro = epicentro;
        this.radio = radioKm;
        this.muestras = new ArrayList<>();
        this.organizacionesRegistradas = new ArrayList<>();
        this.calculadora = calculadora;
    }

    // Getters
    public String getNombre() { return nombre; }
    public Ubicacion getEpicentro() { return epicentro; }
    public double getRadio() { return radio; }
    public List<Muestra> getMuestras() { return new ArrayList<>(muestras); } // Para que no me toquen la lista

    // Principal
    public boolean contiene(Ubicacion ubicacion) {
        return this.epicentro.distanciaA(ubicacion) <= this.radio;
    }

    public boolean seSuperponeCon(ZonaDeCobertura otra) {
        return calculadora.seSuperponenLasZonas(this, otra);
    }

    // Muestras
    
    public void agregarMuestra(Muestra muestra) {
        if (!contieneMuestra(muestra) && this.contiene(muestra.getUbicacion())) {
            muestras.add(muestra);
            muestra.agregarObservador(this);
            notificarNuevaMuestra(muestra);
        }
    }
    
    private boolean contieneMuestra(Muestra muestra) {
        for (Muestra m : muestras) {
            if (m.equals(muestra)) {
                return true;
            }
        }
        return false;
    }

    public void muestraVerificada(Muestra muestra) {
        if (this.contiene(muestra.getUbicacion())) {
            notificarValidacion(muestra);
        }
    }


    // Organizacion
    public void registrarOrganizacion(Organizacion org) {
        if (!organizacionesRegistradas.contains(org)) {
            organizacionesRegistradas.add(org);
        }
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
    