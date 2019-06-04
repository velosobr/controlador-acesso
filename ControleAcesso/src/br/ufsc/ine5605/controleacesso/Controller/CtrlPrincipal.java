/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.View.TelaAdm;
import br.ufsc.ine5605.controleacesso.View.TelaPrincipal;
import br.ufsc.ine5605.controleacesso.View.TelaSwingPrincipal;

/**
 *
 * @author Lino Veloso
 */
public class CtrlPrincipal {

    private static CtrlPrincipal instancia;
    
    private final TelaAdm telaAdm;
    private final CtrlAcesso ctrlAcesso;
    private final CtrlPessoa ctrlPessoa;

    public CtrlPrincipal() {
        telaAdm = new TelaAdm(this);
        ctrlAcesso = new CtrlAcesso(this);
        ctrlPessoa = new CtrlPessoa(this);

    }
    public static CtrlPrincipal getInstancia(){
        if (instancia == null)
            instancia = new CtrlPrincipal();
        
        return instancia;
    }

    public void abreTelaInicial() {
        TelaSwingPrincipal.getInstancia().setVisible(true);
    }

    public void abreTelaAdm() {
        telaAdm.inicio();
    }

    public TelaSwingPrincipal getTelaInicial() {
        return TelaSwingPrincipal.getInstancia();
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
        return CtrlSala.getInstancia();

    }

}
