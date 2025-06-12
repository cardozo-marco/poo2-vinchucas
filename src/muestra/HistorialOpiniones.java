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
}
