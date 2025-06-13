package buscador;

import muestra.Muestra;

public class FiltroAnd implements Filtro {
	private Filtro filtro1;
    private Filtro filtro2;

    public FiltroAnd(Filtro filtro1, Filtro filtro2) {
        this.filtro1 = filtro1;
        this.filtro2 = filtro2;
    }

    @Override
    public boolean aplica(Muestra muestra) {
        return filtro1.aplica(muestra) && filtro2.aplica(muestra);
    }
}
