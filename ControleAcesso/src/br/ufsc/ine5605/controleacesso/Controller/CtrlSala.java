/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.View.TelaSwingSalaCadastro;
import br.ufsc.ine5605.controleacesso.View.TelaSwingGestaoPermissaoSala;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
import br.ufsc.ine5605.controleacesso.Persistencia.SalaDAO;
import br.ufsc.ine5605.controleacesso.View.TelaSwingPessoaCadastro;
import br.ufsc.ine5605.controleacesso.View.TelaSwingPrincipal;
import br.ufsc.ine5605.controleacesso.View.TelaSwingSala;
import br.ufsc.ine5605.controleacesso.interfaces.ICtrlSala;
import java.util.ArrayList;

/**
 *
 * @author Caio Noguerol
 */
public class CtrlSala implements ICtrlSala {

    private static CtrlSala instancia;

    private CtrlSala() {
    }

    public static CtrlSala getInstancia() {
        if (instancia == null) {
            instancia = new CtrlSala();
        }
        return instancia;
    }

    public TelaSwingSala getTelaSwingSala() {
        return TelaSwingSala.getInstancia();
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return CtrlPrincipal.getInstancia();

    }

    @Override
    public boolean addSala(String codigoSala, 
            int numero, char bloco, String centro, 
            String campus) throws IllegalArgumentException {
        Sala salaParaVerificar = SalaDAO.getInstancia().getSala(codigoSala);
        Sala salaParaIncluir = null;
        if (codigoSala.equals("")) {
            throw new IllegalArgumentException("Codigo de sala invalido, cadastro nao realizado!");
        }
        if (salaParaVerificar == null) {
            salaParaIncluir = new Sala(codigoSala, numero, bloco, centro, campus);
            SalaDAO.getInstancia().put(salaParaIncluir);
            return true;
        }
        return false;
    }

    @Override
    public boolean delSala(String codigoSala) {
        Sala salaParaDeletar = findSalaByCodigoSala(codigoSala);
        if (salaParaDeletar != null) {
            SalaDAO.getInstancia().remove(salaParaDeletar);
            return true;
        }

        return false;
    }

    @Override
    public boolean alteradorDeCadastroSala(String codigoSala, int numero, char bloco, String centro, String campus) throws IllegalArgumentException {
        Sala salaParaAlterar = SalaDAO.getInstancia().getSala(codigoSala);
        if (salaParaAlterar == null) {
            throw new IllegalArgumentException("Codigo de sala invalido, alteracao cadastral nao realizada!");
        }
        salaParaAlterar.setNumero(numero);
        salaParaAlterar.setBloco(bloco);
        salaParaAlterar.setCentro(centro);
        salaParaAlterar.setCampus(campus);

        SalaDAO.getInstancia().setSala(codigoSala, salaParaAlterar);

        return true;
    }

    @Override
    public boolean cadastraPessoaNaSala(int matricula, String codigoSala) throws IllegalArgumentException {
        Pessoa pessoaParaCadastrar = PessoaDAO.getInstancia().getPessoa(matricula);
        Sala salaParaCadastrar = SalaDAO.getInstancia().getSala(codigoSala);
        if (pessoaParaCadastrar == null) {
            throw new IllegalArgumentException("Matricula invalida");
        }
        if (salaParaCadastrar == null) {
            throw new IllegalArgumentException("Codigo de sala invalido");
        }

        if (!salaParaCadastrar.getPessoasCadastradas().contains(pessoaParaCadastrar)) {
            pessoaParaCadastrar.addSala(salaParaCadastrar);
            salaParaCadastrar.addPessoa(pessoaParaCadastrar);
            SalaDAO.getInstancia().persist();
            PessoaDAO.getInstancia().persist();
            return true;
        } else {
            throw new IllegalArgumentException("A pessoa ja esta adicionada na sala. Tente novamente.");
        }

    }

    @Override
    public boolean deletaPessoaNaSala(int matricula, String codigoSala) throws IllegalArgumentException {
        Pessoa pessoaParaDeletar = PessoaDAO.getInstancia().getPessoa(matricula);
        Sala salaParaDeletar = SalaDAO.getInstancia().getSala(codigoSala);
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
        } else {
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
            listaPessoasCadastradasNaSala += "@Matricula: " + pessoa.getMatricula() + " Nome: " + pessoa.getNome() + "\n";
        }
        if (listaPessoasCadastradasNaSala.equals("")) {
            listaPessoasCadastradasNaSala = "Nao ha pessoas Cadastradas";
        }
        return listaPessoasCadastradasNaSala;
    }

    @Override
    public String listAllSalasCadastradas() {
        String listaSalasCadastradas = "";
        for (Sala sala : SalaDAO.getInstancia().getList()) {
            listaSalasCadastradas += "@Codigo de sala:" + sala.getCodigoSala() + " Centro: " + sala.getCentro() + "\n";
        }
        if (listaSalasCadastradas.equals("")) {
            listaSalasCadastradas = "Nao ha salas cadastradas";
            return listaSalasCadastradas;
        }
        return listaSalasCadastradas;
    }

    @Override
    public Sala findSalaByCodigoSala(String codigoSala) {
        for (Sala sala : SalaDAO.getInstancia().getList()) {
            if (sala.getCodigoSala().equals(codigoSala)) {
                return sala;
            }
        }

        return null;
    }

    public void abreTelaSwingSala() {
        TelaSwingSala.getInstancia().setVisible(true);
    }

    public void abreTelaGestaoPermissaoSala(String codSala) {
        TelaSwingGestaoPermissaoSala telaGestaoPermissaoSala = new TelaSwingGestaoPermissaoSala(codSala);
        telaGestaoPermissaoSala.setVisible(true);

    }

    public void abreTelaCadastroSala() {
         TelaSwingSalaCadastro telaCadastroSala = new TelaSwingSalaCadastro();
        telaCadastroSala.setVisible(true);
    }

}
