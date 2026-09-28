
package br.ufes.sistema.produtomvp.repository;
import br.ufes.sistema.produtomvp.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private List<Produto> produtos;

    public ProdutoRepository() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto){
        produtos.add(produto);
    }

    public List<Produto> buscaTodos(){
       return new ArrayList<>(produtos);
    }

    public List<Produto> buscarPornome(String nome){
        List<Produto> resultado = new ArrayList<>();

        for (Produto p : produtos){
            if(p.getNome().toLowerCase().contains(nome.toLowerCase())){
                resultado.add(p);
            }
        }

        return resultado;
    }

    public List<Produto> buscarPorCategoria(String categoria){
        List<Produto> resultado = new ArrayList<>();

        for (Produto p : produtos ){
            if (p.getCategoria().getNome().toLowerCase().contains(categoria)){
                resultado.add(p);
            }
        }

        return resultado;
    }
}
