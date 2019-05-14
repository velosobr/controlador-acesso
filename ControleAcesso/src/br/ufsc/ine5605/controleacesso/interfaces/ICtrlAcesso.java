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
     * Um dos principais métodos do sistema, onde ele vai verificar se o usuário tem acesso liberado a sala ou não.
     * @param matricula - matricula do aluno a ser verificado.
     * @param codigoSala - codigo da sala que vai verificar o acesso do aluno.
     * @return deve retornar um boolean.
     */
    public boolean ehLiberadoAcesso(int matricula, String codigoSala);

    /**
     * 
     */
    public void listaAcesso();

    /**
     * método para gerar um log pela matricula
     * @param matricula
     * @return 
     */
    public String geraLogByMatricula(int matricula);

    public String geraLogByCodigoSala(String codigoSala);
    
    public Acesso findAcessoByMatricula(int matricula);
    
    public Acesso findAcessoByCodigoSala(String codigoSala);
    
    
}
