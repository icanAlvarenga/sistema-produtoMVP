package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.HistoricoPreco;
import br.ufes.sistema.produtomvp.util.NumeroUtil;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CalculoMargemLucroView extends javax.swing.JDialog implements ICalculoMargemLucroView {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CalculoMargemLucroView.class.getName());
    private Runnable acaoCalcular;
    private Runnable acaoFechar;

    public CalculoMargemLucroView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cbDataCalculo.setEditable(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DefaultTableModel modelo = (DefaultTableModel) tblProdutosCalculados.getModel();
        modelo.setRowCount(0);
    }

    public CalculoMargemLucroView() {
        this(null, true);
    }

    @Override
    public String getDataCalculo() {
        Object item = cbDataCalculo.getEditor().getItem();
        return item == null ? "" : item.toString().trim();
    }

    @Override
    public void setDataCalculo(String data) {
        cbDataCalculo.getEditor().setItem(data != null ? data : "");
    }

    @Override
    public void setDatasDisponiveis(List<String> datas) {
        cbDataCalculo.removeAllItems();
        if (datas != null) {
            for (String d : datas) {
                cbDataCalculo.addItem(d);
            }
        }
    }

    @Override
    public void exibirProdutosCalculados(List<HistoricoPreco> historicos) {
        DefaultTableModel modelo = (DefaultTableModel) tblProdutosCalculados.getModel();
        modelo.setRowCount(0);

        if (historicos != null) {
            for (HistoricoPreco h : historicos) {
                String nome = h.getProduto() != null ? h.getProduto().getNome() : "";
                String precoCusto = (h.getProduto() != null) ? NumeroUtil.formatar(h.getProduto().getPrecoDeCusto()) : "";
                String categoria = (h.getProduto() != null && h.getProduto().getCategoria() != null)
                        ? h.getProduto().getCategoria().getNome() : "";
                String percLucro = NumeroUtil.formatar(h.getPercentualLucro());
                String precoVenda = NumeroUtil.formatar(h.getPrecoVenda());

                modelo.addRow(new Object[]{
                        nome,
                        precoCusto,
                        categoria,
                        percLucro,
                        precoVenda
                });
            }
        }
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
    public void abrir() {
        setLocationRelativeTo(getParent());
        setVisible(true);
    }

    @Override
    public void fechar() {
        dispose();
    }

    @Override
    public void aoCalcular(Runnable acao) {
        this.acaoCalcular = acao;
    }

    @Override
    public void aoFechar(Runnable acao) {
        this.acaoFechar = acao;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCalcular = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbDataCalculo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutosCalculados = new javax.swing.JTable();
        btnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cálculo de Margem de Lucro");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel1.setText("Data de Cálculo");

        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        jLabel2.setText("O cálculo só pode ser realizado novamente após 10 dias.");

        cbDataCalculo.setEditable(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbDataCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnCalcular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnCalcular)
                    .addComponent(cbDataCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tblProdutosCalculados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome do produto", "Preço unitário", "Categoria", "Percentual de lucro", "Preço de venda calculado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProdutosCalculados);

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFechar)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        if (acaoCalcular != null) {
            acaoCalcular.run();
        }
    }//GEN-LAST:event_btnCalcularActionPerformed

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
                CalculoMargemLucroView dialog = new CalculoMargemLucroView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public javax.swing.JButton getBtnCalcular() {
        return btnCalcular;
    }

    public javax.swing.JTable getTblProdutosCalculados() {
        return tblProdutosCalculados;
    }

    public javax.swing.JButton getBtnFechar() {
        return btnFechar;
    }

    public javax.swing.JComboBox<String> getCbDataCalculo() {
        return cbDataCalculo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnFechar;
    private javax.swing.JComboBox<String> cbDataCalculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProdutosCalculados;
    // End of variables declaration//GEN-END:variables
}
