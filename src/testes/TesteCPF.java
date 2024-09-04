package testes;

import java.util.Scanner;

import lofilmes.utilidades.Formatador;

public class TesteCPF {
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		String cpf = criarCpf();
		System.out.println("cpf antes de formatar: "+cpf);
		cpf = Formatador.formatarCpf(cpf);
		System.out.println("cpf depois de formatado: "+cpf);
		
	}
	
	private static String criarCpf() {
		String cpf;
		String padraoDigitosCpf = "\\d{11}";

		do {
			System.out.println("Antes de continuar, por favor, digite seu CPF: ");
			cpf = scan.nextLine().trim();

			if (!cpf.matches(padraoDigitosCpf)) {
				System.err.println("CPF inválido. Por favor, certifique-se de que ele contém exatamente 11 dígitos numéricos.");
			}

		} while (!cpf.matches(padraoDigitosCpf));

		return cpf;
	}


}
