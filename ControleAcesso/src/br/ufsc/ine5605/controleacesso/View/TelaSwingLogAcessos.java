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
    
    
    private TelaSwingLogAcessos(){
        super("Opcoes de log de acesso");
        JPanel panelAcesso = new JPanel (new GridBagLayout());
        
        GridBagConstraints constraintsPanel = new GridBagConstraints();
        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(10, 10, 10, 10);
        panelAcesso.setBackground(Color.WHITE);
        setSize(1000, 500);
        
        panelAcesso.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Opcoes Pessoa"));
        
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelAcesso);
        
        GridBagConstraints constraintsBTN = new GridBagConstraints();
        
        // Label
        label = new JLabel();
        label.setText("Selecione uma das opções");
        
        // Botao Ver toda a lista
        verTodaLista = new JButton("Ver todos os acessos");
        verTodaLista.setActionCommand("verTodaLista");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 1;
        //constraintsBTN.ipadx = 20;
        //constraintsBTN.ipady = 20;
        
        panelAcesso.add(verTodaLista,constraintsBTN);
        
        // Botao Procurar por matricula
        procurarPorMatricula = new JButton("Procurar por matricula");
        procurarPorMatricula.setActionCommand("procuraMatricula");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 2;
        //constraintsBTN.ipadx = 20;
        //constraintsBTN.ipady = 20;
        
        panelAcesso.add(procurarPorMatricula,constraintsBTN);
        //Botao Procurar por codigo de sala
        procurarPorCodigoSala = new JButton("Procurar por codigo de sala");
        procurarPorCodigoSala.setActionCommand("procuraCodigoSala");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 3;
        //constraintsBTN.ipadx = 20;
        //constraintsBTN.ipady = 20;
        
        panelAcesso.add(procurarPorCodigoSala,constraintsBTN);
        

        //Botao voltar
        voltar = new JButton("Voltar");
        voltar.setActionCommand("voltar");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 4;
        //constraintsBTN.ipadx = 0;
        //constraintsBTN.ipady = 0;
        
        panelAcesso.add(voltar,constraintsBTN);
        
        GridBagConstraints tableConstraints = new GridBagConstraints();
        
        table = new JTable();
        JScrollPane scroll= new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(650,200));
       
           
        tableConstraints.fill = GridBagConstraints.CENTER;
        tableConstraints.gridx =0;
        tableConstraints.gridy = 0;
        tableConstraints.gridheight = 4;
        tableConstraints.gridwidth = 2;
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension (650,200));
        
        
       
        panelAcesso.add(scroll, tableConstraints);
        
        
    }
    private void updateTable(int matricula) {
        DefaultTableModel modelo = new DefaultTableModel(); 
        modelo.setNumRows(0);
        modelo.addColumn("ID");
        modelo.addColumn("Matricula");
        modelo.addColumn("nome");
        modelo.addColumn("Sala");
        modelo.addColumn("Data");
        modelo.addColumn("Situação");
        
        ArrayList <Acesso> listaAcessos = CtrlAcesso.getInstancia().geraListaByMatricula(matricula);
        for(Acesso acesso:listaAcessos){
            modelo.addRow(new Object[]{acesso.getId(), acesso.getPessoa().getMatricula(), acesso.getPessoa().getNome(), acesso.getSala().getCodigoSala(), acesso.getData(), acesso.getSituacao()});
        }
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
        
        ArrayList <Acesso> listaAcessos = CtrlAcesso.getInstancia().geraListaByCodigoSala(codigoSala);
        for(Acesso acesso:listaAcessos){
            modelo.addRow(new Object[]{acesso.getId(), acesso.getPessoa().getMatricula(), acesso.getPessoa().getNome(), acesso.getSala().getCodigoSala(), acesso.getData(), acesso.getSituacao()});
        }
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
        
        ArrayList <Acesso> listaAcessos = CtrlAcesso.getInstancia().geraListaTodosAcessos();
        for(Acesso acesso:listaAcessos){
            modelo.addRow(new Object[]{acesso.getId(), acesso.getPessoa().getMatricula(), acesso.getPessoa().getNome(), acesso.getSala().getCodigoSala(), acesso.getData(), acesso.getSituacao()});
        }
    }
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                switch(e.getActionCommand()){
                    case ("verTodaLista"):
                        
                        updateTable();
                        break;
                    case ("procuraMatricula"):
                        
                        
                        break;
                    case ("procuraCodigoSala"):
                        
                        
                        break;
                    
                    case ("voltar"):
                        setVisible(false);
                        CtrlPrincipal.getInstancia().abreTelaAdm();
                        break;
                    
                }
            }catch (Exception ex){
               JOptionPane.showMessageDialog(null, "Opcao Invalida! Escolha uma opcao dentre das opcoes na lista .");
                TelaSwingLogAcessos.getInstancia().setVisible(true);
            }
            
            
        }

      
        

       
       
        
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
