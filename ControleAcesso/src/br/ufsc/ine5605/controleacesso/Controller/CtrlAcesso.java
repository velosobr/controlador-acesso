/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.Exceptions.CodigoSalaInexistenteException;
import br.ufsc.ine5605.controleacesso.Exceptions.MatriculaInexisteException;
import br.ufsc.ine5605.controleacesso.Exceptions.SemRegistroException;
import br.ufsc.ine5605.controleacesso.Model.Acesso;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import static br.ufsc.ine5605.controleacesso.Model.SituacaoAcesso.NaoPermitido;
import static br.ufsc.ine5605.controleacesso.Model.SituacaoAcesso.Permitido;
import br.ufsc.ine5605.controleacesso.Persistencia.AcessoDAO;
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
import br.ufsc.ine5605.controleacesso.Persistencia.SalaDAO;
import br.ufsc.ine5605.controleacesso.View.TelaSwingLogAcessos;

import br.ufsc.ine5605.controleacesso.interfaces.ICtrlAcesso;
import java.util.ArrayList;

/**
 *
 * @author Linnety3
 */
public class CtrlAcesso implements ICtrlAcesso {

    private static CtrlAcesso instancia;
    private final TelaSwingLogAcessos telaAcesso;
    

    public CtrlAcesso() {
        
        this.telaAcesso = TelaSwingLogAcessos.getInstancia();
        

    }

    public CtrlPrincipal getCtrlPrincipal() {
        return CtrlPrincipal.getInstancia();
    }
    
    public static CtrlAcesso getInstancia(){
        if(instancia == null)
            instancia = new CtrlAcesso();
        return instancia;
    }
    
    
   

    
    @Override
    public boolean ehLiberadoAcesso(int matricula, String codigoSala) throws Exception{
        Pessoa pessoaParaTestarAcesso = PessoaDAO.getInstancia().getPessoa(matricula);
        
        if (pessoaParaTestarAcesso == null){
            throw new MatriculaInexisteException();
        }
        
        Sala salaParaTestarAcesso = SalaDAO.getInstancia().getSala(codigoSala);
        if(salaParaTestarAcesso == null){
            throw new CodigoSalaInexistenteException();
        }
        ArrayList<Sala> salaCadastradasNaPessoa = pessoaParaTestarAcesso.getSalasCadastradas();
        System.out.println(salaCadastradasNaPessoa);
        for (Sala salaCadastrada : salaCadastradasNaPessoa) {
            if (salaCadastrada.getCodigoSala().equals(salaParaTestarAcesso.getCodigoSala())) {
                System.out.println("Entrou no if do eh liberado");
                AcessoDAO.getInstancia().put(new Acesso(pessoaParaTestarAcesso, salaParaTestarAcesso, Permitido.getDescricao()));
                AcessoDAO.getInstancia().persist();
                return true;   
            }
        }
        System.out.println("passando aqui para negar acesso");
        AcessoDAO.getInstancia().put(new Acesso(pessoaParaTestarAcesso, salaParaTestarAcesso, NaoPermitido.getDescricao()));
        AcessoDAO.getInstancia().persist();
        return false;
    }

    
    @Override
    public ArrayList <Acesso> geraListaByMatricula(int matricula)throws Exception{
        //Pessoa pessoa = CtrlPrincipal.getInstancia().getCtrlPessoa().findPessoabyMatricula(matricula);
        Integer matriculaLista = matricula;
        if(matriculaLista == null){
            throw new MatriculaInexisteException();
        }
        ArrayList <Acesso> listaAcessos = new ArrayList();
        for(Acesso acesso:AcessoDAO.getInstancia().getList()){
            
            if(acesso.getPessoa().getMatricula() == (matricula)){ 
                listaAcessos.add(acesso);
            }
            
        }
        if(listaAcessos.isEmpty()){
            throw new SemRegistroException();
        }
    
    return listaAcessos;       
       
    }

    @Override
    public ArrayList <Acesso> geraListaByCodigoSala(String codigoSala)throws Exception {
        
        Sala sala = CtrlPrincipal.getInstancia().getCtrlSala().findSalaByCodigoSala(codigoSala);
        if(sala == null){
            throw new  CodigoSalaInexistenteException();
        }
        ArrayList <Acesso> listaTodosAcessos = new ArrayList();
        listaTodosAcessos = AcessoDAO.getInstancia().getList();
        
        ArrayList <Acesso> listaAcessosCodigoSala = new ArrayList();
        for (Acesso acesso : listaTodosAcessos) {
            
            if (acesso.getSala().getCodigoSala().equals(sala.getCodigoSala())) {
                
                listaAcessosCodigoSala.add(acesso);
            }
           
        }
        if (listaAcessosCodigoSala.isEmpty()){
            throw new SemRegistroException();
        }
        return listaAcessosCodigoSala;
    }
    
    public ArrayList <Acesso> geraListaTodosAcessos(){
        return AcessoDAO.getInstancia().getList();
    }

    @Override
    public Acesso findAcessoByMatricula(int matricula) {
        Acesso acesso = AcessoDAO.getInstancia().getAcesso(matricula);
        return acesso;
    }

    

    public void abreTelaSwingLogAcessos() {
        telaAcesso.setVisible(true);
    }

    

}
