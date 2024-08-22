package lofilmes;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import lofilmes.servicos.CatalogoFilmes;
import lofilmes.servicos.Consultas;
import lofilmes.servicos.HistoricoLocacoes;
import lofilmes.servicos.ServicosLocacao;
import lofilmes.utilidades.GerenciadorMenu;

public class Locadora {
	private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Scanner scan = new Scanner(System.in);

	private HistoricoLocacoes historico = new HistoricoLocacoes();
	private CatalogoFilmes catalogoFilmes = new CatalogoFilmes();
	private Consultas consultas = new Consultas(scan, historico, catalogoFilmes);
	private ServicosLocacao servicosLocacao = new ServicosLocacao(scan, historico);

	public static void main(String[] args) {
		new Locadora().executar();
	}

	public void executar() {
		catalogoFilmes.criarFilmes();
		int escolha;
		do {
			escolha = exibirMenuPrincipal();
			executarEscolhaPrincipal(escolha);
		} while (escolha != 5);
	}

	private int exibirMenuPrincipal() {
		return GerenciadorMenu.mostrarMenu("""
				\n\t({[【﻿ＬｏＦｉｌｍｅｓ】]})
				---------------
				Seja bem vindo a LoFilmes! O que desejaria?

				1 - Catalogo de filmes
				2 - Histórico locações
				3 - Consultas
				4 - Alugar filme
				5 - Encerrar programa
				""");
	}

	private void executarEscolhaPrincipal(int escolha) {
		switch (escolha) {
		case 1:
			catalogoFilmes.listarFilmes(catalogoFilmes.getListaFilmes(), FORMATTER);
			break;
		case 2:
			consultas.consultarHistorico(FORMATTER);
			break;
		case 3:
			entrarMenuDeConsultas();
			break;
		case 4:
			servicosLocacao.alugarFilme(catalogoFilmes, FORMATTER);
			break;
		case 5:
			System.out.println("Encerrando programa...");
			break;
		default:
			System.err.println("Opção não disponível.");
			break;
		}
	}

	private void entrarMenuDeConsultas() {
		int escolha;
		do {
			escolha = exibirMenuConsultas();
			executarEscolhaConsultas(escolha);
		} while (escolha != 9);
	}

	private int exibirMenuConsultas() {
		return GerenciadorMenu.mostrarMenu("""
				\n\t({[【﻿ＬｏＦｉｌｍｅｓ】]})
				---------------
				Seja bem vindo ao menu de consultas! O que desejaria?

				1 - Consulta por titulo
				2 - Consulta por diretor
				3 - Consulta por categoria
				4 - Consulta por preco
				5 - Consulta filmes locados nos últimos 7 dias
				6 - Consulta filmes mais locados
				7 - Consulta valor total das locações no último mês
				8 - Consulta cliente que mais locou filmes
				9 - Retornar ao menu principal
				""");
	}

	private void executarEscolhaConsultas(int escolha) {
		switch (escolha) {
		case 1:
			consultas.consultarFilmePorTitulo(FORMATTER);
			break;
		case 2:
			consultas.consultarFilmePorDiretor(FORMATTER);
			break;
		case 3:
			consultas.consultarFilmePorCategoria(FORMATTER);
			break;
		case 4:
			consultas.consultarFilmePorPreco(FORMATTER);
			break;
		case 5:
			consultas.consultarFilmesLocados7Dias(FORMATTER);
			break;
		case 6:
			consultas.consultarFilmeMaisLocado();
			break;
		case 7:
			consultas.consultarValorTotalLocacoesUltimoMes();
			break;
		case 8:
			consultas.consultarClienteQueMaisLocou();
			break;
		case 9:
			System.out.println("Retornando ao menu principal...");
			break;
		default:
			System.err.println("Opção não disponível.");
			break;
		}
	}

}