package lofilmes.utilidades;

public class ValidadorCliente {

	public static boolean validarCpf(String cpf) {
		String padraoDigitosCpf = "\\d{11}";
		return cpf.matches(padraoDigitosCpf);
	}

	public static boolean validarNome(String nome) {
		return nome.matches("[a-zA-Z]+");
	}

	public static boolean isNomeSobrenomeVazio(String nome, String sobrenome) {
		return nome.isEmpty() || sobrenome.isEmpty();
	}

}
