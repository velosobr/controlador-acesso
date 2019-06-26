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
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
import br.ufsc.ine5605.controleacesso.Persistencia.SalaDAO;
import br.ufsc.ine5605.controleacesso.View.TelaSwingGestaoPermissaoPessoa;
//import br.ufsc.ine5605.controleacesso.View.TelaPessoa;
import br.ufsc.ine5605.controleacesso.View.TelaSwingPessoa;
import br.ufsc.ine5605.controleacesso.interfaces.ICtrlPessoa;
import java.util.ArrayList;

/**
 *
 * @author Caio Noguerol
 */
public class CtrlPessoa implements ICtrlPessoa {

    private static CtrlPessoa instancia;

    private final TelaSwingPessoa telaPessoa;
    
    

    public CtrlPessoa() {
       
        
        this.telaPessoa = new TelaSwingPessoa();
    }
    
    public static CtrlPessoa getInstancia(){
        if(instancia == null)
            instancia = new CtrlPessoa();
        return instancia;
    }
    
    //public TelaPessoa getTelaPessoa() {
        //return telaPessoa;
    //}

    public CtrlPrincipal getCtrlPrincipal() {
        return CtrlPrincipal.getInstancia();
    }
   
    @Override
    public boolean incluiAluno(int matricula, String nome, long telefone, String email, String curso) {
        Pessoa alunoParaVerificar = findPessoabyMatricula(matricula);
        if (alunoParaVerificar == null) {
            Aluno alunoParaIncluir = new Aluno(matricula, nome, telefone, email, curso);
            PessoaDAO.getInstancia().putAluno(alunoParaIncluir);
            return true;
        }
        return false;
    }

    @Override
    public boolean incluiServidor(int matricula, String nome, long telefone, String email, String cargo, boolean administrador) {
        Pessoa servidorParaVerificar = findPessoabyMatricula(matricula);
        if (servidorParaVerificar == null) {
            Servidor servidorParaIncluir = new Servidor(matricula, nome, telefone, email, cargo, administrador);
            PessoaDAO.getInstancia().putServidor(servidorParaIncluir);
            return true;
        }
        return false;
    }
    
    
    @Override
    public boolean alteradorDeCadastroAluno(int matricula, String nome, long telefone, String email, String curso)throws IllegalArgumentException {
        Aluno alunoParaAlterar = (Aluno)findPessoabyMatricula(matricula);
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
        Servidor servidorParaAlterar = (Servidor)  findPessoabyMatricula(matricula);
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
        Pessoa pessoaParaDeletar = findPessoabyMatricula(matricula);
        if (pessoaParaDeletar != null) {
            PessoaDAO.getInstancia().remove(pessoaParaDeletar);
            return true;
        }
        return false;
    }
    

    @Override
    public boolean cadastraSalaNaPessoa(int matricula, String codigoSala) throws IllegalArgumentException {
        Sala salaParaCadastrar = SalaDAO.getInstancia().getSala(codigoSala);
        Pessoa pessoaCadastro = PessoaDAO.getInstancia().getPessoa(matricula);
        ArrayList <Sala> salasCadastradas = pessoaCadastro.getSalasCadastradas();
        if (pessoaCadastro == null) {
            throw new IllegalArgumentException("Matricula invalida");
        }
        if (salaParaCadastrar == null) {
            throw new IllegalArgumentException("Codigo de sala invalido");
        }
        
        Sala salaJahCadastrada = null;
        for(Sala sala: salasCadastradas){
            if(sala.getCodigoSala().equals(salaParaCadastrar.getCodigoSala())){
                salaJahCadastrada = sala;
            }
        }        
        
        if (salaJahCadastrada != null){
            throw new IllegalArgumentException("A Sala ja esta adicionada na pessoa");
       
        }else{
                
            pessoaCadastro.addSala(salaParaCadastrar);
            salaParaCadastrar.addPessoa(pessoaCadastro);
            PessoaDAO.getInstancia().persist();
            SalaDAO.getInstancia().persist();
            return true;         
        }

    }

    @Override
    public void delSalaNaPessoa(int matricula, String codigoSala) throws IllegalArgumentException {
        
        Sala salaParaDeletar = CtrlPrincipal.getInstancia().getCtrlSala().findSalaByCodigoSala(codigoSala);
        Pessoa pessoaCadastro = findPessoabyMatricula(matricula);      
        ArrayList <Sala> salasCadastradas = pessoaCadastro.getSalasCadastradas();
        
        if (pessoaCadastro == null) {
            throw new IllegalArgumentException("Matricula invalida");
        }
        if (salaParaDeletar == null) {
            throw new IllegalArgumentException("Codigo de sala invalido");
        }
        
        Sala salaJahCadastrada = null;
        
        for(Sala sala: salasCadastradas){
            ;
            if(sala.getCodigoSala().equals(salaParaDeletar.getCodigoSala()))
                salaJahCadastrada = sala;
            
        }
        
            pessoaCadastro.delSala(salaJahCadastrada);
            salaParaDeletar.delPessoa(pessoaCadastro);
            PessoaDAO.getInstancia().persist();
            SalaDAO.getInstancia().persist();
            
        if (salaJahCadastrada == null) {
            throw new IllegalArgumentException("A sala nao consta na lista de salas da pessoa. Tente novamente.");
            
            
        } 

    }

   
    public void abreTelaSwingPessoa() {
        TelaSwingPessoa.getInstancia().setVisible(true);
    }
    
    public void abreTelaGestaoPermissaoPessoa(int matricula){
        TelaSwingGestaoPermissaoPessoa telaGestaoPermissaoPessoa = new TelaSwingGestaoPermissaoPessoa(matricula);
        telaGestaoPermissaoPessoa.setVisible(true);
    }
            
            
    public Pessoa findPessoabyMatricula(int matricula){
        Pessoa pessoa = PessoaDAO.getInstancia().getPessoa(matricula);
        return pessoa;
    }

}
