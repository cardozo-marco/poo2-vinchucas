package usuario.estado;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usuario.Usuario;

class EstadoExpertoTest {

	private EstadoExperto estadoExperto;
	private Usuario usuario;
	
	@BeforeEach
	void setUp() {
		estadoExperto = new EstadoExperto();
		usuario = mock(Usuario.class);
	}

	@Test
	void testEsEspecialista() {
		assertFalse(estadoExperto.esEspecialista());
	}
	
	@Test
	void testEsExperto() {
		assertTrue(estadoExperto.esExperto());
	}
	
	@Test
	void tetsEvaluarPromocionSiCumpleRequisito_MantieneEstadoExperto() {
        when(usuario.cumpleRequisitosDeExperto()).thenReturn(true);
        
        estadoExperto.evaluarPromocion(usuario);
        
        //Se verifica que delegue a usuario la verificacion de requisito para Expertdo
        verify(usuario).cumpleRequisitosDeExperto();
        
        //Se verifica que no se llamo a setEstado
        verify(usuario, never()).setEstado(any());
    }
	
	@Test
	void tetsEvaluarPromocionSiNoCumpleRequisito_CambiaEstadoABasico() {
		when(usuario.cumpleRequisitosDeExperto()).thenReturn(false);
        
		estadoExperto.evaluarPromocion(usuario);
        
        //Se verifica que delegue a usuario la verificacion de requisito para Expertdo
        verify(usuario).cumpleRequisitosDeExperto();
        
        //Se verifica que se llamo a setEstado con una instancia de EstadoBasico
        verify(usuario).setEstado(any(EstadoBasico.class));
	}

}
