/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Model;

import java.util.ArrayList;

/**
 *
 * @author Caio Noguerol
 */
public class Servidor extends Pessoa {

    private String cargo;
    private boolean administrador;

    public Servidor(int matricula, String nome, long telefone, String email, String cargo, boolean administrador) {
        super(matricula, nome, telefone, email);
        setCargo(cargo);
        this.administrador = administrador;
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
        if(cargo!=null){
            this.cargo = cargo;
        }
    }

    /**
     * @return the administrador
     */
    public boolean isAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

}
