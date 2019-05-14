/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.interfaces;

import br.ufsc.ine5605.controleacesso.Model.Acesso;

/**
 *
 * @author Linnety3
 */
public interface ICtrlAcesso {

    /**
     * Um dos principais métodos do sistema, onde ele vai verificar se o 
     * @param matricula - matricula do aluno a ser liberado
     * @param codigoSala - codigo da sala que vai receber o acesso do aluno
     * @return deve
     */
    public boolean ehLiberadoAcesso(int matricula, String codigoSala);

    public void listaAcesso();

    public String geraLogByMatricula(int matricula);

    public String geraLogByCodigoSala(String codigoSala);
    
    public Acesso findAcessoByMatricula(int matricula);
    
    public Acesso findAcessoByCodigoSala(String codigoSala);
    
    
}
