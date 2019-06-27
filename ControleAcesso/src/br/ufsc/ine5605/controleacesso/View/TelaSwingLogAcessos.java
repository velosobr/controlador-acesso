/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlAcesso;
import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Model.Acesso;
import br.ufsc.ine5605.controleacesso.Persistencia.AcessoDAO;
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
 * @author caiocaio
 */
public class TelaSwingLogAcessos extends JFrame {

    private static TelaSwingLogAcessos instancia;
    private JLabel label;
    private JButton verTodaLista;
    private JButton procurarPorMatricula;
    private JButton procurarPorCodigoSala;
    private JButton voltar;
    private JTable table;
    private ValidaERetorna validador = new ValidaERetorna();

    private TelaSwingLogAcessos() {
        super("Opcoes de log de acesso");
        System.out.println("entra no construtor TelaSwingLogAcessos");

        JPanel panelAcesso = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsPanel = new GridBagConstraints();

        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(5, 5, 5, 5);
        panelAcesso.setBackground(Color.WHITE);
        setSize(800, 300);

        panelAcesso.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Gerenciamento acesso"));

        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelAcesso);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
// Label
        label = new JLabel();
        label.setText("Selecione uma das opções");

// Botao Ver toda a lista
        verTodaLista = new JButton("Ver todos os acessos");
        verTodaLista.setActionCommand("verTodaLista");

        gbc.gridx = 1;
        gbc.gridy = 3;

        panelAcesso.add(verTodaLista, gbc);

// Botao Procurar por matricula
        procurarPorMatricula = new JButton("Procurar por matricula");
        procurarPorMatricula.setActionCommand("procuraMatricula");

        gbc.gridx = 2;
        gbc.gridy = 3;

        panelAcesso.add(procurarPorMatricula, gbc);

//Botao Procurar por codigo de sala
        procurarPorCodigoSala = new JButton("Procurar por codigo de sala");
        procurarPorCodigoSala.setActionCommand("procuraCodigoSala");

        gbc.gridx = 3;
        gbc.gridy = 3;

        panelAcesso.add(procurarPorCodigoSala, gbc);

//Botao voltar
        voltar = new JButton("Voltar");
        voltar.setActionCommand("voltar");

        gbc.gridx = 4;
        gbc.gridy = 3;

        panelAcesso.add(voltar, gbc);

        GerenciadorBotoes btManager = new GerenciadorBotoes();
        verTodaLista.addActionListener(btManager);
        procurarPorMatricula.addActionListener(btManager);
        procurarPorCodigoSala.addActionListener(btManager);
        voltar.addActionListener(btManager);

//CONFIGURACOES TABELA  
        System.out.println("entra na CONFIGURACOES TABELA");
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

        panelAcesso.add(scroll, gbcTable);

      

    }

    private void updateTable(int matricula) throws Exception{
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setNumRows(0);
        modelo.addColumn("ID");
        modelo.addColumn("Matricula");
        modelo.addColumn("nome");
        modelo.addColumn("Sala");
        modelo.addColumn("Data");
        modelo.addColumn("Situação");
        try{
            ArrayList<Acesso> listaAcessos = CtrlAcesso.getInstancia().geraListaByMatricula(matricula);
        
            for (Acesso acesso : listaAcessos) {
            
                modelo.addRow(new Object[]{acesso.getId(), acesso.getPessoa().getMatricula(), acesso.getPessoa().getNome(), acesso.getSala().getCodigoSala(), acesso.getData(), acesso.getSituacao()});
            }
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        
        table.setModel(modelo);
        this.repaint();
    }

    private void updateTable(String codigoSala) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setNumRows(0);
        modelo.addColumn("ID");
        modelo.addColumn("Matricula");
        modelo.addColumn("nome");
        modelo.addColumn("Sala");
        modelo.addColumn("Data");
        modelo.addColumn("Situação");
        try{
            ArrayList<Acesso> listaAcessos = CtrlAcesso.getInstancia().geraListaByCodigoSala(codigoSala);
        for (Acesso acesso : listaAcessos) {
            
            modelo.addRow(new Object[]{acesso.getId(), acesso.getPessoa().getMatricula(), acesso.getPessoa().getNome(), acesso.getSala().getCodigoSala(), acesso.getData(), acesso.getSituacao()});
            }
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        
        table.setModel(modelo);
        this.repaint();
    }

    private void updateTable() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setNumRows(0);
        modelo.addColumn("ID");
        modelo.addColumn("Matricula");
        modelo.addColumn("nome");
        modelo.addColumn("Sala");
        modelo.addColumn("Data");
        modelo.addColumn("Situação");

        ArrayList<Acesso> listaAcessos = CtrlAcesso.getInstancia().geraListaTodosAcessos();
        for (Acesso acesso : listaAcessos) {
            modelo.addRow(new Object[]{acesso.getId(), acesso.getPessoa().getMatricula(), acesso.getPessoa().getNome(), acesso.getSala().getCodigoSala(), acesso.getData(), acesso.getSituacao()});
        }
        table.setModel(modelo);
        this.repaint();
    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                switch (e.getActionCommand()) {
                    case ("verTodaLista"):

                        updateTable();
                        break;
                    case ("procuraMatricula"):
                        procuraPorMatricula();

                        break;
                    case ("procuraCodigoSala"):
                        procuraPorCodigoSala();

                        break;

                    case ("voltar"):
                        setVisible(false);
                        CtrlPrincipal.getInstancia().abreTelaAdm();
                        break;

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Opcao Invalida! Escolha uma opcao dentre das opcoes na lista .");
                TelaSwingLogAcessos.getInstancia().setVisible(true);
            }

        }

    }

    private void procuraPorMatricula() throws Exception {
        
        int matricula = validador.recebeValorInteiro("Digite a matricula: ");
        updateTable(matricula);
    }

    private void procuraPorCodigoSala() {
        
        String codigoSala = validador.recebeValorString("Digite o codigo de sala: ");
        updateTable(codigoSala);

    }

    public static TelaSwingLogAcessos getInstancia() {
        if (instancia == null) {
            instancia = new TelaSwingLogAcessos();
        }
        return instancia;
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return CtrlPrincipal.getInstancia();
    }

}
