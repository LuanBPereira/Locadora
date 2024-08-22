package lofilmes.modelos;

public class Cliente {
	private final Long ID;
	private String nome;
	private String sobrenome;
	
	public Cliente(Long iD, String nome, String sobrenome) {
		ID = iD;
		this.nome = nome;
		this.sobrenome = sobrenome;
	}
	
	public Long getID() {
		return ID;
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
	
}