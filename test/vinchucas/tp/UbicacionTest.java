package vinchucas.tp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;


class UbicacionTest {

    @Test
    void testCrearUbicacionValida() {
        Ubicacion ubicacion = new Ubicacion(-34.603722, -58.381592);
        
        assertEquals(-34.603722, ubicacion.getLatitud());
        assertEquals(-58.381592, ubicacion.getLongitud());
    }
    
    @Test
    void testDistanciaAUbicacion() {
        Ubicacion ubicacion1 = new Ubicacion(-34.603722, -58.381592);
        Ubicacion ubicacion2 = new Ubicacion(-34.921450, -57.954536);
        
        assertDoesNotThrow(() -> ubicacion1.distanciaA(ubicacion2));
    }
    
}