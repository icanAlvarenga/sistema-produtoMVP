package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Cliente;
import br.ufes.sistema.produtomvp.repository.IClienteRepository;

import java.util.List;
import java.util.Optional;

public class ClienteService {
    private final IClienteRepository clienteRepository;

    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.buscaTodos();
    }

    public Optional<Cliente> buscarPorId(int id) {
        return clienteRepository.buscarPorId(id);
    }

    public Cliente incluir(String nome, String logradouro, String bairro, String cidade, String uf) throws ValidacaoException {
        validarCampos(nome, logradouro, bairro, cidade, uf);
        Cliente cliente = new Cliente(
                clienteRepository.proximoId(),
                nome.trim(),
                logradouro.trim(),
                bairro.trim(),
                cidade.trim(),
                uf.trim()
        );
        clienteRepository.adicionar(cliente);
        return cliente;
    }

    public void atualizar(Cliente cliente, String nome, String logradouro, String bairro, String cidade, String uf) throws ValidacaoException {
        if (cliente == null) {
            throw new ValidacaoException("Cliente inválido para atualização.");
        }
        validarCampos(nome, logradouro, bairro, cidade, uf);
        cliente.setNome(nome.trim());
        cliente.setLogradouro(logradouro.trim());
        cliente.setBairro(bairro.trim());
        cliente.setCidade(cidade.trim());
        cliente.setUf(uf.trim());
        clienteRepository.atualizar(cliente);
    }

    public void excluir(Cliente cliente) throws ValidacaoException {
        if (cliente == null) {
            return;
        }
        clienteRepository.remover(cliente);
    }

    private void validarCampos(String nome, String logradouro, String bairro, String cidade, String uf) throws ValidacaoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidacaoException("O nome do cliente é obrigatório.");
        }
        if (logradouro == null || logradouro.trim().isEmpty()) {
            throw new ValidacaoException("O logradouro é obrigatório.");
        }
        if (bairro == null || bairro.trim().isEmpty()) {
            throw new ValidacaoException("O bairro é obrigatório.");
        }
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new ValidacaoException("A cidade é obrigatória.");
        }
        if (uf == null || uf.trim().isEmpty()) {
            throw new ValidacaoException("O estado (UF) é obrigatório.");
        }
    }
}
