package lofilmes;

import java.util.Scanner;

import lofilmes.persistencias.PersistenciaClientes;
import lofilmes.persistencias.PersistenciaHistoricoLocacoes;
import lofilmes.servicos.CatalogoFilmes;
import lofilmes.servicos.Consultas;
import lofilmes.servicos.GestaoClientes;
import lofilmes.servicos.HistoricoLocacoes;
import lofilmes.servicos.ServicosLocacao;
import lofilmes.utilidades.GerenciadorEntradas;
import lofilmes.utilidades.ControladorMenu;

public class App {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// instancias dos serviços
		PersistenciaClientes persistenciaClientes = new PersistenciaClientes();
		PersistenciaHistoricoLocacoes persistenciaHistoricoLocacoes = new PersistenciaHistoricoLocacoes();
		GerenciadorEntradas gerenciadorEntradas = new GerenciadorEntradas(scan);
		ControladorMenu controladorMenu = new ControladorMenu(scan);
		GestaoClientes gestaoClientes = new GestaoClientes(persistenciaClientes);
		HistoricoLocacoes historicoLocacoes = new HistoricoLocacoes(persistenciaHistoricoLocacoes);
		CatalogoFilmes catalogoFilmes = new CatalogoFilmes();
		Consultas consultas = new Consultas(scan, catalogoFilmes, historicoLocacoes, gestaoClientes);
		ServicosLocacao servicosLocacao = new ServicosLocacao(scan, gestaoClientes, historicoLocacoes, gerenciadorEntradas);

		// injeções de dependencias na locadora, centalizando todos os serviços
		Locadora locadora = new Locadora(controladorMenu, gestaoClientes, catalogoFilmes,
				consultas, servicosLocacao);

		locadora.executarLocadora();
		scan.close();
		historicoLocacoes.finalizarLogger();
		gestaoClientes.finalizarLogger();
	}

}