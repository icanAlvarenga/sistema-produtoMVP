package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface ICategoriaRepository {
    int proximoId();
    void adicionar(Categoria categoria);
    void atualizar(Categoria categoria);
    void remover(Categoria categoria);
    List<Categoria> buscaTodas();
    Optional<Categoria> buscarPorId(int id);
    Optional<Categoria> buscarPorNome(String nome);
}
