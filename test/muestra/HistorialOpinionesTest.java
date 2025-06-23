package muestra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usuario.Usuario;

class HistorialOpinionesTest {

	private HistorialOpiniones historial;
	private Opinion opinion1, opinion2, opinion3;
	private Usuario usuarioExperto, usuarioBasico;
	
	@BeforeEach
	void setUp() {
		historial = new HistorialOpiniones();
		
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		
		usuarioExperto = mock(Usuario.class);
		usuarioBasico = mock(Usuario.class);
	}
	
	@Test
	void testConstructor() {
		assertTrue(historial.getOpiniones().isEmpty());
	}
	
	@Test
	void testAgregarOpinion() {
		historial.agregarOpinion(opinion1);
		
		assertEquals(1, historial.getOpiniones().size());
		assertTrue(historial.getOpiniones().contains(opinion1));
		
	}
	
	@Test
	void testAgregarOpinionMantieneUnOrden() {
		historial.agregarOpinion(opinion1);
		historial.agregarOpinion(opinion2);
		
		List<Opinion> opiniones = historial.getOpiniones();
		assertEquals(opinion1, opiniones.get(0));
		assertEquals(opinion2, opiniones.get(1));
	}
	
	@Test
	void testUltimaOpinion() {
		historial.agregarOpinion(opinion1);
        historial.agregarOpinion(opinion2);
        
        assertEquals(opinion2, historial.getUltimaOpinion());
	}
	
	@Test
	void testUltimaOpinionSinOpinones() {
		
		assertThrows(IndexOutOfBoundsException.class, () -> historial.getUltimaOpinion());
	}
	
	@Test
	void testCantidadOpinionesDeExperto() {
        when(opinion1.getAutor()).thenReturn(usuarioExperto);
        when(opinion2.getAutor()).thenReturn(usuarioBasico);
        when(opinion3.getAutor()).thenReturn(usuarioExperto);
        
        when(usuarioExperto.esExperto()).thenReturn(true);
        when(usuarioBasico.esExperto()).thenReturn(false);
        
        //Se agregan opiniones
        historial.agregarOpinion(opinion1);
        historial.agregarOpinion(opinion2);
        historial.agregarOpinion(opinion3);
        
        assertEquals(2, historial.cantidadOpinionesExperto());
	}

}
