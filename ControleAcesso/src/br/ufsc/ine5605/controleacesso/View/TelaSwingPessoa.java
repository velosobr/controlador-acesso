/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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
        
        Object[] columnNames = {"Matricula","Tipo", "Nome","Telefone", "Email", "Curso", "Cargo", "PermissaoADM"}; 
        
        Object[][] data = {{ 123, "Aluno", "Fulaninho", "6666666666", "fulaninho@gmail.com", "Biblioteconomia","",""},
            {456, "Servidor", "Ciclaninho", "6666666666", "ciclaninho@gmail.com", "", "Tecnico ADM", "true"},
            {777, "Servidor","Beltraninho", "6666666666", "beltraninho@gmail.com", "", "Professor", "false"},
            {666, "Servidor", "Fulaninha", "6666666666", "fulaninha@gmail.com", "", "Tecnico ADM", "true"}};
        
        
        
        GridBagConstraints tableConstraints = new GridBagConstraints();
        JTable table = new JTable(data, columnNames);
       
        table.setFillsViewportHeight(true);
        
        
        tableConstraints.fill = GridBagConstraints.CENTER;
        tableConstraints.gridx =0;
        tableConstraints.gridy = 0;
        tableConstraints.gridheight = 4;
        tableConstraints.gridwidth = 2;
        table.setPreferredScrollableViewportSize(new Dimension (200,10));
        JScrollPane scroll= new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(200,10));
       
        panelPessoa.add(table, tableConstraints);
        
    }
    
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                switch(e.getActionCommand()){
                    case ("cadastro"):
                        testeTipoPessoa();
                       
                        
                        break;
                    case ("editar"):
                        break;
                    case ("remover"):
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

        private void testeTipoPessoa() {
            
            String[] opcoes = {"Aluno", "Servidor"};
            int teste = JOptionPane.showOptionDialog(null, "Escolha um tipo de pessoa", "Selecione", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
            if(teste == 0){
                int matricula = validador.recebeValorInteiro("Digite a matricula: ");
                String nome = validador.recebeValorString("Digite o nome: ");
                String telefone = validador.recebeValorString("Digite o telefone: ");
                String email = validador.recebeValorString("Digite o email: ");
                String curso = validador.recebeValorString("Digite o curso");
                getCtrlPrincipal().getCtrlPessoa().incluiAluno(matricula, nome, teste, email, curso);
            }else{
                int matricula = validador.recebeValorInteiro("Digite a matricula: ");
                String nome = validador.recebeValorString("Digite o nome: ");
                String telefone = validador.recebeValorString("Digite o telefone: ");
                String email = validador.recebeValorString("Digite o email: ");
                String cargo = validador.recebeValorString("Digite o cargo");
                boolean administrador = validador.recebeValorBoolean();
                getCtrlPrincipal().getCtrlPessoa().incluiServidor(matricula, nome, teste, email, cargo, administrador);
            }
        }

        private void cadastraPessoa(int teste) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
