package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.repository.IHistoricoPrecoRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HistoricoPrecoService {
    private final IHistoricoPrecoRepository repository;

    public HistoricoPrecoService(IHistoricoPrecoRepository repository) {
        this.repository = repository;
    }

    public void registrar(HistoricoPreco historico) {
        repository.adicionar(historico);
    }

    // Registros do produto, do cálculo mais recente para o mais antigo (regra da seção 9)
    public List<HistoricoPreco> buscarPorProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não informado.");
        }

        List<HistoricoPreco> lista = repository.buscarPorProduto(produto.getId());
        Collections.sort(lista, new Comparator<HistoricoPreco>() {
            @Override
            public int compare(HistoricoPreco o1, HistoricoPreco o2) {
                if (o1.getData() == null || o2.getData() == null) {
                    return 0;
                }
                return o2.getData().compareTo(o1.getData());
            }
        });
        return lista;
    }
}
