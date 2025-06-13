package muestra;

import java.util.ArrayList;
import java.util.List;

public class HistorialOpiniones {
    private List<Opinion> opiniones;

    public HistorialOpiniones() {
        this.opiniones = new ArrayList<>();
    }

    public void agregarOpinion(Opinion opinion) {
        opiniones.add(opinion);
    }

    public List<Opinion> getOpiniones() {
        return opiniones;
    }
    
    public int cantidadOpinionesExperto() {
    	int opinionesExpertos = (int) this.getOpiniones().stream()
                .filter(opinion -> opinion.getAutor().esExperto())
                .count();
    	
    	return opinionesExpertos;
    }
    
    public Opinion getUltimaOpinion() {
    	return this.opiniones.get(this.opiniones.size() - 1);
    }
}
