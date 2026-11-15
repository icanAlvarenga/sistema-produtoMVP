package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import br.ufes.sistema.produtomvp.model.Produto;
import br.ufes.sistema.produtomvp.repository.IHistoricoPrecoRepository;
import br.ufes.sistema.produtomvp.repository.IProdutoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CalculoPrecoService {
    private final IProdutoRepository produtoRepository;
    private final IHistoricoPrecoRepository historicoPrecoRepository;

    public CalculoPrecoService(IProdutoRepository produtoRepository,
                               IHistoricoPrecoRepository historicoPrecoRepository) {
        this.produtoRepository = produtoRepository;
        this.historicoPrecoRepository = historicoPrecoRepository;
    }

    public LocalDate obterUltimaDataCalculo() {
        return historicoPrecoRepository.obterUltimaDataCalculo();
    }

    public void validarIntervaloCalculo(LocalDate dataCalculo) throws ValidacaoException {
        if (dataCalculo == null) {
            throw new ValidacaoException("A data do cálculo é obrigatória.");
        }
        LocalDate ultimaData = obterUltimaDataCalculo();
        if (ultimaData != null) {
            long dias = ChronoUnit.DAYS.between(ultimaData, dataCalculo);
            if (dias < 10) {
                throw new ValidacaoException("O cálculo só pode ser realizado novamente após 10 dias.");
            }
        }
    }

    public List<HistoricoPreco> calcularPrecos(LocalDate dataCalculo) throws ValidacaoException {
        validarIntervaloCalculo(dataCalculo);

        List<Produto> produtos = produtoRepository.buscaTodos();
        List<HistoricoPreco> historicosGerados = new ArrayList<>();

        for (Produto produto : produtos) {
            double margemLucro = produto.getCategoria() != null ? produto.getCategoria().getPercLucro() : 0.0;
            double precoVenda = produto.getPrecoDeCusto() * (1.0 + (margemLucro / 100.0));
            double precoVendaArredondado = BigDecimal.valueOf(precoVenda)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            produto.atualizarCalculo(margemLucro, precoVendaArredondado);
            produtoRepository.atualizarProduto(produto);

            HistoricoPreco historico = new HistoricoPreco(
                    historicoPrecoRepository.proximoId(),
                    produto,
                    dataCalculo,
                    margemLucro,
                    precoVendaArredondado
            );
            historicoPrecoRepository.adicionar(historico);
            historicosGerados.add(historico);
        }

        return historicosGerados;
    }

    public List<HistoricoPreco> buscarHistoricoPorProduto(int produtoId) {
        return historicoPrecoRepository.buscarPorProduto(produtoId);
    }
}
