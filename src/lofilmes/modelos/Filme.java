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

	/*
	 *	Achei um jeito interessante de fazer o metodo toString
	 * 	no StackOverflow, achei robusto e menos propicio a dar erros.
	 * 	Porém, tem coisas que não entendi muito bem, então quero dar
	 * 	umas olhadas a mais, mas vou deixar salvo aqui para revisar.
	 *
	 *	@Override
	 *	public String toString() {
	 *	StringBuilder sb = new StringBuilder();
	 *	sb.append("Título: ").append(getTitulo() != null ? getTitulo() : "N/A").append("\n");
	 *	sb.append("Categorias: ").append(getCategorias() != null ? String.join(", ", getCategorias()) : "N/A").append("\n");
	 *	sb.append("Diretor: ").append(getDiretor() != null ? getDiretor() : "N/A").append("\n");
	 *	sb.append("Atores: ").append(getAtores() != null ? String.join(", ", getAtores()) : "N/A").append("\n");
	 *	sb.append("Data de Lançamento: ").append(getDataLancamento() != null ? getDataLancamento().format(Formatador.DATA_FORMATADA) : "N/A").append("\n");
	 *	sb.append("Duração: ").append(getDuracao()).append(" min\n");
	 *	sb.append("Preço de Locação: R$").append(String.format("%.2f", getPrecoLocacao())).append("\n");
	 *
	 *	return sb.toString();
	 *	}
	 */
}
