package ubicacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UbicacionTest {
	
	private Ubicacion ubicacion1, ubicacion2, ubicacion3;
	private CalculadoraDistancias calculadora;
	
	@BeforeEach
	void setUp() {
		calculadora = mock(CalculadoraDistancias.class);
		ubicacion1 = new Ubicacion(-34.603722, -58.381592, calculadora);
		ubicacion2 = new Ubicacion(-34.921450, -57.954536, calculadora);
		ubicacion3 = new Ubicacion(-34.921450, -57.954536, calculadora);
	}

    @Test
    void testCrearUbicacionValida() {
        
        assertEquals(-34.603722, ubicacion1.getLatitud());
        assertEquals(-58.381592, ubicacion1.getLongitud());
    }
    
    @Test
    void testDistanciaAUbicacion() {
    	
        assertDoesNotThrow(() -> ubicacion1.distanciaA(ubicacion2));
        verify(calculadora).calcularDistanciaEntreUbicaciones(ubicacion1, ubicacion2);
    }
    
    @Test
    void testUbicacionesCercanas() {
    	List<Ubicacion> ubicaciones = Arrays.asList(ubicacion2, ubicacion3);
    	
    	when(calculadora.calcularDistanciaEntreUbicaciones(ubicacion1, ubicacion2)).thenReturn(12d);
    	when(calculadora.calcularDistanciaEntreUbicaciones(ubicacion1, ubicacion3)).thenReturn(50d);
    	
    	List<Ubicacion> ubicacionesCercanas = ubicacion1.ubicacionesCercanas(ubicaciones, 20d);
    	
    	assertTrue(ubicacionesCercanas.contains(ubicacion2));
    	assertFalse(ubicacionesCercanas.contains(ubicacion3));
    	
    }
    
}