package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Cliente;
import java.util.List;

public interface IClientesView {
    String getNome();
    void setNome(String nome);
    String getLogradouro();
    void setLogradouro(String logradouro);
    String getBairro();
    void setBairro(String bairro);
    String getCidade();
    void setCidade(String cidade);
    String getUf();
    void setUf(String uf);
    String getTipoCliente();
    void setTipoCliente(String tipoCliente);
    String getTotalCompras();
    void setTotalCompras(String totalCompras);
    Cliente getClienteSelecionado();
    void exibirClientes(List<Cliente> clientes);
    void setModo(String modo);
    void setCamposHabilitados(boolean habilitados);
    void setBotoesModoVisualizacao(boolean temSelecao);
    void setBotoesModoEdicaoOuInclusao();
    void focarCampoNome();
    void exibirMensagem(String mensagem);
    void exibirErro(String mensagem);
    boolean confirmarExclusao(String mensagem);
    void abrir();
    void abrirEmModoInclusao();
    void fechar();
    void aoNovo(Runnable acao);
    void aoEditar(Runnable acao);
    void aoExcluir(Runnable acao);
    void aoSalvar(Runnable acao);
    void aoCancelar(Runnable acao);
    void aoFechar(Runnable acao);
    void aoSelecionarLinha(Runnable acao);
}
