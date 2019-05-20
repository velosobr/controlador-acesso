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
            System.out.println("4 - Gerenciamento de acesso - Incluir sala na pessoa");
            System.out.println("5 - Gerenciamento de acesso - Deletar sala na pessoa");
            System.out.println("6 - Listar salas cadastradas na pessoa");
            System.out.println("7 - Listar todas as pessoas cadastradas no sistema");
            System.out.println("8 - Encontrar pessoa pela matrícula");

            System.out.println("9 - Voltar para o menu anterior");

            System.out.println("99 - Sair");

            int opcao = validador.recebeValorInteiro("--- Escolha uma das opcoes acima e tecle enter. ---");
            switch (opcao) {
                case (1):

                    incluiAluno();
                    inicio();
                case (2):
                    incluiServidor();
                    this.inicio();
                case (3):
                    deletaPessoa();
                    inicio();
                case (4):
                    cadastraSalaNaPessoa();
                    teclado.nextLine();
                    inicio();
                case (5):
                    deletarAcessoSala();
                    inicio();
                case (6):
                    listaSalasCadastradasPessoa();
                    teclado.nextLine();
                    inicio();
                case (7):
                    listAllPessoasCadastradas();
                    teclado.nextLine();
                    inicio();
                case (8):
                    encontraPessoaByMatricula();
                    teclado.nextLine();
                    inicio();
                case (9):
                    ctrlPessoa.getCtrlPrincipal().abreTelaAdm();
                case (99):
                    System.exit(0);

                default:
                    throw new IllegalArgumentException("Opção inválida! Escolha uma opção dentre as opções da lista.");

            }
        } catch (IllegalArgumentException e) {
            System.out.println("Aqui que a contece a bagaça toda: "+e.getMessage());
            String[] args = null;
            this.inicio();

        } finally {
            teclado.nextLine();
        }
    }

    private void incluiAluno() {
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

    private void incluiServidor() {
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

    private void cadastraSalaNaPessoa() {
        System.out.println("---");
        System.out.println("Informações para Incluir sala na pessoa");
        System.out.println("---");

        ctrlPessoa.cadastraSalaNaPessoa(validador.recebeValorInteiro("Digite matricula da Pessoa"), validador.recebeValorString("Digite o código da Sala"));
        System.out.println("Inclusão feita com sucesso");
        teclado.nextLine();
    }

    private void deletarAcessoSala() {
        System.out.println("---");
        System.out.println("Informações para exclusão de Pessoa");
        System.out.println("---");

        ctrlPessoa.delSalaNaPessoa(validador.recebeValorInteiro("Digite matricula da Pessoa"), validador.recebeValorString("Digite o código da Sala"));
    }

    private void listaSalasCadastradasPessoa() {
        System.out.println("---");
        System.out.println("Lista quais salas cadastradas no sistema");
        System.out.println("---");

        System.out.println("Codigo de sala:  " + ctrlPessoa.listaSalasCadastradas(validador.recebeValorInteiro("Digite a matricula da Pessoa")));
    }

    private void listAllPessoasCadastradas() {
        System.out.println("---");
        System.out.println("Lista todas as pessoas cadastradas no sistema");
        System.out.println("---");
        System.out.println(ctrlPessoa.listAllPessoasCadastradas());
    }

    private void encontraPessoaByMatricula() {
        System.out.println("---");
        System.out.println("encontra uma pessoa atraves da matricula, e mostra qual o nome");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula da Pessoa");
        Pessoa pessoa = ctrlPessoa.findPessoaByMatricula(matricula);
        String nome = pessoa.getNome();
        String email = pessoa.getEmail();
        int telefone = pessoa.getTelefone();
        System.out.println("O nome encontrado com a matricula " + matricula + " é: " + nome);
        System.out.println("O telefone é: " + telefone);
        System.out.println("O email é: " + email);
        System.out.println("");
    }

}
