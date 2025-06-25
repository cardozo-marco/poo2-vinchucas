package ubicacion;

import java.util.List;
import java.util.stream.Collectors;

public class Ubicacion {
    private double latitud;
    private double longitud;
    private CalculadoraDistancias calculadora;
	
	public Ubicacion(Double latitud, Double longitud, CalculadoraDistancias calculadora) {
		this.setLatitud(latitud);
		this.setLongitud(longitud);
		this.calculadora = calculadora;
	}

	public Double getLatitud() {
		return latitud;
	}

	private void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	private void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public double distanciaA(Ubicacion otra) {
	    return calculadora.calcularDistanciaEntreUbicaciones(this, otra);
	
	}
	
	//A partir de una lista de ubicaciones, retorna aquellas que se encuentran a menos de x kilómetros
	public List<Ubicacion> ubicacionesCercanas(List<Ubicacion> ubicaciones, double distanciaMax) {
		return ubicaciones.stream()
				.filter(ubicacion -> this.distanciaA(ubicacion) <= distanciaMax)
				.collect(Collectors.toList());
	}
}
