package testes;

import lofilmes.utilidades.GeradorId;

/*
 *  O gerador de id funcionou, porém, ele pode ser usado por apenas uma classe.
 *  Caso alguma outra usar ele, vai dar problema. Pois a lista que guarda
 *  os ids é estatica. Vou pensar melhor se deixo assim ou deixo ela mais flexivel
 */

class ClasseA {
    public void gerarId(){
        GeradorId.gerarIdAleatorio();
    }
}

class ClasseB {
    public void gerarId(){
        GeradorId.gerarIdAleatorio();
    }
}

public class TesteGeradorId {

    public static void main(String[] args) {
        ClasseA a = new ClasseA();
        ClasseB b = new ClasseB();
        a.gerarId();
        b.gerarId();

        System.out.println();


    }

}
