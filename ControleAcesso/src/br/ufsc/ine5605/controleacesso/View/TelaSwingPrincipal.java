/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import java.awt.Container;
import java.awt.FlowLayout;
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

    private JLabel label;
    private JButton botaoUm;
    private JButton botaoDois;

    public TelaSwingPrincipal() {
        super("Controlador de acesso");

        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        label = new JLabel();
        botaoUm = new JButton();
        botaoDois = new JButton();

        label.setText("Primeiro JLabel!!!");
        botaoUm.setText("Botão Um");
        botaoUm.setActionCommand("1");
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

    public static TelaSwingPrincipal getInstancia(){
        if (instancia == null)
            instancia = new TelaSwingPrincipal();
        return instancia;
        
    }
    
    
    
    
    
    private class GerenciadorBotoes implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showMessageDialog(null, "Botão pressionado"
                + ae.getActionCommand(), "Titulo", 2);

    }

}
}
