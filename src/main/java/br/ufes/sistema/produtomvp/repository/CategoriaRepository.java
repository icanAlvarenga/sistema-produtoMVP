package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Categoria;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaRepository implements ICategoriaRepository {
    private final List<Categoria> categorias = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public int proximoId() {
        return proximoId++;
    }

    @Override
    public void adicionar(Categoria categoria) {
        categorias.add(categoria);
    }

    @Override
    public void atualizar(Categoria categoria) {
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId() == categoria.getId()) {
                categorias.set(i, categoria);
                return;
            }
        }
    }

    @Override
    public void remover(Categoria categoria) {
        if (categoria == null) {
            return;
        }
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId() == categoria.getId()) {
                categorias.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Categoria> buscaTodas() {
        return new ArrayList<>(categorias);
    }

    @Override
    public Optional<Categoria> buscarPorId(int id) {
        for (Categoria c : categorias) {
            if (c.getId() == id) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Categoria> buscarPorNome(String nome) {
        if (nome == null) {
            return Optional.empty();
        }
        String nomeNormalizado = nome.trim();
        for (Categoria c : categorias) {
            if (c.getNome().equalsIgnoreCase(nomeNormalizado)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }
}
