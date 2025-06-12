package usuario.estado;
import usuario.Usuario;

public class EstadoBasico implements EstadoUsuario {
    @Override
    public boolean esExperto() { return false; }
    @Override
    public boolean esEspecialista() { return false; }

    /*
     * Lo implemente en la clase Usuario, porque los dos estados EstadoUsuario y estadoExperto, tienen la misma implementacion.
     * Se podria implementar en EstadoUsuario, pero tendria que pasar a ser una clase abstracta.
    @Override
    public boolean cumpleRequisitosDeExperto(Usuario usuario) {
        return usuario.getEnvios().size() >= 10 && usuario.getRevisiones().size() >= 20;
    }
    */

    @Override
    public void evaluarPromocion(Usuario usuario) {
        if (usuario.cumpleRequisitosDeExperto()) {
            usuario.setEstado(new EstadoExperto());
        }
    }
}