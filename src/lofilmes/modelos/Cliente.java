package lofilmes.modelos;

import java.util.Objects;

public class Cliente {
	private final Long id;
	private final String cpf;
	private String nome;
	private String sobrenome;

	public Cliente(Long id, String cpf, String nome, String sobrenome) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}

	public Long getId() {
		return id;
	}

	public String getCPF() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getNomeCompleto() {
		return nome + " " + sobrenome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cliente cliente = (Cliente) o;
		return id.equals(cliente.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return String.format("Cliente [\nId: %d\nCpf: %s\nCliente: %s\n]\n", id, cpf, getNomeCompleto());
	}

}