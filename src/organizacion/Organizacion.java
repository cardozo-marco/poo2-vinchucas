package organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import muestra.Muestra;
import ubicacion.Ubicacion;
import ubicacion.ZonaDeCobertura;

public class Organizacion implements ZonaObserver {
    private String nombre;
    private Ubicacion ubicacion;
    private TipoOrganizacion tipo;
    private int cantidadEmpleados;
    private List<ZonaDeCobertura> zonas;
    private FuncionalidadExterna funcionalidadNuevaMuestra;
    private FuncionalidadExterna funcionalidadValidacion;

    public Organizacion(String nombre, Ubicacion ubicacion, TipoOrganizacion tipo, int cantidadEmpleados) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.cantidadEmpleados = cantidadEmpleados;
        this.zonas = new ArrayList<>();
    }

    public void agregarZona(ZonaDeCobertura zona) {
        this.zonas.add(zona);
    }

    public void setFuncionalidadNuevaMuestra(FuncionalidadExterna funcionalidad) {
        this.funcionalidadNuevaMuestra = funcionalidad;
    }

    public void setFuncionalidadValidacion(FuncionalidadExterna funcionalidad) {
        this.funcionalidadValidacion = funcionalidad;
    }

    @Override
    public void notificarNuevaMuestra(ZonaDeCobertura zona, Muestra muestra) {
        if (funcionalidadNuevaMuestra != null) {
            funcionalidadNuevaMuestra.nuevoEvento(this, zona, muestra);
        }
    }

    @Override
    public void notificarValidacion(ZonaDeCobertura zona, Muestra muestra) {
        if (funcionalidadValidacion != null) {
            funcionalidadValidacion.nuevoEvento(this, zona, muestra);
        }
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public Ubicacion getUbicacion() { return ubicacion; }
    public TipoOrganizacion getTipo() { return tipo; }
    public int getCantidadEmpleados() { return cantidadEmpleados; }
    public List<ZonaDeCobertura> getZonas() { return zonas; }
    
    
    //Obtener todas las muestras existentes
    public List<Muestra> obtenerMuestras() {
    	return this.zonas.stream()
    			   .flatMap(zona -> zona.getMuestras().stream())
    			   .distinct()
    			   .collect(Collectors.toList());
    }
    
    //Dado una muestra, conocer todas las muestras obtenidas a menos de x kilómetros.
    public List<Muestra> muestrasCercanas(Muestra muestraObjetivo, double distanciaEnKm) {
    	return this.obtenerMuestras().stream()
    			.filter(muestra -> !muestra.equals(muestraObjetivo))
    			.filter(muestra -> muestra.getUbicacion().distanciaA(muestraObjetivo.getUbicacion()) <= distanciaEnKm)
				.collect(Collectors.toList());
    }
}