package lofilmes.servicos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import lofilmes.modelos.Cliente;
import lofilmes.utilidades.Formatador;

public class GerenciadorClientes {
	private Scanner scan = new Scanner(System.in);
	private Map<Long, Cliente> listaClientes = new HashMap<>();
	private Set<Long> numerosGerados = new HashSet<>();

	public Cliente criarCliente() {
		String nome = null, sobrenome = null, cpf = null;
		Long id = null;

		while (true) {
			cpf = criarCpf();
			id = gerarIdAleatorio();

			// (log)
			if(getClientePorCpf(cpf) == null) {
				System.out.println("Cliente não existia. Portanto, criando um novo!");
			}
			
			if (getClientePorCpf(cpf) != null) {
				// (log) usei para ver se o cliente já existia
				System.out.println("Cliente já existe, portanto, para n repetir, apenas retornei ele :]"); 
				return getClientePorCpf(cpf);
			}

			String[] nomeSobrenome = criarNomeSobrenome();
			nome = nomeSobrenome[0];
			sobrenome = nomeSobrenome[1];

			if (isNomeSobrenomeVazio(nome, sobrenome)) {
				System.err.println("Nome e sobrenome não podem ser vazios. (Nome: " + nome + ", Sobrenome: " + sobrenome
						+ "). Tente novamente.");
				continue;
			}
			
			Cliente novoCliente = new Cliente(id, cpf, nome, sobrenome);
			
			// (log) apenas para ver se o cliente tá vindo com os dados certos
			System.out.println(Formatador.formatarCpf(novoCliente.getCPF()) + " " + novoCliente.getNomeCompleto());
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

	public Cliente getClientePorId(Long id) {
		return listaClientes.get(id);
	}
	
	private Cliente getClientePorCpf(String cpf) {
		for (Cliente cliente : listaClientes.values()) {
			if (cliente.getCPF().equals(cpf)) {
				return cliente;
			}
		}
		
		return null;
	}
	
	private void guardaNaLista(Cliente cliente) {
		listaClientes.put(cliente.getID(), cliente);
	}

	private boolean isNomeSobrenomeVazio(String nome, String sobrenome) {
		return nome.isEmpty() || sobrenome.isEmpty();
	}

	private String criarCpf() {
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

	private String[] criarNomeSobrenome() {
		String nome = validarEntrada("nome");
		String sobrenome = validarEntrada("sobrenome");
		
		return new String[] { nome, sobrenome };
	}
		
	private String validarEntrada(String tipo) {
	    String entrada;
	    
	    while (true) {
	        System.out.println("Agora, digite seu " + tipo + ":");
	        entrada = scan.nextLine().trim();
	        
	        if (entrada.matches("[a-zA-Z]+")) {
	            return entrada;  // retorna entrada valida
	        } else {
	            System.out.println(tipo + " inválido. Por favor, digite apenas letras."); 
	        }
	    }
	}

	private Long gerarIdAleatorio() {
		Long idAleatorio;
		do {
			idAleatorio = new Random().nextLong() & Long.MAX_VALUE;
		} while (numerosGerados.contains(idAleatorio));

		numerosGerados.add(idAleatorio);
		return idAleatorio;
	}
	
}