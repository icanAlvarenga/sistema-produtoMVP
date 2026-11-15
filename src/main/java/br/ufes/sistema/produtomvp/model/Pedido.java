package br.ufes.sistema.produtomvp.model;

public class Pedido {
    private int numeroPedido;
    private String dataHora;
    private String cliente;
    private String status;
    private String formaPagamento;
    private Double total;

    public Pedido(int numeroPedido, String dataHora, String cliente, String status, String formaPagamento, Double total) {
        this.numeroPedido = numeroPedido;
        this.dataHora = dataHora;
        this.cliente = cliente;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.total = total;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public String getDataHora() {
        return dataHora;
    }

    public String getCliente() {
        return cliente;
    }

    public String getStatus() {
        return status;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public Double getTotal() {
        return total;
    }
}
