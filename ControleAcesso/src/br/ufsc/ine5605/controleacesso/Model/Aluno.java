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
public class Aluno extends Pessoa{
    private String curso;
    public Aluno(int matricula, String nome, int telefone, String email, String curso, boolean administrador) {
        super(matricula, nome, telefone, email, administrador);
        this.curso = curso;
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
        this.curso = curso;
    }
    
    
    
}
