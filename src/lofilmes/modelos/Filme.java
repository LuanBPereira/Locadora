package lofilmes.modelos;

import java.time.LocalDate;
import java.util.List;

public class Filme {
	private String titulo;
	private List<String> categorias;
	private String diretor;
	private List<String> atores;
	private LocalDate dataLancamento;
	private int duracao;
	private double precoLocacao;
	
	public Filme(String titulo, List<String> categorias, String diretor,
			List<String> atores, LocalDate dataLancamento, int duracao, double precoLocacao) {
		this.titulo = titulo;
		this.categorias = categorias;
		this.diretor = diretor;
		this.atores = atores;
		this.dataLancamento = dataLancamento;
		this.duracao = duracao;
		this.precoLocacao = precoLocacao;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public List<String> getCategorias() {
		return categorias;
	}
	
	public String getDiretor() {
		return diretor;
	}

	public List<String> getAtores() {
		return atores;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	public double getPrecoLocacao() {
		return precoLocacao;
	}
	
}
