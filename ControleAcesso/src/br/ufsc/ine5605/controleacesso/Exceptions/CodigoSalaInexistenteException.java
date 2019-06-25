/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Exceptions;

/**
 *
 * @author caiocaio
 */
public class CodigoSalaInexistenteException extends RuntimeException{
    public CodigoSalaInexistenteException(){
        this("Codigo de sala nao cadastrado!");
    }
    public CodigoSalaInexistenteException(String message){
        super(message);
    }
}
