package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.repository.CategoriaRepository;
import br.ufes.sistema.produtomvp.repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {
    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository repository,
                          CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Produto> buscarTodos() {
        return repository.buscaTodos();
    }

    public List<Produto> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    public List<Produto> buscarPorCategoria(String categoria) {
        return repository.buscarPorCategoria(categoria);
    }


    public void incluir(String nome, Double precoCusto, Categoria categoria)
            throws ValidacaoException {
        validar(nome, precoCusto, categoria);
        Produto produto = new Produto(repository.proximoId(),
                nome.trim(), precoCusto, categoria);
        repository.adicionarProduto(produto);
    }

    public void atualizar(Produto produto, String nome, Double precoCusto, Categoria categoria)
            throws ValidacaoException {
        validar(nome, precoCusto, categoria);
        produto.setNome(nome.trim());
        produto.setPrecoDeCusto(precoCusto);
        produto.setCategoria(categoria);
        repository.atualizarProduto(produto);
    }

    private void validar(String nome, Double precoCusto, Categoria categoria)
            throws ValidacaoException {
        if (nome == null || nome.isBlank()) {
            throw new ValidacaoException("O nome do produto é obrigatório.");
        }
        if (precoCusto == null) {
            throw new ValidacaoException("O preço de custo é obrigatório.");
        }
        if (precoCusto <= 0) {
            throw new ValidacaoException("O preço de custo deve ser maior que zero.");
        }
        if (categoria == null || categoriaRepository.buscarPorId(categoria.getId()).isEmpty()) {
            throw new ValidacaoException("Selecione uma categoria existente.");
        }
    }
}