/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Linnety3
 */
public class TelaSwingPrincipal extends JFrame {

    private static TelaSwingPrincipal instancia;
    private JLabel label;
    private JButton botaoUm;
    private JButton botaoDois;

    public TelaSwingPrincipal() {

        super("Controlador de acesso");

        // create a new panel with GridBagLayout manager
        JPanel panelPrincipal = new JPanel(new GridBagLayout());

        GridBagConstraints constraintsPanel = new GridBagConstraints();
        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(10, 10, 10, 10);
        panelPrincipal.setBackground(Color.white);

        panelPrincipal.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Controle de acesso"));

        setSize(315, 210);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelPrincipal);

        GridBagConstraints constraints = new GridBagConstraints(); // instancia restrições

        //Abrir porta 
        // botão 1
        botaoUm = new JButton();
        botaoUm.setText("Abrir porta");

        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipadx = 52;
        constraints.ipady = 20;

        panelPrincipal.add(botaoUm, constraints);
        botaoUm.setActionCommand("1");

        //Abrir Tela Admm
        //botão 2
        botaoDois = new JButton();
        botaoDois.setText("Abrir Tela ADM");

        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.ipadx = 30;
        constraints.ipady = 20;
        panelPrincipal.add(botaoDois, constraints);
        botaoDois.setActionCommand("2");

        GerenciadorBotoes btManager = new GerenciadorBotoes();

        botaoUm.addActionListener(btManager);

        botaoDois.addActionListener(btManager);

        setLocationRelativeTo(null);

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

        if (PessoaDAO.getInstancia().getPessoa(matricula) instanceof Servidor) {
            Servidor servidor = (Servidor) PessoaDAO.getInstancia().getPessoa(matricula);
            ehAdm = servidor.isAdministrador();
        } else {
            throw new IllegalArgumentException("A matriculada digitada nao pertence a um servidor, somente servidores podem acessar a tela gerencial");
        }
        return ehAdm;
    }
}
