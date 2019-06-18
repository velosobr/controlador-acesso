/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Lino Veloso
 */
public class TelaSwingAdm extends JFrame {
    
    private static TelaSwingAdm instancia;

    // configura a GUI
    public TelaSwingAdm() {
        super("Tela ADM");

        //create a new panel with GBL manager
        JPanel panelADM = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsPanel = new GridBagConstraints(); // instancia restrições

        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(panelADM);

        // cria componentes GUI
        GridBagConstraints constraints = new GridBagConstraints();
        JTextArea textArea1 = new JTextArea("TextArea1", 5, 10);
        
        JTextField textField1 = new JTextField("text1", 5);
        JTextField textField2 = new JTextField("text2", 5);
        JTextField textField3 = new JTextField("text3", 5);
        
        JButton btn1 = new JButton("Button 1");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panelADM.add(btn1, constraints);
        
        JButton btn2 = new JButton("Button 2");
        
        JButton btn3 = new JButton("Button 3");
        
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        //  botaoUm.addActionListener(btManager);

        setLocationRelativeTo(null);
        setSize(315, 210);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static TelaSwingAdm GetInstacia() {
        if (instancia == null) {
            instancia = new TelaSwingAdm();
        }
        return instancia;
    }
    
    public CtrlPrincipal getCtrlPrincipal() {
        return CtrlPrincipal.getInstancia();
    }

    /**
     *
     * @param component - componete
     * @param row - linha
     * @param column - coluna
     * @param width - largura
     * @param height - altura
     */
    /**
     * public void inicio() throws IllegalArgumentException,
     * InputMismatchException {
     *
     * System.out.println("--- TELA ADM - GERENCIAL ---"); System.out.println("
     * "); System.out.println("1 - Cadastro de pessoas"); System.out.println("2
     * - Cadastro de salas"); System.out.println("3 - Listar acessos");
     * System.out.println("4 - Voltar ao menu principal");
     * System.out.println("");
     *
     * try { int opcao = validador.recebeValorInteiro("Escolha a opcao desejada,
     * insira o numero e tecle enter: ---");
     *
     * switch (opcao) { case (1):
     * CtrlPrincipal.getInstancia().getCtrlPessoa().getTelaPessoa().inicio();
     * break; case (2):
     * CtrlPrincipal.getInstancia().getCtrlSala().getTelaSala().inicio(); break;
     * case (3):
     * CtrlPrincipal.getInstancia().getCtrlAcesso().getTelaAcesso().inicio();
     * break; case (4): this.getCtrlPrincipal().abreTelaInicial(); break;
     * default: throw new IllegalArgumentException(); } } catch
     * (IllegalArgumentException e) { System.out.println("Opcao Invalida!
     * Escolha uma opcao dentre das opcoes na lista."); String[] args = null;
     * this.inicio(); } finally { teclado.nextLine(); }
     */
    private class GerenciadorBotoes implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            if (ae.getActionCommand() == "1") {
                TelaSwingAdm.GetInstacia().setVisible(false);
                TelaPessoa.getInstancia().setVisible(true);
            }else if(ae.getActionCommand() == "2"){
                TelaSwingAdm.GetInstacia().setVisible(false);
                TelaSala.getInstancia().setVisible(true);
            }
        }
        
    }
    
}
