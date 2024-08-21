package lofilmes.servicos;

import java.util.Scanner;

import lofilmes.modelos.Cliente;

public class GerenciadorClientes {

	private Scanner scan = new Scanner(System.in);
	private Cliente cliente;

	// a verificação do nome ou sobrenome vazios, quando cai nela, tá vindo antes
	// da linha 20. Ver como arrumar isso depois, mas a função em si tá funcionando certinho!
	public Cliente criarCliente() {
		while(true) {
			System.out.println("Antes de continuar, digite seu nome:");
			String nome = scan.nextLine().trim();
			
			System.out.println("Ótimo " + nome + ", agora seu sobrenome:");
			String sobrenome = scan.nextLine().trim();
			
			if (nome.isEmpty() || sobrenome.isEmpty()) {
                System.err.println("Nome e sobrenome não podem ser vazios "+"("+ "Nome: " + nome +" "+ "Sobrenome: " + sobrenome +")."+ " Tente novamente.");
            } else {
            	cliente = new Cliente(nome, sobrenome);
            	return cliente;
            }
		}
		
	}

	public Cliente getCliente() {
        if (cliente == null) {
            System.out.println("Nenhum cliente criado. Criando um novo cliente...");
            return criarCliente();
        }
        return cliente;
    }
		
	
}
