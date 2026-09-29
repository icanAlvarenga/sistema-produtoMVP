package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import br.ufes.sistema.produtomvp.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class HistoricoPrecoRepository {
    private List<HistoricoPreco> historicos;

    public HistoricoPrecoRepository() {
        this.historicos = new ArrayList<>();
    }

    public void adicionarHistorico(HistoricoPreco historico) {
        historicos.add(historico);
    }

    public List<HistoricoPreco> buscaTodos() {
        return new ArrayList<>(historicos);
    }

    public List<HistoricoPreco> buscarPorProduto(Produto produto) {
        List<HistoricoPreco> resultado = new ArrayList<>();

        for (HistoricoPreco h : historicos) {
            if (h.getProduto().getId() == produto.getId()) {
                resultado.add(h);
            }
        }

        return resultado;
    }
}