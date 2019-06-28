/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Exceptions.MatriculaJahExisteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linnety3
 */
public class ControleAcesso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        CtrlPrincipal controle = new CtrlPrincipal();
        try {
//            //TESTES NO SISTEMA
//            //ALUNOS
//            //controle.getCtrlPessoa().incluiAluno(5, "Karla", 84585858, "karla@gmail.com", "enfermagem");
//            //controle.getCtrlPessoa().incluiAluno(6, "larissa", 123421312, "larissa@ufsc.br", "nutricao");
//            //controle.getCtrlPessoa().incluiAluno(7, "Maria Clara", 984255835, "maria.clara@ufsc.br", "aplicacao");
//            //SERVIDORES
            controle.getCtrlPessoa().incluiServidor(0, "USUARIOMASTER", 11111111, "MASTER@gmail.com", "administrador", true);
//            //controle.getCtrlPessoa().incluiServidor(2, "caio", 9123123, "caionoguerol@gmail.com", "administrador", true);
//            //controle.getCtrlPessoa().incluiServidor(3, "joao", 99998888, "joao@gmail.com", "limpeza", false);
//            //controle.getCtrlPessoa().incluiServidor(4, "duda", 84841111, "duda@gmail.com", "aux de limpeza", false);
        } catch (MatriculaJahExisteException ex) {
            Logger.getLogger("WTF is that?" + ControleAcesso.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SALAS
//        controle.getCtrlSala().addSala("1", 101, 'a', "ine", "fln");
//        controle.getCtrlSala().addSala("2", 102, 'a', "ine", "fln");
//        controle.getCtrlSala().addSala("3", 103, 'a', "ine", "fln");
//        controle.getCtrlSala().addSala("4", 101, 'b', "ine", "fln");
//        
        controle.abreTelaInicial();
    }

}
