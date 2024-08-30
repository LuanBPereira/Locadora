package lofilmes;

public class App {
	
	public static void main(String[] args) {
		new Locadora().executarLocadora();
	}
	
	/*
	 * pensei agora em fazer o cliente ter um ID como chave em vez do cpf
	 * o cpf sendo um Long, vai ser muito dificil de formatar ele. Não sei nem se é possível
	 * Então, pensei em criar um atributo ID que vai ser unico para cada cliente, como o cpf.
	 * E o cpf ser uma String, para ser mais fácil de formatar.
	 * aí, o ID seria gerado automatico quando o cliente terminasse de alugar o filme.
	 * Todo cliente precisa entrar com o cpf (valor unico: final) para alugar o filme, então, se o cpf já existir
	 * pego a chave que tem o valor do cpf desse cliente cadastrado. Caso não exista, é criado um novo cliente com um
	 * novo id aleatorio e unico.
	 */
}

