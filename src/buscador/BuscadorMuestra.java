package buscador;

import java.util.List;
import java.util.stream.Collectors;

import muestra.Muestra;

public class BuscadorMuestra { 
	//resuelto con Composite. Filtros compuestos como FiltroAnd y FiltroOr y el resto de filtros simples son leaf.
	//cumple con el principio de single responsability ya que se encarga solamente de aplicar un filtro a una coleccion de muestras.
	//component: Filtro client: BuscadorMuestra composite: FiltroAnd, FiltroOr leaf: resto de filters simples
	public List<Muestra> buscar(List<Muestra> muestras, Filtro filtro) {
        return muestras.stream()
            .filter(m -> filtro.aplica(m))
            .toList();
    }
}
