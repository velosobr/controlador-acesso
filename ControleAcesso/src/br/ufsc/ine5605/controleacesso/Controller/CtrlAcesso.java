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
    private ArrayList<Acesso> acessos;

    public CtrlAcesso(CtrlPrincipal ctrlPrincipal) {
        this.ctrlPrincipal = ctrlPrincipal;
        this.telaAcesso = new TelaAcesso(this);
        this.acessos = new ArrayList<>();

    }

    public CtrlPrincipal getCtrlPrincipal() {
        return ctrlPrincipal;
    }

    
    @Override
    public boolean ehLiberadoAcesso(int matricula, String codigoSala) throws IllegalArgumentException{
        Pessoa pessoaParaTestarAcesso = ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
        Sala salaParaTestarAcesso = ctrlPrincipal.getCtrlSala().findSalaByCodigoSala(codigoSala);
        
       
        if (pessoaParaTestarAcesso == null){
            throw new IllegalArgumentException("Matricula invalida");
        }
        if(salaParaTestarAcesso == null){
            throw new IllegalArgumentException("Codigo de sala invalido");
        }
        ArrayList<Pessoa> pessoasCadastradasNaSala = salaParaTestarAcesso.getPessoasCadastradas();
        for (Pessoa pessoaCadastrada : pessoasCadastradasNaSala) {
            if (pessoaCadastrada.equals(pessoaParaTestarAcesso)) {
                //LOG
                //Acesso acesso = new Acesso(pessoaParaTestarAcesso, salaParaTestarAcesso, Permitido.getDescricao());
                acessos.add(new Acesso(pessoaParaTestarAcesso, salaParaTestarAcesso, Permitido.getDescricao()));
                return true;
                // colocar enum Permitido
            }
        }
        //LOG
        //Acesso acesso = new Acesso(pessoaParaTestarAcesso, salaParaTestarAcesso, NaoPermitido.getDescricao());
        acessos.add(new Acesso(pessoaParaTestarAcesso, salaParaTestarAcesso, NaoPermitido.getDescricao()));
        return false;// clocar enum NaoPermitido
    }

    
    @Override
    public String geraLogByMatricula(int matricula)throws IllegalArgumentException {
        String logAcessos = "";
        Pessoa pessoa = ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
        if(pessoa == null){
            throw new IllegalArgumentException("Matricula invalida");
        }
        for (Acesso acesso : acessos) {
            if (acesso.getPessoa().getMatricula() == matricula) {
                
                logAcessos += "@Data: " + acesso.getData() + " Matricula: " + acesso.getPessoa().getMatricula() + " Codigo de Sala: " + acesso.getSala().getCodigoSala() + " Situacao de Acesso: " + acesso.getSituacao()+"\n";
            }
        }
        if (acessos.size()==0){
            logAcessos = "Sem registro de acesso";
        }
        return logAcessos;
    }

    @Override
    public String geraLogByCodigoSala(String codigoSala)throws IllegalArgumentException {
        String logAcessos = "";
        Sala sala = ctrlPrincipal.getCtrlSala().findSalaByCodigoSala(codigoSala);
        if(sala == null){
            throw new IllegalArgumentException("Codigo de sala invalido");
        }
        
        for (Acesso acesso : acessos) {
            if (acesso.getSala().getCodigoSala().equals(codigoSala)) {
                logAcessos += "@Data: "+ acesso.getData() + " Matricula: " + acesso.getPessoa().getMatricula() + " Codigo de Sala: " + acesso.getSala().getCodigoSala() + " Situacao de acesso: " + acesso.getSituacao()+"\n";
            }
        }
        if (acessos.size()==0){
            logAcessos = "Sem registro de acesso";
        }
        return logAcessos;
    }

    @Override
    public Acesso findAcessoByMatricula(int matricula) {
        int matriculaNoAcesso;
        for (Acesso acesso : acessos) {
            matriculaNoAcesso = acesso.getPessoa().getMatricula();
            if (matriculaNoAcesso == matricula) {
                return acesso;
            }
        }
        return null;
    }

    @Override
    public Acesso findAcessoByCodigoSala(String codigoSala) {
        String codigoSalaNoAcesso = null;
        for (Acesso acesso : acessos) {
            codigoSalaNoAcesso = acesso.getSala().getCodigoSala();
            if (codigoSalaNoAcesso.equals(codigoSala)) {
                return acesso;
            }
        }
        return null;
    }

    /**
     * @return the telaAcesso
     */
    public TelaAcesso getTelaAcesso() {
        return telaAcesso;
    }

}
