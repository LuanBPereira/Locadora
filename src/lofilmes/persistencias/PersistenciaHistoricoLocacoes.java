package lofilmes.persistencias;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import lofilmes.modelos.DadosLocacao;

public class PersistenciaHistoricoLocacoes extends PersistenciaBase<DadosLocacao> {

	private static final Logger LOGGER = Logger.getLogger(PersistenciaHistoricoLocacoes.class.getName());
	
	public PersistenciaHistoricoLocacoes() {
		super("historicoLocacoes_persistence.txt", "historicoLocacoes_persistence_log.txt", LOGGER);
	}

	@Override
	public void adicionarEmArquivo(DadosLocacao dadosLocacao) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivoPersistencia, true))) {
			if (itemExiste(dadosLocacao.idLocacao())) {
				LOGGER.info("Locação já existente no arquivo: " + caminhoArquivoPersistencia);
				return;
			}
			
			bw.write(dadosLocacao.toString());
			LOGGER.info("Dados da locação salvo com sucesso!\n" + dadosLocacao.toString());
			
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Erro: arquivo não encontrado: " + caminhoArquivoPersistencia, e);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Erro: não foi possivel adicionar ao arquivo: " +  caminhoArquivoPersistencia, e);
		}
	}
	
}
