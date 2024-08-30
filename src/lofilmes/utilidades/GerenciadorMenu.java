package lofilmes.utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciadorMenu {
	
	private Scanner scan;
	
	public GerenciadorMenu(Scanner scan){
		this.scan = scan;
	}
	
	public int mostrarMenu(String menu) {
		int escolha = 0;
		boolean escolhaValida = false;

		while (!escolhaValida) {
			try {
				System.out.println(menu);
				escolha = scan.nextInt();
				scan.nextLine();

				escolhaValida = true;
			} catch (InputMismatchException e) {
				System.err.println("Erro: Digite um dos números mostrados no catálogo.");
				scan.nextLine();
			}
		}
		
		return escolha;
	}

}
