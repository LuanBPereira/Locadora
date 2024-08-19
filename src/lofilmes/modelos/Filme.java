package lofilmes.modelos;

import java.time.LocalDate;
import java.util.List;

public record Filme(String titulo, List<String> categorias,
		String diretor,List<String> atores,
		LocalDate dataLancamento, int duracao, double precoLocacao) {}


