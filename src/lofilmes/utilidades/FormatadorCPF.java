package lofilmes.utilidades;

public class FormatadorCPF {

	public static String formatarCpf(String cpf) {
		if(cpf == null || cpf.length() != 11) {
			throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
		}
		
	    // formata o CPF para o formato ###.###.###-##
	    return String.format("%s.%s.%s-%s", 
	        cpf.substring(0, 3),
	        cpf.substring(3, 6),
	        cpf.substring(6, 9),
	        cpf.substring(9, 11));
	    
	}
	
}
