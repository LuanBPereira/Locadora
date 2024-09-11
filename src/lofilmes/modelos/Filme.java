package lofilmes.modelos;

import java.time.LocalDate;
import java.util.List;

import lofilmes.utilidades.Formatador;

public class Filme {
	private String titulo;
	private List<String> categorias;
	private String diretor;
	private List<String> atores;
	private LocalDate dataLancamento;
	private int duracao;
	private double precoLocacao;

	public Filme(String titulo, List<String> categorias, String diretor, List<String> atores, LocalDate dataLancamento,
			int duracao, double precoLocacao) {
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

	@Override
	public String toString() {
		return "Título: " + getTitulo() + "\n" +
				"Categorias: " + String.join(", ", getCategorias()) + "\n" +
				"Diretor: " + getDiretor() + "\n" +
				"Atores: " + String.join(", ", getAtores()) + "\n" +
				"Data de Lançamento: " + getDataLancamento().format(Formatador.FORMATO_DATA_BR) + "\n" +
				"Duração: " + getDuracao() + " min\n" +
				"Preço de Locação: R$ " + String.format("%.2f", getPrecoLocacao()) + "\n";
	}

}