package lofilmes.servicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lofilmes.modelos.Filme;

public class CatalogoFilmes {
	private List<Filme> filmes = new ArrayList<>();

	public void criarFilmes() {
		filmes.add(new Filme(1L, "Um Sonho de Liberdade", List.of("Drama", "Crime"), "Frank Darabont",
				List.of("Tim Robbins", "Morgan Freeman"), data(1994, 9, 22), 142, 15.99));
		filmes.add(new Filme(2L, "O Poderoso Chefão", List.of("Crime", "Drama"), "Francis Ford Coppola",
				List.of("Marlon Brando", "Al Pacino"), data(1972, 3, 24), 175, 12.50));
		filmes.add(new Filme(3L, "O Cavaleiro das Trevas", List.of("Ação", "Crime", "Drama"), "Christopher Nolan",
				List.of("Christian Bale", "Heath Ledger"), data(2008, 7, 18), 152, 17.75));
		filmes.add(new Filme(4L, "Pulp Fiction: Tempo de Violência", List.of("Crime", "Drama"), "Quentin Tarantino",
				List.of("John Travolta", "Uma Thurman"), data(1994, 10, 14), 154, 20.00));
		filmes.add(new Filme(5L, "Forrest Gump: O Contador de Histórias", List.of("Drama", "Romance"), "Robert Zemeckis",
				List.of("Tom Hanks", "Robin Wright"), data(1994, 7, 6), 142, 18.99));
		filmes.add(new Filme(6L, "A Origem", List.of("Ação", "Aventura", "Ficção Científica"), "Christopher Nolan",
				List.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), data(2010, 7, 16), 148, 16.50));
		filmes.add(new Filme(7L, "Clube da Luta", List.of("Drama", "Ação", "Comédia"), "David Fincher",
				List.of("Brad Pitt", "Edward Norton"), data(1999, 10, 15), 139, 14.75));
		filmes.add(new Filme(8L, "Matrix", List.of("Ação", "Ficção Científica"), "Lana Wachowski",
				List.of("Keanu Reeves", "Laurence Fishburne"), data(1999, 3, 31), 136, 19.99));
		filmes.add(new Filme(9L, "O Senhor dos Anéis: O Retorno do Rei", List.of("Ação", "Aventura", "Drama"),
				"Peter Jackson", List.of("Elijah Wood", "Viggo Mortensen"), data(2003, 12, 17), 201, 21.50));
		filmes.add(new Filme(10L, "Interestelar", List.of("Aventura", "Drama", "Ficção Científica"), "Christopher Nolan",
				List.of("Matthew McConaughey", "Anne Hathaway"), data(2014, 11, 7), 169, 26.00));
	}

	public void listarFilmes(List<Filme> filmes) {
		for (int i = 0; i < filmes.size(); i++) {
			Filme f = filmes.get(i);
			System.out.printf("\n%d) \n%s", (i + 1), f);
		}
	}

	public List<Filme> getListaFilmes() {
		return filmes;
	}

	private LocalDate data(int ano, int mes, int dia) {
		return LocalDate.of(ano, mes, dia);
	}

}