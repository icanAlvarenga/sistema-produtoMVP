package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HistoricoPrecoRepository implements IHistoricoPrecoRepository {
    private final List<HistoricoPreco> historicos = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public int proximoId() {
        return proximoId++;
    }

    @Override
    public void adicionar(HistoricoPreco historico) {
        historicos.add(historico);
    }

    @Override
    public List<HistoricoPreco> buscaTodos() {
        return new ArrayList<>(historicos);
    }

    @Override
    public List<HistoricoPreco> buscarPorProduto(int produtoId) {
        List<HistoricoPreco> resultado = new ArrayList<>();
        for (HistoricoPreco h : historicos) {
            if (h.getProduto() != null && h.getProduto().getId() == produtoId) {
                resultado.add(h);
            }
        }
        // Ordenação: primeiro o cálculo mais recente (data decrescente)
        Collections.sort(resultado, new Comparator<HistoricoPreco>() {
            @Override
            public int compare(HistoricoPreco o1, HistoricoPreco o2) {
                if (o1.getData() == null || o2.getData() == null) {
                    return 0;
                }
                return o2.getData().compareTo(o1.getData());
            }
        });
        return resultado;
    }

    @Override
    public LocalDate obterUltimaDataCalculo() {
        LocalDate maisRecente = null;
        for (HistoricoPreco h : historicos) {
            if (h.getData() != null) {
                if (maisRecente == null || h.getData().isAfter(maisRecente)) {
                    maisRecente = h.getData();
                }
            }
        }
        return maisRecente;
    }
}
