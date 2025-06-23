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

class EstadoBasicoTest {

	private EstadoBasico estadoBasico;
	private Usuario usuario;
	
	@BeforeEach
	void setUp() {
		estadoBasico = new EstadoBasico();
		usuario = mock(Usuario.class);
	}

	@Test
	void testEsEspecialista() {
		assertFalse(estadoBasico.esEspecialista());
	}
	
	@Test
	void testEsExperto() {
		assertFalse(estadoBasico.esExperto());
	}
	
	@Test
	void tetsEvaluarPromocionSiCumpleRequisito_CambiaAEstadoExperto() {
        when(usuario.cumpleRequisitosDeExperto()).thenReturn(true);
        
        estadoBasico.evaluarPromocion(usuario);
        
        //Se verifica que delegue a usuario la verificacion de requisito para Expertdo
        verify(usuario).cumpleRequisitosDeExperto();
        
        //Se verifica que se llamo a setEstado con una instancia de EstadoExperto
        verify(usuario).setEstado(any(EstadoExperto.class));
    }
	
	@Test
	void tetsEvaluarPromocionSiNoCumpleRequisito_NoCambiaEstado() {
		when(usuario.cumpleRequisitosDeExperto()).thenReturn(false);
        
        estadoBasico.evaluarPromocion(usuario);
        
        //Se verifica que delegue a usuario la verificacion de requisito para Expertdo
        verify(usuario).cumpleRequisitosDeExperto();
        
        //Se verifica que no se llamo a setEstado
        verify(usuario, never()).setEstado(any());
	}
}
