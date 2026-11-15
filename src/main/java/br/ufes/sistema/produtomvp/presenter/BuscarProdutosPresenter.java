package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.service.CalculoPrecoService;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.view.IBuscaProdutoView;
import br.ufes.sistema.produtomvp.view.TelaProdutoInclusaoEdicao;
import br.ufes.sistema.produtomvp.view.TelaProdutoVisualizacao;

import java.util.List;

public class BuscarProdutosPresenter {

    private final IBuscaProdutoView view;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final CalculoPrecoService calculoPrecoService;

    public BuscarProdutosPresenter(IBuscaProdutoView view,
                                   ProdutoService produtoService,
                                   CategoriaService categoriaService,
                                   CalculoPrecoService calculoPrecoService) {
        this.view = view;
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.calculoPrecoService = calculoPrecoService;

        configurarAcoes();
    }

    public void iniciar() {
        buscarProdutos();
        view.abrir();
    }

    private void configurarAcoes() {
        view.aoBuscar(new Runnable() {
            @Override
            public void run() {
                buscarProdutos();
            }
        });

        view.aoNovo(new Runnable() {
            @Override
            public void run() {
                novoProduto();
            }
        });

        view.aoVisualizar(new Runnable() {
            @Override
            public void run() {
                visualizarProduto();
            }
        });

        view.aoFechar(new Runnable() {
            @Override
            public void run() {
                view.fechar();
            }
        });

        view.aoSelecionarLinha(new Runnable() {
            @Override
            public void run() {
                view.setVisualizarHabilitado(view.getProdutoSelecionado() != null);
            }
        });
    }

    public void buscarProdutos() {
        String texto = view.getTextoBusca();
        List<Produto> produtos;

        if (texto == null || texto.trim().isEmpty()) {
            produtos = produtoService.buscarTodos();
        } else {
            String tipoBusca = view.getTipoBusca();
            if (tipoBusca != null && tipoBusca.toLowerCase().contains("categoria")) {
                produtos = produtoService.buscarPorCategoria(texto);
            } else {
                produtos = produtoService.buscarPorNome(texto);
            }
        }

        view.exibirProdutos(produtos);
    }

    public void novoProduto() {
        TelaProdutoInclusaoEdicao telaInclusao = new TelaProdutoInclusaoEdicao();
        ProdutoInclusaoEdicaoPresenter presenter = new ProdutoInclusaoEdicaoPresenter(
                telaInclusao,
                produtoService,
                categoriaService,
                null,
                new Runnable() {
                    @Override
                    public void run() {
                        buscarProdutos();
                    }
                }
        );
        presenter.iniciar();
    }

    public void visualizarProduto() {
        Produto produto = view.getProdutoSelecionado();
        if (produto == null) {
            return;
        }

        TelaProdutoVisualizacao telaVis = new TelaProdutoVisualizacao();
        ProdutoVisualizacaoPresenter presenter = new ProdutoVisualizacaoPresenter(
                telaVis,
                produtoService,
                categoriaService,
                calculoPrecoService,
                produto,
                new Runnable() {
                    @Override
                    public void run() {
                        buscarProdutos();
                    }
                }
        );
        presenter.iniciar();
    }
}
