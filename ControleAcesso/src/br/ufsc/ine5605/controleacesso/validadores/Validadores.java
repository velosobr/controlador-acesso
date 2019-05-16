/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.validadores;

import br.ufsc.ine5605.controleacesso.Exceptions.valorStringException;
import br.ufsc.ine5605.controleacesso.Exceptions.numeroInteiroException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Linnety3
 */
public class Validadores {

    private final Scanner teclado;

    public Validadores(Scanner teclado) {
        this.teclado = new Scanner(System.in);
    }

    public String recebeValorString() {
        String valor = null;
        try {
            valor = teclado.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return valor;
    }

    public int recebeValorInteiro() {
        int valor = 0;

        try {
            valor = teclado.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Valor invalido, você deve digitar um valor inteiro");
        }

        return valor;
    }
}
