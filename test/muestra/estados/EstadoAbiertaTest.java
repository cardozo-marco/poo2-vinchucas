package muestra.estados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import muestra.Opinion;
import muestra.enums.TipoOpinion;
import usuario.Usuario;

class EstadoAbiertaTest {
	
	private EstadoAbierta estadoAbierta;
	private Usuario usuario1, usuario2, usuario3, usuario4, usuario5;
	private Muestra muestra;
	private Opinion opinion1, opinion2, opinion3, opinion4;
	private List<Opinion> opiniones;
	
	@BeforeEach
	void setUp() {
		estadoAbierta = new EstadoAbierta();
		
		//Muestra
		muestra = mock(Muestra.class);
		
		//Usuarios
		usuario1 = mock(Usuario.class);
		usuario2 = mock(Usuario.class);
		usuario3 = mock(Usuario.class);
		usuario4 = mock(Usuario.class);
		usuario5 = mock(Usuario.class);
		
		//Opiniones
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		opinion4 = mock(Opinion.class);
		
		when(usuario1.esExperto()).thenReturn(false);
		when(usuario2.esExperto()).thenReturn(false);
		when(usuario3.esExperto()).thenReturn(false);
		when(usuario4.esExperto()).thenReturn(false);
		
		when(opinion1.getAutor()).thenReturn(usuario1);
		when(opinion2.getAutor()).thenReturn(usuario2);
		when(opinion3.getAutor()).thenReturn(usuario3);
		when(opinion4.getAutor()).thenReturn(usuario4);
		
		when(opinion1.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
		when(opinion2.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
		when(opinion3.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
		when(opinion4.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
		
		opiniones = new ArrayList<>();
		opiniones.add(opinion1);
		opiniones.add(opinion2);
		opiniones.add(opinion3);
		
		when(muestra.getAutor()).thenReturn(usuario5);
		when(muestra.getOpiniones()).thenReturn(opiniones);
		
	}

	@Test
	void testAgregarOpion() {
		estadoAbierta.agregarOpinion(muestra, opinion4);
		
		//Se verifica que delegue agregar opinion a la muestra
		verify(muestra, times(1)).agregarOpinionAlHistorial(opinion4);
	}
	
	
	@Test
	void testResultadoActual() {
		TipoOpinion resultado = estadoAbierta.getResultadoActual(muestra);
		
		assertEquals(TipoOpinion.IMAGEN_POCO_CLARA, resultado);
	}
	
	@Test
	void testResultadoActualEsEmpate() {
		//Caso Empate
		opiniones.add(opinion4);
		TipoOpinion resultado = estadoAbierta.getResultadoActual(muestra);
		
		assertEquals(TipoOpinion.NINGUNA, resultado);
	}
	
}
