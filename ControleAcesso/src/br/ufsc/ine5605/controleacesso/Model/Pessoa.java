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
public abstract class Pessoa {

    private ArrayList<Sala> salasCadastradas;
    private int matricula;
    private String nome;
    private int telefone;
    private String email;

    public Pessoa(int matricula, String nome, int telefone, String email) {
        this.salasCadastradas = new ArrayList<>();
        this.matricula = matricula;
        setNome(nome);
        this.telefone = telefone;
        setEmail(email);
    }

    /**
     * @return the matricula
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;               
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        if(nome!=null){
            this.nome = nome;
        }
    }

    /**
     * @return the telefone
     */
    public int getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        if(email!=null){
            this.email = email;
        }
    }

    public void addSala(Sala sala) {
        if(sala!=null){
            salasCadastradas.add(sala);
        }
    }

    public void delSala(Sala sala) {
        if(sala!=null){
            salasCadastradas.remove(sala);
        }
    }

    public ArrayList<Sala> getSalasCadastradas() {
        return salasCadastradas;
    }

}
