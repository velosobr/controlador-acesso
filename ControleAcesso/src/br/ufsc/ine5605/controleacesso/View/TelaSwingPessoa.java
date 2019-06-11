/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author caiocaio
 */
public class TelaSwingPessoa extends JFrame{
    private static TelaSwingPessoa instancia;
    private JLabel label;
    private JButton botaoOpcoesCadastro;
    private JButton botaoOpcoesPermissao;
    private JButton botaoOpcoesVisualizacao;

    public TelaSwingPessoa() {
        super("Opcoes de gerenciamento de pessoa");
        
        Container container = getContentPane();
        container.setLayout(new GridLayout());
        
        label = new JLabel();
        botaoOpcoesCadastro = new JButton();
        botaoOpcoesPermissao = new JButton();
        botaoOpcoesVisualizacao = new JButton();
    
        
        label.setText("Selecione uma das opções");
        
        //opcoes cadastro
        
        botaoOpcoesCadastro.setText("Opcoes de Cadastro");
        botaoOpcoesCadastro.setActionCommand("cadastro");
        
        // opcoes gerenciamento
        
        botaoOpcoesPermissao.setText("Opcoes de Permissao de Acesso");
        botaoOpcoesPermissao.setActionCommand("permisao");
        
        //opcoes Visualizacao
        
        botaoOpcoesVisualizacao.setText("Opcoes de Visualizacao");
        botaoOpcoesVisualizacao.setActionCommand("visalizacao");
        
        
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        botaoOpcoesCadastro.addActionListener(btManager);
        botaoOpcoesPermissao.addActionListener(btManager);
        botaoOpcoesVisualizacao.addActionListener(btManager);
        
        container.add(label);
        container.add(botaoOpcoesCadastro);
        container.add(botaoOpcoesPermissao);
        container.add(botaoOpcoesVisualizacao);
        
        setSize(360, 150);
        setLocationRelativeTo(null);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, "BotaoPressionado" + e.getActionCommand(), "Teste", 1);
        }
        
    }

    /**
     * @return the instancia
     */
    public static TelaSwingPessoa getInstancia() {
        if (instancia == null) {
            instancia = new TelaSwingPessoa();
        }
        return instancia;
    }

}
