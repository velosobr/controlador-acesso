/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.Exceptions.CampoVazioException;
import br.ufsc.ine5605.controleacesso.Exceptions.CodigoSalaInexistenteException;
import br.ufsc.ine5605.controleacesso.Exceptions.CodigoSalaJahExisteException;
import br.ufsc.ine5605.controleacesso.Exceptions.MatriculaInexisteException;
import br.ufsc.ine5605.controleacesso.Exceptions.PermissaoJahRealizadaException;
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
    public boolean addSala(String codigoSala, int numero, char bloco, String centro, String campus) throws Exception {
        try {

            Sala salaParaVerificar = SalaDAO.getInstancia().getSala(codigoSala);

            if (codigoSala.isEmpty() || centro.isEmpty() || campus.isEmpty()) {
                throw new CampoVazioException();
            }

            if (salaParaVerificar == null) {
                Sala salaParaIncluir = new Sala(codigoSala, numero, bloco, centro, campus);
                SalaDAO.getInstancia().put(salaParaIncluir);
                return true;
            } else {
                throw new CodigoSalaJahExisteException();
            }

        } catch (NullPointerException e) {
            throw new NullPointerException("Preencha corretamente os campos");
        }

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
    public boolean cadastraPessoaNaSala(int matricula, String codigoSala) throws Exception {
        Pessoa pessoaJahCadastrada = null;
        Pessoa pessoaParaCadastrar = PessoaDAO.getInstancia().getPessoa(matricula);
        Sala salaParaCadastrar = SalaDAO.getInstancia().getSala(codigoSala);

        ArrayList<Pessoa> pessoasCadastradas = salaParaCadastrar.getPessoasCadastradas();
        if (pessoaParaCadastrar == null) {
            throw new MatriculaInexisteException();
        }
        if (salaParaCadastrar == null) {
            throw new CodigoSalaInexistenteException();
        }

        for (Pessoa pessoa : pessoasCadastradas) {
            if (pessoa.getMatricula() == pessoaParaCadastrar.getMatricula()) {
                pessoaJahCadastrada = pessoa;
            }
        }

        if (pessoaJahCadastrada != null) {
            throw new PermissaoJahRealizadaException();
        } else {
            pessoaParaCadastrar.addSala(salaParaCadastrar);
            salaParaCadastrar.addPessoa(pessoaParaCadastrar);
            SalaDAO.getInstancia().persist();
            PessoaDAO.getInstancia().persist();
            return true;

        }
    }

    @Override
    public void deletaPessoaNaSala(int matricula, String codigoSala) throws Exception {
        Pessoa pessoaJahCadastrada = null;
        Pessoa pessoaParaDeletar = PessoaDAO.getInstancia().getPessoa(matricula);
        Sala salaParaDeletar = SalaDAO.getInstancia().getSala(codigoSala);
        ArrayList<Pessoa> pessoasCadastradas = salaParaDeletar.getPessoasCadastradas();

        if (pessoaParaDeletar == null) {
            throw new MatriculaInexisteException();
        }
        if (salaParaDeletar == null) {
            throw new CodigoSalaInexistenteException();
        }
        for (Pessoa pessoa : pessoasCadastradas) {

            if (pessoa.getMatricula() == pessoaParaDeletar.getMatricula()) {
                pessoaJahCadastrada = pessoa;
            }

        }
        if (pessoaJahCadastrada == null) {
            throw new MatriculaInexisteException();
        }
        salaParaDeletar.delPessoa(PessoaDAO.getInstancia().getPessoa(matricula));
        pessoaParaDeletar.delSala(SalaDAO.getInstancia().getSala(codigoSala));
        SalaDAO.getInstancia().persist();
        PessoaDAO.getInstancia().persist();

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
