package br.ufes.sistema.produtomvp.view;

public interface ILoginView {
    String getIdentificacao();
    String getSenha();
    void limparCampos();
    void exibirErro(String mensagem);
    void abrir();
    void fechar();
    void aoEntrar(Runnable acao);
    void aoFechar(Runnable acao);
}
