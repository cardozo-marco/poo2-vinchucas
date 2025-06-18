package buscador;

import muestra.Muestra;

public class FiltroVerificada implements Filtro{
	
	@Override
    public boolean aplica(Muestra muestra) {
        return muestra.estaVerificada();
    }
}
