/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Linnety3
 */
public class TelaSwingPrincipal extends JFrame {

    private static TelaSwingPrincipal instancia;
    
    private final GridBagLayout layout; // layout desse frame
    private GridBagConstraints constraints;
    private JLabel label;
    private JButton botaoUm;
    private JButton botaoDois;

    public TelaSwingPrincipal() {
        super("Controlador de acesso");
        layout = new GridBagLayout();
        setLayout(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(315 ,210);
        
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        label = new JLabel();
        botaoUm = new JButton();
        botaoDois = new JButton();

        label.setText("Bem vindo ao sistema!!!");
        //Abrir porta
        botaoUm.setText("Abrir porta");
        botaoUm.setActionCommand("1");

        //Abrir Tela Admm
        botaoDois.setText("Botão Dois");
        botaoDois.setActionCommand("2");
        
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        botaoUm.addActionListener(btManager);
        botaoDois.addActionListener(btManager);

        container.add(label);
        container.add(botaoUm);
        container.add(botaoDois);

        setSize(360, 250);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static TelaSwingPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new TelaSwingPrincipal();
        }
        return instancia;

    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getActionCommand() == "1") {
                JOptionPane.showMessageDialog(null, "Porta aberta");
            }
            if (ae.getActionCommand() == "2") {
                TelaSwingPrincipal.getInstancia().setVisible(false);
                TelaSwingAdm.GetInstacia().setVisible(true);
            }
        }

    }

    private boolean validacaoPorta(int matricula, String codSala) {
        return CtrlPrincipal.getInstancia().getCtrlAcesso().ehLiberadoAcesso(matricula, codSala);
    }

    private boolean validacaoTelaAdm(int matricula) {
        boolean ehAdm = false;

        if (CtrlPrincipal.getInstancia().getCtrlPessoa().findPessoaByMatricula(matricula) instanceof Servidor) {
            Servidor servidor = (Servidor) CtrlPrincipal.getInstancia().getCtrlPessoa().findPessoaByMatricula(matricula);
            ehAdm = servidor.isAdministrador();
        } else {
            throw new IllegalArgumentException("A matriculada digitada nao pertence a um servidor, somente servidores podem acessar a tela gerencial");
        }
        return ehAdm;
    }
}
