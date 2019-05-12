/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.interfaces.ICtrlPessoa;
import java.util.ArrayList;

/**
 *
 * @author Linnety3
 */
public class CtrlPessoa implements ICtrlPessoa{

    CtrlPessoa(CtrlPrincipal aThis) {
    }

    @Override
    public Pessoa incluirAluno(String curso, int matricula, int telefone, String nome, String email, ArrayList<Sala> listaSalas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa incluirServidor(boolean administrador, String cargo, int matricula, int telefone, String nome, String email, ArrayList<Sala> listaSalas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delPessoa(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    

    
}
