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
 * @author Linnety3
 */
public class CtrlPrincipal {

    private TelaPrincipal telaInicial;
    private TelaAdm telaAdm;

    public CtrlPrincipal() {
        telaInicial = new TelaPrincipal(this);
    }

    public void abreTelaInicial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
