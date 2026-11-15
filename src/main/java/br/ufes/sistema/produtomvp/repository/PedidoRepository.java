package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository implements IPedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();
    private int pedidosEmAndamento = 8;
    private int pedidosEntreguesHoje = 24;
    private double totalEmVendas = 1245.60;
    private int entregadoresAtivos = 5;

    @Override
    public void adicionar(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public List<Pedido> buscaTodos() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public int getPedidosEmAndamento() {
        return pedidosEmAndamento;
    }

    @Override
    public int getPedidosEntreguesHoje() {
        return pedidosEntreguesHoje;
    }

    @Override
    public double getTotalEmVendas() {
        return totalEmVendas;
    }

    @Override
    public int getEntregadoresAtivos() {
        return entregadoresAtivos;
    }
}
