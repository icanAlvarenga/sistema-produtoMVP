package br.ufes.sistema.produtomvp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class TelaProdutoVisualizacao extends javax.swing.JPanel implements IProdutoVisualizacaoView {

    private JFrame frame;

    public TelaProdutoVisualizacao() {
        initComponents();
        txtNomeProdutoVisualizacao.setEditable(false);
        txtPrecoProdutoVisualizacao.setEditable(false);
        txtMargemLucroVisualizacao.setEditable(false);
        txtPrecoVendaVisualizacao.setEditable(false);
        cmbCategoriaProdutoVisualizacao.setEnabled(false);

        frame = new JFrame("Produto - Visualização");
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void setNome(String nome) {
        txtNomeProdutoVisualizacao.setText(nome != null ? nome : "");
    }

    @Override
    public void setPrecoCusto(String precoCusto) {
        txtPrecoProdutoVisualizacao.setText(precoCusto != null ? precoCusto : "");
    }

    @Override
    public void setCategoria(String categoria) {
        cmbCategoriaProdutoVisualizacao.removeAllItems();
        if (categoria != null) {
            cmbCategoriaProdutoVisualizacao.addItem(categoria);
            cmbCategoriaProdutoVisualizacao.setSelectedItem(categoria);
        }
    }

    @Override
    public void setMargemLucro(String margem) {
        txtMargemLucroVisualizacao.setText(margem != null ? margem : "");
    }

    @Override
    public void setPrecoVenda(String preco) {
        txtPrecoVendaVisualizacao.setText(preco != null ? preco : "");
    }

    @Override
    public void abrir() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void fechar() {
        frame.dispose();
    }

    @Override
    public void aoEditar(final Runnable acao) {
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao != null) {
                    acao.run();
                }
            }
        });
    }

    @Override
    public void aoVisualizarHistorico(final Runnable acao) {
        btnVisualizarHistoricoPrecos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao != null) {
                    acao.run();
                }
            }
        });
    }

    @Override
    public void aoFechar(final Runnable acao) {
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao != null) {
                    acao.run();
                } else {
                    fechar();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblNomeProdutoVisualizacao = new javax.swing.JLabel();
        lblPrecoProdutoVisualizacao = new javax.swing.JLabel();
        lblCategoriaProdutoVisualizacao = new javax.swing.JLabel();
        lblMargemLucroVisualizacao = new javax.swing.JLabel();
        lblPrecoVendaVisualizacao = new javax.swing.JLabel();
        txtNomeProdutoVisualizacao = new javax.swing.JTextField();
        txtPrecoProdutoVisualizacao = new javax.swing.JTextField();
        cmbCategoriaProdutoVisualizacao = new javax.swing.JComboBox<>();
        txtMargemLucroVisualizacao = new javax.swing.JTextField();
        txtPrecoVendaVisualizacao = new javax.swing.JTextField();
        btnVisualizarHistoricoPrecos = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Produto"));

        lblNomeProdutoVisualizacao.setText("Nome do produto:");

        lblPrecoProdutoVisualizacao.setText("Preço de custo:");

        lblCategoriaProdutoVisualizacao.setText("Categoria do produto:");

        lblMargemLucroVisualizacao.setText("Margem de lucro (%):");

        lblPrecoVendaVisualizacao.setText("Preço de venda:");

        cmbCategoriaProdutoVisualizacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMargemLucroVisualizacao.setEditable(false);

        txtPrecoVendaVisualizacao.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeProdutoVisualizacao)
                    .addComponent(lblPrecoProdutoVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCategoriaProdutoVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMargemLucroVisualizacao)
                    .addComponent(lblPrecoVendaVisualizacao))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNomeProdutoVisualizacao)
                        .addComponent(txtPrecoProdutoVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbCategoriaProdutoVisualizacao, 0, 416, Short.MAX_VALUE)
                        .addComponent(txtMargemLucroVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPrecoVendaVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeProdutoVisualizacao)
                    .addComponent(txtNomeProdutoVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecoProdutoVisualizacao)
                    .addComponent(txtPrecoProdutoVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoriaProdutoVisualizacao)
                    .addComponent(cmbCategoriaProdutoVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMargemLucroVisualizacao)
                    .addComponent(txtMargemLucroVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrecoVendaVisualizacao)
                    .addComponent(txtPrecoVendaVisualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        btnVisualizarHistoricoPrecos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnVisualizarHistoricoPrecos.setText("Visualizar histórico de preços");

        btnEditar.setText("Editar");

        btnFechar.setText("Fechar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisualizarHistoricoPrecos, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFechar)
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVisualizarHistoricoPrecos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnFechar))
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnVisualizarHistoricoPrecos;
    private javax.swing.JComboBox<String> cmbCategoriaProdutoVisualizacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCategoriaProdutoVisualizacao;
    private javax.swing.JLabel lblMargemLucroVisualizacao;
    private javax.swing.JLabel lblNomeProdutoVisualizacao;
    private javax.swing.JLabel lblPrecoProdutoVisualizacao;
    private javax.swing.JLabel lblPrecoVendaVisualizacao;
    private javax.swing.JTextField txtMargemLucroVisualizacao;
    private javax.swing.JTextField txtNomeProdutoVisualizacao;
    private javax.swing.JTextField txtPrecoProdutoVisualizacao;
    private javax.swing.JTextField txtPrecoVendaVisualizacao;
    // End of variables declaration//GEN-END:variables
}
