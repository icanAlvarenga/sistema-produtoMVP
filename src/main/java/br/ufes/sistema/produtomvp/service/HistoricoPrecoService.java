package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.repository.HistoricoPrecoRepository;

import java.util.Comparator;
import java.util.List;

public class HistoricoPrecoService {
    private HistoricoPrecoRepository repository;

    public HistoricoPrecoService(HistoricoPrecoRepository repository) {
        this.repository = repository;
    }

    public void registrar(HistoricoPreco historico) {
        repository.adicionarHistorico(historico);
    }

    // Registros do produto, do cálculo mais recente para o mais antigo (regra da seção 9)
    public List<HistoricoPreco> buscarPorProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não informado.");
        }

        List<HistoricoPreco> lista = repository.buscarPorProduto(produto);
        lista.sort(Comparator.comparing(HistoricoPreco::getData).reversed());
        return lista;
    }
}
