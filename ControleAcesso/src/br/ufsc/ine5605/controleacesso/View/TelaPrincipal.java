/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
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

        validacaoInicial();

    }

    private void validacaoInicial() {
        System.out.println("--- Bem vindo ao sistema! ---");

        System.out.println("Matricula: ");
        int matricula = teclado.nextInt();

        System.out.println("Codigo Sala: ");
        String codigoSala = teclado.nextLine();

        verificaSeEhAdm(matricula, codigoSala);
    }

    private boolean verificaSeEhAdm(int matricula, String codigoSala) {
        Pessoa pessoaIn = ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
    }

}
