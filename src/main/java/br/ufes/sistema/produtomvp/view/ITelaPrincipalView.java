package br.ufes.sistema.produtomvp.view;

public interface ITelaPrincipalView {
    void exibir();
    void fechar();
    void aoIncluirProdutos(Runnable acao);
    void aoBuscarProdutos(Runnable acao);
    void aoCategorias(Runnable acao);
    void aoCalcularMargemLucro(Runnable acao);
}
