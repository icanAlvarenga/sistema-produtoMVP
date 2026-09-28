
package br.ufes.sistema.produtomvp.model;

public class Produto {
    private int id;
    private String nome;
    private double precoDeCusto;
    private Categoria categoria;

    public Produto(int id, String nome, double precoDeCusto, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.precoDeCusto = precoDeCusto;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoDeCusto() {
        return precoDeCusto;
    }

    
    
    public Categoria getCategoria() {
        return categoria;
    }
    
}
