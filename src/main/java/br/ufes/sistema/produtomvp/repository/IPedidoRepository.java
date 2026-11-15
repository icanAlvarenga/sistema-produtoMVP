package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Pedido;
import java.util.List;

public interface IPedidoRepository {
    void adicionar(Pedido pedido);
    List<Pedido> buscaTodos();
    int getPedidosEmAndamento();
    int getPedidosEntreguesHoje();
    double getTotalEmVendas();
    int getEntregadoresAtivos();
}
