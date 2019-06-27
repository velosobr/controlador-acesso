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

    public String recebeValorString(String mensagem) {
        String valor = "vazio";

        while (valor == "vazio") {
            try {
                String v = JOptionPane.showInputDialog(null, mensagem);
                if (v == null) {
                    System.out.println("Erro");
                    break;
                } else {

                    if (v.length() == 0) {
                        JOptionPane.showMessageDialog(null, "STRING: Você precisa digitar um valor válido, tente novamente");

                    } else {
                        valor = v;
                    }
                }

            } catch (Exception npe) {
                break;

            }
        }
        return valor;

    }

    public int recebeValorInteiro(String mensagem) {
        int valor = -1;

        while (valor == -1) {
            try {
                String o = JOptionPane.showInputDialog(null, mensagem);
                if (o == null) {
                    break;
                } else {
                    valor = Integer.parseInt(o);
                    break;
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "INT: Você precisa digitar um valor válido, tente novamente");
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Valor vazio");

            }
        }
        return valor;

    }

// Arrumar o método recebevalorBoolean
    public Boolean recebeValorBoolean() {
        int valor = -1;
        boolean valorBool = false;

        String[] opcoes = {"Sim", "Nao"};
        int teste = JOptionPane.showOptionDialog(null, "Possui acesso como administrador?", "Selecione", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);

        try {
            switch (teste) {
                case (0):
                    valorBool = true;
                    break;
                case (1):
                    valorBool = false;
                    break;
                default:
                    throw new IllegalArgumentException("Voce deve digitar opcoes validas. Selecione um tipo dentre os tipos listados.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("recebeValorBoolean: "+e.getMessage());
            //this.recebeValorBoolean("Tente novamente");
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
