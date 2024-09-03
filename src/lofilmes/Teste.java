package lofilmes;

import lofilmes.servicos.GerenciadorClientes;
import lofilmes.utilidades.FormatadorCPF;

public class Teste {

	public static void main(String[] args) {
		GerenciadorClientes gc = new GerenciadorClientes();
		
		String cpf = gc.criarCpf();
		FormatadorCPF.formatarCpf(cpf);
	}
	
}
