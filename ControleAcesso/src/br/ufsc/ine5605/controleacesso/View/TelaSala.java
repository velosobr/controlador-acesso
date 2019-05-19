/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlSala;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.util.Scanner;

/**
 *
 * @author Lino Veloso
 */
public class TelaSala {

    private final CtrlSala ctrlSala;
    private final Scanner teclado;
    private final ValidaERetorna validador;

    public TelaSala(CtrlSala ctrlSala) {
        this.ctrlSala = ctrlSala;
        teclado = new Scanner(System.in);
        this.validador = new ValidaERetorna(teclado);
    }

    public CtrlSala getCtrlAcesso() {
        return ctrlSala;
    }

    public void inicio() {
        System.out.println("---TELA DE GERENCIAMENTO DE SALAS---");
        System.out.println(" ");
        try {

            System.out.println("1 ");
            System.out.println("2 ");
            System.out.println("9 - Sair");

            int opcao = validador.recebeValorInteiro("--- Escolha uma das opcoes acima e tecle enter. ---");
            switch (opcao) {
                case (1):

                    break;

                case (2):

                    break;
                case (9):
                    System.exit(0);

                default:
                    throw new IllegalArgumentException("Opção inválida! Escolha uma opção dentre as opções da lista.");

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            String[] args = null;
            this.inicio();

        } finally {
            teclado.nextLine();
        }
    }
}
