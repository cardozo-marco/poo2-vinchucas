package muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.enums.Especie;

import usuario.Usuario;
import ubicacion.Ubicacion;
import java.time.LocalDate;


class MuestraTest {
    
    private Muestra muestra;
    private Ubicacion ubicacion;
    private Usuario autor;
    private MuestraObserver observer;
    private muestra.estados.EstadoMuestra estadoInicial;
    private muestra.enums.Especie especie;
    private LocalDate fechaCreacion;
    private String foto;
    
    @BeforeEach
    void setUp() {
        ubicacion = mock(Ubicacion.class);
        autor = mock(Usuario.class);
        observer = mock(MuestraObserver.class);
        estadoInicial = mock(muestra.estados.EstadoMuestra.class);
        especie = Especie.INFESTANS;
        fechaCreacion = LocalDate.now();
        foto = "foto.png";
        muestra = new Muestra(foto, ubicacion, autor, especie, fechaCreacion, estadoInicial);
        muestra.agregarObservador(observer);
    }
    
    @Test
    void testQuitarObservador() {
        muestra.quitarObservador(observer);
        muestra.verificar();
        verify(observer, never()).muestraVerificada(muestra);
    }

    @Test
    void testSetEstado() {
        muestra.estados.EstadoMuestra nuevoEstado = mock(muestra.estados.EstadoMuestra.class);
        when(nuevoEstado.getNombreEstado()).thenReturn("EstadoMock");
        muestra.setEstado(nuevoEstado);
        assertEquals("EstadoMock", muestra.getNombreEstado());
    }

    @Test
    void testGetFotoEspecieYFecha() {
        assertEquals(foto, muestra.getFoto());
        assertEquals(especie, muestra.getEspecieInicial());
        assertEquals(fechaCreacion, muestra.getFechaCreacion());
    }

    @Test
    void testAgregarOpinion() {
        Opinion opinion = mock(Opinion.class);
        // Simular el comportamiento del estado para agregar la opinion al historial
        doAnswer(invocation -> {
            muestra.agregarOpinionAlHistorial(opinion);
            return null;
        }).when(estadoInicial).agregarOpinion(muestra, opinion);

        muestra.agregarOpinion(opinion);
        
        assertEquals(1, muestra.getOpiniones().size());
        assertTrue(muestra.getOpiniones().contains(opinion));
    }
    
    @Test
    void testNoEstaVerificadaInicialmente() {
        when(estadoInicial.esVerificada()).thenReturn(false);
        assertFalse(muestra.estaVerificada());
    }
    
    @Test
    void testVerificarNotificaObservers() {
        muestra.verificar();
        assertTrue(muestra.estaVerificada());
        verify(observer).muestraVerificada(muestra);
    }
    
    @Test
    void testGetUbicacion() {
        assertEquals(ubicacion, muestra.getUbicacion());
    }
    
    @Test
    void testGetFechaUltimaOpinion() {
    	Opinion opinion1 = mock(Opinion.class);
    	Opinion opinion2 = mock(Opinion.class);
    	
    	when(opinion1.getFecha()).thenReturn(LocalDate.of(2023, 12, 25));
    	when(opinion2.getFecha()).thenReturn(LocalDate.of(2025, 11, 25));
    	
    	muestra.agregarOpinionAlHistorial(opinion1);
    	muestra.agregarOpinionAlHistorial(opinion2);
    	
    	LocalDate resultado = muestra.getFechaUltimaVotacion();
    	
    	assertEquals(LocalDate.of(2025, 11, 25), resultado);
    }
    
    @Test
    void testGetFechaUltimaOpinionSinOpiniones() {
    	LocalDate resultado = muestra.getFechaUltimaVotacion();
    	
    	assertEquals(fechaCreacion, resultado);
    }
}