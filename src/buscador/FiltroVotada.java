package buscador;

import muestra.Muestra;

public class FiltroVotada implements Filtro{
	
	@Override
    public boolean aplica(Muestra muestra) {
        return !muestra.estaVerificada();
    }
}
