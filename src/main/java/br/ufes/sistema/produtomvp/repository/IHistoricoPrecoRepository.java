package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import java.time.LocalDate;
import java.util.List;

public interface IHistoricoPrecoRepository {
    int proximoId();
    void adicionar(HistoricoPreco historico);
    List<HistoricoPreco> buscaTodos();
    List<HistoricoPreco> buscarPorProduto(int produtoId);
    LocalDate obterUltimaDataCalculo();
}
