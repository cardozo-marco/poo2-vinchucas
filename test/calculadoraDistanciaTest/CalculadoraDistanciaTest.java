package calculadoraDistanciaTest;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calculadoraDistancia.CalculadoraDistancia;
import vinchucas.tp.Ubicacion;
import vinchucas.tp.ZonaDeCobertura;

class CalculadoraDistanciaTest {

	private Ubicacion unq, obelisco, aeropuertoBariloche, estacionDeQuilmes;
	private ZonaDeCobertura zonaObelisco, zonaQuilmes, zonaBariloche;
	
	@BeforeEach
	public void setUp() {
		
		//Doc
		unq = mock(Ubicacion.class); 
		when(unq.getLatitud()).thenReturn(-34.70628551389169);
		when(unq.getLongitud()).thenReturn(-58.27836721576365);
		
		obelisco = mock(Ubicacion.class); 
		when(obelisco.getLatitud()).thenReturn(-34.6035005619795);
		when(obelisco.getLongitud()).thenReturn(-58.38157001576607);
		
		aeropuertoBariloche = mock(Ubicacion.class); 
		when(aeropuertoBariloche.getLatitud()).thenReturn(-41.145731174194765);
		when(aeropuertoBariloche.getLongitud()).thenReturn(-71.16158813075121);
		
		estacionDeQuilmes = mock(Ubicacion.class);
		when(estacionDeQuilmes.getLatitud()).thenReturn(-34.72424658113071);
		when(estacionDeQuilmes.getLongitud()).thenReturn(-58.26082765206043);
		
		zonaObelisco = mock(ZonaDeCobertura.class); 
		when(zonaObelisco.getEpicentro()).thenReturn(obelisco);
		when(zonaObelisco.getRadio()).thenReturn(8d);
		
		zonaQuilmes = mock(ZonaDeCobertura.class); 
		when(zonaQuilmes.getEpicentro()).thenReturn(unq);
		when(zonaQuilmes.getRadio()).thenReturn(8d);
		
		zonaBariloche = mock(ZonaDeCobertura.class); 
		when(zonaBariloche.getEpicentro()).thenReturn(aeropuertoBariloche);
		when(zonaBariloche.getRadio()).thenReturn(500d);
		
		
	}
	
	@Test
	void testCalcularDistanciaEntreDosUbicaciones() {
		
		//Exercise
		Double distanciaDeUnqAObelisco = CalculadoraDistancia.calcularDistanciaEntreUbicaciones(unq, obelisco);
		Double distanciaDeObeliscoAUnq = CalculadoraDistancia.calcularDistanciaEntreUbicaciones(obelisco, unq);
		Double distanciaDeObeliscoAAeropuestoBariloche = CalculadoraDistancia.calcularDistanciaEntreUbicaciones(obelisco, aeropuertoBariloche);
		
		//Verify
		assertEquals(distanciaDeUnqAObelisco, 14.82d, 0.01);
		assertEquals(distanciaDeObeliscoAUnq, 14.82d, 0.01);
		assertEquals(distanciaDeObeliscoAAeropuestoBariloche, 1334.79d, 0.01);
	}
	
	@Test
	void testSuperposicionDeZonas() {
		//Exercise
		Boolean superponeZonaQuilmesZonaObelisco = CalculadoraDistancia.seSuperponenLasZonas(zonaQuilmes, zonaObelisco);
		Boolean superponeZonaQuilmesZonaBariloche = CalculadoraDistancia.seSuperponenLasZonas(zonaQuilmes, zonaBariloche);
		Boolean superponeZonaBarilocheZonaObelisco  = CalculadoraDistancia.seSuperponenLasZonas(zonaBariloche, zonaObelisco);
		
		//Verify
		assertEquals(superponeZonaQuilmesZonaObelisco, true);
		assertEquals(superponeZonaQuilmesZonaBariloche, false);
		assertEquals(superponeZonaBarilocheZonaObelisco, false);
		
	}
	
	@Test
	void testSuperposicionDeUnaMismaZona() {
		//Exercise
		Boolean superponeZonaQuilmesZonaQuilmes = CalculadoraDistancia.seSuperponenLasZonas(zonaQuilmes, zonaQuilmes);
		
		//Verify
		assertEquals(superponeZonaQuilmesZonaQuilmes, true);
	}
	
	@Test
	void testUbicacionSeEncuentraDentroDeZona() {
		//Exercise
		Boolean estacionQuilmesZonaQuilmes = CalculadoraDistancia.ubicacionEstaDentroDeZona(estacionDeQuilmes, zonaQuilmes);
		Boolean unqZonaBariloche = CalculadoraDistancia.ubicacionEstaDentroDeZona(unq, zonaBariloche);
		Boolean obeliscoZonaQuilmes = CalculadoraDistancia.ubicacionEstaDentroDeZona(obelisco, zonaQuilmes);
		
		//Verify
		assertEquals(estacionQuilmesZonaQuilmes, true);
		assertEquals(unqZonaBariloche, false);
		assertEquals(obeliscoZonaQuilmes, false);
		
	}

}
