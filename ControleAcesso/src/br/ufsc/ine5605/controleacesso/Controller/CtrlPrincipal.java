/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.View.TelaSwingAdm;
import br.ufsc.ine5605.controleacesso.View.TelaSwingPrincipal;

/**
 *
 * @author Lino Veloso
 */
public class CtrlPrincipal {

    private static CtrlPrincipal instancia;

    public CtrlPrincipal() {   
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
        TelaSwingAdm.GetInstacia().setVisible(true);
    }

    public TelaSwingPrincipal getTelaInicial() {
        return TelaSwingPrincipal.getInstancia();
    }

    public TelaSwingAdm getTelaAdm() {
        return TelaSwingAdm.GetInstacia();
    }

    public CtrlAcesso getCtrlAcesso() {
        return CtrlAcesso.getInstancia();
    }

    public CtrlPessoa getCtrlPessoa() {
        return CtrlPessoa.getInstancia();
    }

    public CtrlSala getCtrlSala() {
        return CtrlSala.getInstancia();

    }

}
