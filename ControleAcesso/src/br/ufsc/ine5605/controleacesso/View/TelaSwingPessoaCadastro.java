/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPessoa;
import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Exceptions.MatriculaJahExisteException;
import br.ufsc.ine5605.controleacesso.Model.EhAdm;
import br.ufsc.ine5605.controleacesso.Model.TipoCargo;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author caiocaio
 */
public class TelaSwingPessoaCadastro extends JFrame {
    private JLabel label;
    private JLabel opcaoCadastro;
    private JLabel matricula;
    private JLabel nome;
    private JLabel telefone;
    private JLabel email;
    private JLabel curso;
    private JLabel cargo;
    private JLabel ehAdm;
    
    private JTextField matriculaTextField;
    private JTextField nomeTextField;
    private JTextField telefoneTextField;
    private JTextField emailTextField;
    private JTextField cursoTextField;
    private JComboBox cargoField;
    private JComboBox ehAdmField;
    
    private JButton confirmar;
    private JButton cancelar;
    
    private ValidaERetorna validador = new ValidaERetorna();
   

    public TelaSwingPessoaCadastro(int teste) {
        super("Opcoes de gerenciamento de pessoa");
        if(teste == 0){
            getContentPane().add(telaAluno());
        }else{
            getContentPane().add(telaServidor());
        }
    }

    private JPanel telaAluno() {
        JPanel panelAluno = new JPanel();
        GridBagConstraints constraintsPanel = new GridBagConstraints();

        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(0, 0, 0, 0);
        panelAluno.setBackground(Color.WHITE);
        setSize(400, 300);

        panelAluno.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Opcoes Pessoa"));

        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 9;
        gbcPanel.gridy = 1;
        gbcPanel.gridwidth = 3;
        gbcPanel.gridheight = 1;
        gbcPanel.anchor = GridBagConstraints.NORTH;
        gbcPanel.insets = new Insets(0,0,0,0);
        
        matricula = new JLabel("Matricula");
        nome = new JLabel ("Nome");
        telefone = new JLabel ("Telefone");
        email = new JLabel ("Email");
        curso = new JLabel ("Curso");
        
        
        matriculaTextField = new JTextField("");
        nomeTextField = new JTextField("");
        telefoneTextField = new JTextField("");
        emailTextField = new JTextField("");
        cursoTextField = new JTextField("");
        
        
        panelAluno.setLayout(new GridBagLayout());
        panelAluno.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 2, 2);
        gbc.anchor = GridBagConstraints.NORTHEAST;
                
        
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        panelAluno.add(matricula,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        panelAluno.add(matriculaTextField, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        panelAluno.add(nome, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        
        panelAluno.add(nomeTextField, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        
        panelAluno.add(telefone, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        
        panelAluno.add(telefoneTextField, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        
        panelAluno.add(email, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 3;
        
        panelAluno.add(emailTextField, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        
        panelAluno.add(curso, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        
        panelAluno.add(cursoTextField, gbc);
       
        confirmar = new JButton("Confirmar");
        confirmar.setActionCommand("confirmaAluno");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        
        panelAluno.add(confirmar, gbc);
        cancelar = new JButton ("Cancelar");
        cancelar.setActionCommand("cancelar");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelAluno.add(cancelar, gbc);
        
        
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        confirmar.addActionListener(btManager);
        cancelar.addActionListener(btManager);
      
    return panelAluno;
    }

    private JPanel telaServidor() {
        JPanel panelCadastro = new JPanel();
        GridBagConstraints constraintsPanel = new GridBagConstraints();

        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(0, 0, 0, 0);
        panelCadastro.setBackground(Color.WHITE);
        setSize(400, 300);

        panelCadastro.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Opcoes Pessoa"));

        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 9;
        gbcPanel.gridy = 1;
        gbcPanel.gridwidth = 3;
        gbcPanel.gridheight = 1;
        gbcPanel.anchor = GridBagConstraints.NORTH;
        gbcPanel.insets = new Insets(0,0,0,0);
        
        matricula = new JLabel("Matricula");
        nome = new JLabel ("Nome");
        telefone = new JLabel ("Telefone");
        email = new JLabel ("Email");
        
        cargo = new JLabel ("Cargo");
        ehAdm= new JLabel ("Eh administrador?");
        
        matriculaTextField = new JTextField("");
        nomeTextField = new JTextField("");
        telefoneTextField = new JTextField("");
        emailTextField = new JTextField("");
        
        cargoField = new JComboBox(TipoCargo.values());
        ehAdmField = new JComboBox(EhAdm.values());
        
        panelCadastro.setLayout(new GridBagLayout());
        panelCadastro.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 2, 2);
        gbc.anchor = GridBagConstraints.NORTHEAST;
                
        
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        panelCadastro.add(matricula,gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        panelCadastro.add(matriculaTextField, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        panelCadastro.add(nome, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        
        panelCadastro.add(nomeTextField, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        
        panelCadastro.add(telefone, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        
        panelCadastro.add(telefoneTextField, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        
        panelCadastro.add(email, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 3;
        
        panelCadastro.add(emailTextField, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        
        panelCadastro.add(cargo, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 4;
        
        panelCadastro.add(cargoField, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        
        panelCadastro.add(ehAdm, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 5;
        
        panelCadastro.add(ehAdmField, gbc);
        
        confirmar = new JButton("Confirmar");
        confirmar.setActionCommand("confirmaServidor");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 6;
        
        panelCadastro.add(confirmar, gbc);
        
        cancelar = new JButton ("Cancelar");
        cancelar.setActionCommand("cancelar");
        gbc.gridx = 1;
        gbc.gridy = 6;
        
        panelCadastro.add(cancelar, gbc);
        
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        confirmar.addActionListener(btManager);
        cancelar.addActionListener(btManager);
        
        
    return panelCadastro;
    }
    
    private class GerenciadorBotoes implements ActionListener {
        TelaSwingPessoa telaPessoa;
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                switch (e.getActionCommand()) {
                    case ("confirmaAluno"):
                        cadastroAluno();
                        break;
                    case ("confirmaServidor"):
                        cadastroServidor();
                        
                        break;
                    case ("cancelar"):
                        setVisible(false);
                        CtrlPessoa.getInstancia().abreTelaSwingPessoa();
                        
                        break;
                    

                }
            } catch (Exception ex) {
                //JOptionPane.showMessageDialog(null, "Opcao Invalida! Escolha uma opcao dentre das opcoes na lista.");
                TelaSwingPessoa.getInstancia().setVisible(true);
            }

        }

         public CtrlPessoa getCtrlPessoa() {
        return CtrlPessoa.getInstancia();
           }       
            
            
    }

        
    
        
    private void cadastroAluno() throws Exception {
            
            try{
              
                Integer matriculaParaCadastrar = Integer.valueOf(matriculaTextField.getText());
                String nomeParaCadastrar = nomeTextField.getText();
                long telefoneParaCadastrar = Integer.valueOf(telefoneTextField.getText());
                String emailParaCadastrar = emailTextField.getText();
                String cursoParaCadastrar = cursoTextField.getText();
                CtrlPessoa.getInstancia().incluiAluno(matriculaParaCadastrar, nomeParaCadastrar, telefoneParaCadastrar, emailParaCadastrar, cursoParaCadastrar);
                CtrlPessoa.getInstancia().abreTelaSwingPessoa();
                TelaSwingPessoa.getInstancia().updateTable();
                setVisible(false);
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null,"Digite um numero inteiro para matricula e telefone");
            
                
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }

     

   }

    private void cadastroServidor() throws Exception{
            try{    
                Integer matriculaParaCadastrar = Integer.valueOf(matriculaTextField.getText());
                String nomeParaCadastrar = nomeTextField.getText();
                long telefoneParaCadastrar = Integer.valueOf(telefoneTextField.getText());
                String emailParaCadastrar = emailTextField.getText();
                
                String cargoParaCadastrar = cargoField.getSelectedItem().toString();
                System.out.println("cargoParaCadastrar" + cargoParaCadastrar);
                String ehAdmString = ehAdmField.getSelectedItem().toString();
                boolean ehAdmBool;
                if(ehAdmString.equals("SIM")){
                    ehAdmBool = true;
                }else{
                    ehAdmBool = false;
                }
                
                CtrlPessoa.getInstancia().incluiServidor(matriculaParaCadastrar, nomeParaCadastrar, telefoneParaCadastrar, emailParaCadastrar, cargoParaCadastrar, ehAdmBool);
                CtrlPessoa.getInstancia().abreTelaSwingPessoa();
                TelaSwingPessoa.getInstancia().updateTable();
                setVisible(false);
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null,"Digite um numero inteiro para matricula e telefone");
            
                
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }

       
}   

