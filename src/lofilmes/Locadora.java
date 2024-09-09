package lofilmes;

import java.util.Scanner;

import lofilmes.servicos.CatalogoFilmes;
import lofilmes.servicos.Consultas;
import lofilmes.servicos.GerenciadorClientes;
import lofilmes.servicos.HistoricoLocacoes;
import lofilmes.servicos.ServicosLocacao;
import lofilmes.utilidades.Formatador;
import lofilmes.utilidades.GerenciadorMenu;

public class Locadora {
	
	private final Scanner scan;
	private final GerenciadorMenu gerenciadorMenu;
	private final GerenciadorClientes gerenciadorClientes;
	private final HistoricoLocacoes historicoLocacoes;
	private final CatalogoFilmes catalogoFilmes;
	private final Consultas consultas;
	private final ServicosLocacao servicosLocacao;

	public Locadora(Scanner scan, GerenciadorMenu gerenciadorMenu, GerenciadorClientes gerenciadorClientes,
			HistoricoLocacoes historicoLocacoes, CatalogoFilmes catalogoFilmes, Consultas consultas,
			ServicosLocacao servicosLocacao) {
		this.scan = scan;
		this.gerenciadorMenu = gerenciadorMenu;
		this.gerenciadorClientes = gerenciadorClientes;
		this.historicoLocacoes = historicoLocacoes;
		this.catalogoFilmes = catalogoFilmes;
		this.consultas = consultas;
		this.servicosLocacao = servicosLocacao;
	}

	public void executarLocadora() {
		entrarMenuPrincipal();
	}

	private void entrarMenuPrincipal() {
		catalogoFilmes.criarFilmes();
		int escolha;
		do {
			escolha = exibirMenuPrincipal();
			executarEscolhaPrincipal(escolha);
		} while (escolha != 5);
		scan.close();
	}

	private int exibirMenuPrincipal() {
		return gerenciadorMenu.mostrarMenu("""
				\n\t({[【﻿ＬｏＦｉｌｍｅｓ】]})
				---------------
				Seja bem vindo a LoFilmes! O que desejaria?

				1 - Catalogo de filmes
				2 - Alugar filme
				3 - Consultas
				4 - Listar clientes
				5 - Encerrar programa
				""");
	}

	private void executarEscolhaPrincipal(int escolha) {
		switch (escolha) {
		case 1 -> catalogoFilmes.listarFilmes(catalogoFilmes.getListaFilmes());
		case 2 -> servicosLocacao.alugarFilme(catalogoFilmes);
		case 3 -> entrarMenuDeConsultas();
		case 4 -> gerenciadorClientes.listarClientes();
		case 5 -> System.out.println("Encerrando programa...");
		default -> System.err.println("Opção não disponível.");
		}
	}

	private void entrarMenuDeConsultas() {
		int escolha;
		do {
			escolha = exibirMenuConsultas();
			executarEscolhaConsultas(escolha);
		} while (escolha != 10);
	}

	private int exibirMenuConsultas() {
		return gerenciadorMenu.mostrarMenu("""
				\n\t({[【﻿ＬｏＦｉｌｍｅｓ】]})
				---------------
				Seja bem vindo ao menu de consultas! O que desejaria?

				1 - Consulta por título
				2 - Consulta por diretor
				3 - Consulta por categoria
				4 - Consulta por preço
				5 - Consulta histórico locações
				6 - Consulta filmes locados nos últimos 7 dias
				7 - Consulta filmes mais locados
				8 - Consulta valor total das locações no último mês
				9 - Consulta cliente que mais locou filmes
				10 - Retornar ao menu principal
				""");
	}

	private void executarEscolhaConsultas(int escolha) {
		switch (escolha) {
		case 1 -> consultas.consultarFilmePorTitulo(catalogoFilmes);
		case 2 -> consultas.consultarFilmePorDiretor(catalogoFilmes);
		case 3 -> consultas.consultarFilmePorCategoria(catalogoFilmes);
		case 4 -> consultas.consultarFilmePorPreco(catalogoFilmes);
		case 5 -> consultas.consultarHistorico(historicoLocacoes);
		case 6 -> consultas.consultarFilmesLocados7Dias(historicoLocacoes);
		case 7 -> consultas.consultarFilmeMaisLocado(historicoLocacoes);
		case 8 -> consultas.consultarValorTotalLocacoesUltimoMes(historicoLocacoes);
		case 9 -> consultas.consultarClienteQueMaisLocou(historicoLocacoes, gerenciadorClientes);
		case 10 -> System.out.println("Retornando ao menu principal...");
		default -> System.err.println("Opção não disponível.");

		}
	}

}
