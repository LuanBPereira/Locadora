package lofilmes.utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciadorMenu {
	
	static Scanner scan = new Scanner(System.in);
	
	public static int mostrarMenu(Scanner scan, String menu) {
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
