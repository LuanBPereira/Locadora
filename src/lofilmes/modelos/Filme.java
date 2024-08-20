package lofilmes.modelos;

import java.time.LocalDate;
import java.util.List;

public class Filme {
	String titulo;
	List<String> categorias;
	String diretor;
	List<String> atores;
	LocalDate dataLancamento;
	int duracao;
	double precoLocacao;
	
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
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public List<String> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
	
	public String getDiretor() {
		return diretor;
	}
	
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	
	public List<String> getAtores() {
		return atores;
	}
	
	public void setAtores(List<String> atores) {
		this.atores = atores;
	}
	
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	
	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	public double getPrecoLocacao() {
		return precoLocacao;
	}
	
	public void setPrecoLocacao(double precoLocacao) {
		this.precoLocacao = precoLocacao;
	}
	
}
