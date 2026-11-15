package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import java.util.List;

public interface IHistoricoPrecosView {
    void setNomeProduto(String nome);
    void setNomeCategoria(String categoria);
    void exibirHistorico(List<HistoricoPreco> historico);
    void abrir();
    void fechar();
    void aoFechar(Runnable acao);
}
