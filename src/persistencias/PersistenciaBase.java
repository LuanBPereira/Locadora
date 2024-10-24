package persistencias;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class PersistenciaBase<T> {

	public abstract void adicionarEmArquivo(T objeto);
	
	protected static void criarLog(Logger logger, String caminhoArq) {
        try {
            // criando um FileHandler para salvar logs em arquivo
            FileHandler fileHandler = new FileHandler(caminhoArq, true);
            fileHandler.setFormatter(new SimpleFormatter());  
            logger.addHandler(fileHandler); 

            logger.setLevel(Level.ALL);  // define o nível de log (pode ser ajustado)
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao configurar o logger", e);
        }
    }
	
}
