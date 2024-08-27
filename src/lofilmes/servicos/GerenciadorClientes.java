package lofilmes.servicos;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import lofilmes.modelos.Cliente;

public class GerenciadorClientes {
	private Scanner scan = new Scanner(System.in);
	private Map<Long, Cliente> listaClientes = new HashMap<>();

	public Cliente criarCliente() {
		while (true) {
			System.out.println("Antes de continuar, por favor, digite seu CPF");
			Long cpf = scan.nextLong();
			scan.nextLine();

			if (listaClientes.containsKey(cpf)) {
				System.out.println("Cliente já existe, portanto, para n repetir, apenas retornei ele :]"); // log
				return listaClientes.get(cpf);
			}

			System.out.println("Agora, digite seu nome:");
			String nome = scan.nextLine().trim();

			System.out.println("Ótimo " + nome + ", agora seu sobrenome:");
			String sobrenome = scan.nextLine().trim();

			try {
				verificaSeNomeSobrenomeEstaVazio(nome, sobrenome);

				Cliente novoCliente = new Cliente(cpf, nome, sobrenome);
				guardaEmLista(novoCliente);

				return novoCliente;
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void listarCLientes() {
		System.out.println("Listando clientes");

		if(listaClientes.isEmpty()){
			System.out.println("Lista de clientes vazia.");
		} else {
			for (Cliente cliente : listaClientes.values()) {
				System.out.println(cliente);
			}
		}
	}

	public Cliente getClientePorCpf(Long cpf) {
		return listaClientes.get(cpf);
	}

	private void guardaEmLista(Cliente cliente) {
		listaClientes.put(cliente.getCPF(), cliente);
	}

	private void verificaSeNomeSobrenomeEstaVazio(String nome, String sobrenome) {
		if (nome.isEmpty() || sobrenome.isEmpty()) {
			throw new IllegalArgumentException("Nome e sobrenome não podem ser vazios. " + "(Nome: " + nome
					+ ", Sobrenome: " + sobrenome + "). Tente novamente.");
		}
	}

}
