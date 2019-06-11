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
 * @author Caio Noguerol
 */
public class CtrlPessoa implements ICtrlPessoa {

    private static CtrlPessoa instancia;

    private final TelaPessoa telaPessoa;
    private final ArrayList<Pessoa> pessoas;

    public CtrlPessoa() {
       
        this.pessoas = new ArrayList<>();
        this.telaPessoa = new TelaPessoa(this);
    }
    
    public static CtrlPessoa getInstancia(){
        if(instancia == null)
            instancia = new CtrlPessoa();
        return instancia;
    }
    
    public TelaPessoa getTelaPessoa() {
        return telaPessoa;
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return CtrlPrincipal.getInstancia();
    }

    @Override
    public boolean incluiAluno(int matricula, String nome, long telefone, String email, String curso) {
        Pessoa alunoParaVerificar = findPessoaByMatricula(matricula);
        if (alunoParaVerificar == null) {
            Pessoa alunoParaIncluir = new Aluno(matricula, nome, telefone, email, curso);
            pessoas.add(alunoParaIncluir);
            return true;
        }
        return false;
    }

    @Override
    public boolean incluiServidor(int matricula, String nome, long telefone, String email, String cargo, boolean administrador) {
        Pessoa servidorParaVerificar = findPessoaByMatricula(matricula);
        if (servidorParaVerificar == null) {
            Pessoa servidorParaIncluir = new Servidor(matricula, nome, telefone, email, cargo, administrador);
            pessoas.add(servidorParaIncluir);
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean alteradorDeCadastroAluno(int matricula, String nome, long telefone, String email, String curso)throws IllegalArgumentException {
        Aluno alunoParaAlterar = (Aluno)findPessoaByMatricula(matricula);
        if(alunoParaAlterar==null){
           throw new IllegalArgumentException("Matricula invalida, alteracao nao foi realizada"); 
        }
        alunoParaAlterar.setNome(nome);
        alunoParaAlterar.setTelefone(telefone);
        alunoParaAlterar.setEmail(email);
        alunoParaAlterar.setCurso(email);
        return true;
        
    }
    
    @Override
    public boolean alteradorDeCadastroServidor(int matricula, String nome, long telefone, String email, String cargo, boolean administrador)throws IllegalArgumentException{
        Servidor servidorParaAlterar = (Servidor)  findPessoaByMatricula(matricula);
        if(servidorParaAlterar==null){
           throw new IllegalArgumentException("Matricula invalida, alteracao nao foi realizada");
        }
        servidorParaAlterar.setNome(nome);
        servidorParaAlterar.getTelefone();
        servidorParaAlterar.setEmail(email);
        servidorParaAlterar.setCargo(cargo);
        servidorParaAlterar.setAdministrador(administrador);
        return true;
    }
    
    @Override
    public boolean delPessoa(int matricula) throws IllegalArgumentException {
        Pessoa pessoaParaDeletar = findPessoaByMatricula(matricula);
        if (pessoaParaDeletar != null) {
            pessoas.remove(pessoaParaDeletar);
            return true;
        }
        return false;
    }
    

    @Override
    public boolean cadastraSalaNaPessoa(int matricula, String codigoSala) throws IllegalArgumentException {
        Sala salaParaCadastrar = CtrlPrincipal.getInstancia().getCtrlSala().findSalaByCodigoSala(codigoSala);
        Pessoa pessoaCadastro = findPessoaByMatricula(matricula);
        if (pessoaCadastro == null) {
            throw new IllegalArgumentException("Matricula invalida");
        }
        if (salaParaCadastrar == null) {
            throw new IllegalArgumentException("Codigo de sala invalido");
        }

        if (!pessoaCadastro.getSalasCadastradas().contains(salaParaCadastrar)) {
            pessoaCadastro.addSala(salaParaCadastrar);
            salaParaCadastrar.addPessoa(pessoaCadastro);
            return true;
        } else {
            throw new IllegalArgumentException("A Sala ja esta adicionada na pessoa");
        }

    }

    @Override
    public boolean delSalaNaPessoa(int matricula, String codigoSala) throws IllegalArgumentException {
        Sala salaParaDeletar = CtrlPrincipal.getInstancia().getCtrlSala().findSalaByCodigoSala(codigoSala);
        Pessoa pessoaCadastro = findPessoaByMatricula(matricula);
        if (pessoaCadastro == null) {
            throw new IllegalArgumentException("Matricula invalida");
        }
        if (salaParaDeletar == null) {
            throw new IllegalArgumentException("Codigo de sala invalido");
        }

        if (pessoaCadastro.getSalasCadastradas().contains(salaParaDeletar)) {

            pessoaCadastro.delSala(salaParaDeletar);
            salaParaDeletar.delPessoa(pessoaCadastro);
            return true;
        } else {
            throw new IllegalArgumentException("A sala nao consta na lista de salas da pessoa. Tente novamente.");
        }

    }

    /**
     *
     * @param matricula
     * @return
     */
    @Override
    public String listaSalasCadastradas(int matricula) throws IllegalArgumentException {
        Pessoa pessoaCadastrada = findPessoaByMatricula(matricula);
        String lista = "";
        if (pessoaCadastrada == null) {
            throw new IllegalArgumentException("Matricula invalida");
        }

        ArrayList<Sala> salasCadastradas = pessoaCadastrada.getSalasCadastradas();
        for (Sala salaCadastrada : salasCadastradas) {
            lista += "@Codigo de sala: " +salaCadastrada.getCodigoSala() + " Numero: " + salaCadastrada.getNumero() + " Centro: " + salaCadastrada.getCentro() + "\n";

        }
        if (lista.equals("")) {
            lista = "Nao ha salas cadastradas";
        }

        return lista;

    }

    @Override
    public String listAllPessoasCadastradas() {
        String listaPessoasCadastradas = "";
        for (Pessoa pessoa : pessoas) {
            listaPessoasCadastradas += "@Matricula: " + pessoa.getMatricula() + " Nome: " + pessoa.getNome() + "\n";
        }
        if (listaPessoasCadastradas.equals("")) {
            listaPessoasCadastradas = "Nao ha pessoas cadastradas";
            return listaPessoasCadastradas;
        }
        return listaPessoasCadastradas;
    }

    @Override
    public Pessoa findPessoaByMatricula(int matricula) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getMatricula() == matricula) {
                return pessoa;
            }
        }
        return null;
    }

}
