package lofilmes;

import java.util.Scanner;

import lofilmes.servicos.CatalogoFilmes;
import lofilmes.servicos.Consultas;
import lofilmes.servicos.GerenciadorClientes;
import lofilmes.servicos.GerenciadorEntradas;
import lofilmes.servicos.HistoricoLocacoes;
import lofilmes.servicos.ServicosLocacao;
import lofilmes.utilidades.GerenciadorMenu;

public class App {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// instancias dos serviços
		GerenciadorEntradas gerenciadorEntradas = new GerenciadorEntradas(scan);
		GerenciadorMenu gerenciadorMenu = new GerenciadorMenu(scan);
		GerenciadorClientes gerenciadorClientes = new GerenciadorClientes();
		HistoricoLocacoes historicoLocacoes = new HistoricoLocacoes();
		CatalogoFilmes catalogoFilmes = new CatalogoFilmes();
		Consultas consultas = new Consultas(scan, catalogoFilmes);
		ServicosLocacao servicosLocacao = new ServicosLocacao(scan, gerenciadorClientes, historicoLocacoes, gerenciadorEntradas);

		// injeções de dependencias na locadora, centalizando todos os serviços
		Locadora locadora = new Locadora(scan, gerenciadorMenu, gerenciadorClientes, historicoLocacoes, catalogoFilmes,
				consultas, servicosLocacao);

		locadora.executarLocadora();
		scan.close();
	}

}