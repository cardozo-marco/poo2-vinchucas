package usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import muestra.Opinion;
import muestra.enums.TipoOpinion;
import usuario.estado.EstadoUsuario;

class UsuarioTest {
	
	private Usuario usuario, usuario2;
	private Usuario usuarioEspecialista;
	private Muestra muestra;
	private Opinion opinion;
	private EstadoUsuario estado;
	
	@BeforeEach
	void setUp() {
		usuario = new Usuario("Pedro", "pedro123@gmail.com");
		usuario2 = new Usuario("Alex", "alex123@gmail.com");
		usuarioEspecialista = new Usuario("Juan", "juan123@gmail.com", true);
		muestra = mock(Muestra.class);
		estado = mock(EstadoUsuario.class);
	}

	
	@Test
	void testConstructorBasico() {
		assertEquals("Pedro", usuario.getNombre());
		assertEquals("pedro123@gmail.com", usuario.getEmail());
		assertTrue(usuario.getEnvios().isEmpty());
	    assertTrue(usuario.getRevisiones().isEmpty());
		assertFalse(usuario.esExperto());
		assertFalse(usuario.esEspecialista());
	}
	
	@Test
	void testConstructorUsuarioEspecialista() {
		assertEquals("Juan", usuarioEspecialista.getNombre());
		assertEquals("juan123@gmail.com", usuarioEspecialista.getEmail());
		assertTrue(usuarioEspecialista.getEnvios().isEmpty());
	    assertTrue(usuarioEspecialista.getRevisiones().isEmpty());
		assertTrue(usuarioEspecialista.esExperto());
		assertTrue(usuarioEspecialista.esEspecialista());
	}
	
	@Test
	void testEnviarUnaMuestra() {
		when(muestra.getFechaCreacion()).thenReturn(LocalDate.now());
		
		usuario.enviarMuestra(muestra);
		
		assertEquals(1, usuario.getEnvios().size());;
		assertTrue(usuario.getEnvios().contains(muestra));
	}
	
	@Test
	void testNoSePuedenEnviarMuestrasRepetidas() {
		when(muestra.getFechaCreacion()).thenReturn(LocalDate.now());
		
		usuario.enviarMuestra(muestra);
		
		assertThrows(IllegalStateException.class, () -> usuario.enviarMuestra(muestra));
	}
	
	@Test
	void testOpinarDeUnaMuestra() {
		when(muestra.getAutor()).thenReturn(usuario2);
		
		usuario.opinar(muestra, TipoOpinion.VINCHUCA);
		
		assertEquals(1, usuario.getRevisiones().size());
		verify(muestra).agregarOpinion(any(Opinion.class));
	}
	
	@Test
	void testOpinarSobreMuestraPropia() {
		when(muestra.getAutor()).thenReturn(usuario);
		
		assertThrows(IllegalStateException.class, () -> usuario.opinar(muestra, TipoOpinion.CHINCHE_FOLIADA));
	}
	
	@Test
	void testOpinarDosVecesDeUnaMuestra() {
		when(muestra.getAutor()).thenReturn(usuario2);
		usuario.opinar(muestra, TipoOpinion.VINCHUCA);
		
		assertThrows(IllegalStateException.class, () -> usuario.opinar(muestra, TipoOpinion.CHINCHE_FOLIADA));
	}
	
	@Test
	void testUsuarioCumpleRequisitoDeExperto() {
		// Bucle para agregar muestras enviadas
	    for (int i = 0; i < 11; i++) {
	        muestra = mock(Muestra.class);
	        when(muestra.getFechaCreacion()).thenReturn(LocalDate.now().minusDays(10));
	        usuario.enviarMuestra(muestra);
	    }
	        
	    // Bucle para agregar revisiones de muestras
	    for (int i = 0; i < 21; i++) {
	    	opinion = mock(Opinion.class);
	    	when(opinion.getFecha()).thenReturn(LocalDate.now().minusDays(5));
	        usuario.getRevisiones().add(opinion);
	    }
	    
	    assertTrue(usuario.cumpleRequisitosDeExperto());

	}
	
	@Test
	void testUsuarioNoCumpleRequisitoDeExperto() {
		// Bucle para agregar muestras enviadas
	    for (int i = 0; i < 5; i++) {
	        muestra = mock(Muestra.class);
	        when(muestra.getFechaCreacion()).thenReturn(LocalDate.now().minusDays(10));
	        usuario.enviarMuestra(muestra);
	    }
	        
	    // Bucle para agregar revisiones de muestras
	    for (int i = 0; i < 5; i++) {
	    	opinion = mock(Opinion.class);
	    	when(opinion.getFecha()).thenReturn(LocalDate.now().minusDays(5));
	        usuario.getRevisiones().add(opinion);
	    }
	    
	    assertFalse(usuario.cumpleRequisitosDeExperto());
	}
	
	@Test
	void testAlEnviarUnaMuestraSeEvaluaPromocion() {
		usuario.setEstado(estado);
		when(muestra.getFechaCreacion()).thenReturn(LocalDate.now());
		
		usuario.enviarMuestra(muestra);
		
		verify(estado).evaluarPromocion(usuario);
	}
	
	@Test
	void testAlEnviarUnaOpinionSeEvaluaPromocion() {
		usuario.setEstado(estado);
		when(muestra.getAutor()).thenReturn(usuario2);
		
		usuario.opinar(muestra, TipoOpinion.CHINCHE_FOLIADA);
		
		verify(estado).evaluarPromocion(usuario);
	}
	
	@Test
	void testCantidadMuestrasRecientes() {
	    Muestra muestraReciente = mock(Muestra.class);
	    Muestra muestraAntigua = mock(Muestra.class);
	    when(muestraReciente.getFechaCreacion()).thenReturn(LocalDate.now().minusDays(10));
	    when(muestraAntigua.getFechaCreacion()).thenReturn(LocalDate.now().minusDays(60)); //Muestra fuera de la fecha limite
	    
	    usuario.enviarMuestra(muestraReciente);
	    usuario.enviarMuestra(muestraAntigua);
	    
	    long cantidadMuestrasRecientes = usuario.cantidadMuestrasRecientes(LocalDate.now().minusDays(30));
	    
	    assertEquals(1, cantidadMuestrasRecientes);
	}
	
	@Test
	void testCantidadRevisionesRecientes() {
	    Opinion opinionReciente = mock(Opinion.class);
	    Opinion opinionAntigua = mock(Opinion.class);
	    
	    when(opinionReciente.getFecha()).thenReturn(LocalDate.now().minusDays(10));
	    when(opinionAntigua.getFecha()).thenReturn(LocalDate.now().minusDays(50)); //Revision fuera de la fecha limite
	    
	    usuario.getRevisiones().add(opinionReciente);
	    usuario.getRevisiones().add(opinionAntigua);
	    
	    long cantidadRevisionesRecientes = usuario.cantidadRevisionesRecientes(LocalDate.now().minusDays(30));
	    
	    assertEquals(1, cantidadRevisionesRecientes);
	}

	

}
