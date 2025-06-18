package buscador;

import muestra.Muestra;
import muestra.enums.TipoOpinion;

public class FiltroTipoInsecto implements Filtro{
	private TipoOpinion tipoBuscado;

    public FiltroTipoInsecto(TipoOpinion tipoBuscado) {
        this.tipoBuscado = tipoBuscado;
    }

    @Override
    public boolean aplica(Muestra muestra) {
        return muestra.getHistorialOpiniones().getOpiniones().stream()
            .anyMatch(o -> o.getTipo().equals(tipoBuscado));
    }
}
