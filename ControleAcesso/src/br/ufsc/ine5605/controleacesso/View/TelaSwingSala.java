/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.View;

import br.ufsc.ine5605.controleacesso.Controller.CtrlSala;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import br.ufsc.ine5605.controleacesso.validadores.ValidaERetorna;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Lino Veloso
 */
public class TelaSwingSala extends JFrame {

    private static TelaSwingSala instancia;

    public static TelaSwingSala GetInstancia() {
        if (instancia == null) {
            instancia = new TelaSwingSala();
        }
        return instancia;
    }

    public TelaSwingSala() {
        super("Tela Sala");

        //create a new Panel with gbl manager;
        JPanel panelSala = new JPanel(new GridBagLayout());
        GridBagConstraints constraintsPanel = new GridBagConstraints();

        constraintsPanel.anchor = GridBagConstraints.WEST;
        constraintsPanel.insets = new Insets(10, 10, 10, 10);
        panelSala.setBackground(Color.WHITE);
        panelSala.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Tela sala"));

        setSize(315, 280);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panelSala);

    }

    public CtrlSala getCtrlSala() {
        return CtrlSala.getInstancia();
    }

    /**
     * public void inicio() { System.out.println("---TELA DE GERENCIAMENTO DE
     * SALAS---"); System.out.println(" "); try { System.out.println("1 -
     * Cadastrar sala no sistema"); System.out.println("2 - Deletar sala do
     * sistema"); System.out.println("3 - Alterar cadastro sala");
     * System.out.println("4 - Encontrar sala pelo codigo");
     * System.out.println("5 - Gerenciamento de acesso - Incluir pessoa na
     * sala"); System.out.println("6 - Gerenciamento de acesso - Deletar pessoa
     * na sala"); System.out.println("7 - Listar pessoas cadastradas na sala");
     * System.out.println("8 - Listar todas as salas cadastradas");
     * System.out.println("9 - Voltar para o menu anterior");
     *
     * System.out.println("99 - Sair");
     *
     * int opcao = validador.recebeValorInteiro("--- Escolha uma das opcoes
     * acima e tecle enter. ---"); switch (opcao) { case (1): incluirSala();
     * inicio(); case (2): deletaSala(); inicio(); case (3):
     * alteraCadastroSala(); inicio(); case (4): encontraSalaPorCodigo();
     * teclado.nextLine(); inicio(); case (5): cadastraPessoaNaSala(); inicio();
     * case (6): deletaPessoaNaSala(); inicio(); case (7):
     * listaPessoasCadastradasNaSala(); inicio(); case (8):
     * listAllSalasCadastradas(); inicio(); case (9):
     * ctrlSala.getCtrlPrincipal().abreTelaAdm(); case (99): System.exit(0); } }
     * catch (IllegalArgumentException e) { System.out.println(e.getMessage());
     * String[] args = null; teclado.nextLine(); this.inicio();
     *
     * } finally { teclado.nextLine(); } }
     *
     * private void incluirSala() { System.out.println("---");
     * System.out.println("Informacoes de cadastro da sala");
     * System.out.println("---"); String codigoSala =
     * validador.recebeValorString("Digite o codigo da sala"); int numero =
     * validador.recebeValorInteiro("Digite o numero da sala"); char bloco =
     * validador.recebeValorChar("Digite bloco da sala"); String centro =
     * validador.recebeValorString("Digite o centro da sala"); String campus =
     * validador.recebeValorString("Digite o campus que a sala fica
     * localizada");
     *
     * if (CtrlSala.getInstancia().addSala(codigoSala, numero, bloco, centro,
     * campus)) { System.out.println("Sala cadastrada!"); } else {
     * System.out.println("Nao foi realizado o cadastro, codigo de sala ja
     * cadastrado!"); } }
     *
     * private void deletaSala() { System.out.println("---");
     * System.out.println("Informacoes para exclusao de Sala");
     * System.out.println("---"); String codigoSala =
     * validador.recebeValorString("Digite o codigo da sala"); if
     * (CtrlSala.getInstancia().delSala(codigoSala)) { System.out.println("Sala
     * deletada!"); } else { System.out.println("Nao foi realizado a exclusao,
     * codigo de sala invalido!"); } }
     *
     * private void alteraCadastroSala() { System.out.println("---");
     * System.out.println("Informacoes de cadastro da sala");
     * System.out.println("---"); String codigoSala =
     * validador.recebeValorString("Digite o codigo da sala"); int numero =
     * validador.recebeValorInteiro("Digite o numero da sala"); char bloco =
     * validador.recebeValorChar("Digite bloco da sala"); String centro =
     * validador.recebeValorString("Digite o centro da sala"); String campus =
     * validador.recebeValorString("Digite o campus que a sala fica
     * localizada"); CtrlSala.getInstancia().alteradorDeCadastroSala(codigoSala,
     * numero, bloco, centro, campus); System.out.println("Cadastro de sala
     * alterado!"); }
     *
     * private void encontraSalaPorCodigo() { System.out.println("---");
     * System.out.println("Informacao para encontrar sala e mostrar suas
     * informacoes na tela"); System.out.println("---"); String codigoSala =
     * validador.recebeValorString("Digite o codigo da sala");
     *
     * Sala sala = null; if
     * (CtrlSala.getInstancia().findSalaByCodigoSala(codigoSala) != null) { sala
     * = CtrlSala.getInstancia().findSalaByCodigoSala(codigoSala); } else {
     * System.out.println("Nao foi encontrado uma sala com o codigo digitado.
     * Tente novamente com um codigo valido"); teclado.nextLine(); inicio(); }
     * System.out.println("Numero da sala: " + sala.getNumero());
     * System.out.println("Bloco da sala: " + sala.getBloco());
     * System.out.println("Centro : " + sala.getCentro());
     * System.out.println("Campus: " + sala.getCampus()); }
     *
     * private void cadastraPessoaNaSala() { System.out.println("---");
     * System.out.println("Informacoes para inclusao de acesso da pessoa na
     * sala"); System.out.println("---"); int matricula =
     * validador.recebeValorInteiro("Digite a matricula"); String codigoSala =
     * validador.recebeValorString("Digite o codigo da sala");
     * CtrlSala.getInstancia().cadastraPessoaNaSala(matricula, codigoSala);
     *
     * System.out.println("Inclusao feita com sucesso"); teclado.nextLine();
     *
     * }
     *
     * private void deletaPessoaNaSala() { System.out.println("---");
     * System.out.println("Informacoes para exclusao de acesso da pessoa na
     * sala"); System.out.println("---"); int matricula =
     * validador.recebeValorInteiro("Digite a matricula"); String codigoSala =
     * validador.recebeValorString("Digite o codigo da sala");
     * CtrlSala.getInstancia().deletaPessoaNaSala(matricula, codigoSala);
     * System.out.println("Exclusao feita com sucesso"); teclado.nextLine(); }
     *
     * private void listaPessoasCadastradasNaSala() { System.out.println("---");
     * System.out.println("Informacoes para listar pessoas cadastradas na
     * sala"); System.out.println("---"); String codigoSala =
     * validador.recebeValorString("Digite o codigo da sala"); String
     * listaDePessoas =
     * CtrlSala.getInstancia().listaPessoasCadastradas(codigoSala);
     * System.out.println("Estas sao as pessoas cadastradas na sala: ");
     * System.out.println(listaDePessoas); }
     *
     * private void listAllSalasCadastradas() { System.out.println("---");
     * System.out.println("Lista todas as salas cadastradas no sistema");
     * System.out.println("---");
     * System.out.println(CtrlSala.getInstancia().listAllSalasCadastradas()); }
     */
}
