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
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Título: '%s'\n", getTitulo()));
		sb.append(String.format("Categorias: '%s'\n", String.join(", ", getCategorias())));
		sb.append(String.format("Diretor: '%s'\n", getDiretor()));
		sb.append(String.format("Atores: '%s'\n", String.join(", ", getAtores())));
		sb.append(String.format("Data de Lançamento: '%s'\n", getDataLancamento().format(Formatador.DATA_FORMATADA)));
		sb.append(String.format("Duração: '%d min'\n", getDuracao()));
		sb.append(String.format("Preço de Locação: 'R$%.2f'\n", getPrecoLocacao()));

		return sb.toString();
	}

}
