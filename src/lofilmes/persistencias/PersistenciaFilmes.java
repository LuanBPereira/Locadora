package lofilmes.persistencias;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import lofilmes.modelos.Filme;

public class PersistenciaFilmes extends PersistenciaBase<Filme> {

	private static final Logger LOGGER = Logger.getLogger(PersistenciaFilmes.class.getName());

	public PersistenciaFilmes() {
		super("filmes_persistence.txt", "filmes_persistence_log.txt", LOGGER);
	}

	@Override
	public void adicionarEmArquivo(Filme filme) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivoPersistencia, true))) {
			if(itemExiste(filme)) {
				LOGGER.warning("Filme já existe no arquivo: " + caminhoArquivoPersistencia);
				return;
			}
			
			bw.write(filme.toString());
			LOGGER.info("Filme salvo com sucesso!\n" + filme.toString());

		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Erro: arquivo não encontrado: " + caminhoArquivoPersistencia, e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro: não foi possivel adicionar ao arquivo: " + caminhoArquivoPersistencia, e);
		}
	}
	
}