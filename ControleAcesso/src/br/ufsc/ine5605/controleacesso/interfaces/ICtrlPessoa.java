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
     * É utilizado pela TelaPessoa para incluir um novo
     * É utilizado pela TelaFuncionario para incluir um novo funcionário na lista de funcionários e gerar matrícula sequencial
     * para cada nova inclusão
     * @param conteudo - Conteúdo do tipo DadosFuncionario recebido na entrada do método cadastroFuncionario da TelaFuncionario
     * @return novo - Novo funcionário quando o conteudo não recebido não é vazio
     */    
    public Pessoa incluirAluno (String curso, int matricula, int telefone, String nome, String email, ArrayList<Sala> listaSalas);
    
    public Pessoa incluirServidor (boolean administrador, String cargo, int matricula, int telefone, String nome, String email, ArrayList<Sala> listaSalas);
    
    public void delPessoa(int matricula);
    
}
