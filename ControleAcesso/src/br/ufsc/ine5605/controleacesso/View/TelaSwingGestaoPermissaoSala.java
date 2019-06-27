/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPessoa;
import br.ufsc.ine5605.controleacesso.Controller.CtrlSala;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
import br.ufsc.ine5605.controleacesso.Persistencia.SalaDAO;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import com.sun.corba.se.impl.naming.namingutil.INSURL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Linnety3
 */
public class TelaSwingGestaoPermissaoSala extends JFrame {

    private JLabel label;
    private JButton permitirAcesso;
    private JButton removerAcesso;
    private JButton voltar;
    private JTable table;
    private String codSala;
    private ValidaERetorna validador = new ValidaERetorna();

    public TelaSwingGestaoPermissaoSala(String codigo) {
        super("Gerenciamento das liberações de salas");

        codSala = codigo;

        //criação do painel
        JPanel panelPermissaoSala = new JPanel(new GridBagLayout());
        GridBagConstraints gbcPanel = new GridBagConstraints();

        gbcPanel.anchor = GridBagConstraints.WEST;
        gbcPanel.insets = new Insets(5, 5, 5, 5);
        panelPermissaoSala.setBackground(Color.WHITE);
        setSize(680, 300);

        panelPermissaoSala.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
                "Gerenciamento das liberações de acesso | Número da sala selecionada: "
                + CtrlSala.getInstancia().findSalaByCodigoSala(codigo).getNumero()));
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelPermissaoSala);

        //COMPONENTES TELASWINGPERMISSAOSALA
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label
        label = new JLabel();
        label.setText("Selecione uma das opções");

        //adicionar Sala a pessoa
        permitirAcesso = new JButton("Permitir Acesso");
        permitirAcesso.setActionCommand("permitirAcesso");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panelPermissaoSala.add(permitirAcesso, gbc);

//Botao Remover acesso
//tira sala da pessoa
        removerAcesso = new JButton("Remover Acesso");
        removerAcesso.setActionCommand("removerAcesso");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panelPermissaoSala.add(removerAcesso, gbc);

        //Botao voltar
        voltar = new JButton("Voltar");
        voltar.setActionCommand("voltar");
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panelPermissaoSala.add(voltar, gbc);

        TelaSwingGestaoPermissaoSala.GerenciadorBotoes btManager = new TelaSwingGestaoPermissaoSala.GerenciadorBotoes();
        permitirAcesso.addActionListener(btManager);
        removerAcesso.addActionListener(btManager);

        voltar.addActionListener(btManager);

//CONFIGURACOES TABELA       
        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        GridBagConstraints gbcTable = new GridBagConstraints();
        scroll.setPreferredSize(new Dimension(650, 200));

        gbcTable.gridx = 0;
        gbcTable.gridy = 0;
        gbcTable.gridheight = 2;
        gbcTable.gridwidth = 8;
        gbcTable.fill = GridBagConstraints.CENTER;
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(650, 200));

        panelPermissaoSala.add(scroll, gbcTable);

        updateTable(codSala);
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                switch (e.getActionCommand()) {
                    case ("permitirAcesso"):
                        permitirAcesso();
                        updateTable(codSala);
                        break;
                    case ("removerAcesso"):
                        removerAcesso();
                        updateTable(codSala);
                        break;
                    case ("voltar"):
                        setVisible(false);
                        CtrlSala.getInstancia().abreTelaSwingSala();
                        break;

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Opcao Invalida! Escolha uma opcao dentre das opcoes na lista .");

            }

        }
    }

    private void updateTable(String codSala) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setNumRows(0);
        modelo.addColumn("matricula");
        modelo.addColumn("nome");
        modelo.addColumn("telefone");
        modelo.addColumn("email");

        ArrayList<Pessoa> listaPessoasCadastradas = SalaDAO.getInstancia().getSala(codSala).getPessoasCadastradas();
        if (listaPessoasCadastradas == null) {
            modelo.addRow(new Object[]{"Sem Pessoa cadastrada", "N/A", "N/A", "N/A", "N/A"});
        } else {
            for (Pessoa pessoa : listaPessoasCadastradas) {
                modelo.addRow(new Object[]{pessoa.getMatricula(), pessoa.getNome(), pessoa.getTelefone(), pessoa.getEmail()});

            }

        }
        table.setModel(modelo);
        this.repaint();
    }

    private void permitirAcesso() {
        int matricula = validador.recebeValorInteiro("Digite a matricula: ");
        try {
            CtrlSala.getInstancia().cadastraPessoaNaSala(matricula, codSala);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void removerAcesso() {
        System.out.println("entra no remove acesso da tela seing getstao de permissao sala");
        int linhaSelecionada = table.getSelectedRow();

        System.out.println(linhaSelecionada);
        try {
            if (linhaSelecionada >= 0) {

                int matricula = (int) table.getValueAt(linhaSelecionada, 0);
                System.out.println("A matricula capturada é: " + matricula);

                CtrlSala.getInstancia().deletaPessoaNaSala(matricula, codSala);

            } else {
                JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
            }
        } catch (Exception exception) {
            System.out.println("entrou no catch. a mensagem da exception é: " + exception.getMessage());
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

    }

}
