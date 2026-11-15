package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Categoria;
import java.util.List;

public interface ICategoriasDeProdutosView {
    String getNomeCategoria();
    void setNomeCategoria(String nome);
    String getPercentualLucro();
    void setPercentualLucro(String perc);
    Categoria getCategoriaSelecionada();
    void exibirCategorias(List<Categoria> categorias);
    void setModo(String modo);
    void setCamposHabilitados(boolean habilitados);
    void setBotoesModoVisualizacao(boolean temSelecao);
    void setBotoesModoEdicaoOuInclusao();
    void focarCampoCategoria();
    void exibirMensagem(String mensagem);
    void exibirErro(String mensagem);
    boolean confirmarExclusao(String mensagem);
    void abrir();
    void fechar();
    void aoNovo(Runnable acao);
    void aoEditar(Runnable acao);
    void aoExcluir(Runnable acao);
    void aoSalvar(Runnable acao);
    void aoCancelar(Runnable acao);
    void aoFechar(Runnable acao);
    void aoSelecionarLinha(Runnable acao);
}
