package muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.enums.TipoOpinion;
import usuario.Usuario;
import java.time.LocalDate;


class OpinionTest {
    
    private Usuario usuario;
    private Muestra muestra;
    private LocalDate fecha;
    
    @BeforeEach
    void setUp() {
        usuario = mock(Usuario.class);
        muestra = mock(Muestra.class);
        fecha = LocalDate.now();
    }
    
    @Test
    void testCreacionOpinion() {
        Opinion opinion = new Opinion(usuario, TipoOpinion.VINCHUCA, false, muestra, fecha);
        
        assertEquals(usuario, opinion.getAutor());
        assertEquals(muestra, opinion.getMuestra());
        assertEquals(TipoOpinion.VINCHUCA, opinion.getTipo());
        assertFalse(opinion.esDeExperto());
        assertEquals(fecha, opinion.getFecha());
    }
    
    @Test
    void testNoPermitirAutoVoto() {
        Usuario creador = mock(Usuario.class);
        Muestra muestra = mock(Muestra.class);
        when(muestra.getAutor()).thenReturn(creador);
        
        // Deberia tirar exc
        assertThrows(IllegalStateException.class, () -> 
            new Opinion(creador, TipoOpinion.VINCHUCA, false, muestra, fecha));
    }
    
    @Test
    void testOpinionDeExperto() {
        Opinion opinionExperta = new Opinion(usuario,TipoOpinion.VINCHUCA, true, muestra, fecha);
        assertTrue(opinionExperta.esDeExperto());
    }
}