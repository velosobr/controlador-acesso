/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.interfaces;

import br.ufsc.ine5605.controleacesso.Model.Sala;
import java.util.ArrayList;

/**
 *
 * @author Linnety3
 */
public interface ICtrlSala {
    
    /**
     * método que adiciona sala.
     * @param codigoSala - código/id único da sala.
     * @param numero - número da sala.
     * @param bloco - bloco do prédio onde se encontra a sala.
     * @param centro - qual centro academico se encontra a sala.
     * @param campus - Em qual campus se encontra a sala.
     */
    public void addSala(String codigoSala, int numero, char bloco, String centro, String campus);
    
    /**
     * Este método é responsavel por excluir uma sala do cadastro. Seria interessante criar uma validação para que a exclusão só fosse possível se não houvesse nenhum aluno com a sala no cadastro deles.
     * @param codigoSala - atributo responsável por encontrar na lista de salas.
     */
    public void delSala(String codigoSala);
    
    /**
     * Método responsável por encontrar a sala através do código da sala passado por parâmetro.
     * @param codigoSala - código da sala.
     * @return - deve retornar um objeto do tipo sala.
     */
    public Sala findSalaByCodigoSala(String codigoSala);
    
    /**
     * Método responsável por cadastrar acesso das pessoas a sala.
     * @param matricula matricula da pessoa que quer ter acesso a sala.
     */
    public void cadastraAcessoPessoa(int matricula);
    
    /**
     * Deleta da pessoa o acesso à sala
     * @param matricula Atributo responsável por encontrar a pessoa.
     */
    public void deletaAcessoSalaPessoa(int matricula);
    
    /**
     * Lista as pessoas que possuem acesso à sala.
     * @param codigoSala código da sala para encontrar as pessoas cadastradas nela
     * @return - retorna os nomes das pessoas cadastradas.
     */
    public String listaPessoasCadastradas(String codigoSala);
    
    
    
    
}
