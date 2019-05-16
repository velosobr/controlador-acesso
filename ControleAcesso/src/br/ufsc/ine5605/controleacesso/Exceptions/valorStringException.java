/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Exceptions;

/**
 *
 * @author Linnety3
 */
public class valorStringException extends Exception {

    public valorStringException() {
        this("Valor inválido, você deve digitar um texto");
    }

    public valorStringException(String message) {
        super(message);
    }

}
