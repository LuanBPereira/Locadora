package persistencias;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import lofilmes.modelos.Filme;

public class PersistenciaFilmes extends PersistenciaBase<Filme> {
	
	private static final String CAMINHO_ARQUIVO_PERSISTENCIA = "filmes_persistence.txt";
	private static final String CAMINHO_ARQUIVO_LOG = "filmes_persistence_log.txt";
	private static final Logger logger = Logger.getLogger(PersistenciaFilmes.class.getName());

	@Override
	public void adicionarEmArquivo(Filme filme) {
		criarLog(logger, CAMINHO_ARQUIVO_LOG);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO_PERSISTENCIA))) {
			bw.write(filme.toString());
			logger.info("Filme salvo com sucesso!\n" + filme.toString());
		
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Erro: arquivo não encontrado: " + CAMINHO_ARQUIVO_PERSISTENCIA, e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Erro: não foi possivel adicionar ao arquivo: " +  CAMINHO_ARQUIVO_PERSISTENCIA, e);
		}
	}
	
}