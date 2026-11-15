package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Usuario;
import br.ufes.sistema.produtomvp.repository.IUsuarioRepository;

import java.util.Optional;

public class AutenticacaoService {
    private final IUsuarioRepository usuarioRepository;

    public AutenticacaoService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario autenticar(String identificacao, String senha) throws ValidacaoException {
        if (identificacao == null || identificacao.trim().isEmpty() || senha == null || senha.isEmpty()) {
            throw new ValidacaoException("Preencha todos os campos obrigatórios.");
        }

        Optional<Usuario> optUsuario = usuarioRepository.buscarPorUsernameOuEmail(identificacao.trim());
        if (optUsuario.isEmpty()) {
            throw new ValidacaoException("Credenciais inválidas.");
        }

        Usuario usuario = optUsuario.get();
        if (!senha.equals(usuario.getSenha())) {
            throw new ValidacaoException("Credenciais inválidas.");
        }

        if (!usuario.isHabilitado()) {
            throw new ValidacaoException("Usuário desabilitado. Acesso negado.");
        }

        return usuario;
    }
}
