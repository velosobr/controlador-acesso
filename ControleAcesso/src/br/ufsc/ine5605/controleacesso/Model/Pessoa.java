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
public abstract class Pessoa {
   private ArrayList <Sala> salasCadastradas;
   private int matricula;
   private String nome;
   private int telefone;
   private String email;
   
   
    public Pessoa(int matricula, String nome, int telefone, String email){
        this.salasCadastradas = new ArrayList<>();
        this.matricula = matricula;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
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
        this.nome = nome;
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
        this.email = email;
    }
    
    public void addSala(Sala sala){
        salasCadastradas.add(sala);
    }
    
    public void delSala(Sala sala){
        salasCadastradas.remove(sala);
    }

    public ArrayList<Sala> getSalasCadastradas() {
        return salasCadastradas;
    }
    
    
}
