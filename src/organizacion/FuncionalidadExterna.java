package organizacion;

import muestra.Muestra;
import ubicacion.ZonaDeCobertura;

public interface FuncionalidadExterna {
    void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zona, Muestra muestra);
}
