/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Model;


import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Linnety3
 */
public class Acesso {
    private Integer id;
    private Pessoa pessoa;
    private Sala sala;
    private Date data;
    private String situacao;
    Random random = new Random();
    
    public Acesso(Pessoa pessoa, Sala sala, String situacao){
        setPessoa(pessoa);
        setSala(sala);
        this.data =Calendar.getInstance().getTime();
        this.situacao = situacao;
        id = random.nextInt(100000);
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        if(pessoa!=null){
            this.pessoa = pessoa;
        }
    }

    /**
     * @return the sala
     */
    public Sala getSala() {
        return sala;
    }

    /**
     * @param sala the sala to set
     */
    public void setSala(Sala sala) {
        if(sala!=null){
            this.sala = sala;
        }
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    
    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
}
