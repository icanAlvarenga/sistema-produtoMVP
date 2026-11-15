package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Cliente;
import br.ufes.sistema.produtomvp.model.Usuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UsuariosView extends javax.swing.JFrame implements IUsuariosView {

    private List<Usuario> usuariosExibidos;
    private Runnable acaoNovo;
    private Runnable acaoEditar;
    private Runnable acaoExcluir;
    private Runnable acaoHabilitar;
    private Runnable acaoDesabilitar;
    private Runnable acaoSalvar;
    private Runnable acaoCancelar;
    private Runnable acaoFechar;
    private Runnable acaoSelecionarLinha;
    private Runnable acaoMudarPerfil;
    private Runnable acaoIncluirCliente;

    public UsuariosView() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        usuariosExibidos = new ArrayList<Usuario>();

        tblUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && acaoSelecionarLinha != null) {
                    acaoSelecionarLinha.run();
                }
            }
        });

        configurarAcoes();
    }

    private void configurarAcoes() {
        btnNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoNovo != null) {
                    acaoNovo.run();
                }
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoEditar != null) {
                    acaoEditar.run();
                }
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoExcluir != null) {
                    acaoExcluir.run();
                }
            }
        });

        btnHabilitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoHabilitar != null) {
                    acaoHabilitar.run();
                }
            }
        });

        btnDesabilitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoDesabilitar != null) {
                    acaoDesabilitar.run();
                }
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoSalvar != null) {
                    acaoSalvar.run();
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoCancelar != null) {
                    acaoCancelar.run();
                }
            }
        });

        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoFechar != null) {
                    acaoFechar.run();
                } else {
                    fechar();
                }
            }
        });

        btnToggleSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtSenha.getEchoChar() == (char) 0) {
                    txtSenha.setEchoChar('\u2022');
                } else {
                    txtSenha.setEchoChar((char) 0);
                }
            }
        });

        btnToggleConfirmarSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtConfirmarSenha.getEchoChar() == (char) 0) {
                    txtConfirmarSenha.setEchoChar('\u2022');
                } else {
                    txtConfirmarSenha.setEchoChar((char) 0);
                }
            }
        });

        cmbPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoMudarPerfil != null) {
                    acaoMudarPerfil.run();
                }
            }
        });

        btnIncluirCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoIncluirCliente != null) {
                    acaoIncluirCliente.run();
                }
            }
        });
    }

    @Override
    public String getNomeCompleto() {
        return txtNomeCompleto.getText();
    }

    @Override
    public void setNomeCompleto(String nome) {
        txtNomeCompleto.setText(nome != null ? nome : "");
    }

    @Override
    public String getEmail() {
        return txtEmail.getText();
    }

    @Override
    public void setEmail(String email) {
        txtEmail.setText(email != null ? email : "");
    }

    @Override
    public String getNomeUsuario() {
        return txtNomeUsuario.getText();
    }

    @Override
    public void setNomeUsuario(String username) {
        txtNomeUsuario.setText(username != null ? username : "");
    }

    @Override
    public String getSenha() {
        return new String(txtSenha.getPassword());
    }

    @Override
    public void setSenha(String senha) {
        txtSenha.setText(senha != null ? senha : "");
    }

    @Override
    public String getConfirmarSenha() {
        return new String(txtConfirmarSenha.getPassword());
    }

    @Override
    public void setConfirmarSenha(String senha) {
        txtConfirmarSenha.setText(senha != null ? senha : "");
    }

    @Override
    public String getPerfil() {
        Object selecionado = cmbPerfil.getSelectedItem();
        return selecionado != null ? selecionado.toString() : "";
    }

    @Override
    public void setPerfil(String perfil) {
        if (perfil != null) {
            cmbPerfil.setSelectedItem(perfil);
        }
    }

    @Override
    public String getStatus() {
        return txtStatus.getText();
    }

    @Override
    public void setStatus(String status) {
        txtStatus.setText(status != null ? status : "");
    }

    @Override
    public Cliente getClienteAssociado() {
        Object selecionado = cmbClienteAssociado.getSelectedItem();
        if (selecionado instanceof Cliente) {
            return (Cliente) selecionado;
        }
        return null;
    }

    @Override
    public void setClienteAssociado(Cliente cliente) {
        if (cliente != null) {
            cmbClienteAssociado.setSelectedItem(cliente);
        } else {
            if (cmbClienteAssociado.getItemCount() > 0) {
                cmbClienteAssociado.setSelectedIndex(0);
            }
        }
    }

    @Override
    public void setClientesDisponiveis(List<Cliente> clientes) {
        DefaultComboBoxModel<Object> modelo = new DefaultComboBoxModel<Object>();
        if (clientes != null) {
            for (Cliente c : clientes) {
                modelo.addElement(c);
            }
        }
        cmbClienteAssociado.setModel(modelo);
    }

    @Override
    public Usuario getUsuarioSelecionado() {
        int linha = tblUsuarios.getSelectedRow();
        if (linha == -1 || linha >= usuariosExibidos.size()) {
            return null;
        }
        return usuariosExibidos.get(linha);
    }

    @Override
    public void exibirUsuarios(List<Usuario> usuarios) {
        this.usuariosExibidos = usuarios != null ? usuarios : new ArrayList<Usuario>();
        DefaultTableModel modelo = (DefaultTableModel) tblUsuarios.getModel();
        modelo.setRowCount(0);

        for (Usuario u : this.usuariosExibidos) {
            String clienteNome = "";
            if (u.getClienteAssociado() != null) {
                clienteNome = u.getClienteAssociado().getNome();
            }
            modelo.addRow(new Object[]{
                    u.getNomeCompleto(),
                    u.getEmail(),
                    u.getNomeUsuario(),
                    u.getPerfil(),
                    clienteNome,
                    u.getStatus()
            });
        }
    }

    @Override
    public void setModo(String modo) {
        lblModo.setText(modo);
    }

    @Override
    public void setCamposHabilitados(boolean habilitados, boolean habilitarClienteAssociado) {
        txtNomeCompleto.setEnabled(habilitados);
        txtEmail.setEnabled(habilitados);
        txtNomeUsuario.setEnabled(habilitados);
        txtSenha.setEnabled(habilitados);
        txtConfirmarSenha.setEnabled(habilitados);
        btnToggleSenha.setEnabled(habilitados);
        btnToggleConfirmarSenha.setEnabled(habilitados);
        cmbPerfil.setEnabled(habilitados);
        cmbClienteAssociado.setEnabled(habilitados && habilitarClienteAssociado);
        btnIncluirCliente.setEnabled(habilitados && habilitarClienteAssociado);
    }

    @Override
    public void setBotoesModoVisualizacao(boolean temSelecao, boolean isAdministradorSelecionado, boolean isHabilitado) {
        btnNovo.setEnabled(true);
        btnEditar.setEnabled(temSelecao);
        btnExcluir.setEnabled(temSelecao && !isAdministradorSelecionado);
        btnHabilitar.setEnabled(temSelecao && !isAdministradorSelecionado && !isHabilitado);
        btnDesabilitar.setEnabled(temSelecao && !isAdministradorSelecionado && isHabilitado);
        btnSalvar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnFechar.setEnabled(true);
    }

    @Override
    public void setBotoesModoEdicaoOuInclusao() {
        btnNovo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnHabilitar.setEnabled(false);
        btnDesabilitar.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnFechar.setEnabled(false);
    }

    @Override
    public void focarCampoNomeCompleto() {
        txtNomeCompleto.requestFocus();
    }

    @Override
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public boolean confirmarExclusao(String mensagem) {
        int resp = JOptionPane.showConfirmDialog(
                this,
                mensagem,
                "Confirmação de exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        return resp == JOptionPane.YES_OPTION;
    }

    @Override
    public void abrir() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void fechar() {
        dispose();
    }

    @Override
    public void aoNovo(Runnable acao) {
        this.acaoNovo = acao;
    }

    @Override
    public void aoEditar(Runnable acao) {
        this.acaoEditar = acao;
    }

    @Override
    public void aoExcluir(Runnable acao) {
        this.acaoExcluir = acao;
    }

    @Override
    public void aoHabilitar(Runnable acao) {
        this.acaoHabilitar = acao;
    }

    @Override
    public void aoDesabilitar(Runnable acao) {
        this.acaoDesabilitar = acao;
    }

    @Override
    public void aoSalvar(Runnable acao) {
        this.acaoSalvar = acao;
    }

    @Override
    public void aoCancelar(Runnable acao) {
        this.acaoCancelar = acao;
    }

    @Override
    public void aoFechar(Runnable acao) {
        this.acaoFechar = acao;
    }

    @Override
    public void aoSelecionarLinha(Runnable acao) {
        this.acaoSelecionarLinha = acao;
    }

    @Override
    public void aoMudarPerfil(Runnable acao) {
        this.acaoMudarPerfil = acao;
    }

    @Override
    public void aoIncluirCliente(Runnable acao) {
        this.acaoIncluirCliente = acao;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDetalhes = new javax.swing.JPanel();
        lblNomeCompleto = new javax.swing.JLabel();
        txtNomeCompleto = new javax.swing.JTextField();
        lblModo = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblNomeUsuario = new javax.swing.JLabel();
        txtNomeUsuario = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnToggleSenha = new javax.swing.JButton();
        lblConfirmarSenha = new javax.swing.JLabel();
        txtConfirmarSenha = new javax.swing.JPasswordField();
        btnToggleConfirmarSenha = new javax.swing.JButton();
        lblPerfil = new javax.swing.JLabel();
        cmbPerfil = new javax.swing.JComboBox<String>();
        lblStatus = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        lblClienteAssociado = new javax.swing.JLabel();
        cmbClienteAssociado = new javax.swing.JComboBox<Object>();
        btnIncluirCliente = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnHabilitar = new javax.swing.JButton();
        btnDesabilitar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        pnlCadastrados = new javax.swing.JPanel();
        scrollUsuarios = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuários");

        pnlDetalhes.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalhes do Usuário"));

        lblNomeCompleto.setText("Nome completo:");
        lblModo.setText("Modo: Visualização");
        lblEmail.setText("E-mail:");
        lblNomeUsuario.setText("Nome de usuário:");
        lblSenha.setText("Senha:");
        btnToggleSenha.setText("\uD83D\uDC41");
        lblConfirmarSenha.setText("Confirmar senha:");
        btnToggleConfirmarSenha.setText("\uD83D\uDC41");
        lblPerfil.setText("Perfil do usuário:");

        cmbPerfil.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[]{"Administrador", "Atendente", "Cliente"}));

        lblStatus.setText("Status:");
        txtStatus.setEditable(false);
        lblClienteAssociado.setText("Cliente associado:");
        btnIncluirCliente.setText("Incluir cliente");
        btnNovo.setText("Novo");
        btnEditar.setText("Editar");
        btnExcluir.setText("Excluir");
        btnHabilitar.setText("Habilitar");
        btnDesabilitar.setText("Desabilitar");
        btnSalvar.setText("Salvar");
        btnCancelar.setText("Cancelar");
        btnFechar.setText("Fechar");

        javax.swing.GroupLayout pnlDetalhesLayout = new javax.swing.GroupLayout(pnlDetalhes);
        pnlDetalhes.setLayout(pnlDetalhesLayout);
        pnlDetalhesLayout.setHorizontalGroup(
            pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalhesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalhesLayout.createSequentialGroup()
                        .addComponent(lblClienteAssociado)
                        .addGap(10, 10, 10)
                        .addComponent(cmbClienteAssociado, 480, 480, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(btnIncluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetalhesLayout.createSequentialGroup()
                        .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNomeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDetalhesLayout.createSequentialGroup()
                                .addComponent(txtNomeCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(lblModo))
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlDetalhesLayout.createSequentialGroup()
                                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlDetalhesLayout.createSequentialGroup()
                                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(btnToggleSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20)
                                        .addComponent(lblConfirmarSenha)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(btnToggleConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlDetalhesLayout.createSequentialGroup()
                                        .addComponent(cmbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(lblStatus)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlDetalhesLayout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnHabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnDesabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        pnlDetalhesLayout.setVerticalGroup(
            pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalhesLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCompleto)
                    .addComponent(txtNomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModo))
                .addGap(8, 8, 8)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeUsuario)
                    .addComponent(txtNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSenha)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnToggleSenha)
                    .addComponent(lblConfirmarSenha)
                    .addComponent(txtConfirmarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnToggleConfirmarSenha))
                .addGap(8, 8, 8)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPerfil)
                    .addComponent(cmbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClienteAssociado)
                    .addComponent(cmbClienteAssociado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIncluirCliente))
                .addGap(16, 16, 16)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnHabilitar)
                    .addComponent(btnDesabilitar)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnFechar))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pnlCadastrados.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuários cadastrados"));
        pnlCadastrados.setLayout(new java.awt.BorderLayout());

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Nome completo", "E-mail", "Usuário", "Perfil", "Cliente associado", "Status"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        scrollUsuarios.setViewportView(tblUsuarios);
        pnlCadastrados.add(scrollUsuarios, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalhes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCadastrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(pnlDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlCadastrados, 270, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesabilitar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnHabilitar;
    private javax.swing.JButton btnIncluirCliente;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnToggleConfirmarSenha;
    private javax.swing.JButton btnToggleSenha;
    private javax.swing.JComboBox<Object> cmbClienteAssociado;
    private javax.swing.JComboBox<String> cmbPerfil;
    private javax.swing.JLabel lblClienteAssociado;
    private javax.swing.JLabel lblConfirmarSenha;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblModo;
    private javax.swing.JLabel lblNomeCompleto;
    private javax.swing.JLabel lblNomeUsuario;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPanel pnlCadastrados;
    private javax.swing.JPanel pnlDetalhes;
    private javax.swing.JScrollPane scrollUsuarios;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNomeCompleto;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JPasswordField txtConfirmarSenha;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtStatus;
    // End of variables declaration//GEN-END:variables
}
