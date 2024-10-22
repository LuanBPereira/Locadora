package testes;

import java.util.HashMap;
import java.util.Map;

import lofilmes.utilidades.GeradorId;

class ClasseA {
	GeradorId geradorId = new GeradorId();

	public Long gerarId() {
		return geradorId.gerarIdAleatorio();

	}
}

class ClasseB {
	GeradorId geradorId = new GeradorId();

	public void criarPessoa(){
		Map<Long, String> pessoa = new HashMap<>();
		
		pessoa.put(gerarId(), "Luan");
		
		System.out.println(pessoa);
	}
	
	public Long gerarId() {
		return geradorId.gerarIdAleatorio();
	}
}

public class TesteGeradorId {

	public static void main(String[] args) {
		
		ClasseA a = new ClasseA();
		ClasseB b = new ClasseB();
		a.gerarId();
		a.gerarId();
		
		b.gerarId();
		b.criarPessoa();
		
		System.out.println("Números gerados ClasseA: " + a.geradorId.getNumerosGerados());
		System.out.println("Números gerados ClasseB: " + b.geradorId.getNumerosGerados());


	}

}
