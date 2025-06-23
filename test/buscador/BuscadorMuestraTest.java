package buscador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import muestra.HistorialOpiniones;
import muestra.Muestra;
import muestra.Opinion;
import muestra.enums.TipoOpinion;

public class BuscadorMuestraTest {

    private BuscadorMuestra buscador;
    private Muestra muestraMock1;
    private Muestra muestraMock2;
    private HistorialOpiniones historialMock;
    private Opinion opinionMock;

    @BeforeEach
    public void setUp() {
        buscador = new BuscadorMuestra();
        muestraMock1 = mock(Muestra.class);
        muestraMock2 = mock(Muestra.class);
        historialMock = mock(HistorialOpiniones.class);
        opinionMock = mock(Opinion.class);
    }

    @Test
    public void testBuscarConFiltroEspecie() {
    	when(opinionMock.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
        when(historialMock.getOpiniones()).thenReturn(List.of(opinionMock));
        when(muestraMock1.getHistorialOpiniones()).thenReturn(historialMock);

        Filtro filtro = new FiltroTipoInsecto(TipoOpinion.VINCHUCA);
        List<Muestra> resultado = buscador.buscar(List.of(muestraMock1), filtro);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestraMock1));
    }
    
    @Test
    public void testFiltroFechaCreacionAplicaCorrectamente() {
        LocalDate fecha = LocalDate.of(2024, 1, 1);
        Muestra muestra = mock(Muestra.class);
        when(muestra.getFechaCreacion()).thenReturn(fecha);

        Filtro filtro = new FiltroFechaCreacion(fecha);
        assertTrue(filtro.aplica(muestra));
    }

    @Test
    public void testFiltroFechaCreacionNoAplicaSiFechaDistinta() {
        Muestra muestra = mock(Muestra.class);
        when(muestra.getFechaCreacion()).thenReturn(LocalDate.of(2024, 1, 2));

        Filtro filtro = new FiltroFechaCreacion(LocalDate.of(2024, 1, 1));
        assertFalse(filtro.aplica(muestra));
    }
    
    @Test
    public void testFiltroFechaUltimaVotacionAplicaCorrectamente() {
        LocalDate fecha = LocalDate.of(2024, 5, 10);
        Muestra muestra = mock(Muestra.class);
        when(muestra.getFechaUltimaVotacion()).thenReturn(fecha);

        Filtro filtro = new FiltroFechaUltimaVotacion(fecha);
        assertTrue(filtro.aplica(muestra));
    }

    @Test
    public void testFiltroFechaUltimaVotacionNoAplicaSiFechaDistinta() {
        Muestra muestra = mock(Muestra.class);
        when(muestra.getFechaUltimaVotacion()).thenReturn(LocalDate.of(2024, 5, 9));

        Filtro filtro = new FiltroFechaUltimaVotacion(LocalDate.of(2024, 5, 10));
        assertFalse(filtro.aplica(muestra));
    }
    
    @Test
    public void testFiltroVerificadaAplicaCuandoMuestraVerificada() {
        Muestra muestra = mock(Muestra.class);
        when(muestra.estaVerificada()).thenReturn(true);

        Filtro filtro = new FiltroVerificada();
        assertTrue(filtro.aplica(muestra));
    }

    @Test
    public void testFiltroVerificadaNoAplicaCuandoNoVerificada() {
        Muestra muestra = mock(Muestra.class);
        when(muestra.estaVerificada()).thenReturn(false);

        Filtro filtro = new FiltroVerificada();
        assertFalse(filtro.aplica(muestra));
    }

    @Test
    public void testBuscarConFiltroCompuestoAND() {
    	when(opinionMock.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
        when(historialMock.getOpiniones()).thenReturn(List.of(opinionMock));
        when(muestraMock1.getHistorialOpiniones()).thenReturn(historialMock);
        when(muestraMock2.getHistorialOpiniones()).thenReturn(historialMock);
        
        when(muestraMock1.getResultadoUltimaOpinion()).thenReturn(TipoOpinion.VINCHUCA);
        when(muestraMock1.estaVerificada()).thenReturn(true);

        when(muestraMock2.getResultadoUltimaOpinion()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
        when(muestraMock2.estaVerificada()).thenReturn(false);

        Filtro filtroEspecie = new FiltroTipoInsecto(TipoOpinion.VINCHUCA);
        Filtro filtroVerificada = new FiltroVerificada();
        Filtro filtroCompuesto = new FiltroAnd(filtroEspecie, filtroVerificada);

        List<Muestra> resultado = buscador.buscar(Arrays.asList(muestraMock1, muestraMock2), filtroCompuesto);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(muestraMock1));
    }

    @Test
    public void testBuscarConFiltroCompuestoOR() {
    	when(opinionMock.getTipo()).thenReturn(TipoOpinion.VINCHUCA);
        when(historialMock.getOpiniones()).thenReturn(List.of(opinionMock));
        when(muestraMock1.getHistorialOpiniones()).thenReturn(historialMock);
        when(muestraMock2.getHistorialOpiniones()).thenReturn(historialMock);
        
        when(muestraMock1.getResultadoUltimaOpinion()).thenReturn(TipoOpinion.VINCHUCA);
        when(muestraMock1.estaVerificada()).thenReturn(false);

        when(muestraMock2.getResultadoUltimaOpinion()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
        when(muestraMock2.estaVerificada()).thenReturn(true);

        Filtro filtroEspecie = new FiltroTipoInsecto(TipoOpinion.VINCHUCA);
        Filtro filtroVerificada = new FiltroVerificada();
        Filtro filtroCompuesto = new FiltroOr(filtroEspecie, filtroVerificada);

        List<Muestra> resultado = buscador.buscar(Arrays.asList(muestraMock1, muestraMock2), filtroCompuesto);

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(muestraMock1));
        assertTrue(resultado.contains(muestraMock2));
    }

    @Test
    public void testBuscarSinCoincidencias() {
        LocalDate fechaFiltro = LocalDate.of(2025, 1, 1);

        when(muestraMock1.getFechaCreacion()).thenReturn(LocalDate.of(2023, 6, 1));
        when(muestraMock2.getFechaCreacion()).thenReturn(LocalDate.of(2023, 6, 10));

        Filtro filtroFecha = new FiltroFechaCreacion(fechaFiltro);

        List<Muestra> resultado = buscador.buscar(Arrays.asList(muestraMock1, muestraMock2), filtroFecha);

        assertTrue(resultado.isEmpty());
    }
}
