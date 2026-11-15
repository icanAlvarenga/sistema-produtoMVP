package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Cliente;
import br.ufes.sistema.produtomvp.model.Usuario;
import br.ufes.sistema.produtomvp.service.ClienteService;
import br.ufes.sistema.produtomvp.service.UsuarioService;
import br.ufes.sistema.produtomvp.view.ClientesView;
import br.ufes.sistema.produtomvp.view.IUsuariosView;

import java.util.List;

public class UsuariosPresenter {

    private final IUsuariosView view;
    private final UsuarioService usuarioService;
    private final ClienteService clienteService;
    private final Usuario usuarioLogado;

    private static final String MODO_VISUALIZACAO = "Modo: Visualização";
    private static final String MODO_INCLUSAO = "Modo: Inclusão";
    private static final String MODO_EDICAO = "Modo: Edição";

    private String modoAtual;

    public UsuariosPresenter(IUsuariosView view,
                             UsuarioService usuarioService,
                             ClienteService clienteService,
                             Usuario usuarioLogado) {
        this.view = view;
        this.usuarioService = usuarioService;
        this.clienteService = clienteService;
        this.usuarioLogado = usuarioLogado;
        this.modoAtual = MODO_VISUALIZACAO;

        configurarAcoes();
    }

    public void iniciar() {
        carregarClientes();
        carregarUsuarios();
        entrarModoVisualizacao();
        view.abrir();
    }

    private void configurarAcoes() {
        view.aoNovo(new Runnable() {
            @Override
            public void run() {
                acaoNovo();
            }
        });

        view.aoEditar(new Runnable() {
            @Override
            public void run() {
                acaoEditar();
            }
        });

        view.aoExcluir(new Runnable() {
            @Override
            public void run() {
                acaoExcluir();
            }
        });

        view.aoHabilitar(new Runnable() {
            @Override
            public void run() {
                acaoHabilitar();
            }
        });

        view.aoDesabilitar(new Runnable() {
            @Override
            public void run() {
                acaoDesabilitar();
            }
        });

        view.aoSalvar(new Runnable() {
            @Override
            public void run() {
                acaoSalvar();
            }
        });

        view.aoCancelar(new Runnable() {
            @Override
            public void run() {
                acaoCancelar();
            }
        });

        view.aoFechar(new Runnable() {
            @Override
            public void run() {
                view.fechar();
            }
        });

        view.aoSelecionarLinha(new Runnable() {
            @Override
            public void run() {
                acaoSelecionarLinha();
            }
        });

        view.aoMudarPerfil(new Runnable() {
            @Override
            public void run() {
                acaoMudarPerfil();
            }
        });

        view.aoIncluirCliente(new Runnable() {
            @Override
            public void run() {
                acaoIncluirCliente();
            }
        });
    }

    private void acaoNovo() {
        modoAtual = MODO_INCLUSAO;
        view.setModo(MODO_INCLUSAO);
        limparCampos();
        atualizarHabilitacaoClienteAssociado();
        view.setBotoesModoEdicaoOuInclusao();
        view.focarCampoNomeCompleto();
    }

    private void acaoEditar() {
        Usuario selecionado = view.getUsuarioSelecionado();
        if (selecionado == null) {
            view.exibirErro("Selecione um usuário para editar.");
            return;
        }
        modoAtual = MODO_EDICAO;
        view.setModo(MODO_EDICAO);
        preencherCampos(selecionado);
        atualizarHabilitacaoClienteAssociado();
        view.setBotoesModoEdicaoOuInclusao();
        view.focarCampoNomeCompleto();
    }

    private void acaoExcluir() {
        Usuario selecionado = view.getUsuarioSelecionado();
        if (selecionado == null) {
            view.exibirErro("Selecione um usuário para excluir.");
            return;
        }
        boolean confirmado = view.confirmarExclusao(
                "Deseja realmente excluir o usuário \"" + selecionado.getNomeCompleto() + "\"?"
        );
        if (!confirmado) {
            return;
        }
        try {
            usuarioService.excluir(selecionado, usuarioLogado);
            carregarUsuarios();
            limparCampos();
            entrarModoVisualizacao();
            view.exibirMensagem("Usuário excluído com sucesso.");
        } catch (ValidacaoException ex) {
            view.exibirErro(ex.getMessage());
        }
    }

    private void acaoHabilitar() {
        Usuario selecionado = view.getUsuarioSelecionado();
        if (selecionado == null) {
            view.exibirErro("Selecione um usuário para habilitar.");
            return;
        }
        try {
            usuarioService.habilitar(selecionado, usuarioLogado);
            carregarUsuarios();
            preencherCampos(selecionado);
            view.setBotoesModoVisualizacao(true, selecionado.isAdministrador(), selecionado.isHabilitado());
            view.exibirMensagem("Usuário habilitado com sucesso.");
        } catch (ValidacaoException ex) {
            view.exibirErro(ex.getMessage());
        }
    }

    private void acaoDesabilitar() {
        Usuario selecionado = view.getUsuarioSelecionado();
        if (selecionado == null) {
            view.exibirErro("Selecione um usuário para desabilitar.");
            return;
        }
        try {
            usuarioService.desabilitar(selecionado, usuarioLogado);
            carregarUsuarios();
            preencherCampos(selecionado);
            view.setBotoesModoVisualizacao(true, selecionado.isAdministrador(), selecionado.isHabilitado());
            view.exibirMensagem("Usuário desabilitado com sucesso.");
        } catch (ValidacaoException ex) {
            view.exibirErro(ex.getMessage());
        }
    }

    private void acaoSalvar() {
        String nomeCompleto = view.getNomeCompleto();
        String email = view.getEmail();
        String nomeUsuario = view.getNomeUsuario();
        String senha = view.getSenha();
        String confirmarSenha = view.getConfirmarSenha();
        String perfil = view.getPerfil();
        Cliente clienteAssociado = view.getClienteAssociado();

        try {
            if (MODO_INCLUSAO.equals(modoAtual)) {
                usuarioService.incluir(nomeCompleto, email, nomeUsuario, senha, confirmarSenha, perfil, clienteAssociado);
                view.exibirMensagem("Usuário incluído com sucesso.");
            } else if (MODO_EDICAO.equals(modoAtual)) {
                Usuario selecionado = view.getUsuarioSelecionado();
                if (selecionado == null) {
                    view.exibirErro("Nenhum usuário selecionado para edição.");
                    return;
                }
                usuarioService.atualizar(selecionado, nomeCompleto, email, nomeUsuario, senha, confirmarSenha, perfil, clienteAssociado);
                view.exibirMensagem("Usuário atualizado com sucesso.");
            }
            carregarUsuarios();
            limparCampos();
            entrarModoVisualizacao();
        } catch (ValidacaoException ex) {
            view.exibirErro(ex.getMessage());
        }
    }

    private void acaoCancelar() {
        limparCampos();
        entrarModoVisualizacao();
    }

    private void acaoSelecionarLinha() {
        Usuario selecionado = view.getUsuarioSelecionado();
        if (selecionado != null) {
            preencherCampos(selecionado);
            view.setCamposHabilitados(false, false);
            view.setBotoesModoVisualizacao(true, selecionado.isAdministrador(), selecionado.isHabilitado());
        } else {
            view.setBotoesModoVisualizacao(false, false, false);
        }
    }

    private void acaoMudarPerfil() {
        if (MODO_INCLUSAO.equals(modoAtual) || MODO_EDICAO.equals(modoAtual)) {
            atualizarHabilitacaoClienteAssociado();
        }
    }

    private void acaoIncluirCliente() {
        ClientesView clientesView = new ClientesView();
        ClientesPresenter clientesPresenter = new ClientesPresenter(clientesView, clienteService);
        clientesPresenter.abrirEmModoInclusao();
        // Após o fechamento da janela de clientes, recarrega o combo
        carregarClientes();
    }

    private void atualizarHabilitacaoClienteAssociado() {
        String perfil = view.getPerfil();
        boolean isCliente = "Cliente".equalsIgnoreCase(perfil);
        view.setCamposHabilitados(true, isCliente);
        if (!isCliente) {
            view.setClienteAssociado(null);
        }
    }

    private void carregarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        view.exibirUsuarios(usuarios);
    }

    private void carregarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        view.setClientesDisponiveis(clientes);
    }

    private void entrarModoVisualizacao() {
        modoAtual = MODO_VISUALIZACAO;
        view.setModo(MODO_VISUALIZACAO);
        view.setCamposHabilitados(false, false);
        view.setBotoesModoVisualizacao(false, false, false);
    }

    private void limparCampos() {
        view.setNomeCompleto("");
        view.setEmail("");
        view.setNomeUsuario("");
        view.setSenha("");
        view.setConfirmarSenha("");
        view.setPerfil("Administrador");
        view.setStatus("");
        view.setClienteAssociado(null);
    }

    private void preencherCampos(Usuario usuario) {
        view.setNomeCompleto(usuario.getNomeCompleto());
        view.setEmail(usuario.getEmail());
        view.setNomeUsuario(usuario.getNomeUsuario());
        view.setSenha("");
        view.setConfirmarSenha("");
        view.setPerfil(usuario.getPerfil());
        view.setStatus(usuario.getStatus());
        view.setClienteAssociado(usuario.getClienteAssociado());
    }
}
