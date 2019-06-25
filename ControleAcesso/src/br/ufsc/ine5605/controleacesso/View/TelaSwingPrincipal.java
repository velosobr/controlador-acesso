/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Exceptions.CodigoSalaInexistenteException;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.awt.Color;
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

/**
 *
 * @author Linnety3
 */
public class TelaSwingPrincipal extends JFrame {

    private static TelaSwingPrincipal instancia;
    private JLabel label;
    private JButton botaoUm;
    private JButton botaoDois;
    private ValidaERetorna validador;

    public TelaSwingPrincipal() {

        super("Controlador de acesso");
        validador = new ValidaERetorna();

        // create a new panel with GridBagLayout manager
        JPanel panelPrincipal = new JPanel(new GridBagLayout());

        GridBagConstraints constraintsPanel = new GridBagConstraints();
        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(10, 10, 10, 10);
        panelPrincipal.setBackground(Color.white);

        panelPrincipal.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Controle de acesso"));

        //tamanho da tela
        setSize(315, 280);

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
        constraints.gridy = 0;
        constraints.ipadx = 52;
        constraints.ipady = 20;

        panelPrincipal.add(botaoUm, constraints);
        botaoUm.setActionCommand("1");

        //Abrir Tela Admm
        //botão 2
        botaoDois = new JButton("Abrir Tela ADM");

        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.ipadx = 30;
        constraints.ipady = 20;
        panelPrincipal.add(botaoDois, constraints);
        botaoDois.setActionCommand("2");

        GerenciadorBotoes btManager = new GerenciadorBotoes();

        botaoUm.addActionListener(btManager);

        botaoDois.addActionListener(btManager);

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
            if (ae.getActionCommand().equals("1")) {
                int matricula = validador.recebeValorInteiro("Digite a matricula: ");
                String codSala = validador.recebeValorString("Digite a sala que deseja entrar: ");
                try {
                    if (!codSala.equals("")) {
                        if (validacaoPorta(matricula, codSala)) {
                            JOptionPane.showMessageDialog(null, "Porta aberta");
                        } else {
                            JOptionPane.showMessageDialog(null, "Você não possui acesso");
                        }
                    }
                } catch (CodigoSalaInexistenteException e) {
                    System.out.println("Foi enviardo para o gerenciador de botoes um valor null para o codigo sala"); // just for log on console
                }

            }

            if (ae.getActionCommand().equals("2")) {
                int matricula = validador.recebeValorInteiro("Digite a matricula: ");
                try {
                    if (matricula != -1) {
                        if (validacaoTelaAdm(matricula)) {
                            setVisible(false);
                            TelaSwingAdm.GetInstacia().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Você não possui acesso");
                        }
                    }

                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Matricula inexistente. Tente novamente.");
                }

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
