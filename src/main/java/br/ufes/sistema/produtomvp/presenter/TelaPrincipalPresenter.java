package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.model.Pedido;
import br.ufes.sistema.produtomvp.model.Usuario;
import br.ufes.sistema.produtomvp.service.CalculoPrecoService;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.service.ClienteService;
import br.ufes.sistema.produtomvp.service.DashboardService;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.service.UsuarioService;
import br.ufes.sistema.produtomvp.view.BuscaProdutoView;
import br.ufes.sistema.produtomvp.view.CalculoMargemLucroView;
import br.ufes.sistema.produtomvp.view.CategoriasDeProdutosView;
import br.ufes.sistema.produtomvp.view.ClientesView;
import br.ufes.sistema.produtomvp.view.ITelaPrincipalView;
import br.ufes.sistema.produtomvp.view.ILoginView;
import br.ufes.sistema.produtomvp.view.LoginView;
import br.ufes.sistema.produtomvp.view.TelaProdutoInclusaoEdicao;
import br.ufes.sistema.produtomvp.view.UsuariosView;

import java.util.List;

public class TelaPrincipalPresenter {

    private final ITelaPrincipalView view;
    private final Usuario usuarioLogado;
    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;
    private final CalculoPrecoService calculoPrecoService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final DashboardService dashboardService;
    private final br.ufes.sistema.produtomvp.service.AutenticacaoService autenticacaoService;

    public TelaPrincipalPresenter(ITelaPrincipalView view,
                                  Usuario usuarioLogado,
                                  CategoriaService categoriaService,
                                  ProdutoService produtoService,
                                  CalculoPrecoService calculoPrecoService,
                                  ClienteService clienteService,
                                  UsuarioService usuarioService,
                                  DashboardService dashboardService) {
        this(view, usuarioLogado, categoriaService, produtoService, calculoPrecoService,
             clienteService, usuarioService, dashboardService, null);
    }

    public TelaPrincipalPresenter(ITelaPrincipalView view,
                                  Usuario usuarioLogado,
                                  CategoriaService categoriaService,
                                  ProdutoService produtoService,
                                  CalculoPrecoService calculoPrecoService,
                                  ClienteService clienteService,
                                  UsuarioService usuarioService,
                                  DashboardService dashboardService,
                                  br.ufes.sistema.produtomvp.service.AutenticacaoService autenticacaoService) {
        this.view = view;
        this.usuarioLogado = usuarioLogado;
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
        this.calculoPrecoService = calculoPrecoService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
        this.dashboardService = dashboardService;
        this.autenticacaoService = autenticacaoService;

        configurarAcoes();
    }

    public void iniciar() {
        String identificacao = usuarioLogado != null ? usuarioLogado.getNomeCompleto() : "";
        boolean podeGerenciarUsuarios = usuarioLogado != null && usuarioLogado.isAdministrador();

        carregarDashboard();
        view.exibir(identificacao, podeGerenciarUsuarios);
    }

    private void carregarDashboard() {
        List<Pedido> pedidos = dashboardService.listarUltimosPedidos();
        view.exibirPedidos(pedidos);
        view.exibirIndicadores(
                dashboardService.getPedidosEmAndamento(),
                dashboardService.getPedidosEntreguesHoje(),
                dashboardService.getTotalEmVendas(),
                dashboardService.getEntregadoresAtivos()
        );
    }

    private void configurarAcoes() {
        view.aoSair(new Runnable() {
            @Override
            public void run() {
                acaoSair();
            }
        });

        view.aoMenuProdutos(new Runnable() {
            @Override
            public void run() {
                abrirBuscaProdutos();
            }
        });

        view.aoMenuCategorias(new Runnable() {
            @Override
            public void run() {
                abrirCategorias();
            }
        });

        view.aoMenuClientes(new Runnable() {
            @Override
            public void run() {
                abrirClientes();
            }
        });

        view.aoMenuUsuarios(new Runnable() {
            @Override
            public void run() {
                abrirUsuarios();
            }
        });

        view.aoNovoProduto(new Runnable() {
            @Override
            public void run() {
                abrirInclusaoProduto();
            }
        });

        view.aoNovoCliente(new Runnable() {
            @Override
            public void run() {
                abrirClientesEmInclusao();
            }
        });

        view.aoBuscarClientes(new Runnable() {
            @Override
            public void run() {
                abrirClientes();
            }
        });
    }

    private void acaoSair() {
        view.fechar();
        ILoginView loginView = new LoginView();
        LoginPresenter loginPresenter = new LoginPresenter(
                loginView,
                autenticacaoService,
                categoriaService,
                produtoService,
                calculoPrecoService,
                clienteService,
                usuarioService,
                dashboardService
        );
        loginPresenter.iniciar();
    }

    private void abrirInclusaoProduto() {
        TelaProdutoInclusaoEdicao telaInclusao = new TelaProdutoInclusaoEdicao();
        ProdutoInclusaoEdicaoPresenter presenter = new ProdutoInclusaoEdicaoPresenter(
                telaInclusao,
                produtoService,
                categoriaService,
                null,
                null
        );
        presenter.iniciar();
    }

    private void abrirBuscaProdutos() {
        BuscaProdutoView buscaView = new BuscaProdutoView();
        BuscarProdutosPresenter presenter = new BuscarProdutosPresenter(
                buscaView,
                produtoService,
                categoriaService,
                calculoPrecoService
        );
        presenter.iniciar();
    }

    private void abrirCategorias() {
        CategoriasDeProdutosView catView = new CategoriasDeProdutosView();
        CategoriaPresenter presenter = new CategoriaPresenter(catView, categoriaService);
        presenter.iniciar();
    }

    private void abrirClientes() {
        ClientesView clientesView = new ClientesView();
        ClientesPresenter presenter = new ClientesPresenter(clientesView, clienteService);
        presenter.iniciar();
    }

    private void abrirClientesEmInclusao() {
        ClientesView clientesView = new ClientesView();
        ClientesPresenter presenter = new ClientesPresenter(clientesView, clienteService);
        presenter.abrirEmModoInclusao();
    }

    private void abrirUsuarios() {
        UsuariosView usuariosView = new UsuariosView();
        UsuariosPresenter presenter = new UsuariosPresenter(usuariosView, usuarioService, clienteService, usuarioLogado);
        presenter.iniciar();
    }
}
