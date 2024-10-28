package lofilmes.servicos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import lofilmes.modelos.DadosLocacao;
import lofilmes.modelos.Filme;

public class Consultas {

	private Scanner scan;
	private CatalogoFilmes catalogoFilmes;
	
	public Consultas(Scanner scan, CatalogoFilmes catalogoFilmes) {
		this.scan = scan;
		this.catalogoFilmes = catalogoFilmes;
	}

	public void consultarFilmePorTitulo() {
		List<Filme> filmesEncontrados = new ArrayList<>();
		String titulo;

		System.out.println("Qual filme você gostaria de procurar pelo título?");
		titulo = scan.nextLine().toLowerCase();

		for (Filme f : catalogoFilmes.getListaFilmes()) {
			if (f.getTitulo().toLowerCase().contains(titulo)) {
				filmesEncontrados.add(f);
			}
		}

		catalogoFilmes.listarFilmes(filmesEncontrados);
	}

	public void consultarFilmePorDiretor() {
		List<Filme> filmesEncontrados = new ArrayList<>();
		String diretor;

		System.out.println("Qual filme você gostaria de procurar pelo diretor?");
		diretor = scan.nextLine().toLowerCase();

		for (Filme f : catalogoFilmes.getListaFilmes()) {
			if (f.getDiretor().toLowerCase().contains(diretor)) {
				filmesEncontrados.add(f);
			}
		}
		catalogoFilmes.listarFilmes(filmesEncontrados);
	}

	public void consultarFilmePorCategoria() {
		List<Filme> filmesEncontrados = new ArrayList<>();
		String categorias;

		System.out.println("Qual filme você gostaria de procurar por categoria?");
		categorias = scan.nextLine().toLowerCase();

		for (Filme f : catalogoFilmes.getListaFilmes())
			for (String categoria : f.getCategorias())
				if (categoria.toLowerCase().contains(categorias)) {
					filmesEncontrados.add(f);
				}
		catalogoFilmes.listarFilmes(filmesEncontrados);
	}

	public void consultarFilmePorPreco() {
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
				catalogoFilmes.listarFilmes(filmesEncontrados);
				break;
			} catch (InputMismatchException e) {
				System.err.println("Erro: Entrada inválida. Insira apenas números no preço.");
				scan.nextLine();
			}
		}
	}

	public void consultarFilmesLocados7Dias(HistoricoLocacoes historico) {
		List<DadosLocacao> listaHistorico = historico.getFilmesLocadosNosUltimos7Dias();
		System.out.println("Filmes locados nos últimos 7 dias: \n");

		for (DadosLocacao dadosL : listaHistorico) {
			System.out.println("Filme: " + dadosL.filme().getTitulo() + ", Data: " + dadosL.data());
		}
	}

	public void consultarFilmeMaisLocado(HistoricoLocacoes historico) {
		List<String> filmesMaisLocados = historico.getFilmesMaisLocados();
		System.out.println("Filmes mais locados: \n");
		for (String filmes : filmesMaisLocados)
			System.out.println("Filme: " + filmes);
	}

	public void consultarValorTotalLocacoesUltimoMes(HistoricoLocacoes historico) {
		double valorTotalLocacoes = historico.getValorTotalLocacoesUltimoMes();
		System.out.println("Valor total das locações do último mês: R$" + valorTotalLocacoes);
	}

	public void consultarClienteQueMaisLocou(HistoricoLocacoes historico, GestaoClientes gestaoClientes) {
		List<String> clienteQueMaisLocou = historico.getClienteQueMaisLocou(gestaoClientes);

		if (clienteQueMaisLocou.size() == 1) {
			for (String cliente : clienteQueMaisLocou) {
				System.out.println("Cliente que mais locou: " + cliente);
			}
		} else {
			System.out.println("Clientes que mais locaram:");
			for (String cliente : clienteQueMaisLocou) {
				System.out.println("Cliente: " + cliente);
			}
		}
	}

	public void consultarHistorico(HistoricoLocacoes historico) {
		List<DadosLocacao> listaHistorico = historico.getHistorico();

		if (listaHistorico.isEmpty())
			System.out.println("Nenhum filme alugado.");
		else
			System.out.println("Histórico de locações:\n");
		for (DadosLocacao alugados : listaHistorico) {
			System.out.printf("id: %d,CPF: %s, %s %s, %s, R$%.2f, %s, Dias alugados: %d%n%n",
					alugados.cliente().getId(),
					alugados.cliente().getCPF(),
					alugados.cliente().getNome(),
					alugados.cliente().getSobrenome(),
					alugados.filme().getTitulo(),
					alugados.valorPago(),
					alugados.data(),
					alugados.diasAlugado());
		}
	}

}