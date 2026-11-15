package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Pedido;
import java.util.List;

public interface ITelaPrincipalView {
    void exibir(String identificacaoUsuario, boolean podeGerenciarUsuarios);
    void fechar();
    void aoSair(Runnable acao);
    void aoMenuProdutos(Runnable acao);
    void aoMenuCategorias(Runnable acao);
    void aoMenuClientes(Runnable acao);
    void aoMenuUsuarios(Runnable acao);
    void aoNovoProduto(Runnable acao);
    void aoNovoCliente(Runnable acao);
    void aoBuscarClientes(Runnable acao);
    void exibirPedidos(List<Pedido> pedidos);
    void exibirIndicadores(int andamento, int entregues, double totalVendas, int entregadores);
}
