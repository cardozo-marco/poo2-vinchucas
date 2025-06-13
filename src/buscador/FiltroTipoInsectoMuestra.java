package buscador;

import muestra.Muestra;
import muestra.enums.TipoOpinion;

public class FiltroTipoInsectoMuestra implements Filtro{
	private TipoOpinion tipoBuscado;

    public FiltroTipoInsectoMuestra(TipoOpinion tipoBuscado) {
        this.tipoBuscado = tipoBuscado;
    }

    @Override
    public boolean aplica(Muestra muestra) {
        return muestra.getHistorialOpiniones().getOpiniones().stream()
            .anyMatch(o -> o.getTipo().equals(tipoBuscado));
    }
}
