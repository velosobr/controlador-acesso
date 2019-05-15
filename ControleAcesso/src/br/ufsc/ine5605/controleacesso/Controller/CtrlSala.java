/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Controller;

import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.View.TelaSala;
import br.ufsc.ine5605.controleacesso.interfaces.ICtrlSala;
import java.util.ArrayList;

/**
 *
 * @author Linnety3
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
        salas.remove(salaParaDeletar);
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
        
    }

    @Override
    public void deletaPessoaNaSala(int matricula, String codigoSala) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listaPessoasCadastradas(String codigoSala) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
