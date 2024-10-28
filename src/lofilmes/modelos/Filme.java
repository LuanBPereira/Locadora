package lofilmes.modelos;

import java.time.LocalDate;
import java.util.List;

import lofilmes.utilidades.Formatador;
import lofilmes.utilidades.Identificavel;

public class Filme implements Identificavel{
	private final Long id;
	private String titulo;
	private List<String> categorias;
	private String diretor;
	private List<String> atores;
	private LocalDate dataLancamento;
	private int duracao;
	private double precoLocacao;

	public Filme(Long id, String titulo, List<String> categorias, String diretor, List<String> atores, LocalDate dataLancamento,
			int duracao, double precoLocacao) {
		this.id = id;
		this.titulo = titulo;
		this.categorias = categorias;
		this.diretor = diretor;
		this.atores = atores;
		this.dataLancamento = dataLancamento;
		this.duracao = duracao;
		this.precoLocacao = precoLocacao;
	}

	@Override
	public Long getId() {
		return id;
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
		return String.format("Filme [\nId: %d\nTítulo: %s\nCategorias: %s\nDiretor: %s\nAtores: %s\nData de lançamento: %s\nDuração: %d\nPreco de locação: R$%.2f\n]\n", getId(),
				getTitulo(),  String.join(", ", getCategorias()), getDiretor(), String.join(", ", getAtores()),
				getDataLancamento().format(Formatador.FORMATO_DATA_BR), getDuracao(), getPrecoLocacao());
	}
}