package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Usuario;
import br.ufes.sistema.produtomvp.service.AutenticacaoService;
import br.ufes.sistema.produtomvp.service.CalculoPrecoService;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.service.ClienteService;
import br.ufes.sistema.produtomvp.service.DashboardService;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.service.UsuarioService;
import br.ufes.sistema.produtomvp.view.ILoginView;
import br.ufes.sistema.produtomvp.view.ITelaPrincipalView;
import br.ufes.sistema.produtomvp.view.TelaPrincipalView;

public class LoginPresenter {

    private final ILoginView view;
    private final AutenticacaoService autenticacaoService;
    private final CategoriaService categoriaService;
    private final ProdutoService produtoService;
    private final CalculoPrecoService calculoPrecoService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final DashboardService dashboardService;

    public LoginPresenter(ILoginView view,
                          AutenticacaoService autenticacaoService,
                          CategoriaService categoriaService,
                          ProdutoService produtoService,
                          CalculoPrecoService calculoPrecoService,
                          ClienteService clienteService,
                          UsuarioService usuarioService,
                          DashboardService dashboardService) {
        this.view = view;
        this.autenticacaoService = autenticacaoService;
        this.categoriaService = categoriaService;
        this.produtoService = produtoService;
        this.calculoPrecoService = calculoPrecoService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
        this.dashboardService = dashboardService;

        configurarAcoes();
    }

    public void iniciar() {
        view.abrir();
    }

    private void configurarAcoes() {
        view.aoEntrar(new Runnable() {
            @Override
            public void run() {
                autenticar();
            }
        });

        view.aoFechar(new Runnable() {
            @Override
            public void run() {
                view.fechar();
                System.exit(0);
            }
        });
    }

    private void autenticar() {
        String identificacao = view.getIdentificacao();
        String senha = view.getSenha();

        try {
            Usuario usuarioLogado = autenticacaoService.autenticar(identificacao, senha);
            view.fechar();
            abrirTelaPrincipal(usuarioLogado);
        } catch (ValidacaoException ex) {
            view.exibirErro(ex.getMessage());
        }
    }

    private void abrirTelaPrincipal(Usuario usuarioLogado) {
        ITelaPrincipalView telaPrincipalView = new TelaPrincipalView();
        TelaPrincipalPresenter telaPrincipalPresenter = new TelaPrincipalPresenter(
                telaPrincipalView,
                usuarioLogado,
                categoriaService,
                produtoService,
                calculoPrecoService,
                clienteService,
                usuarioService,
                dashboardService,
                autenticacaoService
        );
        telaPrincipalPresenter.iniciar();
    }
}
