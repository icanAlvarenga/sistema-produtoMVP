package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository implements IClienteRepository {
    private final List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public int proximoId() {
        return proximoId++;
    }

    @Override
    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    @Override
    public void atualizar(Cliente cliente) {
        if (cliente == null) {
            return;
        }
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                return;
            }
        }
    }

    @Override
    public void remover(Cliente cliente) {
        if (cliente == null) {
            return;
        }
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Cliente> buscaTodos() {
        return new ArrayList<>(clientes);
    }

    @Override
    public Optional<Cliente> buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cliente> buscarPorNome(String nome) {
        if (nome == null) {
            return Optional.empty();
        }
        String termo = nome.trim();
        for (Cliente c : clientes) {
            if (c.getNome() != null && c.getNome().equalsIgnoreCase(termo)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
}
