
package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {
    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> buscarTodos(){
        return repository.buscaTodos();
    }

    public List<Produto> buscarPorNome(String nome){
       return repository.buscarPornome(nome);
    }

    public List<Produto> buscarPorCategoria(String categoria){
      return   repository.buscarPorCategoria(categoria);
    }
}
