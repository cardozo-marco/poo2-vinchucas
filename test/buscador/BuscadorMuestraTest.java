package buscador;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import muestra.enums.Especie;
import muestra.enums.TipoOpinion;
import muestra.estados.EstadoAbierta;
import ubicacion.Ubicacion;
import usuario.Usuario;

class BuscadorMuestraTest {

	private BuscadorMuestra buscador;
    private Muestra muestra1;
    private Muestra muestra2;

    @BeforeEach
    public void setUp() {
        buscador = new BuscadorMuestra();

        muestra1 = new Muestra("foto1", new Ubicacion(0.0, 0.0), new Usuario("user1", "frank"), Especie.INFESTANS, LocalDate.of(2023, 6, 1), new EstadoAbierta());
        muestra2 = new Muestra("foto2", new Ubicacion(1.1, 1.1), new Usuario("user2", "franks"), Especie.GUASAYANA, LocalDate.of(2023, 6, 10), new EstadoAbierta());
    }

    @Test
    public void testBuscarConFiltroEspecie() {
        Filtro filtroEspecie = new FiltroTipoInsecto(TipoOpinion.VINCHUCA);

        List<Muestra> resultado = buscador.buscar(Arrays.asList(muestra1, muestra2), filtroEspecie);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra1));
    }

    @Test
    public void testBuscarConFiltroCompuestoAND() {
        muestra1.verificar();

        Filtro filtroEspecie = new FiltroTipoInsecto(TipoOpinion.VINCHUCA);
        Filtro filtroVerificada = new FiltroVerificada();

        Filtro filtroCompuesto = new FiltroAnd(filtroEspecie, filtroVerificada);

        List<Muestra> resultado = buscador.buscar(Arrays.asList(muestra1, muestra2), filtroCompuesto);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestra1));
    }

    @Test
    public void testBuscarSinCoincidencias() {
        Filtro filtroFecha = new FiltroFechaCreacion(LocalDate.of(2025, 1, 1)); //ninguna muestra tiene esa fecha

        List<Muestra> resultado = buscador.buscar(Arrays.asList(muestra1, muestra2), filtroFecha);

        assertTrue(resultado.isEmpty());
    }

}
