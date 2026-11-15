package br.ufes.sistema.produtomvp.model;

public class Categoria {
    private int id;
    private String nome;
    private double percLucro;

    public Categoria(int id, String nome, double percLucro) {
        this.id = id;
        this.nome = nome;
        this.percLucro = percLucro;
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

    public double getPercLucro() {
        return percLucro;
    }

    public void setPercLucro(double percLucro) {
        this.percLucro = percLucro;
    }

    @Override
    public String toString() {
        return nome;
    }
}
