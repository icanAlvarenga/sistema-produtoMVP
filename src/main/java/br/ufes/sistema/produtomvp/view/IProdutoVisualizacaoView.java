package br.ufes.sistema.produtomvp.view;

public interface IProdutoVisualizacaoView {
    void setNome(String nome);
    void setPrecoCusto(String precoCusto);
    void setCategoria(String categoria);
    void setMargemLucro(String margem);
    void setPrecoVenda(String preco);

    void abrir();
    void fechar();

    void aoEditar(Runnable acao);
    void aoVisualizarHistorico(Runnable acao);
    void aoFechar(Runnable acao);
}