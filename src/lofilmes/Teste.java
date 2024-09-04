package lofilmes;

import lofilmes.servicos.GerenciadorClientes;
import lofilmes.utilidades.Formatador;

public class Teste {

	public static void main(String[] args) {
		GerenciadorClientes gc = new GerenciadorClientes();

		// teste formatação cpf
		String cpf = gc.criarCpf();
		System.out.println("cpf antes de formatar: "+cpf);
		cpf = Formatador.formatarCpf(cpf);
		System.out.println("cpf depois de formatado: "+cpf);
	}
	
}
