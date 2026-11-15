package br.ufes.sistema.produtomvp.model;

public class Usuario {
    private int id;
    private String nomeCompleto;
    private String email;
    private String nomeUsuario;
    private String senha;
    private String perfil;
    private String status;
    private Cliente clienteAssociado;

    public Usuario(int id, String nomeCompleto, String email, String nomeUsuario, String senha, String perfil, String status, Cliente clienteAssociado) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.perfil = perfil;
        this.status = status != null ? status : "Habilitado";
        this.clienteAssociado = clienteAssociado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getClienteAssociado() {
        return clienteAssociado;
    }

    public void setClienteAssociado(Cliente clienteAssociado) {
        this.clienteAssociado = clienteAssociado;
    }

    public boolean isHabilitado() {
        return "Habilitado".equalsIgnoreCase(this.status);
    }

    public boolean isAdministrador() {
        return "Administrador".equalsIgnoreCase(this.perfil);
    }

    public boolean isAtendente() {
        return "Atendente".equalsIgnoreCase(this.perfil);
    }

    public boolean isCliente() {
        return "Cliente".equalsIgnoreCase(this.perfil);
    }

    @Override
    public String toString() {
        return nomeUsuario != null ? nomeUsuario : "";
    }
}
