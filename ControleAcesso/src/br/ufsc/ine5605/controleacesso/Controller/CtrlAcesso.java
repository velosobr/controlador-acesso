/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.Model.Acesso;
import br.ufsc.ine5605.controleacesso.interfaces.ICtrlAcesso;

/**
 *
 * @author Linnety3
 */
public class CtrlAcesso implements ICtrlAcesso {

    CtrlAcesso(CtrlPrincipal aThis) {
    }

    @Override
    public boolean ehLiberadoAcesso(int matricula, String codigoSala) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listaAcesso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
