package buscador;

import muestra.Muestra;

public class FiltroMuestraVotada implements Filtro{
	
	@Override
    public boolean aplica(Muestra muestra) {
        return !muestra.estaVerificada();
    }
}
