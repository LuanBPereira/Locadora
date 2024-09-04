package lofilmes.utilidades;

public class Formatador {

	public static String formatarCpf(String cpf) {
		if(cpf == null || !cpf.matches("\\d{11}")) {
			throw new IllegalArgumentException("CPF deve conter 11 dígitos numéricos.");
		}
		
	    // formata o CPF para o formato ###.###.###-##
		return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
	}
	
}
