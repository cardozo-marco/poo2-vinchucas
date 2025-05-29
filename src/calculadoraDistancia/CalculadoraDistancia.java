package calculadoraDistancia;
import vinchucas.tp.Ubicacion;
import vinchucas.tp.ZonaDeCobertura;

public class CalculadoraDistancia {

	private static Double radioTierra = 6371.01;
	
	
	/*
	 * Calcula la distancia aproximada (tolerancia de 10 metros) entre dos ubicaciones de la superficie terrestre.
	 * Se utiliza la formula de Haversine, con el radio promedio 6371.01.
	 * @return La distancia entre dos ubicaciones, en kilometros.
	 */
	public static Double calcularDistanciaEntreUbicaciones(Ubicacion ubicacion1, Ubicacion ubicacion2) {
		
		Double latitudUbicacion1 = ubicacion1.getLatitud();
		Double longitudUbicacion1 = ubicacion1.getLongitud();
		Double latitudUbicacion2 = ubicacion2.getLatitud();
		Double longitudUbicacion2 = ubicacion2.getLongitud();
		
		
		Double haversine, distancia;
		Double dLatitud, dLongitud;
		Double DegRad = 0.01745329251994;
		
		dLatitud = (latitudUbicacion2 - latitudUbicacion1) * DegRad;
		dLongitud = (longitudUbicacion2 - longitudUbicacion1) * DegRad;
		
		haversine = Math.sin(dLatitud * 0.5) * Math.sin(dLatitud * 0.5) +
				Math.sin(dLongitud * 0.5) * Math.sin(dLongitud * 0.5) *
				Math.cos(latitudUbicacion1 * DegRad) *
				Math.cos(latitudUbicacion2 * DegRad);
		
		distancia = Math.asin(Math.sqrt(haversine)) * radioTierra * 2.0;
		
		return distancia;
		
		
	}
	
	public static Boolean seSuperponenLasZonas(ZonaDeCobertura zona1, ZonaDeCobertura zona2) {
	
		//Se calcula la distancia entre los dos epicentros.
		Double distancia = calcularDistanciaEntreUbicaciones(zona1.getEpicentro(), zona2.getEpicentro());
		
		// Si la distancia entre los dos epicentros es menor o igual a la suma de los radios, entonces las zonas se superponen.
		return distancia <= (zona1.getRadio() + zona2.getRadio());
	}
	
	
	public static Boolean ubicacionEstaDentroDeZona(Ubicacion ubicacion, ZonaDeCobertura zona) {
		
		//Se calcula la distancia entre el epicentro y la ubicacion.
		Double distancia = calcularDistanciaEntreUbicaciones(zona.getEpicentro(), ubicacion);
		
		// Si la distancia entre el epicentro y la ubicacion es menor o igual al radio de la zonaDeCobertura, entonces la ubicacion se encuentra dentro de la zona.
		return distancia <= (zona.getRadio());
	}
}
