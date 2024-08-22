package lofilmes.servicos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import lofilmes.modelos.Cliente;
import lofilmes.modelos.Filme;

public class ServicosLocacao {
	private GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
	private HistoricoLocacoes historicoLocacoes;
	private Scanner scan;

	public ServicosLocacao(Scanner s, HistoricoLocacoes h) {
		this.scan = s;
		this.historicoLocacoes = h;
	}

	public void alugarFilme(CatalogoFilmes catalogo, DateTimeFormatter FORMATTER) {
		List<Filme> listaFilmes = catalogo.getListaFilmes();
		LocalDate data = LocalDate.now();

		Cliente cliente = gerenciadorClientes.criarCliente();

		try {
			System.out.println(
					"Qual filme você gostaria de alugar? (Digite de acordo com o número mostrado no catálogo)");
			int opcaoFilme = scan.nextInt();
			scan.nextLine();

			if (opcaoFilme <= 0 || opcaoFilme > listaFilmes.size()) {
				System.out.println("Opção indisponível.");
				return;
			}

			System.out.println("Por quantos dias você quer alugar o filme?");
			int diasAlugado = scan.nextInt();
			scan.nextLine();

			if (diasAlugado <= 0) {
				System.out.println("Número de dias inválido.");
				return;
			}

			Filme filmeEscolhido = listaFilmes.get(opcaoFilme - 1);
			System.out.println("Filme '" + filmeEscolhido.getTitulo() + "' escolhido com sucesso!");

			double precoTotal = filmeEscolhido.getPrecoLocacao() * diasAlugado;
			historicoLocacoes.salvar(cliente, precoTotal, data, filmeEscolhido, diasAlugado);
		} catch (InputMismatchException e) {
			System.err.println("Entrada inválida. Por favor, insira um número.");
			scan.nextLine();
		}
	}

}
