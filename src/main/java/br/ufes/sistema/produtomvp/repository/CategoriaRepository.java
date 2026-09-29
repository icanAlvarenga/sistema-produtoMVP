package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaRepository {
    private final List<Categoria> categorias = new ArrayList<>();
    private int proximoId = 1;

    public int proximoId() {
        return proximoId++;
    }

    public void adicionar(Categoria categoria) {
        categorias.add(categoria);
    }

    public void remover(Categoria categoria) {
        categorias.removeIf(c -> c.getId() == categoria.getId());
    }

    public List<Categoria> buscaTodas() {
        return new ArrayList<>(categorias);
    }

    public Optional<Categoria> buscarPorId(int id) {
        return categorias.stream().filter(c -> c.getId() == id).findFirst();
    }

    public Optional<Categoria> buscarPorNome(String nome) {
        if (nome == null) {
            return Optional.empty();
        }
        return categorias.stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome.trim()))
                .findFirst();
    }
}