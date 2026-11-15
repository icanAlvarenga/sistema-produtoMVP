package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.repository.ICategoriaRepository;
import br.ufes.sistema.produtomvp.repository.IProdutoRepository;

import java.util.List;

public class ProdutoService {
    private final IProdutoRepository repository;
    private final ICategoriaRepository categoriaRepository;

    public ProdutoService(IProdutoRepository repository,
                          ICategoriaRepository categoriaRepository) {
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
        if (produto == null) {
            throw new ValidacaoException("Produto inválido para atualização.");
        }
        validar(nome, precoCusto, categoria);
        produto.setNome(nome.trim());
        produto.setPrecoDeCusto(precoCusto);
        produto.setCategoria(categoria);
        repository.atualizarProduto(produto);
    }

    private void validar(String nome, Double precoCusto, Categoria categoria)
            throws ValidacaoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("O nome do produto é obrigatório.");
        }
        if (precoCusto == null) {
            throw new ValidacaoException("O preço de custo é obrigatório.");
        }
        if (precoCusto <= 0) {
            throw new ValidacaoException("O preço de custo deve ser maior que zero.");
        }
        if (categoria == null || !categoriaRepository.buscarPorId(categoria.getId()).isPresent()) {
            throw new ValidacaoException("Categoria do produto é obrigatória e deve corresponder a uma categoria existente no repositório.");
        }
    }
}
