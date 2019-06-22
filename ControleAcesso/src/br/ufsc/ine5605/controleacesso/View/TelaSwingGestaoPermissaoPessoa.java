/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPessoa;
import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Model.Aluno;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
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
public class TelaSwingGestaoPermissaoPessoa extends JFrame {
    private static TelaSwingGestaoPermissaoPessoa instancia;
    private JLabel label;
    private JButton permitirAcesso;
    private JButton removerAcesso;
    private JButton voltar;
    private JTable table;
    private int matriculaPessoa;
    private ValidaERetorna validador = new ValidaERetorna();
   
public TelaSwingGestaoPermissaoPessoa(int matricula){
    super("Opcoes de gerenciamento de permissao de acesso");
    int matriculaPessoa = matricula;
    JPanel panelPermissaoPessoa = new JPanel (new GridBagLayout());
        
        GridBagConstraints constraintsPanel = new GridBagConstraints();
        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(10, 10, 10, 10);
        panelPermissaoPessoa.setBackground(Color.WHITE);
        setSize(1000, 500);
        
        panelPermissaoPessoa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Opcoes de permissao de acesso"));
        
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelPermissaoPessoa);
        
        GridBagConstraints constraintsBTN = new GridBagConstraints();
        
        //COMPONENTES TELASWINGPESSOA
        // Label
        label = new JLabel();
        label.setText("Selecione uma das opções");
        
        
        //Permitir Acesso a Sala
        permitirAcesso = new JButton("Permitir Acesso");
        permitirAcesso.setActionCommand("permitirAcesso");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 0;
        //constraintsBTN.ipadx = 20;
        //constraintsBTN.ipady = 20;
        panelPermissaoPessoa.add(permitirAcesso,constraintsBTN );
        
        
        //Botao Remover
        removerAcesso = new JButton("Remover Acesso");
        removerAcesso.setActionCommand("removerAcesso");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 1;
        //constraintsBTN.ipadx = 20;
        //constraintsBTN.ipady = 20;
        
        panelPermissaoPessoa.add(removerAcesso,constraintsBTN);
        

        //Botao voltar
        voltar = new JButton("Voltar");
        voltar.setActionCommand("voltar");
        constraintsBTN.weightx = 0.5;
        constraintsBTN.insets = new Insets(10, 0, 0, 0);
        constraintsBTN.gridx = 2;
        constraintsBTN.gridy = 4;
        //constraintsBTN.ipadx = 0;
        //constraintsBTN.ipady = 0;
        
        panelPermissaoPessoa.add(voltar,constraintsBTN);
       
        
        
        
        
        TelaSwingGestaoPermissaoPessoa.GerenciadorBotoes btManager = new TelaSwingGestaoPermissaoPessoa.GerenciadorBotoes();
        permitirAcesso.addActionListener(btManager);
        removerAcesso.addActionListener(btManager);
        
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
        
        
       
        panelPermissaoPessoa.add(scroll, tableConstraints);
        
        updateTable();
    }
    
    private void updateTable(){
        DefaultTableModel modelo = new DefaultTableModel(); 
        modelo.setNumRows(0);
        modelo.addColumn("Codigo Sala");
        modelo.addColumn("Numero");
        modelo.addColumn("Bloco");
        modelo.addColumn("Centro");
        modelo.addColumn("Campus");
        
        ArrayList <Sala> listaSalasCadastradas = PessoaDAO.getInstancia().getPessoa(matriculaPessoa).getSalasCadastradas();
        
        for(Sala sala: listaSalasCadastradas){
            modelo.addRow(new Object []{sala.getCodigoSala(),sala.getNumero(),sala.getBloco(), sala.getCentro(), sala.getCampus()});
            
        }
        table.setModel(modelo);
        this.repaint();
    }

    private  class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                switch(e.getActionCommand()){
                    case ("permitirAcesso"):
                        permitirAcesso();
                        updateTable();
                        break;
                    case ("removerAcesso"):
                        removerAcesso();
                        updateTable();
                        break;
                    case ("voltar"):
                        setVisible(false);
                        CtrlPessoa.getInstancia().abreTelaSwingPessoa();
                        break;
                    
                }
            }catch (Exception ex){
               JOptionPane.showMessageDialog(null, "Opcao Invalida! Escolha uma opcao dentre das opcoes na lista .");
                
            }
            
            
        }

       

        
    }

    private void permitirAcesso() {
            String sala = validador.recebeValorString("Digite o codigo de sala: ");
            CtrlPessoa.getInstancia().cadastraSalaNaPessoa(matriculaPessoa, sala);
        }
    
     private void removerAcesso() {
            int linhaSelecionada = table.getSelectedRow();
             String codigoSala = (String) table.getValueAt(linhaSelecionada, 0);
             CtrlPessoa.getInstancia().delSalaNaPessoa(matriculaPessoa, codigoSala);
        }
}




/*public static TelaSwingGestaoPermissaoPessoa GetInstancia() {
if (instancia == null) {
instancia = new TelaSwingGestaoPermissaoPessoa();
}
return instancia;
}

}*/
