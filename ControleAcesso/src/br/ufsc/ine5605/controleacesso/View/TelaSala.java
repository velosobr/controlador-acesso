/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlSala;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.util.Scanner;

/**
 *
 * @author Lino Veloso
 */
public class TelaSala {

    private final CtrlSala ctrlSala;
    private final Scanner teclado;
    private final ValidaERetorna validador;

    public TelaSala(CtrlSala ctrlSala) {
        this.ctrlSala = ctrlSala;
        teclado = new Scanner(System.in);
        this.validador = new ValidaERetorna(teclado);
    }

    public CtrlSala getCtrlAcesso() {
        return ctrlSala;
    }

    public void inicio() {
        System.out.println("---TELA DE GERENCIAMENTO DE SALAS---");
        System.out.println(" ");
        try {
            System.out.println("1 - Incluir sala");
            System.out.println("2 - Deletar sala");
            System.out.println("3 - Encontrar sala pelo código");
            System.out.println("4 - Gerenciamento de acesso - Incluir pessoa na sala");
            System.out.println("5 - Gerenciamento de acesso - Deleta pessoa na sala");
            System.out.println("6 - Listar pessoas cadastradas na sala");
           
            System.out.println("7 - Voltar para o menu anterior");

            System.out.println("99 - Sair");

            int opcao = validador.recebeValorInteiro("--- Escolha uma das opcoes acima e tecle enter. ---");
            switch (opcao) {
                case (1):
                    incluirSala();
                    inicio();
                case (2):
                    deletaSala();
                    inicio();
                case (3):
                    encontraSalaPorCodigo();
                    teclado.nextLine();
                    inicio();
                case (4):
                    cadastraPessoaNaSala();
                    inicio();
                case (5):
                    deletaPessoaNaSala();
                    inicio();
                case (6):
                    listaPessoasCadastradasNaSala();
                    inicio();
                case (7):
                    ctrlSala.getCtrlPrincipal().abreTelaAdm();
                case (99):
                    System.exit(0);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            String[] args = null;
            this.inicio();

        } finally {
            teclado.nextLine();
        }
    }

    private void incluirSala() {
        System.out.println("---");
        System.out.println("Informações de cadastro da sala");
        System.out.println("---");
        String codigoSala = validador.recebeValorString("Digite o código da sala");
        int numero = validador.recebeValorInteiro("Digite o número da sala");
        char bloco = validador.recebeValorChar("Digite bloco da sala");
        String centro = validador.recebeValorString("Digite o centro da sala");
        String campus = validador.recebeValorString("Digite o campus que a sala fica localizada");

        ctrlSala.addSala(codigoSala, numero, bloco, centro, campus);
    }

    private void deletaSala() {
        System.out.println("---");
        System.out.println("Informações para exclusão de Sala");
        System.out.println("---");
        String codigoSala = validador.recebeValorString("Digite o código da sala");
        ctrlSala.delSala(codigoSala);
    }

    private void encontraSalaPorCodigo() {
        System.out.println("---");
        System.out.println("Informação para encontrar sala e mostrar suas informações na tela");
        System.out.println("---");
        String codigoSala = validador.recebeValorString("Digite o código da sala");

        Sala sala = null;
        if (ctrlSala.findSalaByCodigoSala(codigoSala) != null) {
            sala = ctrlSala.findSalaByCodigoSala(codigoSala);
        } else {
            System.out.println("Não foi encontrado uma sala com o código digitado. Tente novamente com um código válido");
            teclado.nextLine();
            inicio();
        }
        System.out.println("Numero da sala: " + sala.getNumero());
        System.out.println("Bloco da sala: " + sala.getBloco());
        System.out.println("Centro academico: " + sala.getCentro());
        System.out.println("Campus: " + sala.getCampus());
    }

    private void cadastraPessoaNaSala() {
        System.out.println("---");
        System.out.println("Informações para inclusão de acesso da pessoa na sala");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula");
        String codigoSala = validador.recebeValorString("Digite o código da sala");
        ctrlSala.cadastraPessoaNaSala(matricula, codigoSala);
        
        System.out.println("Inclusão feita com sucesso");
        teclado.nextLine();

    }

    private void deletaPessoaNaSala() {
        System.out.println("---");
        System.out.println("Informações para exclusão de acesso da pessoa na sala");
        System.out.println("---");
        int matricula = validador.recebeValorInteiro("Digite a matricula");
        String codigoSala = validador.recebeValorString("Digite o código da sala");
        ctrlSala.deletaPessoaNaSala(matricula, codigoSala);
    }

    private void listaPessoasCadastradasNaSala() {
        System.out.println("---");
        System.out.println("Informações para listar pessoas cadastradas na sala");
        System.out.println("---");
        String codigoSala = validador.recebeValorString("Digite o código da sala");
        String listaDePessoas = ctrlSala.listaPessoasCadastradas(codigoSala);
        System.out.println("Estas são as pessoas cadastradas na sala: ");
        System.out.println(listaDePessoas);
    }

}
