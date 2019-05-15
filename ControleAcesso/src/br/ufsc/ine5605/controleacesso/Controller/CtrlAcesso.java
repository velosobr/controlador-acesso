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
import java.util.Date;

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

        for(Pessoa pessoaCadastrada: pessoasCadastradasNaSala ){
            if(pessoaCadastrada.equals(pessoaParaTestarAcesso)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void addAcesso(Pessoa pessoa, Sala sala) {
        String situacao = "";
        Date data = null;
        Acesso acesso = new Acesso(pessoa, sala, data, situacao);
        acessos.add(acesso);
    }
    
    
    

    @Override
    public String geraLogByMatricula(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String geraLogByCodigoSala(String codigoSala) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Acesso findAcessoByMatricula(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Acesso findAcessoByCodigoSala(String codigoSala) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
