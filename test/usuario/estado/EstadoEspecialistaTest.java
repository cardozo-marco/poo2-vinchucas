package usuario.estado;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usuario.Usuario;

class EstadoEspecialistaTest {

	private EstadoEspecialista estadoEspecialista;
	private Usuario usuario;
	
	@BeforeEach
	void setUp() {
		estadoEspecialista = new EstadoEspecialista();
		usuario = mock(Usuario.class);
	}

	@Test
	void testEsEspecialista() {
		assertTrue(estadoEspecialista.esEspecialista());
	}
	
	@Test
	void testEsExperto() {
		assertTrue(estadoEspecialista.esExperto());
	}
	
	@Test
	void testEvaluarPromocion_NuncaCambiaDeEstado() {
		estadoEspecialista.evaluarPromocion(usuario);
		
		//Se verifica que no se interactue con el usuario
        verifyNoInteractions(usuario);
	}

}
