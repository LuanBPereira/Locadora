package lofilmes.servicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lofilmes.modelos.Cliente;
import lofilmes.modelos.DadosLocacao;
import lofilmes.modelos.Filme;

public class HistoricoLocacoes {

	private List<DadosLocacao> historico = new ArrayList<>();

 	public void salvar(Long idLocacao, Cliente cliente, Filme filme, LocalDate data, double valorPago, int diasAlugado) {
		DadosLocacao dadosLocacao = new DadosLocacao(idLocacao, cliente, filme, data, valorPago, diasAlugado);
		historico.add(dadosLocacao);
	}

	public List<DadosLocacao> getHistorico() {
		return historico;
	}
	
	public List<DadosLocacao> getFilmesLocadosNosUltimos7Dias() {
		LocalDate dataAtual = LocalDate.now();
		List<DadosLocacao> alugadosRecentes = new ArrayList<>();

		for (DadosLocacao dadosL : historico) {
			LocalDate dataCompra = dadosL.data();
			if (!dataCompra.isBefore(dataAtual.minusDays(7))) {
				alugadosRecentes.add(dadosL);
			}
		}

		return alugadosRecentes;
	}
	
	public List<String> getFilmesMaisLocados() {
	    Map<String, Integer> contagemFilmes = new HashMap<>();
	    List<String> filmesMaisLocados = new ArrayList<>();
	    int contagemMax = 0;

	    for (DadosLocacao dadosL : historico) {
	        String titulo = dadosL.filme().getTitulo();
	        int contagemAtual = contagemFilmes.getOrDefault(titulo, 0) + 1;
	        contagemFilmes.put(titulo, contagemAtual);

	        /*
	         * Apenas gostaria de explicar o porquê de ter usado o clear() na lista de cliente que mais locou:
	         * se a contagem do cliente foir maior que a contagemMax, a contagemMax recebe o numero de vezes
	         * que esse cliente apareceu, limpa a lista de cliente que mais locou. 
	         * Isso é para evitar que um cliente anterior que mais locou apareça de novo. 
	         * Por exemplo, clienteA alugou 3x e o clienteB 4x,
	         * o clienteA apareceria junto com o clienteB, o que não faz sentido. Já que o cliente A foi que mais locou.
	         */
	        
	        // se encontramos um novo máximo, limpamos a lista e adicionamos o filme
	        if (contagemAtual > contagemMax) {
	            contagemMax = contagemAtual;
	            filmesMaisLocados.clear();  
	            filmesMaisLocados.add(titulo);
	        } 
	        // se a contagem é igual ao máximo, apenas adicionar à lista
	        else if (contagemAtual == contagemMax) {
	            filmesMaisLocados.add(titulo);
	        }
	    }

	    return filmesMaisLocados;
	}


	public double getValorTotalLocacoesUltimoMes() {
		LocalDate dataAtual = LocalDate.now();
		double total = 0.0;

		for (DadosLocacao dadosL : historico) {
			LocalDate dataLocacao = dadosL.data();
			if (dataLocacao.getMonthValue() == dataAtual.getMonthValue() &&
					dataLocacao.getYear() == dataAtual.getYear()) {
				total += dadosL.valorPago();
			}
		}
		return total;
	}

	public List<String> getClienteQueMaisLocou(GestaoClientes gerenciadorClientes) {
	    Map<Long, Integer> contagemLocacaoCliente = new HashMap<>();
	    List<String> clienteQueMaisLocou = new ArrayList<>();
	    int contagemMax = 0;

	    // contagem de locações por cliente e identificação dos que mais locaram
	    for (DadosLocacao dadosL : historico) {
	        Long idCliente = dadosL.cliente().getId();
	        int contagemAtual = contagemLocacaoCliente.getOrDefault(idCliente, 0) + 1;
	        contagemLocacaoCliente.put(idCliente, contagemAtual);
	        
	        // se encontramos um novo máximo, limpamos a lista e adicionamos o cliente
	        if (contagemAtual > contagemMax) {
	            contagemMax = contagemAtual;
	            clienteQueMaisLocou.clear(); // explicação do porquê usar o clear() no metodo getFilmesMaisLocados(). (linha 49 - 56)
	            Cliente cliente = gerenciadorClientes.getClientePorId(idCliente);
	            if (cliente != null) {
	                clienteQueMaisLocou.add(cliente.getNomeCompleto());
	            }
	         // se a contagem é igual ao máximo, apenas adicionar à lista
	        } else if (contagemAtual == contagemMax) {
	            Cliente cliente = gerenciadorClientes.getClientePorId(idCliente);
	            if (cliente != null) {
	                clienteQueMaisLocou.add(cliente.getNomeCompleto());
	            }
	        }
	    }

	    return clienteQueMaisLocou;
	}

	
}
