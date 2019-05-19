/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.util.Scanner;

/**
 *
 * @author Lino Veloso
 */
public class TelaPrincipal {

    private final CtrlPrincipal ctrlPrincipal;
    private final ValidaERetorna validador;

    private final Scanner teclado;

    public TelaPrincipal(CtrlPrincipal ctrlprincipal) {
        this.ctrlPrincipal = ctrlprincipal;
        this.teclado = new Scanner(System.in);
        this.validador = new ValidaERetorna(teclado);
    }

    public CtrlPrincipal getCtrlPrincipal() {
        return ctrlPrincipal;
    }

    public void inicio() {
        System.out.println("#### BEM VINDO AO SISTEMA ####");
        System.out.println(" ");
        try {
            System.out.println("1 - Acessar a porta");
            System.out.println("2 - Acessar a tela gerencial");
            System.out.println("9 - Sair");

            int opcao = validador.recebeValorInteiro("--- Escolha uma das opcoes acima e tecle enter. ---");
            switch (opcao) {
                case (1):

                    System.out.println("Digite a sua matricula");
                    int matricula = validador.recebeValorInteiro();

                    System.out.println("Digite o codigo da sala");
                    String codSala = validador.recebeValorString();

                    if (validacaoPorta(matricula, codSala)) {
                        System.out.println("Porta aberta com sucesso");
                    } else {
                        System.out.println("Você não possui acesso a esta porta, procure um administrador de sistema");
                      teclado.nextLine();
                        this.inicio();
                    }

                    break;

                case (2):

                    int matriculaadm = validador.recebeValorInteiro("Digite a sua matricula");

                    if (validacaoTelaAdm(matriculaadm)) {
                        System.out.println("Login efetuado com sucesso");
                        this.ctrlPrincipal.getTelaAdm().inicio();
                    } else {
                        System.out.println("Usuário não possui acesso a tela Adm");
                        System.out.println("Tente novamente");
                        System.out.println("");
                        ctrlPrincipal.abreTelaInicial();
                    }
                    break;
                case (9):
                    System.exit(0);

                default:
                    throw new IllegalArgumentException("Opção inválida! Escolha uma opção dentre as opções da lista.");

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            String[] args = null;
            this.inicio();

        } finally {
            teclado.nextLine();
        }
    }

    private boolean validacaoPorta(int matricula, String codSala) {
        return ctrlPrincipal.getCtrlAcesso().ehLiberadoAcesso(matricula, codSala);
    }

    private boolean validacaoTelaAdm(int matricula) {
        boolean ehAdm = false;

        if (ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula) instanceof Servidor) {
            Servidor servidor = (Servidor) ctrlPrincipal.getCtrlPessoa().findPessoaByMatricula(matricula);
            ehAdm = servidor.isAdministrador();
        } else {
            throw new IllegalArgumentException("A matriculada digitada não pertence a um servidor, somente servidores podem acessar a tela gerencial");
        }
        return ehAdm;
    }

}
