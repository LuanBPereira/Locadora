// GeradorId.java
package lofilmes.utilidades;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeradorId {
	private static Set<Long> numerosGerados = new HashSet<>();
    private static final long min = 1000L;
    private static final long max = 100000L;


    public static Long gerarIdAleatorio() {
        Long idAleatorio;
        do {
            idAleatorio = min + (long) (Math.random() * (min - max));
        } while (numerosGerados.contains(idAleatorio));

        numerosGerados.add(idAleatorio);
        return idAleatorio;
    }

}
