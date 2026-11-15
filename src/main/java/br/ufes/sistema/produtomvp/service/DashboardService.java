package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.model.Pedido;
import br.ufes.sistema.produtomvp.repository.IPedidoRepository;

import java.util.List;

public class DashboardService {
    private final IPedidoRepository pedidoRepository;

    public DashboardService(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarUltimosPedidos() {
        return pedidoRepository.buscaTodos();
    }

    public int getPedidosEmAndamento() {
        return pedidoRepository.getPedidosEmAndamento();
    }

    public int getPedidosEntreguesHoje() {
        return pedidoRepository.getPedidosEntreguesHoje();
    }

    public double getTotalEmVendas() {
        return pedidoRepository.getTotalEmVendas();
    }

    public int getEntregadoresAtivos() {
        return pedidoRepository.getEntregadoresAtivos();
    }
}
