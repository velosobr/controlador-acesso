/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.interfaces;

import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import java.util.ArrayList;

/**
 *
 * @author Linnety3
 */
public interface ICtrlPessoa {
    
     /**
     * É utilizado pela TelaPessoa para incluir um novo Aluno na lista de pessoas.
     * @param curso - Qual curso o aluno está cursando na universidade.
     * @param matricula - Qual matricula o aluno possui.
     * @param telefone - Qual telefone do aluno.
     * @param nome - Nome do aluno a ser cadastrado.
     * @param email - email do aluno.
     * @param listaSalas - Lista de salas (salas cadastradas na tela de salas) o aluno tem acesso,.
     * @return novo - Novo Aluno quando o conteudo não é vazio.
     */    
    public Pessoa incluirAluno (String curso, int matricula, int telefone, String nome, String email, ArrayList<Sala> listaSalas);
    
    public Pessoa incluirServidor (boolean administrador, String cargo, int matricula, int telefone, String nome, String email, ArrayList<Sala> listaSalas);
    
    public void delPessoa(int matricula);
    
}
