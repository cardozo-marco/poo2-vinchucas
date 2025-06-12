package usuario.estado;
import usuario.Usuario;

public class EstadoEspecialista implements EstadoUsuario {
    @Override
    public boolean esExperto() { return true; }
    @Override
    public boolean esEspecialista() { return true; }
    @Override
    public boolean cumpleRequisitosDeExperto(Usuario usuario) {
        return true;
    }

    @Override
    public void evaluarPromocion(Usuario usuario) {
        // Nunca cambia de estado
    }
}
