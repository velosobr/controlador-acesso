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
public class Aluno extends Pessoa{
    private String curso;
    private boolean administrador = false;
    public Aluno(int matricula, String nome, long telefone, String email, String curso) {
        super(matricula, nome, telefone, email);
        setCurso(curso);
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        
        if(curso!=null){
            this.curso = curso;
        }
    }
    
    
    
}
