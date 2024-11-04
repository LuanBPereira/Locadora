package lofilmes.servicos;

import lofilmes.modelos.Cliente;
import lofilmes.persistencias.PersistenciaClientes;

import java.util.HashMap;
import java.util.Map;

public class GestaoClientes {
	private Map<Long, Cliente> listaClientes = new HashMap<>();
	private PersistenciaClientes persistencia;
	
	public GestaoClientes(PersistenciaClientes persistencia) {
		this.persistencia = persistencia;
	}

	public Cliente criarCliente(Long id, String cpf, String nome, String sobrenome) {
		Cliente clienteExistente = getClientePorCpf(cpf);
		if (clienteExistente != null) {
			System.out.println("Cliente já existe. Retornando cliente existente.");
			return clienteExistente;
		}

		Cliente novoCliente = new Cliente(id, cpf, nome, sobrenome);
		salvarCliente(novoCliente);

		System.out.println("Cliente criado com sucesso: " + novoCliente.getNomeCompleto());
		return novoCliente;
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
	
	public void exibirArquivoPersistencia() {
		String caminhoArquivo = "clientes_persistence.txt";
		persistencia.exibirArquivoPersistencia(caminhoArquivo);
	}

	public void finalizarLogger() {
		persistencia.finalizarLogger();
	}
	
	private void salvarCliente(Cliente cliente) {
		listaClientes.put(cliente.getId(), cliente);
		persistencia.adicionarEmArquivo(cliente);
	}
	
	private Cliente getClientePorCpf(String cpf) {
		for (Cliente cliente : listaClientes.values()) {
			if (cliente.getCPF().equals(cpf)) {
				return cliente;
			}
		}
		return null;
	}
	
}