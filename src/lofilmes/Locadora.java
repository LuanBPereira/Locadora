package lofilmes;

import lofilmes.servicos.CatalogoFilmes;
import lofilmes.servicos.Consultas;
import lofilmes.servicos.GestaoClientes;
import lofilmes.servicos.ServicosLocacao;
import lofilmes.utilidades.ControladorMenu;

public class Locadora {
	
	private final ControladorMenu controladorMenu;
	private final GestaoClientes gestaoClientes;
	private final CatalogoFilmes catalogoFilmes;
	private final Consultas consultas;
	private final ServicosLocacao servicosLocacao;

	public Locadora(ControladorMenu controladorMenu, GestaoClientes gestaoClientes,
			CatalogoFilmes catalogoFilmes, Consultas consultas, ServicosLocacao servicosLocacao) {
		this.controladorMenu = controladorMenu;
		this.gestaoClientes = gestaoClientes;
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
	}

	private int exibirMenuPrincipal() {
		return controladorMenu.mostrarMenu("""
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
		case 4 -> gestaoClientes.listarClientes();
		case 5 -> System.out.println("Encerrando programa...");
		default -> System.err.println("Opção não disponível.");
		}
	}

	private void entrarMenuDeConsultas() {
		int escolha;
		do {
			escolha = exibirMenuConsultas();
			executarEscolhaConsultas(escolha);
		} while (escolha != 12);
	}

	private int exibirMenuConsultas() {
		return controladorMenu.mostrarMenu("""
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
				10 - Consulta arquivo persistencia cliente
				11 - Consulta arquivo persistencia histórico locações
				12 - Retornar ao menu principal
				""");
	}

	private void executarEscolhaConsultas(int escolha) {
		switch (escolha) {
		case 1 -> consultas.consultarFilmePorTitulo();
		case 2 -> consultas.consultarFilmePorDiretor();
		case 3 -> consultas.consultarFilmePorCategoria();
		case 4 -> consultas.consultarFilmePorPreco();
		case 5 -> consultas.consultarHistorico();
		case 6 -> consultas.consultarFilmesLocados7Dias();
		case 7 -> consultas.consultarFilmeMaisLocado();
		case 8 -> consultas.consultarValorTotalLocacoesUltimoMes();
		case 9 -> consultas.consultarClienteQueMaisLocou();
		case 10 -> consultas.consultarPersistenciaCliente();
		case 11 -> consultas.consultarPersistenciaHistoricoLocacoes();
		case 12 -> System.out.println("Retornando ao menu principal...");
		default -> System.err.println("Opção não disponível.");

		}
	}

}
