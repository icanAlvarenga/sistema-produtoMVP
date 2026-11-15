package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    int proximoId();
    void adicionar(Usuario usuario);
    void atualizar(Usuario usuario);
    void remover(Usuario usuario);
    List<Usuario> buscaTodos();
    Optional<Usuario> buscarPorId(int id);
    Optional<Usuario> buscarPorUsername(String username);
    Optional<Usuario> buscarPorEmail(String email);
    Optional<Usuario> buscarPorUsernameOuEmail(String identificacao);
    boolean existeComUsername(String username, Integer idDesconsiderar);
}
