package organizacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import ubicacion.Ubicacion;
import ubicacion.ZonaDeCobertura;

public class OrganizacionTest {

	private Organizacion organizacion;
    private FuncionalidadExterna funcionalidadNuevaMock;
    private FuncionalidadExterna funcionalidadValidacionMock;
    private ZonaDeCobertura zona1, zona2;
    private Muestra muestra1, muestra2, muestra3, muestra4;
    private Ubicacion ubicacion1, ubicacion2, ubicacion3, ubicacion4;

    @BeforeEach
    public void setUp() {
    	Ubicacion ubicacion = mock(Ubicacion.class);
    	
        organizacion = new Organizacion("Hospital Central", ubicacion, TipoOrganizacion.SALUD, 200);
        
        funcionalidadNuevaMock = mock(FuncionalidadExterna.class);
        funcionalidadValidacionMock = mock(FuncionalidadExterna.class);
        
        zona1 = mock(ZonaDeCobertura.class);
        zona2 = mock(ZonaDeCobertura.class);
        
        muestra1 = mock(Muestra.class);
        muestra2 = mock(Muestra.class);
        muestra3 = mock(Muestra.class);
        muestra4 = mock(Muestra.class);
        
        ubicacion1 = mock(Ubicacion.class);
        ubicacion2 = mock(Ubicacion.class);
        ubicacion3 = mock(Ubicacion.class);
        ubicacion4 = mock(Ubicacion.class);
    }

    @Test
    public void testAgregarZona() {
        assertEquals(0, organizacion.getZonas().size());
        organizacion.agregarZona(zona1);
        assertEquals(1, organizacion.getZonas().size());
        assertTrue(organizacion.getZonas().contains(zona1));
    }

    @Test
    public void testNotificarNuevaMuestraEjecutaFuncionalidad() {
        organizacion.setFuncionalidadNuevaMuestra(funcionalidadNuevaMock);

        organizacion.notificarNuevaMuestra(zona1, muestra1);

        verify(funcionalidadNuevaMock).nuevoEvento(organizacion, zona1, muestra1);
    }

    @Test
    public void testNotificarNuevaMuestraSinFuncionalidadNoHaceNada() {
        //no seteo funcionalidad
        assertDoesNotThrow(() -> organizacion.notificarNuevaMuestra(zona1, muestra1));
    }

    @Test
    public void testNotificarValidacionEjecutaFuncionalidad() {
        organizacion.setFuncionalidadValidacion(funcionalidadValidacionMock);

        organizacion.notificarValidacion(zona1, muestra1);

        verify(funcionalidadValidacionMock).nuevoEvento(organizacion, zona1, muestra1);
    }

    @Test
    public void testNotificarValidacionSinFuncionalidadNoHaceNada() {
        //no seteo funcionalidad
        assertDoesNotThrow(() -> organizacion.notificarValidacion(zona1, muestra1));
    }
    
    @Test
    public void testObtenerMuestras() {
    	 when(zona1.getMuestras()).thenReturn(Arrays.asList(muestra1, muestra2));
         when(zona2.getMuestras()).thenReturn(Arrays.asList(muestra2, muestra3));
         
         organizacion.agregarZona(zona1);
         organizacion.agregarZona(zona2);
         
         // Exercise
         List<Muestra> resultado = organizacion.getMuestras();
         
         assertEquals(3, resultado.size());
         assertTrue(resultado.contains(muestra1));
         assertTrue(resultado.contains(muestra2));
         assertTrue(resultado.contains(muestra3));
    }
    
    @Test
    public void testMuestrasCercanas() {
    	
    	when(muestra1.getUbicacion()).thenReturn(ubicacion1);
        when(muestra2.getUbicacion()).thenReturn(ubicacion2);
        when(muestra3.getUbicacion()).thenReturn(ubicacion3);
        
        when(zona1.getMuestras()).thenReturn(Arrays.asList(muestra1, muestra2, muestra3));
        
        when(ubicacion2.distanciaA(ubicacion1)).thenReturn(5d); // dentro de la distancia
        when(ubicacion3.distanciaA(ubicacion1)).thenReturn(15d); // fuera de la distancia
        
        organizacion.agregarZona(zona1);
        
        // Exercise : muestras cercanas a 10km
        List<Muestra> resultado = organizacion.muestrasCercanas(muestra1, 10d);
        
        // Assert
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra2));
        assertFalse(resultado.contains(muestra3));
    }
}
