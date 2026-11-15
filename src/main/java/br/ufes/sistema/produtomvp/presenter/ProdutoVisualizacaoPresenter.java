package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.service.CalculoPrecoService;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.util.NumeroUtil;
import br.ufes.sistema.produtomvp.view.HistoricoPrecosView;
import br.ufes.sistema.produtomvp.view.IProdutoVisualizacaoView;
import br.ufes.sistema.produtomvp.view.TelaProdutoInclusaoEdicao;

public class ProdutoVisualizacaoPresenter {

    private final IProdutoVisualizacaoView view;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final CalculoPrecoService calculoPrecoService;
    private final Produto produto;
    private final Runnable aoAtualizar;

    public ProdutoVisualizacaoPresenter(IProdutoVisualizacaoView view,
                                        ProdutoService produtoService,
                                        CategoriaService categoriaService,
                                        CalculoPrecoService calculoPrecoService,
                                        Produto produto,
                                        Runnable aoAtualizar) {
        this.view = view;
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.calculoPrecoService = calculoPrecoService;
        this.produto = produto;
        this.aoAtualizar = aoAtualizar;

        configurarAcoes();
        preencherTela();
    }

    public void iniciar() {
        view.abrir();
    }

    private void configurarAcoes() {
        view.aoEditar(new Runnable() {
            @Override
            public void run() {
                editar();
            }
        });

        view.aoVisualizarHistorico(new Runnable() {
            @Override
            public void run() {
                abrirHistorico();
            }
        });

        view.aoFechar(new Runnable() {
            @Override
            public void run() {
                view.fechar();
            }
        });
    }

    private void preencherTela() {
        view.setNome(produto.getNome());
        view.setPrecoCusto(NumeroUtil.formatar(produto.getPrecoDeCusto()));
        view.setCategoria(produto.getCategoria() != null ? produto.getCategoria().getNome() : "");
        view.setMargemLucro(NumeroUtil.formatar(produto.getMargemLucroAtual()));
        view.setPrecoVenda(NumeroUtil.formatar(produto.getPrecoVendaAtual()));
    }

    private void editar() {
        view.fechar();
        TelaProdutoInclusaoEdicao telaEdicao = new TelaProdutoInclusaoEdicao();
        ProdutoInclusaoEdicaoPresenter presenter = new ProdutoInclusaoEdicaoPresenter(
                telaEdicao,
                produtoService,
                categoriaService,
                produto,
                new Runnable() {
                    @Override
                    public void run() {
                        preencherTela();
                        if (aoAtualizar != null) {
                            aoAtualizar.run();
                        }
                    }
                }
        );
        presenter.iniciar();
    }

    private void abrirHistorico() {
        HistoricoPrecosView histView = new HistoricoPrecosView();
        HistoricoPrecosPresenter presenter = new HistoricoPrecosPresenter(
                histView,
                calculoPrecoService,
                produto
        );
        presenter.iniciar();
    }
}
