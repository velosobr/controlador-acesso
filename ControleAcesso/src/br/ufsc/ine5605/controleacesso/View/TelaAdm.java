/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lino Veloso
 */
public class TelaAdm {

    private final CtrlPrincipal ctrlPrincipal;

    private final Scanner teclado;

    public TelaAdm(CtrlPrincipal ctrlPrincipal) {
        this.ctrlPrincipal = ctrlPrincipal;
        teclado = new Scanner(System.in);
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return ctrlPrincipal;
    }

    public void inicio() throws IllegalArgumentException, InputMismatchException {

        System.out.println("--- TELA ADM - GERENCIAL ---");
        System.out.println("Escolha a opção desejada, insira o número e tecle enter: ---");
        System.out.println("1 - Cadastro de pessoas");
        System.out.println("2 - Cadastro de Salas");
        System.out.println("3 - Gerenciamento de Acessos");
        System.out.println("4 - Voltar ao Menu Principal");
        System.out.println("");

        try {
            int opcao = teclado.nextInt();

            teclado.nextLine();
            switch (opcao) {
                case (1):
                    ctrlPrincipal.getCtrlPessoa().getTelaPessoa().inicio();
                    break;
                case (2):
                   ctrlPrincipal.getCtrlSala().getTelaSala().inicio();
                    break;
                case (3):
                    ctrlPrincipal.getCtrlAcesso().getTelaAcesso().inicio();
                    break;
                case (4):
                    this.getCtrlPrincipal().abreTelaInicial();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Opção Inválida! Escolha uma opção dentre das opções na lista.");
            String[] args = null;
            this.inicio();
        }

    }

}
