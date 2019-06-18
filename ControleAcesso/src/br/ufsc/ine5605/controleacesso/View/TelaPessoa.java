/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPessoa;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Servidor;
import br.ufsc.ine5605.controleacesso.Persistencia.PessoaDAO;
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
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar servidor");
            System.out.println("3 - Deletar cadastro no sistema");
            System.out.println("4 - Alterar cadastro de pessoa");
            System.out.println("5 - Gerenciamento de acesso - Incluir sala na pessoa");
            System.out.println("6 - Gerenciamento de acesso - Deletar sala na pessoa");
            System.out.println("7 - Listar salas cadastradas na pessoa");
            System.out.println("8 - Listar todas as pessoas cadastradas no sistema");
            System.out.println("9 - Encontrar pessoa pela matricula");

            System.out.println("10 - Voltar para o menu anterior");

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
                    alteraCadastroPessoa();
                    inicio();
                case (5):
                    cadastraSalaNaPessoa();

                    inicio();
                case (6):
                    deletaSalaNaPessoa();
                    inicio();
                case (7):
                    listaSalasCadastradasPessoa();
                    teclado.nextLine();
                    inicio();
                case (8):
                    listAllPessoasCadastradas();
                    teclado.nextLine();
                    inicio();
                case (9):
                    encontraPessoaByMatricula();
                    teclado.nextLine();
                    inicio();
                case (10):
                    ctrlPessoa.getCtrlPrincipal().abreTelaAdm();
                case (99):
                    System.exit(0);

                default:
                    throw new IllegalArgumentException("Opcao invalida! Escolha uma opcao dentre as opcoes da lista.");

            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            String[] args = null;
            teclado.nextLine();
            this.inicio();

        } finally {
            teclado.nextLine();
        }
    }

    private void incluiAluno() {
        System.out.println("---");
        System.out.println("Informacoes de cadastro do aluno");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula");
        String nome = validador.recebeValorString("Digite o nome do aluno");
        long telefone = validador.recebeValorLong("Digite o telefone do aluno sem caracteres especiais. Ex: 984841234");
        String email = validador.recebeValorString("Digite o email do aluno");
        String curso = validador.recebeValorString("Digite o curso que o aluno pertence");
        if(ctrlPessoa.incluiAluno(matricula, nome, telefone, email, curso)){
         System.out.println("Cadastro feito com sucesso!");
        }else{
            System.out.println("Cadastro nao realizado, matricula ja cadastrada!");
        }


    }

    private void incluiServidor() {
        System.out.println("---");
        System.out.println("Informacoes de cadastro do servidor");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula do servidor");
        String nome = validador.recebeValorString("Digite o nome do servidor");
        long telefone = validador.recebeValorLong("Digite o telefone do servidor sem caracteres especiais. Ex: 984841234");
        String email = validador.recebeValorString("Digite o email do servidor");
        String cargo = validador.recebeValorString("Digite o cargo do servidor");
        Boolean administrador = validador.recebeValorBoolean("O servidor possui acesso de administrador");
        if(ctrlPessoa.incluiServidor(matricula, nome, telefone, email, cargo, administrador)){
            System.out.println("Cadastro feito com sucesso!");
        }else{
            System.out.println("Cadastro nao realizado, matricula ja cadastrada!");
        }
    }

    private void deletaPessoa() {
        System.out.println("---");
        System.out.println("Informacoes para exclusao de Pessoa");
        System.out.println("---");

        if(ctrlPessoa.delPessoa(validador.recebeValorInteiro("Digite a matricula da pessoa que deseja excluir"))){
            System.out.println("Exclusao feita com sucesso");
        }else{
            System.out.println("Nao foi possivel excluir, matricula invalida!");
        }
    }
    
    private void alteraCadastroPessoa(){
        System.out.println("---");
        System.out.println("Informacoes de cadastro da sala");
        System.out.println("---");
        
        int matricula = validador.recebeValorInteiro("Digite a matricula da pessoa");
        if(PessoaDAO.getInstancia().getPessoa(matricula) instanceof Servidor){
            String nome = validador.recebeValorString("Digite o nome do servidor");
            long telefone = validador.recebeValorLong("Digite o telefone do servidor sem caracteres especiais. Ex: 984841234");
            String email = validador.recebeValorString("Digite o email do servidor");
            String cargo = validador.recebeValorString("Digite o cargo do servidor");
            boolean administrador = validador.recebeValorBoolean("O servidor possui acesso de administrador");
            ctrlPessoa.alteradorDeCadastroServidor(matricula, nome, telefone, email, cargo, administrador);
            System.out.println("Alteracao de cadastro realizada!");
        }else{
            String nome = validador.recebeValorString("Digite o nome do aluno");
            long telefone = validador.recebeValorLong("Digite o telefone do aluno sem caracteres especiais. Ex: 984841234");
            String email = validador.recebeValorString("Digite o email do aluno");
            String curso = validador.recebeValorString("Digite o cursos do aluno");
            ctrlPessoa.alteradorDeCadastroAluno(matricula, nome, telefone, email, curso);
            System.out.println("Alteracao de cadastro realizada!");
        }
    }

    private void cadastraSalaNaPessoa() {
        System.out.println("---");
        System.out.println("Informacoes para incluir sala na pessoa");
        System.out.println("---");

        if (ctrlPessoa.cadastraSalaNaPessoa(validador.recebeValorInteiro("Digite matricula da pessoa"), validador.recebeValorString("Digite o código da Sala"))) {
            System.out.println("Inclusao feita com sucesso");
        } else {
            System.out.println("Nao foi possivel cadastrar sala na pessoa");
        }

        teclado.nextLine();
    }

    private void deletaSalaNaPessoa() {
        System.out.println("---");
        System.out.println("Informacoes para exclusao de pessoa");
        System.out.println("---");

        if (ctrlPessoa.delSalaNaPessoa(validador.recebeValorInteiro("Digite matricula da pessoa"), validador.recebeValorString("Digite o código da Sala"))) {
            System.out.println("Exclusao efetuada com sucesso");
        } else {
            System.out.println("Nao foi possivel deletar acesso a sala na pessoa. Tente novamente");
        }
    }

    private void listaSalasCadastradasPessoa() {
        System.out.println("---");
        System.out.println("Lista quais salas cadastradas no sistema");
        System.out.println("---");

        System.out.println(ctrlPessoa.listaSalasCadastradas(validador.recebeValorInteiro("Digite a matricula da Pessoa")));
    }

    private void listAllPessoasCadastradas() {
        System.out.println("---");
        System.out.println("Lista todas as pessoas cadastradas no sistema");
        System.out.println("---");
        System.out.println(ctrlPessoa.listAllPessoasCadastradas());
    }

    private void encontraPessoaByMatricula() {
        System.out.println("---");
        System.out.println("Encontra uma pessoa atraves da matricula");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula da pessoa");
        Pessoa pessoa = PessoaDAO.getInstancia().getPessoa(matricula);
        String nome = pessoa.getNome();
        String email = pessoa.getEmail();
        long telefone = pessoa.getTelefone();
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("");
    }

}
