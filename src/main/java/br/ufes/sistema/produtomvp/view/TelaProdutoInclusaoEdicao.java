package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Categoria;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaProdutoInclusaoEdicao extends javax.swing.JFrame implements IProdutoInclusaoEdicaoView {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaProdutoInclusaoEdicao.class.getName());

    public TelaProdutoInclusaoEdicao() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        txtMargemLucro.setEditable(false);
        txtPrecoVenda.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDadosProduto = new javax.swing.JPanel();
        lblNomeProduto = new javax.swing.JLabel();
        lblPrecoCusto = new javax.swing.JLabel();
        lblCategoriaProduto = new javax.swing.JLabel();
        lblMargemLucro = new javax.swing.JLabel();
        lblPrecoVenda = new javax.swing.JLabel();
        txtNomeProduto = new javax.swing.JTextField();
        txtPrecoCusto = new javax.swing.JTextField();
        txtMargemLucro = new javax.swing.JTextField();
        txtPrecoVenda = new javax.swing.JTextField();
        cmbBoxCategoriaProduto = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlDadosProduto.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Produto"));

        lblNomeProduto.setText("Nome do produto:");

        lblPrecoCusto.setText("Preço de custo:");

        lblCategoriaProduto.setText("Categoria do produto:");

        lblMargemLucro.setText("Margem de lucro (%):");

        lblPrecoVenda.setText("Preço de venda:");

        txtMargemLucro.setEditable(false);

        txtPrecoVenda.setEditable(false);

        javax.swing.GroupLayout pnlDadosProdutoLayout = new javax.swing.GroupLayout(pnlDadosProduto);
        pnlDadosProduto.setLayout(pnlDadosProdutoLayout);
        pnlDadosProdutoLayout.setHorizontalGroup(
            pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosProdutoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblCategoriaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                        .addComponent(lblNomeProduto)
                        .addComponent(lblPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMargemLucro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cmbBoxCategoriaProduto, javax.swing.GroupLayout.Alignment.LEADING, 0, 185, Short.MAX_VALUE)
                        .addComponent(txtPrecoVenda, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMargemLucro, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtPrecoCusto, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        pnlDadosProdutoLayout.setVerticalGroup(
            pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeProduto)
                    .addComponent(txtNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPrecoCusto)
                    .addComponent(txtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoriaProduto)
                    .addComponent(cmbBoxCategoriaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMargemLucro)
                    .addComponent(txtMargemLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecoVenda)
                    .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        btnSalvar.setText("Salvar");

        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(pnlDadosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(pnlDadosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                new TelaProdutoInclusaoEdicao().setVisible(true);
            }
        });
    }

    @Override
    public String getNome() {
        return txtNomeProduto.getText();
    }

    @Override
    public String getPrecoCusto() {
        return txtPrecoCusto.getText();
    }

    @Override
    public Categoria getCategoriaSelecionada() {
        Object item = cmbBoxCategoriaProduto.getSelectedItem();
        if (item instanceof Categoria) {
            return (Categoria) item;
        }
        return null;
    }

    @Override
    public void setTitulo(String titulo) {
        setTitle(titulo);
    }

    @Override
    public void setNome(String nome) {
        txtNomeProduto.setText(nome != null ? nome : "");
    }

    @Override
    public void setPrecoCusto(String valor) {
        txtPrecoCusto.setText(valor != null ? valor : "");
    }

    @Override
    public void setCategorias(List<Categoria> categorias) {
        cmbBoxCategoriaProduto.removeAllItems();
        if (categorias != null) {
            for (Categoria c : categorias) {
                cmbBoxCategoriaProduto.addItem(c);
            }
        }
        cmbBoxCategoriaProduto.setSelectedIndex(-1);
    }

    @Override
    public void setCategoriaSelecionada(Categoria c) {
        cmbBoxCategoriaProduto.setSelectedItem(c);
    }

    @Override
    public void setMargemLucro(String m) {
        txtMargemLucro.setText(m != null ? m : "");
    }

    @Override
    public void setPrecoVenda(String p) {
        txtPrecoVenda.setText(p != null ? p : "");
    }

    @Override
    public void exibirMensagem(String m) {
        JOptionPane.showMessageDialog(this, m, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void exibirErro(String m) {
        JOptionPane.showMessageDialog(this, m, "Atenção", JOptionPane.WARNING_MESSAGE);
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
    public void aoSalvar(final Runnable acao) {
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao != null) {
                    acao.run();
                }
            }
        });
    }

    @Override
    public void aoCancelar(final Runnable acao) {
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao != null) {
                    acao.run();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Categoria> cmbBoxCategoriaProduto;
    private javax.swing.JLabel lblCategoriaProduto;
    private javax.swing.JLabel lblMargemLucro;
    private javax.swing.JLabel lblNomeProduto;
    private javax.swing.JLabel lblPrecoCusto;
    private javax.swing.JLabel lblPrecoVenda;
    private javax.swing.JPanel pnlDadosProduto;
    private javax.swing.JTextField txtMargemLucro;
    private javax.swing.JTextField txtNomeProduto;
    private javax.swing.JTextField txtPrecoCusto;
    private javax.swing.JTextField txtPrecoVenda;
    // End of variables declaration//GEN-END:variables
}
