package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.service.CategoriaService;
import br.ufes.sistema.produtomvp.util.NumeroUtil;
import br.ufes.sistema.produtomvp.view.ICategoriasDeProdutosView;

public class CategoriaPresenter {

    private final ICategoriasDeProdutosView view;
    private final CategoriaService categoriaService;
    private boolean modoInclusao = false;
    private boolean modoEdicao = false;
    private Categoria categoriaSelecionada = null;

    public CategoriaPresenter(ICategoriasDeProdutosView view, CategoriaService categoriaService) {
        this.view = view;
        this.categoriaService = categoriaService;

        configurarAcoes();
    }

    public void iniciar() {
        carregarCategorias();
        entrarModoVisualizacao();
        view.abrir();
    }

    private void configurarAcoes() {
        view.aoNovo(new Runnable() {
            @Override
            public void run() {
                novo();
            }
        });

        view.aoEditar(new Runnable() {
            @Override
            public void run() {
                editar();
            }
        });

        view.aoExcluir(new Runnable() {
            @Override
            public void run() {
                excluir();
            }
        });

        view.aoSalvar(new Runnable() {
            @Override
            public void run() {
                salvar();
            }
        });

        view.aoCancelar(new Runnable() {
            @Override
            public void run() {
                cancelar();
            }
        });

        view.aoFechar(new Runnable() {
            @Override
            public void run() {
                fechar();
            }
        });

        view.aoSelecionarLinha(new Runnable() {
            @Override
            public void run() {
                selecionarCategoria();
            }
        });
    }

    private void carregarCategorias() {
        view.exibirCategorias(categoriaService.listarTodas());
    }

    private void entrarModoVisualizacao() {
        modoInclusao = false;
        modoEdicao = false;
        view.setModo("Modo: Visualização");
        view.setCamposHabilitados(false);
        selecionarCategoria();
    }

    private void selecionarCategoria() {
        if (!modoInclusao && !modoEdicao) {
            Categoria c = view.getCategoriaSelecionada();
            if (c != null) {
                categoriaSelecionada = c;
                view.setNomeCategoria(c.getNome());
                view.setPercentualLucro(NumeroUtil.formatar(c.getPercLucro()));
                view.setBotoesModoVisualizacao(true);
            } else {
                categoriaSelecionada = null;
                view.setNomeCategoria("");
                view.setPercentualLucro("");
                view.setBotoesModoVisualizacao(false);
            }
        }
    }

    private void novo() {
        modoInclusao = true;
        modoEdicao = false;
        categoriaSelecionada = null;
        view.setNomeCategoria("");
        view.setPercentualLucro("");
        view.setCamposHabilitados(true);
        view.setBotoesModoEdicaoOuInclusao();
        view.setModo("Modo: Inclusão");
        view.focarCampoCategoria();
    }

    private void editar() {
        Categoria c = view.getCategoriaSelecionada();
        if (c == null) {
            return;
        }
        modoInclusao = false;
        modoEdicao = true;
        categoriaSelecionada = c;
        view.setNomeCategoria(c.getNome());
        view.setPercentualLucro(NumeroUtil.formatar(c.getPercLucro()));
        view.setCamposHabilitados(true);
        view.setBotoesModoEdicaoOuInclusao();
        view.setModo("Modo: Edição");
        view.focarCampoCategoria();
    }

    private void salvar() {
        try {
            String nome = view.getNomeCategoria();
            Double percLucro = converterPercentual(view.getPercentualLucro());

            if (modoInclusao) {
                categoriaService.incluir(nome, percLucro);
            } else if (modoEdicao && categoriaSelecionada != null) {
                categoriaService.atualizar(categoriaSelecionada, nome, percLucro);
            }

            view.exibirMensagem("Item salvo com sucesso!");
            carregarCategorias();
            entrarModoVisualizacao();
        } catch (ValidacaoException e) {
            view.exibirErro(e.getMessage());
        }
    }

    private void cancelar() {
        entrarModoVisualizacao();
    }

    private void excluir() {
        Categoria c = view.getCategoriaSelecionada();
        if (c == null) {
            return;
        }

        if (view.confirmarExclusao("Deseja realmente excluir?")) {
            try {
                categoriaService.excluir(c);
                view.exibirMensagem("Item '" + c.getNome() + "' excluído com sucesso!");
                carregarCategorias();
                entrarModoVisualizacao();
            } catch (ValidacaoException e) {
                view.exibirErro(e.getMessage());
            }
        }
    }

    private void fechar() {
        view.fechar();
    }

    private Double converterPercentual(String texto) throws ValidacaoException {
        try {
            return NumeroUtil.parse(texto);
        } catch (NumberFormatException e) {
            throw new ValidacaoException("Percentual de lucro inválido. Digite apenas números.");
        }
    }
}
