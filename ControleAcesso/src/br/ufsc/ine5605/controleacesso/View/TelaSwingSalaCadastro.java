/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlSala;
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
import javax.swing.JTextField;

/**
 *
 * @author Linnety3
 */
public class TelaSwingSalaCadastro extends JFrame {

    private JLabel codigoSala;
    private JLabel numero;
    private JLabel bloco;
    private JLabel centro;
    private JLabel campus;

    private JTextField codigoTextField;
    private JTextField numeroTextField;
    private JTextField blocoTextField;
    private JTextField centroTextField;
    private JTextField campusTextField;

    private JButton confirmar;
    private JButton cancelar;

    public TelaSwingSalaCadastro() {
        super("Opções de cadastro de pessoa");

        getContentPane().add(telaCadastroSala());
    }

    private JPanel telaCadastroSala() {
        JPanel panelSala = new JPanel();
        panelSala.setBackground(Color.WHITE);
        setSize(400, 300);

        panelSala.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Cadastro Sala"));

        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        numero = new JLabel("Numero");
        codigoSala = new JLabel("Código");
        bloco = new JLabel("Bloco");
        centro = new JLabel("Centro");
        campus = new JLabel("Campus");

        numeroTextField = new JTextField("");
        codigoTextField = new JTextField("");
        blocoTextField = new JTextField("");
        centroTextField = new JTextField("");
        campusTextField = new JTextField("");

        panelSala.setLayout(new GridBagLayout());
        panelSala.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 2, 2);
        gbc.anchor = GridBagConstraints.NORTHEAST;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelSala.add(codigoSala, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelSala.add(codigoTextField, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        panelSala.add(numero, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;

        panelSala.add(numeroTextField, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelSala.add(bloco, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelSala.add(blocoTextField, gbc);

//centro
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelSala.add(centro, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelSala.add(centroTextField, gbc);
//campus

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelSala.add(campus, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelSala.add(campusTextField, gbc);
        //confirmar e cancelar
        confirmar = new JButton("Confirmar");
        confirmar.setActionCommand("confirmarSala");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelSala.add(confirmar, gbc);

        cancelar = new JButton("Cancelar");
        cancelar.setActionCommand("cancelar");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelSala.add(cancelar, gbc);

        GerenciadorBotoes btManager = new GerenciadorBotoes();
        confirmar.addActionListener(btManager);
        cancelar.addActionListener(btManager);

        return panelSala;
    }

    private class GerenciadorBotoes implements ActionListener {

        TelaSwingSala telaSala;

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                switch (e.getActionCommand()) {
                    case ("confirmaSala"):
                        cadastroSala();
                        break;

                    case ("cancelar"):
                        setVisible(false);
                        CtrlSala.getInstancia().abreTelaSwingSala();

                        break;

                }
            } catch (Exception ex) {
                TelaSwingSala.getInstancia().setVisible(true);
            }
        }
    }

    private void cadastroSala() throws Exception {
        try {
            String codigoParaCadastrar = codigoTextField.getText();
            int numerParaCadastrar = Integer.valueOf(numeroTextField.getText());
            char blocoParaCadastrar = blocoTextField.getText().charAt(0);
            String centroParaCadastrar = centroTextField.getText();
            String campusParaCadastrar = campusTextField.getText();

            CtrlSala.getInstancia().addSala(codigoParaCadastrar, numerParaCadastrar, blocoParaCadastrar, centroParaCadastrar, campusParaCadastrar);
            CtrlSala.getInstancia().abreTelaSwingSala();
            TelaSwingSala.getInstancia().updateTable();
            setVisible(false);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Digite um numero inteiro para o númeroda sala");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}
