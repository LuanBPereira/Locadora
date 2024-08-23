package lofilmes.servicos;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import lofilmes.modelos.Cliente;

public class GerenciadorClientes {
	private Scanner scan = new Scanner(System.in);
	private Set<Cliente> clientes = new HashSet<>();

	public Cliente criarCliente() {
		while (true) {
			System.out.println("Antes de continuar, por favor, digite seu CPF");
			Long cpf = scan.nextLong();
			scan.nextLine();
			
			System.out.println("Agora, digite seu nome:");
			String nome = scan.nextLine().trim();

			System.out.println("Ótimo " + nome + ", agora seu sobrenome:");
			String sobrenome = scan.nextLine().trim();
			
			try {
				verificaSeNomeSobrenomeEstaVazio(nome, sobrenome);
				
				Cliente novoCliente = new Cliente(cpf, nome, sobrenome);
				verificaSeExisteClienteOuAdiciona(novoCliente);
				
				return novoCliente;
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	public Set<Cliente> getListaClientes(){
		return clientes;
	}
	
	public void listarClientes() {
		for(Cliente c : clientes) {
			System.out.println(c);
		}
	}

	private Cliente verificaSeExisteClienteOuAdiciona(Cliente novoCliente) {
		if(clientes.contains(novoCliente)) {
			System.out.println("Cliente com cpf " +novoCliente.getID()+ " já existe");
			return getClienteExistente(novoCliente.getID());
		} else {
			adicionarClienteEmLista(novoCliente);
			return novoCliente;
		}
		
	}
	
	private void verificaSeNomeSobrenomeEstaVazio(String nome, String sobrenome) {
		if (nome.isEmpty() || sobrenome.isEmpty()) {
			throw new IllegalArgumentException("Nome e sobrenome não podem ser vazios. " + "(Nome: " + nome
					+ ", Sobrenome: " + sobrenome + "). Tente novamente.");
		}
	}

	private Cliente getClienteExistente(Long cpf) {
		for(Cliente c : clientes) {
			if(c.getID().equals(cpf)) {
				return c;
			}
		}
		return null;
	}
	
	private void adicionarClienteEmLista(Cliente cliente) {
		clientes.add(cliente);
	}
	
}
