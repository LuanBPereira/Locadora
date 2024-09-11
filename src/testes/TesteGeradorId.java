package testes;

import java.util.HashMap;
import java.util.Map;

import lofilmes.utilidades.GeradorId;

/*
 *  O gerador de id funcionou, porém, ele pode ser usado por apenas uma classe.
 *  Caso alguma outra usar ele, vai dar problema. Pois a lista que guarda
 *  os ids é estatica. Vou pensar melhor se deixo assim ou deixo ela mais flexivel
 */

class ClasseA {
	GeradorId geradorId = new GeradorId();

	public Long gerarId() {
		return geradorId.gerarIdAleatorio();

	}
}

class ClasseB {
	GeradorId geradorId = new GeradorId();

	public Map<Long, String> criarPessoa (){
		Map<Long, String> Pessoa = new HashMap<>();
	}
	
	public Long gerarId() {
		return geradorId.gerarIdAleatorio();
	}
}

public class TesteGeradorId {

	public static void main(String[] args) {
		GeradorId geradorId = new GeradorId();
		ClasseA a = new ClasseA();
		ClasseB b = new ClasseB();
		a.gerarId();
		a.gerarId();
		b.gerarId();

		System.out.println("Números gerados ClasseA: " + a.geradorId.getNumerosGerados());
		System.out.println("Números gerados ClasseB: " + b.geradorId.getNumerosGerados());
		System.out.println("Números guardado lista Geral: " + geradorId.getNumerosGerados());

	}

}
