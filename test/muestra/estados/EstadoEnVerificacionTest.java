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

class EstadoEnVerificacionTest {
	
	private EstadoEnVerificacion estadoEnVerificacion;
	private Usuario usuario1, usuarioExperto1, usuarioExperto2, usuarioExperto3, usuarioExperto4;
	private Muestra muestra;
	private Opinion opinion1, opinion2, opinion3, opinion4;
	private List<Opinion> opiniones;
	
	@BeforeEach
	void setUp() {
		estadoEnVerificacion = new EstadoEnVerificacion();
		
		//Muestra
		muestra = mock(Muestra.class);
		
		//Usuarios
		usuario1 = mock(Usuario.class);
		usuarioExperto1 = mock(Usuario.class);
		usuarioExperto2 = mock(Usuario.class);
		usuarioExperto3 = mock(Usuario.class);
		usuarioExperto4 = mock(Usuario.class);
		
		//Opiniones
		opinion1 = mock(Opinion.class);
		opinion2 = mock(Opinion.class);
		opinion3 = mock(Opinion.class);
		opinion4 = mock(Opinion.class);
		
		when(usuario1.esExperto()).thenReturn(false);
		when(usuarioExperto1.esExperto()).thenReturn(true);
		when(usuarioExperto2.esExperto()).thenReturn(true);
		when(usuarioExperto3.esExperto()).thenReturn(true);
		
		when(opinion1.getAutor()).thenReturn(usuario1);
		when(opinion2.getAutor()).thenReturn(usuarioExperto1);
		when(opinion3.getAutor()).thenReturn(usuarioExperto2);
		when(opinion4.getAutor()).thenReturn(usuarioExperto3);
		
		when(opinion1.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
		when(opinion2.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
		when(opinion3.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
		when(opinion4.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
		
		opiniones = new ArrayList<>();
		opiniones.add(opinion2);
		
		TipoOpinion ultimoResultado = opiniones.get(opiniones.size() - 1).getTipo();
		
		when(muestra.getAutor()).thenReturn(usuarioExperto4);
		when(muestra.getOpiniones()).thenReturn(opiniones);
		when(muestra.getResultadoUltimaOpinion()).thenReturn(ultimoResultado);
	}

	@Test
	void testAgregarOpinionDeExperto() {
		estadoEnVerificacion.agregarOpinion(muestra, opinion3);
		
		//Se verifica que delegue agregar opinion a la muestra.
		verify(muestra, times(1)).agregarOpinionAlHistorial(opinion3);
	}
	
	@Test
	void testAgregarOpinionDeUsuarioBasico() {
		//Al intentar agregar la opion de un usuario no experto, lanza excepcion
		assertThrows(IllegalArgumentException.class, () -> 
		estadoEnVerificacion.agregarOpinion(muestra, opinion1));
	}
	
	@Test
	void testResultadoActual() {
		//Se simula que hay 1 sola opinion de experto
		when(muestra.cantidadOpinionesExperto()).thenReturn(1);
		
		assertEquals(TipoOpinion.VINCHUCA, estadoEnVerificacion.getResultadoActual(muestra));
	}
	
	@Test
	void testResultadoActualCuandoHayMasDeUnExperto() {
		//Se simula que hay 2 opiniones de experto
		when(muestra.cantidadOpinionesExperto()).thenReturn(2);
		
		assertEquals(TipoOpinion.NINGUNA, estadoEnVerificacion.getResultadoActual(muestra));
	}
	
	@Test
	void testHayCoincidenciasDeExpertos() {
		assertTrue(estadoEnVerificacion.hayCoincidenciaDeExpertos(muestra, TipoOpinion.VINCHUCA));
	}
	
	@Test
	void testNoHayCoincidenciasDeExpertos() {
		assertFalse(estadoEnVerificacion.hayCoincidenciaDeExpertos(muestra, TipoOpinion.CHINCHE_FOLIADA));
	}

}
