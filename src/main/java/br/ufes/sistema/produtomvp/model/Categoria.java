
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

    public String getNome() {
        return nome;
    }

    public double getPercLucro() {
        return percLucro;
    }

    @Override
    public String toString() {
        return nome;
    }
}
