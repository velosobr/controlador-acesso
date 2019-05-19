/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPessoa;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.util.Scanner;

/**
 *
 * @author Lino Veloso
 */
public class TelaPessoa {

    private final CtrlPessoa ctrlPessoa;
    private final Scanner teclado;
    private final ValidaERetorna validador;

    public TelaPessoa(CtrlPessoa ctrlPessoa) {
        this.ctrlPessoa = ctrlPessoa;
        teclado = new Scanner(System.in);
        this.validador = new ValidaERetorna(teclado);
    }

    public CtrlPessoa getCtrlPessoa() {
        return ctrlPessoa;
    }

    public void inicio() {
        System.out.println("---TELA DE PESSOAS---");
        System.out.println(" ");
        try {

            System.out.println("1 - Incluir aluno");
            System.out.println("2 - Incluir servidor");
            System.out.println("3 - Deletar pessoa no sistema");
            System.out.println("4 - Cadastrar acesso à sala");
            System.out.println("5 - Deletar acesso à sala");
            System.out.println("6 - Listar salas cadastras na pessoa");
            System.out.println("7 - Encontrar pessoa pela matrícula");
            System.out.println("99 - Sair");

            int opcao = validador.recebeValorInteiro("--- Escolha uma das opcoes acima e tecle enter. ---");
            switch (opcao) {
                case (1):
                    System.out.println("---");
                    System.out.println("Informações de cadastro do aluno");
                    System.out.println("---");
                    int matricula = validador.recebeValorInteiro("Digite a matricula");
                    String nome = validador.recebeValorString("Digite o nome do aluno");
                    int telefone = validador.recebeValorInteiro("Digite o telefone do aluno sem caracteres especiais. Ex: 984841234");
                    String email = validador.recebeValorString("Digite o email do aluno");
                    String curso = validador.recebeValorString("Digite o curso que o aluno pertence");
                    ctrlPessoa.incluiAluno(matricula, nome, telefone, email, curso);
                    break;

                case (2):

                    break;
                case (3):

                    break;
                case (4):

                    break;
                case (5):

                    break;
                case (6):

                    break;
                case (7):

                    break;
                case (99):
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

}
