package ubicacion;

public interface CalculadoraDistancias {
	Double calcularDistanciaEntreUbicaciones(Ubicacion ubicacion1, Ubicacion ubicacion2);
	Boolean seSuperponenLasZonas(ZonaDeCobertura zona1, ZonaDeCobertura zona2);
	Boolean ubicacionEstaDentroDeZona(Ubicacion ubicacion, ZonaDeCobertura zona);
}
