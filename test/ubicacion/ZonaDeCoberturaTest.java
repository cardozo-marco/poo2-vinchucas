package ubicacion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import muestra.enums.Especie;
import muestra.estados.EstadoMuestra;
import organizacion.Organizacion;
import usuario.Usuario;


class ZonaDeCoberturaTest {
    
    private ZonaDeCobertura zona;
    private Ubicacion epicentro;
    private Muestra muestraDentro, muestraFuera;
    private Usuario autor;
    private Organizacion organizacion;
    
    @BeforeEach
    void setUp() {
        epicentro = mock(Ubicacion.class);
        zona = new ZonaDeCobertura("Zona Test", epicentro, 10.0);
        
        Ubicacion ubicacionDentro = mock(Ubicacion.class);
        Ubicacion ubicacionFuera = mock(Ubicacion.class);
        
        when(epicentro.distanciaA(ubicacionDentro)).thenReturn(5.0);
        when(epicentro.distanciaA(ubicacionFuera)).thenReturn(15.0);
        
        muestraDentro = new Muestra(
            "fotoDentro.png",
            ubicacionDentro,
            autor,
            Especie.INFESTANS,
            java.time.LocalDate.now(),
            mock(EstadoMuestra.class)
        );
        muestraFuera = new Muestra(
            "fotoFuera.png",
            ubicacionFuera,
            autor,
            Especie.INFESTANS,
            java.time.LocalDate.now(),
            mock(EstadoMuestra.class)
        );
        
        organizacion = mock(Organizacion.class);
    }
    
    @Test
    void testAgregarMuestraDentroDeZona() {
        zona.agregarMuestra(muestraDentro);
        
        assertTrue(zona.getMuestras().contains(muestraDentro));
    }
    
    @Test
    void testNoAgregarMuestraFueraDeZona() {
        zona.agregarMuestra(muestraFuera);
        
        assertFalse(zona.getMuestras().contains(muestraFuera));
    }
    
    @Test
    void testNotificarNuevaMuestra() {
        zona.registrarOrganizacion(organizacion);
        zona.agregarMuestra(muestraDentro);
        
        verify(organizacion).notificarNuevaMuestra(zona, muestraDentro);
    }
    
    @Test
    void testAgregarMismaMuestraDosVeces() {
        zona.agregarMuestra(muestraDentro);
        zona.agregarMuestra(muestraDentro);
        assertEquals(1, zona.getMuestras().stream().filter(m -> m == muestraDentro).count());
    }

    @Test
    void testNoNotificarOrganizacionDesregistrada() {
        zona.registrarOrganizacion(organizacion);
        zona.desregistrarOrganizacion(organizacion);
        zona.agregarMuestra(muestraDentro);
        muestraDentro.verificar();
        verify(organizacion, never()).notificarValidacion(any(), any());
        verify(organizacion, never()).notificarNuevaMuestra(any(), any());
    }

    @Test
    void testNotificarValidacion() {
        zona.registrarOrganizacion(organizacion);
        zona.agregarMuestra(muestraDentro);
        
        // Cuando se verifica se debe notificar
        muestraDentro.verificar();
        
        verify(organizacion).notificarValidacion(zona, muestraDentro);
    }
    
    @Test
    void testNoNotificarSiMuestraNoEstaEnZona() {
        zona.registrarOrganizacion(organizacion);
        muestraFuera.verificar();
        
        verify(organizacion, never()).notificarValidacion(any(), any());
    }
    
    @Test
    void testRegistrarYDesregistrarOrganizacion() {
        zona.registrarOrganizacion(organizacion);
        assertTrue(zona.getMuestras().isEmpty()); // Para ver si puedo
        
        zona.desregistrarOrganizacion(organizacion);
        // Como se que se deregistro ?? igual no deberia poder acceder
    }
}