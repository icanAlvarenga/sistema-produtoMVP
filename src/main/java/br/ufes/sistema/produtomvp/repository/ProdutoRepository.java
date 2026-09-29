package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepository {
    private final List<Produto> produtos;
    private int proximoId = 1;

    public ProdutoRepository() {
        this.produtos = new ArrayList<>();
    }

    public int proximoId() {
        return proximoId++;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void atualizarProduto(Produto produto) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == produto.getId()) {
                produtos.set(i, produto);
                return;
            }
        }
    }

    public Optional<Produto> buscarPorId(int id) {
        return produtos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public List<Produto> buscaTodos() {
        return new ArrayList<>(produtos);
    }

    public List<Produto> buscarPorNome(String nome) {
        String termo = nome == null ? "" : nome.trim().toLowerCase();
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(termo)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        String termo = categoria == null ? "" : categoria.trim().toLowerCase();
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : produtos) {
            if (p.getCategoria().getNome().toLowerCase().contains(termo)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public boolean existeProdutoComCategoria(int categoriaId) {
        return produtos.stream().anyMatch(p -> p.getCategoria().getId() == categoriaId);
    }
}