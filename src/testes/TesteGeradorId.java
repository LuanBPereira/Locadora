package testes;

import lofilmes.utilidades.GeradorId;

class ClasseA {

	public void gerarId1() {
		GeradorId.gerarIdAleatorio();
	}
}

class ClasseB {
	
	public void gerarId2() {
		GeradorId.gerarIdAleatorio();
	}

}

public class TesteGeradorId {

	public static void main(String[] args) {
		
		ClasseA a = new ClasseA();
		ClasseB b = new ClasseB();
		
		a.gerarId1();
		b.gerarId2();
		
		if(GeradorId.getLista().size() > 1) {
			System.out.println("Tamanho da lista: " + GeradorId.getLista().size() + 
					"\nItens da lista: " + GeradorId.getLista());
		}
	}

}
