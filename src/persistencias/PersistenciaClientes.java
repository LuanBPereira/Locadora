package persistencias;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import lofilmes.modelos.Cliente;

public class PersistenciaClientes extends PersistenciaBase<Cliente> {

	private static final String CAMINHO_ARQUIVO_PERSISTENCIA = "clientes_persistence.dat";
	private static final String CAMINHO_ARQUIVO_LOG = "clientes_persistence_log.txt";
	private static final Logger logger = Logger.getLogger(PersistenciaClientes.class.getName());

	@Override
	public void adicionarEmArquivo(Cliente cliente) {
		criarLog(logger, CAMINHO_ARQUIVO_LOG);
		
		try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(CAMINHO_ARQUIVO_PERSISTENCIA))) {
			oss.writeObject(cliente);
			logger.info("Cliente salvo com sucesso!\n" + cliente.toString());
			
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Erro: arquivo não encontrado: " + CAMINHO_ARQUIVO_PERSISTENCIA, e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Erro: não foi possivel adicionar ao arquivo: " +  CAMINHO_ARQUIVO_PERSISTENCIA, e);
		}
	}
	
}
