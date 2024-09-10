package testes;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeradorIdTeste {
	private  Set<Long> numerosGerados = new HashSet<>();
	
	public  Set<Long> getLista() {
		return numerosGerados;
	}
	
    public  Long gerarIdAleatorio() {
        Long idAleatorio;
        do {
            idAleatorio = new Random().nextLong() & Long.MAX_VALUE;
        } while (numerosGerados.contains(idAleatorio));

        numerosGerados.add(idAleatorio);
        return idAleatorio;
    }
  
}