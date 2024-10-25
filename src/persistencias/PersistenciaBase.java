package persistencias;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
