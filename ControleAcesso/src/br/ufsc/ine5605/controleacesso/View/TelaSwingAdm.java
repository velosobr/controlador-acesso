package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
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
        panelADM.setBackground(Color.WHITE);
        setSize(315, 280);

        panelADM.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Tela ADM"));

        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelADM);

        // cria componentes 
        //Botão sala
        JButton btnSala = new JButton("Gerenciador de SALA");
        GridBagConstraints constraints = new GridBagConstraints();
        btnSala.setActionCommand("1");
        constraints.weightx = 0.5;
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.ipadx = 40;
        constraints.ipady = 20;

        panelADM.add(btnSala, constraints);

        //Botão Pessoas
        JButton btnPessoa = new JButton("Gerenciador de Pessoas");
        btnPessoa.setActionCommand("2");
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.ipadx = 20;
        constraints.ipady = 20;

        panelADM.add(btnPessoa, constraints);

        //Botão Acessos
        JButton btnAcesso = new JButton("Gerenciador de Acessos");
        btnAcesso.setActionCommand("3");
        constraints.insets = new Insets(10, 0, 0, 0);
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipadx = 20;
        constraints.ipady = 20;

        panelADM.add(btnAcesso, constraints);

        //Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setActionCommand("4");
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.ipadx = 125;
        constraints.ipady = 20;
        

        panelADM.add(btnVoltar, constraints);

        //gerenciador dos botões
        GerenciadorBotoes btManager = new GerenciadorBotoes();
        btnSala.addActionListener(btManager);
        btnPessoa.addActionListener(btManager);
        btnAcesso.addActionListener(btManager);
        btnVoltar.addActionListener(btManager);

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

//    public void inicio() throws IllegalArgumentException, InputMismatchException {
//        System.out.println("--- TELA ADM - GERENCIAL---");
//        System.out.println(" ");
//        System.out.println("1 - Cadastro de pessoas ");
//        System.out.println("2 - Cadastro de salas ");
//        System.out.println("3 - Listar acessos ");
//        System.out.println("4 - Voltar ao menu principal ");
//        System.out.println("");
//
//        try {
//            int opcao = validador.recebeValorInteiro("Escolha a opcao desejada, insira o numero e tecle enter : ---");
//
//            switch (opcao) {
//                case (1):
//                    CtrlPrincipal.getInstancia().getCtrlPessoa().getTelaPessoa().inicio();
//                    break;
//                case (2):
//                    CtrlPrincipal.getInstancia().getCtrlSala().getTelaSala().inicio();
//                    break;
//                case (3):
//                    CtrlPrincipal.getInstancia().getCtrlAcesso().getTelaAcesso().inicio();
//                    break;
//                case (4):
//                    this.getCtrlPrincipal().abreTelaInicial();
//                    break;
//                default:
//                    throw new IllegalArgumentException();
//            }
//        } catch (IllegalArgumentException e) {
//            System.out.println("Opcao Invalida! Escolha uma opcao dentre das opcoes na lista .");
//            String[] args = null;
//            this.inicio();
//        } finally {
//            teclado.nextLine();
//        }
//    }
    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            if (ae.getActionCommand() == "1") {
                setVisible(false);
                CtrlPrincipal.getInstancia().getCtrlSala().abreTelaSwingSala();
            } else if (ae.getActionCommand() == "2") {
                setVisible(false);
                CtrlPrincipal.getInstancia().getCtrlPessoa().abreTelaSwingPessoa();
            } else if (ae.getActionCommand() == "3") {
                setVisible(false);
                CtrlPrincipal.getInstancia().getCtrlAcesso().abreTelaSwingAcesso();
            } else if (ae.getActionCommand() == "4") {
                setVisible(false);
                CtrlPrincipal.getInstancia().abreTelaInicial();
            }
        }
    }

}
