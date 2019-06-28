/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;
//IMPORTS

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Controller.CtrlSala;
import br.ufsc.ine5605.controleacesso.Model.Sala;
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
//CONSTRUTOR

    public TelaSwingSala() {
        super("Gerenciamento de salas");
        getContentPane().add(panelPrincipal());

    }

    //CRIAÇÃO DO PAINEL
    private JPanel panelPrincipal() {
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

        GridBagConstraints gbcBTN = new GridBagConstraints();
        gbcBTN.insets = new Insets(5, 5, 5, 5);

//COMPONENTES TELASWINGSALA
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

        return panelSala;
    }

    public void updateTable() {
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
                        int response = JOptionPane.showConfirmDialog(null, "Você tem certeza que vai excluir???", "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.OK_CANCEL_OPTION);
                        if (response == JOptionPane.NO_OPTION) {
                            updateTable();

                        } else if (response == JOptionPane.YES_OPTION) {
                            removeSala();
                            updateTable();
                        }
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

    public CtrlSala getCtrlSala() {
        return CtrlSala.getInstancia();
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

        try {

            getCtrlSala().abreTelaCadastroSala();

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());

        }
//        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
//        int numero = validador.recebeValorInteiro("Digite o numero da sala");
//        char bloco = validador.recebeValorChar("Digite bloco da sala");
//        String centro = validador.recebeValorString("Digite o centro da sala");
//        String campus = validador.recebeValorString("Digite o campus que a sala fica localizada");
//
//        if (CtrlSala.getInstancia().addSala(codigoSala, numero, bloco, centro, campus)) {
//            System.out.println("Sala cadastrada!");
//        } else {
//            System.out.println("Nao foi realizado o cadastro, codigo de sala ja cadastrado!");
//        }
    }

    private void editaSala() {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada >= 0) {
            String codigoSala = (String) table.getValueAt(linhaSelecionada, 0);
            try {

                int numero = validador.recebeValorInteiro("Digite o numero da sala");
                char bloco = validador.recebeValorChar("Digite bloco da sala");
                String centro = validador.recebeValorString("Digite o centro da sala");
                String campus = validador.recebeValorString("Digite o campus que a sala fica localizada");
                CtrlSala.getInstancia().alteradorDeCadastroSala(codigoSala, numero, bloco, centro, campus);
                JOptionPane.showMessageDialog(null, "Cadastro de sala alterado!");
            } catch (Exception e) {

                if (e.getMessage().equals("null")) {

                } else {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }

    }

    private void opcoesPermissao() {
        int linhaSelecionada = table.getSelectedRow();
        if (linhaSelecionada >= 0) {
            String codSala = (String) table.getValueAt(linhaSelecionada, 0);
            getCtrlSala().abreTelaGestaoPermissaoSala(codSala);
        } else {
            JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
        }
    }

//    private void deletaPessoaNaSala() {
//        System.out.println("---");
//        System.out.println("Informacoes para exclusao de acesso da pessoa na sala");
//        System.out.println("---");
//        int matricula = validador.recebeValorInteiro("Digite a matricula");
//        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
//        CtrlSala.getInstancia().deletaPessoaNaSala(matricula, codigoSala);
//        System.out.println("Exclusao feita com sucesso");
//    }
//
//    private void listaPessoasCadastradasNaSala() {
//        System.out.println("---");
//        System.out.println("Informacoes para listar pessoas cadastradas na sala");
//        System.out.println("---");
//        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
//        String listaDePessoas = CtrlSala.getInstancia().listaPessoasCadastradas(codigoSala);
//        System.out.println("Estas sao as pessoas cadastradas na sala: ");
//        System.out.println(listaDePessoas);
//    }
//
//    private void listAllSalasCadastradas() {
//        System.out.println("---");
//        System.out.println("Lista todas as salas cadastradas no sistema");
//        System.out.println("---");
//        System.out.println(CtrlSala.getInstancia().listAllSalasCadastradas());
//    }
//
//    private void cadastraPessoaNaSala() {
//        System.out.println("---");
//        System.out.println("Informacoes para inclusao de acesso da pessoa na sala");
//        System.out.println("---");
//        int matricula = validador.recebeValorInteiro("Digite a matricula");
//        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
//        CtrlSala.getInstancia().cadastraPessoaNaSala(matricula, codigoSala);
//
//        System.out.println("Inclusao feita com sucesso");
//
//    }
//
//    private void encontraSalaPorCodigo() {
//        System.out.println("---");
//        System.out.println("Informacao para encontrar sala e mostrar suas informacoes na tela");
//        System.out.println("---");
//        String codigoSala = validador.recebeValorString("Digite o codigo da sala");
//
//        Sala sala = null;
//
//        if (CtrlSala.getInstancia().findSalaByCodigoSala(codigoSala) != null) {
//            sala = CtrlSala.getInstancia().findSalaByCodigoSala(codigoSala);
//        } else {
//            System.out.println("Nao foi encontrado uma sala com o codigo digitado. Tente novamente com um codigo valido");
//        }
//
//        System.out.println("Numero da sala: " + sala.getNumero());
//        System.out.println("Bloco da sala: " + sala.getBloco());
//        System.out.println("Centro : " + sala.getCentro());
//        System.out.println("Campus: " + sala.getCampus());
//    }

}
