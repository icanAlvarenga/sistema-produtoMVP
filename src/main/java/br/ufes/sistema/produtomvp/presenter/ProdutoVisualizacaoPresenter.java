package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.util.NumeroUtil;
import br.ufes.sistema.produtomvp.view.IProdutoVisualizacaoView;
import br.ufes.sistema.produtomvp.view.TelaProdutoInclusaoEdicao;

public class ProdutoVisualizacaoPresenter {

    private final IProdutoVisualizacaoView view;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final Produto produto;
    private final Runnable aoAtualizar;

    public ProdutoVisualizacaoPresenter(IProdutoVisualizacaoView view, ProdutoService produtoService, CategoriaService categoriaService, Produto produto, Runnable aoAtualizar) {
        this.view = view;
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.produto = produto;
        this.aoAtualizar = aoAtualizar;

        view.aoEditar(this::editar);
        view.aoVisualizarHistorico(this::abrirHistorico);
        view.aoFechar(view::fechar);
        preencherTela();
    }

    public void iniciar() {
        view.abrir();
    }

    private void preencherTela() {
        view.setNome(produto.getNome());
        view.setPrecoCusto(NumeroUtil.formatar(produto.getPrecoDeCusto()));
        view.setCategoria(produto.getCategoria().getNome());
        view.setMargemLucro(NumeroUtil.formatar(produto.getMargemLucroAtual()));
        view.setPrecoVenda(NumeroUtil.formatar(produto.getPrecoVendaAtual()));
    }

    private void editar() {
        view.fechar();
        new ProdutoInclusaoEdicaoPresenter(new TelaProdutoInclusaoEdicao(),
                produtoService, categoriaService, produto, aoAtualizar).iniciar();
    }

    private void abrirHistorico() {
        // Integrar com quem faz a tela 9 (HistoricoPrecosView), algo como:
        // new HistoricoPrecosPresenter(new HistoricoPrecosView(), produto, ...).iniciar();
    }
}