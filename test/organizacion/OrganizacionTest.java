package organizacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import ubicacion.Ubicacion;
import ubicacion.ZonaDeCobertura;

public class OrganizacionTest {

	private Organizacion organizacion;
    private FuncionalidadExterna funcionalidadNuevaMock;
    private FuncionalidadExterna funcionalidadValidacionMock;
    private ZonaDeCobertura zonaMock;
    private Muestra muestraMock;

    @BeforeEach
    public void setUp() {
        organizacion = new Organizacion("Hospital Central", new Ubicacion(10.0, 20.0), TipoOrganizacion.SALUD, 200);
        funcionalidadNuevaMock = mock(FuncionalidadExterna.class);
        funcionalidadValidacionMock = mock(FuncionalidadExterna.class);
        zonaMock = mock(ZonaDeCobertura.class);
        muestraMock = mock(Muestra.class);
    }

    @Test
    public void testAgregarZona() {
        assertEquals(0, organizacion.getZonas().size());
        organizacion.agregarZona(zonaMock);
        assertEquals(1, organizacion.getZonas().size());
        assertTrue(organizacion.getZonas().contains(zonaMock));
    }

    @Test
    public void testNotificarNuevaMuestraEjecutaFuncionalidad() {
        organizacion.setFuncionalidadNuevaMuestra(funcionalidadNuevaMock);

        organizacion.notificarNuevaMuestra(zonaMock, muestraMock);

        verify(funcionalidadNuevaMock).nuevoEvento(organizacion, zonaMock, muestraMock);
    }

    @Test
    public void testNotificarNuevaMuestraSinFuncionalidadNoHaceNada() {
        //no seteo funcionalidad
        assertDoesNotThrow(() -> organizacion.notificarNuevaMuestra(zonaMock, muestraMock));
    }

    @Test
    public void testNotificarValidacionEjecutaFuncionalidad() {
        organizacion.setFuncionalidadValidacion(funcionalidadValidacionMock);

        organizacion.notificarValidacion(zonaMock, muestraMock);

        verify(funcionalidadValidacionMock).nuevoEvento(organizacion, zonaMock, muestraMock);
    }

    @Test
    public void testNotificarValidacionSinFuncionalidadNoHaceNada() {
        //no seteo funcionalidad
        assertDoesNotThrow(() -> organizacion.notificarValidacion(zonaMock, muestraMock));
    }
}
