package lofilmes.servicos;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import lofilmes.modelos.Cliente;
import lofilmes.modelos.Filme;
import lofilmes.utilidades.GerenciadorEntradas;

public class ServicosLocacao {
    private GerenciadorClientes gerenciadorClientes;
    private HistoricoLocacoes historicoLocacoes;
    private GerenciadorEntradas gerenciadorEntradas;
    private Scanner scan;

    public ServicosLocacao(Scanner s, GerenciadorClientes gc, HistoricoLocacoes hl, GerenciadorEntradas ge) {
        this.scan = s;
        this.gerenciadorClientes = gc;
        this.historicoLocacoes = hl;
        this.gerenciadorEntradas = ge;
    }

    public void alugarFilme(CatalogoFilmes catalogo) {
        List<Filme> listaFilmes = catalogo.getListaFilmes();
        LocalDate data = LocalDate.now();

        // coleta de dados feita com o gerenciador de entradas
        String cpf = gerenciadorEntradas.solicitarCpf();
        String[] nomeSobrenome = gerenciadorEntradas.solicitarNomeSobrenome();
        Long id = gerenciadorEntradas.gerarIdAleatorio();

        // após os dados coletados, cria o cliente com a classe responsavel 
        // pelo gerenciamento do cliente
        Cliente cliente = gerenciadorClientes.criarCliente(cpf, nomeSobrenome[0], nomeSobrenome[1], id);

        int opcaoFilme = getOpcaoFilme(listaFilmes);
        int diasAlugado = getDiasAlugado();

        Filme filmeEscolhido = listaFilmes.get(opcaoFilme - 1);
        System.out.println("Filme '" + filmeEscolhido.getTitulo() + "' escolhido com sucesso!");

        double precoTotal = getPrecoTotal(filmeEscolhido.getPrecoLocacao(), diasAlugado);
        historicoLocacoes.salvar(cliente, precoTotal, data, filmeEscolhido, diasAlugado);
        System.out.println("Aluguel realizado com sucesso! Preço total: R$ " + precoTotal);
    }

    private int getOpcaoFilme(List<Filme> listaFilmes) {
        while (true) {
            try {
                System.out.println("Qual filme você gostaria de alugar? (Digite o número):");
                int opcaoFilme = scan.nextInt();
                scan.nextLine();

                if (!isFilmeValido(opcaoFilme, listaFilmes.size())) {
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }
                return opcaoFilme;
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, insira um número.");
                scan.nextLine();
            }
        }
    }

    private int getDiasAlugado() {
        while (true) {
            try {
                System.out.println("Por quantos dias deseja alugar o filme?");
                int diasAlugado = scan.nextInt();
                scan.nextLine();

                if (diasAlugado <= 0) {
                    System.out.println("Número de dias inválido. Tente novamente.");
                    continue;
                }
                return diasAlugado;
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor, insira um número.");
                scan.nextLine();
            }
        }
    }

    private double getPrecoTotal(double precoLocacao, int diasAlugados) {
        return precoLocacao * diasAlugados;
    }

    private boolean isFilmeValido(int opcaoFilme, int tamanhoLista) {
        return opcaoFilme > 0 && opcaoFilme <= tamanhoLista;
    }
}
