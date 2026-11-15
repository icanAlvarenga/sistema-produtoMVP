package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.repository.ICategoriaRepository;
import br.ufes.sistema.produtomvp.repository.IProdutoRepository;

import java.util.List;
import java.util.Optional;

public class CategoriaService {
    private final ICategoriaRepository categoriaRepository;
    private final IProdutoRepository produtoRepository;

    public CategoriaService(ICategoriaRepository categoriaRepository, IProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.buscaTodas();
    }

    public Optional<Categoria> buscarPorId(int id) {
        return categoriaRepository.buscarPorId(id);
    }

    public void incluir(String nome, Double percLucro) throws ValidacaoException {
        validar(null, nome, percLucro);
        Categoria categoria = new Categoria(categoriaRepository.proximoId(), nome.trim(), percLucro);
        categoriaRepository.adicionar(categoria);
    }

    public void atualizar(Categoria categoria, String nome, Double percLucro) throws ValidacaoException {
        if (categoria == null) {
            throw new ValidacaoException("Categoria inválida para atualização.");
        }
        validar(categoria.getId(), nome, percLucro);
        categoria.setNome(nome.trim());
        categoria.setPercLucro(percLucro);
        categoriaRepository.atualizar(categoria);
    }

    public void excluir(Categoria categoria) throws ValidacaoException {
        if (categoria == null) {
            return;
        }
        if (produtoRepository != null && produtoRepository.existeProdutoComCategoria(categoria.getId())) {
            throw new ValidacaoException("Não é possível excluir a categoria pois existem produtos associados a ela.");
        }
        categoriaRepository.remover(categoria);
    }

    private void validar(Integer categoriaIdAtual, String nome, Double percLucro) throws ValidacaoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("O nome da categoria é obrigatório.");
        }

        Optional<Categoria> existente = categoriaRepository.buscarPorNome(nome.trim());
        if (existente.isPresent()) {
            if (categoriaIdAtual == null || existente.get().getId() != categoriaIdAtual.intValue()) {
                throw new ValidacaoException("Já existe uma categoria com o nome informado.");
            }
        }

        if (percLucro == null) {
            throw new ValidacaoException("O percentual de lucro é obrigatório.");
        }
        if (percLucro < 0) {
            throw new ValidacaoException("O percentual de lucro deve ser maior ou igual a zero.");
        }
    }
}
