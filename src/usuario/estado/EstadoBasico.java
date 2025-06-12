package usuario.estado;
import usuario.Usuario;

public class EstadoBasico implements EstadoUsuario {
    @Override
    public boolean esExperto() { return false; }
    @Override
    public boolean esEspecialista() { return false; }

    @Override
    public boolean cumpleRequisitosDeExperto(Usuario usuario) {
        return usuario.getEnvios().size() >= 10 && usuario.getRevisiones().size() >= 20;
    }

    @Override
    public void evaluarPromocion(Usuario usuario) {
        if (cumpleRequisitosDeExperto(usuario)) {
            usuario.setEstado(new EstadoExperto());
        }
    }
}