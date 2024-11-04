package lofilmes.persistencias;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import lofilmes.utilidades.ManipuladorArquivos;

public abstract class PersistenciaBase<T> {

	protected String caminhoArquivoPersistencia;
	protected String caminhoArquivoLog;
	protected Logger logger;

	public PersistenciaBase(String caminhoArquivoPersistencia, String caminhoArquivoLog, Logger logger) {
		this.caminhoArquivoPersistencia = caminhoArquivoPersistencia;
		this.caminhoArquivoLog = caminhoArquivoLog;
		this.logger = logger;
		configurarLogger();

	}

	public abstract void adicionarEmArquivo(T objeto);

	/**
	 * Verifica se um item com o ID especificado já existe no arquivo de persistência.
	 *
	 * @param id o ID do objeto a ser verificado.
	 * @return {@code true} se o ID já existe no arquivo; {@code false} caso contrário.
	 */
	public boolean itemExiste(Long id) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivoPersistencia))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("Id: ") && linha.contains(id.toString())) {
                    return true; // já existe item no arquivo
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Erro: arquivo não encontrado: " + caminhoArquivoPersistencia, e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro: não foi possível adicionar ao arquivo: " + caminhoArquivoPersistencia, e);
        }
        return false; // não existe item no arquivo
    }

	public void exibirArquivoPersistencia(String caminhoArquivo) {	
		ManipuladorArquivos.lerArquivo(caminhoArquivo);
	}
	
 	public void finalizarLogger() {
	    for (Handler handler : logger.getHandlers()) {
	        handler.close();
	        logger.removeHandler(handler);
	    }
	}
	
	private void configurarLogger() {
		try {
			FileHandler fileHandler = new FileHandler(caminhoArquivoLog, true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.setLevel(Level.ALL);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Erro ao configurar o logger", e);
		}
	}
	
}