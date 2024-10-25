package persistencias;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import lofilmes.modelos.Cliente;

public class PersistenciaClientes extends PersistenciaBase<Cliente> {

	private static final Logger logger = Logger.getLogger(PersistenciaClientes.class.getName());
	
	public PersistenciaClientes() {
		super("clientes_persistence.dat", "clientes_persistence_log.txt", logger);
	}

	@Override
	public void adicionarEmArquivo(Cliente cliente) {
		try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(caminhoArquivoPersistencia))) {
			oss.writeObject(cliente);
			logger.info("Cliente salvo com sucesso!\n" + cliente.toString());
			
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Erro: arquivo não encontrado: " + caminhoArquivoPersistencia, e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Erro: não foi possivel adicionar ao arquivo: " +  caminhoArquivoPersistencia, e);
		}
	}
	
}
