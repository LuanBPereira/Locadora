package lofilmes.servicos;

import lofilmes.modelos.Cliente;
import java.util.HashMap;
import java.util.Map;

public class GestaoClientes {
	private Map<Long, Cliente> listaClientes = new HashMap<>();

	public Cliente criarCliente(Long id, String cpf, String nome, String sobrenome) {
		Cliente clienteExistente = getClientePorCpf(cpf);
		if (clienteExistente != null) {
			System.out.println("Cliente já existe. Retornando cliente existente.");
			return clienteExistente;
		}

		Cliente novoCliente = new Cliente(id, cpf, nome, sobrenome);
		listaClientes.put(novoCliente.getId(), novoCliente);

		System.out.println("Cliente criado com sucesso: " + novoCliente.getNomeCompleto());
		return novoCliente;
	}

	private Cliente getClientePorCpf(String cpf) {
		for (Cliente cliente : listaClientes.values()) {
			if (cliente.getCPF().equals(cpf)) {
				return cliente;
			}
		}
		return null;
	}

	public Cliente getClientePorId(Long id) {
		return listaClientes.get(id);
	}

	public void listarClientes() {
		if (listaClientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado.");
		}

		for (Cliente cliente : listaClientes.values()) {
			System.out.println(cliente);
		}
		
	}

}
