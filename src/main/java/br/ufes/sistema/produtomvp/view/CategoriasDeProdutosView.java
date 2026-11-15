package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Categoria;
import br.ufes.sistema.produtomvp.util.NumeroUtil;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDeProdutosView extends javax.swing.JFrame implements ICategoriasDeProdutosView {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CategoriasDeProdutosView.class.getName());
    private List<Categoria> categoriasExibidas;
    private Runnable acaoNovo;
    private Runnable acaoEditar;
    private Runnable acaoExcluir;
    private Runnable acaoSalvar;
    private Runnable acaoCancelar;
    private Runnable acaoFechar;
    private Runnable acaoSelecionarLinha;

    public CategoriasDeProdutosView() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        categoriasExibidas = new ArrayList<>();

        DefaultTableModel modelo = (DefaultTableModel) tblCategorias.getModel();
        modelo.setRowCount(0);

        tblCategorias.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && acaoSelecionarLinha != null) {
                    acaoSelecionarLinha.run();
                }
            }
        });
    }

    @Override
    public String getNomeCategoria() {
        return txtCategoria.getText();
    }

    @Override
    public void setNomeCategoria(String nome) {
        txtCategoria.setText(nome != null ? nome : "");
    }

    @Override
    public String getPercentualLucro() {
        return txtPercentualLucro.getText();
    }

    @Override
    public void setPercentualLucro(String perc) {
        txtPercentualLucro.setText(perc != null ? perc : "");
    }

    @Override
    public Categoria getCategoriaSelecionada() {
        int linha = tblCategorias.getSelectedRow();
        if (linha == -1 || linha >= categoriasExibidas.size()) {
            return null;
        }
        return categoriasExibidas.get(linha);
    }

    @Override
    public void exibirCategorias(List<Categoria> categorias) {
        this.categoriasExibidas = (categorias != null) ? categorias : new ArrayList<Categoria>();
        DefaultTableModel modelo = (DefaultTableModel) tblCategorias.getModel();
        modelo.setRowCount(0);

        for (Categoria c : this.categoriasExibidas) {
            modelo.addRow(new Object[]{
                    c.getNome(),
                    NumeroUtil.formatar(c.getPercLucro())
            });
        }
    }

    @Override
    public void setModo(String modo) {
        lblModo.setText(modo);
    }

    @Override
    public void setCamposHabilitados(boolean habilitados) {
        txtCategoria.setEnabled(habilitados);
        txtPercentualLucro.setEnabled(habilitados);
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
    public void focarCampoCategoria() {
        txtCategoria.requestFocus();
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
        int op = JOptionPane.showConfirmDialog(
                this,
                mensagem,
                "Confirmação de exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        return op == JOptionPane.YES_OPTION;
    }

    @Override
    public void abrir() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        txtPercentualLucro = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        lblModo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Categorias de Produtos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalhes da Categoria"));

        jLabel1.setText("Categoria:");

        jLabel2.setText("Percentual de lucro (%):");

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        lblModo.setText("Modo: Visualização");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFechar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtPercentualLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtCategoria))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblModo)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPercentualLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnFechar))
                .addGap(47, 47, 47))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Categorias cadastradas"));

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoria", "Percentual de lucro (%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCategorias);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        if (acaoNovo != null) {
            acaoNovo.run();
        }
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        if (acaoEditar != null) {
            acaoEditar.run();
        }
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if (acaoExcluir != null) {
            acaoExcluir.run();
        }
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        if (acaoSalvar != null) {
            acaoSalvar.run();
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        if (acaoCancelar != null) {
            acaoCancelar.run();
        }
    }

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {
        if (acaoFechar != null) {
            acaoFechar.run();
        } else {
            fechar();
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CategoriasDeProdutosView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblModo;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtPercentualLucro;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnExcluir() { return btnExcluir; }
    public JButton getBtnFechar() { return btnFechar; }
    public JButton getBtnNovo() { return btnNovo; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JLabel getjLabel1() { return jLabel1; }
    public JLabel getjLabel2() { return jLabel2; }
    public JPanel getjPanel1() { return jPanel1; }
    public JPanel getjPanel2() { return jPanel2; }
    public JScrollPane getjScrollPane1() { return jScrollPane1; }
    public JTable getTblCategorias() { return tblCategorias; }
    public JTextField getTxtCategoria() { return txtCategoria; }
    public JTextField getTxtPercentualLucro() { return txtPercentualLucro; }
    public JLabel getLblModo() { return lblModo; }
}
