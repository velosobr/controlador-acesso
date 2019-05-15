/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.ControleAcesso;
import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Model.Aluno;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Linnety3
 */
public class TelaPrincipal {

    private final CtrlPrincipal ctrlPrincipal;

    private final Scanner teclado;

    public TelaPrincipal(CtrlPrincipal ctrlprincipal) {
        this.ctrlPrincipal = ctrlprincipal;
        this.teclado = new Scanner(System.in);
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return ctrlPrincipal;
    }

    public void inicio() {

        System.out.println("--- Bem vindo ao sistema! ---");

        int opcao = 0;
        try {
            System.out.println("--- Digite uma opção abaixo e tecle enter. ---");
            System.out.println("1 - Acessar a porta");
            System.out.println("2 - Acessar a tela gerencial");
            System.out.println("9 - Sair");

            try {
                opcao = teclado.nextInt();
            } catch (InputMismatchException e) {
                throw new IllegalArgumentException("Opção inválida! Escolha uma opção dentre as opções na lista.");
            }
            switch (opcao) {
                case (1):
                    System.out.println("Digite a sua matricula");
                    int matricula = recebeValorInteiro();
                    System.out.println("Digite o codigo da sala");
                    String codSala = recebeValorString();

                    try {
                        if (validacaoPorta(matricula, codSala)) {
                        System.out.println("Porta aberta com sucesso");
                    } else {
                        System.out.println("Você não possui acesso a esta porta, procure um administrador de sistema");
                    }
                    } catch (Exception e) {
                    }
                    
                    break;

                case (2):
                    System.out.println("Digite a sua matricula");
                    int matriculaadm = recebeValorInteiro();

                    if (validacaoTelaAdm(matriculaadm)) {
                        ctrlPrincipal.getTelaAdm().inicio();
                    }else{
                        System.out.println("Usuário não possui acesso a tela Adm");
                    }

                    break;
                case (9):
                    System.exit(0);

                default:
                    throw new IllegalArgumentException("Opção inválida! Escolha uma opção dentre as opções da lista.");

            }
        } catch (IllegalArgumentException e) {
            e.getMessage();
            String[] args = null;
            ControleAcesso.main(args);
        }
    }

    private boolean validacaoTelaAdm(int matricula) {
        boolean ehAdm = false;
        try {
            if (ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula) instanceof Servidor) {
                Servidor servidor = (Servidor) ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
                ehAdm = servidor.isAdministrador();
            } else {
                throw new IllegalArgumentException("A matriculada digitada não pertence a um servidor, somente servidores podem acessar a tela gerencial");

            }
        } catch (IllegalArgumentException e) {
            System.out.println("Exceção: " + e.getMessage());
        }

        return ehAdm;
    }

    private boolean validacaoPorta(int matricula, String codSala) {
        return true;
    }

    private int recebeValorInteiro() {
        int valor = 0;

        try {
            valor = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception e) {
            System.out.println("Valor invalido! Digite um numero inteiro");
        }
        return valor;
    }

    private String recebeValorString() {
        return teclado.nextLine();
    }

}
