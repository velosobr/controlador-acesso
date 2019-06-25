/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Model.Aluno;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.NORTHWEST;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caiocaio
 */
public class TelaSwingPessoa extends JFrame {

    private static TelaSwingPessoa instancia;
    private JLabel label;
    private JButton cadastro;
    private JButton editar;
    private JButton remover;
    private JButton opcoesPermissao;
    private JButton voltar;
    private JTable table;
    private ValidaERetorna validador = new ValidaERetorna();

    public TelaSwingPessoa() {
        super("Opcoes de gerenciamento de pessoa");

        JPanel panelPessoa = new JPanel(new GridBagLayout());

        GridBagConstraints constraintsPanel = new GridBagConstraints();

        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(5, 5, 5, 5);
        panelPessoa.setBackground(Color.WHITE);
        setSize(680, 300);

        panelPessoa.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Opcoes Pessoa"));

        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelPessoa);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

//COMPONENTES TELASWINGPESSOA
// Label
        label = new JLabel();
        label.setText("Selecione uma das opções");

//Botao Cadastro
        cadastro = new JButton("Cadastro");
        cadastro.setActionCommand("cadastro");

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panelPessoa.add(cadastro, gbc);

//Botao Editar
        editar = new JButton("Editar");
        editar.setActionCommand("editar");

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        panelPessoa.add(editar, gbc);

// Botao Remover
        remover = new JButton("Remover");
        remover.setActionCommand("remover");

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        panelPessoa.add(remover, gbc);

//Botao opcoesPermissoes
        opcoesPermissao = new JButton("Opcoes de permissao");
        opcoesPermissao.setActionCommand("opcoesPermissao");

        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        panelPessoa.add(opcoesPermissao, gbc);

//Botao voltar
        voltar = new JButton("Voltar");
        voltar.setActionCommand("voltar");

        gbc.gridx = 5;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        panelPessoa.add(voltar, gbc);

//Gerenciador de botões
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        cadastro.addActionListener(btManager);
        editar.addActionListener(btManager);
        remover.addActionListener(btManager);
        opcoesPermissao.addActionListener(btManager);
        voltar.addActionListener(btManager);

// CONFIGURACOES TABELA
        table = new JTable();
        JScrollPane scroll = new JScrollPane(table);
        GridBagConstraints gbcTable = new GridBagConstraints();
        scroll.setPreferredSize(new Dimension(650, 200));

        gbcTable.fill = GridBagConstraints.CENTER;
        gbcTable.gridx = 0;
        gbcTable.gridy = 0;
        gbcTable.gridheight = 2;
        gbcTable.gridwidth = 8;

        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(650, 200));

        panelPessoa.add(scroll, gbcTable);

        updateTable();
    }

    private void updateTable() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setNumRows(0);
        modelo.addColumn("Matricula");
        modelo.addColumn("Nome");
        modelo.addColumn("Telefone");
        modelo.addColumn("Email");
        modelo.addColumn("Curso");
        modelo.addColumn("Cargo");
        modelo.addColumn("Administrador");
        modelo.addColumn("Tipo");
        ArrayList<Pessoa> listaPessoas = PessoaDAO.getInstancia().getList();

        for (Pessoa pessoa : listaPessoas) {

            if (pessoa instanceof Aluno) {

                Aluno aluno = (Aluno) pessoa;
                modelo.addRow(new Object[]{aluno.getMatricula(), aluno.getNome(), aluno.getTelefone(), aluno.getEmail(), aluno.getCurso(), "N/A", "N/A", "Aluno"});
            } else {
                Servidor servidor = (Servidor) pessoa;
                modelo.addRow(new Object[]{servidor.getMatricula(), servidor.getNome(), servidor.getTelefone(), servidor.getEmail(), "N/A", servidor.getCargo(), servidor.isAdministrador(), "Servidor"});
            }

        }
        table.setModel(modelo);
        this.repaint();
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                switch (e.getActionCommand()) {
                    case ("cadastro"):
                        cadastraPessoa();
                        updateTable();
                        break;
                    case ("editar"):
                        editarPessoa();
                        updateTable();
                        break;
                    case ("remover"):
                        removerPessoa();
                        updateTable();
                        break;
                    case ("opcoesPermissao"):

                        opcoesPermissao();

                        break;
                    case ("voltar"):
                        setVisible(false);
                        CtrlPrincipal.getInstancia().abreTelaAdm();
                        break;

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Opcao Invalida! Escolha uma opcao dentre das opcoes na lista.");
                TelaSwingPessoa.getInstancia().setVisible(true);
            }

        }

    }

    private void cadastraPessoa() {

            
            String[] opcoes = {"Aluno", "Servidor"};
            int teste = JOptionPane.showOptionDialog(null, "Escolha um tipo de pessoa", "Selecione", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
            if(teste == 0){
                int matricula = validador.recebeValorInteiro("Digite a matricula: ");
                String nome = validador.recebeValorString("Digite o nome: ");
                long telefone = validador.recebeValorLong("Digite o telefone: ");
                String email = validador.recebeValorString("Digite o email: ");
                String curso = validador.recebeValorString("Digite o curso: ");
                getCtrlPrincipal().getCtrlPessoa().incluiAluno(matricula, nome, telefone, email, curso);
            }else{
                int matricula = validador.recebeValorInteiro("Digite a matricula: ");
                String nome = validador.recebeValorString("Digite o nome: ");
                long telefone = validador.recebeValorLong("Digite o telefone: ");
                String email = validador.recebeValorString("Digite o email: ");
                //String cargo = JOptionPane.showInputDialog(rootPane, teste, nome, teste, icon, opcoes, telefone)
                String cargo = validador.recebeValorString("Digite o cargo: ");
                boolean administrador = validador.recebeValorBoolean();
                getCtrlPrincipal().getCtrlPessoa().incluiServidor(matricula, nome, telefone, email, cargo, administrador);


        }
    }

    private void editarPessoa() {

        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada >= 0) {
            String tipoPessoa = table.getValueAt(linhaSelecionada, 7).toString();
            int matricula = (int) table.getValueAt(linhaSelecionada, 0);
            if (tipoPessoa.equals("Aluno")) {
                String nome = validador.recebeValorString("Digite o nome: ");
                long telefone = validador.recebeValorLong("Digite o telefone: ");
                String email = validador.recebeValorString("Digite o email: ");
                String curso = validador.recebeValorString("Digite o curso: ");
                getCtrlPrincipal().getCtrlPessoa().alteradorDeCadastroAluno(matricula, nome, telefone, email, curso);
            } else {
                String nome = validador.recebeValorString("Digite o nome: ");
                long telefone = validador.recebeValorLong("Digite o telefone: ");
                String email = validador.recebeValorString("Digite o email: ");
                String cargo = validador.recebeValorString("Digite o cargo: ");
                boolean administrador = validador.recebeValorBoolean();
                getCtrlPrincipal().getCtrlPessoa().alteradorDeCadastroServidor(matricula, nome, telefone, email, cargo, administrador);
            }
        } else {
            JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
        }
    }

    private void removerPessoa() {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int matricula = (int) table.getValueAt(linhaSelecionada, 0);
            Pessoa pessoaParaRemover = PessoaDAO.getInstancia().getPessoa(matricula);
            PessoaDAO.getInstancia().remove(pessoaParaRemover);
        } else {
            JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
        }
    }

    private void opcoesPermissao() {
        Integer linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int matricula = (int) table.getValueAt(linhaSelecionada, 0);
            getCtrlPrincipal().getCtrlPessoa().abreTelaGestaoPermissaoPessoa(matricula);
        } else {
            JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
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

    public CtrlPrincipal getCtrlPrincipal() {
        return CtrlPrincipal.getInstancia();
    }

}
