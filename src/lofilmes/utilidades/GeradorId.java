// GeradorId.java
package lofilmes.utilidades;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeradorId {
	private static Set<Long> numerosGerados = new HashSet<>();
	
    public static Long gerarIdAleatorio() {
        Long idAleatorio;
        do {
            idAleatorio = new Random().nextLong() & Long.MAX_VALUE;
        } while (numerosGerados.contains(idAleatorio));

        numerosGerados.add(idAleatorio);
        return idAleatorio;
    }
    
}
