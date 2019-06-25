/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

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
    public boolean ehLiberadoAcesso(int matricula, String codigoSala) throws IllegalArgumentException{
        Pessoa pessoaParaTestarAcesso = PessoaDAO.getInstancia().getPessoa(matricula);
        Sala salaParaTestarAcesso = SalaDAO.getInstancia().getSala(codigoSala);
        
       
        if (pessoaParaTestarAcesso == null){
            throw new IllegalArgumentException("Matricula não encontrada");
        }
        if(salaParaTestarAcesso == null){
            throw new IllegalArgumentException("Sala não encontrada");
        }
        ArrayList<Sala> salaCadastradasNaPessoa = pessoaParaTestarAcesso.getSalasCadastradas();
        System.out.println(salaCadastradasNaPessoa);
        for (Sala salaCadastrada : salaCadastradasNaPessoa) {
            if (salaCadastrada.equals(salaParaTestarAcesso)) {
                System.out.println("Entrou no if do eh liberado");
                AcessoDAO.getInstancia().put(new Acesso(pessoaParaTestarAcesso, salaParaTestarAcesso, Permitido.getDescricao()));
                return true;   
            }
        }
        System.out.println("passando aqui para negar acesso");
        AcessoDAO.getInstancia().put(new Acesso(pessoaParaTestarAcesso, salaParaTestarAcesso, NaoPermitido.getDescricao()));
        return false;
    }

    
    @Override
    public ArrayList <Acesso> geraListaByMatricula(int matricula)throws IllegalArgumentException {
        
        
        
        Pessoa pessoa = CtrlPrincipal.getInstancia().getCtrlPessoa().findPessoabyMatricula(matricula);
        if(pessoa == null){
            throw new IllegalArgumentException("Matricula invalida");
        }
        ArrayList <Acesso> listaAcessos = new ArrayList();
        for(Acesso acesso:AcessoDAO.getInstancia().getList()){
            if(acesso.getPessoa().equals(pessoa)){
                listaAcessos.add(acesso);
            }
            
        }
        if(listaAcessos.isEmpty()){
            throw new IllegalArgumentException("Sem registro de acesso");
        }
    
    return listaAcessos;       
       
    }

    @Override
    public ArrayList <Acesso> geraListaByCodigoSala(String codigoSala)throws IllegalArgumentException {
        
        Sala sala = CtrlPrincipal.getInstancia().getCtrlSala().findSalaByCodigoSala(codigoSala);
        if(sala == null){
            throw new IllegalArgumentException("Codigo de sala invalido");
        }
        ArrayList <Acesso> listaAcessos = new ArrayList();
        for (Acesso acesso : listaAcessos) {
            if (acesso.getSala().equals(sala)) {
                listaAcessos.add(acesso);
            }
        }
        if (listaAcessos.isEmpty()){
            throw new IllegalArgumentException("Sem registro de acesso");
        }
        return listaAcessos;
    }
    
    public ArrayList <Acesso> geraListaTodosAcessos(){
        return AcessoDAO.getInstancia().getList();
    }

    @Override
    public Acesso findAcessoByMatricula(int matricula) {
        Acesso acesso = AcessoDAO.getInstancia().getAcesso(matricula);
        return acesso;
    }

    @Override
    public Acesso findAcessoByCodigoSala(String codigoSala) {
        Acesso acesso = AcessoDAO.getInstancia().getAcesso(codigoSala);
        return acesso;
    }

    public void abreTelaSwingLogAcessos() {
        telaAcesso.setVisible(true);
    }

    

}
