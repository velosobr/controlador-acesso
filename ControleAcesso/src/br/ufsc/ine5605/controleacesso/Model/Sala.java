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
public class Sala {
    private ArrayList <Pessoa> pessoasCadastradas;
    private String codigoSala;
    private int numero;
    private char bloco;
    private String centro;
    private String campus;
    
    public Sala(String codigoSala, int numero, char bloco, String centro, String campus){
        this.pessoasCadastradas = new ArrayList<>();
        this.codigoSala = codigoSala;
        this.numero = numero;
        this.bloco = bloco;
        this.centro = centro;
        this.campus = campus;
    }

    /**
     * @return the codigoSala
     */
    public String getCodigoSala() {
        return codigoSala;
    }

    /**
     * @param codigoSala the codigoSala to set
     */
    public void setCodigoSala(String codigoSala) {
        this.codigoSala = codigoSala;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the bloco
     */
    public char getBloco() {
        return bloco;
    }

    /**
     * @param bloco the bloco to set
     */
    public void setBloco(char bloco) {
        this.bloco = bloco;
    }

    /**
     * @return the centro
     */
    public String getCentro() {
        return centro;
    }

    /**
     * @param centro the centro to set
     */
    public void setCentro(String centro) {
        this.centro = centro;
    }

    /**
     * @return the campus
     */
    public String getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(String campus) {
        this.campus = campus;
    }
    
    public void addPessoa(Pessoa pessoa){
        pessoasCadastradas.add(pessoa);
    }
    
    public void delPessoa(Pessoa pessoa){
        pessoasCadastradas.remove(pessoa);
    }
}
