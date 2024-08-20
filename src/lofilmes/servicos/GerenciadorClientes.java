package lofilmes.servicos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import lofilmes.modelos.*;

public class GerenciadorClientes {

	private Scanner scan = new Scanner(System.in);
	Cliente cliente;

	public Cliente criarCliente() {
		String nome = null, sobrenome = null;
		boolean valido = true;

		while(valido) {
			System.out.println("Antes de continuar, digite seu nome:");
			nome = scan.nextLine().trim();
			
			System.out.println("Ótimo " + nome + ", agora seu sobrenome:");
			sobrenome = scan.nextLine().trim();
			
			if (nome.isEmpty() || sobrenome.isEmpty()) {
                System.err.println("Nome e sobrenome não podem ser vazios "+"("+ "Nome: " + nome +" "+ "Sobrenome: " + sobrenome +")."+ " Tente novamente.");
                continue; 
            } else {
			valido = false;	
            }

		}
		
		return new Cliente(nome, sobrenome);
	}

	public Cliente getCliente() {
		return cliente;
	}
		
	// criar outra classe para esse método
	// não faz sentido um gerenciador de clientes escolher um filme
	public void escolherfilme(CatalogoFilmes filme, DateTimeFormatter FORMATTER, HistoricoLocacoes historico) {
		List<Filme>listaFilmes = filme.getListaFilmes();
		LocalDate data = LocalDate.now();

		Cliente c = criarCliente();
		try {
			System.out.println(
					"Qual filme você gostaria de alugar? (Digite de acordo com o número mostrado no catálogo)");
			int alugar = scan.nextInt();

			if (alugar <= 0 || alugar > listaFilmes.size()) {
				System.out.println("Opção indisponível.");
				return;
			}

			System.out.println("Por quantos dias você quer alugar o filme?");
			int diasAlugado = scan.nextInt();

			if (diasAlugado <= 0) {
				System.out.println("Número de dias inválido.");
				return;
			}

			if (alugar > 0 && alugar <= listaFilmes.size()) {
				Filme filmeEscolhido = listaFilmes.get(alugar - 1);
				System.out.println("Filme '" + filmeEscolhido.getTitulo() + "' escolhido com sucesso!");

				double precoTotal = filmeEscolhido.getPrecoLocacao() * diasAlugado;
				filmeEscolhido.setPrecoLocacao(precoTotal);
				historico.salvar(new Cliente(c.nome(), c.sobrenome()), precoTotal, data, filmeEscolhido.getTitulo(),
						diasAlugado);
			} else {
				System.out.println("Opção indisponível.");

			}
		} catch (InputMismatchException e) {
			System.err.println("Entrada inválida. Por favor, insira um número.");
			scan.nextLine();
		}
	}
	
}
