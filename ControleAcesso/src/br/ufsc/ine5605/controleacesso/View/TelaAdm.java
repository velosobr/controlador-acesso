/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sun.security.jca.GetInstance;

/**
 *
 * @author Lino Veloso
 */
public class TelaAdm extends JFrame {

    private static TelaAdm instancia;
    private JLabel label;
    private JButton botaoUm;
    private JButton botaoOpcoesPessoa;

    public TelaAdm() {
        super("Controlador de acesso");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        label = new JLabel();
        botaoUm = new JButton();
        botaoOpcoesPessoa = new JButton();
        
        label.setText("TELA ADM");
        botaoUm.setText("teste de botão");
        botaoUm.setActionCommand("1");
        
        botaoOpcoesPessoa.setText("Opcoes Pessoa");
        botaoOpcoesPessoa.setActionCommand("opcoesPessoa");
        
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        botaoUm.addActionListener(btManager);
        botaoOpcoesPessoa.addActionListener(btManager);

        container.add(label);
        container.add(botaoUm);
        container.add(botaoOpcoesPessoa);

        setSize(360, 250);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static TelaAdm GetInstacia() {
        if (instancia == null) {
            instancia = new TelaAdm();
        }
        return instancia;
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return CtrlPrincipal.getInstancia();
    }

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
            if("opcoesPessoa".equals(ae.getActionCommand())){
                TelaAdm.GetInstacia().setVisible(false);
                TelaSwingPessoa.getInstancia().setVisible(true);
                
            }
            
            
            JOptionPane.showMessageDialog(null, "Botão pressionado: "
                    + ae.getActionCommand(), "Titulo", 1);

        }

    }

}
