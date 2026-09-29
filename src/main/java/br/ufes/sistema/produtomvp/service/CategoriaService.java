package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.repository.CategoriaRepository;

import java.util.List;

public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.buscaTodas();
    }


}
