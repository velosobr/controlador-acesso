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
public class TelaSwingPessoa extends JFrame{
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
        
       
        JPanel panelPessoa = new JPanel (new GridBagLayout());
        
        GridBagConstraints constraintsPanel = new GridBagConstraints();
        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(10, 10, 10, 10);
        panelPessoa.setBackground(Color.WHITE);
        setSize(1000, 500);
        
        panelPessoa.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Opcoes Pessoa"));
        
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelPessoa);
        
        GridBagConstraints constraintsBTN = new GridBagConstraints();
        
        //COMPONENTES TELASWINGPESSOA
        // Label
        label = new JLabel();
        label.setText("Selecione uma das opções");
        
        
        //Botao Cadastro
        cadastro = new JButton("Cadastro");
        cadastro.setActionCommand("cadastro");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 0;
        //constraintsBTN.ipadx = 20;
        //constraintsBTN.ipady = 20;
        
        panelPessoa.add(cadastro,constraintsBTN );
        //Botao Editar
        editar = new JButton("Editar");
        editar.setActionCommand("editar");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 1;
        //constraintsBTN.ipadx = 20;
        //constraintsBTN.ipady = 20;
        
        panelPessoa.add(editar,constraintsBTN);
        // Botao Remover
        remover = new JButton("Remover");
        remover.setActionCommand("remover");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 2;
        //constraintsBTN.ipadx = 20;
        //constraintsBTN.ipady = 20;
        
        panelPessoa.add(remover,constraintsBTN);
        //Botao opcoesPermissoes
        opcoesPermissao = new JButton("Opcoes de permissao");
        opcoesPermissao.setActionCommand("opcoesPermissao");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 3;
        //constraintsBTN.ipadx = 20;
        //constraintsBTN.ipady = 20;
        
        panelPessoa.add(opcoesPermissao,constraintsBTN);
        

        //Botao voltar
        voltar = new JButton("Voltar");
        voltar.setActionCommand("voltar");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 4;
        //constraintsBTN.ipadx = 0;
        //constraintsBTN.ipady = 0;
        
        panelPessoa.add(voltar,constraintsBTN);
       
        
        
        
        
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        cadastro.addActionListener(btManager);
        editar.addActionListener(btManager);
        remover.addActionListener(btManager);
        opcoesPermissao.addActionListener(btManager);
        voltar.addActionListener(btManager);
        
        
        
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
        
        
       
        panelPessoa.add(scroll, tableConstraints);
        
        updateTable();
    }
    
    private void updateTable(){
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
        ArrayList <Pessoa> listaPessoas = PessoaDAO.getInstancia().getList();
        
        for(Pessoa pessoa: listaPessoas){
            
            if(pessoa instanceof Aluno){
                
                Aluno aluno = (Aluno) pessoa;
                modelo.addRow(new Object []{aluno.getMatricula(),aluno.getNome(),aluno.getTelefone(), aluno.getEmail(), aluno.getCurso(), "N/A", "N/A", "Aluno"});
            }else{
                Servidor servidor = (Servidor) pessoa;
                modelo.addRow(new Object [] {servidor.getMatricula(), servidor.getNome(), servidor.getTelefone(), servidor.getEmail(), "N/A", servidor.getCargo(), servidor.isAdministrador(), "Servidor"});
            }
            
            table.setModel(modelo);
            this.repaint();
        }           
    }
    
   /* private static class SeletorLinha implements ListSelectionListener{
        JTable table;
        TelaSwingPessoa context;
        Object record;
        Pessoa pessoa;
        
        public SeletorLinha(JTable table, TelaSwingPessoa context){
            this.table = table;
            this.context = context;
}
        @Override
        public void valueChanged(ListSelectionEvent e) {
            record = this.table.getValueAt(table.getSelectedRow(),0);
            this.setRecord(record);
        }
        
        public void setRecord(Object record){
            this.record = record;
}
        
    }*/
    
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                switch(e.getActionCommand()){
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
                        
                        break;
                    case ("voltar"):
                        setVisible(false);
                        CtrlPrincipal.getInstancia().abreTelaInicial();
                        break;
                    
                }
            }catch (Exception ex){
               JOptionPane.showMessageDialog(null, "Opcao Invalida! Escolha uma opcao dentre das opcoes na lista .");
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
                String cargo = validador.recebeValorString("Digite o cargo: ");
                boolean administrador = validador.recebeValorBoolean();
                getCtrlPrincipal().getCtrlPessoa().incluiServidor(matricula, nome, telefone, email, cargo, administrador);
        }
    }
    
    
    private void editarPessoa() {
        
        int linhaSelecionada = table.getSelectedRow();
        if(linhaSelecionada>=0){
            String tipoPessoa = table.getValueAt(linhaSelecionada, 7).toString();
            int matricula = (int) table.getValueAt(linhaSelecionada, 0);
            if(tipoPessoa.equals("Aluno")){
                String nome = validador.recebeValorString("Digite o nome: ");
                long telefone = validador.recebeValorLong("Digite o telefone: ");
                String email = validador.recebeValorString("Digite o email: ");
                String curso = validador.recebeValorString("Digite o curso: ");
                getCtrlPrincipal().getCtrlPessoa().alteradorDeCadastroAluno(matricula, nome, telefone, email, curso);
            }else{
                String nome = validador.recebeValorString("Digite o nome: ");
                long telefone = validador.recebeValorLong("Digite o telefone: ");
                String email = validador.recebeValorString("Digite o email: ");
                String cargo = validador.recebeValorString("Digite o cargo: ");
                boolean administrador = validador.recebeValorBoolean();
                getCtrlPrincipal().getCtrlPessoa().alteradorDeCadastroServidor(matricula, nome, telefone, email, cargo, administrador);
            }
        }else{
            JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
        }
    }
    
     private void removerPessoa() {
            int linhaSelecionada = table.getSelectedRow();
            if(linhaSelecionada>=0){
                int matricula = (int) table.getValueAt(linhaSelecionada, 0);
                Pessoa pessoaParaRemover = PessoaDAO.getInstancia().getPessoa(matricula);
                PessoaDAO.getInstancia().remove(pessoaParaRemover);
            }else{
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
