package buscador;

import java.time.LocalDate;

import muestra.Muestra;

public class FiltroFechaCreacion implements Filtro{
	private LocalDate fecha;

    public FiltroFechaCreacion(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean aplica(Muestra muestra) {
        return muestra.getFechaCreacion().isEqual(fecha);
    }
}
