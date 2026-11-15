package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Produto;
import java.util.List;

public interface IBuscaProdutoView {
    String getTextoBusca();
    String getTipoBusca();
    Produto getProdutoSelecionado();
    void exibirProdutos(List<Produto> produtos);
    void setVisualizarHabilitado(boolean habilitado);
    void abrir();
    void fechar();
    void aoBuscar(Runnable acao);
    void aoNovo(Runnable acao);
    void aoVisualizar(Runnable acao);
    void aoFechar(Runnable acao);
    void aoSelecionarLinha(Runnable acao);
}
