package persistencias;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import lofilmes.modelos.Filme;

public class PersistenciaFilmes extends PersistenciaBase<Filme> {
	
	private static final Logger logger = Logger.getLogger(PersistenciaFilmes.class.getName());
	
	public PersistenciaFilmes() {
		super("filmes_persistence.txt", "filmes_persistence_log.txt", logger);
	}
	
	@Override
	public void adicionarEmArquivo(Filme filme) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivoPersistencia))) {
			bw.write(filme.toString());
			logger.info("Filme salvo com sucesso!\n" + filme.toString());
		
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Erro: arquivo não encontrado: " + caminhoArquivoPersistencia, e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Erro: não foi possivel adicionar ao arquivo: " +  caminhoArquivoPersistencia, e);
		}
	}
	
}