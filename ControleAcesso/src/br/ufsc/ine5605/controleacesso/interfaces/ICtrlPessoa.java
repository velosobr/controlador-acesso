/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.interfaces;

import br.ufsc.ine5605.controleacesso.Exceptions.MatriculaInexisteException;
import br.ufsc.ine5605.controleacesso.Exceptions.MatriculaJahExisteException;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import java.util.ArrayList;

/**
 *
 * @author Lino Veloso
 */
public interface ICtrlPessoa {

    /**
     * É utilizado pela TelaPessoa para incluir um novo Aluno na lista de
     * pessoas.
     *
     * @param curso - Qual curso o aluno está cursando na universidade.
     * @param matricula - Qual matricula o aluno possui.
     * @param telefone - Qual telefone do aluno.
     * @param nome - Nome do aluno a ser cadastrado.
     * @param email - email do aluno.
     * @return 
     * @throws br.ufsc.ine5605.controleacesso.Exceptions.MatriculaJahExisteException
     *
     */
    public boolean incluiAluno(int matricula, String nome, long telefone, String email, String curso) throws Exception ;

    /**
     * É utilizado pela TelaPessoa para incluir um novo Servidor na lista de
     * pessoas.
     *
     * @param administrador - Valor booleano se o servidor é administrador do
     * sistema ou não.
     * @param cargo - Qual cargo do servidor.
     * @param matricula - Qual matricula o aluno possui.
     * @param telefone - Qual telefone do aluno.
     * @param nome - Nome do aluno a ser cadastrado.
     * @param email - email do aluno.
     * @return 
     * @throws br.ufsc.ine5605.controleacesso.Exceptions.MatriculaJahExisteException
     */
    public boolean incluiServidor(int matricula, String nome, long telefone, String email, String cargo, boolean administrador) throws Exception;

    /**
     * É utilizado pela TelaPessoa para deletar uma pessoa na lista de pessoas.
     *
     * @param matricula - Qual matricula o aluno possui.
     * @return
     * @throws br.ufsc.ine5605.controleacesso.Exceptions.MatriculaInexisteException
     */
    public boolean delPessoa(int matricula) throws MatriculaInexisteException;

    public boolean alteradorDeCadastroAluno(int matricula, String nome, long telefone, String email, String curso)throws Exception;

    public boolean alteradorDeCadastroServidor(int matricula, String nome, long telefone, String email, String cargo, boolean administrador)throws Exception;

    /**
     * É utilizado pela TelaPessoa para incluir uma sala cadastrada na lista de
     * salas da pessoa.
     *
     * @param matricula
     * @param codigoSala - código na listaSalas da pessoa a sala encontrada com
     * esse codigo sala.
     * @return
     * @throws java.lang.Exception
     */
    public boolean cadastraSalaNaPessoa(int matricula, String codigoSala) throws Exception;

    /**
     *
     * @param matricula
     * @param codigoSala - é utilizado para encontrara a sala na lista de salas
     * do aluno e deleta a sala da lista.
     * @throws java.lang.Exception
     */
    public void delSalaNaPessoa(int matricula, String codigoSala) throws Exception;

    

    
}
