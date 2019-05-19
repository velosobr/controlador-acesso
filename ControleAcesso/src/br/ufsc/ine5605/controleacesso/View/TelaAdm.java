/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Lino Veloso
 */
public class TelaAdm {

    private final CtrlPrincipal ctrlPrincipal;

    private final Scanner teclado;
    private final ValidaERetorna validador;

    public TelaAdm(CtrlPrincipal ctrlPrincipal) {
        this.ctrlPrincipal = ctrlPrincipal;
        teclado = new Scanner(System.in);
        this.validador = new ValidaERetorna(teclado);
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return ctrlPrincipal;
    }

    public void inicio() throws IllegalArgumentException, InputMismatchException {

        System.out.println("--- TELA ADM - GERENCIAL ---");
        System.out.println(" ");
        System.out.println("1 - Cadastro de pessoas");
        System.out.println("2 - Cadastro de Salas");
        System.out.println("3 - Gerenciamento de Acessos");
        System.out.println("4 - Voltar ao Menu Principal");
        System.out.println("");

        try {
            int opcao = validador.recebeValorInteiro("Escolha a opção desejada, insira o número e tecle enter: ---");

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
        } finally {
            teclado.nextLine();
        }

    }

}
