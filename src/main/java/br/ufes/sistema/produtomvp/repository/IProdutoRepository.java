package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Produto;
import java.util.List;
import java.util.Optional;

public interface IProdutoRepository {
    int proximoId();
    void adicionarProduto(Produto produto);
    void atualizarProduto(Produto produto);
    Optional<Produto> buscarPorId(int id);
    List<Produto> buscaTodos();
    List<Produto> buscarPorNome(String nome);
    List<Produto> buscarPorCategoria(String categoria);
    boolean existeProdutoComCategoria(int categoriaId);
}
