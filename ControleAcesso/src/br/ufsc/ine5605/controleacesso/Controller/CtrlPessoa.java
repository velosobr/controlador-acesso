/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.Model.Aluno;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import br.ufsc.ine5605.controleacesso.View.TelaPessoa;
import br.ufsc.ine5605.controleacesso.interfaces.ICtrlPessoa;
import java.util.ArrayList;

/**
 *
 * @author Linnety3
 */
public class CtrlPessoa implements ICtrlPessoa {

    private CtrlPrincipal ctrlPrincipal;
    private TelaPessoa telaPessoa;
    private ArrayList<Pessoa> pessoas;

    public CtrlPessoa(CtrlPrincipal ctrlPrincipal) {
        this.ctrlPrincipal = ctrlPrincipal;
        this.pessoas = new ArrayList<>();
        this.telaPessoa = new TelaPessoa(this);
    }

    

    //######################ISERIR EXCEPTION########################
    @Override
    public void incluiAluno(int matricula, String nome, int telefone, String email, String curso) {
        Pessoa alunoParaVerificar = findPessoaByMatricula(matricula);
        Pessoa alunoParaIncluir = null;
        if(alunoParaVerificar==null){
            alunoParaIncluir = new Aluno(matricula, nome, telefone, email, curso );
        }
        pessoas.add(alunoParaIncluir);
    }

    @Override
    public void incluiServidor(boolean administrador, String cargo, int matricula, int telefone, String nome, String email, ArrayList<Sala> listaSalas) {
        Pessoa servidorParaVerificar = findPessoaByMatricula(matricula);
        Pessoa servidorParaIncluir = null;
        if(servidorParaIncluir==null){
            servidorParaIncluir = new Servidor(matricula, nome, telefone, email, cargo, administrador);
        }
    }

    public void delPessoa(int matricula) {
        Pessoa pessoaParaDeletar = findPessoaByMatricula(matricula);
        pessoas.remove(pessoaParaDeletar);
    }

    @Override
    public void cadastraSala(int matricula, String codigoSala) {
        boolean existeSala = false;
        Pessoa pessoaCadastro = findPessoaByMatricula(matricula);
        if (pessoaCadastro != null) {

        }
    }

    @Override
    public void delSala(String codigoSala) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Sala> listaSalasCadastradas(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Pessoa findPessoaByMatricula(int matricula) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getMatricula() == matricula) {
                return pessoa;

            }
        }
        return null;
    }
    
    
}
