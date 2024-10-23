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
	
	public Long getID() {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
	
	@Override
	public String toString() {
		return String.format("Cpf: %s\n Cliente: %s\n", cpf, getNomeCompleto());
	}

	
}