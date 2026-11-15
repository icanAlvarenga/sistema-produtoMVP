package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Cliente;
import br.ufes.sistema.produtomvp.util.NumeroUtil;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientesView extends javax.swing.JFrame implements IClientesView {

    private List<Cliente> clientesExibidos;
    private Runnable acaoNovo;
    private Runnable acaoEditar;
    private Runnable acaoExcluir;
    private Runnable acaoSalvar;
    private Runnable acaoCancelar;
    private Runnable acaoFechar;
    private Runnable acaoSelecionarLinha;

    public ClientesView() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        clientesExibidos = new ArrayList<>();

        tblClientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
    }

    @Override
    public String getNome() {
        return txtNome.getText();
    }

    @Override
    public void setNome(String nome) {
        txtNome.setText(nome != null ? nome : "");
    }

    @Override
    public String getLogradouro() {
        return txtLogradouro.getText();
    }

    @Override
    public void setLogradouro(String logradouro) {
        txtLogradouro.setText(logradouro != null ? logradouro : "");
    }

    @Override
    public String getBairro() {
        return txtBairro.getText();
    }

    @Override
    public void setBairro(String bairro) {
        txtBairro.setText(bairro != null ? bairro : "");
    }

    @Override
    public String getCidade() {
        return txtCidade.getText();
    }

    @Override
    public void setCidade(String cidade) {
        txtCidade.setText(cidade != null ? cidade : "");
    }

    @Override
    public String getUf() {
        return txtUf.getText();
    }

    @Override
    public void setUf(String uf) {
        txtUf.setText(uf != null ? uf : "");
    }

    @Override
    public String getTipoCliente() {
        return txtTipoCliente.getText();
    }

    @Override
    public void setTipoCliente(String tipoCliente) {
        txtTipoCliente.setText(tipoCliente != null ? tipoCliente : "PRATA");
    }

    @Override
    public String getTotalCompras() {
        return txtTotalCompras.getText();
    }

    @Override
    public void setTotalCompras(String totalCompras) {
        txtTotalCompras.setText(totalCompras != null ? totalCompras : "0,00");
    }

    @Override
    public Cliente getClienteSelecionado() {
        int linha = tblClientes.getSelectedRow();
        if (linha == -1 || linha >= clientesExibidos.size()) {
            return null;
        }
        return clientesExibidos.get(linha);
    }

    @Override
    public void exibirClientes(List<Cliente> clientes) {
        this.clientesExibidos = clientes != null ? clientes : new ArrayList<Cliente>();
        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setRowCount(0);

        for (Cliente c : this.clientesExibidos) {
            modelo.addRow(new Object[]{
                    c.getNome(),
                    c.getCidade(),
                    c.getUf(),
                    c.getBairro(),
                    c.getTipoCliente(),
                    NumeroUtil.formatar(c.getTotalCompras())
            });
        }
    }

    @Override
    public void setModo(String modo) {
        lblModo.setText(modo);
    }

    @Override
    public void setCamposHabilitados(boolean habilitados) {
        txtNome.setEnabled(habilitados);
        txtLogradouro.setEnabled(habilitados);
        txtBairro.setEnabled(habilitados);
        txtCidade.setEnabled(habilitados);
        txtUf.setEnabled(habilitados);
        txtTipoCliente.setEnabled(false);
        txtTotalCompras.setEnabled(false);
    }

    @Override
    public void setBotoesModoVisualizacao(boolean temSelecao) {
        btnNovo.setEnabled(true);
        btnEditar.setEnabled(temSelecao);
        btnExcluir.setEnabled(temSelecao);
        btnSalvar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnFechar.setEnabled(true);
    }

    @Override
    public void setBotoesModoEdicaoOuInclusao() {
        btnNovo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnFechar.setEnabled(false);
    }

    @Override
    public void focarCampoNome() {
        txtNome.requestFocus();
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
    public void abrirEmModoInclusao() {
        setLocationRelativeTo(null);
        setVisible(true);
        if (acaoNovo != null) {
            acaoNovo.run();
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDetalhes = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblModo = new javax.swing.JLabel();
        pnlEndereco = new javax.swing.JPanel();
        lblLogradouro = new javax.swing.JLabel();
        txtLogradouro = new javax.swing.JTextField();
        lblBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        lblCidade = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        lblUf = new javax.swing.JLabel();
        txtUf = new javax.swing.JTextField();
        lblTipoCliente = new javax.swing.JLabel();
        txtTipoCliente = new javax.swing.JTextField();
        lblTotalCompras = new javax.swing.JLabel();
        txtTotalCompras = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        pnlCadastrados = new javax.swing.JPanel();
        scrollClientes = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");

        pnlDetalhes.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalhes do Cliente"));

        lblNome.setText("Nome:");

        lblModo.setText("Modo: Visualização");

        pnlEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        lblLogradouro.setText("Logradouro:");

        lblBairro.setText("Bairro:");

        lblCidade.setText("Cidade:");

        lblUf.setText("UF:");

        javax.swing.GroupLayout pnlEnderecoLayout = new javax.swing.GroupLayout(pnlEndereco);
        pnlEndereco.setLayout(pnlEnderecoLayout);
        pnlEnderecoLayout.setHorizontalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogradouro)
                    .addComponent(lblCidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLogradouro, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(txtCidade))
                .addGap(24, 24, 24)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBairro)
                    .addComponent(lblUf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBairro, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(txtUf, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        pnlEnderecoLayout.setVerticalGroup(
            pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEnderecoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogradouro)
                    .addComponent(txtLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBairro)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCidade)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUf)
                    .addComponent(txtUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        lblTipoCliente.setText("Tipo de cliente:");

        txtTipoCliente.setEditable(false);

        lblTotalCompras.setText("Total de compras (R$):");

        txtTotalCompras.setEditable(false);

        btnNovo.setText("Novo");

        btnEditar.setText("Editar");

        btnExcluir.setText("Excluir");

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
                    .addComponent(pnlEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalhesLayout.createSequentialGroup()
                        .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNome)
                        .addGap(18, 18, 18)
                        .addComponent(lblModo))
                    .addGroup(pnlDetalhesLayout.createSequentialGroup()
                        .addComponent(lblTipoCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblTotalCompras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlDetalhesLayout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        pnlDetalhesLayout.setVerticalGroup(
            pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalhesLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoCliente)
                    .addComponent(txtTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalCompras)
                    .addComponent(txtTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnFechar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlCadastrados.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes cadastrados"));
        pnlCadastrados.setLayout(new java.awt.BorderLayout());

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Cidade", "UF", "Bairro", "Tipo de cliente", "Total de compras (R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollClientes.setViewportView(tblClientes);

        pnlCadastrados.add(scrollClientes, java.awt.BorderLayout.CENTER);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCadastrados, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblBairro;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblLogradouro;
    private javax.swing.JLabel lblModo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTipoCliente;
    private javax.swing.JLabel lblTotalCompras;
    private javax.swing.JLabel lblUf;
    private javax.swing.JPanel pnlCadastrados;
    private javax.swing.JPanel pnlDetalhes;
    private javax.swing.JPanel pnlEndereco;
    private javax.swing.JScrollPane scrollClientes;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtLogradouro;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTipoCliente;
    private javax.swing.JTextField txtTotalCompras;
    private javax.swing.JTextField txtUf;
    // End of variables declaration//GEN-END:variables
}
