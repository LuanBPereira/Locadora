package lofilmes;

import java.time.format.DateTimeFormatter;
import java.util.*;

import lofilmes.servicos.*;
import lofilmes.utilidades.GerenciadorMenu;

public class Main {
	private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Scanner scan = new Scanner(System.in);
	private CatalogoFilmes filmes = new CatalogoFilmes();
	private HistoricoLocacoes historico = new HistoricoLocacoes();
	private Consultas consultas = new Consultas();
	
	public static void main(String[] args) {
		Main main = new Main();
		main.executar();
	}

	private void executar() {
        filmes.criarFilmes();
        historico.criarListaExemplo();
        int escolha;
        do {
            escolha = GerenciadorMenu.mostrarMenu(scan, """
                            \n\t({[【﻿ＬｏＦｉｌｍｅｓ】]})
                        ---------------
                    Seja bem vindo a LoFilmes! O que desejaria?

                    1 - Catalogo de filmes
                    2 - Histórico locações
                    3 - Consultas
                    4 - Encerrar programa
                            """);

            switch (escolha) {
            case 1:
                filmes.listarFilmes(filmes.getListaFilmes(), FORMATTER);
                break;
            case 2:
                historico.exibirHistorico(FORMATTER);
                break;
            case 3:
                entrarMenuDeConsultas();
                break;
            case 4:
                System.out.println("Encerrando programa...");
                break;
            default:
                System.err.println("Opção não disponível.");
                break;
            }

        } while (escolha != 4);
        scan.close();
    }
	
	private void entrarMenuDeConsultas() {
		int escolha;
		do {
			escolha = GerenciadorMenu.mostrarMenu(scan,"""
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
				consultas.consultarFilmePorTitulo(filmes, scan, FORMATTER);
				break;
			case 2:
				consultas.consultarFilmePorDiretor(filmes, scan, FORMATTER);
				break;
			case 3:
				consultas.consultarFilmePorCategoria(filmes, scan, FORMATTER);
				break;
			case 4:
				consultas.consultarFilmePorPreco(filmes, scan, FORMATTER);
				break;
			case 5:
				consultas.consultarFilmesLocados7Dias(historico, FORMATTER);
				break;
			case 6:
				consultas.consultarFilmeMaisLocado(historico);
				break;
			case 7:
				consultas.consultarValorTotalLocacoesUltimoMes(historico);
				break;
			case 8:
				consultas.consultarClienteQueMaisLocou(historico);
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