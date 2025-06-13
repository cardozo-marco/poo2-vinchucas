package buscador;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroFechaCreacionMuestra implements Filtro{
	private LocalDate fecha;

    public FiltroFechaCreacionMuestra(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean aplica(Muestra muestra) {
        return muestra.getFechaCreacion().isEqual(fecha);
    }
}
