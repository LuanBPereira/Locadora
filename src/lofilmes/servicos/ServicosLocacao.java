package lofilmes.servicos;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import lofilmes.modelos.Cliente;
import lofilmes.modelos.Filme;

public class ServicosLocacao {
	private GerenciadorClientes gerenciadorClientes;
	private HistoricoLocacoes historicoLocacoes;
	private Scanner scan;

	public ServicosLocacao(Scanner s, GerenciadorClientes gc, HistoricoLocacoes hl) {
		this.scan = s;
		this.gerenciadorClientes = gc;
		this.historicoLocacoes = hl;
	}

	public void alugarFilme(CatalogoFilmes catalogo) {
		List<Filme> listaFilmes = catalogo.getListaFilmes();
		LocalDate data = LocalDate.now();

		Cliente cliente = gerenciadorClientes.criarCliente();
		System.out.println("Cliente cadastrado/recuperado: " + cliente);

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
				System.out.println("Qual filme você gostaria de alugar? (Digite de acordo com o número mostrado no catálogo)");
				int opcaoFilme = scan.nextInt();
				scan.nextLine();

				if (!isFilmeValido(opcaoFilme, listaFilmes.size())) {
					System.out.println("Opção indisponível. Tente novamente.");
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
				System.out.println("Por quantos dias você quer alugar o filme?");
				int diasAlugado = scan.nextInt();
				scan.nextLine();

				if (!isDiasValido(diasAlugado)) {
					System.out.println("Número de dias inválido. Deve ser maior que 0.");
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

	private boolean isDiasValido(int diasAlugado) {
		return diasAlugado > 0;
	}

	private boolean isFilmeValido(int opcaoFilme, int tamanhoLista) {
		return opcaoFilme > 0 && opcaoFilme <= tamanhoLista;
	}
}
