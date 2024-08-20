package lofilmes;

import java.time.format.DateTimeFormatter;

import lofilmes.servicos.CatalogoFilmes;
import lofilmes.servicos.Consultas;
import lofilmes.servicos.GerenciadorClientes;
import lofilmes.servicos.HistoricoLocacoes;
import lofilmes.utilidades.GerenciadorMenu;

public class Main {
	private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private CatalogoFilmes catalogoFilmes = new CatalogoFilmes();
	private HistoricoLocacoes historicoLocacoes = new HistoricoLocacoes();
	private Consultas consultas = new Consultas();
	private GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
	
	public static void main(String[] args) {
		Main main = new Main();
		main.executar();
	}

	private void executar() {
        catalogoFilmes.criarFilmes();
        historicoLocacoes.criarListaExemplo();
        int escolha;
        do {
            escolha = GerenciadorMenu.mostrarMenu("""
                            \n\t({[【﻿ＬｏＦｉｌｍｅｓ】]})
                        ---------------
                    Seja bem vindo a LoFilmes! O que desejaria?

                    1 - Catalogo de filmes
                    2 - Histórico locações
                    3 - Consultas
                    4 - Alugar filme
                    5 - Encerrar programa
                            """);

            switch (escolha) {
            case 1:
                catalogoFilmes.listarFilmes(catalogoFilmes.getListaFilmes(), FORMATTER);
                break;
            case 2:
                historicoLocacoes.exibirHistorico(FORMATTER);
                break;
            case 3:
                entrarMenuDeConsultas();
                break;
            case 4:
            	gerenciadorClientes.escolherfilme(catalogoFilmes, FORMATTER, historicoLocacoes);
            	break;
            case 5:
                System.out.println("Encerrando programa...");
                break;
            default:
                System.err.println("Opção não disponível.");
                break;
            }

        } while (escolha != 5);
    }
	
	private void entrarMenuDeConsultas() {
		int escolha;
		do {
			escolha = GerenciadorMenu.mostrarMenu("""
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

			switch (escolha) {
			case 1:
				consultas.consultarFilmePorTitulo(catalogoFilmes, FORMATTER);
				break;
			case 2:
				consultas.consultarFilmePorDiretor(catalogoFilmes, FORMATTER);
				break;
			case 3:
				consultas.consultarFilmePorCategoria(catalogoFilmes,FORMATTER);
				break;
			case 4:
				consultas.consultarFilmePorPreco(catalogoFilmes, FORMATTER);
				break;
			case 5:
				consultas.consultarFilmesLocados7Dias(historicoLocacoes, FORMATTER);
				break;
			case 6:
				consultas.consultarFilmeMaisLocado(historicoLocacoes);
				break;
			case 7:
				consultas.consultarValorTotalLocacoesUltimoMes(historicoLocacoes);
				break;
			case 8:
				consultas.consultarClienteQueMaisLocou(historicoLocacoes);
				break;
			case 9:
				return;
			default:
				System.err.println("Opção não disponível.");
				break;
			}
		} while (escolha != 9);
	}
		

}