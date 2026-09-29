package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Categoria;
import java.util.List;

public interface IProdutoInclusaoEdicaoView {
    String getNome();
    String getPrecoCusto();
    Categoria getCategoriaSelecionada();

    void setTitulo(String titulo);
    void setNome(String nome);
    void setPrecoCusto(String precoCusto);
    void setCategorias(List<Categoria> categorias);
    void setCategoriaSelecionada(Categoria categoria);
    void setMargemLucro(String margem);
    void setPrecoVenda(String preco);

    void exibirMensagem(String mensagem);
    void exibirErro(String mensagem);
    void abrir();
    void fechar();

    void aoSalvar(Runnable acao);
    void aoCancelar(Runnable acao);
}