package br.ufes.sistema.produtomvp.repository;

import br.ufes.sistema.produtomvp.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository implements IUsuarioRepository {
    private final List<Usuario> usuarios = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public int proximoId() {
        return proximoId++;
    }

    @Override
    public void adicionar(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public void atualizar(Usuario usuario) {
        if (usuario == null) {
            return;
        }
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuario.getId()) {
                usuarios.set(i, usuario);
                return;
            }
        }
    }

    @Override
    public void remover(Usuario usuario) {
        if (usuario == null) {
            return;
        }
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuario.getId()) {
                usuarios.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Usuario> buscaTodos() {
        return new ArrayList<>(usuarios);
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        if (username == null) {
            return Optional.empty();
        }
        String termo = username.trim();
        for (Usuario u : usuarios) {
            if (u.getNomeUsuario() != null && u.getNomeUsuario().equalsIgnoreCase(termo)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }
        String termo = email.trim();
        for (Usuario u : usuarios) {
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(termo)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorUsernameOuEmail(String identificacao) {
        if (identificacao == null) {
            return Optional.empty();
        }
        String termo = identificacao.trim();
        for (Usuario u : usuarios) {
            if (u.getNomeUsuario() != null && u.getNomeUsuario().equalsIgnoreCase(termo)) {
                return Optional.of(u);
            }
            if (u.getEmail() != null && u.getEmail().equalsIgnoreCase(termo)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existeComUsername(String username, Integer idDesconsiderar) {
        if (username == null) {
            return false;
        }
        String termo = username.trim();
        for (Usuario u : usuarios) {
            if (idDesconsiderar != null && u.getId() == idDesconsiderar.intValue()) {
                continue;
            }
            if (u.getNomeUsuario() != null && u.getNomeUsuario().equalsIgnoreCase(termo)) {
                return true;
            }
        }
        return false;
    }
}
