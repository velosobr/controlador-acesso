/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
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
        System.out.println("Olá o programa está funcionando");
        System.out.println("Teste de commit para ver quantos commits cada contribuidor tem");
    }

}
