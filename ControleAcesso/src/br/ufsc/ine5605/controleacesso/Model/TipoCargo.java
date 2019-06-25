/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Model;

/**
 *
 * @author caiocaio
 */
public enum TipoCargo {
    PROFESSOR("Professor"),
    
    TECNICOADM("Tecnico administrativo"),
    
    TECNICOLABORATORIO("Tecnico laboratorio");
    
    private String descricao;
    
    private TipoCargo (String descricao){
        this.descricao = descricao;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
    
}
