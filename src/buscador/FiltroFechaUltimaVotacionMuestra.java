package buscador;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroFechaUltimaVotacionMuestra implements Filtro{
	private LocalDate fecha;

    public FiltroFechaUltimaVotacionMuestra(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean aplica(Muestra muestra) {
        return muestra.getFechaUltimaVotacion().isEqual(fecha);
    }
}
