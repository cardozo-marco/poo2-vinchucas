package buscador;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroFechaUltimaVotacion implements Filtro{
	private LocalDate fecha;

    public FiltroFechaUltimaVotacion(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean aplica(Muestra muestra) {
        return muestra.getFechaUltimaVotacion().isEqual(fecha);
    }
}
