package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository implements IProdutoRepository {
    private final List<Produto> produtos;
    private int proximoId = 1;

    public ProdutoRepository() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public int proximoId() {
        return proximoId++;
    }

    @Override
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    @Override
    public void atualizarProduto(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                produtos.set(i, produto);
                return;
            }
        }
    }

    @Override
    public Optional<Produto> buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Produto> buscaTodos() {
        return new ArrayList<>(produtos);
    }

    @Override
    public List<Produto> buscarPorNome(String nome) {
        String termo = nome == null ? "" : nome.trim().toLowerCase();
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getNome() != null && p.getNome().toLowerCase().contains(termo)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    @Override
    public List<Produto> buscarPorCategoria(String categoria) {
        String termo = categoria == null ? "" : categoria.trim().toLowerCase();
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getCategoria() != null && p.getCategoria().getNome() != null &&
                    p.getCategoria().getNome().toLowerCase().contains(termo)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    @Override
    public boolean existeProdutoComCategoria(int categoriaId) {
        for (Produto p : produtos) {
            if (p.getCategoria() != null && p.getCategoria().getId() == categoriaId) {
                return true;
            }
        }
        return false;
    }
}
