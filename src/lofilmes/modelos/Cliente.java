package lofilmes.modelos;

import java.util.Objects;

public class Cliente {
	private final Long CPF;
	private String nome;
	private String sobrenome;
	
	public Cliente(Long iD, String nome, String sobrenome) {
		CPF = iD;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	
	public Long getID() {
		return CPF;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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
		return "Id: " + CPF + "\nnome: " + nome + "\nsobrenome: " + sobrenome;
	}

	
}