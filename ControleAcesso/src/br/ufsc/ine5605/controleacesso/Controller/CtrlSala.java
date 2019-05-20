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
    private final TelaSala telaSala;
    private final ArrayList<Sala> salas;

    public CtrlSala(CtrlPrincipal ctrlPrincipal) {
        this.ctrlPrincipal = ctrlPrincipal;
        this.telaSala = new TelaSala(this);
        this.salas = new ArrayList<>();
    }

    public TelaSala getTelaSala() {
        return telaSala;
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return this.ctrlPrincipal;
    }

    @Override
    public boolean addSala(String codigoSala, int numero, char bloco, String centro, String campus) {
        Sala salaParaVerificar = findSalaByCodigoSala(codigoSala);
        Sala salaParaIncluir = null;
        if (salaParaVerificar == null) {
            salaParaIncluir = new Sala(codigoSala, numero, bloco, centro, campus);
            salas.add(salaParaIncluir);
            return true;
        }
        return false;
    }

    @Override
    public boolean delSala(String codigoSala) {
        Sala salaParaDeletar = findSalaByCodigoSala(codigoSala);
        if (salaParaDeletar != null) {
            salas.remove(salaParaDeletar);
            return true;
        }

        return false;
    }

    @Override
    public boolean cadastraPessoaNaSala(int matricula, String codigoSala) throws IllegalArgumentException {
        Pessoa pessoaParaCadastrar = ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
        Sala salaParaCadastrar = findSalaByCodigoSala(codigoSala);
        if (pessoaParaCadastrar == null) {
            throw new IllegalArgumentException("Matricula invalida");
        }
        if (salaParaCadastrar == null) {
            throw new IllegalArgumentException("Codigo de sala invalido");
        }

        if (!salaParaCadastrar.getPessoasCadastradas().contains(pessoaParaCadastrar)) {
            pessoaParaCadastrar.addSala(salaParaCadastrar);
            salaParaCadastrar.addPessoa(pessoaParaCadastrar);
            return true;
        } else {
            throw new IllegalArgumentException("A pessoa ja esta adicionada na sala. Tente novamente.");
        }

    }

    @Override
    public boolean deletaPessoaNaSala(int matricula, String codigoSala) throws IllegalArgumentException {
        Pessoa pessoaParaDeletar = ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
        Sala salaParaDeletar = findSalaByCodigoSala(codigoSala);
        if (pessoaParaDeletar == null) {
            throw new IllegalArgumentException("Matricula invalida");
        }
        if (salaParaDeletar == null) {
            throw new IllegalArgumentException("Codigo de sala invalido");
        }
        if (salaParaDeletar.getPessoasCadastradas().contains(pessoaParaDeletar)) {
            pessoaParaDeletar.delSala(salaParaDeletar);
            salaParaDeletar.delPessoa(pessoaParaDeletar);
            return true;
        }else{
            throw new IllegalArgumentException("A pessoa nao consta na lista de pessoas da sala.");
        }

    }

    @Override
    public String listaPessoasCadastradas(String codigoSala) throws IllegalArgumentException {
        Sala salaCadastrada = findSalaByCodigoSala(codigoSala);
        String listaPessoasCadastradasNaSala = "";

        if (salaCadastrada == null) {
            throw new IllegalArgumentException("Codigo de sala invalido");
        }

        ArrayList<Pessoa> pessoasCadastradas = salaCadastrada.getPessoasCadastradas();
        for (Pessoa pessoa : pessoasCadastradas) {
            listaPessoasCadastradasNaSala +="@Matricula: "+ pessoa.getMatricula() + " Nome: " + pessoa.getNome() + "\n";
        }
        if (listaPessoasCadastradasNaSala.equals("")) {
            listaPessoasCadastradasNaSala = "Nao ha pessoas Cadastradas";
        }
        return listaPessoasCadastradasNaSala;
    }

    public String listAllSalasCadastradas() {
        String listaSalasCadastradas = "";
        for (Sala sala : salas) {
            listaSalasCadastradas += "@Codigo de sala:" + sala.getCodigoSala() +" Centro: "+ sala.getCentro() + "\n";
        }
        if (listaSalasCadastradas.equals("")) {
            listaSalasCadastradas = "Nao ha salas cadastradas";
            return listaSalasCadastradas;
        }
        return listaSalasCadastradas;
    }

    @Override
    public Sala findSalaByCodigoSala(String codigoSala) {
        for (Sala sala : salas) {
            if (sala.getCodigoSala().equals(codigoSala)) {
                return sala;
            }
        }

        return null;
    }

}
