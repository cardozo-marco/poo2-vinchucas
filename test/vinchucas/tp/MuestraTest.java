package vinchucas.tp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MuestraTest {
    
    private Muestra muestra;
    private Ubicacion ubicacion;
    private Usuario autor;
    private MuestraObserver observer;
    
    @BeforeEach
    void setUp() {
        ubicacion = mock(Ubicacion.class);
        muestra = new Muestra(ubicacion, autor);
        observer = mock(MuestraObserver.class);
        muestra.agregarObservador(observer);
    }
    
    @Test
    void testAgregarOpinion() {
        Opinion opinion = mock(Opinion.class);
        muestra.agregarOpinion(opinion);
        
        assertEquals(1, muestra.getOpiniones().size());
        assertTrue(muestra.getOpiniones().contains(opinion));
    }
    
    @Test
    void testNoEstaVerificadaInicialmente() {
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
}