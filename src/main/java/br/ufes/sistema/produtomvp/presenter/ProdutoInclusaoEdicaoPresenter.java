package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.service.ProdutoService;
import br.ufes.sistema.produtomvp.util.NumeroUtil;
import br.ufes.sistema.produtomvp.view.IProdutoInclusaoEdicaoView;

public class ProdutoInclusaoEdicaoPresenter {

    private final IProdutoInclusaoEdicaoView view;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final Produto produtoEmEdicao;
    private final Runnable aoConcluir;

    public ProdutoInclusaoEdicaoPresenter(IProdutoInclusaoEdicaoView view, ProdutoService produtoService, CategoriaService categoriaService, Produto produtoEmEdicao, Runnable aoConcluir) {
        this.view = view;
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
        this.produtoEmEdicao = produtoEmEdicao;
        this.aoConcluir = aoConcluir;

        view.aoSalvar(this::salvar);
        view.aoCancelar(this::cancelar);
        configurarTela();
    }

    public void iniciar() {
        view.abrir();
    }

    private void configurarTela() {
        // lista vem do repositório, nunca fixa na tela
        view.setCategorias(categoriaService.listarTodas());

        if (produtoEmEdicao == null) {
            view.setTitulo("Produto - Inclusão");
            view.setNome("");
            view.setPrecoCusto("");
            view.setMargemLucro("");
            view.setPrecoVenda("");
        } else {
            view.setTitulo("Produto - Edição");
            view.setNome(produtoEmEdicao.getNome());
            view.setPrecoCusto(NumeroUtil.formatar(produtoEmEdicao.getPrecoDeCusto()));
            view.setCategoriaSelecionada(produtoEmEdicao.getCategoria());
            view.setMargemLucro(NumeroUtil.formatar(produtoEmEdicao.getMargemLucroAtual()));
            view.setPrecoVenda(NumeroUtil.formatar(produtoEmEdicao.getPrecoVendaAtual()));
        }
    }

    private void salvar() {
        try {
            String nome = view.getNome();
            Double precoCusto = converterPrecoCusto(view.getPrecoCusto());
            Categoria categoria = view.getCategoriaSelecionada();

            if (produtoEmEdicao == null) {
                produtoService.incluir(nome, precoCusto, categoria);
            } else {
                produtoService.atualizar(produtoEmEdicao, nome, precoCusto, categoria);
            }

            view.exibirMensagem("Item salvo com sucesso!");
            view.fechar();
            if (aoConcluir != null) {
                aoConcluir.run();
            }
        } catch (ValidacaoException e) {
            view.exibirErro(e.getMessage());
        }
    }

    private void cancelar() {
        view.fechar();
    }

    private Double converterPrecoCusto(String texto) throws ValidacaoException {
        try {
            return NumeroUtil.parse(texto);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Preço de custo inválido. Digite apenas números.");
        }
    }
}