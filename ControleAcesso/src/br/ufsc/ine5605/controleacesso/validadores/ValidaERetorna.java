/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.validadores;

import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Linnety3
 */
public class ValidaERetorna {

    public ValidaERetorna(Scanner teclado) {
    }

    public ValidaERetorna() {

    }

    public String recebeValorString(String mensagem) {
        String valor = null;
        do {
            valor = JOptionPane.showInputDialog(mensagem);
        } while (valor == null);

        return valor;
    }

    public int validaValorInteiro(String mensagem) {
        int valor = -1;

        try {
            valor = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O valor digitado nao e um inteiro valido");
        }

        return valor;
    }

    public int recebeValorInteiro(String mensagem) {
        int valor = -1;

        do {
            valor = validaValorInteiro(mensagem);
        } while (valor == -1);

        return valor;
    }

    // Arrumar o método recebevalorBoolean
    public Boolean recebeValorBoolean(String mensagem) {
        int valor = -1;
        boolean valorBool = false;

        System.out.println(mensagem);
        System.out.println("1 - SIM");
        System.out.println("2 - NAO");
        valor = validaValorInteiro(mensagem);

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

    public char validaValorChar(String mensagem) {
        char valor = 000;
        try {
          
            valor = JOptionPane.showInputDialog(mensagem).charAt(0);
        } catch (InputMismatchException e) {
            System.out.println("O valor digitado nao e um inteiro valido");
        } 

        return valor;
    }

    public char recebeValorChar(String mensagem) {
        char valor = 000;

        do {
            System.out.println(mensagem);

            valor = validaValorChar(mensagem);

        } while (valor == 000);

        return valor;
    }

//    public long recebeValorLong() {
//        long valor = -1;
//
//        try {
//            valor = Long.parseLong(JOptionPane.showInputDialog(mensagem));;
//        } catch (InputMismatchException e) {
//            System.out.println("O valor digitado nao e um long valido");
//        } 
//
//        return valor;
//    }

    public long recebeValorLong(String mensagem) {
        long valor = -1;

        do {
            valor = Long.parseLong(JOptionPane.showInputDialog(mensagem));

        } while (valor == -1);

        return valor;
    }
}
