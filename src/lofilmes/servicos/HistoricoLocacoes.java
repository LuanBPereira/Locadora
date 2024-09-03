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

	public void salvar(Cliente cliente, double valorPago, LocalDate data, Filme filme, int diasAlugado) {
		historico.add(new DadosLocacao(cliente, valorPago, data, filme, diasAlugado));
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

			if (contagemAtual > contagemMax) {
				contagemMax = contagemAtual;
			}
		}

		for (Map.Entry<String, Integer> entry : contagemFilmes.entrySet()) {
			String filme = entry.getKey();
			int contagem = entry.getValue();
			
			if (contagem == contagemMax) {
				filmesMaisLocados.add(filme);
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

	public List<String> getClienteQueMaisLocou(GerenciadorClientes gerenciadorClientes) {
	    Map<Long, Integer> contagemLocacaoCliente = new HashMap<>();
	    List<String> clienteQueMaisLocou = new ArrayList<>();
	    int contagemMax = 0;

	    // contagem de locações por cliente
	    for (DadosLocacao dadosL : historico) {
	        Long idCliente = dadosL.cliente().getID();
	        int contagemAtual = contagemLocacaoCliente.getOrDefault(idCliente, 0) + 1;
	        contagemLocacaoCliente.put(idCliente, contagemAtual);

	        if(contagemAtual > contagemMax) {
	        	contagemMax = contagemAtual;
	        }
	    }

	    // identificar o(s) cliente(s) que mais locaram
	    for (Map.Entry<Long, Integer> entry : contagemLocacaoCliente.entrySet()) {
	        Long id = entry.getKey();
	        int contagem = entry.getValue();
	        
	        if (contagem == contagemMax) {
	            Cliente cliente = gerenciadorClientes.getClientePorId(id);
	            if (cliente != null) {
	                String nomeCompleto = cliente.getNomeCompleto();
	                clienteQueMaisLocou.add(nomeCompleto);
	            }
	        }
	    }

	    return clienteQueMaisLocou;
	}
	
}
