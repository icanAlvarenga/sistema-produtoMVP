package br.ufes.sistema.produtomvp.model;

import java.time.LocalDate;

public class HistoricoPreco {
    private int id;
    private Produto produto;
    private LocalDate data;
    private double percentualLucro;
    private double precoVenda;

    public HistoricoPreco(int id, Produto produto, LocalDate data, double percentualLucro, double precoVenda) {
        this.id = id;
        this.produto = produto;
        this.data = data;
        this.percentualLucro = percentualLucro;
        this.precoVenda = precoVenda;
    }

    public int getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public LocalDate getData() {
        return data;
    }

    public double getPercentualLucro() {
        return percentualLucro;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }
}
