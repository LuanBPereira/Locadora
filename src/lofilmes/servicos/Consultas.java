package lofilmes.servicos;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import lofilmes.modelos.DadosLocacao;
import lofilmes.modelos.Filme;

public class Consultas {
	private CatalogoFilmes catalogoFilmes;
	private HistoricoLocacoes historico;
	private Scanner scan;
	
	public Consultas(Scanner s, HistoricoLocacoes h, CatalogoFilmes cf) {
		this.scan = s;
		this.historico = h;
		this.catalogoFilmes = cf;
	}
	
	
	public void consultarFilmePorTitulo(DateTimeFormatter dataFormatada) {
		List<Filme> filmesEncontrados = new ArrayList<>();
		String titulo;

		System.out.println("Qual filme você gostaria de procurar pelo titulo?");
		titulo = scan.nextLine().toLowerCase();

		for (Filme f : catalogoFilmes.getListaFilmes()) {
			if (f.getTitulo().toLowerCase().contains(titulo)) {
				filmesEncontrados.add(f);
			}
		}

		catalogoFilmes.listarFilmes(filmesEncontrados, dataFormatada);
	}

	public void consultarFilmePorDiretor(DateTimeFormatter dataFormatada) {
		List<Filme> filmesEncontrados = new ArrayList<>();
		String diretor;

		System.out.println("Qual filme você gostaria de procurar pelo diretor?");
		diretor = scan.nextLine().toLowerCase();

		for (Filme f : catalogoFilmes.getListaFilmes()) {
			if (f.getDiretor().toLowerCase().contains(diretor)) {
				filmesEncontrados.add(f);
			}
		}
		catalogoFilmes.listarFilmes(filmesEncontrados, dataFormatada);
	}

	public void consultarFilmePorCategoria(DateTimeFormatter dataFormatada) {
		List<Filme> filmesEncontrados = new ArrayList<>();
		String categorias;

		System.out.println("Qual filme você gostaria de procurar por categoria?");
		categorias = scan.nextLine().toLowerCase();

		for (Filme f : catalogoFilmes.getListaFilmes())
			for (String categoria : f.getCategorias())
				if (categoria.toLowerCase().contains(categorias)) {
					filmesEncontrados.add(f);
				}
		catalogoFilmes.listarFilmes(filmesEncontrados, dataFormatada);
	}

	public void consultarFilmePorPreco(DateTimeFormatter dataFormatada) {
		List<Filme> filmesEncontrados = new ArrayList<>();
		double precoMin, precoMax;

		while (true) {
			try {
				System.out.println("Informe o preço mínimo:");
				precoMin = scan.nextDouble();

				System.out.println("Informe o preço máximo:");
				precoMax = scan.nextDouble();

				if (precoMin > precoMax) {
					System.err.println("Erro: Preço mínimo não pode ser maior que o máximo.");
					continue;
				}

				System.out.println("Filmes entre R$" + precoMin + " e R$" + precoMax + ":\n");

				for (Filme f : catalogoFilmes.getListaFilmes()) {
					if (f.getPrecoLocacao() >= precoMin && f.getPrecoLocacao() <= precoMax) {
						filmesEncontrados.add(f);
					}
				}
				catalogoFilmes.listarFilmes(filmesEncontrados, dataFormatada);
				break;
			} catch (InputMismatchException e) {
				System.err.println("Erro: Entrada inválida. Insira apenas números no preço.");
				scan.nextLine();
			}
		}
	}

	public void consultarFilmesLocados7Dias(DateTimeFormatter dataFormatada) {
		List<DadosLocacao> listaHistorico = historico.getfilmesLocados7Dias();
		System.out.println("Filmes locados nos últimos 7 dias: ");
		for (DadosLocacao dadosL : listaHistorico) {
			System.out.println("Filme: " + dadosL.tituloFilme() + ", Data: " + dadosL.data().format(dataFormatada));
		}
	}

	public void consultarFilmeMaisLocado() {
		List<String> filmesMaisLocados = historico.getFilmesMaisLocados();
		System.out.println("Filmes mais locados: ");
		for (String filmes : filmesMaisLocados)
			System.out.println("Filme: " + filmes);
	}

	public void consultarValorTotalLocacoesUltimoMes() {
		double valorTotalLocacoes = historico.getValorTotalLocacoesUltimoMes();
		System.out.println("Valor total das locações do último mês: R$" + valorTotalLocacoes);
	}

	public void consultarClienteQueMaisLocou() {
		List<String> clienteQueMaisLocou = historico.getClienteQueMaisLocou();

		if (clienteQueMaisLocou.size() == 1) {
			for (String cliente : clienteQueMaisLocou) {
				System.out.println("Cliente que mais locou: " + cliente);
			}

		} else if (clienteQueMaisLocou.size() > 1) {
			System.out.println("Clientes que mais locaram: ");
			for (String cliente : clienteQueMaisLocou) {
				System.out.println("Cliente: " + cliente);
			}
		}
	}

	public void consultarHistorico(DateTimeFormatter dataFormatada) {
		List<DadosLocacao> listaHistorico = historico.getHistorico();

		if (listaHistorico.isEmpty())
			System.out.println("Nenhum filme alugado.");
		else
			System.out.println("Histórico locações:\n");
		for (DadosLocacao alugados : listaHistorico) {
			System.out.printf("%s %s, %s, R$%.2f, %s, Dias alugados: %d%n%n", alugados.cliente().nome(),
					alugados.cliente().sobrenome(), alugados.tituloFilme(), alugados.valorPago(), alugados.data().format(dataFormatada),
					alugados.diasAlugado());
		}

	}
}