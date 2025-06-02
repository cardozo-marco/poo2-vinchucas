package vinchucas.tp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OpinionTest {
    
    private Usuario usuario;
    private Muestra muestra;
    
    @BeforeEach
    void setUp() {
        usuario = mock(Usuario.class);
        muestra = mock(Muestra.class);
    }
    
    @Test
    void testCreacionOpinion() {
        Opinion opinion = new Opinion(usuario, TipoOpinion.VINCHUCA_INFESTANS, false, muestra);
        
        assertEquals(usuario, opinion.getAutor());
        assertEquals(muestra, opinion.getMuestra());
        assertEquals(TipoOpinion.VINCHUCA_INFESTANS, opinion.getTipo());
        assertFalse(opinion.esDeExperto());
    }
    
    @Test
    void testNoPermitirAutoVoto() {
        Usuario creador = mock(Usuario.class);
        Muestra muestra = mock(Muestra.class);
        when(muestra.getAutor()).thenReturn(creador);
        
        // Deberia tirar exc
        assertThrows(IllegalStateException.class, () -> 
            new Opinion(creador, TipoOpinion.VINCHUCA_INFESTANS, false, muestra));
    }
    
    @Test
    void testOpinionDeExperto() {
        Opinion opinionExperta = new Opinion(usuario,TipoOpinion.VINCHUCA_INFESTANS, true, muestra);
        assertTrue(opinionExperta.esDeExperto());
    }
}