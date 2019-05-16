/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPessoa;
import java.util.Scanner;

/**
 *
 * @author Lino Veloso
 */
public class TelaPessoa {

    private final CtrlPessoa ctrlPessoa;

    private final Scanner teclado;

    public TelaPessoa(CtrlPessoa ctrlPessoa) {
        this.ctrlPessoa = ctrlPessoa;
        teclado = new Scanner(System.in);
    }

    public CtrlPessoa getCtrlPessoa() {
        return ctrlPessoa;
    }

    public void inicio() {
        System.out.println("---TELA DE PESSOAS---");
    }

}
