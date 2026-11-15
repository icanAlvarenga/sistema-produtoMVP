package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface IClienteRepository {
    int proximoId();
    void adicionar(Cliente cliente);
    void atualizar(Cliente cliente);
    void remover(Cliente cliente);
    List<Cliente> buscaTodos();
    Optional<Cliente> buscarPorId(int id);
    Optional<Cliente> buscarPorNome(String nome);
}
