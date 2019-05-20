/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.validadores;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Linnety3
 */
public class ValidaERetorna {

    private final Scanner teclado;

    public ValidaERetorna(Scanner teclado) {
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

    public String recebeValorString(String mensagem) {
        String valor = null;
        do {
            System.out.println(mensagem);

            valor = recebeValorString();
        } while (valor == null);

        return valor;
    }

    public int recebeValorInteiro() {
        int valor = -1;

        try {
            valor = teclado.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("O valor digitado nao e um inteiro valido");
        } finally {
            teclado.nextLine();
        }

        return valor;
    }

    public int recebeValorInteiro(String mensagem) {
        int valor = -1;

        do {
            System.out.println(mensagem);

            valor = recebeValorInteiro();

        } while (valor == -1);

        return valor;
    }

    public Boolean recebeValorBoolean(String mensagem) {
        int valor = -1;
        boolean valorBool = false;

        System.out.println(mensagem);
        System.out.println("1 - SIM");
        System.out.println("2 - NAO");
        valor = recebeValorInteiro();

        try {
            switch (valor) {
                case (1):
                    valorBool = true;
                    break;
                case (2):
                    valorBool = false;
                    break;
                default:
                    throw new IllegalArgumentException("Voce deve digitar opcoes validas. Selecione um tipo dentre os tipos listados.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.recebeValorBoolean("Tente novamente");
        }

        return valorBool;
    }

    public char recebeValorChar() {
        char valor = 000;
        try {
            valor = teclado.next().charAt(0);
        } catch (InputMismatchException e) {
            System.out.println("O valor digitado nao e um inteiro valido");
        } finally {
            teclado.nextLine();
        }

        return valor;
    }

    public char recebeValorChar(String mensagem) {
        char valor = 000;

        do {
            System.out.println(mensagem);

            valor = recebeValorChar();

        } while (valor == 000);

        return valor;
    }

    public long recebeValorLong() {
        long valor = -1;

        try {
            valor = teclado.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("O valor digitado nao e um long valido");
        } finally {
            teclado.nextLine();
        }

        return valor;
    }

    public long recebeValorLong(String mensagem) {
        long valor = -1;

        do {
            System.out.println(mensagem);

            valor = recebeValorLong();

        } while (valor == -1);

        return valor;
    }
}
