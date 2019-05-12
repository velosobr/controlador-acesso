/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.View.TelaAdm;
import br.ufsc.ine5605.controleacesso.View.TelaPrincipal;

/**
 *
 * @author Lino Veloso
 */
public class CtrlPrincipal {

    private final TelaPrincipal telaInicial;
    private final TelaAdm telaAdm;
    private final CtrlAcesso ctrlAcesso;
    private final CtrlPessoa ctrlPessoa;
    private final CtrlSala ctrlSala;

    public CtrlPrincipal() {
        telaInicial = new TelaPrincipal(this);
        telaAdm = new TelaAdm(this);
        ctrlAcesso = new CtrlAcesso(this);
        ctrlPessoa = new CtrlPessoa(this);
        ctrlSala = new CtrlSala(this);

    }

    public void abreTelaInicial() {
        telaInicial.inicio();
    }

    public boolean verificaSeEhAdm(int matricula) {
        return false;

    }

    public void acessaCtrlSala() {

    }

    public void acessaCtrlAcesso() {

    }

    public void acessaCtrlPessoa() {

    }

    public TelaPrincipal getTelaInicial() {
        return telaInicial;
    }

    public TelaAdm getTelaAdm() {
        return telaAdm;
    }

    public CtrlAcesso getCtrlAcesso() {
        return ctrlAcesso;
    }

    public CtrlPessoa getCtrlPessoa() {
        return ctrlPessoa;
    }

    public CtrlSala getCtrlSala() {
        return ctrlSala;
    }

}
