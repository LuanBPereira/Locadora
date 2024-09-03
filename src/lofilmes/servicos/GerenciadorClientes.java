package lofilmes.servicos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lofilmes.modelos.Cliente;

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

			if (getClientePorCpf(cpf) != null) {
				// (log) usei para ver se o cliente já existia
				System.out.println("Cliente já existe, portanto, para n repetir, apenas retornei ele :]");
				return getClientePorCpf(cpf);
			}

			String[] nomeSobrenome = criarNomeSobrenome(nome, sobrenome);
			nome = nomeSobrenome[0];
			sobrenome = nomeSobrenome[1];

			if (isNomeSobrenomeVazio(nome, sobrenome)) {
				System.err.println("Nome e sobrenome não podem ser vazios. (Nome: " + nome + ", Sobrenome: " + sobrenome
						+ "). Tente novamente.");
				continue;
			}
			
			Cliente novoCliente = new Cliente(id, cpf, nome, sobrenome);
			
			// (log) apenas para ver se o cliente tá vindo com os dados certos
			System.out.println(novoCliente);
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

	public String criarCpf() {
	    String cpf;
	    String padraoCpfSemFormato = "\\d{11}";

	    Pattern patternSemFormato = Pattern.compile(padraoCpfSemFormato);

	    do {
	        System.out.println("Antes de continuar, por favor, digite seu CPF (apenas números):");
	        cpf = scan.nextLine().trim();

	        Matcher matcherSemFormato = patternSemFormato.matcher(cpf);

	        if (!matcherSemFormato.matches()) {
	            System.err.println("CPF inválido. Por favor, use apenas números.");
	        }

	    } while (!(cpf.length() == 11));

	    return cpf;
	}

	private String[] criarNomeSobrenome(String nome, String sobrenome) {
		System.out.println("Agora, digite seu nome:");
		nome = scan.nextLine().trim();

		System.out.println("Ótimo " + nome + ", agora seu sobrenome:");
		sobrenome = scan.nextLine().trim();

		return new String[] { nome, sobrenome };
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