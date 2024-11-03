package lofilmes.utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ManipuladorArquivos {

	public static void lerArquivo(String caminhoArquivo) {

		try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {		
			
			List<String> linhas = br.lines()
		            .filter(linha -> !linha.trim().isEmpty()) // filtra linhas vazias. Se não tiver vazia, pega a linha.
		            .collect(Collectors.toList());

			if (linhas.isEmpty()) {
	            System.out.println("Arquivo vazio.");
	            return;
	        }
			
			linhas.forEach(System.out::println);
			
		} catch (FileNotFoundException e) {
			System.err.println("Não foi possível encontrar o arquivo: " + caminhoArquivo);

		} catch (IOException e) {
			System.err.println("Erro: " + e.getMessage());

		}
	}

}
