package br.ufes.sistema.produtomvp.service;

import br.ufes.sistema.produtomvp.exception.ValidacaoException;
import br.ufes.sistema.produtomvp.model.Cliente;
import br.ufes.sistema.produtomvp.model.Usuario;
import br.ufes.sistema.produtomvp.repository.IUsuarioRepository;

import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.buscaTodos();
    }

    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.buscarPorId(id);
    }

    public void incluir(String nomeCompleto, String email, String nomeUsuario, String senha, String confirmarSenha, String perfil, Cliente clienteAssociado) throws ValidacaoException {
        validarDados(null, nomeCompleto, email, nomeUsuario, senha, confirmarSenha, perfil, clienteAssociado, true);

        Cliente cliente = "Cliente".equalsIgnoreCase(perfil) ? clienteAssociado : null;
        Usuario usuario = new Usuario(
                usuarioRepository.proximoId(),
                nomeCompleto.trim(),
                email.trim(),
                nomeUsuario.trim(),
                senha,
                perfil.trim(),
                "Habilitado",
                cliente
        );
        usuarioRepository.adicionar(usuario);
    }

    public void atualizar(Usuario usuario, String nomeCompleto, String email, String nomeUsuario, String senha, String confirmarSenha, String perfil, Cliente clienteAssociado) throws ValidacaoException {
        if (usuario == null) {
            throw new ValidacaoException("Usuário inválido para atualização.");
        }
        boolean alterarSenha = (senha != null && !senha.isEmpty());
        validarDados(usuario.getId(), nomeCompleto, email, nomeUsuario, senha, confirmarSenha, perfil, clienteAssociado, alterarSenha);

        usuario.setNomeCompleto(nomeCompleto.trim());
        usuario.setEmail(email.trim());
        usuario.setNomeUsuario(nomeUsuario.trim());
        if (alterarSenha) {
            usuario.setSenha(senha);
        }
        usuario.setPerfil(perfil.trim());
        usuario.setClienteAssociado("Cliente".equalsIgnoreCase(perfil) ? clienteAssociado : null);

        usuarioRepository.atualizar(usuario);
    }

    public void habilitar(Usuario usuario, Usuario usuarioLogado) throws ValidacaoException {
        if (usuario == null) {
            return;
        }
        if (usuarioLogado != null && !usuarioLogado.isAdministrador()) {
            throw new ValidacaoException("Apenas o Administrador pode habilitar usuários.");
        }
        usuario.setStatus("Habilitado");
        usuarioRepository.atualizar(usuario);
    }

    public void desabilitar(Usuario usuario, Usuario usuarioLogado) throws ValidacaoException {
        if (usuario == null) {
            return;
        }
        if (usuarioLogado != null && !usuarioLogado.isAdministrador()) {
            throw new ValidacaoException("Apenas o Administrador pode desabilitar usuários.");
        }
        if (usuario.isAdministrador()) {
            throw new ValidacaoException("O Administrador não pode ser desabilitado.");
        }
        usuario.setStatus("Desabilitado");
        usuarioRepository.atualizar(usuario);
    }

    public void excluir(Usuario usuario, Usuario usuarioLogado) throws ValidacaoException {
        if (usuario == null) {
            return;
        }
        if (usuarioLogado != null && !usuarioLogado.isAdministrador()) {
            throw new ValidacaoException("Apenas o Administrador pode excluir usuários.");
        }
        if (usuario.isAdministrador()) {
            throw new ValidacaoException("O registro do Administrador não pode ser excluído.");
        }
        usuarioRepository.remover(usuario);
    }

    private void validarDados(Integer idAtual, String nomeCompleto, String email, String nomeUsuario, String senha, String confirmarSenha, String perfil, Cliente clienteAssociado, boolean validarSenha) throws ValidacaoException {
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new ValidacaoException("O nome completo é obrigatório.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new ValidacaoException("O e-mail é obrigatório.");
        }
        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            throw new ValidacaoException("O nome de usuário é obrigatório.");
        }
        if (perfil == null || perfil.trim().isEmpty()) {
            throw new ValidacaoException("O perfil do usuário é obrigatório.");
        }

        if (usuarioRepository.existeComUsername(nomeUsuario, idAtual)) {
            throw new ValidacaoException("Já existe um usuário cadastrado com este nome de usuário.");
        }

        if (validarSenha) {
            if (senha == null || senha.isEmpty()) {
                throw new ValidacaoException("A senha é obrigatória.");
            }
            if (confirmarSenha == null || confirmarSenha.isEmpty()) {
                throw new ValidacaoException("A confirmação de senha é obrigatória.");
            }
            if (!senha.equals(confirmarSenha)) {
                throw new ValidacaoException("As senhas não conferem.");
            }
        }

        if ("Cliente".equalsIgnoreCase(perfil.trim())) {
            if (clienteAssociado == null) {
                throw new ValidacaoException("Usuários do perfil Cliente devem possuir um cliente associado.");
            }
        }
    }
}
