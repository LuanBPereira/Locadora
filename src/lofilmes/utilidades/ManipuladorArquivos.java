package lofilmes.utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ManipuladorArquivos {

	public static void lerArquivo(String caminhoArquivo) {

		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {			
			String linha = br.readLine();
			
			if (linha == null)

			while ((linha = br.readLine()) != null) {
				System.out.println(linha);
			}

		} catch (FileNotFoundException e) {
			System.err.println("Não foi possível encontrar o arquivo: " + caminhoArquivo);

		} catch (IOException e) {
			System.err.println("Erro: " + e.getMessage());

		}
	}

	public void escreverArquivo() {

	}

}
