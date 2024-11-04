package lofilmes.persistencias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import lofilmes.modelos.Cliente;

public class PersistenciaClientes extends PersistenciaBase<Cliente> {

	private static final Logger LOGGER = Logger.getLogger(PersistenciaClientes.class.getName());

	public PersistenciaClientes() {
		super("clientes_persistence.txt", "clientes_persistence_log.txt", LOGGER);
	}

	@Override
	public void adicionarEmArquivo(Cliente cliente) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivoPersistencia, true))) {
			if (itemExiste(cliente.getId()) || clienteExistePorCPF(cliente.getCPF())) {
				LOGGER.warning("Cliente já existe no log: " + caminhoArquivoPersistencia);
				return;
			}
			
			bw.write(cliente.toString());
			LOGGER.info("Cliente salvo com sucesso!\n" + cliente.toString());

		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Erro: arquivo não encontrado: " + caminhoArquivoPersistencia, e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro: não foi possivel adicionar ao arquivo: " + caminhoArquivoPersistencia, e);
		}
	}
	
	/**
	 * Verifica se um cliente com o CPF especificado já existe no arquivo de persistência.
	 *
	 * @param cpf o CPF do cliente a ser verificado.
	 * @return {@code true} se o CPF já existe no arquivo; {@code false} caso contrário.
	 */
	public boolean clienteExistePorCPF(String cpf) {
		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoPersistencia))) {
			String linha;

			while ((linha = br.readLine()) != null) {
				if (linha.contains(cpf)) {
					return true; // já existe cliente com o CPF no arquivo
				}
			}
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Erro: arquivo não encontrado: " + caminhoArquivoPersistencia, e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro: não foi possível ler o arquivo: " + caminhoArquivoPersistencia, e);
		}
		return false; // não existe cliente com o CPF no arquivo
	}
	
}
