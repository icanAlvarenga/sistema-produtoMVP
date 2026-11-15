package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Cliente;
import br.ufes.sistema.produtomvp.model.Usuario;
import java.util.List;

public interface IUsuariosView {
    String getNomeCompleto();
    void setNomeCompleto(String nome);
    String getEmail();
    void setEmail(String email);
    String getNomeUsuario();
    void setNomeUsuario(String username);
    String getSenha();
    void setSenha(String senha);
    String getConfirmarSenha();
    void setConfirmarSenha(String senha);
    String getPerfil();
    void setPerfil(String perfil);
    String getStatus();
    void setStatus(String status);
    Cliente getClienteAssociado();
    void setClienteAssociado(Cliente cliente);
    void setClientesDisponiveis(List<Cliente> clientes);
    Usuario getUsuarioSelecionado();
    void exibirUsuarios(List<Usuario> usuarios);
    void setModo(String modo);
    void setCamposHabilitados(boolean habilitados, boolean habilitarClienteAssociado);
    void setBotoesModoVisualizacao(boolean temSelecao, boolean isAdministradorSelecionado, boolean isHabilitado);
    void setBotoesModoEdicaoOuInclusao();
    void focarCampoNomeCompleto();
    void exibirMensagem(String mensagem);
    void exibirErro(String mensagem);
    boolean confirmarExclusao(String mensagem);
    void abrir();
    void fechar();
    void aoNovo(Runnable acao);
    void aoEditar(Runnable acao);
    void aoExcluir(Runnable acao);
    void aoHabilitar(Runnable acao);
    void aoDesabilitar(Runnable acao);
    void aoSalvar(Runnable acao);
    void aoCancelar(Runnable acao);
    void aoFechar(Runnable acao);
    void aoSelecionarLinha(Runnable acao);
    void aoMudarPerfil(Runnable acao);
    void aoIncluirCliente(Runnable acao);
}
