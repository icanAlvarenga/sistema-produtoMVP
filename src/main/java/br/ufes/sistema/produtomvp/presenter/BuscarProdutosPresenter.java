package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.view.BuscaProdutoView;

import java.util.List;

public class BuscarProdutosPresenter {

    private final BuscaProdutoView view;
    private final ProdutoService service;

    public BuscarProdutosPresenter(BuscaProdutoView view, ProdutoService service) {
        this.view = view;
        this.service = service;
    }

    
    public void buscarProdutos() {

        String texto = view.getTextoBusca();

        List<Produto> produtos;

        if (texto == null || texto.trim().isEmpty()) {

            produtos = service.buscarTodos();

        } else {

            String tipoBusca = view.getTipoBusca();

            if (tipoBusca.equals("Nome do Produto")) {
                produtos = service.buscarPorNome(texto);
            } else {
                produtos = service.buscarPorCategoria(texto);
            }
        }

        view.exibirProdutos(produtos);
    }
}
