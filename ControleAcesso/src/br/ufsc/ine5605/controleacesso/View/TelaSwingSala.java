/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Controller.CtrlSala;
import br.ufsc.ine5605.controleacesso.Model.Aluno;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
import br.ufsc.ine5605.controleacesso.Persistencia.SalaDAO;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lino Veloso
 */
public class TelaSwingSala extends JFrame {

    private JLabel label;
    private JButton cadastro;
    private JButton editar;
    private JButton remover;
    private JButton opcoesPermissao;
    private JButton voltar;
    private JTable table;
    private ValidaERetorna validador = new ValidaERetorna();

    private static TelaSwingSala instancia;

    public static TelaSwingSala GetInstancia() {
        if (instancia == null) {
            instancia = new TelaSwingSala();
        }
        return instancia;
    }

    public TelaSwingSala() {
        super("Gerenciamento de salas");

        JPanel panelSala = new JPanel(new GridBagLayout());

        GridBagConstraints constraintsPanel = new GridBagConstraints();
        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(5, 5, 5, 5);
        panelSala.setBackground(Color.WHITE);
        setSize(680, 300);

        panelSala.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Opcoes Sala"));

        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelSala);

        GridBagConstraints gbcBTN = new GridBagConstraints();
        gbcBTN.insets = new Insets(5, 5, 5, 5);

//COMPONENTES TELASWINGSALA
// Label
        label = new JLabel();
        label.setText("Selecione uma das opções");

//Botao Cadastro
        cadastro = new JButton("Cadastro");
        cadastro.setActionCommand("cadastro");

        gbcBTN.gridx = 1;
        gbcBTN.gridy = 3;
        gbcBTN.gridwidth = 1;
        gbcBTN.fill = GridBagConstraints.HORIZONTAL;

        panelSala.add(cadastro, gbcBTN);

//Botao Editar
        editar = new JButton("Editar");
        editar.setActionCommand("editar");

        gbcBTN.gridx = 2;
        gbcBTN.gridy = 3;

        panelSala.add(editar, gbcBTN);

// Botao Remover
        remover = new JButton("Remover");
        remover.setActionCommand("remover");

        gbcBTN.gridx = 3;
        gbcBTN.gridy = 3;

        panelSala.add(remover, gbcBTN);

//Botao opcoesPermissoes
        opcoesPermissao = new JButton("Opções de permissão");
        opcoesPermissao.setActionCommand("opcoesPermissao");
        gbcBTN.gridx = 4;
        gbcBTN.gridy = 3;

        panelSala.add(opcoesPermissao, gbcBTN);

//Botao voltar
        voltar = new JButton("Voltar");
        voltar.setActionCommand("voltar");
        gbcBTN.gridx = 5;
        gbcBTN.gridy = 3;

        panelSala.add(voltar, gbcBTN);

//Gerenciador de botões
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        cadastro.addActionListener(btManager);
        editar.addActionListener(btManager);
        remover.addActionListener(btManager);
        opcoesPermissao.addActionListener(btManager);
        voltar.addActionListener(btManager);

// CONFIGURACOES TABELA
        table = new JTable();
        GridBagConstraints gbcTable = new GridBagConstraints();
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(650, 200));

        gbcTable.fill = GridBagConstraints.CENTER;
        gbcTable.gridx = 0;
        gbcTable.gridy = 0;
        gbcTable.gridheight = 2;
        gbcTable.gridwidth = 8;

        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(650, 200));

        panelSala.add(scroll, gbcTable);

        updateTable();
    }

    private void updateTable() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setNumRows(0);
        modelo.addColumn("Codigo");
        modelo.addColumn("Numero");
        modelo.addColumn("bloco");
        modelo.addColumn("centro");
        modelo.addColumn("campus");
        ArrayList<Sala> listaSalas = SalaDAO.getInstancia().getList();

        for (Sala sala : listaSalas) {

            modelo.addRow(new Object[]{sala.getCodigoSala(), sala.getNumero(), sala.getBloco(), sala.getCentro(), sala.getCampus()});

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
                        cadastraSala();
                        updateTable();
                        break;
                    case ("editar"):
                        editaSala();
                        updateTable();
                        break;
                    case ("remover"):
                        removeSala();
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
                JOptionPane.showMessageDialog(null, "Opcao Invalida! Escolha uma opcao dentre das opcoes na lista .");
                TelaSwingSala.getInstancia().setVisible(true);
            }

        }

    }

    /**
     * @return the instancia
     */
    public static TelaSwingSala getInstancia() {
        if (instancia == null) {
            instancia = new TelaSwingSala();
        }
        return instancia;
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return CtrlPrincipal.getInstancia();
    }

    private boolean removeSala() {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada >= 0) {
            String codigoSala = (String) table.getValueAt(linhaSelecionada, 0);
            return CtrlSala.getInstancia().delSala(codigoSala);
        } else {
            JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
        }
        return false;
    }

    private void cadastraSala() {
        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
        int numero = validador.recebeValorInteiro("Digite o numero da sala");
        char bloco = validador.recebeValorChar("Digite bloco da sala");
        String centro = validador.recebeValorString("Digite o centro da sala");
        String campus = validador.recebeValorString("Digite o campus que a sala fica localizada");

        if (CtrlSala.getInstancia().addSala(codigoSala, numero, bloco, centro, campus)) {
            System.out.println("Sala cadastrada!");
        } else {
            System.out.println("Nao foi realizado o cadastro, codigo de sala ja cadastrado!");
        }
    }

    private void editaSala() {

        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
        int numero = validador.recebeValorInteiro("Digite o numero da sala");
        char bloco = validador.recebeValorChar("Digite bloco da sala");
        String centro = validador.recebeValorString("Digite o centro da sala");
        String campus = validador.recebeValorString("Digite o campus que a sala fica localizada");
        CtrlSala.getInstancia().alteradorDeCadastroSala(codigoSala, numero, bloco, centro, campus);
        System.out.println("Cadastro de sala alterado!");
    }

    private void opcoesPermissao() {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int matricula = (int) table.getValueAt(linhaSelecionada, 0);
            getCtrlPrincipal().getCtrlPessoa().abreTelaGestaoPermissaoPessoa(matricula);
        } else {
            JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
        }
    }

    private void deletaPessoaNaSala() {
        System.out.println("---");
        System.out.println("Informacoes para exclusao de acesso da pessoa na sala");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula");
        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
        CtrlSala.getInstancia().deletaPessoaNaSala(matricula, codigoSala);
        System.out.println("Exclusao feita com sucesso");
    }

    private void listaPessoasCadastradasNaSala() {
        System.out.println("---");
        System.out.println("Informacoes para listar pessoas cadastradas na sala");
        System.out.println("---");
        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
        String listaDePessoas = CtrlSala.getInstancia().listaPessoasCadastradas(codigoSala);
        System.out.println("Estas sao as pessoas cadastradas na sala: ");
        System.out.println(listaDePessoas);
    }

    private void listAllSalasCadastradas() {
        System.out.println("---");
        System.out.println("Lista todas as salas cadastradas no sistema");
        System.out.println("---");
        System.out.println(CtrlSala.getInstancia().listAllSalasCadastradas());
    }

    private void cadastraPessoaNaSala() {
        System.out.println("---");
        System.out.println("Informacoes para inclusao de acesso da pessoa na sala");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula");
        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
        CtrlSala.getInstancia().cadastraPessoaNaSala(matricula, codigoSala);

        System.out.println("Inclusao feita com sucesso");

    }

    private void encontraSalaPorCodigo() {
        System.out.println("---");
        System.out.println("Informacao para encontrar sala e mostrar suas informacoes na tela");
        System.out.println("---");
        String codigoSala = validador.recebeValorString("Digite o codigo da sala");

        Sala sala = null;

        if (CtrlSala.getInstancia().findSalaByCodigoSala(codigoSala) != null) {
            sala = CtrlSala.getInstancia().findSalaByCodigoSala(codigoSala);
        } else {
            System.out.println("Nao foi encontrado uma sala com o codigo digitado. Tente novamente com um codigo valido");
        }

        System.out.println("Numero da sala: " + sala.getNumero());
        System.out.println("Bloco da sala: " + sala.getBloco());
        System.out.println("Centro : " + sala.getCentro());
        System.out.println("Campus: " + sala.getCampus());
    }

}

// public void inicio() { System.out.println("---TELA DE GERENCIAMENTO DE SALAS---");
//System.out.println(" ");
//}
//try { System.out.println("1 - Cadastrar sala no sistema");
//
//}
// System.out.println("2 - Deletar sala do sistema");
// 
//System.out.println("3 - Alterar cadastro sala"); 
//System.out.println("4 - Encontrar sala pelo codigo");
//System.out.println("5 - Gerenciamento de acesso - Incluir pessoa na sala");
//System.out.println("6 - Gerenciamento de acesso - Deletar pessoa na sala");
//System.out.println("7 - Listar pessoas cadastradas na sala");
//System.out.println("8 - Listar todas as salas cadastradas");
// System.out.println("9 - Voltar para o menu anterior");
// 
// System.out.println("99 - Sair");
// 
// int opcao = validador.recebeValorInteiro("--- Escolha uma das opcoes acima e tecle enter. ---"); 
//switch (opcao) { 
//case (1): 
//incluirSala();
//} inicio(); 
//case
// (2): deletaSala(); inicio(); case (3): alteraCadastroSala(); inicio(); case
// (4): encontraSalaPorCodigo(); teclado.nextLine(); inicio(); case (5):
// cadastraPessoaNaSala(); inicio(); case (6): deletaPessoaNaSala(); inicio();
// case (7): listaPessoasCadastradasNaSala(); inicio(); case (8):
// listAllSalasCadastradas(); inicio(); case (9):
// ctrlSala.getCtrlPrincipal().abreTelaAdm(); case (99): System.exit(0); } }
// catch (IllegalArgumentException e) { System.out.println(e.getMessage());
// String[] args = null; teclado.nextLine(); this.inicio();
// 
// } finally { teclado.nextLine(); } }

