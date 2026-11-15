package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.service.CalculoPrecoService;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.view.BuscaProdutoView;
import br.ufes.sistema.produtomvp.view.CalculoMargemLucroView;
import br.ufes.sistema.produtomvp.view.CategoriasDeProdutosView;
import br.ufes.sistema.produtomvp.view.ITelaPrincipalView;
import br.ufes.sistema.produtomvp.view.TelaProdutoInclusaoEdicao;

public class TelaPrincipalPresenter {
    private final ITelaPrincipalView view;
    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;
    private final CalculoPrecoService calculoPrecoService;

    public TelaPrincipalPresenter(ITelaPrincipalView view,
                                  CategoriaService categoriaService,
                                  ProdutoService produtoService,
                                  CalculoPrecoService calculoPrecoService) {
        this.view = view;
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
        this.calculoPrecoService = calculoPrecoService;

        configurarAcoes();
    }

    public void iniciar() {
        view.exibir();
    }

    private void configurarAcoes() {
        view.aoIncluirProdutos(new Runnable() {
            @Override
            public void run() {
                abrirInclusaoProduto();
            }
        });

        view.aoBuscarProdutos(new Runnable() {
            @Override
            public void run() {
                abrirBuscaProdutos();
            }
        });

        view.aoCategorias(new Runnable() {
            @Override
            public void run() {
                abrirCategorias();
            }
        });

        view.aoCalcularMargemLucro(new Runnable() {
            @Override
            public void run() {
                abrirCalculoMargemLucro();
            }
        });
    }

    private void abrirInclusaoProduto() {
        TelaProdutoInclusaoEdicao telaInclusao = new TelaProdutoInclusaoEdicao();
        ProdutoInclusaoEdicaoPresenter presenter = new ProdutoInclusaoEdicaoPresenter(
                telaInclusao,
                produtoService,
                categoriaService,
                null,
                null
        );
        presenter.iniciar();
    }

    private void abrirBuscaProdutos() {
        BuscaProdutoView buscaView = new BuscaProdutoView();
        BuscarProdutosPresenter presenter = new BuscarProdutosPresenter(
                buscaView,
                produtoService,
                categoriaService,
                calculoPrecoService
        );
        presenter.iniciar();
    }

    private void abrirCategorias() {
        CategoriasDeProdutosView catView = new CategoriasDeProdutosView();
        CategoriaPresenter presenter = new CategoriaPresenter(catView, categoriaService);
        presenter.iniciar();
    }

    private void abrirCalculoMargemLucro() {
        CalculoMargemLucroView calcView = new CalculoMargemLucroView();
        CalculoMargemLucroPresenter presenter = new CalculoMargemLucroPresenter(calcView, calculoPrecoService);
        presenter.iniciar();
    }
}
