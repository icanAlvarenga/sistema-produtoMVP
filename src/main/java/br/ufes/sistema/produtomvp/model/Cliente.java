package br.ufes.sistema.produtomvp.model;

public class Cliente {
    private int id;
    private String nome;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String tipoCliente;
    private Double totalCompras;

    public Cliente(int id, String nome, String logradouro, String bairro, String cidade, String uf) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.tipoCliente = "PRATA";
        this.totalCompras = 0.0;
    }

    public Cliente(int id, String nome, String logradouro, String bairro, String cidade, String uf, String tipoCliente, Double totalCompras) {
        this.id = id;
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.tipoCliente = tipoCliente != null ? tipoCliente : "PRATA";
        this.totalCompras = totalCompras != null ? totalCompras : 0.0;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Double getTotalCompras() {
        return totalCompras;
    }

    public void setTotalCompras(Double totalCompras) {
        this.totalCompras = totalCompras;
    }

    @Override
    public String toString() {
        return nome;
    }
}
