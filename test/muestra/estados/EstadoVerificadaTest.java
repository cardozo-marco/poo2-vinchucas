package muestra.estados;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import muestra.Opinion;
import muestra.enums.TipoOpinion;
import usuario.Usuario;

class EstadoVerificadaTest {
	
	private EstadoVerificada estadoVerificada;
	private Usuario usuarioExperto1, usuarioExperto2;
	private Muestra muestra;
	private Opinion opinion1;
	
	@BeforeEach
	void setUp() {
		estadoVerificada = new EstadoVerificada();
		
		//Muestra
		muestra = mock(Muestra.class);
		
		//Usuarios
		usuarioExperto1 = mock(Usuario.class);
		usuarioExperto2 = mock(Usuario.class);
		
		//Opiniones
		opinion1 = mock(Opinion.class);
		
		when(usuarioExperto1.esExperto()).thenReturn(true);
		when(usuarioExperto2.esExperto()).thenReturn(true);
		
		when(opinion1.getAutor()).thenReturn(usuarioExperto1);
		
		when(opinion1.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);

		when(muestra.getAutor()).thenReturn(usuarioExperto2);
	}

	@Test
	void testResultadoActual() {
		estadoVerificada.getResultadoActual(muestra);
		
		verify(muestra, times(1)).getResultadoVerificado();
	}
	
	@Test
	void testNoSePuedeAgregarOpinion() {
		assertThrows(IllegalStateException.class, () -> 
		estadoVerificada.agregarOpinion(muestra, opinion1));
	}

}
