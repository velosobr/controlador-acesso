/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Model;

import java.util.ArrayList;

/**
 *
 * @author Linnety3
 */
public class Servidor extends Pessoa {

    private String cargo;

    public Servidor(int matricula, String nome, int telefone, String email, String cargo, boolean administrador) {
        super(matricula, nome, telefone, email, administrador);
        this.cargo = cargo;

    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
