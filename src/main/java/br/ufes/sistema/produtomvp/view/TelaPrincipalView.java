package br.ufes.sistema.produtomvp.view;

import br.ufes.sistema.produtomvp.model.Pedido;
import br.ufes.sistema.produtomvp.util.NumeroUtil;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TelaPrincipalView extends javax.swing.JFrame implements ITelaPrincipalView {

    private Runnable acaoSair;
    private Runnable acaoMenuProdutos;
    private Runnable acaoMenuCategorias;
    private Runnable acaoMenuClientes;
    private Runnable acaoMenuUsuarios;
    private Runnable acaoNovoProduto;
    private Runnable acaoNovoCliente;
    private Runnable acaoBuscarClientes;

    public TelaPrincipalView() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        DefaultTableModel modelo = (DefaultTableModel) tblPedidos.getModel();
        modelo.setRowCount(0);

        configurarAcoes();
    }

    private void configurarAcoes() {
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoSair != null) {
                    acaoSair.run();
                }
            }
        });

        itemProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoMenuProdutos != null) {
                    acaoMenuProdutos.run();
                }
            }
        });

        itemCategorias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoMenuCategorias != null) {
                    acaoMenuCategorias.run();
                }
            }
        });

        itemClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoMenuClientes != null) {
                    acaoMenuClientes.run();
                }
            }
        });

        menuUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (menuUsuarios.isEnabled() && acaoMenuUsuarios != null) {
                    acaoMenuUsuarios.run();
                }
            }
        });

        btnNovoProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoNovoProduto != null) {
                    acaoNovoProduto.run();
                }
            }
        });

        btnNovoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoNovoCliente != null) {
                    acaoNovoCliente.run();
                }
            }
        });

        btnBuscarClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acaoBuscarClientes != null) {
                    acaoBuscarClientes.run();
                }
            }
        });
    }

    @Override
    public void exibir(String identificacaoUsuario, boolean podeGerenciarUsuarios) {
        lblUsuarioLogado.setText("Usuário: " + (identificacaoUsuario != null ? identificacaoUsuario : ""));
        menuUsuarios.setVisible(podeGerenciarUsuarios);
        menuUsuarios.setEnabled(podeGerenciarUsuarios);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    @Override
    public void fechar() {
        dispose();
    }

    @Override
    public void aoSair(Runnable acao) {
        this.acaoSair = acao;
    }

    @Override
    public void aoMenuProdutos(Runnable acao) {
        this.acaoMenuProdutos = acao;
    }

    @Override
    public void aoMenuCategorias(Runnable acao) {
        this.acaoMenuCategorias = acao;
    }

    @Override
    public void aoMenuClientes(Runnable acao) {
        this.acaoMenuClientes = acao;
    }

    @Override
    public void aoMenuUsuarios(Runnable acao) {
        this.acaoMenuUsuarios = acao;
    }

    @Override
    public void aoNovoProduto(Runnable acao) {
        this.acaoNovoProduto = acao;
    }

    @Override
    public void aoNovoCliente(Runnable acao) {
        this.acaoNovoCliente = acao;
    }

    @Override
    public void aoBuscarClientes(Runnable acao) {
        this.acaoBuscarClientes = acao;
    }

    @Override
    public void exibirPedidos(List<Pedido> pedidos) {
        DefaultTableModel modelo = (DefaultTableModel) tblPedidos.getModel();
        modelo.setRowCount(0);

        if (pedidos != null) {
            for (Pedido p : pedidos) {
                modelo.addRow(new Object[]{
                        p.getNumeroPedido(),
                        p.getDataHora(),
                        p.getCliente(),
                        p.getStatus(),
                        p.getFormaPagamento(),
                        NumeroUtil.formatar(p.getTotal())
                });
            }
        }
    }

    @Override
    public void exibirIndicadores(int andamento, int entregues, double totalVendas, int entregadores) {
        lblCard1Valor.setText(" " + andamento);
        lblCard2Valor.setText(" " + entregues);
        lblCard3Valor.setText(" " + NumeroUtil.formatar(totalVendas));
        lblCard4Valor.setText(" " + entregadores);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblUsuarioLogado = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        pnlCards = new javax.swing.JPanel();
        card1 = new javax.swing.JPanel();
        lblCard1Titulo = new javax.swing.JLabel();
        lblCard1Valor = new javax.swing.JLabel();
        card2 = new javax.swing.JPanel();
        lblCard2Titulo = new javax.swing.JLabel();
        lblCard2Valor = new javax.swing.JLabel();
        card3 = new javax.swing.JPanel();
        lblCard3Titulo = new javax.swing.JLabel();
        lblCard3Valor = new javax.swing.JLabel();
        card4 = new javax.swing.JPanel();
        lblCard4Titulo = new javax.swing.JLabel();
        lblCard4Valor = new javax.swing.JLabel();
        pnlPedidos = new javax.swing.JPanel();
        scrollPedidos = new javax.swing.JScrollPane();
        tblPedidos = new javax.swing.JTable();
        pnlAcessoRapido = new javax.swing.JPanel();
        btnNovoPedido = new javax.swing.JButton();
        btnBuscarPedidos = new javax.swing.JButton();
        btnNovoProduto = new javax.swing.JButton();
        btnNovoCliente = new javax.swing.JButton();
        btnBuscarClientes = new javax.swing.JButton();
        btnAcompanharEntregas = new javax.swing.JButton();
        menuBarPrincipal = new javax.swing.JMenuBar();
        menuOperacao = new javax.swing.JMenu();
        menuDados = new javax.swing.JMenu();
        itemProdutos = new javax.swing.JMenuItem();
        itemCategorias = new javax.swing.JMenuItem();
        itemClientes = new javax.swing.JMenuItem();
        itemEntregadores = new javax.swing.JMenuItem();
        itemTaxasEntrega = new javax.swing.JMenuItem();
        menuUsuarios = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POC Delivery");

        pnlHeader.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setText("Bem-vindo ao POC Delivery");

        lblSubtitulo.setText("Gerencie seus pedidos, entregas e clientes de forma simples e eficiente.");

        lblUsuarioLogado.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblUsuarioLogado.setText("Usuário: Administrador");

        btnSair.setText("Sair");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSubtitulo)
                    .addComponent(lblTitulo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUsuarioLogado)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(lblUsuarioLogado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubtitulo)
                    .addComponent(btnSair))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnlCards.setLayout(new java.awt.GridLayout(1, 4, 12, 0));

        card1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        card1.setLayout(new java.awt.BorderLayout());

        lblCard1Titulo.setText(" Pedidos em andamento");
        card1.add(lblCard1Titulo, java.awt.BorderLayout.NORTH);

        lblCard1Valor.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblCard1Valor.setText(" 8");
        card1.add(lblCard1Valor, java.awt.BorderLayout.CENTER);

        pnlCards.add(card1);

        card2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        card2.setLayout(new java.awt.BorderLayout());

        lblCard2Titulo.setText(" Pedidos entregues hoje");
        card2.add(lblCard2Titulo, java.awt.BorderLayout.NORTH);

        lblCard2Valor.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblCard2Valor.setText(" 24");
        card2.add(lblCard2Valor, java.awt.BorderLayout.CENTER);

        pnlCards.add(card2);

        card3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        card3.setLayout(new java.awt.BorderLayout());

        lblCard3Titulo.setText(" Total em vendas (R$)");
        card3.add(lblCard3Titulo, java.awt.BorderLayout.NORTH);

        lblCard3Valor.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblCard3Valor.setText(" 1.245,60");
        card3.add(lblCard3Valor, java.awt.BorderLayout.CENTER);

        pnlCards.add(card3);

        card4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        card4.setLayout(new java.awt.BorderLayout());

        lblCard4Titulo.setText(" Entregadores ativos");
        card4.add(lblCard4Titulo, java.awt.BorderLayout.NORTH);

        lblCard4Valor.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblCard4Valor.setText(" 5");
        card4.add(lblCard4Valor, java.awt.BorderLayout.CENTER);

        pnlCards.add(card4);

        pnlPedidos.setBorder(javax.swing.BorderFactory.createTitledBorder("Últimos pedidos"));
        pnlPedidos.setLayout(new java.awt.BorderLayout());

        tblPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Pedido", "Data e hora", "Cliente", "Status", "Forma de pagamento", "Total (R$)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollPedidos.setViewportView(tblPedidos);

        pnlPedidos.add(scrollPedidos, java.awt.BorderLayout.CENTER);

        pnlAcessoRapido.setBorder(javax.swing.BorderFactory.createTitledBorder("Acesso rápido"));
        pnlAcessoRapido.setLayout(new java.awt.GridLayout(3, 2, 10, 10));

        btnNovoPedido.setText("Novo Pedido");
        pnlAcessoRapido.add(btnNovoPedido);

        btnBuscarPedidos.setText("Buscar Pedidos");
        pnlAcessoRapido.add(btnBuscarPedidos);

        btnNovoProduto.setText("Novo Produto");
        pnlAcessoRapido.add(btnNovoProduto);

        btnNovoCliente.setText("Novo Cliente");
        pnlAcessoRapido.add(btnNovoCliente);

        btnBuscarClientes.setText("Buscar Clientes");
        pnlAcessoRapido.add(btnBuscarClientes);

        btnAcompanharEntregas.setText("Acompanhar Entregas");
        pnlAcessoRapido.add(btnAcompanharEntregas);

        menuOperacao.setText("Operação");
        menuBarPrincipal.add(menuOperacao);

        menuDados.setText("Dados");

        itemProdutos.setText("Produtos");
        menuDados.add(itemProdutos);

        itemCategorias.setText("Categorias");
        menuDados.add(itemCategorias);

        itemClientes.setText("Clientes");
        menuDados.add(itemClientes);

        itemEntregadores.setText("Entregadores");
        menuDados.add(itemEntregadores);

        itemTaxasEntrega.setText("Taxas de entrega");
        menuDados.add(itemTaxasEntrega);

        menuBarPrincipal.add(menuDados);

        menuUsuarios.setText("Usuários");
        menuBarPrincipal.add(menuUsuarios);

        setJMenuBar(menuBarPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                        .addGap(16, 16, 16)
                        .addComponent(pnlAcessoRapido, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCards, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addComponent(pnlAcessoRapido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcompanharEntregas;
    private javax.swing.JButton btnBuscarClientes;
    private javax.swing.JButton btnBuscarPedidos;
    private javax.swing.JButton btnNovoCliente;
    private javax.swing.JButton btnNovoPedido;
    private javax.swing.JButton btnNovoProduto;
    private javax.swing.JButton btnSair;
    private javax.swing.JPanel card1;
    private javax.swing.JPanel card2;
    private javax.swing.JPanel card3;
    private javax.swing.JPanel card4;
    private javax.swing.JMenuItem itemCategorias;
    private javax.swing.JMenuItem itemClientes;
    private javax.swing.JMenuItem itemEntregadores;
    private javax.swing.JMenuItem itemProdutos;
    private javax.swing.JMenuItem itemTaxasEntrega;
    private javax.swing.JLabel lblCard1Titulo;
    private javax.swing.JLabel lblCard1Valor;
    private javax.swing.JLabel lblCard2Titulo;
    private javax.swing.JLabel lblCard2Valor;
    private javax.swing.JLabel lblCard3Titulo;
    private javax.swing.JLabel lblCard3Valor;
    private javax.swing.JLabel lblCard4Titulo;
    private javax.swing.JLabel lblCard4Valor;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuarioLogado;
    private javax.swing.JMenuBar menuBarPrincipal;
    private javax.swing.JMenu menuDados;
    private javax.swing.JMenu menuOperacao;
    private javax.swing.JMenu menuUsuarios;
    private javax.swing.JPanel pnlAcessoRapido;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlPedidos;
    private javax.swing.JScrollPane scrollPedidos;
    private javax.swing.JTable tblPedidos;
    // End of variables declaration//GEN-END:variables
}
