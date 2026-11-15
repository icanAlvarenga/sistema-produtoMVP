package br.ufes.sistema.produtomvp.model;

public class Produto {
    private int id;
    private String nome;
    private double precoDeCusto;
    private Categoria categoria;
    private Double margemLucroAtual;
    private Double precoVendaAtual;

    public Produto(int id, String nome, double precoDeCusto, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.precoDeCusto = precoDeCusto;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoDeCusto() {
        return precoDeCusto;
    }

    public void setPrecoDeCusto(double precoDeCusto) {
        this.precoDeCusto = precoDeCusto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getMargemLucroAtual() {
        return margemLucroAtual;
    }

    public Double getPrecoVendaAtual() {
        return precoVendaAtual;
    }

    public void atualizarCalculo(Double margemLucro, Double precoVenda) {
        this.margemLucroAtual = margemLucro;
        this.precoVendaAtual = precoVenda;
    }
}
