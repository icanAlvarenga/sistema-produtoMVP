package br.ufes.sistema.produtomvp;

import br.ufes.sistema.produtomvp.presenter.TelaPrincipalPresenter;
import br.ufes.sistema.produtomvp.repository.CategoriaRepository;
import br.ufes.sistema.produtomvp.repository.HistoricoPrecoRepository;
import br.ufes.sistema.produtomvp.repository.ICategoriaRepository;
import br.ufes.sistema.produtomvp.repository.IHistoricoPrecoRepository;
import br.ufes.sistema.produtomvp.repository.IProdutoRepository;
import br.ufes.sistema.produtomvp.repository.ProdutoRepository;
import br.ufes.sistema.produtomvp.seeder.Seeder;
import br.ufes.sistema.produtomvp.service.CalculoPrecoService;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.view.ITelaPrincipalView;
import br.ufes.sistema.produtomvp.view.TelaPrincipalView;

public class SistemaProdutoMVP {

    public static void main(String[] args) {
        // Inicialização dos repositórios em memória
        final ICategoriaRepository categoriaRepository = new CategoriaRepository();
        final IProdutoRepository produtoRepository = new ProdutoRepository();
        final IHistoricoPrecoRepository historicoPrecoRepository = new HistoricoPrecoRepository();

        // Execução do Seeder antes da abertura da tela principal (Requisito 8)
        Seeder.executar(categoriaRepository, produtoRepository, historicoPrecoRepository);

        // Inicialização dos serviços
        final CategoriaService categoriaService = new CategoriaService(categoriaRepository, produtoRepository);
        final ProdutoService produtoService = new ProdutoService(produtoRepository, categoriaRepository);
        final CalculoPrecoService calculoPrecoService = new CalculoPrecoService(produtoRepository, historicoPrecoRepository);

        // Abertura da interface gráfica
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ITelaPrincipalView view = new TelaPrincipalView();
                TelaPrincipalPresenter presenter = new TelaPrincipalPresenter(
                        view,
                        categoriaService,
                        produtoService,
                        calculoPrecoService
                );
                presenter.iniciar();
            }
        });
    }
}
