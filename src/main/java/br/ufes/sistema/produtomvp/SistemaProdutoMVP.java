package br.ufes.sistema.produtomvp;

import br.ufes.sistema.produtomvp.presenter.LoginPresenter;
import br.ufes.sistema.produtomvp.repository.CategoriaRepository;
import br.ufes.sistema.produtomvp.repository.ClienteRepository;
import br.ufes.sistema.produtomvp.repository.HistoricoPrecoRepository;
import br.ufes.sistema.produtomvp.repository.ICategoriaRepository;
import br.ufes.sistema.produtomvp.repository.IClienteRepository;
import br.ufes.sistema.produtomvp.repository.IHistoricoPrecoRepository;
import br.ufes.sistema.produtomvp.repository.IPedidoRepository;
import br.ufes.sistema.produtomvp.repository.IProdutoRepository;
import br.ufes.sistema.produtomvp.repository.IUsuarioRepository;
import br.ufes.sistema.produtomvp.repository.PedidoRepository;
import br.ufes.sistema.produtomvp.repository.ProdutoRepository;
import br.ufes.sistema.produtomvp.repository.UsuarioRepository;
import br.ufes.sistema.produtomvp.seeder.Seeder;
import br.ufes.sistema.produtomvp.service.AutenticacaoService;
import br.ufes.sistema.produtomvp.service.CalculoPrecoService;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.service.ClienteService;
import br.ufes.sistema.produtomvp.service.DashboardService;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.service.UsuarioService;
import br.ufes.sistema.produtomvp.view.ILoginView;
import br.ufes.sistema.produtomvp.view.LoginView;

public class SistemaProdutoMVP {

    public static void main(String[] args) {
        // Inicialização dos repositórios em memória
        final ICategoriaRepository categoriaRepository = new CategoriaRepository();
        final IProdutoRepository produtoRepository = new ProdutoRepository();
        final IHistoricoPrecoRepository historicoPrecoRepository = new HistoricoPrecoRepository();
        final IClienteRepository clienteRepository = new ClienteRepository();
        final IUsuarioRepository usuarioRepository = new UsuarioRepository();
        final IPedidoRepository pedidoRepository = new PedidoRepository();

        // Execução do Seeder (popula todos os repositórios antes da abertura da tela)
        Seeder.executar(
                categoriaRepository,
                produtoRepository,
                historicoPrecoRepository,
                clienteRepository,
                usuarioRepository,
                pedidoRepository
        );

        // Inicialização dos serviços
        final CategoriaService categoriaService = new CategoriaService(categoriaRepository, produtoRepository);
        final ProdutoService produtoService = new ProdutoService(produtoRepository, categoriaRepository);
        final CalculoPrecoService calculoPrecoService = new CalculoPrecoService(produtoRepository, historicoPrecoRepository);
        final ClienteService clienteService = new ClienteService(clienteRepository);
        final UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        final AutenticacaoService autenticacaoService = new AutenticacaoService(usuarioRepository);
        final DashboardService dashboardService = new DashboardService(pedidoRepository);

        // Abertura da interface gráfica pela tela de login
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
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
        });
    }
}
