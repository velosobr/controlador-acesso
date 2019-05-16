/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.Model.Acesso;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.View.TelaAcesso;
import br.ufsc.ine5605.controleacesso.interfaces.ICtrlAcesso;
import java.util.ArrayList;

/**
 *
 * @author Linnety3
 */
public class CtrlAcesso implements ICtrlAcesso {
    private final CtrlPrincipal ctrlPrincipal;
    private TelaAcesso telaAcesso;
    private ArrayList <Acesso> acessos;
    
    
    public CtrlAcesso(CtrlPrincipal ctrlPrincipal) {
        this.ctrlPrincipal = ctrlPrincipal;
        this.telaAcesso = new TelaAcesso(this);
        this.acessos = new ArrayList<>();
        
    }

    @Override
    public boolean ehLiberadoAcesso(int matricula, String codigoSala) {
        Pessoa pessoaParaTestarAcesso = ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
        Sala salaParaTestarAcesso = ctrlPrincipal.getCtrlSala().findSalaByCodigoSala(codigoSala);
        ArrayList <Pessoa> pessoasCadastradasNaSala = salaParaTestarAcesso.getPessoasCadastradas();
        addAcesso(pessoaParaTestarAcesso, salaParaTestarAcesso);
        if(pessoaParaTestarAcesso==null){
            return false;//colocar enum ErroMatricula
        }else{
            if(salaParaTestarAcesso==null){
                return false; //colocar enum ErroSala
            }
        }
        for(Pessoa pessoaCadastrada: pessoasCadastradasNaSala ){
            if(pessoaCadastrada.equals(pessoaParaTestarAcesso)){
                return true; // colocar enum Permitido
            }
        }
        return false;// colocar enum NaoPermitido
    }
    
    @Override
    public void addAcesso(Pessoa pessoa, Sala sala) {
        String situacao = "";
        Acesso acesso = new Acesso(pessoa, sala, situacao);
        acessos.add(acesso);
    }
    
    
    

    @Override
    public String geraLogByMatricula(int matricula) {
        String logAcessos = null;
        for(Acesso acesso: acessos){
            if(acesso.getPessoa().getMatricula() == matricula){
                logAcessos += " @"+acesso.getData()+" "+acesso.getPessoa().getMatricula()+" "+acesso.getSala().getCodigoSala()+" "+acesso.getSituacao();
            }
        
        }
        return logAcessos;
    }
    
    @Override
    public String geraLogByCodigoSala(String codigoSala) {
        String logAcessos = null;
        for(Acesso acesso: acessos){
            if(acesso.getSala().getCodigoSala().equals(codigoSala)){
                logAcessos += " @"+acesso.getData()+" "+acesso.getPessoa().getMatricula()+" "+acesso.getSala().getCodigoSala()+" "+acesso.getSituacao();
            }
        }
        return logAcessos;
    }

    @Override
    public Acesso findAcessoByMatricula(int matricula) {
        int matriculaNoAcesso;
        for(Acesso acesso: acessos){
            matriculaNoAcesso = acesso.getPessoa().getMatricula();
            if(matriculaNoAcesso == matricula){
                return acesso;
            }
        }
        return null;
    }

    @Override
    public Acesso findAcessoByCodigoSala(String codigoSala) {
        String codigoSalaNoAcesso = null;
        for(Acesso acesso: acessos){
            codigoSalaNoAcesso = acesso.getSala().getCodigoSala();
            if(codigoSalaNoAcesso.equals(codigoSala)){
                return acesso;
            }
        }
        return null;
    }

    
}
