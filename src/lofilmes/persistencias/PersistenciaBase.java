package lofilmes.persistencias;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import lofilmes.utilidades.Identificavel;

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

	/*
	 * Método que verifica se algum item existe no arquivo, baseado no seu ID.
	 * Primeiro, obtemos o ID do objeto fornecido. Abrimos um BufferedReader para
	 * ler o arquivo. Enquanto houver linhas no arquivo, verificamos se alguma
	 * contém o ID. Se encontrar, retornamos true. Caso contrário, retornamos false.
	 */
	public <T extends Identificavel> boolean itemExiste(T objeto) {
		Long id = objeto.getId();
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
			logger.log(Level.SEVERE, "Erro: não foi possivel adicionar ao arquivo: " + caminhoArquivoPersistencia, e);
		}
		return false; // não existe filme no arquivo
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
