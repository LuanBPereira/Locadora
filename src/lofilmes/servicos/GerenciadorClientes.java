package lofilmes.servicos;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import lofilmes.modelos.Cliente;

public class GerenciadorClientes {
	private Scanner scan = new Scanner(System.in);
	private Map<Long, Cliente> listaClientes = new HashMap<>();

	public Cliente criarCliente() {
		String nome = null, sobrenome = null;
		Long cpf = null;
		
		while (true) {
			cpf = criarCpf(cpf);
			
			if (listaClientes.containsKey(cpf)) {
				// (log) usei para ver se o cliente já existia
				System.out.println("Cliente já existe, portanto, para n repetir, apenas retornei ele :]");
				return listaClientes.get(cpf);
			}

			String [] nomeSobrenome = criarNomeSobrenome(nome, sobrenome);
			nome = nomeSobrenome[0];
			sobrenome = nomeSobrenome[1];
			
			if (isNomeSobrenomeVazio(nome, sobrenome)) {
				System.err.println("Nome e sobrenome não podem ser vazios. (Nome: " + nome + ", Sobrenome: " + sobrenome
						+ "). Tente novamente.");
				continue;
			}

			Cliente novoCliente = new Cliente(cpf, nome, sobrenome);
			guardaNaLista(novoCliente);

			return novoCliente;
		}
	}

	public void listarCLientes() {
		if (listaClientes.isEmpty()) {
			System.out.println("Lista de clientes vazia.");
		} else {
			System.out.println("Listando clientes:");
			for (Cliente cliente : listaClientes.values()) {
				System.out.println(cliente);
			}
		}
	}

	public Cliente getClientePorCpf(Long cpf) {
		return listaClientes.get(cpf);
	}

	private void guardaNaLista(Cliente cliente) {
		listaClientes.put(cliente.getCPF(), cliente);
	}

	private boolean isNomeSobrenomeVazio(String nome, String sobrenome) {
		return nome.isEmpty() || sobrenome.isEmpty();
	}
	
	private Long criarCpf(Long cpf) {
		System.out.println("Antes de continuar, por favor, digite seu CPF");
		cpf = scan.nextLong();
		scan.nextLine();
		return cpf;
	}
	
	private String[] criarNomeSobrenome(String nome, String sobrenome) {
		System.out.println("Agora, digite seu nome:");
		nome = scan.nextLine().trim();

		System.out.println("Ótimo " + nome + ", agora seu sobrenome:");
		sobrenome = scan.nextLine().trim();
		
		return new String[] {nome, sobrenome};
	}
	

}