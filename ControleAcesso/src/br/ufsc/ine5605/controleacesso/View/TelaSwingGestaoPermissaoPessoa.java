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
    
    private JLabel label;
    private JButton permitirAcesso;
    private JButton removerAcesso;
    private JButton voltar;
    private JTable table;
    private int matriculaPessoa;
    private ValidaERetorna validador = new ValidaERetorna();
   
public TelaSwingGestaoPermissaoPessoa(int matricula){
    super("Opcoes de gerenciamento de permissao de acesso");
    matriculaPessoa = matricula;
    JPanel panelPermissaoPessoa = new JPanel (new GridBagLayout());
        
        GridBagConstraints constraintsPanel = new GridBagConstraints();
        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(5,5,5,5);
        panelPermissaoPessoa.setBackground(Color.WHITE);
        setSize(680, 300);
        
        panelPermissaoPessoa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Opcoes de permissao de acesso"));
        
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelPermissaoPessoa);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        
//COMPONENTES TELASWINGPESSOA
// Label
        label = new JLabel();
        label.setText("Selecione uma das opções");
        
        
//Permitir Acesso a Sala
        permitirAcesso = new JButton("Permitir Acesso");
        permitirAcesso.setActionCommand("permitirAcesso");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        panelPermissaoPessoa.add(permitirAcesso,gbc );
        
        
//Botao Remover
        removerAcesso = new JButton("Remover Acesso");
        removerAcesso.setActionCommand("removerAcesso");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        panelPermissaoPessoa.add(removerAcesso,gbc);
        

//Botao voltar
        voltar = new JButton("Voltar");
        voltar.setActionCommand("voltar");
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        
        panelPermissaoPessoa.add(voltar,gbc);
       
        
        
        
        
        TelaSwingGestaoPermissaoPessoa.GerenciadorBotoes btManager = new TelaSwingGestaoPermissaoPessoa.GerenciadorBotoes();
        permitirAcesso.addActionListener(btManager);
        removerAcesso.addActionListener(btManager);
        
        voltar.addActionListener(btManager);
        
        
        
       
//CONFIGURACOES TABELA       
        table = new JTable();
        JScrollPane scroll= new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(650,200));
       
           
        gbc.gridx =0;
        gbc.gridy = 0;
        gbc.gridheight =2;
        gbc.gridwidth = 8;
        gbc.fill = GridBagConstraints.CENTER;
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension (650,200));
        
        
       
        panelPermissaoPessoa.add(scroll, gbc);
        
        updateTable(matricula);
    }
    
    private void updateTable(int matriculaPessoa){
        DefaultTableModel modelo = new DefaultTableModel(); 
        modelo.setNumRows(0);
        modelo.addColumn("Codigo Sala");
        modelo.addColumn("Numero");
        modelo.addColumn("Bloco");
        modelo.addColumn("Centro");
        modelo.addColumn("Campus");
        
        ArrayList <Sala> listaSalasCadastradas = PessoaDAO.getInstancia().getPessoa(matriculaPessoa).getSalasCadastradas();
        if(listaSalasCadastradas == null){
            modelo.addRow(new Object [] {"Sem sala cadastrada", "N/A", "N/A", "N/A","N/A"});
        }else{
            for(Sala sala: listaSalasCadastradas){
            modelo.addRow(new Object []{sala.getCodigoSala(),sala.getNumero(),sala.getBloco(), sala.getCentro(), sala.getCampus()});
            
        }
        
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
                        updateTable(matriculaPessoa);
                        break;
                    case ("removerAcesso"):
                        removerAcesso();
                        updateTable(matriculaPessoa);
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
