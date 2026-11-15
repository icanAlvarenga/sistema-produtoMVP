package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.service.CalculoPrecoService;
import br.ufes.sistema.produtomvp.view.IHistoricoPrecosView;

import java.util.List;

public class HistoricoPrecosPresenter {

    private final IHistoricoPrecosView view;
    private final CalculoPrecoService calculoPrecoService;
    private final Produto produto;

    public HistoricoPrecosPresenter(IHistoricoPrecosView view,
                                   CalculoPrecoService calculoPrecoService,
                                   Produto produto) {
        this.view = view;
        this.calculoPrecoService = calculoPrecoService;
        this.produto = produto;

        configurarAcoes();
    }

    public void iniciar() {
        if (produto != null) {
            view.setNomeProduto(produto.getNome());
            view.setNomeCategoria(produto.getCategoria() != null ? produto.getCategoria().getNome() : "");
            List<HistoricoPreco> historico = calculoPrecoService.buscarHistoricoPorProduto(produto.getId());
            view.exibirHistorico(historico);
        }
        view.abrir();
    }

    private void configurarAcoes() {
        view.aoFechar(new Runnable() {
            @Override
            public void run() {
                view.fechar();
            }
        });
    }
}
