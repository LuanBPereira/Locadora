package lofilmes.modelos;

import java.util.Objects;

public class Cliente {
	private final Long CPF;
	private String nome;
	private String sobrenome;
	
	public Cliente(Long cpf, String nome, String sobrenome) {
		CPF = cpf;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	
	public Long getCPF() {
		return CPF;
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
        return CPF.equals(cliente.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CPF);
    }
	
	@Override
	public String toString() {
		return "Cpf: " + CPF + "\nnome: " + nome + "\nsobrenome: " + sobrenome;
	}

	
}