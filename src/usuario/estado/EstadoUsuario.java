package usuario.estado;

import usuario.Usuario;

public interface EstadoUsuario {
    boolean esExperto();
    boolean esEspecialista();
    //boolean cumpleRequisitosDeExperto(Usuario usuario);
    void evaluarPromocion(Usuario usuario);
}
