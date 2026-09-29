/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.sistema.produtomvp.presenter;

/**
 *
 * @author jveli
 */

import br.ufes.sistema.produtomvp.view.CategoriasDeProdutosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class CategoriaPresenter {
    
    private CategoriasDeProdutosView view;
    
    public CategoriaPresenter() {
        this.view = new CategoriasDeProdutosView();
        
        // Remove as linhas em branco padrão da tabela gerada pelo NetBeans
        DefaultTableModel modelo = (DefaultTableModel) view.getTblCategorias().getModel();
        modelo.setNumRows(0);
        
        estadoInicial();
        configurarAcoesDosBotoes();
        
        this.view.setVisible(true);
    }
    
    private void estadoInicial() {
        view.getTxtCategoria().setEnabled(false);
        view.getTxtPercentualLucro().setEnabled(false);
        
        view.getBtnSalvar().setEnabled(false);
        view.getBtnCancelar().setEnabled(false);
        
        view.getBtnNovo().setEnabled(true);
        view.getBtnEditar().setEnabled(true); // Ficará para a próxima etapa do trabalho
        view.getBtnExcluir().setEnabled(true); // Ficará para a próxima etapa do trabalho
        
        view.getLblModo().setText("Modo: Visualização");
    }
    
    private void configurarAcoesDosBotoes() {
        // Ação do botão NOVO
        view.getBtnNovo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTxtCategoria().setEnabled(true);
                view.getTxtPercentualLucro().setEnabled(true);
                view.getTxtCategoria().setText("");
                view.getTxtPercentualLucro().setText("");
                
                view.getBtnSalvar().setEnabled(true);
                view.getBtnCancelar().setEnabled(true);
                
                view.getBtnNovo().setEnabled(false);
                view.getBtnEditar().setEnabled(false);
                view.getBtnExcluir().setEnabled(false);
                
                view.getLblModo().setText("Modo: Inclusão");
                view.getTxtCategoria().requestFocus();
            }
        });

        // Ação do botão SALVAR
        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoria = view.getTxtCategoria().getText();
                String percentual = view.getTxtPercentualLucro().getText();
                
                // Adiciona os dados na tabela
                DefaultTableModel modelo = (DefaultTableModel) view.getTblCategorias().getModel();
                modelo.addRow(new Object[]{categoria, percentual});
                
                // Limpa os campos e volta ao modo de visualização
                view.getTxtCategoria().setText("");
                view.getTxtPercentualLucro().setText("");
                estadoInicial();
            }
        });

        // Ação do botão CANCELAR
        view.getBtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.getTxtCategoria().setText("");
                view.getTxtPercentualLucro().setText("");
                estadoInicial();
            }
        });
        
        // Ação do botão FECHAR
        view.getBtnFechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose(); // Fecha a tela
            }
        });
    }

    public static void main(String[] args) {
        new CategoriaPresenter();
    }
}