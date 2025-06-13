package buscador;

import muestra.Muestra;

public class FiltroMuestraVerificada implements Filtro{
	
	@Override
    public boolean aplica(Muestra muestra) {
        return muestra.estaVerificada();
    }
}
