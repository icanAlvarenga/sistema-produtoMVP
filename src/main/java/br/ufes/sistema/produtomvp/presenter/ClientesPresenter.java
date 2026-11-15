package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Cliente;
import br.ufes.sistema.produtomvp.service.ClienteService;
import br.ufes.sistema.produtomvp.util.NumeroUtil;
import br.ufes.sistema.produtomvp.view.ClientesView;
import br.ufes.sistema.produtomvp.view.IClientesView;

import java.util.List;

public class ClientesPresenter {

    private final IClientesView view;
    private final ClienteService clienteService;

    private static final String MODO_VISUALIZACAO = "Modo: Visualização";
    private static final String MODO_INCLUSAO = "Modo: Inclusão";
    private static final String MODO_EDICAO = "Modo: Edição";

    private String modoAtual;

    public ClientesPresenter(IClientesView view, ClienteService clienteService) {
        this.view = view;
        this.clienteService = clienteService;
        this.modoAtual = MODO_VISUALIZACAO;

        configurarAcoes();
    }

    public void iniciar() {
        carregarClientes();
        entrarModoVisualizacao();
        view.abrir();
    }

    public void abrirEmModoInclusao() {
        carregarClientes();
        entrarModoVisualizacao();
        view.abrirEmModoInclusao();
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
    }

    private void acaoNovo() {
        modoAtual = MODO_INCLUSAO;
        view.setModo(MODO_INCLUSAO);
        view.setCamposHabilitados(true);
        view.setBotoesModoEdicaoOuInclusao();
        limparCampos();
        view.focarCampoNome();
    }

    private void acaoEditar() {
        Cliente selecionado = view.getClienteSelecionado();
        if (selecionado == null) {
            view.exibirErro("Selecione um cliente para editar.");
            return;
        }
        modoAtual = MODO_EDICAO;
        view.setModo(MODO_EDICAO);
        view.setCamposHabilitados(true);
        view.setBotoesModoEdicaoOuInclusao();
        preencherCampos(selecionado);
        view.focarCampoNome();
    }

    private void acaoExcluir() {
        Cliente selecionado = view.getClienteSelecionado();
        if (selecionado == null) {
            view.exibirErro("Selecione um cliente para excluir.");
            return;
        }
        boolean confirmado = view.confirmarExclusao(
                "Deseja realmente excluir o cliente \"" + selecionado.getNome() + "\"?"
        );
        if (!confirmado) {
            return;
        }
        try {
            clienteService.excluir(selecionado);
            carregarClientes();
            limparCampos();
            entrarModoVisualizacao();
            view.exibirMensagem("Cliente excluído com sucesso.");
        } catch (ValidacaoException ex) {
            view.exibirErro(ex.getMessage());
        }
    }

    private void acaoSalvar() {
        String nome = view.getNome();
        String logradouro = view.getLogradouro();
        String bairro = view.getBairro();
        String cidade = view.getCidade();
        String uf = view.getUf();

        try {
            if (MODO_INCLUSAO.equals(modoAtual)) {
                clienteService.incluir(nome, logradouro, bairro, cidade, uf);
                view.exibirMensagem("Cliente incluído com sucesso.");
            } else if (MODO_EDICAO.equals(modoAtual)) {
                Cliente selecionado = view.getClienteSelecionado();
                if (selecionado == null) {
                    view.exibirErro("Nenhum cliente selecionado para edição.");
                    return;
                }
                clienteService.atualizar(selecionado, nome, logradouro, bairro, cidade, uf);
                view.exibirMensagem("Cliente atualizado com sucesso.");
            }
            carregarClientes();
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
        Cliente selecionado = view.getClienteSelecionado();
        if (selecionado != null) {
            preencherCampos(selecionado);
            view.setBotoesModoVisualizacao(true);
        } else {
            view.setBotoesModoVisualizacao(false);
        }
    }

    private void carregarClientes() {
        List<Cliente> clientes = clienteService.listarTodos();
        view.exibirClientes(clientes);
    }

    private void entrarModoVisualizacao() {
        modoAtual = MODO_VISUALIZACAO;
        view.setModo(MODO_VISUALIZACAO);
        view.setCamposHabilitados(false);
        view.setBotoesModoVisualizacao(false);
    }

    private void limparCampos() {
        view.setNome("");
        view.setLogradouro("");
        view.setBairro("");
        view.setCidade("");
        view.setUf("");
        view.setTipoCliente("PRATA");
        view.setTotalCompras("0,00");
    }

    private void preencherCampos(Cliente cliente) {
        view.setNome(cliente.getNome());
        view.setLogradouro(cliente.getLogradouro());
        view.setBairro(cliente.getBairro());
        view.setCidade(cliente.getCidade());
        view.setUf(cliente.getUf());
        view.setTipoCliente(cliente.getTipoCliente());
        view.setTotalCompras(NumeroUtil.formatar(cliente.getTotalCompras()));
    }
}
