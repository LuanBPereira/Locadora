package lofilmes.utilidades;

import java.util.HashSet;
import java.util.Set;

public class GeradorId {
	private Set<Long> numerosGerados = new HashSet<>();
    private static final long MIN = 1000L;
    private static final long MAX = 100000L;


    public Long gerarIdAleatorio() {
        Long idAleatorio;
        do {
        	idAleatorio = MIN + (long) (Math.random() * (MAX - MIN + 1));
            
        } while (numerosGerados.contains(idAleatorio));

        numerosGerados.add(idAleatorio);
        return idAleatorio;
    }

    public Set<Long> getNumerosGerados() {
    	return numerosGerados;
    }
     	
}
