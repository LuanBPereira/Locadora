package lofilmes.utilidades;

import java.util.Scanner;

public class GerenciadorEntradas {

	private Scanner scan;

    public GerenciadorEntradas(Scanner scan) {
        this.scan = scan;
    }

    public String solicitarCpf() {
        String cpf;
        do {
            System.out.println("Digite o CPF (11 dígitos): ");
            cpf = scan.nextLine().trim();

            if (!cpf.matches("\\d{11}")) {
                System.err.println("CPF inválido. Tente novamente.");
            }
        } while (!cpf.matches("\\d{11}"));

        return cpf;
    }

    public String[] solicitarNomeSobrenome() {
        String nome = solicitarEntrada("Nome");
        String sobrenome = solicitarEntrada("Sobrenome");
        return new String[]{nome, sobrenome};
    }

    private String solicitarEntrada(String tipo) {
        String entrada;
        do {
            System.out.println("Digite o " + tipo + ": ");
            entrada = scan.nextLine().trim();
            if (!entrada.matches("[a-zA-Z]+")) {
                System.err.println(tipo + " inválido. Use apenas letras.");
            }
        } while (!entrada.matches("[a-zA-Z]+"));
        return entrada;
    }
	
}
