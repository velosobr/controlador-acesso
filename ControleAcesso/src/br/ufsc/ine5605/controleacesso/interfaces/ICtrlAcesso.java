/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.interfaces;

import br.ufsc.ine5605.controleacesso.Exceptions.CodigoSalaInexistenteException;
import br.ufsc.ine5605.controleacesso.Exceptions.MatriculaInexisteException;
import br.ufsc.ine5605.controleacesso.Model.Acesso;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import java.util.ArrayList;

/**
 *
 * @author Lino Veloso
 */
public interface ICtrlAcesso {

    /**
     * Um dos principais métodos do sistema, onde ele vai verificar se o usuário tem acesso liberado a sala ou não.
     * @param matricula - matricula do aluno a ser verificado.
     * @param codigoSala - codigo da sala que vai verificar o acesso do aluno.
     * @return deve retornar um boolean.
     */
    public boolean ehLiberadoAcesso(int matricula, String codigoSala);
    
    
    
    
    
   // public void addAcesso(Pessoa pessoa, Sala sala, String situacaoDoTeste);
  
   
    /**
     * método para gerar um log pela matricula
     * @param matricula
     * @return 
     */
    public ArrayList <Acesso> geraListaByMatricula(int matricula) throws Exception;

    public ArrayList <Acesso> geraListaByCodigoSala(String codigoSala)throws Exception;
    
    public Acesso findAcessoByMatricula(int matricula);
    

    
    
}
