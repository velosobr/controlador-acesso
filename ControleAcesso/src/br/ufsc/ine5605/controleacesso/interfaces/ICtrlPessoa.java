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
     *
     */
    public boolean incluiAluno(int matricula, String nome, long telefone, String email, String curso);

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
     * @param listaSalas - Lista de salas (salas cadastradas na tela de salas) o
     * aluno tem acesso,.
     */
    public boolean incluiServidor(int matricula, String nome, long telefone, String email, String cargo, boolean administrador);

    /**
     * É utilizado pela TelaPessoa para deletar uma pessoa na lista de pessoas.
     *
     * @param matricula - Qual matricula o aluno possui.
     * @return
     */
    public boolean delPessoa(int matricula);

    public boolean alteradorDeCadastroAluno(int matricula, String nome, long telefone, String email, String curso);

    public boolean alteradorDeCadastroServidor(int matricula, String nome, long telefone, String email, String cargo, boolean administrador);

    /**
     * É utilizado pela TelaPessoa para incluir uma sala cadastrada na lista de
     * salas da pessoa.
     *
     * @param matricula
     * @param codigoSala - código na listaSalas da pessoa a sala encontrada com
     * esse codigo sala.
     * @return
     */
    public boolean cadastraSalaNaPessoa(int matricula, String codigoSala);

    /**
     *
     * @param matricula
     * @param codigoSala - é utilizado para encontrara a sala na lista de salas
     * do aluno e deleta a sala da lista.
     * @return
     */
    public void delSalaNaPessoa(int matricula, String codigoSala);

    

    
}
