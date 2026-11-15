package br.ufes.sistema.produtomvp.presenter;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import br.ufes.sistema.produtomvp.service.CalculoPrecoService;
import br.ufes.sistema.produtomvp.view.ICalculoMargemLucroView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class CalculoMargemLucroPresenter {

    private final ICalculoMargemLucroView view;
    private final CalculoPrecoService calculoPrecoService;
    private static final DateTimeFormatter FORMATO_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CalculoMargemLucroPresenter(ICalculoMargemLucroView view,
                                       CalculoPrecoService calculoPrecoService) {
        this.view = view;
        this.calculoPrecoService = calculoPrecoService;

        configurarAcoes();
    }

    public void iniciar() {
        prepararDatas();
        view.abrir();
    }

    private void prepararDatas() {
        LocalDate hoje = LocalDate.now();
        List<String> datas = new ArrayList<>();
        datas.add(hoje.format(FORMATO_BR));
        datas.add(hoje.plusDays(10).format(FORMATO_BR));
        datas.add(hoje.plusDays(15).format(FORMATO_BR));

        view.setDatasDisponiveis(datas);
        view.setDataCalculo(hoje.format(FORMATO_BR));
    }

    private void configurarAcoes() {
        view.aoCalcular(new Runnable() {
            @Override
            public void run() {
                calcular();
            }
        });

        view.aoFechar(new Runnable() {
            @Override
            public void run() {
                view.fechar();
            }
        });
    }

    private void calcular() {
        String textoData = view.getDataCalculo();
        if (textoData == null || textoData.trim().isEmpty()) {
            view.exibirErro("A data do cálculo é obrigatória.");
            return;
        }

        LocalDate data;
        try {
            data = parseData(textoData.trim());
        } catch (DateTimeParseException e) {
            view.exibirErro("Formato de data inválido. Utilize o formato dd/MM/yyyy.");
            return;
        }

        try {
            List<HistoricoPreco> gerados = calculoPrecoService.calcularPrecos(data);
            view.exibirProdutosCalculados(gerados);
            view.exibirMensagem("Cálculo realizado com sucesso!");
        } catch (ValidacaoException e) {
            view.exibirErro(e.getMessage());
        }
    }

    private LocalDate parseData(String texto) {
        if (texto.contains("/")) {
            return LocalDate.parse(texto, FORMATO_BR);
        } else {
            return LocalDate.parse(texto, DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }
}
