/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.View.TelaSala;
import br.ufsc.ine5605.controleacesso.interfaces.ICtrlSala;
import java.util.ArrayList;

/**
 *
 * @author Caio Noguerol
 */
public class CtrlSala implements ICtrlSala {
    
    private final CtrlPrincipal ctrlPrincipal;
    private TelaSala telaSala;
    private ArrayList <Sala> salas;
    
    public CtrlSala(CtrlPrincipal ctrlPrincipal) {
        this.ctrlPrincipal = ctrlPrincipal;
        this.telaSala = new TelaSala(this);
        this.salas = new ArrayList<>();
    }

    @Override
    public void addSala(String codigoSala, int numero, char bloco, String centro, String campus) {
        Sala salaParaVerificar = findSalaByCodigoSala(codigoSala);
        Sala salaParaIncluir = null;
        if(salaParaVerificar == null){
            salaParaIncluir = new Sala(codigoSala, numero, bloco, centro, campus);
            salas.add(salaParaIncluir);
        }
    }

    @Override
    public void delSala(String codigoSala) {
        Sala salaParaDeletar = findSalaByCodigoSala(codigoSala);
        if(salaParaDeletar!=null){
            salas.remove(salaParaDeletar);
        }
    }

    @Override
    public Sala findSalaByCodigoSala(String codigoSala) {
        for(Sala sala:salas){
            if(sala.getCodigoSala().equals(codigoSala)){
                return sala;
            }
        }
        return null;
    }

    @Override
    public void cadastraPessoaNaSala(int matricula, String codigoSala) {
        Pessoa pessoaParaCadastrar = ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
        Sala salaParaCadastrar = findSalaByCodigoSala(codigoSala);
        ArrayList <Pessoa> pessoasCadastradas = salaParaCadastrar.getPessoasCadastradas();
        if(pessoaParaCadastrar != null && salaParaCadastrar != null){
            pessoaParaCadastrar.addSala(salaParaCadastrar);
            salaParaCadastrar.addPessoa(pessoaParaCadastrar);
        }
    }

    @Override
    public void deletaPessoaNaSala(int matricula, String codigoSala) {
        Pessoa pessoaParaCadastrar = ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
        Sala salaParaCadastrar = findSalaByCodigoSala(codigoSala);
        if(pessoaParaCadastrar != null && salaParaCadastrar != null){
            pessoaParaCadastrar.delSala(salaParaCadastrar);
            salaParaCadastrar.delPessoa(pessoaParaCadastrar);
        }
    }
    @Override
    public String listaPessoasCadastradas(String codigoSala) {
        Sala salaCadastrada = findSalaByCodigoSala(codigoSala);
        ArrayList <Pessoa> pessoasCadastradas = salaCadastrada.getPessoasCadastradas();
        String listaPessoasCadastradasNaSala = "";
        if(salaCadastrada!=null){
            for(Pessoa pessoa:pessoasCadastradas ){
            listaPessoasCadastradasNaSala += pessoa.getMatricula() + " ";
            }
        }
    return listaPessoasCadastradasNaSala;
    }



}
