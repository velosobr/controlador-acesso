/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPessoa;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
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
            System.out.println("4 - Liberar acesso à sala");
            System.out.println("5 - Deletar acesso à sala");
            System.out.println("6 - Listar salas cadastras na pessoa");
            System.out.println("7 - Encontrar pessoa pela matrícula");
            System.out.println("8 - Voltar para o menu anterior");

            System.out.println("99 - Sair");

            int opcao = validador.recebeValorInteiro("--- Escolha uma das opcoes acima e tecle enter. ---");
            switch (opcao) {
                case (1):

                    incluirAluno();
                    inicio();
                case (2):
                    cadastraServidor();
                    this.inicio();
                case (3):
                    deletaPessoa();
                    inicio();
                case (4):
                    liberarAcessoSala();
                    inicio();
                case (5):
                    deletarAcessoSala();
                    inicio();
                case (6):
                    listaSalasCadastradasPessoa();
                    inicio();
                case (7):
                    encontraPessoaByMatricula();
                case (8):
                    ctrlPessoa.getCtrlPrincipal().abreTelaAdm();
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

    private void incluirAluno() {
        System.out.println("---");
        System.out.println("Informações de cadastro do aluno");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula");
        String nome = validador.recebeValorString("Digite o nome do aluno");
        int telefone = validador.recebeValorInteiro("Digite o telefone do aluno sem caracteres especiais. Ex: 984841234");
        String email = validador.recebeValorString("Digite o email do aluno");
        String curso = validador.recebeValorString("Digite o curso que o aluno pertence");
        ctrlPessoa.incluiAluno(matricula, nome, telefone, email, curso);

    }

    private void cadastraServidor() {
        System.out.println("---");
        System.out.println("Informações de cadastro do Servidor");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula do Servidor");
        String nome = validador.recebeValorString("Digite o nome do Servidor");
        int telefone = validador.recebeValorInteiro("Digite o telefone do Servidor sem caracteres especiais. Ex: 984841234");
        String email = validador.recebeValorString("Digite o email do Servidor");
        String cargo = validador.recebeValorString("Digite o cargo do servidor");
        Boolean administrador = validador.recebeValorBoolean("O Servidor possui acesso de administrador");
        ctrlPessoa.incluiServidor(matricula, nome, telefone, email, cargo, administrador);
    }

    private void deletaPessoa() {
        System.out.println("---");
        System.out.println("Informações para exclusão de Pessoa");
        System.out.println("---");

        ctrlPessoa.delPessoa(validador.recebeValorInteiro("Digite a matricula da pessoa que você quer excluir"));
    }

    private void liberarAcessoSala() {
        System.out.println("---");
        System.out.println("Informações para liberação de acesso da Pessoa à Sala");
        System.out.println("---");

        ctrlPessoa.cadastraSalaNaPessoa(validador.recebeValorInteiro("Digite matricula da Pessoa"), validador.recebeValorString("Digite o código da Sala"));
    }

    private void deletarAcessoSala() {
        System.out.println("---");
        System.out.println("Informações para exclusão de Pessoa");
        System.out.println("---");

        ctrlPessoa.delSalaNaPessoa(validador.recebeValorInteiro("Digite matricula da Pessoa"), validador.recebeValorString("Digite o código da Sala"));
    }

    private void listaSalasCadastradasPessoa() {
        System.out.println("---");
        System.out.println("Informações para exclusão de Pessoa");
        System.out.println("---");

        System.out.println(ctrlPessoa.listaSalasCadastradas(validador.recebeValorInteiro("Digite a matricula da Pessoa")));
    }

    private void encontraPessoaByMatricula() {
        System.out.println("---");
        System.out.println("Informações para exclusão de Pessoa");
        System.out.println("---");
        Pessoa pessoa = ctrlPessoa.findPessoaByMatricula(validador.recebeValorInteiro("Digite a matricula da Pessoa"));
        String nome = pessoa.getNome();
        System.out.println("O nome encontrado com este matricula é: "+nome);
    }

}
